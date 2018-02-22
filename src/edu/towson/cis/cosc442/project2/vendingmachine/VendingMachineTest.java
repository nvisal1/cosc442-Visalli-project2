package edu.towson.cis.cosc442.project2.vendingmachine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class VendingMachineTest {
	VendingMachine vending;
	VendingMachineItem item;
	VendingMachineItem item2;

	@Before
	public void setUp() throws Exception {
	    vending = new VendingMachine(); 
	    item = new VendingMachineItem("Pepsi", 1.57); 
	    item2 = new VendingMachineItem("Coke-A-Cola", 1.39);
	}

	//testAddItem tests the addItem method with normal input.
	@Test
	public void testAddItem() {
		try {
			vending.addItem(item, "A");
			assertEquals("Result",item,vending.getItem("A"));
		}
		catch (Exception e) { 
			fail("Should not have thrown exception!");
		}
	}
	
	//testAddItemInvalidCode tests the addItem method with an invalid input 
	//in order to make sure the an exception is thrown
	@Test
	public void testAddItemInvalidCode() {
		try {
			vending.addItem(item, "E");
			assertEquals("Result",item,vending.getItem("E"));
			fail("Should have thrown exception"); 
		} 
		catch (Exception e) {
		    String expectedMessage = "Invalid code for vending machine item";
		    assertEquals( "Exception message must be correct", expectedMessage, e.getMessage() );
		}   
	}
	
	//testAddItemAlreadyOccupied tests the addItem method by adding two items to the same slot 
	//in order to make sure that an exception is thrown
	@Test
	public void testAddItemAlreadyOccupied() {

		try {
			vending.addItem(item, "A");
			vending.addItem(item2, "A");
			//assertEquals("Result",item,vending.getItem("A"));
			fail("Should have thrown exception"); 
		} 
		catch (Exception e) {
		    String expectedMessage = "Slot A already occupied";
		    assertEquals( "Exception message must be correct", expectedMessage, e.getMessage() );
		}   
	}
	
	//testRemoveItem adds an item and then calls removeItem to 
	//ensure that added item is properly removed.
	@Test
	public void testRemoveItem() {

		vending.addItem(item, "A");
		assertEquals(item, vending.removeItem("A"));
	}
	
	//testRemoveItemIsEmpty tries to remove an item that doesn't exist in 
	//order to make sure that an exception is thrown
	@Test
	public void testRemoveItemIsEmpty() {
		try {
			//vending.addItem(item, "A");
			assertEquals(item, vending.removeItem("A"));
			fail("Should have thrown exception"); 
		} 
		catch (Exception e) {
		    String expectedMessage = "Slot A is empty -- cannot remove item";
		    assertEquals( "Exception message must be correct", expectedMessage, e.getMessage() );
		}   
	}
	
	
	//testInsertMoney calls insertMoney and then calls getBalance to ensure 
	//that the appropriate amount was correctly inserted. 
	@Test
	public void testInsertMoney() {
		vending.insertMoney(1.57);
		assertEquals(1.57, vending.getBalance(), 0); 
	}
	
	//testInsuertMoneyInvalidAmount tries to insert a negative amount 
	//of money into the vending machine in order to make sure it throws an exception
	
	@Test
	public void testInsertMoneyInvalidAmount() {
		try {
			vending.insertMoney(-5);
			//assertEquals(1.57, vending.getBalance(), 0);
			fail("Should have thrown exception"); 
		} 
		catch (Exception e) {
		    String expectedMessage = "Invalid amount.  Amount must be >= 0";
		    assertEquals( "Exception message must be correct", expectedMessage, e.getMessage() );
		}   
	}
	
	//testGetBalance checks to ensure that the initial balance is 0
	
	@Test
	public void testGetBalance() {
		assertEquals(0, vending.getBalance(), 0); 
	}
	
	//testMakePurchase adds an item, inserts cash, and checks to make
	//sure a standard purchase can be made.
	
	@Test
	public void testMakePurchase() {
		vending.addItem(item, "A");
		vending.insertMoney(1.57);
		assertTrue("Purchase should be successful",vending.makePurchase("A"));
	}
	
	//testMakePurchaseFalse purposely enters an insufficient amount of money 
	//in order to make sure that an exception is thrown 
	
	@Test
	public void testMakePurchaseFalse() { 
		vending.addItem(item, "A");
		vending.insertMoney(1.50);
		assertFalse("Purchase should be unsuccessful",vending.makePurchase("A"));
	}
	
	//testReturnChange inserts an amount of money, makes a purchase 
	//and checks to make sure that the customer receives the correct change. 
	
	@Test
	public void testReturnChange() {
		vending.insertMoney(1.57);
		vending.addItem(item, "A");
		vending.makePurchase("A"); 
		assertEquals(0, vending.returnChange(), 0);
	}
	
	//testReturnChange2 inserts a negative amount of money and then asks for change. 
	//This ensures that after trying to enter a negative amount, the balance is still 0
	
	@Test
	public void testReturnChange2() {
		try {
			vending.insertMoney(-5);
			//assertEquals(1.57, vending.getBalance(), 0);
			fail("Should have thrown exception"); 
		} 
		catch (Exception e) {
		    String expectedMessage = "Invalid amount.  Amount must be >= 0";
		    assertEquals( "Exception message must be correct", expectedMessage, e.getMessage() );
		    assertEquals(0 , vending.returnChange(), 0);
		}   
	}

	@After
	public void tearDown(){
		vending = null;
		item = null; 
		item2 = null; 
	}
}
