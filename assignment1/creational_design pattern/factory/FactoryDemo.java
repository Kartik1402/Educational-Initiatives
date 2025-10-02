package factory;

public class FactoryDemo {
    public static void runDemo() {
        NotificationFactory factory = new NotificationFactory();

        Notification email = factory.createNotification("EMAIL");
        email.notifyUser();

        Notification sms = factory.createNotification("SMS");
        sms.notifyUser();

        Notification push = factory.createNotification("PUSH");
        push.notifyUser();
    }
}
