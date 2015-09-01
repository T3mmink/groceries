package nl.arnovanoort.groceries.controllers

import akka.testkit.TestProbe
import org.joda.time.DateTime
import org.joda.time.format.ISODateTimeFormat
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
      {"list":{
        "aardappels":{
          "hoeveelheid":2,
          "eenheid":"stuks"
        },
        "pindakaas":{
          "hoeveelheid":1,
          "eenheid":"pot"
        }}
      ,"dateTime":"01-01-2011"
      }
      """ //+ new DateTime() + """"} """

    val mockedActor = TestProbe().ref
    Post("/list", HttpEntity(ContentTypes.`application/json`, testGroceryList)) ~> groceriesRoute(mockedActor) ~> check {
      responseAs[String] should include("Grocery list is being processed")
      status should equal(spray.http.StatusCodes.Created)
    }
  }

}
