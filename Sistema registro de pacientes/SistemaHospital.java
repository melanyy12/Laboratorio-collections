import java.util.*;

public class SistemaHospital {

    private Queue<Paciente> cola = new LinkedList<>();
    private HashMap<Integer, Paciente> mapa = new HashMap<>();
    private HashSet<Integer> documentos = new HashSet<>();

    // registrar paciente
    public void registrarPaciente(Paciente p) {

        if (!documentos.contains(p.getDocumento())) {

            cola.add(p);
            mapa.put(p.getDocumento(), p);
            documentos.add(p.getDocumento());

        }
    }

    // buscar paciente
    public Paciente buscarPaciente(int documento) {
        return mapa.get(documento);
    }

    // mostrar pacientes
    public void mostrarPacientes() {

        for (Paciente p : cola) {
            System.out.println(p);
        }

    }
}