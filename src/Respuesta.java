import java.util.Optional;

public record Respuesta<T>(boolean exito, String mensaje, Optional<T> datos) {
    Respuesta(boolean exito, String mensaje) {
        this(exito, mensaje, Optional.empty());
    }
}
