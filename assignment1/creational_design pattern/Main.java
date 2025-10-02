import java.util.Scanner;
import factory.FactoryDemo;
import builder.BuilderDemo;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a demo to run:");
        System.out.println("1. Factory Pattern Demo");
        System.out.println("2. Builder Pattern Demo");
        System.out.print("Enter choice: ");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                FactoryDemo.runDemo();
                break;
            case 2:
                BuilderDemo.runDemo();
                break;
            default:
                System.out.println("Invalid choice.");
        }
        scanner.close();
    }
}
