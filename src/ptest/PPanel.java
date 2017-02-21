/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ptest;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author mark
 */
public class PPanel extends JPanel implements ActionListener {

    int buttonNumber = 1;
    int randomNumber = 1;
    int height = 600;//720
    int width = 1000;//1280
    int rows = 3;//9
    int columns = 3;//5

    int errors = 0;
    int delay = 8; //delay in ms
    int counter = 0;
    int numberOfWords = 11;

    long unixTimeStart;
    long unixTimeEnd;
    long totalTime;

    MainPanel main;
    
    int temporaryArrayHolder;

    JButton button1;
    JButton button2;
    JButton button3;

    ArrayList<String> keyWords;
    ArrayList<String> finalWordList;

    
    ArrayList<Result> results;
    ArrayList<Result> orderedResults;
    ArrayList<ArrayList> allResults;
    
    ArrayList<JButton> pButtons;
    WordRandomizer wordRandomizer;
    Timer t;// = new Timer(delay, this);

   String realWord; 
    
    
    
    
    
    public PPanel(MainPanel p) throws IOException {

        super();
        setLayout(null);
        main = p;
        setBackground(Color.white);

        
       results = new ArrayList();
       allResults = new ArrayList();
        
      //  System.out.println("new PPanel, size of results = " + results.size());
        
        
        wordRandomizer = new WordRandomizer();
        t = new Timer(delay, this);
        getKeyWords();

        button1 = new JButton();
        button1.setBounds(100, 240, 220, 80);
        button1.setFont(new Font("Arial", Font.PLAIN, 40));

        // add(button1);
        button3 = new JButton();
        button3.setBounds(620, 240, 220, 80);
        button3.setFont(new Font("Arial", Font.PLAIN, 40));

        //  button3.setBackground(Color.red);
        //  add(button3);
        button2 = new JButton();
        button2.setBounds(380, 240, 220, 80);
        button2.setFont(new Font("Arial", Font.PLAIN, 40));

        // add(button2);
        pButtons = new ArrayList();
        pButtons.add(button1);
        pButtons.add(button2);
        pButtons.add(button3);
        add(pButtons.get(1));

        
        t.start();
        // rows = r;//(int)(Math.random() * 10) + 1;
        //  columns = rows;
        // pick a random number within range, this will be the target button
        //  randomNumber = (int) (Math.random() * rows * rows) + 1;
        // addButtons();

    }

    @Override
    public void actionPerformed(ActionEvent event) {

        Object obj = event.getSource();

        if (obj == t) {
            if (counter >= numberOfWords) {
                t.stop();
                addListeners();
                getResponses();
            } else {
                button2.setText(keyWords.get(counter));
                counter++;
            }
        }

        if (obj == button1) {
                calculateTime();
            if (isContained(button1.getText())) {
               createResult(temporaryArrayHolder, totalTime, 0);
            } else {
                errors++;
                createResult(temporaryArrayHolder, totalTime, 1);
            }

            offerChoice();

        }

        if (obj == button2) {
            calculateTime();
            if (isContained(button2.getText())) {
                createResult(temporaryArrayHolder, totalTime, 0);
            } else {
                errors++;
                createResult(temporaryArrayHolder, totalTime, 1);
            }
            offerChoice();
        }

        if (obj == button3) {
            calculateTime();
            if (isContained(button3.getText())) {
               createResult(temporaryArrayHolder, totalTime, 0);
            } else {
                errors++;
                createResult(temporaryArrayHolder, totalTime, 1);
            }
            offerChoice();
        }

    } // end action listener

    
   private void createResult(int position, long time, int error){
       
       Result temp = new Result((position + 1),  time,  error);
      results.add(temp);
      /// System.out.println("size of results array = " + results.size()); 
       
   } 
    
    
    
    
    
    
    private boolean isContained(String word) {
        
        System.out.println("checkArray()...");
        checkArray(realWord);
        
        boolean pop = false;

        for (int i = 0; i < finalWordList.size(); i++) {

            // System.out.println("Does " + finalWordList.get(i) + " equal " +  word);
            if (finalWordList.get(i).equals(word)) {
                pop = true;
                 //temporaryArrayHolder = i;
               // calculateTime();
                break;
            }

        }

        return pop;
    }

    private void calculateTime() {

        totalTime = System.currentTimeMillis() - unixTimeStart;
       // System.out.println("total time = " + totalTime);
    }  // end calculate time

    private void getKeyWords() {

        keyWords = new ArrayList();
        finalWordList = new ArrayList();

        for (int i = 0; i < numberOfWords; i++) {
            String tempWord = wordRandomizer.getRandomWord();
            keyWords.add(tempWord);
            finalWordList.add(tempWord);  // we make a copy so we can remove words from keyWords and keep the overall list intact
            //System.out.println(keyWords.get(i));
        }

    }


    private void getResponses() {

        add(button1);
        add(button3);

        offerChoice();

    }// end get responses

    private void offerChoice() {

        unixTimeStart = System.currentTimeMillis();

        if (keyWords.size() > 0) {

            Random rand = new Random();

            int whichOne = rand.nextInt(keyWords.size());
            temporaryArrayHolder = whichOne;
            int choice = rand.nextInt(3);

            realWord = keyWords.get(whichOne);
           // System.out.println(" keyWords has " + keyWords.size() + ", finalWordList has " + finalWordList.size() + " words");
            keyWords.remove(whichOne);

           // System.out.println(" keyWords has " + keyWords.size() + ", finalWordList has " + finalWordList.size() + " words");

            switch (choice) {

                case 0:
                   // System.out.println("case 0");
                    button1.setText(realWord);
                    fillInOtherTwo(button2, button3);
                    break;
                case 1:
                 //   System.out.println("case 1");
                    button2.setText(realWord);
                    fillInOtherTwo(button3, button1);
                    break;
                case 2:
                //    System.out.println("case 2");
                    button3.setText(realWord);
                    fillInOtherTwo(button1, button2);
                    break;
            }

        } else {
            try {
                main.updatePanel();
            } catch (IOException ex) {
                Logger.getLogger(PPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
           // printResultsInOrder();
          //  button1.setText("Finished");
          //  button2.setText("Errors =");
          //  button3.setText("" + errors);
            
        }

    } // end offer choice

    private void fillInOtherTwo(JButton a, JButton b) {

        a.setText(wordRandomizer.getRandomWord());
        b.setText(wordRandomizer.getRandomWord());

    }  // end fill in other two

    private void addListeners() {

        button1.addActionListener(this);
        button2.addActionListener(this);

        button3.addActionListener(this);

    }    // end add listeners

    
  private void checkArray(String word){
      
      for(int i = 0; i < finalWordList.size(); i++){
      if(finalWordList.get(i).equals(word)){
       // System.out.println("THE POSITION IS #" + i);
        temporaryArrayHolder = i; 
        break;
      }else{
       //System.out.println("no luck #" + i);  
      }
      }
      
  }  
    

    
    
    
} // end
