import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;



public class Main {
    public static void main(String[] args) {
        Logger logger = LogManager.getLogger("mylogger");

        logger.error("test message");

        System.out.println("Hello world.");

    }
}
