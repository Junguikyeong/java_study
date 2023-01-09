package day0109;
import java.util.Scanner;

public class star01 {
    public static void main(String[]args){
        Scanner scanner = new Scanner(System.in);
        int linNumber  = scanner.nextInt();

        for(int i=1; i<=linNumber;i++) {
            for(int j=1; j<=i; j++)
            {
                System.out.print("*");
            }
            System.out.println();
        }

        scanner.close();
    }
}

