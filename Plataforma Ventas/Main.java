

import java.util.List;

/**
 * Clase principal — Escenario 2: Plataforma de Ventas Masivas
 *
 * Demuestra todas las operaciones del CatalogoVentas y ejecuta
 * las mediciones de rendimiento de la Fase 4.
 *
*/
public class Main {

    public static void main(String[] args) {

        System.out.println("=".repeat(70));
        System.out.println("  ESCENARIO 2 — Plataforma de Ventas Masivas");
        System.out.println("=".repeat(70));

        CatalogoVentas catalogo = new CatalogoVentas();

        //1. Inserción individual 
        System.out.println("\n Insertando productos de prueba...");
        catalogo.insertar(new Producto("E001", "Laptop Pro",       1299.99, "Electronica", 15));
        catalogo.insertar(new Producto("R001", "Camiseta Sport",     29.99, "Ropa",        200));
        catalogo.insertar(new Producto("H001", "Cafetera Premium",   89.99, "Hogar",       50));
        catalogo.insertar(new Producto("D001", "Tenis Running",     120.00, "Deportes",    80));
        catalogo.insertar(new Producto("E002", "Auriculares BT",     59.99, "Electronica", 100));
        catalogo.insertar(new Producto("J001", "LEGO Classic",       45.00, "Juguetes",    60));
        System.out.println("  Total en catálogo: " + catalogo.total());

        //2. Buscar por código 
        System.out.println("\n Búsqueda por código (O(1)):");
        Producto p = catalogo.buscarPorCodigo("E001");
        System.out.println("  buscarPorCodigo(\"E001\") → " + (p != null ? p : "no encontrado"));
        p = catalogo.buscarPorCodigo("XXX");
        System.out.println("  buscarPorCodigo(\"XXX\") → " + (p != null ? p : "no encontrado"));

        //3. Ordenados por precio
        System.out.println("\n Productos ordenados por precio (TreeMap O(n)):");
        for (Producto prod : catalogo.ordenadosPorPrecio()) {
            System.out.println("  " + prod);
        }

        //4. Filtrar por categoría
        System.out.println("\n Filtrar por categoría 'Electronica':");
        for (Producto prod : catalogo.filtrarPorCategoria("Electronica")) {
            System.out.println("  " + prod);
        }

        //5. Inserción al inicio (lote) 
        System.out.println("\n Inserción de lote al inicio de la lista:");
        List<Producto> lote = List.of(
            new Producto("N001", "Nuevo Smartphone", 699.00, "Electronica", 30),
            new Producto("N002", "Nuevo Monitor 4K", 499.00, "Electronica", 20)
        );
        int ok = catalogo.insertarLoteAlInicio(lote);
        System.out.println("  Insertados: " + ok);
        System.out.println("  Primeros 3 en orden de inserción:");
        catalogo.todosEnOrdenDeInsercion().stream().limit(3)
                .forEach(prod -> System.out.println("  " + prod));

        //6. Categorías disponibles.
        System.out.println("\n Categorías disponibles: " + catalogo.categorias());

        //7. Mediciones de rendimiento (Fase 4) 
        System.out.println("\n" + "=".repeat(70));
        System.out.println("  FASE 4 — Medición de Rendimiento");
        System.out.println("=".repeat(70));
        System.out.println();

        int[] tamanos = { 100, 1_000, 10_000, 100_000 };
        for (int n : tamanos) {
            System.out.println(MedicionRendimiento.ejecutarPrueba(n));
        }

        System.out.println("\n" + "=".repeat(70));
        System.out.println("  Fin de la demostración.");
        System.out.println("=".repeat(70));
    }
}