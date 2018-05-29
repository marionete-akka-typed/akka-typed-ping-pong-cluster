package uk.co.marionete.ping_pong

import akka.actor.typed.ActorSystem
import akka.NotUsed

object Main extends App {
  val system: ActorSystem[NotUsed] = ActorSystem(Game.root, "PingPong")
}
