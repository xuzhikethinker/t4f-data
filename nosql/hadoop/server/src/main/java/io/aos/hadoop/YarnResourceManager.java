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

import org.apache.hadoop.yarn.server.resourcemanager.ResourceManager;
import org.apache.log4j.Logger;

/**
 * ResourceManager (190 threads) java -Dproc_resourcemanager -Xmx1000m -Xdebug
 * -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=n
 * -Dhadoop.log.dir=/d/opt/hadoop-3.0.0-SNAPSHOT/logs
 * -Dyarn.log.dir=/d/opt/hadoop-3.0.0-SNAPSHOT/logs -Dhadoop.log.file=yarn.log
 * -Dyarn.log.file=yarn.log -Dyarn.home.dir= -Dyarn.id.str=
 * -Dhadoop.root.LOGGER=INFO,console -Dyarn.root.LOGGER=INFO,console
 * -Dyarn.policy.file=hadoop-policy.xml
 * -Dhadoop.log.dir=/d/opt/hadoop-3.0.0-SNAPSHOT/logs
 * -Dyarn.log.dir=/d/opt/hadoop-3.0.0-SNAPSHOT/logs
 * -Dhadoop.log.file=yarn-echarles-resourcemanager-echarles.log
 * -Dyarn.log.file=yarn-echarles-resourcemanager-echarles.log -Dyarn.home.dir=
 * -Dyarn.id.str=echarles -Dhadoop.root.LOGGER=INFO,RFA
 * -Dyarn.root.LOGGER=INFO,RFA -Dyarn.policy.file=hadoop-policy.xml
 * -Dhadoop.log.dir=/d/opt/hadoop-3.0.0-SNAPSHOT/logs
 * -Dyarn.log.dir=/d/opt/hadoop-3.0.0-SNAPSHOT/logs
 * -Dhadoop.log.file=yarn-echarles-resourcemanager-echarles.log
 * -Dyarn.log.file=yarn-echarles-resourcemanager-echarles.log
 * -Dyarn.home.dir=/d/opt/hadoop-3.0.0-SNAPSHOT
 * -Dhadoop.home.dir=/d/opt/hadoop-3.0.0-SNAPSHOT -Dhadoop.root.LOGGER=INFO,RFA
 * -Dyarn.root.LOGGER=INFO,RFA -classpath
 * /d/opt/hadoop-3.0.0-SNAPSHOT/etc/hadoop
 * :/d/opt/hadoop-3.0.0-SNAPSHOT/etc/hadoop
 * :/d/opt/hadoop-3.0.0-SNAPSHOT/etc/hadoop
 * :/d/opt/hadoop-3.0.0-SNAPSHOT/share/hadoop
 * /common/lib/*:/d/opt/hadoop-3.0.0-SNAPSHOT
 * /share/hadoop/common/*:/d/opt/hadoop
 * -3.0.0-SNAPSHOT/contrib/capacity-scheduler
 * /*.jar:/d/opt/hadoop-3.0.0-SNAPSHOT/
 * contrib/capacity-scheduler/*.jar:/d/opt/hadoop
 * -3.0.0-SNAPSHOT/contrib/capacity
 * -scheduler/*.jar:/d/opt/hadoop-3.0.0-SNAPSHOT/
 * contrib/capacity-scheduler/*.jar
 * :/d/opt/hadoop-3.0.0-SNAPSHOT/share/hadoop/hdfs
 * :/d/opt/hadoop-3.0.0-SNAPSHOT/share
 * /hadoop/hdfs/lib/*:/d/opt/hadoop-3.0.0-SNAPSHOT
 * /share/hadoop/hdfs/*:/d/opt/hadoop
 * -3.0.0-SNAPSHOT/share/hadoop/mapreduce/lib/*
 * :/d/opt/hadoop-3.0.0-SNAPSHOT/share
 * /hadoop/mapreduce/*:/d/opt/hadoop-3.0.0-SNAPSHOT
 * /share/hadoop/mapreduce/*:/d/opt
 * /hadoop-3.0.0-SNAPSHOT/share/hadoop/mapreduce/
 * lib/*:/d/opt/hadoop-3.0.0-SNAPSHOT/etc/hadoop/rm-config/log4j.properties
 * org.apache.hadoop.yarn.server.resourcemanager.ResourceManager
 */
public class YarnResourceManager {
    private static final Logger LOGGER = Logger.getLogger(YarnResourceManager.class);

    public static void main(String[] args) throws Exception {

        new AosProcessLauncher() {
            @Override
            public void process() throws Exception {
                ResourceManager.main(new String[] {});
            }
        }.launch();

        while (true) {
            LOGGER.info("Sleeping...");
            TimeUnit.MINUTES.sleep(1);
        }

    }

}
