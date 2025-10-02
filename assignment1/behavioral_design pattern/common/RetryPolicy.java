package common;

public class RetryPolicy {
    public static void execute(Runnable action, int retries, int delayMilliseconds) {
        int attempt = 0;
        while (attempt < retries) {
            try {
                action.run();
                return;
            } catch (Exception ex) {
                attempt++;
                Logger.logError("Attempt " + attempt + " failed: " + ex.getMessage());
                if (attempt >= retries) {
                    throw ex;
                }
                try {
                    Thread.sleep(delayMilliseconds);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
