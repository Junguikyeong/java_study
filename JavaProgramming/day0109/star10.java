package day0109;

import java.util.Scanner;

public class star10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int linNumber = scanner.nextInt();

        int totalHeight = 2 * linNumber - 1;

        for (int i = 1; i <= totalHeight; i++) {
            String stars = "";


            if (i == 1 || i == totalHeight) {
                for (int j = 1; j <= totalHeight; j++) {
                    stars += "*";
                }
            } else {
                int starWidth = 0;

                if (i < linNumber) {
                    starWidth = linNumber - i + 1;
                } else {
                    int lowerI = i - linNumber + 1;
                    starWidth = lowerI;
                }

                int spaceWidth = totalHeight-2*starWidth;

                for (int j = 1; j <= starWidth; j++) {
                    stars += "*";
                }
                for (int j = 1; j <= spaceWidth; j++) {
                    stars += " ";
                }
                for (int j = 1; j <= starWidth; j++) {
                    stars += "*";
                }

            }

            System.out.println(stars);
        }

        scanner.close();
    }
}