import java.util
import java.util.Properties

import org.apache.kafka.clients.consumer.KafkaConsumer
import scala.collection.JavaConverters._

object Consumer extends App {
   val usage = """
         USAGE:
         Consumer <topicName> <servers>
         Example:
         Consumer test localhost:9092
        """
    if (args.length != 2) {
            println(usage);
            System.exit(1);
    }
    // our topic   
    val topic = args(0)
    val servers = args(1)

    val  props = new Properties()
    val herokuKafkaURL = "kafka+ssl://ec2-3-209-220-203.compute-1.amazonaws.com:9096,kafka+ssl://ec2-34-232-170-88.compute-1.amazonaws.com:9096,kafka+ssl://ec2-34-238-210-1.compute-1.amazonaws.com:9096" 
    val herokuKafkaURL2 = "ec2-3-209-220-203.compute-1.amazonaws.com:9096"
    // props.put("bootstrap.servers", herokuKafkaURL2)

    props.put("bootstrap.servers", servers)
    props.put("key.deserializer",   "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("group.id", "mohansun")

    val consumer = new KafkaConsumer[String, String](props)
    consumer.subscribe(util.Collections.singletonList(topic))

    val poolTime = 100
    while(true){
      val records=consumer.poll(poolTime)
      for (record<-records.asScala) {
        println(record)
      }
    }
}
