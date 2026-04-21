package model;

import enum1.FoodCategory;
import java.util.Objects;

public final class MenuItem {
    private final String id;
    private final String name;
    private final FoodCategory category;
    private final double price;

    public MenuItem(String id, String name, FoodCategory category, double price) {
        this.id = id.trim().toUpperCase();
        this.name = name.trim();
        this.category = category;
        this.price = price;
    }

    public double getPrice() { return price; }
    public String getId() { return id; }
    public String getName() { return name; }
    public FoodCategory getCategory() { return category; }

    @Override
    public String toString() {
        return String.format("🍽️ %s [%s] Rp%.0f", name, category, price);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof MenuItem)) return false;
        return Objects.equals(id, ((MenuItem)obj).id);
    }
}