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

import org.apache.hadoop.hdfs.server.namenode.NameNode;
import org.apache.log4j.Logger;

/**
 * NameNode (37 threads) java -Dproc_namenode -Xmx1000m
 * -Djava.net.preferIPv4Stack=true -Xmx128m -Xmx128m
 * -Dhadoop.log.dir=/d/opt/hadoop-3.0.0-SNAPSHOT/logs
 * -Dhadoop.log.file=hadoop-echarles-namenode-echarles.log
 * -Dhadoop.home.dir=/d/opt/hadoop-3.0.0-SNAPSHOT -Dhadoop.id.str=echarles
 * -Dhadoop.root.LOGGER=INFO,RFA -Dhadoop.policy.file=hadoop-policy.xml
 * -Djava.net.preferIPv4Stack=true -Dhadoop.security.LOGGER=INFO,RFAS
 * -Dhdfs.audit.LOGGER=INFO,NullAppender -Dhadoop.security.LOGGER=INFO,RFAS
 * -Dhdfs.audit.LOGGER=INFO,NullAppender -Dhadoop.security.LOGGER=INFO,RFAS
 * -Dhdfs.audit.LOGGER=INFO,NullAppender -Dhadoop.security.LOGGER=INFO,RFAS
 * org.apache.hadoop.hdfs.server.namenode.NameNode
 */
public class HdfsNameNode {
    private static final Logger LOGGER = Logger.getLogger(HdfsNameNode.class);

    public static void main(String... args) throws Exception {

        new AosProcessLauncher() {
            @Override
            public void process() throws Exception {
                NameNode.main(new String[] {});
            }
        }.launch();

        while (true) {
            LOGGER.info("Sleeping...");
            TimeUnit.MINUTES.sleep(1);
        }

    }

}
