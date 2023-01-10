package day0110;

import java.util.Scanner;
import util.ScannerUtil;

// 2. 5명의 학생의 성적을 관리하는 프로그램을 작성하시오.
//    단, 5명을 모두 입력한 후에 새로운 학생 정보를 입력할 시에는
//    가장 오래된 기록을 제거하고 새로운 학생 정보가 입력되도록 코드를 작성하시오.

public class EX12Gradebook04 {
    public static final Scanner SCANNER = new Scanner(System.in);

    public static final int STUDENT_NUM = 5;

    public static void main(String[] args) {
        System.out.printf("총 %d명의 학생의 성적을 관리할수있습니다.\n",STUDENT_NUM);

        Student[] arraystudent = new Student[STUDENT_NUM];
        for(int i=0; i<arraystudent.length;i++){
            arraystudent[i] = new Student();
        }

        boolean inputSwitch = false;

        int studentNum = 0;
        while(true)
        {
            String message = "1.입력 2.출력 3.종료";

            int userChoice = ScannerUtil.nextInt(SCANNER, message);

            if(userChoice == 1){
                if(studentNum>=STUDENT_NUM)
                {
                    studentNum = 0;
                }
                insertInfo(arraystudent,studentNum);
                studentNum++;

                inputSwitch = true;
            }
            else if(userChoice == 2){
                if(inputSwitch) {
                    printInfo(arraystudent);
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

    public static void insertInfo(Student[] arrays,int i){
        System.out.printf("%d번째 학생의 정보를 입력해주세요.\n",i+1);
        Ex04Gradebook02.insertInfo(SCANNER,arrays[i]);
    }
    public static void printInfo(Student[] arrays){
        String message = "확인하고 싶은 학생의 번호를 입력하세요.";
        int studentNum = ScannerUtil.nextInt(SCANNER,message,1,5);
        Ex04Gradebook02.printInfo(arrays[studentNum-1]);
    }
}
