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
    return mongo;
  }
}
