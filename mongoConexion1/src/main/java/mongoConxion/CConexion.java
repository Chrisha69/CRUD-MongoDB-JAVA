package mongoConxion;


import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import java.util.List;
import javax.swing.JOptionPane;



public class CConexion {
  public MongoClient crearConexion(){
    //Datos para conexion al servidor de base de datos NR
    MongoClient mongo = null;
    String servidor = "localhost";
    Integer puerto=27017;
    
    try {
          mongo = new MongoClient(servidor,puerto);
          List<String> NombresBasesDeDatos = mongo.getDatabaseNames();
          //JOptionPane.showMessageDialog(null, "Se conecto correctamente la BD MONGO, la lista de base de datos disponibles es:"+NombresBasesDeDatos.toString());
      } catch (MongoException e) {
          JOptionPane.showMessageDialog(null, "Error de conexion"+ e.toString());
      }
    return mongo;}
  /*
   public static void mostrarActualizar() {
        String[] opciones = { "ActualizarNombre", "ActualizarCiudad", "ActualizarApellidos", "ActualizarNacimiento", "ActualizarEmail", "Salir" };
        String seleccion = (String)JOptionPane.showInputDialog(
                null,
                "Seleccione una opción",
                "Menú CRUD",
                JOptionPane.PLAIN_MESSAGE,
                null, opciones, opciones[0]);

        switch (seleccion) {
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
        }
    }
  
  public static void mostrarFormularioActualizacion(String case) {
  
  switch(case){
  case "ActualizarNombre":

        String cedula = JOptionPane.showInputDialog(null, "Ingrese la cedula del usuario a actualizar:");
        String nombre = JOptionPane.showInputDialog(null, "Ingrese el nuevo nombre:");
        
        if(!cedula.isEmpty()&&!nombre.isEmpty()){
        actualizarName(cedula, nombre);
        }
        if(nombre.isEmpty()){
            mostrarFormularioActualizacion();
        }
        break;
  case "ActualizarCiudad":
        String cedula = JOptionPane.showInputDialog(null, "Ingrese la cedula del usuario a actualizar:");
        String ciudad = JOptionPane.showInputDialog(null, "Ingrese la nueva ciudad:");
        
        if(!cedula.isEmpty()&&!ciudad.isEmpty()){
        actualizarCity(cedula, ciudad);
        }
        if(cedula.isEmpty()){
            mostrarFormularioActualizacion();
        }
        break;
  case "ActualizarApellidos":
        String cedula = JOptionPane.showInputDialog(null, "Ingrese la cedula del usuario a actualizar:");
        String apellido = JOptionPane.showInputDialog(null, "Ingrese el nuevo apellido:");
        
        if(!cedula.isEmpty()&&!apellido.isEmpty()){
        actualizarLastName(cedula, apellido);
        }
        if(cedula.isEmpty()){
            mostrarFormularioActualizacion();
        }
        break;
  case "ActualizarNacimiento":
        String cedula = JOptionPane.showInputDialog(null, "Ingrese la cedula del usuario a actualizar:");
        String nac = JOptionPane.showInputDialog(null, "Ingrese la nueva fecha");
        
        if(!cedula.isEmpty()&&!nac.isEmpty()){
        actualizarNac(cedula, nac);
        }
        if(cedula.isEmpty()){
            mostrarFormularioActualizacion();
        }
        break;
  case "ActualizarEmail":
        String cedula = JOptionPane.showInputDialog(null, "Ingrese la cedula del usuario a actualizar:");
        String email = JOptionPane.showInputDialog(null, "Ingrese el nuevo email");
        
        if(!cedula.isEmpty()&&!email.isEmpty()){
        actualizarEmail(cedula, email);
        }
        if(cedula.isEmpty()){
            mostrarFormularioActualizacion();
        }
        break;
  default:
       mostrarActualizar();
                break;
    }
  
  public static void actualizarName(String cedula, String name) {
        DBCollection colec = db.getCollection(coleccion);
        BasicDBObject updatePais = new BasicDBObject();
        updatePais.append("$set", new BasicDBObject().append("nombre", name));
        BasicDBObject buscarCedula = new BasicDBObject();
        buscarCedula.append("cedula", cedula);
        colec.updateMulti(buscarCedula, updatePais);
        JOptionPane.showMessageDialog(null, "Nombre actualizado correctamente");
    }
  
  public static void actualizarCity(String cedula, String city) {
        DBCollection colec = db.getCollection(coleccion);
        BasicDBObject updatePais = new BasicDBObject();
        updatePais.append("$set", new BasicDBObject().append("ciudad", city));
        BasicDBObject buscarCedula = new BasicDBObject();
        buscarCedula.append("cedula", cedula);
        colec.updateMulti(buscarCedula, updatePais);
        JOptionPane.showMessageDialog(null, "Ciudad actualizado correctamente");
    }
  
  public static void actualizarLastName(String cedula, String name) {
        DBCollection colec = db.getCollection(coleccion);
        BasicDBObject updatePais = new BasicDBObject();
        updatePais.append("$set", new BasicDBObject().append("apellidos", name));
        BasicDBObject buscarCedula = new BasicDBObject();
        buscarCedula.append("cedula", cedula);
        colec.updateMulti(buscarCedula, updatePais);
        JOptionPane.showMessageDialog(null, "Apellido actualizado correctamente");
    }
  
  public static void actualizarNac(String cedula, String name) {
        DBCollection colec = db.getCollection(coleccion);
        BasicDBObject updatePais = new BasicDBObject();
        updatePais.append("$set", new BasicDBObject().append("fecha de nacimiento", name));
        BasicDBObject buscarCedula = new BasicDBObject();
        buscarCedula.append("cedula", cedula);
        colec.updateMulti(buscarCedula, updatePais);
        JOptionPane.showMessageDialog(null, "Fecha actualizada correctamente");
    }
  
  public static void actualizarEmail(String cedula, String email) {
        DBCollection colec = db.getCollection(coleccion);
        BasicDBObject updatePais = new BasicDBObject();
        updatePais.append("$set", new BasicDBObject().append("email", email));
        BasicDBObject buscarCedula = new BasicDBObject();
        buscarCedula.append("cedula", cedula);
        colec.updateMulti(buscarCedula, updatePais);
        JOptionPane.showMessageDialog(null, "email actualizado correctamente");
    }
*/
}
