class PierController {
    private final PierModel model;
    private final PierView view;
    private int passengersServed = 0;

    public PierController(PierModel model, PierView view) {
        this.model = model;
        this.view = view;
    }

    public void simulate(int timeUnits) {
        for (int i = 0; i < timeUnits; i++) {
            model.simulateStep();

            int queueBefore = model.getQueueSize();

            if (model.isBoatArrivalTime()) {
                int boarded = model.processBoatArrival();
                passengersServed += boarded;
                view.showBoatArrival(boarded, 10); // simplified seats
            }

            if (model.getQueueSize() > queueBefore) {
                view.showPassengerArrival(model.getCurrentTime(), model.getQueueSize());
            }

            if (model.isOverflow()) {
                view.showOverflow(model.getQueueSize());
            }
        }

        view.showResults(passengersServed, 5.0, model.getPassengerInterval() * 5);
    }
}