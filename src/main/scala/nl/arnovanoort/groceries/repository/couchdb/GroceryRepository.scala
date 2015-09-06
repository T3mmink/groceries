package nl.arnovanoort.groceries.repository

import akka.actor.Actor
import akka.actor.Actor.Receive
import nl.arnovanoort.groceries.domain.{GroceryList, Grocery, Create}
import org.joda.time.LocalDate
import org.reactivecouchbase.ReactiveCouchbaseDriver
import play.api.libs.json._

// first import the implicit execution context
import scala.concurrent.ExecutionContext.Implicits.global
import org.reactivecouchbase.ReactiveCouchbaseDriver
// import the implicit JsObject reader and writer
import org.reactivecouchbase.CouchbaseRWImplicits.documentAsJsObjectReader
import org.reactivecouchbase.CouchbaseRWImplicits.jsObjectToDocumentWriter
import scala.concurrent.Future
import play.api.libs.json._

/**
 * Created by temmink on 8/16/15.
 */

//case class GroceryList(list: List[Grocery])

class GroceryRepository extends Actor {
  override def receive: Receive = {
    case Create(groceryList) => {
      // get a driver instance driver
      val driver = ReactiveCouchbaseDriver()
      // get the default bucket
      val bucket = driver.bucket("default")

      // provide implicit Json formatters
      implicit val groceryFormatter = Json.format[Grocery]
      implicit val groceryListFormatter = Json.format[GroceryList]

      bucket.set[GroceryList]("john-doe", groceryList).onSuccess {
       case status => println(s"Operation status : ${status.getMessage}")
    }
      // shutdown the driver (only at app shutdown)
      driver.shutdown()

    }
    case _ => {
      println("No match ")
    }


  }
}

