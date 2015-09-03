package nl.arnovanoort.groceries.domain

import org.joda.time.{LocalDate, DateTime}
import org.scalatest.FlatSpec

/**
 * Created by temmink on 8/14/15.
 */
class GroceryListTest extends FlatSpec{

  "a new Grocery list" should "contain its groceries" in {

    val date = new LocalDate()
    val groceries = List("Peanutbutter", "Salami")
    val groceryListUnderTest = GroceryListTest.createTestGroceryList(date, groceries)
    assert(groceryListUnderTest.list.head.name == groceries(0))
    assert(groceryListUnderTest.date == date)
  }

}
object GroceryListTest{
  def createTestGroceryList(date: LocalDate, groceries: List[String]): GroceryList = {
    val list = GroceryListTest.createListOfGroceries(groceries)
    new GroceryList(list,date)
  }

  def createListOfGroceries(groceries: List[String]): List[Grocery] = groceries match{
    case x::xs => GroceryTest.createTestGrocery(x) :: createListOfGroceries(xs)
    case Nil => List();
  }
}