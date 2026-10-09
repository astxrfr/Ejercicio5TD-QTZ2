public class CargaInicial {
    public static Mision construirInicial() {
        var recursos = new RecursosMision(25, 210, 830);
        var mision = new Mision(recursos);

        // 1. Panel activo
        mision.agregarModulo(new ModuloEnergia(
                "Panel Solar Norte", 100, 450.0, recursos,
                20, 18));

        // 2. Cámara activa
        mision.agregarModulo(new ModuloVuelo(
                "Cámara Óptica Alfa", 95, 820.0, recursos,
                Instrumento.CAMARA, 25, 8, 14));

        // 3. Antena activa
        mision.agregarModulo(new ModuloTierra(
                "Antena Campus UVG", 90, 600.0, recursos,
                40, 6, 320, "Campus UVG"));

        // 4. Sensor activo
        mision.agregarModulo(new ModuloVuelo(
                "Sensor Magnetómetro", 88, 540.0, recursos,
                Instrumento.SENSOR, 15, 5, 18));

        // 5. Panel activo
        mision.agregarModulo(new ModuloEnergia(
                "Panel Solar Sur", 92, 380.0, recursos,
                15, 20));

        // 6. Antena activa
        mision.agregarModulo(new ModuloTierra(
                "Antena Quetzaltenango", 85, 560.0, recursos,
                30, 5, 100, "Estación Quetzaltenango"));

        // 7. Cámara INACTIVA (sirve para probar el mensaje de causa)
        mision.agregarModulo(inactivo(new ModuloVuelo(
                "Cámara Infrarroja Beta", 40, 910.0, recursos,
                Instrumento.CAMARA, 30, 10, 10)));

        // 8. Antena INACTIVA
        mision.agregarModulo(inactivo(new ModuloTierra(
                "Antena Petén", 55, 480.0, recursos,
                25, 4, 90, "Estación Petén")));

        // 9. Panel INACTIVO
        mision.agregarModulo(inactivo(new ModuloEnergia(
                "Panel Solar Auxiliar", 60, 250.0, recursos,
                10, 16)));

        // 10. Sensor activo
        mision.agregarModulo(new ModuloVuelo(
                "Sensor de Radiación", 97, 430.0, recursos,
                Instrumento.SENSOR, 10, 4, 12));

        // 11. Antena activa; empata en el máximo histórico (210 MB) con
        //     "Antena Quetzaltenango": prueba el caso de empate del resumen
        mision.agregarModulo(new ModuloTierra(
                "Antena Cobán", 80, 390.0, recursos,
                20, 4, 320, "Estación Cobán"));

        return mision;
    }

    private static <T extends Modulo> T inactivo(T modulo) {
        modulo.desactivar();
        return modulo;
    }
}
