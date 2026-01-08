package es.iesfranciscodelosrios.dam1.ed;

import java.util.Objects;

public final class Product {
    private final String code;
    private final String name;
    private int units;

    public Product(String code, String name, int units) {
        this.code = validateCode(code);
        this.name = validateName(name);
        if (units < 0) throw new IllegalArgumentException("units no puede ser negativo");
        this.units = units;
    }

    private static String validateCode(String code) {
        if (code == null || code.trim().isEmpty()) {
            throw new IllegalArgumentException("code no puede estar vacío");
        }
        return code.trim();
    }

    private static String validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("name no puede estar vacío");
        }
        return name.trim();
    }

    public String getCode() { return code; }
    public String getName() { return name; }
    public int getUnits() { return units; }

    public void addUnits(int delta) {
        if (delta <= 0) throw new IllegalArgumentException("delta debe ser > 0");
        units += delta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product other = (Product) o;
        return code.equals(other.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
