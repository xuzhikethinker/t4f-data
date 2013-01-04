package aos.kafka;

import kafka.api.FetchRequest;
import kafka.javaapi.consumer.SimpleConsumer;
import kafka.javaapi.message.ByteBufferMessageSet;
import kafka.message.MessageAndOffset;

public class KafkaConsumerSimpleMain {

    public static void main(String[] args) {
        SimpleConsumer consumer = new SimpleConsumer("localhost", 2181, 10000, 1024000);
        long offset = 0;
        while (true) {
            // Create a fetch request for topic “test”, partition 0, current
            // offset, and fetch size of 1MB
            FetchRequest fetchRequest = new FetchRequest("test", 0, offset, 1000000);
            // Get the message set from the consumer and print them out
            ByteBufferMessageSet messages = consumer.fetch(fetchRequest);
            for (MessageAndOffset msg : messages) {
                // System.out.println("consumed: " + new
                // Utils().toString(msg.message().payload(), "UTF-8"));
                System.out.println("consumed: " + msg.message().payload().toString());
                // advance the offset after consuming each message
                offset = msg.offset();
                
            }
        }

    }

}
