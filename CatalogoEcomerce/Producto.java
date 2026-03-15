/**
 * Representa un producto del catálogo de e-commerce.
 * Implementa Comparable para ordenamiento por precio en TreeMap.
 */
public class Producto implements Comparable<Producto> {

    private String codigo;
    private String nombre;
    private String descripcion;
    private double precio;
    private int    stock;

    public Producto(String codigo, String nombre, String descripcion, double precio, int stock) {
        this.codigo      = codigo;
        this.nombre      = nombre;
        this.descripcion = descripcion;
        this.precio      = precio;
        this.stock       = stock;
    }

   

    public String getCodigo() {
      return codigo;
    }

    public String getNombre() {
      return nombre;
    }

    public String getDescripcion() {
      return descripcion;
    }

    public double getPrecio() {
      return precio;
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
     * Desempata por código para evitar pérdidas en TreeMap (claves únicas).
     */
    @Override
    public int compareTo(Producto otro) {
        int cmp = Double.compare(this.precio, otro.precio);
        return cmp != 0 ? cmp : this.codigo.compareTo(otro.codigo);
    }



    @Override
    public String toString() {
      return "Producto [codigo=" + codigo + ", nombre=" + nombre + ", descripcion=" + descripcion + ", precio=" + precio
          + ", stock=" + stock + "]";
    }


}