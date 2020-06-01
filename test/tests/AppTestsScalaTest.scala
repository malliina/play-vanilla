package tests

import com.malliina.http.{FullUrl, OkClient}
import munit.FunSuite
import play.api.test.FakeRequest
import play.api.test.Helpers._

class AppTestsScalaTest extends FunSuite with PlayAppFixture with PlayServerFixture {
  app.test("can read component") { comps => assert(comps.demoValue == 42) }

  app.test("can make app request") { comps =>
    val req = FakeRequest("GET", "/")
    val res = await(route(comps.application, req).get)
    assert(res.header.status == 200)
  }

  server.test("can make server request") { server =>
    val port = server.endpoints.httpEndpoint.map(_.port).get
    val http = OkClient.default
    val res = await(http.get(FullUrl("http", s"localhost:$port", "")))
    http.close()
    assert(res.code == 200)
  }
}
