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
package aos.hbase;

import org.apache.hadoop.hbase.master.HMaster;
import org.apache.hadoop.hbase.regionserver.HRegionServer;
import org.apache.hadoop.hbase.zookeeper.HQuorumPeer;
import org.apache.log4j.Logger;

/**
 * <p>
 * Simple class that launches a Zookeeper server, HBase Master and an HBase Region Server.
 * 
 * It holds the thread so that you can use the HBase console to test the
 * connection, create tables...
 * 
 * It is aimed for demo purposes to highlight the various components and is
 * not intended to be run as a real cluster.
 * 
 * The configuration can be defined in the src/main/resources/hbase-site.xml
 * file.
 * </p>
 */
public class HBaseServerLauncher {
    private static final Logger LOGGER = Logger.getLogger(HBaseServerLauncher.class);

    public static void main(String... args) throws Exception {
        
        runHBaseProcess(new HBaseProcessRunner() {
            @Override
            public void run() throws Exception {
                HQuorumPeer.main(new String[] { "start" });
            }
        });

        runHBaseProcess(new HBaseProcessRunner() {
            @Override
            public void run() throws Exception {
                HRegionServer.main(new String[] { "start" });
            }
        });

        runHBaseProcess(new HBaseProcessRunner() {
            @Override
            public void run() throws Exception {
                HMaster.main(new String[] { "start" });
            }
        });

        while (true) {
            LOGGER.info("Sleeping...");
            Thread.sleep(10000);
        }

    }
    
    private static void runHBaseProcess(final HBaseProcessRunner hBaseProcessRunner) {
        Thread mt = new Thread(new Runnable() {
            @Override
            public void run() {
                LOGGER.info("Trying to start...");
                try {
                    hBaseProcessRunner.run();
                    while (true) {
                        LOGGER.info("Sleeping...");
                        Thread.sleep(10000);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        mt.start();
    }
    
    public static interface HBaseProcessRunner {
        public void run() throws Exception;
    }

}
