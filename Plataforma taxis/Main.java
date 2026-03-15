public class Main {

    public static void main(String[] args) {

        // ===== DEMOSTRACIÓN DEL SISTEMA =====
        SistemaTaxi sistema = new SistemaTaxi();

        sistema.registrarSolicitud(new Solicitud(1,"Ana","Centro","Norte"));
        sistema.registrarSolicitud(new Solicitud(2,"Luis","Sur","Centro"));
        sistema.registrarSolicitud(new Solicitud(3,"Pedro","Este","Oeste"));

        System.out.println("Solicitudes pendientes:");
        sistema.mostrarSolicitudes();

        System.out.println("\nAtendiendo solicitud:");
        System.out.println(sistema.atenderSolicitud());

        System.out.println("\nSolicitudes restantes:");
        sistema.mostrarSolicitudes();

        System.out.println("\nCancelando solicitud 3");
        sistema.cancelarSolicitud(3);

        System.out.println("\nSolicitudes pendientes:");
        sistema.mostrarSolicitudes();


        // ===== PRUEBAS DE RENDIMIENTO =====
        int[] tamaños = {100, 1000, 10000, 100000};

        for (int n : tamaños) {

            SistemaTaxi sistemaPrueba = new SistemaTaxi();

            long inicio = System.nanoTime();

            for (int i = 0; i < n; i++) {
                sistemaPrueba.registrarSolicitud(
                        new Solicitud(i, "Usuario" + i, "Origen", "Destino"));
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