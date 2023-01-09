package day0109;

import java.util.Scanner;

public class star06easy {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int linNumber = scanner.nextInt();

        for (int i = linNumber; i >= 1; i--) {
            String stars = "";
            for (int j = 1; j <= linNumber - i; j++) {
                stars += " ";
            }
            for (int j = 1; j <= i * 2 - 1; j++) {
                stars += "*";
            }
            System.out.println(stars);
        }

        scanner.close();
    }
}