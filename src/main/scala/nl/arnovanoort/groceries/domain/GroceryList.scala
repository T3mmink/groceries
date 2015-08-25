package nl.arnovanoort.groceries.domain

import org.joda.time.DateTime

case class GroceryList(list: List[Grocery], dateTime: DateTime){

}