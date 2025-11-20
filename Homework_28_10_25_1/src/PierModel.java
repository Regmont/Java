import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

class PierModel {
    private final Queue<Integer> passengerQueue = new LinkedList<>();
    private final Random random = new Random();

    private final double passengerInterval;
    private final double boatInterval;
    private final int maxPeople;
    private int currentTime = 0;

    public PierModel(double passengerInterval, double boatInterval, int maxPeople) {
        this.passengerInterval = passengerInterval;
        this.boatInterval = boatInterval;
        this.maxPeople = maxPeople;
    }

    public void simulateStep() {
        currentTime++;

        if (currentTime % passengerInterval == 0) {
            passengerQueue.add(currentTime);
        }
    }

    public int processBoatArrival() {
        int freeSeats = random.nextInt(10) + 1;
        int boarded = 0;

        while (!passengerQueue.isEmpty() && boarded < freeSeats) {
            passengerQueue.poll();
            boarded++;
        }

        return boarded;
    }

    public boolean isBoatArrivalTime() {
        return currentTime % boatInterval == 0;
    }

    public boolean isOverflow() {
        return passengerQueue.size() > maxPeople;
    }

    public int getQueueSize() { return passengerQueue.size(); }
    public int getCurrentTime() { return currentTime; }
    public double getPassengerInterval() { return passengerInterval; }
}