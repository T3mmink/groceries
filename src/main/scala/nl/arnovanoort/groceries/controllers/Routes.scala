package nl.arnovanoort.groceries.controllers

import akka.actor.{Props, ActorRef}
import nl.arnovanoort.groceries.domain.{Create, Grocery, GroceryList}
import org.joda.time.{LocalDate, DateTime}
import org.joda.time.format.{DateTimeFormatter, ISODateTimeFormat}
import org.json4s.{DefaultFormats, Formats}
import spray.http.StatusCodes
import spray.httpx.{SprayJsonSupport, Json4sSupport}
import spray.json._
import spray.routing.HttpService
import org.json4s._
import org.json4s.native.JsonMethods._

/**
 * Created by temmink on 25-8-15.
 */

trait Routes extends HttpService {

  import nl.arnovanoort.groceries.controllers.JsonConverters._

  def groceriesRoute(actor: ActorRef) = {
      path("create") {
      post {
        respondWithStatus(StatusCodes.Created) {
          entity(as[GroceryList]) {
            groceryList =>{
              actor ! Create(groceryList)
              complete {
                "Grocery list is being created"
              }
              }
          }
        }
      }
    }
  }
}
