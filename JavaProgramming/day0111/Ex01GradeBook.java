package day0111;
// 학생관리프로그램
import java.util.Scanner;
import util.ScannerUtil;

public class Ex01GradeBook {
    public static final Scanner SCANNER = new Scanner(System.in);
    public static final int STUDENT_SIZE = 5;
    public static final int SCORE_MIN = 0;
    public static final int SCORE_MAX = 100;
    public static int nextId = 1;
    public static Student[] studentArray = new Student[STUDENT_SIZE];

    public static void main(String[] args) {
        showMenu();

        SCANNER.close();
    }

    public static void showMenu(){
        while(true){
            String message = "1. 입력 2. 출력 3.종료";
            int userChoice = ScannerUtil.nextInt(SCANNER, message);

            if (userChoice == 1) {
                insertInfo();
            } else if (userChoice == 2) {
                printInfo();
            } else if (userChoice == 3) {
                System.out.println("사용해주셔서 감사합니다.");
                break;
            }
        }
    }

    private static void insertInfo(){
        //Student s = studentArray[]
        //s.id =

    }

    private static void printInfo(){

    }


}
