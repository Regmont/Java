class PierView {
    public void showPassengerArrival(int time, int queueSize) {
        System.out.println("Passenger arrived at " + time + ". Queue: " + queueSize);
    }

    public void showBoatArrival(int boarded, int freeSeats) {
        System.out.println("Boat took " + boarded + " passengers. Free seats: " + freeSeats);
    }

    public void showOverflow(int queueSize) {
        System.out.println("⚠OVERFLOW! " + queueSize + " people in queue!");
    }

    public void showResults(int served, double avgWait, double recommendedInterval) {
        System.out.println("\n=== RESULTS ===");
        System.out.println("Passengers served: " + served);
        System.out.println("Average waiting time: " + avgWait);
        System.out.println("Recommended boat interval: " + recommendedInterval);
    }
}