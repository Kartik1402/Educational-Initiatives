package facade;

import common.Logger;

public class VideoPlayer {
    public void playVideo(String fileName) {
        Logger.logInfo("Playing video file: " + fileName);
    }
}
