import facade.MediaFacade;
import proxy.Image;
import proxy.ProxyImage;
import common.Logger;

public class Main {
    public static void main(String[] args) {
        Logger.logInfo("=== FACADE PATTERN DEMO ===");
        MediaFacade mediaFacade = new MediaFacade();
        mediaFacade.playMedia("audio", "song.mp3");
        mediaFacade.playMedia("video", "movie.mp4");
        mediaFacade.playMedia("image", "photo.jpg");

        Logger.logInfo("=== PROXY PATTERN DEMO ===");
        Image image1 = new ProxyImage("test1.jpg");
        Image image2 = new ProxyImage("test2.jpg");

        // Images will be loaded only when display is called
        image1.display();
        image1.display(); // this time cached

        image2.display();
    }
}
