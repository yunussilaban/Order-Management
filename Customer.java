package model;

public class Customer {
    private String name;
    private String phone;

    public Customer(String name, String phone) {
        this.name = name.trim().toUpperCase();
        this.phone = validatePhone(phone);
    }

    private String validatePhone(String phone) {
        String clean = phone.trim().replaceAll("[^0-9]", "");
        if (clean.length() < 10) {
            throw new IllegalArgumentException("Phone minimal 10 digit");
        }
        return clean;
    }

    // Getters
    public String getName() { return name; }
    public String getPhone() { return phone; }

    @Override
    public String toString() {
        return String.format("👤 %s (%s)", name, phone);
    }
}