import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SumTest {

    private Sum sum;

    @Before
    public void setUp(){
        this.sum = new Sum(3,3);
    }

    @Test
    public void makeSum() {
        Logger testLogger = LogManager.getLogger("Test logger");

        assertEquals(6,sum.makeSum());
        testLogger.info("Tested makeSum method.");
    }
}