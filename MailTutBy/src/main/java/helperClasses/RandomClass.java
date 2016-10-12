package helperClasses;

import java.util.Date;
import java.util.UUID;

public class RandomClass {

    public  static String getRandomString() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(10);
    }
    public static String getRandomSubject(){
        return getRandomString()+"_"+new Date().toString().replaceAll(" ", "").replaceAll(":", "").toLowerCase();
    }
}
