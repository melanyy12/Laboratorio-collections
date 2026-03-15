public class Prueba {

    public static void main(String[] args) {

        // ===== DEMOSTRACIÓN DEL SISTEMA =====
        SistemaHospital sistema = new SistemaHospital();

        sistema.registrarPaciente(new Paciente(1, "Ana"));
        sistema.registrarPaciente(new Paciente(2, "Luis"));
        sistema.registrarPaciente(new Paciente(3, "Pedro"));

        System.out.println("Pacientes registrados:");
        sistema.mostrarPacientes();

        System.out.println("\nBuscar paciente con documento 2:");
        System.out.println(sistema.buscarPaciente(2));



        // ===== PRUEBAS DE RENDIMIENTO =====

        int[] tamaños = {100, 1000, 10000, 100000};

        for (int n : tamaños) {

            SistemaHospital sistemaPrueba = new SistemaHospital();

            long inicio = System.nanoTime();

            for (int i = 0; i < n; i++) {

                sistemaPrueba.registrarPaciente(
                        new Paciente(i, "Paciente" + i));

            }

            long fin = System.nanoTime();

            long tiempo = fin - inicio;

            long memoria = Runtime.getRuntime().totalMemory()
                    - Runtime.getRuntime().freeMemory();

            System.out.println("\nDatos: " + n);
            System.out.println("Tiempo: " + tiempo + " ns");
            System.out.println("Memoria: " + memoria + " bytes");
            System.out.println("----------------------");
        }
    }
}