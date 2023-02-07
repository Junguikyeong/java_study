package day0112;
import viewer.BoardViewer;
import viewer.UserViewer;

import java.util.Scanner;

// 로그인부터해야하니 userviewer가 먼저 실행이 되어야 한다.
public class Ex04UserBoard {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        BoardViewer boardViewer = new BoardViewer(scanner);
        UserViewer userViewer = new UserViewer(scanner);

        userViewer.setBoardViewer(boardViewer);
        //boardViewer.setUserViewer(userViewer);

        userViewer.showIndex();

    }
}

// 두가지 방법으로 의존성을 주입가능
// 1. 생성자를통해 의존관계 -> 생성자 주입
// 2.