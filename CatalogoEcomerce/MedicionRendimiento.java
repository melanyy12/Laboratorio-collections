import java.util.Random;

/**
 * Clase utilitaria para medir el rendimiento del CatalogoEcommerce
 * con diferentes volúmenes de datos (Fase 4).
 */
public class MedicionRendimiento {

    private static final Random RND = new Random(42);

    //Genera y llena un catálogo con N productos de prueba. 
    public static CatalogoEcommerce generarCatalogo(int n) {
        CatalogoEcommerce catalogo = new CatalogoEcommerce();
        for (int i = 0; i < n; i++) {
            String codigo = String.format("PROD-%06d", i);
            String nombre = "Producto " + i;
            double precio = 1.0 + RND.nextDouble() * 9999.0;
            catalogo.insertar(new Producto(codigo, nombre, precio, "Descripción " + i, RND.nextInt(500) + 1));
        }
        return catalogo;
    }

    // Ejecuta una prueba completa para N productos y retorna el resultado. */
    public static String ejecutarPrueba(int n) {
        // Inserción
        long t0 = System.nanoTime();
        CatalogoEcommerce catalogo = generarCatalogo(n);
        long tInsercion = System.nanoTime() - t0;

        // Búsqueda por código (elemento del medio)
        String codigoTarget = String.format("PROD-%06d", n / 2);
        t0 = System.nanoTime();
        Producto encontrado = catalogo.buscarPorCodigo(codigoTarget);
        long tBusqueda = System.nanoTime() - t0;

        // Orden por precio
        t0 = System.nanoTime();
        catalogo.ordenadosPorPrecio();
        long tOrden = System.nanoTime() - t0;

        // Rango de precio
        t0 = System.nanoTime();
        catalogo.porRangoDePrecio(100.0, 500.0);
        long tRango = System.nanoTime() - t0;

        Runtime rt = Runtime.getRuntime();
        rt.gc();
        long memoriaBytes = rt.totalMemory() - rt.freeMemory();

        return String.format(
            "N=%7d | Inserción: %6.1f ms | Búsqueda: %5.4f ms | " +
            "Orden: %5.1f ms | Rango: %5.1f ms | Mem: %.1f MB | Encontrado: %s",
            n,
            tInsercion / 1_000_000.0,
            tBusqueda  / 1_000_000.0,
            tOrden     / 1_000_000.0,
            tRango     / 1_000_000.0,
            memoriaBytes / (1024.0 * 1024.0),
            encontrado != null ? "Sí" : "No"
        );
    }
}