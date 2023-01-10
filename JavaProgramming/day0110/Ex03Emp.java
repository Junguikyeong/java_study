package day0110;

import util.ScannerUtil;

import java.util.Scanner;

// 사원 관리 프로그램을 작성하시오.
// 단, 사원 정보(사원 번호, 이름, 직급, 소속 부서, 연봉)은 하나의 구조체로 통제하고
// 사원 정보 입력, 출력은 별개의 메소드를 통하여 관리합니다.
public class Ex03Emp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Emp emp = new Emp();

        insertInfo(scanner, emp);

        printInfo(emp);

        scanner.close();
    }

    public static void insertInfo(Scanner scanner, Emp emp) {
        //각종 출력에서 사용할 메시시즐 저장할 String 변수 message
        String message;
        message = "사원의 번호를 입력해주세요.";
        emp.id = ScannerUtil.nextInt(scanner, message);

        message = "사원의 이름을 입력해주세요.";
        emp.name = ScannerUtil.nextLine(scanner, message);

        message = "사원의 직급을 입력해주세요.";
        emp.position = ScannerUtil.nextLine(scanner, message);

        message = "사원의 부서를 입력해주세요.";
        emp.department = ScannerUtil.nextLine(scanner, message);

        message = "사원의 연봉을 입력해주세요.";
        emp.income = ScannerUtil.nextInt(scanner, message);
    }

    public static void printInfo(Emp emp) {
        System.out.println("-------------------------------------");
        System.out.println("번호: " + emp.id);
        System.out.println("이름: " + emp.name);
        System.out.println("직급: " + emp.position);
        System.out.println("부서: " + emp.department);
        System.out.println("연봉: " + emp.income);
        System.out.println("-------------------------------------");
    }

}