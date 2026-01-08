package es.iesfranciscodelosrios.dam1.ed;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

    @Test
    void add_increasesSize_and_findByCode_returnsProduct() {
        Inventory inv = new Inventory(3);

        inv.add(new Product("P1", "Teclado", 10));
        inv.add(new Product("P2", "Raton", 5));

        assertEquals(2, inv.size());
        assertNotNull(inv.findByCode("P1"));
        assertEquals("Teclado", inv.findByCode("P1").getName());
        assertNull(inv.findByCode("NOEXISTE"));
    }

    @Test
    void add_rejectsDuplicateCode() {
        Inventory inv = new Inventory(3);
        inv.add(new Product("P1", "Teclado", 10));

        assertThrows(IllegalArgumentException.class,
                () -> inv.add(new Product("P1", "Otro", 1)));
    }

    @Test
    void totalUnits_sumsAllUnits() {
        Inventory inv = new Inventory(5);
        inv.add(new Product("P1", "A", 2));
        inv.add(new Product("P2", "B", 3));
        inv.add(new Product("P3", "C", 10));

        assertEquals(15, inv.totalUnits());
    }

    @Test
    void addUnits_increasesUnitsOfExistingProduct() {
        Inventory inv = new Inventory(2);
        inv.add(new Product("P1", "A", 2));

        inv.addUnits("P1", 5);

        assertEquals(7, inv.findByCode("P1").getUnits());
    }

    @Test
    void addUnits_throwsIfProductDoesNotExist() {
        Inventory inv = new Inventory(2);

        assertThrows(IllegalArgumentException.class,
                () -> inv.addUnits("P9", 1));
    }

    // =========================
    // PENDIENTES (activar luego)
    // =========================

    @Disabled("Pendiente: implementar Inventory.removeByCode")
    @Test
    void removeByCode_removesAndCompactsArray() {
    }

    @Disabled("Pendiente: implementar Inventory.mostStocked")
    @Test
    void mostStocked_returnsProductWithHighestUnits_orNullIfEmpty() {
    }
}