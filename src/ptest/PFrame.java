/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ptest;

import java.awt.BorderLayout;
import java.io.IOException;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 *
 * @author mark
 */
public class PFrame  extends JFrame{
    
   // public GalaxyMapPanel galaxyPanel = new GalaxyMapPanel(21,21);
     public MainPanel  mainPanel;// = new GameJPanel();
    // public MainPanel main = new MainPanel();
    private int bPanelSize;
    public PFrame(int width, int height) throws IOException{
        super ("Memory Test");
        
        
                getContentPane().setLayout(new BorderLayout());
             
                 mainPanel = new MainPanel();
              
                getContentPane().add( mainPanel,"Center");
             
                setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize (width, height);//setSize (1920, 1000);1366x768
		setVisible(true);
    }
    
}

