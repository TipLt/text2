
import java.util.ArrayList;

public class Manager {

    // Hiển thị menu
    static int menu() {
        System.out.println("1. Create Fruit");
        System.out.println("2. View orders");
        System.out.println("3. Shopping (for buyer)");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
        int choice = Validation.checkInputIntLimit(1, 4);
        return choice;
    }

    // Cho phép người dùng tạo trái cây
    static void createFruit(ArrayList<Fruit> lf) {
        while (true) {
            System.out.print("Enter fruit id: ");
            String fruitId = Validation.checkInputString();
            // Kiểm tra id có tồn tại không
            Fruit existingFruit = Validation.getFruitById(lf, fruitId);
            if (existingFruit != null) {
                System.out.println("FruitID exists. Detail:");
                System.out.println("Name: " + existingFruit.getFruitName());
                System.out.println("Quantity: " + existingFruit.getQuantity());
                System.out.println("Price: " + existingFruit.getPrice());
                System.out.println("Origin: " + existingFruit.getOrigin());
                System.out.println("Do you want to add more quantity to this fruit(Y/n or y/N)");
                if(Validation.checkInputYN1()){
                    System.out.println("Enter addtional quantity");
                    int addQuantity = Validation.checkInputInt();
                    existingFruit.setQuantity(existingFruit.getQuantity() + addQuantity);
                    System.out.println("Updated quantity: " + existingFruit.getQuantity());
                }
            } else {
                System.out.print("Enter fruit name: ");
                String fruitName = Validation.checkInputString();
                System.out.print("Enter price: ");
                double price = Validation.checkInputDouble();
                System.out.print("Enter quantity: ");
                int quantity = Validation.checkInputInt();
                System.out.print("Enter origin: ");
                String origin = Validation.checkInputString();
                lf.add(new Fruit(fruitId, fruitName, price, quantity, origin));
            }

            if (!Validation.checkInputYN()) {
                return;
            }
        }
    }

    // Hiển thị danh sách đơn hàng theo khách hàng
    static void viewOrder(ArrayList<Order> orders) {
        for (Order order : orders) {
            System.out.println("Customer: " + order.getCustomerName());
            displayListOrder((ArrayList<Fruit>) order.getOrderList());
        }
    }

    // Cho phép người dùng mua hàng
    static void shopping(ArrayList<Fruit> lf, ArrayList<Order> orders) {
        if (lf.isEmpty()) {
            System.err.println("No have item.");
            return;
        }
        // Xây dựng giỏ hàng dưới dạng danh sách các Fruit
        ArrayList<Fruit> cart = new ArrayList<>();
        while (true) {
            displayListFruit(lf);
            System.out.print("Enter item: ");
            int item = Validation.checkInputIntLimit(1, lf.size());
            Fruit selectedFruit = getFruitByItem(lf, item);
            if (selectedFruit == null) {
                System.err.println("Invalid item selection.");
                continue;
            }
            System.out.print("Enter quantity: ");
            int quantity = Validation.checkInputIntLimit(1, selectedFruit.getQuantity());
            // Update số lượng trái cây trong kho
            selectedFruit.setQuantity(selectedFruit.getQuantity() - quantity);
            // Nếu trái cây đã có trong giỏ hàng thì cập nhật quantity, nếu chưa có thì thêm mới
            if (checkFruitExist(cart, selectedFruit.getFruitId())) {
                updateFruitQuantity(cart, selectedFruit.getFruitId(), quantity);
            } else {
                // Tạo một bản sao của trái cây với số lượng đặt hàng
                Fruit orderedFruit = new Fruit(selectedFruit.getFruitId(), selectedFruit.getFruitName(), selectedFruit.getPrice(), quantity, selectedFruit.getOrigin());
                cart.add(orderedFruit);
            }
            if (!Validation.checkInputYN()) {
                break;
            }
        }
        // Hiển thị giỏ hàng
        displayListOrder(cart);
        System.out.print("Enter your name: ");
        String name = Validation.checkInputString();
        orders.add(new Order(name, cart));
        System.err.println("Add successful");
    }

    // Hiển thị danh sách trái cây có sẵn
    static void displayListFruit(ArrayList<Fruit> lf) {
        int countItem = 1;
        System.out.printf("%-10s%-20s%-20s%-15s%-10s\n", "Item", "Fruit name", "Origin", "Price","Quantity");
        for (Fruit fruit : lf) {
            if (fruit.getQuantity() > 0) {
                System.out.printf("%-10d%-20s%-20s%-12.0f$%-10s\n", countItem++, fruit.getFruitName(), fruit.getOrigin(), fruit.getPrice(),fruit.getQuantity());
            }
        }
    }

    // Lấy đối tượng Fruit mà người dùng chọn mua dựa vào số thứ tự item
    static Fruit getFruitByItem(ArrayList<Fruit> lf, int item) {
        int countItem = 1;
        for (Fruit fruit : lf) {
            if (fruit.getQuantity() > 0) {
                if (countItem == item) {
                    return fruit;
                }
                countItem++;
            }
        }
        return null;
    }

    // Hiển thị danh sách đơn hàng (theo giỏ hàng các trái cây)
    static void displayListOrder(ArrayList<Fruit> orderList) {
        double total = 0;
        System.out.printf("%15s%15s%15s%15s\n", "Product", "Quantity", "Price", "Amount");
        for (Fruit fruit : orderList) {
            double amount = fruit.getPrice() * fruit.getQuantity();
            System.out.printf("%15s%15d%15.0f$%15.0f$\n", fruit.getFruitName(), fruit.getQuantity(), fruit.getPrice(), amount);
            total += amount;
        }
        System.out.println("Total: " + total);
    }

    // Kiểm tra xem trái cây đã có trong giỏ hàng hay chưa (dựa theo fruitId)
    static boolean checkFruitExist(ArrayList<Fruit> cart, String fruitId) {
        for (Fruit fruit : cart) {
            if (fruit.getFruitId().equalsIgnoreCase(fruitId)) {
                return true;
            }
        }
        return false;
    }

    // Cập nhật số lượng của trái cây trong giỏ hàng
    static void updateFruitQuantity(ArrayList<Fruit> cart, String fruitId, int quantity) {
        for (Fruit fruit : cart) {
            if (fruit.getFruitId().equalsIgnoreCase(fruitId)) {
                fruit.setQuantity(fruit.getQuantity() + quantity);
                break;
            }
        }
    }
}
