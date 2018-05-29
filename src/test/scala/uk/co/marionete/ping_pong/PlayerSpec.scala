package uk.co.marionete.ping_pong

import akka.actor.typed.{ActorRef, Behavior}
import akka.testkit.typed.scaladsl.{ActorTestKit, BehaviorTestKit, TestProbe}
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpec}

class PlayerSpec
  extends WordSpec
    with ActorTestKit
    with Matchers
    with BeforeAndAfterAll {

  "A player" must {
    "be alive" in {
      val testKit: BehaviorTestKit[PCommand] = BehaviorTestKit(new Player().rest)
      testKit.run(StartGame(0))
      assert(testKit.isAlive)
    }
  }

  "A player" must {
    "send message" in {
      val probe: TestProbe[PCommand] = TestProbe[PCommand]()
      val pinger: ActorRef[PCommand] = spawn(new Player().rest)

      pinger ! StartGame(10)
      pinger ! Ping(probe.ref)

      probe.expectMessage(Ping(pinger))
    }
  }

  "A player" must {
    "send a end game message" in {
      val probe: TestProbe[PCommand] = TestProbe[PCommand]()
      val pinger: ActorRef[PCommand] = spawn(new Player().rest)

      pinger ! StartGame(0)
      pinger ! Ping(probe.ref)

      probe.expectMessage(EndGame(pinger))
    }
  }

  override def afterAll(): Unit = shutdownTestKit()
}



