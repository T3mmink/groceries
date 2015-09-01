package nl.arnovanoort.groceries.controllers

import akka.actor.{Props, ActorRef}
import nl.arnovanoort.groceries.domain.{Grocery, GroceryList}
import org.joda.time.DateTime
import org.joda.time.format.{DateTimeFormatter, ISODateTimeFormat}
import org.json4s.{DefaultFormats, Formats}
import spray.http.StatusCodes
import spray.httpx.{SprayJsonSupport, Json4sSupport}
import spray.json._
import spray.routing.HttpService
import org.json4s._
import org.json4s.native.JsonMethods._


//object Json4sProtocol extends Json4sSupport {
//  implicit def json4sFormats: Formats = DefaultFormats
//}

/**
 * Created by temmink on 25-8-15.
 */

trait Controller extends HttpService {

           import EventsService._
            import EventsService.EventFormatter._
//  import Json4sProtocol._
  import org.json4s.ext.JodaTimeSerializers._


  def groceriesRoute(actor: ActorRef) = {
    path("list") {
      post {
        respondWithStatus(StatusCodes.Created) {

          entity(as[GroceryList]) {
            value => System.out.println(value.list(0).name)
              System.out.println("yessss...._")
              actor ! "bla"
              complete {
                "Grocery list is being processed"
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

  implicit object EventFormatter extends RootJsonFormat[GroceryList] with DateFormatter with DateParser {
    def read(json: JsValue): GroceryList = {
      System.out.println("STARTS 1")
            json match {
              case obj: JsObject =>
                System.out.println("JSOBJECT" + obj)

      //          //          val name = obj.fields.get("name").map(_.asInstanceOf[JsString].value).
      //          //            getOrElse(deserializationError("field name not present"))
      //          //          val city = obj.fields.get("city").map(_.asInstanceOf[JsString].value).
      //          //            getOrElse(deserializationError("field city not present"))
      //          val starts = obj.fields.get("dateTime").map(x => parser.parseDateTime(x.asInstanceOf[JsString].value)).
      //            getOrElse(new DateTime())
      //          System.out.println("STARTS" + starts)
      //          //          val ends = obj.fields.get("ends").map(x => parser.parseDateTime(x.asInstanceOf[JsString].value)).
      //          //            getOrElse(deserializationError("ends field not present"))
      //          GroceryList(List[Grocery](), starts)
      //        //        case _ => deserializationError("wrong object to deserialize")
            }
      val grocery = Grocery("Grocery list is being processed")
      GroceryList(List[Grocery](grocery), new DateTime())
    }

    def write(e: GroceryList): JsValue =
      JsObject(Map(
        "dateTime" -> JsString("blabalala")
        //          "list" -> JsString(e.list),
        //          "date" -> JsString(e.dateTime)
      ))o 

    val formatter = ISODateTimeFormat.dateTimeNoMillis()
    val parser = ISODateTimeFormat.dateTimeNoMillis().withOffsetParsed()
  }

  /*
    implicit object EventFormatter extends RootJsonFormat[GroceryList] with DateFormatter with DateParser {
    def read(json: JsValue): GroceryList = {
      System.out.println("STARTS 1")
//      json match {
//        case obj: JsObject =>
//          System.out.println("JSOBJECT" + obj)
//          //          val name = obj.fields.get("name").map(_.asInstanceOf[JsString].value).
//          //            getOrElse(deserializationError("field name not present"))
//          //          val city = obj.fields.get("city").map(_.asInstanceOf[JsString].value).
//          //            getOrElse(deserializationError("field city not present"))
//          val starts = obj.fields.get("dateTime").map(x => parser.parseDateTime(x.asInstanceOf[JsString].value)).
//            getOrElse(new DateTime())
//          System.out.println("STARTS" + starts)
//          //          val ends = obj.fields.get("ends").map(x => parser.parseDateTime(x.asInstanceOf[JsString].value)).
//          //            getOrElse(deserializationError("ends field not present"))
//          GroceryList(List[Grocery](), starts)
//        //        case _ => deserializationError("wrong object to deserialize")
//      }
      GroceryList(List[Grocery](), new DateTime())
    }

    def write(e: GroceryList): JsValue =
      JsObject(Map(
        "dateTime" -> JsString("blabalala")
        //          "list" -> JsString(e.list),
        //          "date" -> JsString(e.dateTime)
      ))

    val formatter = ISODateTimeFormat.dateTimeNoMillis()
    val parser = ISODateTimeFormat.dateTimeNoMillis().withOffsetParsed()
  }

   */


//  implicit object EventFormatter extends RootJsonFormat[GroceryList] with DateFormatter with DateParser {
//    def read(json: JsValue): GroceryList = {
//      System.out.println("STARTS 1")
//      json match {
//        case obj: JsObject =>
//          System.out.println("JSOBJECT" + obj)
//          //          val name = obj.fields.get("name").map(_.asInstanceOf[JsString].value).
//          //            getOrElse(deserializationError("field name not present"))
//          //          val city = obj.fields.get("city").map(_.asInstanceOf[JsString].value).
//          //            getOrElse(deserializationError("field city not present"))
//          val starts = obj.fields.get("dateTime").map(x => parser.parseDateTime(x.asInstanceOf[JsString].value)).
//            getOrElse(new DateTime())
//          System.out.println("STARTS" + starts)
//          //          val ends = obj.fields.get("ends").map(x => parser.parseDateTime(x.asInstanceOf[JsString].value)).
//          //            getOrElse(deserializationError("ends field not present"))
//          GroceryList(List[Grocery](), starts)
//        //        case _ => deserializationError("wrong object to deserialize")
//      }
//    }
//
//    def write(e: GroceryList): JsValue =
//      JsObject(Map(
//        "dateTime" -> JsString("blabalala")
//        //          "list" -> JsString(e.list),
//        //          "date" -> JsString(e.dateTime)
//      ))
//
//    val formatter = ISODateTimeFormat.dateTimeNoMillis()
//    val parser = ISODateTimeFormat.dateTimeNoMillis().withOffsetParsed()
//  }


  /*

  class Color(val name: String, val red: Int, val green: Int, val blue: Int)

object MyJsonProtocol extends DefaultJsonProtocol {
  implicit object ColorJsonFormat extends RootJsonFormat[Color] {
    def write(c: Color) =
      JsArray(JsString(c.name), JsNumber(c.red), JsNumber(c.green), JsNumber(c.blue))

    def read(value: JsValue) = value match {
      case JsArray(Vector(JsString(name), JsNumber(red), JsNumber(green), JsNumber(blue))) =>
        new Color(name, red.toInt, green.toInt, blue.toInt)
      case _ => deserializationError("Color expected")
    }
  }
}

import MyJsonProtocol._

val json = Color("CadetBlue", 95, 158, 160).toJson
val color = json.convertTo[Color]

   */
}

object Controller {


}
