package day0109;

import java.util.Scanner;

public class star06 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int linNumber = scanner.nextInt();

        for (int i = 1; i <= linNumber; i++) {
            String stars = "";
            for (int j = 1; j <= i - 1; j++) {
                stars += " ";
            }
            for (int j = 1; j <= 2 * (linNumber - i) + 1; j++) {
                stars += "*";
            }
            /*
            for(int j=linNumber*2-i; j>=i; j--) {
                stars += "*";
            }*/

            System.out.println(stars);
        }

        scanner.close();
    }
}