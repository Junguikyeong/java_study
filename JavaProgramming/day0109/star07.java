package day0109;

import java.util.Scanner;

public class star07 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int linNumber = scanner.nextInt();

        for (int i = 1; i <= linNumber; i++) {
            String stars = "";
            if (i <= linNumber / 2) {
                for (int j = 1; j <= i; j++) {
                    stars += "*";
                }
            } else {
                for (int j = i; j <= linNumber; j++) {
                    stars += "*";
                }
            }

            System.out.println(stars);
        }

        scanner.close();
    }
}