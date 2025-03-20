import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Fruit> lf = new ArrayList<>();
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