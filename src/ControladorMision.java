public class ControladorMision {
    private final Mision mision;
    private final VistaConsola vista;

    public ControladorMision(Mision mision, VistaConsola vista) {
        this.mision = mision;
        this.vista = vista;
    }

    public boolean ejecutar() {
        vista.mostrarMenu();

        int opcion = vista.leerOpcion(0, 5);
        switch (opcion) {
            case 1 -> listar();
            case 2 -> buscar();
            case 3 -> catalogo();
            case 4 -> avanzarCiclo();
            case 5 -> resumen();

            case 0 -> {
                vista.mostrarMensaje("Saliendo del programa...");
                return false;
            }

            default -> throw new IllegalStateException("Opción inválida: " + opcion);
        }

        return true;
    }

    private void listar() {
        var modulos = mision.getModulos();

        vista.mostrarModulos(modulos.toList());
    }

    private void buscar() {
        vista.mostrarMenuBusqueda();

        int opcion = vista.leerOpcion(1, 2);
        String busqueda = vista.leerTexto("Introduce la busqueda: ");
        switch (opcion) {
            case 1:
                var r = mision.buscarPorId(busqueda);

                vista.mostrarModulos(r.stream().toList());
                break;

            case 2:
                var lr = mision.buscarPorNombre(busqueda).toList();

                vista.mostrarModulos(lr);
                break;

            default:
                throw new IllegalStateException("Opción inválida: " + opcion);
        }
    }

    private void catalogo() {
        var ordenada = mision.catalogoPorCosto().toList();
        vista.mostrarCatalogo(ordenada);
    }

    private void avanzarCiclo() {
        var respuestas = mision.ciclo();

        vista.mostrarResultadosCiclo(mision.getModulos().toList(), respuestas, mision.getRecursos());
    }

    private void resumen() {
        vista.mostrarResumenComunicaciones(
                mision.modulosTierra(),
                mision.modulosTierraActivos(),
                mision.capacidadActiva(),
                mision.totalDescargado(),
                mision.modulosTierraMejores()
        );
    }
}
