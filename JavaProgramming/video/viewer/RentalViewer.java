package video.viewer;

import dbConn.ConnectionMaker;
import util.ScannerUtil;
import video.controller.*;
import video.model.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

public class RentalViewer {
    private final Scanner SCANNER;
    private StaffDTO logIn;
    private Connection connection;
    private ConnectionMaker connectionMaker;

    public RentalViewer(Scanner scanner, Connection connection, StaffDTO logIn) {
        this.connection = connection;
        this.logIn = logIn;
        SCANNER = scanner;
    }

    public void showMenu() {
        System.out.println("===================================================");
        String message = "1. 대여하기 2. 반납하기 3. 뒤로가기";
        int userChoice = ScannerUtil.nextInt(SCANNER, message);
        if (userChoice == 1) {
            rentalVideo();
        } else if (userChoice == 2) {
            returnVideo();
        }
    }

    private void rentalVideo() {
        // 비디오 이름으로 대여
        String message = "대여할 비디오 제목을 입력하세요.";
        String videoTitle = ScannerUtil.nextLine(SCANNER, message);

        VideoController videoController = new VideoController(connection);

        while (!videoController.confirm(videoTitle)) {
            message = "존재하지않는 비디오 제목입니다. 다시 입력해주세요.";
            videoTitle = ScannerUtil.nextLine(SCANNER, message);
        }
        VideoDTO v;
        v = videoController.titleSearch(videoTitle);

        ArrayList<Integer> inventoryIdList = new ArrayList<>();
        InventoryController inventoryController = new InventoryController(connection);
        inventoryIdList = inventoryController.confirmList(v.getId(), logIn.getStoreId());

        int count = 1;
        if (inventoryIdList.isEmpty()) {
            System.out.println("해당 비디오의 재고가 없어서 대여가 불가능합니다.");
            showMenu();
        }
        for (int inventoryId : inventoryIdList) {
            RentalController rentalController = new RentalController(connection);
            if (rentalController.confirm(inventoryId, logIn.getId())) {
                System.out.printf("해당 비디오 재고 %d개 중 %d번째는 대여 중 이라 빌릴수 없습니다.\n", inventoryIdList.size(), count);
            } else {
                message = "대여하는 고객의 이메일을 입력해주세요.";
                String email = ScannerUtil.nextLine(SCANNER, message);

                CustomerController customerController = new CustomerController(connection);
                if (!customerController.confirm(email)) {
                    System.out.println("새로운 고객입니다.");
                    CustomerViewer customerViewer = new CustomerViewer(SCANNER, connection, logIn);
                    customerViewer.register();
                } else {
                    System.out.println("기존 고객입니다.");
                }
                rentalController.rental(email, inventoryId, logIn.getId());
                showMenu();
            }
            count++;
        }
    }

    private void returnVideo() {
        String message = "반납하는 고객의 이메일을 입력해주세요.";
        String email = ScannerUtil.nextLine(SCANNER, message);

        CustomerController customerController = new CustomerController(connection);
        while (!customerController.confirm(email)) {
            System.out.println("존재하지 않는 고객의 이메일입니다.");
            email = ScannerUtil.nextLine(SCANNER, message);
        }

        // 비디오 이름으로 반납
        message = "반납할 비디오 제목을 입력하세요.";
        String videoTitle = ScannerUtil.nextLine(SCANNER, message);
        VideoController videoController = new VideoController(connection);

        while (!videoController.confirm(videoTitle)) {
            message = "존재하지않는 비디오 제목입니다. 다시 입력해주세요.";
            videoTitle = ScannerUtil.nextLine(SCANNER, message);
        }

        VideoDTO v;
        v = videoController.titleSearch(videoTitle);

        ArrayList<Integer> inventoryIdList = new ArrayList<>();
        InventoryController inventoryController = new InventoryController(connection);
        inventoryIdList = inventoryController.confirmList(v.getId(), logIn.getStoreId());

        int count = 1;
        for (int inventoryId : inventoryIdList) {
            RentalController rentalController = new RentalController(connection);
            if (rentalController.confirm(inventoryId, logIn.getId())) {
                rentalController.returnVideo(email, inventoryId, logIn.getId());
                System.out.printf("해당 비디오의 재고 %d개 중 %d번째 비디오를 반납하였습니다.\n",inventoryIdList.size(), count);
                showMenu();
            } else{
                System.out.printf("해당 비디오의 재고 %d개 중 %d번째 비디오를 빌리지 않았습니다.\n",inventoryIdList.size(), count);
            }
            count++;
        }
    }


}
