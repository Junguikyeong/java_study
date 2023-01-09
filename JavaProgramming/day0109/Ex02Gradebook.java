/*
package day0109;
import java.util.Scanner;

//사용자로부터 번호, 이름, 국어, 영어, 수학 점수를 입력받아서
// 각각의 정보를 다음과 같이 출력되는 프로그램을 작성하시오.
// 단, 입력에 관한 메소드, 출력에 관한 메소드, 총점 및 평균을 계산하는 메소드를 따로 분리하시오.

// 출력방법:
// 번호: ###번 이름: ###
// 국어: ##점 영어: ##점 수학: ##점
// 총점: ##점 평균: ##.######점

public class Ex02Gradebook {
    public static void main(String[] args) {
        System.out.println("사용자의 번호, 이름, 국어, 영어, 수학 점수를 차례대로 입력하세요.");
        Scanner scanner = new Scanner(System.in);

        //inputValue();
        //Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        scanner.nextLine();
        String name = scanner.nextLine();
        int korean = scanner.nextInt();
        int english = scanner.nextInt();
        int math = scanner.nextInt();
        int sum = calculateSum(korean, english, math);

        printScore(num, name, korean, english, math, sum);

        scanner.close();
    }

    public static void printScore(int num, String name, int korean, int english, int math, int sum) {
        System.out.println("번호: " + num + " 이름: " + name);
        System.out.println("국어: " + korean + " 영어: " + english + " 수학: " + math);
        System.out.println("총점: " + sum + " 평균: " + sum / 3);
    }

    public static int calculateSum(int korean, int english, int math) {
        int sum = korean + english + math;
        return sum;
    }
}*/

package day0109;

import util.ScannerUtil;

import java.util.Scanner;

public class Ex02Gradebook {
    public static void main(String[] args) {
        System.out.println("사용자의 번호, 이름, 국어, 영어, 수학 점수를 차례대로 입력하세요.");
        Scanner scanner = new Scanner(System.in);

        int id = getId(scanner);
        String name = getName(scanner);
        int korean = getKorean(scanner);
        int english = getEnglish(scanner);
        int math = getMath(scanner);

        int sum = calculateSum(korean, english, math);

        printInfo(id, name, korean, english, math);
    }

    public static int getId(Scanner scanner) {
        String message = "학생의 번호를 입력해주세요.";

        return ScannerUtil.nextInt(scanner,message);
    }

    public static String getName(Scanner scanner) {
        String temp;
        String message = "학생의 이름을 입력해주세요.";
        temp = ScannerUtil.nextLine(scanner, message);

        return temp;
    }

    public static int getKorean(Scanner scanner) {
        int temp;
        String message;
        message = "학생의 국어점수를 입력해주세요.";
        System.out.println(message);
        System.out.println("> ");
        temp = scanner.nextInt();

        int min;
        int max;
        min = 0;
        max = 100;
        while (temp < min || temp > max) {
            System.out.println("잘못 입력하셨습니다.");
            System.out.println(message);
            System.out.println("> ");
            temp = scanner.nextInt();
        }
        return temp;
    }

    public static int getEnglish(Scanner scanner) {
        int temp;
        String message;
        message = "학생의 영어점수를 입력해주세요.";
        System.out.println(message);
        System.out.println("> ");
        temp = scanner.nextInt();

        int min;
        int max;
        min = 0;
        max = 100;
        while (temp < min || temp > max) {
            System.out.println("잘못 입력하셨습니다.");
            System.out.println(message);
            System.out.println("> ");
            temp = scanner.nextInt();
        }
        return temp;
    }

    public static int getMath(Scanner scanner) {
        int temp;
        String message;
        message = "학생의 수학점수를 입력해주세요.";
        System.out.println(message);
        System.out.println("> ");
        temp = scanner.nextInt();

        int min;
        int max;
        min = 0;
        max = 100;
        while (temp < min || temp > max) {
            System.out.println("잘못 입력하셨습니다.");
            System.out.println(message);
            System.out.println("> ");
            temp = scanner.nextInt();
        }
        return temp;
    }


    public static void printInfo(int id, String name, int korean, int english, int math) {
        System.out.println("번호: " + id + " 이름: " + name);
        System.out.println("국어: " + korean + " 영어: " + english + " 수학: " + math);
        System.out.println("총점: " + calculateSum(korean, english, math) + " 평균: " + calculateAvg(korean, english, math));
    }

    public static int calculateSum(int korean, int english, int math) {
        return korean + english + math;
    }

    public static double calculateAvg(int korean, int english, int math) {
        final int SUBJECT_SIZE = 3;
        return (korean + english + math) / (double) SUBJECT_SIZE;
    }
}
