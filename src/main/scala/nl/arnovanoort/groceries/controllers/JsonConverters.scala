package nl.arnovanoort.groceries.controllers

/**
 * Created by temmink on 8/29/15.
 */

import nl.arnovanoort.groceries.domain.{GroceryList, Grocery}
import org.joda.time.{LocalDate, DateTime}
import org.joda.time.format.{DateTimeFormatter, ISODateTimeFormat}
import spray.httpx.SprayJsonSupport
import spray.json.DefaultJsonProtocol
import spray.json._

object JsonConverters extends DefaultJsonProtocol with SprayJsonSupport {


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
      //TODO: implement
      JsObject(Map(
        "dateTime" -> JsString("blabalala")
        //          "list" -> JsString(e.list),
        //          "date" -> JsString(e.dateTime)
    ))
  }
}
