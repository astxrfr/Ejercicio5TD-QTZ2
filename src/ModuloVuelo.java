public class ModuloVuelo extends ModuloConsumidor {
    private Instrumento instrumento;
    private double mbPorCicloCiclo;
    private double energiaPorCiclo;
    private int ciclos;
    private double mbTotalRecolectados;

    public ModuloVuelo(String nombre, double salud, double costo, RecursosMision recursos, Instrumento instrumento, double mbPorCicloCiclo, double energiaPorCiclo, int ciclos) {
        super(nombre, salud, costo, recursos);
        this.instrumento = instrumento;
        this.mbPorCicloCiclo = mbPorCicloCiclo;
        this.energiaPorCiclo = energiaPorCiclo;
        this.ciclos = ciclos;
        this.mbTotalRecolectados = mbPorCicloCiclo * ciclos;
    }

    @Override
    protected double getEnergiaRequerida() {
        return energiaPorCiclo;
    }

    @Override
    protected String operar(RecursosMision recursos) {
        recursos.consumirEnergia(energiaPorCiclo);
        recursos.registrarRecoleccion(mbPorCicloCiclo);
        mbTotalRecolectados += mbPorCicloCiclo;
        ciclos++;

        return String.format("Consumió %.2f de energía y recolectó %.2f MB.", energiaPorCiclo, mbPorCicloCiclo);
    }

    @Override
    public String getPrefijoId() {
        return "VUE";
    }

    @Override
    public String toString() {
        return super.toString() + String.format(
                "%n  Tipo: Vuelo (%s) | Recolecta por ciclo: %.2f MB | Consumo por ciclo: %.2f"
                        + "%n  Ciclos acumulados: %d | Total recolectado: %.2f MB",
                instrumento, mbPorCicloCiclo, energiaPorCiclo, ciclos, mbTotalRecolectados);
    }
}
