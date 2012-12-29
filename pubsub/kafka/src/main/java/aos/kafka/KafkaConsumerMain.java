/****************************************************************
 * Licensed to the AOS Community (AOS) under one or more        *
 * contributor license agreements.  See the NOTICE file         *
 * distributed with this work for additional information        *
 * regarding copyright ownership. The AOS licenses this file   *
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
package aos.kafka;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.Message;

public class KafkaConsumerMain extends Thread {

    public static void main(String[] args) {

        ConsumerConnector consumer;
        String topic;

        consumer = kafka.consumer.Consumer.createJavaConsumerConnector(createConsumerConfig());
        topic = "f";

        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(topic, new Integer(1));
        Map<String, List<KafkaStream<Message>>> consumerMap = consumer.createMessageStreams(topicCountMap);
        KafkaStream<Message> stream = consumerMap.get(topic).get(0);
        ConsumerIterator<Message> it = stream.iterator();
        while (it.hasNext()) {
            System.out.println(getMessage(it.next().message()));
        }

        // KAFKA 0.8
        // Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        // topicCountMap.put(topic, new Integer(1));
        // Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap =
        // consumer.createMessageStreams(topicCountMap);
        // KafkaStream<byte[], byte[]> stream = consumerMap.get(topic).get(0);
        //
        // ConsumerIterator<byte[], byte[]> it = stream.iterator();
        // while (it.hasNext()) {
        // System.out.println(getMessage(it.next().message()));
        // }

    }

    public static String getMessage(Message message) {
        ByteBuffer buffer = message.payload();
        byte[] bytes = new byte[buffer.remaining()];
        buffer.get(bytes);
        return new String(bytes);
    }

    private static ConsumerConfig createConsumerConfig() {
        Properties props = new Properties();
        props.put("zk.connect", "zookeeper.qutics.com:2181");
        props.put("groupid", "CHANGEME");
        props.put("zk.sessiontimeout.ms", "800");
        props.put("zk.synctime.ms", "300");
        props.put("autocommit.interval.ms", "1000");

        return new ConsumerConfig(props);

    }
}