/*
package day0109;
import java.util.Scanner;

public class star02 {
    public static void main(String[]args){
        Scanner scanner = new Scanner(System.in);
        int linNumber  = scanner.nextInt();

        for(int i=1; i<=linNumber;i++) {
            for(int j=linNumber; j>=i; j--)
            {
                System.out.print("*");
            }
            System.out.println();
        }

        scanner.close();
    }
}*/
package day0109;
        import java.util.Scanner;

public class star02 {
    public static void main(String[]args){
        Scanner scanner = new Scanner(System.in);
        int linNumber  = scanner.nextInt();

        for(int i=1; i<=linNumber;i++) {
            String stars = "";
            for(int j=i; j<=linNumber; j++)
            {
                stars += "*";
            }
            System.out.println(stars);
        }

        scanner.close();
    }
}