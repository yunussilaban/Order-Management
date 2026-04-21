package enum1;

public enum FoodCategory {
    MAIN_DISH(0.0), DRINK(0.05), DESSERT(0.1), SIDE(0.03);

    private final double discount;

    FoodCategory(double discount) {
        this.discount = discount;
    }

    public double getDiscount() {
        return discount;
    }
}