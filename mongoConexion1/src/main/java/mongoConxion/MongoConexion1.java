package mongoConxion;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import javax.swing.JOptionPane;

public class MongoConexion1 {

    private static DB db;
    private static String coleccion = "Usuario";

    public static void main(String[] args) {
        // 1. Instanciar la clase CConexion
        CConexion objetoConexion = new CConexion();
        objetoConexion.crearConexion();
        // 2. La variable mongo de MongoClient pasamos el objeto que retorna de la conexión
        MongoClient mongo = objetoConexion.crearConexion();
        // 3. Consultar si la variable mongo es vacía o tiene un valor nulo.
        if (mongo != null) {
            db = mongo.getDB("MONGODB"); // Reemplaza "nombre_de_tu_base_de_datos" con el nombre de tu base de datos
            mostrarMenu();
        } else {
            JOptionPane.showMessageDialog(null, "El objeto mongo es vacío");
        }
    }

    public static void mostrarMenu() {
        String[] opciones = {"Insertar", "Mostrar", "Buscar por nombre", "Actualizar", "Borrar", "Salir"};
        String seleccion = (String) JOptionPane.showInputDialog(
                null,
                "Seleccione una opción",
                "Menú CRUD",
                JOptionPane.PLAIN_MESSAGE,
                null, opciones, opciones[0]);

        switch (seleccion) {
            case "Insertar":
                mostrarFormularioInsercion();
                break;
            case "Mostrar":
                mostrarUsuarios();
                break;
            case "Buscar por nombre":
                mostrarFormularioBusquedaPorNombre();
                break;

            case "Actualizar":
                mostrarActualizar();
                break;
            case "Borrar":
                mostrarFormularioBorrado();
                break;
            default:
                System.exit(0);
                break;
        }
    }

    public static void mostrarFormularioInsercion() {
        String nombre = "", apellidos = "", ciudad = "", nac = "", email = "", cedula = "";
        nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del usuario:");
        cedula = JOptionPane.showInputDialog(null, "Ingrese la cedula del usuario:");
        apellidos = JOptionPane.showInputDialog(null, "Ingrese los apellidos del usuario:");
        ciudad = JOptionPane.showInputDialog(null, "Ingrese la ciudad del usuario:");
        nac = JOptionPane.showInputDialog(null, "Ingrese la fecha de nacimiento del usuario:");
        email = JOptionPane.showInputDialog(null, "Ingrese el email del usuario:");

        if (!nombre.isEmpty() && !cedula.isEmpty()) {
            insertarUsuario(nombre, apellidos, nac, ciudad, email, cedula);
        } else {
            JOptionPane.showMessageDialog(null, "No hay datos que guardar");
            mostrarMenu();
        }

    }

    public static void insertarUsuario(String nombre, String apellidos, String nac, String ciudad, String email, String cedula) {
        DBCollection colec = db.getCollection(coleccion);
        BasicDBObject documento = new BasicDBObject();
        documento.put("nombre", nombre);
        documento.put("apellidos", apellidos);
        documento.put("fecha de nacimiento", nac);
        documento.put("ciudad", ciudad);
        documento.put("email", email);
        documento.put("cedula", cedula);
        colec.insert(documento);
        JOptionPane.showMessageDialog(null, "Usuario insertado correctamente");
    }

    public static void mostrarUsuarios() {
        DBCollection colec = db.getCollection(coleccion);
        DBCursor cursor = colec.find();

        StringBuilder usuarios = new StringBuilder();
        while (cursor.hasNext()) {
            BasicDBObject usuario = (BasicDBObject) cursor.next();
            usuarios.append(usuario.getString("nombre")).append(" - ").append(usuario.getString("apellidos")).append("\n")
                    .append(usuario.getString("cedula")).append(" - ").append(usuario.getString("fecha de nacimiento")).append("\n")
                    .append(usuario.getString("ciudad")).append(" - ").append(usuario.getString("email")).append("\n");
        }

        JOptionPane.showMessageDialog(null, usuarios.toString());
        mostrarMenu();
    }

    public static void mostrarFormularioBusquedaPorNombre() {
        String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del usuario a buscar:");
        if (!nombre.isEmpty()) {
            buscarPorNombre(nombre);
        }
        JOptionPane.showMessageDialog(null, "No hay datos que buscar");
        mostrarMenu();
    }

    public static void buscarPorNombre(String nombre) {
        DBCollection colec = db.getCollection(coleccion);
        BasicDBObject consulta = new BasicDBObject();
        consulta.put("nombre", nombre);
        DBCursor cursor = colec.find(consulta);

        StringBuilder usuarios = new StringBuilder();
        while (cursor.hasNext()) {
            BasicDBObject usuario = (BasicDBObject) cursor.next();
            usuarios.append(usuario.getString("nombre")).append(" - ").append(usuario.getString("apellidos")).append("\n")
                    .append(usuario.getString("cedula")).append(" - ").append(usuario.getString("fecha de nacimiento")).append("\n")
                    .append(usuario.getString("ciudad")).append(" - ").append(usuario.getString("email")).append("\n");
        }

        JOptionPane.showMessageDialog(null, usuarios.toString());
    }

    public static void mostrarActualizar() {
        String[] opciones = {"Actualizar Nombre", "Actualizar Ciudad", "Actualizar Apellido", "Actualizar Fecha de nacimiento", "Actualizar Email", "Salir"};

        Object seleccion = JOptionPane.showInputDialog(
                null,
                "Seleccione una opción",
                "Menú CRUD",
                JOptionPane.PLAIN_MESSAGE,
                null, opciones, opciones[0]);

        if (seleccion != null) {
            int posicionSeleccion = -1;
            for (int i = 0; i < opciones.length; i++) {
                if (seleccion.equals(opciones[i])) {
                    posicionSeleccion = i;
                    break;
                }
            }
            JOptionPane.showMessageDialog(null, "Opción seleccionada: " + seleccion + "\nPosición: " + posicionSeleccion);
            /*  switch (seleccion) {
            case "ActualizarNombre":
                mostrarFormularioActualizacion(seleccion);
                break;
            case "ActualizarCiudad":
                mostrarFormularioActualizacion(seleccion);
                break;
            case "ActualizarApellidos":
                mostrarFormularioActualizacion(seleccion);
                break;

            case "ActualizarNacimiento":
                mostrarFormularioActualizacion(seleccion);
                break;
            case "ActualizarEmail":
                mostrarFormularioActualizacion(seleccion);
                break;
            default:
                System.exit(0);
                break;
        }*/
        }

    

    public static void mostrarFormularioActualizacion(String caso) {
        String cedula = JOptionPane.showInputDialog(null, "Ingrese la cedula del usuario a actualizar:");

        if (!cedula.isEmpty()) {
            switch (caso) {
                case "ActualizarNombre":
                    String nombre = JOptionPane.showInputDialog(null, "Ingrese el nuevo nombre:");
                    if (!nombre.isEmpty()) {
                        actualizarName(cedula, nombre);
                    } else {
                        JOptionPane.showMessageDialog(null, "Nombre no ingresado");
                    }
                    break;
                case "ActualizarCiudad":
                    String ciudad = JOptionPane.showInputDialog(null, "Ingrese la nueva ciudad:");
                    if (!ciudad.isEmpty()) {
                        actualizarCity(cedula, ciudad);
                    } else {
                        JOptionPane.showMessageDialog(null, "Ciudad no ingresada");
                    }
                    break;
                case "ActualizarApellidos":
                    String apellido = JOptionPane.showInputDialog(null, "Ingrese el nuevo apellido:");
                    if (!apellido.isEmpty()) {
                        actualizarLastName(cedula, apellido);
                    } else {
                        JOptionPane.showMessageDialog(null, "Apellido no ingresado");
                    }
                    break;
                case "ActualizarNacimiento":
                    String nac = JOptionPane.showInputDialog(null, "Ingrese la nueva fecha:");
                    if (!nac.isEmpty()) {
                        actualizarNac(cedula, nac);
                    } else {
                        JOptionPane.showMessageDialog(null, "Fecha de nacimiento no ingresada");
                    }
                    break;
                case "ActualizarEmail":
                    String email = JOptionPane.showInputDialog(null, "Ingrese el nuevo email:");
                    if (!email.isEmpty()) {
                        actualizarEmail(cedula, email);
                    } else {
                        JOptionPane.showMessageDialog(null, "Email no ingresado");
                    }
                    break;
                default:
                    mostrarActualizar();
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Cédula no ingresada");
        }
    }

    public static void actualizarName(String cedula, String name) {
        DBCollection colec = db.getCollection(coleccion);
        BasicDBObject updatePais = new BasicDBObject();
        updatePais.append("$set", new BasicDBObject().append("nombre", name));
        BasicDBObject buscarCedula = new BasicDBObject();
        buscarCedula.append("cedula", cedula);
        colec.updateMulti(buscarCedula, updatePais);
        JOptionPane.showMessageDialog(null, "Nombre actualizado correctamente");
        mostrarMenu();
    }

    public static void actualizarCity(String cedula, String city) {
        DBCollection colec = db.getCollection(coleccion);
        BasicDBObject updatePais = new BasicDBObject();
        updatePais.append("$set", new BasicDBObject().append("ciudad", city));
        BasicDBObject buscarCedula = new BasicDBObject();
        buscarCedula.append("cedula", cedula);
        colec.updateMulti(buscarCedula, updatePais);
        JOptionPane.showMessageDialog(null, "Ciudad actualizado correctamente");
        mostrarMenu();
    }

    public static void actualizarLastName(String cedula, String name) {
        DBCollection colec = db.getCollection(coleccion);
        BasicDBObject updatePais = new BasicDBObject();
        updatePais.append("$set", new BasicDBObject().append("apellidos", name));
        BasicDBObject buscarCedula = new BasicDBObject();
        buscarCedula.append("cedula", cedula);
        colec.updateMulti(buscarCedula, updatePais);
        JOptionPane.showMessageDialog(null, "Apellido actualizado correctamente");
        mostrarMenu();
    }

    public static void actualizarNac(String cedula, String name) {
        DBCollection colec = db.getCollection(coleccion);
        BasicDBObject updatePais = new BasicDBObject();
        updatePais.append("$set", new BasicDBObject().append("fecha de nacimiento", name));
        BasicDBObject buscarCedula = new BasicDBObject();
        buscarCedula.append("cedula", cedula);
        colec.updateMulti(buscarCedula, updatePais);
        JOptionPane.showMessageDialog(null, "Fecha actualizada correctamente");
        mostrarMenu();
    }

    public static void actualizarEmail(String cedula, String email) {
        DBCollection colec = db.getCollection(coleccion);
        BasicDBObject updatePais = new BasicDBObject();
        updatePais.append("$set", new BasicDBObject().append("email", email));
        BasicDBObject buscarCedula = new BasicDBObject();
        buscarCedula.append("cedula", cedula);
        colec.updateMulti(buscarCedula, updatePais);
        JOptionPane.showMessageDialog(null, "email actualizado correctamente");
        mostrarMenu();
    }

    public static void mostrarFormularioBorrado() {
        String nombre = JOptionPane.showInputDialog(null, "Ingrese la cedula del usuario a borrar:");
        if (!nombre.isEmpty()) {
            borrarUsuario(nombre);
        } else {
            mostrarFormularioBorrado();
        }
    }

    public static void borrarUsuario(String nombre) {
        DBCollection colec = db.getCollection(coleccion);
        colec.remove(new BasicDBObject().append("cedula", nombre));
        JOptionPane.showMessageDialog(null, "Usuario borrado correctamente");
        mostrarMenu();
    }
}
