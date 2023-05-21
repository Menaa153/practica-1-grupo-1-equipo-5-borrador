package gestorAplicacion;

import java.util.concurrent.atomic.AtomicReference;

import static java.lang.Double.valueOf;

public class Estadistica {

    public static double calcularPosibleCantidadPrestamo(Usuario usuario, double ingresos) {
        double promedioAhorros = calcularPromedioAhorros(usuario);

        return promediarAhorrosIngresos(ingresos, promedioAhorros);
    }
    public static double calcularPromedioAhorros(Usuario usuario) {
        AtomicReference<Double> totalAhorros = new AtomicReference<>((double) 0);
        usuario.getAhorros().stream().forEach(ahorro -> totalAhorros.updateAndGet(v -> valueOf(v + ahorro.getSaldo())));
        int cantidadAhorros =  usuario.getAhorros().size();

        double promedioAhorros = Double.valueOf(String.valueOf(totalAhorros))/ Double.valueOf(cantidadAhorros);
        System.out.println(promedioAhorros);

        return promedioAhorros;
    }

    public static double promediarAhorrosIngresos(double ingresos, double promedioAhorros) {
        int multiplicadorCantidadAPrestar = 2;

        double ingresoDe12Meses = ingresos * 12;
        // Se espera que el usuario tenga por lo menos 12 meses de ahorro de sus ingresos
        // Buscando validar su disciplina
        if (promedioAhorros > ingresoDe12Meses) {
            multiplicadorCantidadAPrestar = 4;
        } else if (promedioAhorros > ingresoDe12Meses / 2) {
            multiplicadorCantidadAPrestar = 3;
        }

        double posiblePrestamo = promedioAhorros * multiplicadorCantidadAPrestar;

        System.out.println(posiblePrestamo);
        return posiblePrestamo;
    }

}
