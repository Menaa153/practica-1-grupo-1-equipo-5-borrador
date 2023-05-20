package uiMain;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import gestorAplicacion.confirmacion.Datos;
import baseDatos.Serializador;
import gestorAplicacion.confirmacion.Listador;
import gestorAplicacion.confirmacion.Verificacion;
import gestorAplicacion.*;


import static gestorAplicacion.confirmacion.Verificacion.*;

import java.io.IOException;

public class Main {
    static Usuario usuario;


    static Usuario login() {
        return Datos.getUsuarios();
    }
    public static void main(String[] args) throws IOException {
        App.intializeApp();
        App.run();

        /*Usuario usuario = new Usuario("1", "Jaime Alberto Guzman Luna", "jGuzman@unal.edu.co");
        DataBank.nuevoUsuario(usuario);*/

        int option;

        usuario = login();
        do {
            System.out.println("---- FINANZAS PERSONALES ----");
            System.out.println("|| USUARIO: " + usuario.getNombre() + " ||");
            System.out.println("¿Qué operación desea realizar?");
            System.out.println("1. Ver Estadisticas de la cuenta");
            System.out.println("2. Ingresar dinero a su cuenta");
            System.out.println("3. Mover dinero en su cuenta");
            System.out.println("4. Sacar dinero de su cuenta");
            System.out.println("5. Agregar bolsillo a su cuenta");
            System.out.println("6. Agregar Ahorro a su cuenta");
            System.out.println("7. Agregar Meta a su cuenta");
            System.out.println("8. Modificar Ahorro/Bolsillo/Meta");
            System.out.println("9. Solicitar Préstamo");
            System.out.println("10. Abonar a un préstamo o Meta");
            System.out.println("11. Logout");
            System.out.println("12. Terminar ");
            option = validarEntradaInt(12, true, 0, false);

            switch (option) {
                case 1 -> saldosDisponibles();
                case 2 -> ingresaDinero();
                case 3 -> moverDineroInterno();
                case 4 -> SacarDinero();
                case 5 -> agregarBolsillo();
                case 6 -> agregarAhorro();
                case 7 -> agregarMeta();
                case 8 -> opcionModificar();
                case 9 -> solicitarPrestamo();
                case 10 -> abonarPrestamoOMeta();
                case 11 -> {
                   return;
                }
                //case 12 -> Serializador.serializar();
                default -> System.out.println("OPCIÓN EN DESARROLLO");
            }
        } while (option != 12);
        System.exit(0);
    }

    //OPCIÓN1
    //Menú de cuentas disponibles y sus respectivos saldos del usuario seleccionado en el login()
    static void saldosDisponibles() {
        int option;
        System.out.println("¿Qué cuentas desea visualizar?");
        System.out.println("1. Bolsillos");
        System.out.println("2. Ahorros");
        System.out.println("3. Metas");
        System.out.println("4. Dinero total");
        System.out.println("5. Volver al inicio");
        option = validarEntradaInt(5, true, 0, false);

        switch (option) {
            case 1 -> Listador.listarBolsillos();
            case 2 -> Listador.listarAhorros(usuario);
            case 3 -> Listador.listarMetas(usuario);
            case 4 -> {
                int dineroTot = usuario.getDineroCuenta();
                System.out.println("--------------------------------------------------------------------------");
                System.out.println("Dinero total: ");
                System.out.println(dineroTot);
                
                System.out.println("--------------------------------------------------------------------------");
            }
        }
    }

    //OPCIÓN2
    //Menú de selección a que cuenta va a realizar el ingreso de dinero y se llama a elección BancoMonto() donde se va a realizar el ingreso
    static void ingresaDinero() {
        int option, opc;
        System.out.println("¿Para donde va su dinero?");
        System.out.println("1. Bolsillos");
        System.out.println("2. Ahorros");
        System.out.println("3. Volver al inicio");
        option = validarEntradaInt(3, true, 1, true);
        boolean bool = false;
        switch (option) {
            case 1 -> {
                List<Categoria> list=new ArrayList<>();
                System.out.println("Bolsillo: ");
                bool = Listador.listarBolsillos();

                for(Categoria bolsillos:Categoria.values()){
                  list.add(bolsillos);
                } 

                if (bool) {
                    opc = validarEntradaInt(8, true, 1, true) - 1;
                    Categoria bolsillo=list.get(opc);

                    double cantidad;
                    System.out.println("Digite la cantidad que desea ingresar en (utilice ',' para el símbolo decimal) (Cantidad maxima 10000000): ");
                    cantidad = Verificacion.validarEntradaDouble(10000000, true, 0, false);
                    Ingreso ingreso = new Ingreso((int)cantidad, LocalDate.now(), bolsillo);
                    System.out.println("Su nuevo saldo es de " + String.format("%.2f",bolsillo.getSaldo()));

                  } 
            }
            case 2 -> {
                List<Cuenta> list = new ArrayList<>();
                System.out.println("Ahorro: ");
                bool = Listador.listarAhorros(usuario);
                list.addAll(usuario.getAhorros());
            
             if (bool) {
               opc = validarEntradaInt(list.size(), true, 1, true) - 1;
               Cuenta cuenta=list.get(opc);

               double cantidad;
               System.out.println("Digite la cantidad que desea ingresar en (utilice ',' para el símbolo decimal) (Cantidad maxima 10000000): ");
               cantidad = Verificacion.validarEntradaDouble(10000000, true, 0, false);
               Ingreso ingreso = new Ingreso((int)cantidad, LocalDate.now(), cuenta);
               usuario.nuevoIngreso(ingreso);
               System.out.println("Su nuevo saldo es de " + String.format("%.2f",cuenta.getSaldo()));

             }
            } 
        }
    }


    //Opcion3
    static void moverDineroInterno() {
        int option;
        System.out.println("¿Para donde va su dinero?");
        System.out.println("1. Bolsillos");
        System.out.println("2. Ahorros");
        System.out.println("3. Volver al inicio");
        option = validarEntradaInt(3, true, 1, true);
        boolean bool = false;
        switch (option) {
            case 1 -> {
                Categoria destino; 
                Object origen;
                List<Categoria> list = new ArrayList<>();
                System.out.println("Bolsillos: ");
                bool = Listador.listarBolsillos();

                for(Categoria bolsillos:Categoria.values()){
                    list.add(bolsillos);
                } 
                
                if (bool) {
                    int opc = validarEntradaInt(8, true, 1, true) - 1;
                    destino = list.get(opc);
                    origen = seleccionarCuentaDeOrigen(destino);

                    if (origen instanceof Cuenta){
                        Cuenta origen2=(Cuenta)origen;
                        if (origen != null && origen2.getSaldo() > 0) {
                            System.out.println("Ingrese la cantidad a transferir (entre 0 y " + String.format("%.2f",origen2.getSaldo()) + ")");
                            double monto = Verificacion.validarEntradaDouble(origen2.getSaldo(), true, 0, false);
                            int monto2=(int)monto;

                            boolean retirado = origen2.retirar(monto2);

                            if (!retirado) {
                                System.err.println("No fue posible retirar");
                                return;
                            }
                            destino.setSaldo(destino.getSaldo()+monto);
                            System.out.println("Nuevo saldo de la cuenta de origen de: " + String.format("%.2f",origen2.getSaldo()));
                            System.out.println("Nuevo saldo de la cuenta de destino de: " + String.format("%.2f",destino.getSaldo()));
                        }else {
            	        System.out.println("La cuenta no existe o no contiene dinero");
                        }

                    }

                    if (origen instanceof Categoria){
                        Categoria origen2=(Categoria)origen;
                        if (origen != null && origen2.getSaldo() > 0) {
                            System.out.println("Ingrese la cantidad a transferir (entre 0 y " + String.format("%.2f",origen2.getSaldo()) + ")");
                            double monto = Verificacion.validarEntradaDouble(origen2.getSaldo(), true, 0, false);
                            int monto2=(int)monto;

                            boolean retirado=true;
                            if (monto2>origen2.getSaldo()){
                              retirado = false;
                            }

                            if (!retirado) {
                                System.err.println("No fue posible retirar");
                                return;
                            }
                            origen2.setSaldo(origen2.getSaldo()-monto2);
                            destino.setSaldo(destino.getSaldo()+monto);
                            System.out.println("Nuevo saldo de la cuenta de origen de: " + String.format("%.2f",origen2.getSaldo()));
                            System.out.println("Nuevo saldo de la cuenta de destino de: " + String.format("%.2f",destino.getSaldo()));
                        }else {
            	        System.out.println("La cuenta no existe o no contiene dinero");
                        }

                    }
                }
            }

            case 2 -> {
                Cuenta destino;
                Object origen;
                List<Cuenta> list = new ArrayList<>();
                System.out.println("Ahorros: ");
                bool = Listador.listarAhorros(usuario);
                list.addAll(usuario.getAhorros());
        
                if (bool) {
                    int opc = validarEntradaInt(list.size(), true, 1, true) - 1;
                    destino = list.get(opc);
                    origen = seleccionarCuentaDeOrigen(destino);

                    if (origen instanceof Cuenta){
                        Cuenta origen2=(Cuenta)origen;
                        if (origen != null && origen2.getSaldo() > 0) {
                            System.out.println("Ingrese la cantidad a transferir (entre 0 y " + String.format("%.2f",origen2.getSaldo()) + ")");
                            double monto = Verificacion.validarEntradaDouble(origen2.getSaldo(), true, 0, false);
                            int monto2=(int)monto;

                            boolean retirado = origen2.retirar(monto2);

                            if (!retirado) {
                                System.err.println("No fue posible retirar");
                                return;
                            }
                            destino.depositar(monto2);
                            System.out.println("Nuevo saldo de la cuenta de origen de: " + String.format("%.2f",origen2.getSaldo()));
                            System.out.println("Nuevo saldo de la cuenta de destino de: " + String.format("%.2f",destino.getSaldo()));
                        }else {
            	        System.out.println("La cuenta no existe o no contiene dinero");
                        }

                    }

                    if (origen instanceof Categoria){
                        Categoria origen2=(Categoria)origen;
                        if (origen != null && origen2.getSaldo() > 0) {
                            System.out.println("Ingrese la cantidad a transferir (entre 0 y " + String.format("%.2f",origen2.getSaldo()) + ")");
                            double monto = Verificacion.validarEntradaDouble(origen2.getSaldo(), true, 0, false);
                            int monto2=(int)monto;

                            boolean retirado=true;
                            if (monto2>origen2.getSaldo()){
                              retirado = false;
                            }

                            if (!retirado) {
                                System.err.println("No fue posible retirar");
                                return;
                            }
                            origen2.setSaldo(origen2.getSaldo()-monto2);
                            destino.depositar(monto2);
                            System.out.println("Nuevo saldo de la cuenta de origen de: " + String.format("%.2f",origen2.getSaldo()));
                            System.out.println("Nuevo saldo de la cuenta de destino de: " + String.format("%.2f",destino.getSaldo()));
                        }else {
            	        System.out.println("La cuenta no existe o no contiene dinero");
                        }

                    }
                    
                }
            }
        }
    }

    static Object seleccionarCuentaDeOrigen(Object destino) {
        boolean repet = false;
        do {
            int option, opc;
            Object origen;
            repet = false;
            System.out.println("¿De donde sale su dinero?");
            System.out.println("1. Bolsillos");
            System.out.println("2. Ahorros");
            System.out.println("3. Volver al inicio");
            option = validarEntradaInt(3, true, 1, true);
            boolean bool = false;
            
            switch (option) {
                case 1 -> {
                    List<Categoria> list = new ArrayList<>();
                    System.out.println("Bolsillos: ");
                    bool = Listador.listarBolsillos();

                    for(Categoria bolsillos:Categoria.values()){
                        list.add(bolsillos);
                    }

                    if (bool) {
                        opc = validarEntradaInt(8, true, 1, true) - 1;
                        origen = list.get(opc);
                        if (origen == destino) {
                            System.out.println("NO PUEDES ENVIAR EL DINERO AL MISMO LUGAR");
                             repet = true;
                        } else {
                            return origen;
                        }
                    }
                    
                
                }
                case 2 -> {
                    List<Cuenta> list = new ArrayList<>();
                    System.out.println("Ahorros: ");
                    bool = Listador.listarAhorros(usuario);
                    list.addAll(usuario.getAhorros());

                    if (bool) {
                        opc = validarEntradaInt(list.size(), true, 1, true) - 1;
                        origen = list.get(opc);
                        if (origen == destino) {
                            System.out.println("NO PUEDES ENVIAR EL DINERO AL MISMO LUGAR");
                            repet = true;
                        } else {
                            return origen;
                        }
                    }
                }
            }
            
        } while (repet);
        return null;
    }

    //OPCION 4
    private static void SacarDinero() {
        int option;
        Cuenta destino = null;
        System.out.println("¿Desea hacer un envio o retiro?");
        System.out.println("1. Retiro");
        System.out.println("2. Volver al menu");
        option = Verificacion.validarEntradaInt(3, true, 1, true);
        switch (option) {
            case 2:
                return;
        }
        Object origen = seleccionarCuentaDeOrigen(destino);

        if (origen instanceof Categoria){
            boolean retirado=true;
            Categoria origen2=(Categoria) origen;
             if (origen != null && origen2.getSaldo()>0) {
                System.out.println("Ingrese la cantidad a retirar (entre 0 y " + String.format("%.2f",origen2.getSaldo()) + ")");
                double monto = Verificacion.validarEntradaDouble(origen2.getSaldo(), true, 0, false);
                if (monto>origen2.getSaldo()){
                    retirado = false;
                }
                if (retirado) {
                    origen2.setSaldo(origen2.getSaldo()-monto);
                    System.out.println("Retiro Exitoso");
                    System.out.println("Nuevo saldo en " + origen2.values() + " es: " + String.format("%.2f",origen2.getSaldo()));
                }
                else{
                    System.out.println("Retiro Fallido");
                }

            }else {
        	    System.out.println("La cuenta no existe o no contiene dinero");
            }
        } 
        if (origen instanceof Cuenta){
            Cuenta origen2=(Cuenta) origen;
            boolean retirado=true;
            if (origen != null && origen2.getSaldo()>0) {
                System.out.println("Ingrese la cantidad a transferir (entre 0 y " + String.format("%.2f",origen2.getSaldo()) + ")");
                double monto = Verificacion.validarEntradaDouble(origen2.getSaldo(), true, 0, false);
                retirado = origen2.retirar((int)monto);
                if (retirado) {
                    System.out.println("Retiro Exitoso");
                    System.out.println("Nuevo saldo en " + origen2.getNombre() + " es: " + String.format("%.2f",origen2.getSaldo()));
                }
                else{
                    System.out.println("Retiro Fallido");
                }
            }else {
                System.out.println("La cuenta no existe o no contiene dinero");
            }
           
        }     
    }

    //OPCIÓN 5
    //Se agrega un bolsillo al usuario que se seleccionó en el login() con el nombre y la divisa seleccionada por el usuario
    static void agregarBolsillo() {
        int divisa;
        String nombre;
        System.out.println("Elija la divisa que desea utilizar en el bolsillo");
        Utils.listarDivisas();
        divisa = validarEntradaInt(Divisa.values().length, true, 0, false) - 1;

        System.out.println("Escriba el nombre que desea asignarle al bolsillo: ");
        nombre = Validador.validarEntradaTexto(false);
        Bolsillo bolsillo = new Bolsillo(usuario, Divisa.values()[divisa], nombre);
        usuario.nuevoBolsillo(bolsillo);
        System.out.println("Bolsillo " + nombre + " AGREGADO CON EXITO");
    }

    //OPCIÓN6
    //Se agrega un ahorro al usuario que se seleccionó en el login() con el nombre y la fecha de retiro deseada
    static void agregarAhorro() {
        int fecha;
        String nombre;
       /* BORRAR System.out.println("Elija la divisa que desea utilizar en el colchón");
        Utils.listarDivisas();
        divisa = validarEntradaInt(Divisa.values().length, true, 1, true) - 1; BORRAR  */ 

        System.out.println("Escriba el nombre que desea asignarle al ahorro: ");
        nombre = Verificacion.validarEntradaTexto(true);

        System.out.println("Elija la fecha en que desea retirar el ahorro ");
        for (int i = 1; i <= 12; i++) {
            System.out.println(i + ". " + LocalDate.now().plusMonths(i));
        }
        fecha = validarEntradaInt(12, true, 1, true);
        Ahorro ahorro = new Ahorro(usuario, nombre, LocalDate.now().plusMonths(fecha));
        usuario.nuevoAhorro(ahorro);
        System.out.println("Ahorro " + nombre + " AGREGADO CON EXITO");
    }

    //OPCION 7
    private static void agregarMeta() {
        int divisa;
        String nombre;
        double objetivo;
        System.out.println("Elija la divisa que desea utilizar la meta: ");
        Utils.listarDivisas();
        divisa = validarEntradaInt(Divisa.values().length, true, 1, true) - 1;

        System.out.println("Escriba el nombre que desea asignarle a la meta: ");
        nombre = Validador.validarEntradaTexto(true);
        System.out.println("Ingrese el valor objetivo que desea asignarle a la meta (recuerde que no podra sacar el dinero de una meta hasta alcanzar el objetivo): ");
        objetivo = validarEntradaDouble(Double.MAX_VALUE, true, 0, true);
        Meta meta = new Meta(usuario, nombre, LocalDate.now(), objetivo, Divisa.values()[divisa]);
        usuario.nuevaMeta(meta);
        System.out.println("Meta Agregada Con Exito");
    }

    //OPCION 8
    //Menú para la eleccion de modificacion, sea bolsillo o colchón, luego se envia la eleccion a la funcion modificar
    static void opcionModificar() {
        int opcion, opc;
        System.out.println("¿Qué desea modificar?");
        System.out.println("1. Bolsillo");
        System.out.println("2. Colchon");
        System.out.println("3. Meta");
        System.out.println("4. Volver al inicio");
        opcion = validarEntradaInt(4, true, 1, true);
        boolean bool = false;
        List<Contable> list = new ArrayList<>();
        switch (opcion) {
            case 1 -> {
                System.out.println("Bolsillos: ");
                bool = Utils.listarBolsillos(usuario);
                list.addAll(usuario.getBolsillos());
            }
            case 2 -> {
                System.out.println("Colchones: ");
                bool = Utils.listarColchones(usuario);
                list.addAll(usuario.getColchones());
            }
            case 3 -> {
                System.out.println("Metas: ");
                bool = Utils.listarMetas(usuario);
                list.addAll(usuario.getMetas());
            }
        }
        if (bool) {
            opc = validarEntradaInt(list.size(), true, 1, true) - 1;
            modificar(list.get(opc));
        }
    }

    static void modificar(Contable x) {
        if (x instanceof Bolsillo then) {
            modificar(then);
        } else if (x instanceof Colchon then) {
            modificar(then);
        } else if (x instanceof Meta then) {
            modificar(then);
        }
    }

    //Menú para modificar nombre o divisa de un bolsillo
    static void modificar(Bolsillo bolsillo) {
        int opcion, divisa;
        System.out.println("¿Qué desea modificar?");
        System.out.println("1. Nombre");
        System.out.println("2. Divisa");
        System.out.println("3. Volver al inicio");
        opcion = validarEntradaInt(3, true, 1, true);

        switch (opcion) {
            case 1:
                System.out.println("Nuevo nombre:");
                String nombre = Validador.validarEntradaTexto(true);
                bolsillo.setNombre(nombre);
                break;
            case 2:
                System.out.println("Nueva divisa:");
                Utils.listarDivisas();
                divisa = validarEntradaInt(Divisa.values().length, true, 1, true) - 1;
                double[] nuevoSaldo = bolsillo.getDivisa().ConvertToDivisa(bolsillo.getSaldo(), Divisa.values()[divisa]);
                bolsillo.setDivisa(Divisa.values()[divisa]);
                bolsillo.setSaldo(nuevoSaldo[0]);
                System.err.println("Tasa de cambio: " + String.format("%.2f",nuevoSaldo[1]));
                System.err.println("Nuevo saldo: " + String.format("%.2f",nuevoSaldo[0]));
                break;
            case 3:
                return;
        }
        System.out.println("MODIFICACION REALIZADA CON EXITO");
    }

    //Menú para modificar nombre, divisa o fecha minima de retiro de un colchon
    static void modificar(Ahorro colchon) {
        int opcion, divisa;
        System.out.println("¿Qué desea modificar?");
        System.out.println("1. Nombre");
        System.out.println("2. Divisa");
        System.out.println("3. Cambiar fecha");
        System.out.println("4. Volver al inicio");
        opcion = validarEntradaInt(4, true, 1, true);

        switch (opcion) {
            case 1 -> {
                System.out.println("Nuevo nombre:");
                String nombre = Validador.validarEntradaTexto(true);
                colchon.setNombre(nombre);
            }
            case 2 -> {
                System.out.println("Nueva divisa:");
                Utils.listarDivisas();
                divisa = validarEntradaInt(Divisa.values().length, true, 1, true) - 1;
                double[] nuevoSaldo = colchon.getDivisa().ConvertToDivisa(colchon.getSaldo(), Divisa.values()[divisa]);
                colchon.setDivisa(Divisa.values()[divisa]);
                colchon.setSaldo(nuevoSaldo[0]);
                System.out.println("Tasa de cambio: " + String.format("%.2f",nuevoSaldo[1]));
                System.out.println("El nuevo saldo es: " + String.format("%.2f",nuevoSaldo[0]));
            }
            case 3 -> {
                System.out.println("¿Cuánto lo desea modificar?");
                System.out.println("1. Días");
                System.out.println("2. Meses");
                System.out.println("3. Años");
                System.out.println("4. Volver al inicio");
                opcion = validarEntradaInt(4, true, 1, true);
                int limite = 0;
                int total = 0;
                switch (opcion) {
                    case 1:
                        limite = 31;
                        break;
                    case 2:
                        limite = 12;
                        break;
                    case 3:
                        limite = 10;
                        break;
                    case 4:
                        return;
                }
                System.out.println("Ingrese la cantidad que desa modificar (entre 1 y " + limite + ")");
                total = Validador.validarEntradaInt(limite, true, 1, true);

                System.out.println("¿Aumentar o reducir?");
                System.out.println("1. Aumentar");
                System.out.println("2. Reducir");
                System.out.println("3. Volver al inicio");
                int opcion2 = Validador.validarEntradaInt(3, true, 1, true);
                if (opcion2 == 3) {
                    return;
                } else if (opcion2 == 2) {
                    switch (opcion) {
                        case 1 -> colchon.setFechaRetiro(colchon.getFechaRetiro().minusDays(total));
                        case 2 -> colchon.setFechaRetiro(colchon.getFechaRetiro().minusMonths(total));
                        case 3 -> colchon.setFechaRetiro(colchon.getFechaRetiro().minusYears(total));
                    }
                } else {
                    switch (opcion) {
                        case 1 -> colchon.setFechaRetiro(colchon.getFechaRetiro().plusDays(total));
                        case 2 -> colchon.setFechaRetiro(colchon.getFechaRetiro().plusMonths(total));
                        case 3 -> colchon.setFechaRetiro(colchon.getFechaRetiro().plusYears(total));
                    }
                }
            }
            case 4 -> {
                return;
            }
        }
        System.out.println("MODIFICACION REALIZADA CON EXITO");
    }

    static void modificar(Meta meta) {
        int opcion, divisa;
        boolean[] bol; 
        System.out.println("¿Qué desea modificar?");
        System.out.println("1. Nombre");
        System.out.println("2. Divisa");
        System.out.println("3. Nuevo objetivo");
        System.out.println("4. Volver al inicio");
        opcion = validarEntradaInt(4, true, 1, true);

        switch (opcion) {
            case 1:
                System.out.println("Nuevo nombre:");
                String nombre = Validador.validarEntradaTexto(true);
                meta.setNombre(nombre);
                break;
            case 2:
                System.out.println("Nueva divisa:");
                Utils.listarDivisas();
                divisa = validarEntradaInt(Divisa.values().length, true, 1, true) - 1;
                double[] nuevoSaldo = meta.getDivisa().ConvertToDivisa(meta.getSaldo(), Divisa.values()[divisa]);
                meta.setDivisa(Divisa.values()[divisa]);
                meta.setSaldo(nuevoSaldo[0]);
                System.out.println("Tasa de cambio: " + String.format("%.2f",nuevoSaldo[1]));
                System.out.println("Nuevo saldo: " + String.format("%.2f",nuevoSaldo[0]));
                break;
            case 3:
                System.out.println("Nuevo Objetivo (en " + meta.getDivisa() + "): ");
                bol = meta.setObjetivo(Validador.validarEntradaDouble(Double.MAX_VALUE, true, 0, false));
                if(!bol[0]) {
                	System.out.println("Esta Meta ya esta cumplida por lo que no es posible cambiar el objetivo");
                	return;
                }
                if (bol[1]) {
                    System.out.println("FELICIDADES HAS CUMPLIDO TU META " + meta.getNombre().toUpperCase());
                    System.out.println("Escoge un Bolsillo al cual enviar el dinero para que lo puedas usar (" + String.format("%.2f",meta.getSaldo()) + " " + meta.getDivisa() + "): ");
                    Utils.listarBolsillos(usuario);
                    int option = Validador.validarEntradaInt(usuario.getBolsillos().size(), true, 1, true) - 1;
                    Bolsillo bolsillo = usuario.getBolsillos().get(option);
                    Movimiento movimiento = meta.terminar(bolsillo);
                    System.out.println("Nuevo saldo en el bolsillo de: " + String.format("%.2f",bolsillo.getSaldo()) + " " + bolsillo.getDivisa());
                    System.out.println("TRM usada de: " + String.format("%.2f",movimiento.getValorDestino()/movimiento.getValorOrigen()));
                }else{
                    System.out.println("Restante para cumplir la meta de: " +String.format("%.2f",meta.getObjetivo()-meta.getSaldo()));
                }
                break;
            case 4:
                return;
        }
        System.out.println("MODIFICACION REALIZADA CON EXITO");
    }

    //OPCIÓN 9
    //Menú para que el usuario seleccione que prestamo desea
    static void solicitarPrestamo() {
        System.out.println("Seleccione la divisa con la que solicitara el prestamo");
        Utils.listarDivisas();
        int divisa = Validador.validarEntradaInt(Divisa.values().length, true, 1, true) - 1;
        int opcion;
        System.out.println("¿Qué tipo de prestamo desea solicitar?");
        System.out.println("1. Prestamo fugaz");
        System.out.println("2. Prestamo a largo plazo");
        System.out.println("3. Volver al inicio");
        opcion = validarEntradaInt(3, true, 1, true);

        switch (opcion) {
            case 1:
                peticionPrestamoF(Divisa.values()[divisa]);
                break;
            case 2:
                peticionPrestamoLP(Divisa.values()[divisa]);
                break;
            case 3:
                break;
        }
    }

    //Se inicia la peticion del prestamo a largo plazo con algunos datos basicos para validar si es apto
    static void peticionPrestamoLP(Divisa divisa) {
        int hijos, tiempo = 0, edad;
        double ingresoMensual, dineroSolicitado;

        System.out.println("---- Criterios para validar el credito ----");
        System.out.println("¿Cuántos hijos tiene?: ");
        hijos = Validador.validarEntradaInt(Integer.MAX_VALUE, true, 0, true);

        System.out.println("Digite su ingreso mensual en " + divisa + " (utilice ',' para el símbolo decimal):");
        ingresoMensual = Validador.validarEntradaDouble(Double.MAX_VALUE, true, 0, false);

        System.out.println("¿Cuánto dinero desea solicitar para realizar el prestamo? " + divisa + "(utilice ',' para el símbolo decimal) ");
        dineroSolicitado = Validador.validarEntradaDouble(Double.MAX_VALUE, true, 0, false);

        System.out.println("¿Cuántos Años tiene usted?");
        edad = Validador.validarEntradaInt(120, true, 18, true);

        double puntaje = 0;
        if (80 - edad >= 1) {
            System.out.println("¿A cuántos Años desea solicitar el prestamo?");
            tiempo = Validador.validarEntradaInt(80 - edad, true, 1, true);
            if (dineroSolicitado / (tiempo * 12) < ingresoMensual * 0.5) {
                puntaje = -hijos + divisa.ConvertToDivisa(ingresoMensual, Divisa.USD)[0] / 200 + tiempo - divisa.ConvertToDivisa(dineroSolicitado, Divisa.USD)[0] / (tiempo * 12) / 1000;
            }

        }
        if (puntaje > 5) {
            AceptadoPrestamoLP(dineroSolicitado, tiempo * 12, divisa);
        } else {
            System.out.println("PRESTAMO RECHAZADO/CANCELADO...");
        }
    }

    //Si el usuario es apto para un prestado a largo plazo se le solicitan unos datos para guardar como garantía, se genera el préstamo y se agrega a los préstamos realizados por el usuario
    static void AceptadoPrestamoLP(double dineroSolicitado, int tiempo, Divisa divisa) {
        String[] referencia = new String[2];
        int opcGarantia = -1, opc;
        System.out.println("Escriba el nombre de una referencia: ");
        referencia[0] = validarEntradaTexto(true);

        System.out.println("Escriba el numero telefonico de la referencia: ");
        referencia[1] = validarEntradaTexto(true);
        System.out.println("¿Desea dar alguna garantía para reducir la taza de interes?");
        System.out.println("1. Si");
        System.out.println("2. No");
        opc = Validador.validarEntradaInt(2, true, 1, true);
        if (opc == 1) {
            System.out.println("Escoja el Elemento que dejara Como garantía");
            Utils.listarGarantias();
            opcGarantia = Validador.validarEntradaInt(Garantia.values().length, true, 1, true) - 1;
        }
        System.out.println("Escoja el bolsillo al que se le enviará el dinero");
        Utils.listarBolsillos(usuario);
        int bolsillo = Validador.validarEntradaInt(usuario.getBolsillos().size(), true, 1, true) - 1;
        PrestamoLargoPlazo prestamo;
        if (opcGarantia < 0) {
			prestamo = new PrestamoLargoPlazo(usuario, dineroSolicitado, tiempo, LocalDate.now(), divisa, referencia);
        } else {
            prestamo = new PrestamoLargoPlazo(usuario, dineroSolicitado, tiempo, LocalDate.now(), divisa, referencia, Garantia.values()[opcGarantia]);
        }
        usuario.nuevoPrestamo(prestamo, usuario.getBolsillos().get(bolsillo));
        System.out.println("PRESTAMO APROBADO...");

    }

    //Se inicia la peticion del prestamo fugaz con algunos datos basicos para validar si es apto
    static void peticionPrestamoF(Divisa divisa) {
        int opcion;
        double montoPrestamo = 0;
        if (usuario.getIngresos().size() > 1) {

            double ingresoTotPesos = 0;
            for (Ingreso i : usuario.getIngresos()) {
                double[] pesos = i.getDivisaDestino().ConvertToDivisa(i.getValorDestino(), Divisa.COP);
                ingresoTotPesos += pesos[0];
            }
            //El prestamo fugaz ofrece 2 opciones cuando el usuario es apto
            double montoPrestamo1 = Divisa.COP.ConvertToDivisa(Math.min(ingresoTotPesos * 0.1, 1000000), divisa)[0];//el 10% de los ingresos realizados desde que sea inferior a 1 millon
            double montoPrestamo2 = Divisa.COP.ConvertToDivisa(Math.min(ingresoTotPesos * 0.4, 2000000), divisa)[0];//o el 40% de los ingresos realizados desde que sea inferior a 2 millones

            System.out.println("¿Qué cantidad desea prestar?");
            System.out.println("1. " + String.format("%.2f",montoPrestamo1));
            System.out.println("2. " + String.format("%.2f",montoPrestamo2));
            System.out.println("3. Volver al inicio");
            opcion = Validador.validarEntradaInt(3, true, 1, true);

            switch (opcion) {
                case 1 -> montoPrestamo = montoPrestamo1;
                case 2 -> montoPrestamo = montoPrestamo2;
                case 3 -> montoPrestamo = 0;
            }
            if (montoPrestamo != 0) {
                System.out.println("Escoja el bolsillo al que se le enviara el dinero");
                Utils.listarBolsillos(usuario);
                int bolsillo = Validador.validarEntradaInt(usuario.getBolsillos().size(), true, 1, true) - 1;
                PrestamoFugaz prestamo = new PrestamoFugaz(usuario, montoPrestamo, LocalDate.now(), divisa);
                usuario.nuevoPrestamo(prestamo, usuario.getBolsillos().get(bolsillo));
                System.out.println("PRESTAMO APROBADO...");
            } else {
                System.out.println("PRESTAMO RECHAZADO/CANCELADO...");
            }
        } else {
            System.out.println("PRESTAMO RECHAZADO/CANCELADO...");
        }
    }

    //OPCION 10
    private static void abonarPrestamoOMeta() {
        System.out.println("¿A que desea abonar?");
        System.out.println("1. Prestamos");
        System.out.println("2. Metas");
        System.out.println("3. Volver al inicio");
        int option = validarEntradaInt(3, true, 1, true);
        Abonable abonable;
        boolean bol;
        switch (option) {
            case 1:
                System.out.println("Seleccione un prestamo");
                bol = Utils.listarPrestamos(usuario);
                if(!bol) {return;}
                abonable = usuario.getPrestamos().get(Validador.validarEntradaInt(usuario.getPrestamos().size(), true, 1, true) - 1);
                break;
            case 2:
                System.out.println("Seleccione una meta");
                bol = Utils.listarMetas(usuario);
                if(!bol) {return;}
                abonable = usuario.getMetas().get(Validador.validarEntradaInt(usuario.getMetas().size(), true, 1, true) - 1);
                break;
            default:
                return;
        }
        System.out.println("Seleccione el Bolsillo desde el que va a abonar");
        Utils.listarBolsillos(usuario);
        Bolsillo bolsillo = usuario.getBolsillos().get(Validador.validarEntradaInt(usuario.getBolsillos().size(), true, 1, true) - 1);
        if(bolsillo.getSaldo()>0) {
        	System.out.println("Ingrese la cantidad que va a abonar (entre 0 y " + String.format("%.2f",bolsillo.getSaldo()) + " " + bolsillo.getDivisa() + "):");
            double monto = Validador.validarEntradaDouble(bolsillo.getSaldo(), true, 0, false);
            Object resp = abonable.abonar(monto, bolsillo);
            boolean bol2;
            if(resp != null) {
            	if(abonable instanceof Meta meta && resp instanceof Movimiento movimiento) {
                	System.out.println("Nuevo Saldo en la meta de: "+ String.format("%.2f",meta.getSaldo()) + " "+ meta.getDivisa());
                    System.out.println("Nuevo saldo en la cuenta origen de: "+ String.format("%.2f",bolsillo.getSaldo()) + "" + bolsillo.getDivisa());
                    System.out.println("TRM usada de: "+ String.format("%.2f",movimiento.getValorDestino()/movimiento.getValorOrigen()));
                    bol2 = meta.metaCumplida();
                    if (bol2) {
                        System.out.println("FELICIDADES HAS CUMPLIDO TU META " + meta.getNombre().toUpperCase());
                        System.out.println("Escoge un Bolsillo al cual enviar el dinero para que lo puedas usar (" + String.format("%.2f",meta.getSaldo()) + " " + meta.getDivisa() + "): ");
                        Utils.listarBolsillos(usuario);
                        int opt = Validador.validarEntradaInt(usuario.getBolsillos().size(), true, 1, true) - 1;
                        Bolsillo bolsilloDes = usuario.getBolsillos().get(opt);
                        Movimiento movimientoDes = meta.terminar(bolsilloDes);
                        System.out.println("Nuevo saldo en el bolsillo de: " + String.format("%.2f",bolsilloDes.getSaldo()) + " " + bolsilloDes.getDivisa());
                        System.out.println("TRM usada de: " + String.format("%.2f",movimientoDes.getValorDestino()/movimientoDes.getValorOrigen()));
                    }else{
                        System.out.println("Restante para cumplir la meta de: " +String.format("%.2f",meta.getObjetivo()-meta.getSaldo()));
                    }
                }else if(abonable instanceof Prestamo prestamo && resp instanceof double[] dou) {
                	System.out.println("Intereses pagados de: " + String.format("%.2f",dou[0]) + " " + prestamo.getDivisa());
                	System.out.println("Abono a Capital de: " + String.format("%.2f",dou[1]) + " " + prestamo.getDivisa());
                	System.out.println("TRM usada de: " + String.format("%.2f",dou[2]));
                	if (prestamo.getValorPagado() < prestamo.getValorInicial()) {
                        System.out.println("Te queda por pagar: " + String.format("%.2f",(prestamo.getValorInicial()-prestamo.getValorPagado())));
                        System.out.println("Se recomienda una cuota mensual de " + String.format("%.2f",prestamo.calcularCuotas()) + " " + prestamo.getDivisa());
                        System.out.println("Para poder dar cumplimiento a la fecha del cobro");
                    } else {
                        Movimiento movimiento = prestamo.terminar(usuario.getBolsillos().get(0));
                        if(movimiento != null) {
                            System.out.println("Quedaste con un saldo a favor de: " + (prestamo.getValorPagado() - prestamo.getValorInicial()));
                            System.out.println("Este se enviara a tu Bolsillo DEFAULT");
                        }
                        System.out.println("FELICIDADES PAGASTE TU PRESTAMO");
                    }
                }
            }else {
            	System.out.println("Abono Fallido");
            }
        }else {
        	System.out.println("La cuenta no existe o no contiene dinero");
        }






    }
}