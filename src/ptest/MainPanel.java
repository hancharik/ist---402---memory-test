/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ptest;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author mark
 */
public class MainPanel extends JPanel implements ActionListener {

    PPanel pPanel;
    ArrayList<String> resultSet = new ArrayList();
    JLabel titleLabel;
    JLabel endLabel;
    JButton startButton;// = new JButton("start");
    JButton pictureButton;// = new JButton("start");
    
    ArrayList<ArrayList<Result>> allResults = new ArrayList();

    ImageIcon background = new ImageIcon("images/jacuzzi.jpg"); // photo from https://www.emaze.com/@AFRLOTIO/Jacuzzi
    Image backgroundImage = background.getImage();
    
    
    int attempts = ptest.Ptest.attempts;

    public MainPanel() throws IOException {

        super();

        setLayout(null);
        endLabel = new JLabel("Objective: try to remember as many words as you can");
        endLabel.setBounds(320, 100, 600, 180);
        setLabel(endLabel);
        add(endLabel);
        titleLabel = new JLabel("");
        titleLabel.setBounds(320, 10, 600, 80);
        setLabel(titleLabel);
        add(titleLabel);
        startButton = new JButton("begin");//startButton = new JButton("begin test");
        startButton.setBounds(550, 340, 120, 40);
        startButton.addActionListener(this);
        add(startButton);
        // setBackground(Color.BLUE);
        // pPanel = new PPanel();
        //addPanel();

    }

    public void addPanel() throws IOException {

        if (attempts > 0) {// if(ptest.Ptest.attempts>0){
            // this.remove(pPanel);
            pPanel = new PPanel( this);
            pPanel.setBounds(140, 30, 1000, 600);
            add(pPanel);
        } else {
            changePicture(1);//setBackground(Color.GREEN);  
            startButton.setVisible(true);
            endLabel.setVisible(true);
            endLabel.setText("Thank You for participating.");//endLabel = new JLabel("Thank You");
            // endLabel.setBounds(400,200,200,200);
            setLabel(endLabel);
            titleLabel.setVisible(true);
            titleLabel.setText("");//endLabel = new JLabel("Thank You");
            // endLabel.setBounds(400,200,200,200);
            setLabel(titleLabel);
            //add(endLabel);
            long unixTimeEndTest = System.currentTimeMillis();
            double totalTime = (unixTimeEndTest - ptest.Ptest.unixTimeStartTest) / 1000;
            System.out.println("total time in seconds is " + (int) totalTime / 60 + " minutes and " + totalTime % 60 + " seconds");// System.out.println("the array size is " + resultSet.size() + ", total time in seconds is " + totalTime);
            pPanel.wordRandomizer.fileHandler.createFile(resultSet);

        }
    } // end add panel

    public void updatePanel() throws IOException {

        allResults.add(pPanel.results);
        attempts--;
        //System.out.println("NUMBER OF ATTEMPTS LEFT: " + attempts);
        remove(pPanel);
        //   setBackground(Color.yellow);
        addPanel();
        repaint();
    }

    public int getArraySize1() {

        int arrayElement = (int) (Math.random() * ptest.Ptest.sizes.size());
        // System.out.println("array element = " + arrayElement + ", size = " + ptest.Ptest.sizes.size());
        int t = ptest.Ptest.sizes.get(arrayElement);
        // System.out.println("t = " + t); 
        ptest.Ptest.sizes.remove(arrayElement);
        return t;
    }

    public void setLabel(JLabel e) {
        // http://stackoverflow.com/questions/2715118/how-to-change-the-size-of-the-font-of-a-jendLabel-to-take-the-maximum-size 

        Font endLabelFont = e.getFont();
        String endLabelText = e.getText();

        int stringWidth = e.getFontMetrics(endLabelFont).stringWidth(endLabelText);
        int componentWidth = e.getWidth();

// Find out how much the font can grow in width.
        double widthRatio = (double) componentWidth / (double) stringWidth;

        int newFontSize = (int) (endLabelFont.getSize() * widthRatio);
        int componentHeight = e.getHeight();

// Pick a new font size so it will not be larger than the height of endLabel.
        int fontSizeToUse = Math.min(newFontSize, componentHeight);

// Set the endLabel's font size to the newly determined size.
        e.setFont(new Font(endLabelFont.getName(), Font.PLAIN, fontSizeToUse));

    }

    @Override
    public void actionPerformed(ActionEvent event) {

        Object obj = event.getSource();

        if (obj == startButton) {

            if (startButton.getText().equals("quit")) {
                System.out.println("FINAL SIZE OF RESULT ARRAY: " + allResults.size() + " should be " +  ptest.Ptest.attempts);
                printResults();
                System.exit(0);
            } else {

                try {
                    changePicture(2);
                    startButton.setVisible(false);
                    endLabel.setVisible(false);
                    titleLabel.setVisible(false);
                    startButton.setText("quit");
                    addPanel();
                    repaint();
                } catch (IOException ex) {
                    Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
                } // end try/catch

            }
        } // end if
    }

    //https://www.emaze.com/@AFRLOTIO/Jacuzzi
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //g.drawImage(backgroundImage, 0, 0, this);
    }

    private void changePicture(int choice) {

        switch (choice) {
            case 1:
                background = new ImageIcon("images/jacuzzi.jpg");
                backgroundImage = background.getImage();
                break;
            case 2:
                background = new ImageIcon("images/white.png");
                backgroundImage = background.getImage();
                break;
        }

    }

    
    
    
    
    
    
   private void printResults(){
       
     
            
            ArrayList<String> fullList = new ArrayList();
            
            for(int i = 0; i < allResults.size(); i++){
                for(int j = 0; j < allResults.get(i).size(); j++){
                    String tempResult = allResults.get(i).get(j).position + "\t" + allResults.get(i).get(j).time + "\t" +  allResults.get(i).get(j).errors;
                    fullList.add(tempResult);
                    System.out.println("position = " + allResults.get(i).get(j).position + ", errors = " + allResults.get(i).get(j).errors + ", time = " + allResults.get(i).get(j).time);
                }
            }
               try {
            WordRandomizer edna = new WordRandomizer();
            try {
                edna.fileHandler.createFile(fullList);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
      
   } 
    
    
    
    
    
    
    
    
    
    
    
    
} // end class
