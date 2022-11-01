package wIwatW;

import java.awt.Dimension;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DarstellungsGUI {
	 private  JDialog darstellungsFrame = new JDialog();
	private JPanel mainPanel= new JPanel();
	private SaveSettings saveSet;
	
	 public DarstellungsGUI(SaveSettings saveSet)
	 {
		 this.saveSet=saveSet;
		 
		 darstellungsFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		 darstellungsFrame.setTitle("Darstellungs Optionen");
		 darstellungsFrame.setSize(400,200);
		 darstellungsFrame.setModal(true);
		 
		 String[] numbers = saveSet.darstellungNumbersMemory; 
		 
		 Box panel1 = new Box( BoxLayout.X_AXIS);
		    panel1.setAlignmentX(0);
		 JTextField wortanzahl = new JTextField();
		 Dimension maximumSize = new Dimension();
	        maximumSize.setSize(100,20);
	        wortanzahl.setMinimumSize(maximumSize);
	        wortanzahl.setMaximumSize(maximumSize);
	        wortanzahl.setText(numbers[0]);
	        //wortanzahl.
	        JLabel label = new JLabel ("Anzahl der Wörter in der Liste:  ");
	        panel1.add(label);
	     panel1.add(wortanzahl);   
	      mainPanel.add(panel1);
		 
	      Box panel2 = new Box( BoxLayout.X_AXIS);
		    panel2.setAlignmentX(0);

		 JTextField worteInText = new JTextField();
	        maximumSize.setSize(100,20);
	        worteInText.setMinimumSize(maximumSize);
	        worteInText.setMaximumSize(maximumSize);
	        worteInText.setText(numbers[1]);
	        //wortanzahl.
	        JLabel label1 = new JLabel ("Anzahl der übernommenen Wörter der Liste:  ");
	        panel2.add(label1);
	     panel2.add(worteInText);   
	     
	     
		 mainPanel.add(panel2);
		 
		 
		 JButton button = new JButton("Anwenden");
	        
	        button.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent e) {
	         
	            		saveSet.darstellungNumbersMemory[0]=wortanzahl.getText();
	            		saveSet.darstellungNumbersMemory[1]=worteInText.getText();
	            	
	            	
	            
	            	DarstellungsGUI.this.saveSet=saveSet;
	            	DarstellungsGUI.this.darstellungsFrame.dispose();
	            	TokenObserver.clearCache();
	            }
	        });
	        JButton standard = new JButton("Standard wiederherstellen");
	        standard.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent e) {
	            	DarstellungsGUI.this.saveSet.darstellungNumbersMemory[0]="1";
	            	DarstellungsGUI.this.saveSet.darstellungNumbersMemory[1]="1";
	            	DarstellungsGUI.this.darstellungsFrame.dispose();
	            	@SuppressWarnings("unused")
	            	DarstellungsGUI dg= new DarstellungsGUI(DarstellungsGUI.this.saveSet);
	            }
	        });
	        
	        
	        mainPanel.add(button);
	        mainPanel.add(standard);
	        
		  mainPanel.setLayout( new javax.swing.BoxLayout(
	                mainPanel, javax.swing.BoxLayout.Y_AXIS ) );
		  
		 darstellungsFrame.add(mainPanel);
		 try{
				
				
				Image image = ImageIO.read(SimpleGUI.getIconFile());
				darstellungsFrame.setIconImage(image);
				} catch(IOException ex){
				ex.printStackTrace();
				}
		 
	        //postProcessingFrame.add(mainFrame);
		 darstellungsFrame.setVisible(true);
	 }
/**
 * Returns savesettings
 * @return the saveset
 */
	 public SaveSettings getSaveSettings()
	 {
		 return saveSet;
	 }

}
