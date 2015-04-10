package edu.cmu.lti.bic.sbs.gson.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.Gson;

import edu.cmu.lti.bic.sbs.gson.Tool;

public class EquipmentTest {
	static Gson gson = new Gson();
	static Tool[] tools = null;

	@BeforeClass
	public static void setUp() throws FileNotFoundException {
		FileReader fileReader = new FileReader(
				"src/test/resources/cli/equipmentTest.json");

		tools = gson.fromJson(fileReader, Tool[].class);
		assertSame(tools.length, 3);

		System.out.println("setting up");
	}

	@AfterClass
	public static void tearDown() {
	}

	@Test
	public void testEquipment() throws IOException {

	}

	@Test
	public void testGetName() {
		assertEquals(tools[0].getName(), "ECMO");
	}

	@Test
	public void testSetName() {
		tools[0].setName("TEST");
		assertEquals(tools[0].getName(), "TEST");
		tools[0].setName("ECMO");
	}

	//
	// @Test
	// public void testGetDescription() {
	// fail("Not yet implemented");
	// }
	//
	// @Test
	// public void testSetDescription() {
	// fail("Not yet implemented");
	// }
	//
	@Test
	public void testGetId() {
		assertEquals(tools[0].getId(), "1");
	}


}
