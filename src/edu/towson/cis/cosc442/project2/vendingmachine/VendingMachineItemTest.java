package edu.towson.cis.cosc442.project2.vendingmachine;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class VendingMachineItemTest {
	VendingMachine vending;
	VendingMachineItem item;

	@Before
	public void setUp() throws Exception {
	    item = new VendingMachineItem("Mountain Dew", 2.00); 
	}

	//testGetName ensures that the constructor set the name to "Mountain Dew". 
	@Test
	public void testGetName() {
		assertEquals("Mountain Dew", item.getName());
	}

	//testGetPrice ensures that the constructor set the price to 2.00. 
	@Test
	public void testGetPrice() {
		assertEquals(2.00, item.getPrice(), 0);
	}

	@After
	public void tearDown(){
		item = null;
		
	}
}
