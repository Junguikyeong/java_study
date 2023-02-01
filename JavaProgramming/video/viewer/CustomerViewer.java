package video.viewer;

import dbController.UserController;
import util.ScannerUtil;
import video.controller.*;
import video.model.StaffDTO;

import java.sql.Connection;
import java.util.Scanner;

public class CustomerViewer {
    private final Scanner SCANNER;
    private StaffDTO logIn;
    private Connection connection;

    public CustomerViewer(Scanner scanner,Connection connection,StaffDTO logIn) {
        this.connection = connection;
        this.logIn = logIn;
        SCANNER = scanner;
    }

    public void showMenu(){
        System.out.println("===================================================");
        String message = "1. 고객 등록 2. 고객 수정 3. 고객 삭제 4. 뒤로가기";
        int userChoice = ScannerUtil.nextInt(SCANNER,message);

        if(userChoice == 1){
            register();
        } else if (userChoice == 2) {
            update();
        } else if (userChoice == 3) {
            delete();
        }
    }

    public void register(){
        String country = country();
        String city = city(country);
        String address = address(city);
        customer(address);
    }

    private void update(){
        String message = "수정할 고객의 이메일을 입력하세요.";
        String email = ScannerUtil.nextLine(SCANNER, message);

        CustomerController customerController = new CustomerController(connection);

        while (!customerController.confirm(email)){
            System.out.println("존재하지 않는 고객의 이메일입니다.");
            email = ScannerUtil.nextLine(SCANNER,message);
        }

        String country = country();
        String city = city(country);
        String address = address(city);
        updateCustomer(address,email);
    }

    private void delete(){
        String message = "삭제할 고객의 이메일을 입력하세요.";
        String email = ScannerUtil.nextLine(SCANNER, message);

        CustomerController customerController = new CustomerController(connection);

        while (!customerController.confirm(email)){
            System.out.println("존재하지 않는 고객의 이메일입니다.");
            email = ScannerUtil.nextLine(SCANNER,message);
        }

        message = "정말로 고객을 삭제하시겠습니까? Y/N";
        String yesNo = ScannerUtil.nextLine(SCANNER, message);

        if (yesNo.equalsIgnoreCase("Y")) {
            customerController.delete(email);
        }
    }


    private void customer(String address){
        String message = "고객의 firstName을 입력해주세요.";
        String firstName = ScannerUtil.nextLine(SCANNER,message);

        message = "고객의 lastName을 입력해주세요.";
        String lastName = ScannerUtil.nextLine(SCANNER,message);

        message = "고객이 사용할 이메일을 입력해주세요.";
        String email = ScannerUtil.nextLine(SCANNER,message);

        CustomerController customerController = new CustomerController(connection);
        while (customerController.confirm(email)){
            System.out.println("현재 사용중인 고객이 있는 이메일입니다.");
            email = ScannerUtil.nextLine(SCANNER,message);
        }
        customerController.register(logIn.getStoreId(),firstName,lastName,email,address);
    }

    private void updateCustomer(String address,String originEmail){
        String message = "고객의 firstName을 입력해주세요.";
        String firstName = ScannerUtil.nextLine(SCANNER,message);

        message = "고객의 lastName을 입력해주세요.";
        String lastName = ScannerUtil.nextLine(SCANNER,message);

        message = "고객이 사용할 이메일을 입력해주세요.";
        String email = ScannerUtil.nextLine(SCANNER,message);

        CustomerController customerController = new CustomerController(connection);
        while (customerController.confirm(email)){
            System.out.println("현재 사용중인 고객이 있는 이메일입니다.");
            email = ScannerUtil.nextLine(SCANNER,message);
        }
        customerController.update(logIn.getStoreId(),firstName,lastName,email,address,originEmail);
    }


    private String country(){
        String message = "고객의 나라를 입력해주세요.";
        String country = ScannerUtil.nextLine(SCANNER,message);

        CountryController countryController = new CountryController(connection);

        if(!countryController.register(country)){
            System.out.println("등록되어 있는 나라입니다.");
        } else {
            System.out.println("새로운 나라입니다.");
        }
        return country;
    }

    private String city(String country){
        String message = "고객의 도시를 입력해주세요.";
        String city = ScannerUtil.nextLine(SCANNER,message);

        CityController cityController = new CityController(connection);

        if(cityController.confirm(city)){
            System.out.println("등록되어 있는 도시입니다.");

            message = "같은 이름을 가진 다른 도시 입니까? Y/N";
            String yesNo = ScannerUtil.nextLine(SCANNER, message);

            if (yesNo.equalsIgnoreCase("Y")) {
                cityController.register(city,country);
            }
        }
        return city;
    }

    private String address(String city){
        String message = "고객의 주소를 입력해주세요.";
        String address = ScannerUtil.nextLine(SCANNER,message);

        AddressController addressController= new AddressController(connection);
        if(!addressController.confirm(address)){
            System.out.println("새로운 주소입니다.");

            message = "고객의 지역을 입력해주세요.";
            String district = ScannerUtil.nextLine(SCANNER,message);

            message = "고객의 핸드폰 번호를 입력해주세요.";
            String phone = ScannerUtil.nextLine(SCANNER,message);

            message = "고객의 위도를 입력해주세요.(-90 ~ 90)";
            int latitude = ScannerUtil.nextInt(SCANNER,message,-90,90);

            message = "고객의 경도를 입력해주세요.(-180 ~ 180)";
            int longitude = ScannerUtil.nextInt(SCANNER,message,-180,180);

            addressController.register(address,city,district,phone,latitude,longitude);
        } else {
            System.out.println("등록되어 있는 주소입니다.");
        }
        return address;
    }

}
