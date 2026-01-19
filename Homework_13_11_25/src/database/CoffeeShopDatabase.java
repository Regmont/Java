package database;

import model.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Класс представляет модель базы данных сети "Кофейня".
 * Автор: Дунин Михаил
 */
public class CoffeeShopDatabase {
    private final Map<Integer, CoffeeOrder> coffeeOrders;
    private final Map<Integer, DessertOrder> dessertOrders;
    private final Map<String, String> workSchedule;
    private final Map<String, Client> clients;

    private final Map<Integer, CoffeeShop> coffeeShops;
    private final Map<Integer, Employee> employees;
    private final Map<Integer, ShopProduct> shopProducts;
    private final List<ArchivedProduct> archivedProducts;
    private final List<EmployeeTransfer> employeeTransfers;
    private final Set<Integer> blacklistedEmployees;

    private int coffeeShopIdCounter;
    private int employeeIdCounter;
    private int shopProductIdCounter;
    private int coffeeOrderIdCounter;
    private int dessertOrderIdCounter;

    public CoffeeShopDatabase() {
        coffeeOrders = new HashMap<>();
        dessertOrders = new HashMap<>();
        workSchedule = new HashMap<>();
        clients = new HashMap<>();

        coffeeShops = new HashMap<>();
        employees = new HashMap<>();
        shopProducts = new HashMap<>();
        archivedProducts = new ArrayList<>();
        employeeTransfers = new ArrayList<>();
        blacklistedEmployees = new HashSet<>();

        coffeeShopIdCounter = 1;
        employeeIdCounter = 1;
        shopProductIdCounter = 1;
        coffeeOrderIdCounter = 1;
        dessertOrderIdCounter = 1;

        initTestData();
    }

    private void initTestData() {
        addCoffeeShop("Центральная", "ул. Ленина, 10", "+7-111-111-11-11");
        addCoffeeShop("Набережная", "ул. Набережная, 5", "+7-222-222-22-22");
        addCoffeeShop("Торговая", "ТРЦ Мега", "+7-333-333-33-33");

        addEmployee("Анна Смирнова", "бариста", 1);
        addEmployee("Игорь Волков", "бариста", 1);
        addEmployee("Ольга Петрова", "официант", 1);
        addEmployee("Денис Козлов", "бариста", 2);
        addEmployee("Марина Соколова", "официант", 2);
        addEmployee("Сергей Морозов", "бариста", 3);

        addShopProduct(1, 1, "Капучино", "напиток", 180.0);
        addShopProduct(1, 2, "Латте", "напиток", 190.0);
        addShopProduct(1, 3, "Тирамису", "десерт", 250.0);

        addShopProduct(2, 1, "Капучино", "напиток", 170.0);
        addShopProduct(2, 4, "Эспрессо", "напиток", 120.0);
        addShopProduct(2, 3, "Тирамису", "десерт", 260.0);

        addShopProduct(3, 1, "Капучино", "напиток", 185.0);
        addShopProduct(3, 5, "Чизкейк", "десерт", 280.0);
        addShopProduct(3, 6, "Американо", "напиток", 150.0);
    }


    public int addCoffeeOrder(String coffeeName, int quantity, String customer) {
        int id = coffeeOrderIdCounter++;
        coffeeOrders.put(id, new CoffeeOrder(id, coffeeName, quantity, customer));
        return id;
    }

    public int addDessertOrder(String dessertName, int quantity, String customer) {
        int id = dessertOrderIdCounter++;
        dessertOrders.put(id, new DessertOrder(id, dessertName, quantity, customer));
        return id;
    }

    public void addOrUpdateWorkSchedule(String dayOfWeek, String schedule) {
        workSchedule.put(dayOfWeek, schedule);
    }

    public int addCoffeeType(String name, String description, double price) {
        int id = shopProductIdCounter++;
        shopProducts.put(id, new ShopProduct(0, id, name, "напиток", price));

        return id;
    }

    public void addClient(Client client) {
        clients.put(client.getName(), client);
    }

    public void updateWorkSchedule(String dayOfWeek, String newSchedule) {
        workSchedule.put(dayOfWeek, newSchedule);
    }

    public void updateCoffeeTypeName(int coffeeTypeId, String newName) {
        ShopProduct product = shopProducts.values().stream()
                .filter(p -> p.getProductId() == coffeeTypeId)
                .findFirst()
                .orElse(null);

        if (product == null) {
            throw new IllegalArgumentException("Вид кофе с ID " + coffeeTypeId + " не найден.");
        }
    }

    public void updateCoffeeOrder(int orderId, String coffeeName, int quantity, String customer) {
        CoffeeOrder order = coffeeOrders.get(orderId);
        if (order == null) {
            throw new IllegalArgumentException("Заказ кофе с ID " + orderId + " не найден.");
        }
        if (coffeeName != null) order.setCoffeeName(coffeeName);
        if (quantity >= 0) order.setQuantity(quantity);
        if (customer != null) order.setCustomer(customer);
    }

    public void updateDessertName(String oldDessertName, String newDessertName) {
        for (DessertOrder order : dessertOrders.values()) {
            if (order.getDessertName().equals(oldDessertName)) {
                order.setDessertName(newDessertName);
            }
        }
    }

    public double getMinDiscount() {
        return clients.values().stream()
                .mapToDouble(Client::getDiscount)
                .min()
                .orElse(0.0);
    }

    public double getMaxDiscount() {
        return clients.values().stream()
                .mapToDouble(Client::getDiscount)
                .max()
                .orElse(0.0);
    }

    public List<Client> getClientsWithMinDiscount() {
        double minDiscount = getMinDiscount();
        List<Client> result = new ArrayList<>();
        for (Client client : clients.values()) {
            if (Math.abs(client.getDiscount() - minDiscount) < 0.001) {
                result.add(client);
            }
        }
        return result;
    }

    public List<Client> getClientsWithMaxDiscount() {
        double maxDiscount = getMaxDiscount();
        List<Client> result = new ArrayList<>();
        for (Client client : clients.values()) {
            if (Math.abs(client.getDiscount() - maxDiscount) < 0.001) {
                result.add(client);
            }
        }
        return result;
    }

    public double getAverageDiscount() {
        return clients.values().stream()
                .mapToDouble(Client::getDiscount)
                .average()
                .orElse(0.0);
    }

    public Client getYoungestClient() {
        return clients.values().stream()
                .max(Comparator.comparing(Client::getAge).reversed())
                .orElse(null);
    }

    public Client getOldestClient() {
        return clients.values().stream()
                .max(Comparator.comparing(Client::getAge))
                .orElse(null);
    }

    public List<Client> getClientsWithBirthdayToday() {
        List<Client> result = new ArrayList<>();
        for (Client client : clients.values()) {
            if (client.hasBirthdayToday()) {
                result.add(client);
            }
        }
        return result;
    }

    public List<Client> getClientsWithoutEmail() {
        List<Client> result = new ArrayList<>();
        for (Client client : clients.values()) {
            if (!client.hasEmail()) {
                result.add(client);
            }
        }
        return result;
    }

    public Map<String, Client> getClients() {
        return new HashMap<>(clients);
    }

    public Map<Integer, CoffeeOrder> getCoffeeOrders() {
        return new HashMap<>(coffeeOrders);
    }

    public Map<Integer, DessertOrder> getDessertOrders() {
        return new HashMap<>(dessertOrders);
    }

    public Map<String, String> getWorkSchedule() {
        return new HashMap<>(workSchedule);
    }

    public void addCoffeeShop(String name, String address, String phone) {
        int id = coffeeShopIdCounter++;
        CoffeeShop shop = new CoffeeShop(id, name, address, phone);
        coffeeShops.put(id, shop);
    }

    public void addEmployee(String name, String position, int coffeeShopId) {
        int id = employeeIdCounter++;
        Employee employee = new Employee(id, name, position, coffeeShopId);
        employees.put(id, employee);
    }

    public void addShopProduct(int coffeeShopId, int productId,
                               String name, String type, double price) {
        int id = shopProductIdCounter++;
        ShopProduct product = new ShopProduct(coffeeShopId, productId, name, type, price);
        shopProducts.put(id, product);
    }

    public void removeDrink(int shopProductId, int coffeeShopId) {
        ShopProduct product = shopProducts.get(shopProductId);
        if (product != null && "напиток".equals(product.getType())) {
            ArchivedProduct archived = new ArchivedProduct(
                    product.getProductId(),
                    product.getName(),
                    product.getType(),
                    product.getPrice(),
                    coffeeShopId
            );
            archivedProducts.add(archived);
            product.setAvailable(false);
        }
    }

    public void removeDessert(int shopProductId, int coffeeShopId) {
        ShopProduct product = shopProducts.get(shopProductId);
        if (product != null && "десерт".equals(product.getType())) {
            ArchivedProduct archived = new ArchivedProduct(
                    product.getProductId(),
                    product.getName(),
                    product.getType(),
                    product.getPrice(),
                    coffeeShopId
            );
            archivedProducts.add(archived);
            product.setAvailable(false);
        }
    }

    public void transferEmployee(int employeeId, int toCoffeeShopId) {
        Employee employee = employees.get(employeeId);
        if (employee != null && !blacklistedEmployees.contains(employeeId)) {
            int fromCoffeeShopId = employee.getCoffeeShopId();
            employee.setCoffeeShopId(toCoffeeShopId);

            EmployeeTransfer transfer = new EmployeeTransfer(
                    employeeId, fromCoffeeShopId, toCoffeeShopId
            );
            employeeTransfers.add(transfer);
        }
    }

    public List<CoffeeShop> getAllCoffeeShops() {
        return new ArrayList<>(coffeeShops.values());
    }

    public List<String> getDrinksAvailableInAllShops() {
        Map<String, Set<Integer>> drinkToShops = new HashMap<>();

        for (ShopProduct product : shopProducts.values()) {
            if ("напиток".equals(product.getType()) && product.isAvailable()) {
                drinkToShops
                        .computeIfAbsent(product.getName(), k -> new HashSet<>())
                        .add(product.getCoffeeShopId());
            }
        }

        int totalShops = coffeeShops.size();
        return drinkToShops.entrySet().stream()
                .filter(entry -> entry.getValue().size() == totalShops)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public List<String> getDessertsAvailableInAllShops() {
        Map<String, Set<Integer>> dessertToShops = new HashMap<>();

        for (ShopProduct product : shopProducts.values()) {
            if ("десерт".equals(product.getType()) && product.isAvailable()) {
                dessertToShops
                        .computeIfAbsent(product.getName(), k -> new HashSet<>())
                        .add(product.getCoffeeShopId());
            }
        }

        int totalShops = coffeeShops.size();
        return dessertToShops.entrySet().stream()
                .filter(entry -> entry.getValue().size() == totalShops)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public List<Employee> getAllBaristas() {
        return employees.values().stream()
                .filter(e -> "бариста".equals(e.getPosition()))
                .collect(Collectors.toList());
    }

    public List<Employee> getAllWaiters() {
        return employees.values().stream()
                .filter(e -> "официант".equals(e.getPosition()))
                .collect(Collectors.toList());
    }

    public String getMostPopularDrink() {
        return shopProducts.values().stream()
                .filter(p -> "напиток".equals(p.getType()) && p.isAvailable())
                .map(ShopProduct::getName)
                .findFirst()
                .orElse("нет данных");
    }

    public String getMostPopularDessert() {
        return shopProducts.values().stream()
                .filter(p -> "десерт".equals(p.getType()) && p.isAvailable())
                .map(ShopProduct::getName)
                .findFirst()
                .orElse("нет данных");
    }

    public List<String> getTop3Drinks() {
        return shopProducts.values().stream()
                .filter(p -> "напиток".equals(p.getType()) && p.isAvailable())
                .map(ShopProduct::getName)
                .distinct()
                .limit(3)
                .collect(Collectors.toList());
    }

    public List<String> getTop3Desserts() {
        return shopProducts.values().stream()
                .filter(p -> "десерт".equals(p.getType()) && p.isAvailable())
                .map(ShopProduct::getName)
                .distinct()
                .limit(3)
                .collect(Collectors.toList());
    }

    public List<Employee> getBaristasWorkedInAllShops() {
        Map<Integer, Set<Integer>> employeeToShops = new HashMap<>();

        for (EmployeeTransfer transfer : employeeTransfers) {
            employeeToShops
                    .computeIfAbsent(transfer.getEmployeeId(), k -> new HashSet<>())
                    .add(transfer.getFromCoffeeShopId());
            employeeToShops
                    .computeIfAbsent(transfer.getEmployeeId(), k -> new HashSet<>())
                    .add(transfer.getToCoffeeShopId());
        }

        for (Employee emp : getAllBaristas()) {
            employeeToShops
                    .computeIfAbsent(emp.getId(), k -> new HashSet<>())
                    .add(emp.getCoffeeShopId());
        }

        int totalShops = coffeeShops.size();
        return employeeToShops.entrySet().stream()
                .filter(entry -> entry.getValue().size() == totalShops)
                .map(entry -> employees.get(entry.getKey()))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public void addToBlacklist(int employeeId) {
        blacklistedEmployees.add(employeeId);
    }

    public List<Employee> getBlacklistedEmployees() {
        return blacklistedEmployees.stream()
                .map(employees::get)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public Map<Integer, CoffeeShop> getCoffeeShopsMap() {
        return new HashMap<>(coffeeShops);
    }

    public Map<Integer, Employee> getEmployeesMap() {
        return new HashMap<>(employees);
    }

    public Map<Integer, ShopProduct> getShopProductsMap() {
        return new HashMap<>(shopProducts);
    }

    public List<ArchivedProduct> getArchivedProducts() {
        return new ArrayList<>(archivedProducts);
    }

    public List<EmployeeTransfer> getEmployeeTransfers() {
        return new ArrayList<>(employeeTransfers);
    }
}
