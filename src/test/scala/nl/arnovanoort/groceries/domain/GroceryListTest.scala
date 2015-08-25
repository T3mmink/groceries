package nl.arnovanoort.groceries.domain

import org.joda.time.DateTime
import org.scalatest.FlatSpec

/**
 * Created by temmink on 8/14/15.
 */
class GroceryListTest extends FlatSpec{

  "a new Grocery list" should "contain its groceries" in {

    val dateTime = new DateTime()
    val groceries = List("Peanutbutter", "Salami")
    val groceryListUnderTest = GroceryListTest.createTestGroceryList(dateTime, groceries)
    assert(groceryListUnderTest.list.head.name == groceries(0))
    assert(groceryListUnderTest.dateTime == dateTime)
  }

}
object GroceryListTest{
  def createTestGroceryList(dateTime: DateTime, groceries: List[String]): GroceryList = {
    val list = GroceryListTest.createListOfGroceries(groceries)
    new GroceryList(list,dateTime)
  }

  def createListOfGroceries(groceries: List[String]): List[Grocery] = groceries match{
    case x::xs => GroceryTest.createTestGrocery(x) :: createListOfGroceries(xs)
    case Nil => List();
  }
}