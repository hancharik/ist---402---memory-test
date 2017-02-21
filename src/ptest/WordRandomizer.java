/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ptest;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 *
 * @author mark
 */
public class WordRandomizer {
    
    
     ArrayList<String> wordArray = new ArrayList();
    FileHandler fileHandler;
    
   public  WordRandomizer() throws IOException{
    
       fileHandler = new FileHandler(this);
       //createFile();
    fileHandler.readCSVfile();
    //showWords();
}// end constructor 
 

public String getRandomWord(){
    
   
    int randomInt = (int)(Math.random() * wordArray.size()); // wordArray.size()-1 to be safe, i don't think it's needed
    
    String randomWord = wordArray.get(randomInt);  
     // once we use the word, we remove it from the list
   wordArray.remove(randomInt);
    
    // once we use the word, we remove it from the list
   //String randomWord = "cat";
    //System.out.println("arraylist size (words left in list) : " + wordArray.size());
    
   return randomWord; 
}


 
public void showWords(){
    
    for(int i = 0; i < wordArray.size(); i++ ){
        
        System.out.println(wordArray.get(i));
    }
    
    
}




} // end class
