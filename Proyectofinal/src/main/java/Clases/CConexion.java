/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;
import java.sql.Connection;
import java.sql.DriverManager;
import javafx.scene.control.Alert;
/**
 *
 * @author jorge
 */
public class CConexion {
 Connection conectar = null;
 
 String url = "jdbc:postgresql://localhost:5432/proyectoBD";
 String usuario = "postgres";
 String clave = "dogy2024"; 
 String puerto = "5432";
 String ip = "localhost";
 String bd = "proyectoBD";
         
 public Connection establecerConexion(){
 try{
     Class.forName("org.postgresql.Driver");
     conectar = DriverManager.getConnection(url, usuario, clave);
     showAlert("mensaje", "Se conecto a la base de datos");
 } catch (Exception e){
 showAlert("mensaje", "No se conecto a la base de datos, error:"+e.toString());
 }
 return conectar; 
 }
  private void showAlert(String titulo, String contenido){
 Alert alert = new Alert (Alert.AlertType.INFORMATION);
 alert.setTitle(titulo);
 alert.setHeaderText(null);
 alert.setContentText(contenido);
 alert.showAndWait();
}
 
 public void cerrarConexion (){
 try {
      if (conectar != null && !conectar.isClosed()){
          conectar.close();
          showAlert("mensaje", "Conexion cerrada");
      } 
 } catch (Exception e) {
   showAlert("mensaje", "Error al cerrar conexion"+e.toString());  
 }
 }   
}
