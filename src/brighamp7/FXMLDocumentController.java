
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
    
    @FXML private TextField txtKey;
    @FXML private TextField txtMessage;
    @FXML private TextField txtCodedMessage;
    @FXML private RadioButton rb2;
    @FXML private TextField txtEnigmaKey;
    @FXML private Button btnDecode;
    private Enigma enigma = new Enigma();
    
    
    @FXML
     private void handleMenuOpenAction(ActionEvent event){
        String codedMessage;
        int key;
        
                FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.setTitle("Open a Coded Message and key in a File");
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
              // String skey = inputFile.nextLine();  
               //key = Integer.parseInt(skey);
               txtCodedMessage.setText(codedMessage);
               txtKey.setText(key + "");
               inputFile.close();
                
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        //enigma.setKey(Integer.valueOf(txtEnigmaKey.getText()));
        enigma.setKey(Integer.valueOf(txtKey.getText()));  //need to change where the key goes
        
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
          JOptionPane.showMessageDialog(null,"Enigma Encoding & "
            + "Decoding Machine v1.8", "Enigma Encryption Macnine", 
            JOptionPane.PLAIN_MESSAGE);
    }
    
    @FXML
    private void handleMenuSave(ActionEvent event){
        
        String codedMessage;
        int key;
        
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("."));
        fileChooser.setTitle("Save a Coded Message in a File");
        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "text files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);         
                //Show the Save File Dialog
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
        // TODO
    }    
    
}
