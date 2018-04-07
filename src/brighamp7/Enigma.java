/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brighamp7;

import java.util.Random;

/**
 *
 * @author wbrigham
 */
public class Enigma implements EnigmaInterface{
    
    private String message, codedMessage;
    private int key = -1;
    private Random randNumber;
    
    public Enigma(){
        
        randNumber = new Random();
       
    }
    
    //setters
   /* @override
    public void getMessage (String mess){
        String Message = message;
        
        //generate random num
        
        encode();

    }*/
    
    public void encode(){
        if(key == -1){
            key = randNumber.nextInt(49) + 1;  
        }

        
        StringBuilder sb = new StringBuilder();
      
      
        for(int i = 0; i < message.length(); i++){
            if(message.charAt(i) + key > 126){
                int wrappedKey = message.charAt(i) + key - 95;
                
                sb.append((char) wrappedKey);
            } else{
                int wrappedKey = message.charAt(i) + key;
                sb.append( (char) wrappedKey);
            }
        }
        codedMessage = sb.toString();
        
        //for loop
        //cast each char to an int
        //add key
        //check if value  >126 if so wrap
        //wrap.ifValue = 127-->32   128-->33  etc
        //convert back to char
        //modify or build coded message
    }
   
    
    public void decode() {
         //for loop
        //cast character into int
        //subtract key
        //check if <32 if so, 31--> 126  30-->125 etc
        StringBuilder sb = new StringBuilder();
        
            for(int i = 0; i < codedMessage.length(); i++){
            if(codedMessage.charAt(i) - key < 32){
                int wrappedKey = codedMessage.charAt(i) - key + 95;
                
                sb.append((char) wrappedKey);
            } else{
                int wrappedKey = codedMessage.charAt(i) - key;
                sb.append( (char) wrappedKey);
            }
        }
        message = sb.toString();
        
    }
    
    public int getKey() {
        return key;
    }
    
    public void setMessage(String message){
        this.message = message;
    }

    public String getCodedMessage(){
        return codedMessage;
    }
    
    public String getMessage(){
        return message;
    }
    
    
    public void setKey(int key){
    this.key = key;  
    }
    
    public void setCodedMessage(String codedMessage){
        this.codedMessage = codedMessage;
    }
    
}
