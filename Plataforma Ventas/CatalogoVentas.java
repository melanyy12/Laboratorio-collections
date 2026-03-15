
import java.util.*;

public class CatalogoVentas {

    // Búsqueda O(1) por código + conserva orden de inserción
    private final LinkedHashMap<String, Producto> porCodigo;

    // Ordenado automáticamente por precio (Comparable de Producto)
    private final TreeMap<Producto, Producto> porPrecio;

    // Índice por categoría para filtros O(1) de acceso + O(k) de recorrido
    private final HashMap<String, List<Producto>> porCategoria;

    public CatalogoVentas() {
        this.porCodigo    = new LinkedHashMap<>();
        this.porPrecio    = new TreeMap<>();
        this.porCategoria = new HashMap<>();
    }

     /**
     * Inserta un producto al final del registro (orden estándar).
     * O(1) en HashMap, O(log n) en TreeMap.
     */
    public boolean insertar(Producto p) {
        if (porCodigo.containsKey(p.getCodigo())) {
            return false; 
        }
        porCodigo.put(p.getCodigo(), p);
        porPrecio.put(p, p);
        porCategoria.computeIfAbsent(p.getCategoria(), k -> new ArrayList<>()).add(p);
        return true;
    }

    /**
     * Inserta un producto al inicio de la lista visible.
     * LinkedHashMap no tiene insertFirst nativo; se reconstruye en O(n).
     * Para miles de inserciones por hora, se recomienda insertarLote().
     */
    public boolean insertarAlInicio(Producto p) {
        if (porCodigo.containsKey(p.getCodigo())) {
            return false;
        }
        LinkedHashMap<String, Producto> nuevo = new LinkedHashMap<>();
        nuevo.put(p.getCodigo(), p);
        nuevo.putAll(porCodigo);
        porCodigo.clear();
        porCodigo.putAll(nuevo);

        porPrecio.put(p, p);
        porCategoria.computeIfAbsent(p.getCategoria(), k -> new ArrayList<>()).add(p);
        return true;
    }

    /**
     * Inserta una lista de productos al inicio de forma eficiente (una sola reconstrucción).
     * Óptimo para las "miles de inserciones por hora" del enunciado.
     * O(m + n) donde m = nuevos, n = existentes.
     */
    public int insertarLoteAlInicio(List<Producto> lote) {
        int insertados = 0;
        LinkedHashMap<String, Producto> nuevo = new LinkedHashMap<>();

        for (Producto p : lote) {
            if (!porCodigo.containsKey(p.getCodigo()) && !nuevo.containsKey(p.getCodigo())) {
                nuevo.put(p.getCodigo(), p);
                porPrecio.put(p, p);
                porCategoria.computeIfAbsent(p.getCategoria(), k -> new ArrayList<>()).add(p);
                insertados++;
            }
        }
        nuevo.putAll(porCodigo);
        porCodigo.clear();
        porCodigo.putAll(nuevo);
        return insertados;
    }

   

    // Busca un producto por código. O(1). 
    public Producto buscarPorCodigo(String codigo) {
        return porCodigo.get(codigo);
    }

  

    // Retorna todos los productos ordenados por precio ascendente. O(n). 
    public List<Producto> ordenadosPorPrecio() {
        return new ArrayList<>(porPrecio.keySet());
    }

    // Retorna productos de una categoría específica. O(1) acceso + O(k) copia. 
    public List<Producto> filtrarPorCategoria(String categoria) {
        return new ArrayList<>(porCategoria.getOrDefault(categoria, Collections.emptyList()));
    }

    // Retorna todos los productos en orden de inserción. O(n). 
    public List<Producto> todosEnOrdenDeInsercion() {
        return new ArrayList<>(porCodigo.values());
    }

    // Retorna el total de productos en el catálogo. 
    public int total() {
        return porCodigo.size();
    }

    // Retorna las categorías disponibles. 
    public Set<String> categorias() {
        return porCategoria.keySet();
    }
}