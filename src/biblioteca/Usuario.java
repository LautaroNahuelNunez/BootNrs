package biblioteca;

public class Usuario implements Notificable{
    private String nombre;
    private int dni;

    public Usuario(String nombre, int dni) {
        this.nombre = nombre;
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    @Override
    public String toString() {
        return "Usuario{" + "nombre=" + nombre + ", dni=" + dni + '}';
    }

    @Override
    public void enviarSaludo() {
        System.out.println("Bienvenido " + nombre);
    }
}
