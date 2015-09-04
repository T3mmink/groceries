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
class ControllerTest extends FlatSpec with Matchers with ScalatestRouteTest with Controller {
  def actorRefFactory = system

//  "test datetime" should "blabla " in {
//    val date = ISODateTimeFormat.dateTimeNoMillis().withOffsetParsed()
//    System.out.println("DATE: " + date)
//    date should equal(date)
//  }

  "Posting a new groceryList" should "blabla" in {
    val testGroceryList = """
      {"groceries":
      [
          {
            "name":"aardappels",
            "amount":2,
            "unit":"stuks"
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

    Post("/list", HttpEntity(ContentTypes.`application/json`, testGroceryList)) ~> groceriesRoute(mockedActor) ~> check {
      responseAs[String] should include("Grocery list is being processed")
      status should equal(spray.http.StatusCodes.Created)
    }
  }

}
