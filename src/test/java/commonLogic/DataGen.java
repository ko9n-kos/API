package commonLogic;

import java.util.Random;

public class DataGen {
    public static String getRandomText(int n) {
        String randomText = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index = (int) (randomText.length()
                    * Math.random());
            sb.append(randomText
                    .charAt(index));
        }
        return sb.toString();
    }

    public static Integer randomNumWith1() {
        Random random = new Random();
        int randomNumber;
        do {
            randomNumber = random.nextInt(100 - 1 + 1) + 1;
        } while (!containsDigitOne(randomNumber));

        return randomNumber;
    }

    public static boolean containsDigitOne(int number) {
        while (number > 0) {
            int digit = number % 10;
            if (digit == 1) {
                return true;
            }
            number /= 10;
        }
        return false;
    }

}