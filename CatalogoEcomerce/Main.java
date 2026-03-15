/**
 * Clase principal — Escenario 4: Catálogo de Productos E-commerce
 *
 * Demuestra todas las operaciones del CatalogoEcommerce y ejecuta
 * las mediciones de rendimiento de la Fase 4.
 *
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("=".repeat(70));
        System.out.println("  ESCENARIO 4 — Catálogo de Productos E-commerce");
        System.out.println("=".repeat(70));

        CatalogoEcommerce catalogo = new CatalogoEcommerce();

        // 1. Inserción de productos 
        System.out.println("\n Insertando productos...");
        catalogo.insertar(new Producto("E001", "Laptop UltraBook",  1299.99, "Intel i7, 16GB RAM", 10));
        catalogo.insertar(new Producto("E002", "Auriculares BT",    59.99,     "Noise-Cancelling", 150));
        catalogo.insertar(new Producto("E003", "Monitor 4K 27\"",   499.00,          "IPS, 144Hz",  25));
        catalogo.insertar(new Producto("E004", "Teclado Mecánico",  89.99,     "RGB, Switch Blue",  60));
        catalogo.insertar(new Producto("E005", "Mouse Gamer",       45.00,            "12000 DPI",  90));
        catalogo.insertar(new Producto("E006", "Webcam Full HD",    79.99,         "1080p, 60fps",  40));
        catalogo.insertar(new Producto("E007", "SSD 1TB NVMe",     129.99,   "7000 MB/s lectura",  35));
        // Intento duplicado
        boolean dup = catalogo.insertar(new Producto("E001", "Duplicado", 0, "", 0));
        System.out.println("  Insertar duplicado 'E001': " + (dup ? "Insertado" : "Rechazado (duplicado)"));
        System.out.println("  Total en catálogo: " + catalogo.total());

        // 2. Buscar por código 
        System.out.println("\n Búsqueda por código (O(1)):");
        Producto p = catalogo.buscarPorCodigo("E003");
        System.out.println("  buscarPorCodigo(\"E003\") → " + (p != null ? p : "no encontrado"));
        p = catalogo.buscarPorCodigo("ZZZ");
        System.out.println("  buscarPorCodigo(\"ZZZ\") → " + (p != null ? p : "no encontrado"));

        // 3. Ordenados por precio
        System.out.println("\n Productos ordenados por precio ascendente:");
        for (Producto prod : catalogo.ordenadosPorPrecio()) {
            System.out.println("  " + prod);
        }

        // 4. Rango de precio 
        System.out.println("\n Productos entre $50 y $130:");
        for (Producto prod : catalogo.porRangoDePrecio(50.0, 130.0)) {
            System.out.println("  " + prod);
        }

        // 5. Eliminar producto 
        System.out.println("\n Eliminar producto 'E002':");
        Producto eliminado = catalogo.eliminar("E002");
        System.out.println("  Eliminado: " + (eliminado != null ? eliminado : "no encontrado"));
        System.out.println("  Total tras eliminar: " + catalogo.total());

        // 6. Mediciones de rendimiento (Fase 4)
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