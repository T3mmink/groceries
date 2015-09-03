package nl.arnovanoort.groceries.domain

import org.joda.time.{LocalDate}

case class GroceryList(list: List[Grocery], date: LocalDate)