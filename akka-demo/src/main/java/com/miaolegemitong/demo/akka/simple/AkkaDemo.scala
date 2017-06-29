package com.miaolegemitong.demo.akka.simple

import akka.actor.{Actor, ActorSystem, Props, UntypedActor}

/**
  * Created by miaolegemitong on 2017/6/22.
  */
object AkkaDemo {
  def main(args: Array[String]): Unit = {
    val system = ActorSystem("DemoSystem")
    val actorRef = system.actorOf(Props[ParentActorDemo], "Parent")
    actorRef ! DemoMessage1("a")
    actorRef ! DemoMessage2("b")
    val sonActorRef = system.actorSelection("/user/Parent/Son*")
    sonActorRef ! DemoMessage2("c")
    system.shutdown()
    system.awaitTermination()
  }
}

class ParentActorDemo extends UntypedActor {
  var index = 0

  override def onReceive(message: Any): Unit = {
    val sonActorRef = context.actorOf(Props[SonActorDemo], "Son" + index)
    index += 1
    sonActorRef ! message
  }
}

class SonActorDemo extends UntypedActor {
  override def onReceive(message: Any): Unit = {
    message match {
      case m: DemoMessage1 => {
        println("1:" + m.message)
      }
      case n: DemoMessage2 => {
        println("2:" + n.message)
      }
    }
  }
}

case class DemoMessage1(message: String)

case class DemoMessage2(message: String)