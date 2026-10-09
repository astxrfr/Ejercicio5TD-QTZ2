import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Mision {
    private List<Modulo> modulos;
    private RecursosMision recursos;

    public Mision(RecursosMision recursos) {
        this.modulos = new ArrayList<>();
        this.recursos = recursos;
    }

    public Stream<Modulo> getModulos() {
        return List.copyOf(modulos).stream();
    }

    public Optional<Modulo> buscarPorId(String id) {
        return modulos.stream().filter(m -> m.getId().equals(id)).findFirst();
    }

    public Stream<Modulo> buscarPorNombre(String nombre) {
        return modulos.stream().filter(m -> m.getNombre().equalsIgnoreCase(nombre));
    }

    public Stream<Modulo> catalogoPorCosto() {
        return List.copyOf(modulos).stream()
                .sorted(Comparator.comparingDouble(Modulo::getCosto));
    }

    public List<Respuesta<?>> ciclo() {
        List<Respuesta<?>> respuestas = new ArrayList<>();
        for (Modulo modulo : modulos) {
            respuestas.add(modulo.procesarCiclo());
        }

        return respuestas;
    }

    public int modulosTierra() {
        return (int) modulos.stream().filter(m -> m instanceof ModuloTierra).count();
    }

    public int modulosTierraActivos() {
        return (int) modulos.stream().filter(Modulo::isActivo).filter(m -> m instanceof ModuloTierra).count();
    }

    public double capacidadActiva() {
        return modulos.stream()
                .filter(Modulo::isActivo)
                .filter(m -> m instanceof ModuloTierra)
                .mapToDouble(m -> ((ModuloTierra) m).getCapacidadDescarga())
                .sum();
    }

    public List<ModuloTierra> modulosTierraMejores() {
        List<ModuloTierra> tierra = modulos.stream()
                .filter(m -> m instanceof ModuloTierra)
                .map(m -> (ModuloTierra) m)
                .toList();
        double max = tierra.stream().mapToDouble(ModuloTierra::getTotalDescargado).max().orElse(0);
        return tierra.stream().filter(t -> t.getTotalDescargado() == max).toList();
    }

    public double totalDescargado() {
        return modulos.stream()
                .filter(m -> m instanceof ModuloTierra)
                .mapToDouble(m -> ((ModuloTierra) m).getTotalDescargado())
                .sum();
    }

    public void agregarModulo(Modulo m) {
        this.modulos.add(m);
    }

    public RecursosMision getRecursos() {
        return recursos;
    }
}
