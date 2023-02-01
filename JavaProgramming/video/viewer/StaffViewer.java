package video.viewer;

import dbConn.ConnectionMaker;
import video.controller.StaffController;
import video.model.StaffDTO;
import util.ScannerUtil;

import java.sql.Connection;
import java.util.Scanner;

public class StaffViewer {
    private final Scanner SCANNER;
    private Connection connection;
    private StaffDTO logIn;

    public StaffViewer(ConnectionMaker connectionMaker) {
        SCANNER = new Scanner(System.in);
        this.connection = connectionMaker.makeConnection();
    }

    public void showIndex() {
        String message = "1. 스태프 로그인 2. 종료";
        while (true) {
            int userChoice = ScannerUtil.nextInt(SCANNER, message);
            if (userChoice == 1) {
                auth();
                if (logIn != null) {
                    showMenu();
                }
            } else if (userChoice == 2) {
                System.out.println("사용해주셔서 감사합니다.");
                break;
            }
        }
    }

    public void showMenu() {
        while(true){
            String message = "1. 고객정보 관리 2. 대여 관리 3. 비디오 관리 4. 로그아웃";
            int userChoice = ScannerUtil.nextInt(SCANNER, message);

            if (userChoice == 1) {
                CustomerViewer customerViewer = new CustomerViewer(SCANNER,connection,logIn);
                customerViewer.showMenu();
            } else if (userChoice == 2) {
                RentalViewer rentalViewer = new RentalViewer(SCANNER,connection,logIn);
                rentalViewer.showMenu();
            } else if (userChoice == 3) {
                VideoViewer videoViewer = new VideoViewer(SCANNER,connection,logIn);
                videoViewer.showMenu();
            } else if (userChoice == 4) {
                break;
            }
        }
    }

    private void auth() {
        String message = "이메일을 입력하세요.";
        String email = ScannerUtil.nextLine(SCANNER, message);

        message = "비밀번호를 입력하세요.";
        String password = ScannerUtil.nextLine(SCANNER, message);

        StaffController staffController = new StaffController(connection);

        logIn = staffController.auth(email, password);

        if (logIn == null) {
            System.out.println("로그인 정보가 정확하지 않습니다.");
        }
    }
}
