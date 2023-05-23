package gestorAplicacion;

import java.util.concurrent.atomic.AtomicReference;

import static java.lang.Double.valueOf;

public class Estadistica {

    public static double calcularPosibleCantidadPrestamo(Usuario usuario, double ingresos, int edad, int hijos) {
        double promedioAhorros = calcularPromedioAhorros(usuario);

        double posiblePrestamo = promediarVariablesDelUsuario(ingresos, promedioAhorros, edad, hijos);

        return posiblePrestamo;
    }
    private static double calcularPromedioAhorros(Usuario usuario) {
        AtomicReference<Double> totalAhorros = new AtomicReference<>((double) 0);
        usuario.getAhorros().stream().forEach(ahorro -> totalAhorros.updateAndGet(v -> valueOf(v + ahorro.getSaldo())));
        int cantidadAhorros =  usuario.getAhorros().size();

        double promedioAhorros = Double.valueOf(String.valueOf(totalAhorros))/ Double.valueOf(cantidadAhorros);
        System.out.println(promedioAhorros);

        return promedioAhorros;
    }

    private static double promediarVariablesDelUsuario(double ingresos, double promedioAhorros, int edad, int hijos) {
        // puntaje
        int multiplicadorCantidadAPrestar = 3;
        double posiblePrestamo = 150000;

        double ingresoDe12Meses = ingresos * 8;
        // Se espera que el usuario tenga por lo menos 8 meses de ahorro de sus ingresos
        // Buscando validar su disciplina
        if (promedioAhorros > ingresoDe12Meses) {
            multiplicadorCantidadAPrestar += 4;
        } else if (promedioAhorros > ingresoDe12Meses / 2) {
            multiplicadorCantidadAPrestar += 3;
        }
        if (edad > 72) {
            multiplicadorCantidadAPrestar -= 3;
        }
        if (hijos > 0) {
            multiplicadorCantidadAPrestar -= 2;
        }

        posiblePrestamo = (posiblePrestamo + promedioAhorros) * multiplicadorCantidadAPrestar;

        System.out.println(posiblePrestamo);
        return posiblePrestamo;
    }

}
