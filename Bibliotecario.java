
package modelo;

import interfaces.Administrable;

public class Bibliotecario extends Usuario implements Administrable {

    public Bibliotecario(int id, String nombre,
                         String rut, String correo) {
        super(id, nombre, rut, correo);
    }

    @Override
    public String getRol() {
        return "bibliotecario";
    }

    @Override
    public boolean puedeAdministrar() {
        return true;
    }
}
