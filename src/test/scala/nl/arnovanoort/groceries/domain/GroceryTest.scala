package nl.arnovanoort.groceries.domain

import org.scalatest.FlatSpec

/**
 * Created by temmink on 8/14/15.
 */
class GroceryTest extends FlatSpec {

  "a new Grocery" should "contain the grocery name" in {
    val groceryName = "Peanutbutter";
    // create the grocery
    val groceryUnderTest = GroceryTest.createTestGrocery(groceryName)
    // and check if it has the given name
    assert(groceryUnderTest.name == groceryName)
  }


}

object GroceryTest{
  def createTestGrocery(name: String):Grocery = {
    new Grocery(name)
  };

}
