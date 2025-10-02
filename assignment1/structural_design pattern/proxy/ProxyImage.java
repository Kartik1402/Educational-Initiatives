package proxy;

import common.Logger;

public class ProxyImage implements Image {
    private RealImage realImage;
    private final String fileName;

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    public void display() {
        if (realImage == null) {
            Logger.logInfo("Creating RealImage instance lazily for: " + fileName);
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }
}
