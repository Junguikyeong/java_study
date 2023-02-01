package video;

import dbConn.ConnectionMaker;
import dbConn.MySqlConnectionMaker;

import video.viewer.StaffViewer;


public class VideoRentalSystem {
    public static void main(String[] args) {
        ConnectionMaker connectionMaker = new MySqlConnectionMaker();

        StaffViewer staffViewer = new StaffViewer(connectionMaker);

        staffViewer.showIndex();
    }
}
