package Utils;

import java.time.Year;
import java.util.Date;

public class DateManager {

    public DateManager(){
    }

    public int getYear(){
        return Year.now().getValue();
    }

    public int getHour(){
        Date now = new Date();
        return now.getHours();
    }

    public int getMinutes(){
        Date now = new Date();
        return now.getMinutes();
    }

    public String getDate(){
        Date now = new Date();
        return now.toString();
    }
}
