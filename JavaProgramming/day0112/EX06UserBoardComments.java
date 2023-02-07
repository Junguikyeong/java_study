//package day0112;
//
//import viewer.BoardViewer;
//import viewer.CommentsViewer;
//import viewer.UserViewer;
//
//import java.util.Scanner;
//
//public class EX06UserBoardComments {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        BoardViewer boardViewer = new BoardViewer(scanner);
//        UserViewer userViewer = new UserViewer(scanner);
//        CommentsViewer commentsViewer = new CommentsViewer(scanner);
//
//        userViewer.setBoardViewer(boardViewer);
//        boardViewer.setCommentsViewer(commentsViewer);
//        commentsViewer.setBoardViewer(boardViewer);
//
//        userViewer.showIndex();
//    }
//}
