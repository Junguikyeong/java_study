package day0109;

import java.util.Scanner;

public class star08 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int linNumber = scanner.nextInt();

        for (int i = 1; i <= linNumber; i++) {
            String stars = "";
            if (i <= linNumber / 2) {
                for (int j = 1; j <= linNumber; j++) {
                    if (j <= linNumber - i) {
                        stars += " ";
                    } else {
                        stars += "*";
                    }
                }
            } else {
                for (int j = 1; j < i; j++) {
                    stars += " ";
                }
                for (int j = linNumber; j >= i; j--) {
                    stars += "*";
                }
            }

            System.out.println(stars);
        }

        scanner.close();
    }
}