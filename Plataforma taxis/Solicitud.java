public class Solicitud {

    private int id;
    private String usuario;
    private String origen;
    private String destino;

    public Solicitud(int id, String usuario, String origen, String destino) {
        this.id = id;
        this.usuario = usuario;
        this.origen = origen;
        this.destino = destino;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        return "ID: " + id + " Usuario: " + usuario +
               " Origen: " + origen + " Destino: " + destino;
    }
}