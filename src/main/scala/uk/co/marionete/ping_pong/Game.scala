package uk.co.marionete.ping_pong

import akka.actor.typed.{ActorRef, Behavior, Terminated}
import akka.actor.typed.scaladsl.Behaviors
import akka.NotUsed
import scala.util.Random

object Game {

  val root: Behavior[NotUsed] =
    Behaviors.setup { ctx =>
      val ping: ActorRef[PCommand] = ctx.spawn(new Player().rest, "Ping")
      val pong: ActorRef[PCommand] = ctx.spawn(new Player().rest, "Pong")
      ctx.watch(ping)
      ctx.watch(pong)


      ping ! StartGame(Random.nextInt(30) + 10)
      pong ! StartGame(Random.nextInt(30) + 10)

      ping ! Ping(pong)

      Behaviors.receiveSignal {
        case (_, Terminated(ref)) => Behaviors.stopped
      }
    }
}
