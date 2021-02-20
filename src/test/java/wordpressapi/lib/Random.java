package wordpressapi.lib;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Random {

    public static String randomString(int length) {

        String characterBank = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder chain = new StringBuilder();

        for (int x = 0; x < length; x++) {
            int randomIndex = randomInt(0, characterBank.length() - 1);
            char randomString = characterBank.charAt(randomIndex);
            chain.append(randomString);
        }
        return chain.toString();
    }

    public static int randomInt(int min, int max) {

        SecureRandom random = new SecureRandom();

        return random.nextInt(max-min+1)+min;

    }


    public static int getRandomId(){

        try {

            SecureRandom number = SecureRandom.getInstance("SHA1PRNG");
            return number.nextInt(1000000);

        } catch (NoSuchAlgorithmException e) {

            return 0;
        }

    }


}
