/*******************************************************************************
*NAME: William Brigham
*EMAIL: wbrigham@cnm.edu
*PROGRAM TITLE: Enigma Encoding & Decoding Machine
*CLASS OBJECTIVE: To drive the user interface
*******************************************************************************/
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
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;

public class FXMLDocumentController implements Initializable {
    
    @FXML private TextField txtKey;           //for the encryption key field
    @FXML private TextField txtMessage;       //for the decoded message field
    @FXML private TextField txtCodedMessage;  //for the coded message field
    @FXML private RadioButton rb2;            //for the enter a key radio dial
    @FXML private TextField txtEnigmaKey;     //for the encryption key
    @FXML private Button btnDecode;           //for the decode button
 
    
    private Enigma enigma = new Enigma();
    
    /*
    *event handler for File>Open
    */
    @FXML
     private void handleMenuOpenAction(ActionEvent event){
         
        String codedMessage; //for the coded message
        int key;             //for the encryption key 
        
        /*
        *create new file chooser
        */
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.setTitle("Open a Coded Message and Key from a File");
        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "text files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);         
        
        //Show the Open File Dialog
        File file = fileChooser.showOpenDialog(null);  
        
        if(file != null)
        {
            try {
                String filename = file.getCanonicalPath(); 
                File myFile = new File(filename);
                Scanner inputFile = new Scanner(myFile);
        	   
               codedMessage = inputFile.nextLine();
               key = inputFile.nextInt();

               txtCodedMessage.setText(codedMessage);
               txtKey.setText(key + "");
               inputFile.close();
                
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
     /*
     *event handler for random, automatic encryption key creation
     */
     @FXML
    private void handleGeneratedKeyAction(ActionEvent event){
            
    }
    
    /*
    *event handler for manually entering an encryption key
    */
    @FXML
    public void handleEnterKeyAction(ActionEvent event){
        txtEnigmaKey.requestFocus();
    }
    
    /*
    *event handler for the Decode button
    */
    @FXML
    private void handleDecodeAction(ActionEvent event){
        enigma.setCodedMessage(txtCodedMessage.getText());
        enigma.setKey(Integer.valueOf(txtKey.getText()));       
        enigma.decode();
        txtMessage.setText(enigma.getMessage());
    }
    
    /*
    *event handler for the Encode button
    */
    @FXML
    private void handleEncodeAction(ActionEvent event){

        boolean valuesEntered = true;
        
        /*if manually entering an encryption key, get the key from the encryption key field
        *and set the key
        */
        if(rb2.isSelected()){
            
          try{ 
            enigma.setKey(Integer.valueOf(txtEnigmaKey.getText()));
          }
           catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Error\n\nYou Didn't input a "
                + "value into the Enter a Key field.",
                "Enigma Encryption Machine", JOptionPane.ERROR_MESSAGE);
            
            valuesEntered = false;
           }
        }
        


        if(valuesEntered == true){
        
            /*
            *get the message from the message field and set it into message
            */
            enigma.setMessage(txtMessage.getText());

            /*
            *call the method to encode the message
            */
            enigma.encode();

            /*
            *get the the value of the key from the enigma class and set that value 
            *in the Key field
            */
            txtKey.setText(String.valueOf(enigma.getKey()));

            /*
            *get the coded message from the enigma class and set that value in the 
            *Coded Message field
            */
            txtCodedMessage.setText(enigma.getCodedMessage());
        }
    }
    
    /*
    *event handler for the Clear button which clears all the data in the form
    */
    @FXML
    private void handleClearAction(ActionEvent event){
        txtEnigmaKey.setText("");
        txtMessage.setText("");
        txtCodedMessage.setText("");
        txtKey.setText("");
    }   
    
    /*
    *event handler for  About Enigma>About which displays a panel with 
    *version information
    */
    @FXML
    private void handleMenuAboutAction(ActionEvent event){
          JOptionPane.showMessageDialog(null,"Enigma Encoding & "
            + "Decoding Machine v1.8", "Enigma Encryption Macnine", 
            JOptionPane.PLAIN_MESSAGE);
    }
    
    /*
    *event handler for File>Save File menu item
    */
    @FXML
    private void handleMenuSave(ActionEvent event){
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.setTitle("Save Coded Message to a File");
        
        /*
        *Set extension filter
        */
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "text files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);  
        
        /*
        *Show the Save File Dialog
        */
        File file = fileChooser.showSaveDialog(null); 
        
        if(file != null)
        {
            PrintWriter outputFile = null;
            try {
                
                String filename = file.getCanonicalPath();
                File myFile = new File(filename);
                outputFile = new PrintWriter(myFile);
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
    } 
}
