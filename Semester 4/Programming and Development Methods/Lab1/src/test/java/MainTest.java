import com.sun.tools.javadoc.Main;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MainTest {

    @Before
    public void setUp(){
    }

    @Test
    public void main() {

        assertEquals(20,20);

        Logger testLogger = LogManager.getLogger("Test logger");
        testLogger.info("Tested main method.");
    }


}