package com.example;

public class Manufacturer {
    private final String name;
    private final String country;
    private final String logoPath;
    private final int employeeCount;
    private final String description;

    public Manufacturer() {
        this.name = "Dell Technologies";
        this.country = "США";
        this.logoPath = "images/logo.png";
        this.employeeCount = 165000;
        this.description = "Dell Technologies — американская корпорация, один" +
                "из крупнейших производителей компьютеров и серверов. Основана в 1984 году Майклом Деллом.";
    }

    public String getName() { return name; }
    public String getCountry() { return country; }
    public String getLogoPath() { return logoPath; }
    public int getEmployeeCount() { return employeeCount; }
    public String getDescription() { return description; }
}
