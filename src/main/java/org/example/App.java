package org.example;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.util.serialization.SimpleStringSchema;

public class App {
    final static String inputTopic = "fix.C__DBZUSER.BANK";
    final static String jobTitle = "Stream";

    public static void main(String[] args) throws Exception {
        final String inputBootstrapServers = "localhost:29092";

        // Set up the streaming execution environment
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        KafkaSource<String> source = KafkaSource.<String>builder()
                .setBootstrapServers(inputBootstrapServers).setTopics(inputTopic)
                .setGroupId("my-group").setStartingOffsets(OffsetsInitializer.earliest())
                .setValueOnlyDeserializer(new SimpleStringSchema()).build();

//        KafkaRecordSerializationSchema<String> serializer = KafkaRecordSerializationSchema.builder()
//                .setValueSerializationSchema(new SimpleStringSchema())
//                .setTopic(outputTopic).build();
        DataStream<String> text = env.fromSource(source, WatermarkStrategy.noWatermarks(),
                "Kafka Source");
        MongoDBSink sink = new MongoDBSink();
        text.sinkTo(sink);
        // Execute program
        try{
            env.execute(jobTitle);
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
