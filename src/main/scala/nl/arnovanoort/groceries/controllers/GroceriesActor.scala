package nl.arnovanoort.groceries.controllers

import akka.actor.Actor
import akka.actor.Actor.Receive

/**
 * Created by temmink on 25-8-15.
 */
class GroceriesActor extends Actor {
  override def receive: Receive = {
    case GroceryList(list) => {}
  }
}
