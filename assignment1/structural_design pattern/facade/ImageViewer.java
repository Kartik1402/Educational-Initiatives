package facade;

import common.Logger;

public class ImageViewer {
    public void displayImage(String fileName) {
        Logger.logInfo("Displaying image file: " + fileName);
    }
}
