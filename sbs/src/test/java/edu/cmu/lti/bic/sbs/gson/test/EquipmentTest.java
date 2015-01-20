package edu.cmu.lti.bic.sbs.gson.test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.Gson;

import edu.cmu.lti.bic.sbs.gson.Equipment;

public class EquipmentTest {
	static Gson gson = new Gson();
	static Equipment[] equipments = null;

	@BeforeClass
	public static void setUp() throws FileNotFoundException {
		FileReader fileReader = new FileReader(
				"src/test/resources/cli/equipmentTest.json");

		equipments = gson.fromJson(fileReader, Equipment[].class);
		assertSame(equipments.length, 3);

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
		assertEquals(equipments[0].getName(), "ECMO");
	}

	@Test
	public void testSetName() {
		equipments[0].setName("TEST");
		assertEquals(equipments[0].getName(), "TEST");
		equipments[0].setName("ECMO");
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
		assertEquals(equipments[0].getId(), "1");
	}

	@Test
	public void testSetId() {
		equipments[0].setId("111");
		assertEquals(equipments[0].getId(), "111");
		equipments[0].setId("0");
	}

}
