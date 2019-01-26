package utilities;

import java.util.Random;

/**
 * Created by trusc on 1/28/2016.
 */
public class DataUtils {

    public static String getRndString(){
        StringBuilder builder = new StringBuilder();
        Random rnd = new Random();

        for (int i = 0; i < 10; i++) {
            char c = (char) (rnd.nextInt(122-97)+97);
            builder.append(c);
        }

        return builder.toString();
    }

}
