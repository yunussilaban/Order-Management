import model.*;
import enum1.*;
import util.OrderUtil;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static List<MenuItem> menu = createMenu();

    public static void main(String[] args) {
        System.out.println(" RESTAURANT ORDER SYSTEM ");
        System.out.println("============================\n");

        Customer customer = new Customer("Budi", "08123456789");
        Order order = new Order("ORD001", customer);

        showMenuList();
        processOrder(order);
    }

    private static List<MenuItem> createMenu() {
        List<MenuItem> menu = new ArrayList<>();
        menu.add(new MenuItem("1", "Nasi Goreng", FoodCategory.MAIN_DISH, 25000));
        menu.add(new MenuItem("2", "Es Teh", FoodCategory.DRINK, 8000));
        menu.add(new MenuItem("3", "Kentang Goreng", FoodCategory.SIDE, 15000));
        menu.add(new MenuItem("4", "Es Krim", FoodCategory.DESSERT, 12000));
        return menu;
    }

    private static void showMenuList() {
        System.out.println("📋 MENU RESTAURANT:");
        for (MenuItem item : menu) {
            System.out.println(item);
        }
        System.out.println();
    }

    private static void processOrder(Order order) {
        while (true) {
            System.out.print("\n1.Add Item | 2.View Order | 3.Pay | 4.Exit\nPilih: ");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1": addItem(order); break;
                case "2": viewOrder(order); break;
                case "3": payOrder(order); return;
                case "4": return;
                default: System.out.println(" Pilihan salah!");
            }
        }
    }

    private static void addItem(Order order) {
        showMenuList();
        System.out.print("Menu ID: ");
        String id = sc.nextLine().trim().toUpperCase();

        MenuItem item = menu.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (item == null) {
            System.out.println(" Menu tidak ditemukan!");
            return;
        }

        System.out.print("Quantity: ");
        int qty = OrderUtil.parseQuantity(sc.nextLine());

        order.addItem(item, qty);
        System.out.println(" Added: " + item.getName() + " x" + qty);
    }

    private static void viewOrder(Order order) {
        System.out.println("\n" + order);
        System.out.println(" Items:");
        for (Order.OrderItem item : order.getItems()) {
            System.out.println(item);
        }
        System.out.printf(" TOTAL: %s\n", OrderUtil.formatRupiah(order.calculateTotal()));
        System.out.println("Status: " + order.getStatus());
    }

    private static void payOrder(Order order) {
        viewOrder(order);
        order.setPaid();
        System.out.printf(" PAID! Total: %s\n", OrderUtil.formatRupiah(order.calculateTotal()));
        System.out.println("Tanggal order: " + order.getOrderDateStr());
    }
}