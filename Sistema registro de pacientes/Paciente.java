public class Paciente {

    private int documento;
    private String nombre;

    public Paciente(int documento, String nombre) {
        this.documento = documento;
        this.nombre = nombre;
    }

    public int getDocumento() {
        return documento;
    }

    public String toString() {
        return "Doc: " + documento + " Nombre: " + nombre;
    }
}