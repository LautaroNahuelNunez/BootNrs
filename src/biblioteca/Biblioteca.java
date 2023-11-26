package biblioteca;

import java.util.*;

public class Biblioteca {
    private static ArrayList<Libro> libros;
    private static ArrayList<Usuario> usuarios;

    public Biblioteca() {
        this.libros = new ArrayList<>();
        this.usuarios = new ArrayList<>();
    }
    
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        menuPrincipal();
        
    }
    
    private static void menuPrincipal(){    //menu con las opciones iniciales

        int opcion;
        
        do {
            System.out.println("MENU PRINCIPAL");
            System.out.println("1 - Agregar un usuario nuevo");
            System.out.println("2 - Ya soy usuario");
            System.out.println("3 - SALIR DEL PROGRAMA");
            System.out.println(" ");
            System.out.print("Seleccione una opcion: ");
            
            if(scanner.hasNextInt()){   //chequeo que la opcion sea un numero
                opcion = scanner.nextInt();
                scanner.nextLine(); //Saco el salto de línea que queda en el buffer

                switch (opcion) { //el IDE me recomendó usar el "rule switch" que no lo conocía, pero es una especie de visión más limpia del código, sin usar el break y usando el operador flecha
                    case 1 -> registrarUsuarioNuevo();
                    case 2 -> validarUserExistente();
                    case 3 -> System.out.println("Saliendo...");
                    default -> System.out.println("LA OPCION INGRESADA NO ES UN NUMERO VALIDO, INTENTE NUEVAMENTE... \n");
                            
                }
            } else {
                System.out.println("LA OPCION INGRESADA NO ES UN NUMERO, INTENTE NUEVAMENTE...\n");
                scanner.nextLine(); //Limpio el buffer
                opcion = 0;
            }
            
        } while (opcion != 3);
        
        scanner.close(); //Cierro el flujo del scanner
    
    }
    
    
    private static void registrarUsuarioNuevo(){
    
        System.out.println(" ");
        System.out.print("Registre el nombre: ");
        String nombre = scanner.nextLine().toUpperCase(); //tomo los nombres y los paso a letra mayuscula
        
        System.out.print("Registre el DNI (8 digitos sin puntos): ");
        String dniStr = scanner.nextLine();
        
        if(validarNombre(nombre) && validarDni(dniStr)){
            int dni = Integer.parseInt(dniStr); // Parseo de String a int el numero de DNI
            Usuario nuevoUser = new Usuario(nombre, dni); // Creo al user con los respectivos datos brindados
            usuarios.add(nuevoUser);    // Agrego al user
            System.out.println(" ");
            System.out.println("Usuario registrado con exito."); // Mensaje exitoso
            System.out.println(" ");
        } else {
            System.out.println("Ocurrio un error con alguno de los datos. El usuario no pudo ser registrado."); // si hay algun error de validación, no se agrega la persona
            System.out.println(" ");
        }
    }
    
    private static boolean validarNombre (String nombre){
        return !nombre.isBlank() && nombre.matches("[a-zA-ZñÑ]+"); //Valido que no esté vacío ni contenga números
    }
    
    private static boolean validarDni(String dniStr){ // Método para corroborar que el DNI tenga 8 digitos
        return dniStr.length() == 8 && dniStr.matches("\\d{8}"); //Corroboro que tenga 8 dígitos, y con matches verifico si son dígitos ("\\d" para representar digitos y {8} para indicar que son 8 exactamente)
    }


    private static void validarUserExistente(){
    
        System.out.println(" ");
        System.out.print("Ingrese con su DNI (8 digitos sin puntos): ");
        String dniStr = scanner.nextLine();
        
        if (validarDni(dniStr)){
            int dni = Integer.parseInt(dniStr);
            if (buscarUsuarioPorDni(usuarios, dni)){
                System.out.println(" ");
                System.out.println("Bienvenido...");
                System.out.println(" ");
                menuUsuario();
            } else {
                System.out.println("Usuario no registrado, registrelo para poder continuar...");
                System.out.println(" ");
            }
            
        } else {
            System.out.println("El DNI no es numerico o no posee 8 digitos. Intente nuevamente."); // si hay algun error de validación, no se agrega la persona
            System.out.println(" ");
        }
        
    }
    
    private static boolean buscarUsuarioPorDni(ArrayList<Usuario> usuarios, int dni){
        for (Usuario usuario : usuarios) {
            if (usuario.getDni() == dni) {
                return true; // Se encontró el usuario
            }
        }
        return false; // No se encontró el usuario
    }
    
    private static void menuUsuario(){

        int opcionUser;
        
        do {
            System.out.println("MENU DE USUARIO");
            System.out.println("1 - Agregar un libro");
            System.out.println("2 - Eliminar un libro");
            System.out.println("3 - Alquilar un libro");
            System.out.println("4 - Devolver un libro");
            System.out.println("5 - Buscar un libro");
            System.out.println("6 - Mostrar los libros");
            System.out.println("7 - Volver al menu principal");
            System.out.println("8 - SALIR DEL PROGRAMA");
            System.out.println(" ");
            System.out.print("Seleccione una opcion: ");
            
            if(scanner.hasNextInt()){   //chequeo que la opcion sea un numero
                opcionUser = scanner.nextInt();
                scanner.nextLine(); //Saco el salto de línea que queda en el buffer

                switch (opcionUser) { //el IDE me recomendó usar el "rule switch" que no lo conocía, pero es una especie de visión más limpia del código, sin usar el break y usando el operador flecha
                    case 1 -> agregarLibro();
                    case 2 -> eliminarLibro();
                    case 3 -> alquilarLibro();
                    case 4 -> devolverLibro();
                    case 5 -> menuBuscarLibro();
                    case 6 -> listarLibros();
                    case 7 -> System.out.println(" "); 
                    case 8 -> {
                        System.out.println("Saliendo...");
                        System.exit(0);
                    }
                    default -> System.out.println("LA OPCION INGRESADA NO ES UN NUMERO VALIDO, INTENTE NUEVAMENTE... \n");
                            
                }
            } else {
                System.out.println("LA OPCION INGRESADA NO ES UN NUMERO, INTENTE NUEVAMENTE...\n");
                scanner.nextLine(); //Limpio el buffer
                opcionUser = 0;
            }
            
        } while (opcionUser != 7);
    
    }
    
    
    private static void agregarLibro(){
    
        System.out.println(" ");
        System.out.print("Ingrese el titulo del libro: ");
        String titulo = scanner.nextLine().toUpperCase();
        
        System.out.print("Ingrese el nombre del autor del libro: ");
        String autor = scanner.nextLine().toUpperCase();
        
        System.out.print("Ingrese el genero del libro: ");
        String genero = scanner.nextLine().toUpperCase();
        
        boolean disponible = true;
        
        if (campoRellenado(titulo) && campoRellenado(autor) && campoRellenado(genero)){ //corroboro que los 3 campos no estén en blanco
            if(!libroRepetido(libros, titulo)){      
                Libro nuevoLibro = new Libro(titulo, autor, genero, disponible); 
                libros.add(nuevoLibro);    // Agrego el libro
                System.out.println(" ");
                System.out.println("Libro agregado con exito."); // Mensaje exitoso
                System.out.println(" ");           
            } else {
                System.out.println("El libro que intentas agregar ya existe, intenta con otro");
                System.out.println(" "); 
            } 
        } else {
            System.out.println("Alguno de los datos estaba en blanco. El libro no pudo ser agregado."); // si hay algun error de validación, no se agrega la persona
            System.out.println(" ");
        }
    }
    
    private static boolean campoRellenado(String palabra){
        return !palabra.isBlank();
    }
    
    private static boolean libroRepetido (ArrayList<Libro> libros, String titulo){
        for (Libro libro : libros) {
            if (libro.getTitulo().equals(titulo)) {
                return true; // Se encontró el titulo
            }
        }
        return false; // No se encontró el titulo
    }
    
    private static void eliminarLibro(){
    
        System.out.println(" ");
        System.out.print("Ingrese el titulo del libro a eliminar: ");
        String titulo = scanner.nextLine().toUpperCase();
        
        for (Libro libro : libros){
            if (libro.getTitulo().equals(titulo)){
                //Eliminamos el encontrado
                libros.remove(libro);
                System.out.println(" ");
                System.out.println("Libro '" + titulo + "' eliminado con exito");
                System.out.println(" ");
                return;
            } 
        }
        System.out.println(" ");
        System.out.println("Libro '" + titulo + "' no encontrado en la lista");
        System.out.println(" ");
    }
    
    private static void listarLibros(){
        for (Libro libro : libros){
            System.out.println(libro);
        }
    }
    
    private static void alquilarLibro(){
    
        System.out.println(" ");
        System.out.print("Ingrese el titulo del libro que quisiera alquilar: ");
        String titulo = scanner.nextLine().toUpperCase();
        
        for (Libro libro : libros){
            if (libro.getTitulo().equals(titulo) && libro.isDisponible()){
                //cambiamos a false el atributo de disponible
                libro.setDisponible(false);
                System.out.println(" ");
                System.out.println("Libro '" + titulo + "' alquilado con exito");
                System.out.println(" ");
                return;
            } 
        }
        System.out.println(" ");
        System.out.println("Libro '" + titulo + "' no encontrado o alquilado. No se pudo alquilar");
        System.out.println(" ");
        
    }
    
    private static void devolverLibro(){
    
    System.out.println(" ");
        System.out.print("Ingrese el titulo del libro que quisiera devolver: ");
        String titulo = scanner.nextLine().toUpperCase();
        
        for (Libro libro : libros){
            if (libro.getTitulo().equals(titulo) && !libro.isDisponible()){
                //cambiamos a true el atributo de disponible
                libro.setDisponible(true);
                System.out.println(" ");
                System.out.println("Libro '" + titulo + "' devuelto con exito");
                System.out.println(" ");
                return;
            } 
        }
        System.out.println(" ");
        System.out.println("Libro '" + titulo + "' no encontrado o ya existente. No se pudo devolver");
        System.out.println(" ");
        
    }
        
    private static void menuBuscarLibro(){
    
        int opcion;
        
        do {
            System.out.println("MENU BUSCADOR");
            System.out.println("1 - Buscar por titulo");
            System.out.println("2 - Buscar por autor");
            System.out.println("3 - Buscar por genero");
            System.out.println("4 - Volver al menu de usuarios");
            System.out.println("5 - SALIR DEL PROGRAMA");
            System.out.println(" ");
            System.out.print("Seleccione una opcion: ");
            
            if(scanner.hasNextInt()){   //chequeo que la opcion sea un numero
                opcion = scanner.nextInt();
                scanner.nextLine(); //Saco el salto de línea que queda en el buffer

                switch (opcion) { //el IDE me recomendó usar el "rule switch" que no lo conocía, pero es una especie de visión más limpia del código, sin usar el break y usando el operador flecha
                    case 1 -> busquedaPorTitulo();
                    case 2 -> busquedaPorAutor();
                    case 3 -> busquedaPorGenero();
                    case 4 -> System.out.println(" ");
                    case 5 -> {
                        System.out.println("Saliendo...");
                        System.exit(0);
                    }
                    default -> System.out.println("LA OPCION INGRESADA NO ES UN NUMERO VALIDO, INTENTE NUEVAMENTE... \n");
                            
                }
            } else {
                System.out.println("LA OPCION INGRESADA NO ES UN NUMERO, INTENTE NUEVAMENTE...\n");
                scanner.nextLine(); //Limpio el buffer
                opcion = 0;
            }
            
        } while (opcion != 4);
    
    }
    
    private static void busquedaPorTitulo(){
        System.out.println(" ");
        System.out.print("Ingrese el titulo del libro que quisiera buscar: ");
        String titulo = scanner.nextLine().toUpperCase();
        
        for (Libro libro : libros){
            if (libro.getTitulo().equals(titulo)){
                System.out.println(libro);
                return; //vuelvo porque no hay titulos repetidos
            } 
        }
        System.out.println(" ");
        System.out.println("No hay libros para mostrar");
        System.out.println(" ");
    }
    
    private static void busquedaPorAutor(){
        System.out.println(" ");
        System.out.print("Ingrese el autor que quisiera buscar: ");
        String autor = scanner.nextLine().toUpperCase();
        int flag = 0;
        
        for (Libro libro : libros){
            if (libro.getAutor().equals(autor)){
                System.out.println(libro);
                flag = 1;
            } 
        }
        System.out.println(" ");
        if (flag == 0){
            System.out.println("No hay libros para mostrar");
            System.out.println(" ");
            return;
        } 
        System.out.println(" ");
    }
    
    private static void busquedaPorGenero(){
        System.out.println(" ");
        System.out.print("Ingrese el genero que quisiera buscar: ");
        String genero = scanner.nextLine().toUpperCase();
        int flag = 0;
        
        for (Libro libro : libros){
            if (libro.getGenero().equals(genero)){
                System.out.println(libro);
                flag = 1;
            } 
        }
        
        System.out.println(" ");
        if (flag == 0){
            System.out.println("No hay libros para mostrar");
            System.out.println(" ");
            return;
        }
        System.out.println(" ");
    }
    
    
}
