package nl.arnovanoort.groceries.controllers

import akka.actor.Actor
import akka.actor.Actor.Receive
import nl.arnovanoort.groceries.domain.GroceryList

/**
 * Created by temmink on 25-8-15.
 */

case class Create(groceryList: GroceryList)

class GroceriesActor extends Actor {

  override def receive: Receive = {
    case Create(groceryList:GroceryList) => {println(groceryList.list)}
    case _ => {println("KON ER NIKS VAN MAKEN")}
  }
}
