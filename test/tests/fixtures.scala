package tests

import com.malliina.app.AppComponents
import munit.FunSuite
import play.api.ApplicationLoader.Context
import play.api.{ApplicationLoader, Environment, Mode, Play}
import play.api.test.{DefaultTestServerFactory, RunningServer}

object TestAppLoader {
  def createTestAppContext: Context = {
    val env =
      new Environment(new java.io.File("."), ApplicationLoader.getClass.getClassLoader, Mode.Test)
    Context.create(env)
  }
}

trait PlayAppFixture { self: FunSuite =>
  val app = new FunFixture[AppComponents](
    opts => {
      val comps = new AppComponents(TestAppLoader.createTestAppContext)
      Play.start(comps.application)
      comps
    },
    comps => {
      Play.stop(comps.application)
    }
  )
}

trait PlayServerFixture { self: FunSuite =>
  val server = new FunFixture[RunningServer](
    opts => {
      val comps = new AppComponents(TestAppLoader.createTestAppContext)
      DefaultTestServerFactory.start(comps.application)
    },
    running => {
      running.stopServer.close()
    }
  )
}
