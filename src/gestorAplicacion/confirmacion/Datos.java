package gestorAplicacion.confirmacion;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

import baseDatos.Deserializador;
import gestorAplicacion.Usuario;


public class Datos implements Serializable {

    public static String[][] filesList = {
        // 1. name of file, 2.setterName, 3. getterName
        {"usuarios.txt", "setUsuarios", "getUsuarios"}
};
private static final long serialVersionUID = 2979265545810011076L;
private static List<Usuario> usuarios = new ArrayList<Usuario>();

static {
    Deserializador.deserializar();
}

//Getters and setters

public static Usuario getUsuarios() {
    return usuarios.get(1);
}


    
}
