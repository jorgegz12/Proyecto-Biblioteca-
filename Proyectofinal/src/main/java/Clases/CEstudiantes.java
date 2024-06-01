/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 *
 * @author jorge
 */
public class CEstudiantes {
  
    public void mostrarsexoCombo (ComboBox<String> comboSexo){
     Clases.CConexion objetoConexion = new Clases.CConexion();
     
     comboSexo.getItems().clear();
      comboSexo.setValue("Seleccione Sexo");
      String sql = "select * from sexo ;";
      try{
          Statement st =  objetoConexion.establecerConexion().createStatement();                                                                                                                                                                           objetoConexion.establecerConexion().createStatement();
          ResultSet rs = st.executeQuery(sql);
          
          while(rs.next()){
          int idSexo = rs.getInt("id"); 
          String nombreSexo = rs.getString("sexo");
          
          comboSexo.getItems().add(nombreSexo);
          comboSexo.getProperties().put(nombreSexo, idSexo);
          } 
      } catch (Exception e){
      showAlert("Error", "Error al mostrar sexos"+e.toString());
      }
      finally {
      objetoConexion.cerrarConexion();
      }
 }
 public void agregarEstudiante(TextField nombres, TextField apellidos, ComboBox<String> combosexo, TextField edad, DatePicker fnacimiento){
 CConexion objetoConexion = new CConexion ();
 String consulta = "insert into estudiantes (nombres, apellidos, fksexo, edad, fecha_nacimiento) values (?,?,?,?,?);";
 
 try (CallableStatement cs = objetoConexion.establecerConexion().prepareCall(consulta)) { 
    cs.setString(1, nombres.getText());
    cs.setString(2, apellidos.getText());
     
    String nombreSeleccionado = combosexo.getSelectionModel().getSelectedItem();
    int idSexo = (int)combosexo.getProperties().get(nombreSeleccionado);
    cs.setInt(3, idSexo);
    
    cs.setInt(4, Integer.parseInt(edad.getText()));
    
    LocalDate fechaSeleccionada = fnacimiento.getValue();
    Date fechaSQL = Date.valueOf(fechaSeleccionada);
    cs.setDate(5, fechaSQL);
    
    cs.execute();
    showAlert("INFORMACION","Se guardo correctamente");
            
            
 } catch (Exception e){
     showAlert("Informacion","Error al guardar  datos"+e.toString());
 }finally{
     objetoConexion.cerrarConexion();   
 }
 }
 public void MostrarEstudiantes (TableView <Object[]> TablaTotalEstudiantes ){
 Clases.CConexion objetoConexion = new Clases.CConexion();
 
 TableColumn<Object[],String> idColumn = new TableColumn<>("id"); 
 TableColumn<Object[],String> nombresColumn = new TableColumn<>("nombres"); 
 TableColumn<Object[],String> apellidosColumn = new TableColumn<>("apellidos"); 
 TableColumn<Object[],String> sexoColumn = new TableColumn<>("sexo"); 
 TableColumn<Object[],String> edadColumn = new TableColumn<>("edad"); 
 TableColumn<Object[],String> fnacimientoColumn = new TableColumn<>("fecha_nacimiento");
 
 
 idColumn.setCellValueFactory(cellData -> new SimpleStringProperty (cellData.getValue()[0].toString()));
 nombresColumn.setCellValueFactory(cellData -> new SimpleStringProperty (cellData.getValue()[1].toString()));
 apellidosColumn.setCellValueFactory(cellData -> new SimpleStringProperty (cellData.getValue()[2].toString()));
 sexoColumn.setCellValueFactory(cellData -> new SimpleStringProperty (cellData.getValue()[3].toString()));
 edadColumn.setCellValueFactory(cellData -> new SimpleStringProperty (cellData.getValue()[4].toString()));
 fnacimientoColumn.setCellValueFactory(cellData -> new SimpleStringProperty (cellData.getValue()[5].toString()));
 
 TablaTotalEstudiantes.getColumns().addAll(idColumn, nombresColumn, apellidosColumn, sexoColumn, edadColumn, fnacimientoColumn);
 
 String sql = "select estudiantes.id,estudiantes.nombres,estudiantes.apellidos, sexo.sexo,estudiantes.edad,estudiantes.fecha_nacimiento from estudiantes inner join sexo on estudiantes.fksexo = sexo.id;";
 /*String sql = "idColumn.setCellValueFactory(cellData -> new SimpleStringProperty (cellData.getValue()[0].toString()));";*/
 try {
      Statement st = objetoConexion.establecerConexion().createStatement();
      ResultSet rs = st.executeQuery(sql);
      
      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
      
      while (rs.next()){
      java.sql.Date fechaSQL = rs.getDate("fecha_nacimiento");
      String nuevaFecha = (fechaSQL != null) ? sdf.format(fechaSQL): null;
     
      Object[] rowData = {
     rs.getString("id"),
     rs.getString("nombres"),
     rs.getString("apellidos"), 
     rs.getString("sexo"),
     rs.getString("edad"),
     rs.getString("fecha_nacimiento"), nuevaFecha
       
      };
      TablaTotalEstudiantes.getItems().add(rowData);
      }
      
 } catch (Exception e){
    showAlert("error", "Erros al guardar"+e.toString());
 } finally {
     objetoConexion.cerrarConexion();
 } 
   
 }
 public void seleccionarUsuario(TableView<Object[]> TablaTotalEstudiantes,TextField id, TextField nombres, TextField apellidos, ComboBox<String> combosexo, TextField edad, DatePicker fnacimiento){

     int fila = TablaTotalEstudiantes.getSelectionModel().getSelectedIndex();
    
     if (fila >= 0){
         
     Object[] filaSeleccionada = TablaTotalEstudiantes.getItems().get(fila);
     id.setText(filaSeleccionada[0].toString());
     nombres.setText(filaSeleccionada[1].toString());
     apellidos.setText(filaSeleccionada[2].toString());
     
     combosexo.getSelectionModel().select(filaSeleccionada[3].toString());
     edad.setText(filaSeleccionada[4].toString());
      String fechaString = filaSeleccionada[5].toString();
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
     LocalDate fechaLocalDate = LocalDate.parse(fechaString,formatter);
     
      fnacimiento.setValue(fechaLocalDate);
     }
}

 
 private void showAlert(String titulo, String contenido){
 Alert alert = new Alert (Alert.AlertType.INFORMATION);
 alert.setTitle(titulo);
 alert.setHeaderText(null);
 alert.setContentText(contenido);
 alert.showAndWait();
} 
}
