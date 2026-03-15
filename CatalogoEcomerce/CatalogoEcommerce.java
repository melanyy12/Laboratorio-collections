import java.util.*;

public class CatalogoEcommerce {

    // Búsqueda O(1) por código de producto
    private final HashMap<String, Producto> porCodigo;

    // Ordenado por precio ascendente; varios productos pueden compartir precio
    private final TreeMap<Double, List<Producto>> porPrecio;

    public CatalogoEcommerce() {
        this.porCodigo = new HashMap<>();
        this.porPrecio = new TreeMap<>();
    }

   

    /**
     * Inserta un nuevo producto en el catálogo.
     * O(1) en HashMap + O(log n) en TreeMap.
     * @return true si se insertó, false si el código ya existe.
     */
    public boolean insertar(Producto p) {
        if (porCodigo.containsKey(p.getCodigo())) {
            return false; // Duplicado
        }
        porCodigo.put(p.getCodigo(), p);
        porPrecio.computeIfAbsent(p.getPrecio(), k -> new ArrayList<>()).add(p);
        return true;
    }

    
    /**
     * Busca un producto por su código único.
     * O(1) promedio.
     */
    public Producto buscarPorCodigo(String codigo) {
        return porCodigo.get(codigo);
    }

  
    /**
     * Retorna todos los productos ordenados por precio ascendente.
     * O(n) — recorre el TreeMap en orden.
     */
    public List<Producto> ordenadosPorPrecio() {
        List<Producto> resultado = new ArrayList<>();
        for (List<Producto> grupo : porPrecio.values()) {
            resultado.addAll(grupo);
        }
        return resultado;
    }

    /**
     * Retorna productos cuyo precio está en el rango [min, max].
     * O(k log n) donde k = grupos de precio en el rango.
     */
    public List<Producto> porRangoDePrecio(double min, double max) {
        List<Producto> resultado = new ArrayList<>();
        for (List<Producto> grupo : porPrecio.subMap(min, true, max, true).values()) {
            resultado.addAll(grupo);
        }
        return resultado;
    }

    

    /**
     * Elimina un producto por código.
     * O(1) en HashMap + O(log n) en TreeMap.
     * @return el producto eliminado, o null si no existía.
     */
    public Producto eliminar(String codigo) {
        Producto p = porCodigo.remove(codigo);
        if (p != null) {
            List<Producto> grupo = porPrecio.get(p.getPrecio());
            if (grupo != null) {
                grupo.remove(p);
                if (grupo.isEmpty()) {
                    porPrecio.remove(p.getPrecio());
                }
            }
        }
        return p;
    }

    // Retorna el total de productos en el catálogo. 
    public int total() {
        return porCodigo.size();
    }
}