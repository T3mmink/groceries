package nl.arnovanoort.groceries.controllers

import akka.testkit.TestProbe
import org.scalatest.{Matchers, FlatSpec}
import spray.http.{ContentTypes, HttpEntity}
import spray.testkit.ScalatestRouteTest

/**
 * Created by temmink on 25-8-15.
 */
class ControllerTest extends FlatSpec with Matchers with ScalatestRouteTest with Controller {
  def actorRefFactory = system

  "Posting a new groceryList" should "blabla" in {
    val testGroceryList = """
      {"groceries":{
        "aardappels": {
          "hoeveelheid" : 2,
          "eenheid"     : "stuks"
        },
        "pindakaas": {
          "hoeveelheid" : 1,
          "eenheid"     : "pot"
        }
      }
      """

    val mockedActor = TestProbe().ref
    Post("/list", HttpEntity(ContentTypes.`application/json`, testGroceryList)) ~> groceriesRoute(mockedActor) ~> check {
      responseAs[String] should include("Grocery list is being processed")
      status should equal(spray.http.StatusCodes.Created)
    }
  }

}
