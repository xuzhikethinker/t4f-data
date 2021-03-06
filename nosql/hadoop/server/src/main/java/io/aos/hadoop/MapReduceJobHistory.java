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
package io.aos.hadoop;

import ios.aos.summer.shell.process.AosProcessLauncher;

import java.util.concurrent.TimeUnit;

import org.apache.hadoop.mapreduce.v2.hs.JobHistoryServer;
import org.apache.log4j.Logger;

/**
 * JobHistoryServer ( threads) java -Dproc_historyserver -Xmx1000m
 * -Djava.net.preferIPv4Stack=true -Xmx128m
 * -Dhadoop.log.dir=/d/opt/hadoop-3.0.0-SNAPSHOT/logs
 * -Dhadoop.log.file=yarn-echarles-historyserver-echarles.log
 * -Dhadoop.home.dir=/d/opt/hadoop-3.0.0-SNAPSHOT -Dhadoop.id.str=echarles
 * -Dhadoop.root.LOGGER=INFO,RFA -Dhadoop.policy.file=hadoop-policy.xml
 * -Djava.net.preferIPv4Stack=true -Dmapred.jobsummary.LOGGER=INFO,JSA
 * -Dhadoop.security.LOGGER=INFO,NullAppender
 * org.apache.hadoop.mapreduce.v2.hs.JobHistoryServer
 */
public class MapReduceJobHistory {
    private static final Logger LOGGER = Logger.getLogger(MapReduceJobHistory.class);

    public static void main(String... args) throws Exception {

        new AosProcessLauncher() {
            @Override
            public void process() throws Exception {
                JobHistoryServer.main(new String[] {});
            }
        }.launch();

        while (true) {
            LOGGER.info("Sleeping...");
            TimeUnit.MINUTES.sleep(1);
        }

    }

}
