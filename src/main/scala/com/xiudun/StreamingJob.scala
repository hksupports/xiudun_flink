package com.xiudun
import java.util.Properties
import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.functions.source.SourceFunction
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011

object StreamingJob {
  // 定义传感器数据样例类
  case class SensorReading( id: String, timestamp: Long, temperature: Double )
  def main(args: Array[String]) {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    //创建属性对象
    val properties = new Properties()
    properties.setProperty("bootstrap.servers","localhost:9092")
    properties.setProperty("group.id","consuer-group")
    properties.setProperty("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer")
    properties.setProperty("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer")
    properties.setProperty("auto.offset.reset","latest")

    //添加数据源
    val stream3 = env.addSource(new FlinkKafkaConsumer011[String]("sensor", new SimpleStringSchema(), properties))
    env.execute("Flink Streaming Scala API Skeleton")
  }
}





















































