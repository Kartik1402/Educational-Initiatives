package builder;

public class BuilderDemo {
    public static void runDemo() {
        Itinerary vacation = new Itinerary.Builder()
                .setDestination("Paris")
                .setFlight("Flight AF123")
                .setHotel("Hilton Paris")
                .addActivity("Visit Eiffel Tower")
                .addActivity("Louvre Museum Tour")
                .addActivity("Seine River Cruise")
                .build();

        vacation.showItinerary();

        Itinerary businessTrip = new Itinerary.Builder()
                .setDestination("New York")
                .setFlight("Flight DL456")
                .setHotel("Marriott Manhattan")
                .addActivity("Business Conference")
                .addActivity("Client Meeting")
                .build();

        businessTrip.showItinerary();
    }
}
