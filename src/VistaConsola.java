import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class VistaConsola {
    private static final String LINEA = "------------------------------------------------------------";

    private final Scanner scanner;

    public VistaConsola() {
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        System.out.println();
        System.out.println("=== DEFENSA DE QTZ2 - Misión Quetzal-2 ===");
        System.out.println("1. Listar módulos");
        System.out.println("2. Buscar módulo (por ID o nombre)");
        System.out.println("3. Catálogo ordenado por costo de construcción");
        System.out.println("4. Avanzar un ciclo");
        System.out.println("5. Resumen de comunicaciones");
        System.out.println("0. Salir");
    }

    public void mostrarMenuBusqueda() {
        System.out.println();
        System.out.println("Buscar por:");
        System.out.println("1. ID");
        System.out.println("2. Nombre");
    }

    public int leerOpcion(int minimo, int maximo) {
        while (true) {
            System.out.print("Seleccione una opción: ");
            String linea = scanner.nextLine().trim();
            try {
                int opcion = Integer.parseInt(linea);
                if (opcion >= minimo && opcion <= maximo) {
                    return opcion;
                }
            } catch (NumberFormatException e) {
                // se informa abajo, igual que un número fuera de rango
            }
            mostrarError("Opción inválida. Ingrese un número entre " + minimo + " y " + maximo + ".");
        }
    }

    public String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarError(String mensaje) {
        System.out.println("[ERROR] " + mensaje);
    }

    public void mostrarModulos(List<Modulo> modulos) {
        if (modulos.isEmpty()) {
            System.out.println("No se encontraron módulos.");
            return;
        }
        System.out.println();
        System.out.println("Módulos (" + modulos.size() + "):");
        for (Modulo m : modulos) {
            System.out.println(LINEA);
            System.out.println(m);
        }
        System.out.println(LINEA);
    }

    public void mostrarCatalogo(List<Modulo> ordenados) {
        System.out.println();
        System.out.println("Catálogo ordenado por costo de construcción:");
        System.out.println(LINEA);
        int n = 1;
        for (Modulo m : ordenados) {
            System.out.println(String.format(Locale.US, "%2d. %-28s %-10s Costo: %.2f",
                    n++, m.getNombre(), m.getId(), m.getCosto()));
        }
        System.out.println(LINEA);
    }

    public void mostrarResultadosCiclo(List<Modulo> modulos, List<Respuesta<?>> respuestas,
                                       RecursosMision recursos) {
        if (modulos.size() != respuestas.size()) {
            throw new IllegalArgumentException("Debe haber una respuesta por cada módulo.");
        }
        System.out.println();
        System.out.println("Resultado del ciclo:");
        System.out.println(LINEA);
        for (int i = 0; i < modulos.size(); i++) {
            Modulo m = modulos.get(i);
            Respuesta<?> r = respuestas.get(i);
            System.out.println(String.format("%2d. %-28s [%s] %s",
                    i + 1, m.getNombre(), r.exito() ? "OPERÓ" : "NO OPERÓ", r.mensaje()));
        }
        System.out.println(LINEA);
        mostrarRecursos(recursos);
    }

    public void mostrarRecursos(RecursosMision recursos) {
        System.out.println("Estado de los recursos:");
        System.out.println(String.format(Locale.US, "  Energía disponible: %.2f", recursos.getEnergiaDisponible()));
        System.out.println(String.format(Locale.US, "  Datos en órbita:    %.2f MB", recursos.getDatosEnOrbita()));
        System.out.println(String.format(Locale.US, "  Datos descargados:  %.2f MB", recursos.getDatosDescargados()));
    }

    public void mostrarResumenComunicaciones(int totalTierra, int activosTierra, double capacidadActiva,
                                             double totalDescargado, List<ModuloTierra> mayores) {
        System.out.println();
        System.out.println("Resumen de comunicaciones:");
        System.out.println(LINEA);
        System.out.println("Módulos de tierra: " + totalTierra + " (activos: " + activosTierra + ")");
        System.out.println(String.format(Locale.US,
                "Capacidad total de descarga por ciclo (activos): %.2f MB", capacidadActiva));
        System.out.println(String.format(Locale.US,
                "Total histórico descargado: %.2f MB", totalDescargado));

        if (mayores.isEmpty()) {
            System.out.println("No hay módulos de tierra para mostrar.");
        } else {
            System.out.println(mayores.size() > 1
                    ? "Módulos con mayor descarga histórica (empate):"
                    : "Módulo con mayor descarga histórica:");
            for (ModuloTierra t : mayores) {
                System.out.println(String.format(Locale.US, "  %s | ID: %s | Estación: %s | %.2f MB",
                        t.getNombre(), t.getId(), t.getEstacion(), t.getTotalDescargado()));
            }
        }
        System.out.println(LINEA);
    }
}