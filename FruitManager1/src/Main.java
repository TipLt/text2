import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Fruit> lf = new ArrayList<>();
        lf.add(new Fruit("A1", "Orange", 2.3, 20, "VN"));
        lf.add(new Fruit("A2", "Apple", 2.1, 10, "USA"));
        lf.add(new Fruit("A3", "Grape", 2.5, 15, "France"));
        ArrayList<Order> orders = new ArrayList<>();
        while (true) {
            int choice = Manager.menu();
            switch (choice) {
                case 1:
                    Manager.createFruit(lf);
                    break;
                case 2:
                    Manager.viewOrder(orders);
                    break;
                case 3:
                    Manager.shopping(lf, orders);
                    break;
                case 4:
                    return;
            }
        }
    }

}