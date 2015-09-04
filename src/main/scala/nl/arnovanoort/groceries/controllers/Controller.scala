package nl.arnovanoort.groceries.controllers

import akka.actor.{Props, ActorRef}
import nl.arnovanoort.groceries.domain.{Grocery, GroceryList}
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

trait Controller extends HttpService {

  import EventsService._
  import EventsService.GroceryListFormatter._
  //  import Json4sProtocol._
  import org.json4s.ext.JodaTimeSerializers._


  def groceriesRoute(actor: ActorRef) = {
    path("list") {
      post {
        respondWithStatus(StatusCodes.Created) {

          entity(as[GroceryList]) {
            groceryList =>{
              actor ! Create(groceryList)
              complete {
                "Grocery list is being processed"
              }
              }
          }
        }
      }
    }
  }

}
object EventsService extends DefaultJsonProtocol with SprayJsonSupport {

  trait DateFormatter {
    val formatter: DateTimeFormatter
  }

  trait DateParser {
    val parser: DateTimeFormatter
  }
  //  implicit val groceryListFormat = jsonFormat2(GroceryList)

  implicit val colorFormat = jsonFormat3(Grocery)

  implicit object GroceryListFormatter extends RootJsonFormat[GroceryList]  {
    def read(json: JsValue): GroceryList = json match{
        case JsObject(jsonMap) => {
          val dateStr: String = jsonMap("date").convertTo[String]
          val groceries: List[Grocery] = jsonMap("groceries").convertTo[List[Grocery]]

          GroceryList(groceries, new LocalDate(dateStr))
        }
    }

    def write(e: GroceryList): JsValue =
      JsObject(Map(
        "dateTime" -> JsString("blabalala")
        //          "list" -> JsString(e.list),
        //          "date" -> JsString(e.dateTime)
      ))

  }

}

object Controller {


}
