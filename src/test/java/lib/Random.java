package lib;

import java.util.concurrent.ThreadLocalRandom;

public class Random {
    /*
    public static void main(String[] args) {
        int length = 12;
        String chain = randomString(length);
        System.out.printf("Cadena aleatoria de %d caracteres: %s\n", length, chain);
    }*/

    public String getRandomId(int length){
        return String.valueOf(Math.random()*length);
    }

    public static String randomString(int length) {
        // El banco de caracteres
        String character_bank = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        // La cadena en donde iremos agregando un carácter aleatorio
        String chain = "";
        for (int x = 0; x < length; x++) {
            int randomIndex = randomInt(0, character_bank.length() - 1);
            char randomString = character_bank.charAt(randomIndex);
            chain += randomString;
        }
        return chain;
    }

    public static int randomInt(int min, int max) {
        // nextInt regresa en rango pero con límite superior exclusivo, por eso sumamos 1
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
