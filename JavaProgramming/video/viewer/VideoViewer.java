package video.viewer;

import util.ScannerUtil;
import video.controller.CustomerController;
import video.controller.LanguageController;
import video.controller.StaffController;
import video.controller.VideoController;
import video.model.StaffDTO;
import video.model.VideoDTO;

import java.sql.Connection;
import java.util.Scanner;

public class VideoViewer {
    private final Scanner SCANNER;
    private Connection connection;

    public VideoViewer(Scanner scanner, Connection connection, StaffDTO logIn) {
        this.connection = connection;
        SCANNER = scanner;

    }
    public void showMenu() {
        while(true)
        {
            System.out.println("===================================================");
            String message = "1. 비디오 등록 2. 비디오 수정 3. 비디오 삭제 4. 비디오 제목으로 찾기 5. 뒤로가기";

            VideoDTO videoDTO;
            int userChoice = ScannerUtil.nextInt(SCANNER, message);
            if (userChoice == 1){
                register();
            } else if (userChoice == 2) {
                update();
            } else if (userChoice == 3) {
                delete();
            } else if(userChoice == 4){
                videoDTO = titleSearch();
                videoPrint(videoDTO);
            } else if (userChoice == 5) {
                break;
            }
        }
    }

    private void register(){
        String language = language();

        String message = "등록하실 비디오의 이름을 입력하세요.";
        String title = ScannerUtil.nextLine(SCANNER,message);

        message = "등록하실 비디오의 줄거리를 입력하세요.";
        String description = ScannerUtil.nextLine(SCANNER,message);

        message = "등록하실 비디오의 개봉년도를 입력하세요.";
        String releaseYear = ScannerUtil.nextLine(SCANNER,message);

        message = "등록하실 비디오의 대여가능기간을 입력하세요.";
        int rentalDuration = ScannerUtil.nextInt(SCANNER,message);

        message = "등록하실 비디오의 연령제한을 입력하세요.";
        String rating = ScannerUtil.nextLine(SCANNER,message);

        message = "등록하실 비디오의 별첨을 입력하세요.";
        String specialFeatures = ScannerUtil.nextLine(SCANNER,message);

        VideoController videoController = new VideoController(connection);
        videoController.register(language,title,description,releaseYear,rentalDuration,rating,specialFeatures);
    }
    private void update(){
        String message = "수정할 비디오의 제목을 입력하세요.";
        String oldTitle = ScannerUtil.nextLine(SCANNER, message);

        VideoController videoController = new VideoController(connection);

        while (!videoController.confirm(oldTitle)){
            System.out.println("존재하지 않는 비디오입니다.");
            oldTitle = ScannerUtil.nextLine(SCANNER,message);
        }

        String language = language();

        message = "비디오의 이름을 입력하세요.";
        String title = ScannerUtil.nextLine(SCANNER,message);

        message = "등록하실 비디오의 줄거리를 입력하세요.";
        String description = ScannerUtil.nextLine(SCANNER,message);

        message = "등록하실 비디오의 개봉년도를 입력하세요.";
        String releaseYear = ScannerUtil.nextLine(SCANNER,message);

        message = "등록하실 비디오의 대여가능기간을 입력하세요.";
        int rentalDuration = ScannerUtil.nextInt(SCANNER,message);

        message = "등록하실 비디오의 연령제한을 입력하세요.";
        String rating = ScannerUtil.nextLine(SCANNER,message);

        message = "등록하실 비디오의 별첨을 입력하세요.";
        String specialFeatures = ScannerUtil.nextLine(SCANNER,message);

        videoController.update(language,title,description,releaseYear,rentalDuration,rating,specialFeatures,oldTitle);

    }

    private void delete(){
        String message = "삭제할 비디오의 제목을 입력하세요.";
        String title = ScannerUtil.nextLine(SCANNER, message);

        VideoController videoController = new VideoController(connection);

        while (!videoController.confirm(title)){
            System.out.println("존재하지 않는 비디오입니다.");
            title = ScannerUtil.nextLine(SCANNER,message);
        }

        message = "정말로 비디오를 삭제하시겠습니까? Y/N";
        String yesNo = ScannerUtil.nextLine(SCANNER, message);

        if (yesNo.equalsIgnoreCase("Y")) {
            videoController.delete(title);
        }

    }
    private String language(){
        String message = "등록하실 비디오의 언어를 입력하세요.";
        String language = ScannerUtil.nextLine(SCANNER,message);

        LanguageController languageController = new LanguageController(connection);

        if (!languageController.confirm(language)) {
            System.out.println("새로운 언어입니다.");
            languageController.register(language);
        } else {
            System.out.println("기존에 등록되어있는 언어입니다.");
        }
        return language;
    }

    public VideoDTO titleSearch(){
        String message = "비디오 제목을 입력하세요.";
        String title = ScannerUtil.nextLine(SCANNER,message);

        VideoController videoController = new VideoController(connection);

        while(!videoController.confirm(title)){
            message = "존재하지 않는 비디오입니다. 다시 입력바랍니다.";
            title = ScannerUtil.nextLine(SCANNER,message);
        }

        VideoDTO videoDTO;
        videoDTO = videoController.titleSearch(title);

        return videoDTO;
    }

    private void videoPrint(VideoDTO videoDTO){
        System.out.printf("============ %d. %s ============\n",videoDTO.getId(),videoDTO.getTitle());
        System.out.printf("줄거리: %s\n",videoDTO.getDescription());
        System.out.printf("개봉년도: %s  대여가능기간: %d\n",videoDTO.getReleaseYear(),videoDTO.getRentalDuration());
        System.out.printf("연령제한: %s 별첨: %s\n",videoDTO.getRating(),videoDTO.getSpecialFeatures());
        System.out.printf("대여료: %.2f\n",videoDTO.getRentalRate());
        System.out.println("===================================================");
    }

    /*
    private void categorySearch(){
        String message = "비디오 카테고리를 입력하세요.";
        String videoCate = ScannerUtil.nextLine(SCANNER,message);

        VideoController videoController = new VideoController(connection);
        videoController.cateSearch(videoCate);
    }
    private void actorSearch(){
        String message = "비디오의 등장인물을 입력하세요.";
        String videoActor = ScannerUtil.nextLine(SCANNER,message);

        VideoController videoController = new VideoController(connection);
        videoController.actorSearch(videoActor);
    }*/

}
