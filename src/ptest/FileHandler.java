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
public class FileHandler {
    
    WordRandomizer wordRandomizer;
    String nameOfStudy  = "memorylab";
    
    public  FileHandler(WordRandomizer w){
    
        wordRandomizer = w;
        
    }
    
    
    
     public void createFile(ArrayList list) throws FileNotFoundException, UnsupportedEncodingException{
   // http://stackoverflow.com/questions/2885173/how-to-create-a-file-and-write-to-a-file-in-java
   String timestamp = nameOfStudy +System.currentTimeMillis() + ".txt";
    PrintWriter writer = new PrintWriter(timestamp);
    for(int i = 0; i < list.size(); i++){
        writer.println(list.get(i));
    }


writer.close();


// while we're here, let's mail this file

//  MailResults mailResultsRightNow = new MailResults(timestamp);



}



public void readCSVfile() throws FileNotFoundException, IOException{
    //http://stackoverflow.com/questions/13433407/java-read-csv-file-and-save-into-arrays
    BufferedReader CSVFile = 
           //new BufferedReader(new FileReader("three.csv")); 
    new BufferedReader(new FileReader("sevenLetterWords.csv"));

      String dataRow = CSVFile.readLine();
      wordRandomizer.wordArray.add(dataRow);
    //  System.out.println(dataRow);
      // Read the number of the lines in .csv file 
      // i = row of the .csv file
      //int i = 0; 
      while (dataRow != null){
        //  i++;
          dataRow = CSVFile.readLine();
          wordRandomizer.wordArray.add(dataRow);
       // System.out.println(dataRow);
        }
      
      // Close the file once all data has been read.
      CSVFile.close();

      //this is to fix a problem with the last line of the data set, problem unique to data set
      wordRandomizer.wordArray.remove(wordRandomizer.wordArray.size()-1);
     
} // end read CSV file

  
    
    
    
    
    
    
    
    
    
}
