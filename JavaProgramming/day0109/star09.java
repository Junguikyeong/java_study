/*
package day0109;
import java.util.Scanner;

public class star09 {
    public static void main(String[]args){
        Scanner scanner = new Scanner(System.in);
        int linNumber  = scanner.nextInt();

        for(int i=1; i<=linNumber;i++) {
            String stars = "";

            if(i<=linNumber/2){
                for(int j=1; j<=linNumber-i; j++){
                    stars += " ";
                }
                for(int j=1; j<=i*2-1; j++) {
                    stars += "*";
                }
            }
            else{
                for(int j=1; j<=i-1; j++) {
                    stars +=" ";
                }
                for(int j=1;j<=2*(linNumber-i)+1; j++) {
                    stars += "*";
                }
            }

            System.out.println(stars);
        }

        scanner.close();
    }
}*/

package day0109;

import java.util.Scanner;

public class star09 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int linNumber = scanner.nextInt();

        int totalHeight = 2 * linNumber - 1;

        for (int i = 1; i <= totalHeight; i++) {
            String stars = "";
            int spaceWidth = 0;
            int starWidth = 0;

            if (i <= linNumber) {
                spaceWidth = linNumber - i;
                starWidth = 2 * i - 1;
            } else {
                int lowerI = i - linNumber + 1;

                spaceWidth = lowerI - 1;
                starWidth = 2 * (linNumber - lowerI) + 1;
            }
            for (int j = 1; j <= spaceWidth; j++) {
                stars += " ";
            }
            for (int j = 1; j <= starWidth; j++) {
                stars += "*";
            }


            System.out.println(stars);
        }

        scanner.close();
    }
}