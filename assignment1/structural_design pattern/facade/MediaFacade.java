package facade;

public class MediaFacade {
    private final AudioPlayer audioPlayer;
    private final VideoPlayer videoPlayer;
    private final ImageViewer imageViewer;

    public MediaFacade() {
        this.audioPlayer = new AudioPlayer();
        this.videoPlayer = new VideoPlayer();
        this.imageViewer = new ImageViewer();
    }

    public void playMedia(String fileType, String fileName) {
        switch (fileType.toLowerCase()) {
            case "audio":
                audioPlayer.playAudio(fileName);
                break;
            case "video":
                videoPlayer.playVideo(fileName);
                break;
            case "image":
                imageViewer.displayImage(fileName);
                break;
            default:
                throw new IllegalArgumentException("Unsupported media type: " + fileType);
        }
    }
}
