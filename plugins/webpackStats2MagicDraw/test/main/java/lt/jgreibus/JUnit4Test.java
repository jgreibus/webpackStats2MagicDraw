package main.java.lt.jgreibus;

import com.nomagic.magicdraw.tests.MagicDrawTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

@RunWith(MagicDrawTestRunner.class)
public class JUnit4Test
{
    @Test
    public void testPluginInitialized()
    {
        assertTrue(Main.initialized);
    }
}
