

/**
 * Representa un producto de la plataforma de ventas masivas.
 * Implementa Comparable para permitir ordenamiento por precio en TreeMap.
 */
public class Producto implements Comparable<Producto> {

    private String codigo;
    private String nombre;
    private double precio;
    private String categoria;
    private int stock;

    public Producto(String codigo, String nombre, double precio, String categoria, int stock) {
        this.codigo    = codigo;
        this.nombre    = nombre;
        this.precio    = precio;
        this.categoria = categoria;
        this.stock     = stock;
    }

    
    public String getCodigo() {
      return codigo;
    }

    public String getNombre() {
      return nombre;
    }

    public double getPrecio() {
      return precio;
    }

    public String getCategoria() {
      return categoria;
    }

    public int getStock() {
      return stock;
    }

    public void setPrecio(double precio) {
      this.precio = precio;
    }

    public void setStock(int stock) {
      this.stock = stock;
    }

    /**
     * Ordena por precio ascendente.
     * Si los precios son iguales, desempata por código para evitar colisiones en TreeMap.
     */
    @Override
    public int compareTo(Producto otro) {
        int cmp = Double.compare(this.precio, otro.precio);
        return cmp != 0 ? cmp : this.codigo.compareTo(otro.codigo);
    }


    @Override
    public String toString() {
      return "Producto [codigo=" + codigo + ", nombre=" + nombre + ", precio=" + precio + ", categoria=" + categoria
          + ", stock=" + stock + "]";
    }

    
}