/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.proyectofinal;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author jorge
 */
public class FXprincipalController implements Initializable {

   @FXML
    public ComboBox <String> cbsexo = new ComboBox<>();
    
    @FXML
    public TextField txtnombres;
    
    @FXML
    public TextField txtapellido;
    
    @FXML
    public TextField txtedad;
    
    @FXML
    public DatePicker datenacimiento;
    
    @FXML
    public TableView<Object[]> tbEstudiantes;
    
    @FXML
    public TextField txtid;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*Clases.CConexion objetoConexion = new Clases.CConexion();
        objetoConexion.establecerConexion();*/
        
        Clases.CEstudiantes objetoEstudiante = new Clases.CEstudiantes();
        objetoEstudiante.mostrarsexoCombo(cbsexo);
        objetoEstudiante.MostrarEstudiantes(tbEstudiantes);
    } 
    
    @FXML
    public void guardarEstudiante (ActionEvent event){
    Clases.CEstudiantes objetoestudiante = new Clases.CEstudiantes();
    objetoestudiante.agregarEstudiante(txtnombres, txtapellido, cbsexo, txtedad, datenacimiento);
    
    }
    @FXML 
    public void seleccionarUsuario(MouseEvent event){
     Clases.CEstudiantes objetoestudiante = new Clases.CEstudiantes();
     objetoestudiante.seleccionarUsuario(tbEstudiantes,   txtid, txtnombres, txtapellido, cbsexo, txtedad, datenacimiento);
    }
}