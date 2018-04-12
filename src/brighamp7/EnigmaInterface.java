/*******************************************************************************
*NAME: William Brigham
*EMAIL: wbrigham@cnm.edu
*PROGRAM TITLE: Enigma Encoding & Decoding Machine
*CLASS OBJECTIVE: To act as an interfaces for methods
*******************************************************************************/
package brighamp7;

public interface EnigmaInterface {
public void setCodedMessage(String codedMessage);
public void setKey(int key);
public String getMessage();
public String getCodedMessage();
public void setMessage(String message);
public int getKey();
public void decode();
public void encode();
}
