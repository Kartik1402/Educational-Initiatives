package builder;

import java.util.ArrayList;
import java.util.List;

public class Itinerary {
    private String destination;
    private String flight;
    private String hotel;
    private List<String> activities;

    private Itinerary(Builder builder) {
        this.destination = builder.destination;
        this.flight = builder.flight;
        this.hotel = builder.hotel;
        this.activities = builder.activities;
    }

    public void showItinerary() {
        System.out.println("Travel Itinerary:");
        System.out.println("Destination: " + destination);
        System.out.println("Flight: " + flight);
        System.out.println("Hotel: " + hotel);
        System.out.println("Activities:");
        for (String activity : activities) {
            System.out.println(" - " + activity);
        }
    }

    public static class Builder {
        private String destination;
        private String flight;
        private String hotel;
        private List<String> activities = new ArrayList<>();

        public Builder setDestination(String destination) {
            this.destination = destination;
            return this;
        }

        public Builder setFlight(String flight) {
            this.flight = flight;
            return this;
        }

        public Builder setHotel(String hotel) {
            this.hotel = hotel;
            return this;
        }

        public Builder addActivity(String activity) {
            this.activities.add(activity);
            return this;
        }

        public Itinerary build() {
            return new Itinerary(this);
        }
    }
}
