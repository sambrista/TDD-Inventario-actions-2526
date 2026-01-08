package es.iesfranciscodelosrios.dam1.ed;

import java.util.Scanner;

public final class App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== TDD Inventario (solo Arrays) ===");
        int capacity = readInt(sc, "Capacidad del inventario (p.ej. 20): ", 1, 10_000);

        Inventory inv = new Inventory(capacity);

        int option;
        do {
            printMenu();
            option = readInt(sc, "Opción: ", 0, 9);

            try {
                switch (option) {
                    case 1 -> addProduct(sc, inv);
                    case 2 -> listProducts(inv);
                    case 3 -> findProduct(sc, inv);
                    case 4 -> addUnits(sc, inv);
                    case 5 -> System.out.println("Unidades totales: " + inv.totalUnits());
                    case 6 -> removeProduct(sc, inv);     // Puede estar pendiente
                    case 7 -> mostStocked(inv);           // Puede estar pendiente
                    case 0 -> System.out.println("Saliendo...");
                    default -> System.out.println("Opción no válida.");
                }
            } catch (UnsupportedOperationException e) {
                System.out.println("Funcionalidad pendiente: " + e.getMessage());
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getClass().getSimpleName() + " - " + e.getMessage());
            }

            System.out.println();
        } while (option != 0);

        sc.close();
    }

    private static void printMenu() {
        System.out.println("""
                --- Menú ---
                1) Añadir producto
                2) Listar productos
                3) Buscar por código
                4) Sumar unidades a un producto
                5) Unidades totales
                6) Eliminar producto por código (pendiente si no implementado)
                7) Producto con más stock (pendiente si no implementado)
                0) Salir
                """);
    }

    private static void addProduct(Scanner sc, Inventory inv) {
        String code = readNonEmpty(sc, "Código: ");
        String name = readNonEmpty(sc, "Nombre: ");
        int units = readInt(sc, "Unidades iniciales (>= 0): ", 0, Integer.MAX_VALUE);

        inv.add(new Product(code, name, units));
        System.out.println("Producto añadido correctamente.");
    }

    private static void listProducts(Inventory inv) {
        if (inv.size() == 0) {
            System.out.println("Inventario vacío.");
            return;
        }

        System.out.println("Listado de productos:");
        Product[] items = inv.items(); // requiere el método items() en Inventory
        for (int i = 0; i < items.length; i++) {
            Product p = items[i];
            System.out.printf("%d) %s - %s - unidades=%d%n",
                    (i + 1), p.getCode(), p.getName(), p.getUnits());
        }
    }

    private static void findProduct(Scanner sc, Inventory inv) {
        String code = readNonEmpty(sc, "Código a buscar: ");
        Product p = inv.findByCode(code);

        if (p == null) {
            System.out.println("No existe producto con ese código.");
        } else {
            System.out.printf("Encontrado: %s - %s - unidades=%d%n",
                    p.getCode(), p.getName(), p.getUnits());
        }
    }

    private static void addUnits(Scanner sc, Inventory inv) {
        String code = readNonEmpty(sc, "Código: ");
        int delta = readInt(sc, "Unidades a sumar (> 0): ", 1, Integer.MAX_VALUE);

        inv.addUnits(code, delta);
        System.out.println("Unidades actualizadas. Ahora: " + inv.findByCode(code).getUnits());
    }

    private static void removeProduct(Scanner sc, Inventory inv) {
        String code = readNonEmpty(sc, "Código a eliminar: ");
        boolean removed = inv.removeByCode(code);
        System.out.println(removed ? "Producto eliminado." : "No existía ese producto.");
    }

    private static void mostStocked(Inventory inv) {
        Product p = inv.mostStocked();
        if (p == null) {
            System.out.println("Inventario vacío (no hay producto con más stock).");
        } else {
            System.out.printf("Más stock: %s - %s - unidades=%d%n",
                    p.getCode(), p.getName(), p.getUnits());
        }
    }

    // -------------------------
    // Helpers de lectura consola
    // -------------------------

    private static int readInt(Scanner sc, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine();
            try {
                int value = Integer.parseInt(line.trim());
                if (value < min || value > max) {
                    System.out.println("Valor fuera de rango [" + min + ", " + max + "].");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Introduce un número entero válido.");
            }
        }
    }

    private static String readNonEmpty(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine();
            if (line != null && !line.trim().isEmpty()) {
                return line.trim();
            }
            System.out.println("El valor no puede estar vacío.");
        }
    }
}
