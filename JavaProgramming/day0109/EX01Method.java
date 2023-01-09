package day0109;

public class EX01Method {
    public static void main(String[] args) {
        printIntro();

        int a = 3;
        int b = 4;
        printBigger(a, b);
        a = 4;
        b = 3;
        printBigger(a, b);
        printBigger(4, 4);

        a=5;
        b=3;
        int result = calculatePower(a,b);
        System.out.println("a의 b승: " + result);

        System.out.println("a의 b승: " + calculatePower(4,4));
    }

    public static void printIntro() {
        System.out.println("EX01Method");
        System.out.println("작성자: 정의경");
        System.out.println("작성일: 2023년 01월 09일");
        System.out.println("내용: 메소드에 대한 설명 및 예제");
    }

    public static void printBigger(int num1, int num2) {
        if (num1 > num2) {
            System.out.println("a가 b보다 더 큽니다.");
        } else {
            System.out.println("b가 a보다 더 크거나 같습니다.");
        }
    }

    public static int calculatePower(int a, int b){
        int result = 1;

        for(int i=1; i<=b; i++){
            result *= a;
        }
        return result;
    }
}


//static 달려있는 메소드쓰려면 메인에도 static 이 꼭 있어야함, 그리고 객체가 없어도됌.
//하지만 객체지향프로그램으로써 객체를 선언하고 쓰기때문에 static을 권장하지않음.
//class씀으로써 static을 빼게 될거임