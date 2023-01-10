package day0110;
import java.util.Scanner;
import util.ScannerUtil;

// 무한루프를 사용하여
// 사용자가 입력을 누를 때마다 새로운 학생의 정보가 입력이 되고
// 출력을 누를때마다 맨 마지막으로 입력한 학생의 정보가 출력되는 프로그램을
// 작성해보시오.

public class Ex04Gradebook02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Student student = new Student();
        boolean inputSwitch = false;

        while(true){
            String message = "1.입력 2.출력 3.종료";

            int userChoice = ScannerUtil.nextInt(scanner, message);

            if(userChoice == 1){
                insertInfo(scanner,student);
                inputSwitch = true;
            }
            else if(userChoice == 2){
                if(inputSwitch) {
                    printInfo(student);
                }
                else{
                    System.out.println("아직 입력된 학생의 정보가 존재하지 않습니다.");
                }
            }
            else if(userChoice == 3){
                System.out.println("사용해주셔서 감사합니다.");
                break;
            }

        }

    }
    public static void insertInfo(Scanner scanner, Student student){
        //각종 출력에서 사용할 메시시즐 저장할 String 변수 message
        String message;
        message = "학생의 번호를 입력해주세요.";
        student.id = ScannerUtil.nextInt(scanner, message);

        message = "학생의 이름을 입력해주세요.";
        student.name = ScannerUtil.nextLine(scanner, message);

        message = "학생의 국어점수를 입력해주세요.";
        student.korean = ScannerUtil.nextInt(scanner, message,0,100);

        message = "학생의 영어점수를 입력해주세요.";
        student.english = ScannerUtil.nextInt(scanner, message,0,100);

        message = "학생의 수학점수을 입력해주세요.";
        student.math = ScannerUtil.nextInt(scanner, message,0,100);
    }
    public static void printInfo(Student student) {
        System.out.printf("번호: %d번 이름: %s\n",student.id,student.name);
        System.out.printf("국어: %d번 영어: %d점 수학: %d점\n",student.korean,student.english,student.math);
        System.out.printf("총점: %d번 평균: %.2f점\n",calculateSum(student),calculateAverage(student));
    }

    public static int calculateSum(Student student){
        return student.korean + student.english + student.math;
    }

    public static double calculateAverage(Student student){
        return (double)calculateSum(student)/3;
    }
}