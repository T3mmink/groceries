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
      {"list":{
        "aardappels":{
          "hoeveelheid":2,
          "eenheid":"stuks"
        },
        "pindakaas":{
          "hoeveelheid":1,
          "eenheid":"pot"
        }}
      ,"date":"01-01-2011"
      }
      """ //+ new DateTime() + """"} """

    val date = new LocalDate()
//    val patternFormat1: DateTimeFormatter  = DateTimeFormatter.forPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
//    val patternFormat2: DateTimeFormatter  = DateTimeFormatter.forPattern("yyyy-MM-dd'T'");
    System.out.println("datetime1" + date)
  ///    println("datetime2" + dateTime.toString(patternFormat1))
////    println("datetime2" + dateTime.toString(patternFormat2))
//    val formatter = ISODateTimeFormat.dateTimeNoMillis()
//    val parser = ISODateTimeFormat.dateTimeNoMillis().withOffsetParsed()


    val mockedActor = TestProbe().ref
    Post("/list", HttpEntity(ContentTypes.`application/json`, testGroceryList)) ~> groceriesRoute(mockedActor) ~> check {
      responseAs[String] should include("Grocery list is being processed")
      status should equal(spray.http.StatusCodes.Created)
    }
  }

}
