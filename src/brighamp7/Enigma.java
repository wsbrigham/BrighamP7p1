/*******************************************************************************
*NAME: William Brigham
*EMAIL: wbrigham@cnm.edu
*PROGRAM TITLE: Enigma Encoding & Decoding Machine
*CLASS OBJECTIVE: To manage the encoding/decoding operations
*******************************************************************************/
package brighamp7;

import java.util.Random;

public class Enigma implements EnigmaInterface{
    
    private String message, codedMessage; //for storing decoded/encoded messages
    private int key = -1;                 //for storing the encryption key
    private Random randNumber;            //for storing random encryption key
    
    /*
    *random number method
    */
    public Enigma(){       
        randNumber = new Random();       
    }
    
    /*
    *encode method
    */
    public void encode(){
        if(key == -1){
            key = randNumber.nextInt(49) + 1;  
        }

        StringBuilder sb = new StringBuilder();
          
        /*
        *if a character code is >126 after adding the encryption key, subtract 
        *95 to wrap
        */
        for(int i = 0; i < message.length(); i++){
            if(message.charAt(i) + key > 126){
                int wrappedKey = message.charAt(i) + key - 95;
                
                sb.append((char) wrappedKey);
            
            /*
            *if a character code ins't > 126 after adding the encryption key
            */
            } else{
                int wrappedKey = message.charAt(i) + key;
                sb.append( (char) wrappedKey);
            }
        }
        /*
        *case coded message to a string
        */
        codedMessage = sb.toString();
    }
   
    /*
    *decode method
    */
    public void decode() {

        /*
        *create a new string builder
        */
        StringBuilder sb = new StringBuilder();
        
            for(int i = 0; i < codedMessage.length(); i++){
                
            /*
            *if a character is < 32 after subtracting the encryption key.
            *add 95 to unwrap
            */
            if(codedMessage.charAt(i) - key < 32){
                int wrappedKey = codedMessage.charAt(i) - key + 95;
                
                sb.append((char) wrappedKey);
                
                /*
                *if a character code is > 32 after subtracting the encryption,
                *key, decode as normal.
                */
            } else{
                int wrappedKey = codedMessage.charAt(i) - key;
                sb.append( (char) wrappedKey);
            }
        }
            
        /*
        *cast decoded message to a string
        */
        message = sb.toString();       
    }
    
    /*
    *get encryption key method
    */
    public int getKey() {
        return key;
    }
    
    /*
    *set decoded message
    */
    public void setMessage(String message){
        this.message = message;
    }

    /*
    *get coded message
    */
    public String getCodedMessage(){
        return codedMessage;
    }
    
    /*
    *get decoded message
    */
    public String getMessage(){
        return message;
    }
   
    /*
    *set encryption key
    */    
    public void setKey(int key){
    this.key = key;  
    }
    
    /*
    *set encoded message
    */
    public void setCodedMessage(String codedMessage){
        this.codedMessage = codedMessage;
    }   
}
