package main.java.lt.jgreibus;

import org.junit.Test;

import java.io.File;

import static main.java.lt.jgreibus.ExtractElementData.parseStatsJSON;

public class WebStatsJSONParserTest {

    private String testFile = "resources/test_stats.json";

    @Test
    public void readFileTest() {
        parseStatsJSON(new File(testFile));
        assert true;
    }
}
