# TDD-Inventario (Maven + JUnit5 + TDD)

Proyecto Java (Maven) diseñado para practicar TDD con un caso simple de inventario.
Restricciones didácticas:
- Sin ficheros.
- Sin colecciones (List, Map, Set). Solo arrays.
- Código deliberadamente “a medio hacer”: parte en verde y parte pendiente.

## Estructura
src/main/java/es/profe/tdd/inventario
- Product.java        (dominio: producto)
- Inventory.java      (dominio: inventario con array fijo)
- App.java            (menú por consola para probar el dominio)

src/test/java/es/profe/tdd/inventario
- ProductTest.java
- InventoryTest.java

## Requisitos TDD (test-first)
La idea es trabajar por iteraciones:
1) Activar un test (o escribirlo) → falla (ROJO)
2) Implementar lo mínimo para pasar → (VERDE)
3) Refactorizar si procede manteniendo los tests en verde

En este proyecto, algunos tests de Inventory están marcados con `@Disabled` para que el alumnado
active el siguiente requisito cuando toque.

## Requisitos implementados (en verde)

### Product (cubierto por ProductTest)
1. Normalización: `code` y `name` se recortan (`trim`) al construir.
2. Validación: `code` no puede ser null, vacío o en blanco.
3. Validación: `name` no puede ser null, vacío o en blanco.
4. Validación: `units` inicial no puede ser negativo (0 es válido).
5. Regla: `addUnits(delta)` incrementa unidades si `delta > 0`.
6. Regla: `addUnits(delta)` lanza excepción si `delta <= 0`.
7. Identidad: `equals()` y `hashCode()` dependen solo de `code`.

### Inventory (cubierto por InventoryTest)
1. Añadir producto (`add`) incrementa el tamaño.
2. No se permiten códigos duplicados.
3. Búsqueda por código (`findByCode`) devuelve el producto o `null`.
4. Cálculo de unidades totales (`totalUnits`) suma todas las unidades.
5. Incremento de unidades por código (`addUnits(code, delta)`).

## Requisitos pendientes (por implementar con TDD)
Estos requisitos tienen tests ya escritos pero desactivados (`@Disabled`) o métodos que lanzan `UnsupportedOperationException`:

1) `removeByCode(code)`:
    - Elimina un producto y compacta el array (sin huecos).
    - Devuelve `true` si eliminó, `false` si no existía.

2) `mostStocked()`:
    - Devuelve el producto con más unidades.
    - Si el inventario está vacío, devuelve `null`.

## Ejecutar tests
```bash
mvn test
