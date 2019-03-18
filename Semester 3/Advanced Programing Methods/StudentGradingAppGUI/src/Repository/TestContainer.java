package Repository;

import Tests.GenericTester;
import java.util.ArrayList;
import java.util.List;

public class TestContainer {

    /**
     *  Main test class
     *  Has GenericTester( or derivated) Objects
     */


    private List<GenericTester> tests;

    public TestContainer(){
        tests = new ArrayList<>();
    }

    /**
     * @param test -the test class we add
     * id must not be null
     */
    public void addTest(GenericTester test){
        this.tests.add(test);
    }

    /**
     *
     * @throws Exception
     * if any test ran by the class throws exception.
     */
    public void testAll() throws Exception{
        String err = "";

        for(GenericTester test : tests){
            try{
                test.test();
            }
            catch(Exception e){
                err = err + e.getMessage();
            }
        }

        if(err.length() > 0){
            throw new Exception(err);
        }
    }
}
