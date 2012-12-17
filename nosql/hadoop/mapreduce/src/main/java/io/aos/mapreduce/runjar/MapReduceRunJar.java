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
package io.aos.mapreduce.runjar;

import ios.aos.summer.shell.process.AosProcessLauncher;

import java.io.File;
import java.io.FileFilter;

import org.apache.hadoop.util.RunJar;
import org.apache.log4j.Logger;

/**
 * RunJar (... threads)
 * 
 * java -Xmx1000m -Djava.net.preferIPv4Stack=true
 * -Dhadoop.log.dir=/d/opt/hadoop-3.0.0-SNAPSHOT/logs
 * -Dhadoop.log.file=hadoop.log -Dhadoop.home.dir=/d/opt/hadoop-3.0.0-SNAPSHOT
 * -Dhadoop.id.str=echarles -Dhadoop.root.logger=INFO,console
 * -Dhadoop.policy.file=hadoop-policy.xml -Djava.net.preferIPv4Stack=true
 * -Dhadoop.security.logger=INFO,NullAppender org.apache.hadoop.util.RunJar
 * ./share/hadoop/mapreduce/hadoop-mapreduce-examples-3.0.0-SNAPSHOT.jar
 * wordcount site site-out2
 * 
 * 
 * java -Dlog4j.configuration=container-log4j.properties
 * -Dyarn.app.mapreduce.container.log.dir=<LOG_DIR>
 * -Dyarn.app.mapreduce.container.log.filesize=0 -Dhadoop.root.logger=INFO,CLA
 * -Xmx1024m org.apache.hadoop.mapreduce.v2.app.MRAppMaster S 1><LOG_DIR>/stdout
 * 2><LOG_DIR>/stderr
 */
public class MapReduceRunJar {
    private static Logger logger = Logger.getLogger(MapReduceRunJar.class);

    public static void main(final String... args) throws Exception {

        File jarFolder = new File(System.getenv("HADOOP_HOME") + "/share/hadoop/mapreduce/");
        final File[] jar = jarFolder.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().startsWith("hadoop-mapreduce-examples-");
            }
        });

        if (jar.length != 1) {
            throw new IllegalStateException("Can't find any hadoop-mapreduce-examples jar in " + jarFolder
                    + " - Check your $HADOOP_HOME");
        }

        new AosProcessLauncher() {
            @Override
            public void process() throws Exception {
                try {
                    String[] rjArgs = new String[args.length + 1];
                    rjArgs[0] = jar[0].getAbsolutePath();
                    System.arraycopy(args, 0, rjArgs, 1, args.length);
                    RunJar.main(rjArgs);
                } catch (Throwable t) {
                    throw new Exception(t);
                }
            }
        }.launch();

        // while (true) {
        // logger.info("Sleeping...");
        // TimeUnit.MINUTES.sleep(1);
        // }

    }

}
