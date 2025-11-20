public class ClientRunner {
    public static void main(String[] args) {
        PierModel model = new PierModel(3, 10, 5);
        PierView view = new PierView();
        PierController controller = new PierController(model, view);

        controller.simulate(60);
    }
}
