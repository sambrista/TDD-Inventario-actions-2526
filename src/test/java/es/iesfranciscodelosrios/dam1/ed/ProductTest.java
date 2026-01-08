package es.iesfranciscodelosrios.dam1.ed;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void constructor_trimsCodeAndName() {
        Product p = new Product("  P1  ", "  Teclado  ", 10);

        assertEquals("P1", p.getCode());
        assertEquals("Teclado", p.getName());
        assertEquals(10, p.getUnits());
    }

    @Test
    void constructor_rejectsNullOrBlankCode() {
        assertThrows(IllegalArgumentException.class, () -> new Product(null, "A", 1));
        assertThrows(IllegalArgumentException.class, () -> new Product("   ", "A", 1));
    }

    @Test
    void constructor_rejectsNullOrBlankName() {
        assertThrows(IllegalArgumentException.class, () -> new Product("P1", null, 1));
        assertThrows(IllegalArgumentException.class, () -> new Product("P1", "   ", 1));
    }

    @Test
    void constructor_rejectsNegativeUnits() {
        assertThrows(IllegalArgumentException.class, () -> new Product("P1", "A", -1));
    }

    @Test
    void addUnits_increasesUnits() {
        Product p = new Product("P1", "A", 2);

        p.addUnits(5);

        assertEquals(7, p.getUnits());
    }

    @Test
    void addUnits_rejectsZeroOrNegativeDelta() {
        Product p = new Product("P1", "A", 2);

        assertThrows(IllegalArgumentException.class, () -> p.addUnits(0));
        assertThrows(IllegalArgumentException.class, () -> p.addUnits(-3));
    }

    @Test
    void equalsAndHashCode_areBasedOnCodeOnly() {
        Product p1 = new Product("P1", "Teclado", 10);
        Product p2 = new Product("P1", "Otro nombre", 0);
        Product p3 = new Product("P2", "Teclado", 10);

        assertEquals(p1, p2);
        assertEquals(p1.hashCode(), p2.hashCode());

        assertNotEquals(p1, p3);
    }
}