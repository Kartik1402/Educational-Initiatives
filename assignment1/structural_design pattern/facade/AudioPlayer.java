package facade;

import common.Logger;

public class AudioPlayer {
    public void playAudio(String fileName) {
        Logger.logInfo("Playing audio file: " + fileName);
    }
}
