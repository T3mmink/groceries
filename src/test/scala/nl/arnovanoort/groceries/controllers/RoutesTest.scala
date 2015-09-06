package nl.arnovanoort.groceries.controllers

import akka.testkit.TestProbe
import org.joda.time.{LocalDate, DateTime}
import org.joda.time.format.{DateTimeFormatter, ISODateTimeFormat}
import org.scalatest.{Matchers, FlatSpec}
import spray.http.{ContentTypes, HttpEntity}
import spray.testkit.ScalatestRouteTest

/**
 * Created by temmink on 25-8-15.
 */
class RoutesTest extends FlatSpec with Matchers with ScalatestRouteTest with Routes {
  def actorRefFactory = system

  "Posting a new groceryList" should "blabla" in {
    val testGroceryList = """
      {"groceries":
      [
          {
            "name":"aardappels",
            "amount":2,
            "unit":"pc"
          },
          {
            "name":"peanutbutter",
            "amount":1,
            "unit":"pot"
          }
      ]
      ,"date":"2015-09-03"
      }
      """

    val mockedActor = TestProbe().ref

    Post("/create", HttpEntity(ContentTypes.`application/json`, testGroceryList)) ~> groceriesRoute(mockedActor) ~> check {
      responseAs[String] should include("Grocery list is being created")
      status should equal(spray.http.StatusCodes.Created)
    }
  }

}
