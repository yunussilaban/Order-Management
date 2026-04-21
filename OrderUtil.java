package util;

public class OrderUtil {
    public static double parsePrice(String priceStr) {
        return Double.parseDouble(priceStr.trim());
    }

    public static int parseQuantity(String qtyStr) {
        return Integer.parseInt(qtyStr.trim());
    }

    public static String formatRupiah(double amount) {
        return String.format("Rp %,d", (int)amount);
    }
}