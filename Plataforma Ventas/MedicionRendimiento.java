

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Clase utilitaria para medir el rendimiento del CatalogoVentas
 * con diferentes volúmenes de datos (Fase 4).
 */
public class MedicionRendimiento {

    private static final String[] CATEGORIAS = {
        "Electronica", "Ropa", "Hogar", "Deportes", "Juguetes", "Alimentos"
    };
    private static final Random RND = new Random(42);

    /** Genera un lote de productos de prueba. */
    public static List<Producto> generarLote(int cantidad, int offset) {
        List<Producto> lote = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            String codigo    = "PROD-" + (offset + i);
            String nombre    = "Producto " + (offset + i);
            double precio    = 1.0 + RND.nextDouble() * 999.0;
            String categoria = CATEGORIAS[RND.nextInt(CATEGORIAS.length)];
            int    stock     = RND.nextInt(500) + 1;
            lote.add(new Producto(codigo, nombre, precio, categoria, stock));
        }
        return lote;
    }

    // Ejecuta una prueba completa para un tamaño N y retorna el resultado como String.
    public static String ejecutarPrueba(int n) {
        CatalogoVentas catalogo = new CatalogoVentas();
        List<Producto> lote = generarLote(n, 0);

        // Medir inserción en lote al inicio
        long t0 = System.nanoTime();
        int insertados = catalogo.insertarLoteAlInicio(lote);
        long tInsercion = System.nanoTime() - t0;

        // Medir búsqueda por código (último producto insertado)
        String codigoTarget = "PROD-" + (n - 1);
        t0 = System.nanoTime();
        Producto encontrado = catalogo.buscarPorCodigo(codigoTarget);
        long tBusqueda = System.nanoTime() - t0;

        // Medir orden por precio
        t0 = System.nanoTime();
        List<Producto> ordenados = catalogo.ordenadosPorPrecio();
        long tOrden = System.nanoTime() - t0;

        // Medir filtro por categoría
        t0 = System.nanoTime();
        List<Producto> filtrados = catalogo.filtrarPorCategoria("Electronica");
        long tFiltro = System.nanoTime() - t0;

        // Memoria aproximada
        Runtime rt = Runtime.getRuntime();
        rt.gc();
        long memoriaBytes = rt.totalMemory() - rt.freeMemory();

        return String.format(
            "N=%7d | Inserción: %6.1f ms | Búsqueda: %4.3f ms | Orden: %5.1f ms | " +
            "Filtro: %4.1f ms | Mem: %.1f MB | Insertados: %d | Filtrados: %d",
            n,
            tInsercion / 1_000_000.0,
            tBusqueda  / 1_000_000.0,
            tOrden     / 1_000_000.0,
            tFiltro    / 1_000_000.0,
            memoriaBytes / (1024.0 * 1024.0),
            insertados,
            filtrados.size()
        );
    }
}