
import java.util.Properties
import org.apache.kafka.clients.producer._

object Producer extends App {
    val usage = """
         USAGE:
         Producer <topicName> <servers>
         Example:
         Producer test localhost:9092
        """
    if (args.length != 2) {
            println(usage);
            System.exit(1);
    }
    // our topic   
    val topic = args(0)
    val servers = args(1)

    val  props = new Properties()
    props.put("bootstrap.servers", servers)
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")

    val producer = new KafkaProducer[String, String](props)
    val farmers = Array("Henry Ford", "Nammalvar", "Eliot Coleman")
    println(farmers(0));
    
    for(i<- 0 to 2){
        val record = new ProducerRecord(topic, "key", farmers(i))
        producer.send(record)
}
        
    val record = new ProducerRecord(topic, "key", "--END-- "+new java.util.Date)
    producer.send(record)

    producer.close()
}