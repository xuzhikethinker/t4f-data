package aos.kafka;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.javaapi.producer.ProducerData;
import kafka.producer.ProducerConfig;

import com.google.common.collect.Lists;

public class KafkaProducerMain {

    public static void main(String... args) {

        Properties props = new Properties();
        props.put("zk.connect", "127.0.0.1:2181");
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        // props.put("partitioner.class", "aos.kafka.MemberIdPartitioner");
        // props.put("serializer.class", "aos.kafka.TrackingDataSerializer");

        ProducerConfig config = new ProducerConfig(props);
        Producer<String, String> producer = new Producer<String, String>(config);

        ProducerData<String, String> data = new ProducerData<String, String>(KafkaQueue.QUEUE_TEST_1.name(),
                "test-message-0");
        producer.send(data);

        List<String> messages = new ArrayList<String>();
        messages.add("test-message-1");
        messages.add("test-message-2");

        ProducerData<String, String> producerData1 = new ProducerData<String, String>(KafkaQueue.QUEUE_TEST_1.name(),
                messages);
        ProducerData<String, String> producerData2 = new ProducerData<String, String>(KafkaQueue.QUEUE_TEST_2.name(),
                messages);

        List<ProducerData<String, String>> producerDataList = Lists.newArrayList(producerData1, producerData2);
        producer.send(producerDataList);

        producer.close();

    }

}
