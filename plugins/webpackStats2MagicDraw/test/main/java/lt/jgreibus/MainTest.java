package main.java.lt.jgreibus;

import com.nomagic.magicdraw.tests.MagicDrawTestCase;

public class MainTest extends MagicDrawTestCase{

	public void testPluginInitialized() {
		assertTrue(Main.initialized);
	}
}
