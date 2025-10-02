package proxy;

import common.Logger;

public class RealImage implements Image {
    private final String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromDisk();
    }

    private void loadFromDisk() {
        Logger.logInfo("Loading image from disk: " + fileName);
    }

    public void display() {
        Logger.logInfo("Displaying image: " + fileName);
    }
}
