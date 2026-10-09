Mision mision = CargaInicial.construirInicial();
ControladorMision controlador = new ControladorMision(mision, new VistaConsola());

void main(String[] args) {
    boolean continuar;
    do {
        continuar = controlador.ejecutar();
    } while (continuar);
}