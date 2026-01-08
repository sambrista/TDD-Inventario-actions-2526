package es.iesfranciscodelosrios.dam1.ed;

public class Inventory {
    final Product[] items;
    private int size;

    public Inventory(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("capacity debe ser > 0");
        this.items = new Product[capacity];
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public void add(Product product) {
        if (product == null) throw new IllegalArgumentException("product no puede ser null");
        if (size == items.length) throw new IllegalStateException("Inventario lleno");

        // Regla simple: no duplicar códigos
        if (findByCode(product.getCode()) != null) {
            throw new IllegalArgumentException("Ya existe un producto con code=" + product.getCode());
        }

        items[size] = product;
        size++;
    }

    public Product findByCode(String code) {
        if (code == null) return null;
        String c = code.trim();
        for (int i = 0; i < size; i++) {
            if (items[i].getCode().equals(c)) {
                return items[i];
            }
        }
        return null;
    }

    public int totalUnits() {
        int total = 0;
        for (int i = 0; i < size; i++) {
            total += items[i].getUnits();
        }
        return total;
    }

    public void addUnits(String code, int delta) {
        Product p = findByCode(code);
        if (p == null) throw new IllegalArgumentException("No existe producto con code=" + code);
        p.addUnits(delta);
        // Probando
    }

    // ======================
    // REQUISITOS PENDIENTES
    // ======================

    /** Pendiente: eliminar por code y compactar el array (mantener orden o no, a elegir). */
    public boolean removeByCode(String code) {
        throw new UnsupportedOperationException("Pendiente: implementar removeByCode()");
    }

    /** Pendiente: devolver el producto con más unidades; si vacío, null. */
    public Product mostStocked() {
        throw new UnsupportedOperationException("Pendiente: implementar mostStocked()");
    }

    public Product[] items() {
        Product[] copy = new Product[items.length]; // misma capacidad (puede incluir nulls)
        for (int i = 0; i < items.length; i++) {
            copy[i] = items[i];
        }
        return copy;
    }
}
