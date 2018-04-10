/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brighamp7;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.swing.JFileChooser;

/**
 *
 * @author wbrigham
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML private TextField txtKey;
    @FXML private TextField txtMessage;
    @FXML private TextField txtCodedMessage;
    @FXML private RadioButton rb2;
    @FXML private TextField txtEnigmaKey;
    @FXML private Button btnDecode;


    
    private Enigma enigma = new Enigma();
    
    //txtEnigmaKey.setVisible (false);

    @FXML
    private void handleMenuSaveAction(ActionEvent event){
        
    }
    
    @FXML
     private void handleMenuOpenAction(ActionEvent event){
        String codedMessage;
        int key;
         
	JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Open A File to be Decoded");       
        fileChooser.setCurrentDirectory(new java.io.File("."));
        String filename = "";
      
        int status = fileChooser.showOpenDialog(null);
        if(status == JFileChooser.APPROVE_OPTION)
        {
            try {               //read in the coded message and other input               
                File selectedFile = fileChooser.getSelectedFile();
                filename = selectedFile.getPath();
                File myFile = new File(filename);
                Scanner inputFile = new Scanner(myFile);
               
                codedMessage = inputFile.nextLine();              
                key = inputFile.nextInt();
                

                inputFile.close();
                
                txtEnigmaKey.setText(Integer.toString(key));
                txtCodedMessage.setText(codedMessage);
              
            } catch (IOException ex) {
                   Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);            }
        }

    }
     @FXML
    private void handleGeneratedKeyAction(ActionEvent event){
        btnDecode.setDisable(true);
        
    }
    
    @FXML
    public void handleEnterKeyAction(ActionEvent event){
        txtEnigmaKey.requestFocus();
    }
    
    @FXML
    private void handleDecodeAction(ActionEvent event){
        enigma.setCodedMessage(txtCodedMessage.getText());
        enigma.setKey(Integer.valueOf(txtEnigmaKey.getText()));
        enigma.decode();
        txtMessage.setText(enigma.getMessage());
    }
    
    @FXML
    private void handleEncodeAction(ActionEvent event){
        if(rb2.isSelected()){
            enigma.setKey(Integer.valueOf(txtEnigmaKey.getText()));
        }
        enigma.setMessage(txtMessage.getText());
        enigma.encode();
        txtKey.setText(String.valueOf(enigma.getKey()));
        txtCodedMessage.setText(enigma.getCodedMessage());
        
    }
    @FXML
    private void handleClearAction(ActionEvent event){
        txtEnigmaKey.setText("");
        txtMessage.setText("");
        txtCodedMessage.setText("");
        txtKey.setText("");
        //btnDecode.setDisable(true);
    }   
    
    @FXML
    private void handleMenuAboutAction(ActionEvent event){
        
    }
    
    @FXML
    private void handleMenuSave(ActionEvent event){
		
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new java.io.File("."));
        fileChooser.setDialogTitle("Save Encoded Message");
        int status = fileChooser.showSaveDialog(null);
        String filename = "";

        if(status == JFileChooser.APPROVE_OPTION)
        {
            PrintWriter outputFile = null;
            try {
                
                File selectedFile = fileChooser.getSelectedFile();
                filename = selectedFile.getPath();  
                outputFile = new PrintWriter(filename);
                outputFile.println(enigma.getCodedMessage());             
                outputFile.println(enigma.getKey());                

               outputFile.close();
            } catch (IOException ex) {
		Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);            
		}
        }

        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
