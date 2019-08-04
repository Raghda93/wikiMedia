package test.java.com.article.utility;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import main.java.com.article.utility.Utilities;

class UtilitiesTest {

	@Test
	void testIsEmptyString() {
		String str = "Hello";
		
		boolean idEmpty = Utilities.isEmptyString(str);
		assertFalse(idEmpty);
		
		idEmpty = Utilities.isEmptyString(null);
		assertTrue(idEmpty);
		
		idEmpty = Utilities.isEmptyString("");
		assertTrue(idEmpty);
	}

	@Test
	void testIsEmptyCollection() {
		ArrayList<Object> arrayList = null;
		assertTrue(Utilities.isEmptyCollection(arrayList));
		
		arrayList = new ArrayList<>();
		assertTrue(Utilities.isEmptyCollection(arrayList));
		
		arrayList.add("Hello");
		assertFalse(Utilities.isEmptyCollection(arrayList));
		
	}

	@Test
	void testIsEmptyMap() {
		HashMap<Object, Object> map = null;
		assertTrue(Utilities.isEmptyMap(map));
		
		map = new HashMap<>();
		assertTrue(Utilities.isEmptyMap(map));
		
		map.put(1, "Hello");
		assertFalse(Utilities.isEmptyMap(map));
		
	}

	@Test
	void testEscapeMetaCharacters() {
		String str = "\\ \" Hello World";

		String actual = Utilities.escapeMetaCharacters(str);
		assertEquals("'\\ '\" Hello World", actual);
	}

}
