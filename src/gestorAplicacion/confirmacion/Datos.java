package gestorAplicacion.confirmacion;

import java.io.Serial;
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
@Serial
private static final long serialVersionUID = 0;
private static List<Usuario> usuarios = new ArrayList<>();

static {
    Deserializador.deserializar();
}

//Getters and setters

public static Usuario getUsuarios() {
    try {
    return usuarios.get(1);

    } catch (Exception e) {
        
        System.out.println("Error getting users: "+ e.getMessage());
    }
    return null;
}


    
}
