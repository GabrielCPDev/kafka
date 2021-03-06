package  br.com.gabriel.ecomerce;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class NewOrder {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
         var producer = new KafkaProducer<String,String>(properties());
         var value = "123,321,123321";
         var record = new ProducerRecord<>("ECOMMERCE_NEW_ORDER", value, value);
        producer.send(record,(data, exeption) -> {
                if(exeption != null){
                exeption.printStackTrace();
                } else {
                    System.out.println(
                            "topico: " +
                            data.topic() +
                            " particao: " +
                            data.partition() +
                            " / " +  data.offset() +
                            " / " + data.timestamp());
                }

        }).get();

    }

    private static Properties properties() {
        var properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"127.0.0.1:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return properties;
    }
}
