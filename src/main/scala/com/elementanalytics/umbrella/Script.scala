package com.elementanalytics.umbrella

import java.io.File

import com.redislabs.redisgraph.impl.api.RedisGraph
import com.univocity.parsers.csv.{CsvParser, CsvParserSettings}
import redis.clients.jedis.{Jedis, JedisPool, JedisPoolConfig}

import scala.io.Source
import scala.collection.JavaConverters._

case class Node(n: String, parent: Option[String])
object Script {
  def main(args: Array[String]): Unit = {
    val jedisPoolConfig = new JedisPoolConfig()
    jedisPoolConfig.setMaxWaitMillis(60000)
    val pool = new JedisPool(jedisPoolConfig,"localhost", 6379, 60000)
    val graph = new RedisGraph(pool)

    val csv = new CsvParser(new CsvParserSettings);
    val l = csv.parseAll(new File("/Users/ptsier/Downloads/sap_hierarchy.csv"))
     .asScala.map { arr =>
      val n = arr(0).replaceAll("[-+./ ()\"]", "_")
      val parent = arr(2)
      Node(n, if (parent.isEmpty) None else Some(parent.replaceAll("[-+./ ()\"]", "_")))
    }.toList

//    val src = Source.fromFile("/Users/ptsier/Downloads/sap_hierarchy.csv")
//    val lines = src.getLines().toList
//    src.close()
//
//    val l = lines.map { l =>
//      val arr = l.split(",")
//      val n = arr(0)
//      val parent = arr(2)
//      Node(n.substring(1, n.length - 1), if (parent.isEmpty) None else Some(parent.substring(1, parent.length - 1)))
//    }

    val nodes = l.map(n => s"(${n.n})")
    val edges = l.filter(n => n.parent.isDefined).map { n =>
      s"(${n.n})-[:parent]->(${n.parent.get})"
    }

    val all = nodes ++ edges

    println("Writing")
    all.grouped(20000).foreach { l =>
      val cmd = "CREATE " + l.mkString(",")
      val now = System.currentTimeMillis()
      graph.query("my-graph", cmd)
      println ("Time: " + (System.currentTimeMillis() - now))

    }



//    nodes.foreach(n => graph.query("my-graph", s"CREATE ${n}"))
//    edges.foreach(e => graph.query("my-graph", s"CREATE ${e}"))
  }
}
