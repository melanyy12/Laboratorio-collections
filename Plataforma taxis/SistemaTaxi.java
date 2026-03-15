import java.util.*;

public class SistemaTaxi {

    private Queue<Solicitud> colaSolicitudes;
    private HashMap<Integer, Solicitud> mapaSolicitudes;

    public SistemaTaxi() {
        colaSolicitudes = new LinkedList<>();
        mapaSolicitudes = new HashMap<>();
    }

    // Registrar solicitud
    public void registrarSolicitud(Solicitud s) {
        colaSolicitudes.add(s);
        mapaSolicitudes.put(s.getId(), s);
    }

    // Atender solicitud más antigua
    public Solicitud atenderSolicitud() {
        Solicitud s = colaSolicitudes.poll();

        if (s != null) {
            mapaSolicitudes.remove(s.getId());
        }

        return s;
    }

    // Cancelar solicitud
    public void cancelarSolicitud(int id) {
        Solicitud s = mapaSolicitudes.get(id);

        if (s != null) {
            colaSolicitudes.remove(s);
            mapaSolicitudes.remove(id);
        }
    }

    // Mostrar pendientes
    public void mostrarSolicitudes() {
        for (Solicitud s : colaSolicitudes) {
            System.out.println(s);
        }
    }
}