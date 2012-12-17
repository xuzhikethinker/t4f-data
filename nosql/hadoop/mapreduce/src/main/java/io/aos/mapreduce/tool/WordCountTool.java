/****************************************************************
 * Licensed to the AOS Community (AOS) under one or more        *
 * contributor license agreements.  See the NOTICE file         *
 * distributed with this work for additional information        *
 * regarding copyright ownership.  The AOS licenses this file   *
 * to you under the Apache License, Version 2.0 (the            *
 * "License"); you may not use this file except in compliance   *
 * with the License.  You may obtain a copy of the License at   *
 *                                                              *
 *   http://www.apache.org/licenses/LICENSE-2.0                 *
 *                                                              *
 * Unless required by applicable law or agreed to in writing,   *
 * software distributed under the License is distributed on an  *
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY       *
 * KIND, either express or implied.  See the License for the    *
 * specific language governing permissions and limitations      *
 * under the License.                                           *
 ****************************************************************/
package io.aos.mapreduce.tool;

import java.io.IOException;
import java.util.Random;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.map.InverseMapper;
import org.apache.hadoop.mapreduce.lib.map.RegexMapper;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.hadoop.mapreduce.lib.reduce.LongSumReducer;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class WordCountTool extends Configured implements Tool {

    private WordCountTool() { 
        /* Singleton */
    }

    public static class WordCountMapper extends Mapper<Object, Text, Text, IntWritable> {
        private final static IntWritable one = new IntWritable(1);
        private Text word = new Text();

        public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            context.getCounter("MyCounterGroup", "MAP_INPUT_RECORDS").increment(1);
            StringTokenizer iter = new StringTokenizer(value.toString());
            while (iter.hasMoreTokens()) {
                word.set(iter.nextToken());
                context.write(word, one);
                context.getCounter("MyCounterGroup", "MAP_OUTPUT_RECORDS").increment(1);
            }
        }
    }

    public static class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
        private IntWritable result = new IntWritable();

        public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException,
                InterruptedException {
            context.getCounter("MyCounterGroup", "REDUCE_INPUT_GROUPS").increment(1);
            int sum = 0;
            for (IntWritable val : values) {
                sum += val.get();
            }
            result.set(sum);
            context.write(key, result);
            context.getCounter("MyCounterGroup", "REDUCE_OUTPUT_RECORDS").increment(1);
        }
    }

    public int run(String[] args) throws Exception {
        if (args.length < 3) {
            System.out.println("Grep <inDir> <outDir> <regex> [<group>]");
            ToolRunner.printGenericCommandUsage(System.out);
            return 2;
        }

        Path tempDir = new Path("grep-temp-" + Integer.toString(new Random().nextInt(Integer.MAX_VALUE)));

        Configuration conf = getConf();
        conf.set(RegexMapper.PATTERN, args[2]);
        if (args.length == 4)
            conf.set(RegexMapper.GROUP, args[3]);

        Job grepJob = new Job(conf);

        try {

            grepJob.setJobName("grep-search");

            FileInputFormat.setInputPaths(grepJob, args[0]);

            grepJob.setMapperClass(RegexMapper.class);

            grepJob.setCombinerClass(LongSumReducer.class);
            grepJob.setReducerClass(LongSumReducer.class);

            FileOutputFormat.setOutputPath(grepJob, tempDir);
            grepJob.setOutputFormatClass(SequenceFileOutputFormat.class);
            grepJob.setOutputKeyClass(Text.class);
            grepJob.setOutputValueClass(LongWritable.class);

            grepJob.waitForCompletion(true);

            Job sortJob = new Job(conf);
            sortJob.setJobName("grep-sort");

            FileInputFormat.setInputPaths(sortJob, tempDir);
            sortJob.setInputFormatClass(SequenceFileInputFormat.class);

            sortJob.setMapperClass(InverseMapper.class);

            sortJob.setNumReduceTasks(1); // write a single file
            FileOutputFormat.setOutputPath(sortJob, new Path(args[1]));
            sortJob.setSortComparatorClass( // sort by decreasing freq
            LongWritable.DecreasingComparator.class);

            sortJob.waitForCompletion(true);
        } finally {
            FileSystem.get(conf).delete(tempDir, true);
        }
        return 0;
    }

    public static void main(String[] args) throws Exception {
        int res = ToolRunner.run(new Configuration(), new WordCountTool(), args);
        System.exit(res);
    }

}
