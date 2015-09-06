package nl.arnovanoort.groceries.domain

import akka.actor.{Props, Actor}
import nl.arnovanoort.groceries.repository.GroceryRepository
import org.joda.time.LocalDate

/**
 * Created by temmink on 25-8-15.
 */

// messages
case class Create(groceryList: GroceryList)

// domain entities
case class GroceryList(list: List[Grocery], date: LocalDate)
case class Grocery(name: String, amount: Int, unit:String)

class GroceriesService extends Actor {

  override def receive: Receive = {
    case Create(groceryList: GroceryList) => {
      val child = context.actorOf(Props[GroceryRepository], name = "GroceryRepository")
      child ! Create(groceryList)
    }
    case _ => {
      println("KON ER NIKS VAN MAKEN")
    }
  }
}

//object GroceriesService extends GroceriesService
