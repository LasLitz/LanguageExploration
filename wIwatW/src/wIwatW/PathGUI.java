package wIwatW;

import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * GUI: window for save path configuring
 * @author Lasse
 *
 */
public class PathGUI {
	 private  JDialog pathFrame = new JDialog();
	private JPanel mainPanel= new JPanel();
	private SaveSettings saveSet;
	private JLabel label1;
    private JLabel label2; 

	private JTextField [] textField = new JTextField [3];
	private JButton[] dialogButtons = new JButton[4];
	private Box[] boxes = new Box[2];
	/**
	 * creates the pathgui
	 * @param saveSet input saveset
	 * @param meta metacorpus
	 */
	 public PathGUI(SaveSettings saveSet, MetaCorpus meta)
	 {
	        Dimension maximumSize = new Dimension();
	        maximumSize.setSize(400, 20);
		 this.saveSet=saveSet;
		 pathFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		 //featureFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		 pathFrame.setTitle("Pfad-Angaben");
		 pathFrame.setSize(700,500);
		 pathFrame.setModal(true);
	        

	        String[] memo = saveSet.pathMemory;
	  

	        	boxes[0]= new Box(BoxLayout.X_AXIS);
	        	boxes[1]= new Box(BoxLayout.X_AXIS);
	        	textField[0]= new JTextField(memo[0]);
	        	textField[0].setMaximumSize(maximumSize);
	        	textField[1]= new JTextField(memo[1]);
	        	textField[1].setMaximumSize(maximumSize);
	        	textField[2]= new JTextField(memo[2]);
	        	textField[2].setMaximumSize(maximumSize);
	        	dialogButtons[0]=new JButton("Text-Ordner");
	        	dialogButtons[0].addActionListener(new java.awt.event.ActionListener() {
		            public void actionPerformed(java.awt.event.ActionEvent e) {
		            	 // Dateiauswahldialog wird erzeugt...
		                JFileChooser fc = new JFileChooser();
		                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		                // ... und angezeigt
		                fc.showOpenDialog(null);
		                
		                String path="";
		                path=fc.getSelectedFile().getAbsolutePath();
		                textField[0].setText(path); //deaktiviert bis Corpusverwaltung ausgereift
		            }
		        });
	        	
	        	dialogButtons[1]=new JButton("Speicher-Ordner");
	        	dialogButtons[1].addActionListener(new java.awt.event.ActionListener() {
		            public void actionPerformed(java.awt.event.ActionEvent e) {
		            	 // Dateiauswahldialog wird erzeugt...
		                JFileChooser fc = new JFileChooser();
		                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		                // ... und angezeigt
		                fc.showOpenDialog(null);
		                String path="";
		                path=fc.getSelectedFile().getAbsolutePath();
		                textField[1].setText(path);//deaktiviert bis Corpusverwaltung ausgereift
		            }
		        });
	        	boxes[0].add(textField[0]);
	        	boxes[0].add(dialogButtons[0]);
	        	boxes[0].setAlignmentX(0);
	        	boxes[1].add(textField[1]);
	        	boxes[1].add(dialogButtons[1]);
	        	boxes[1].setAlignmentX(0);
	        	
	        mainPanel.add(boxes[0]);
	        mainPanel.add(boxes[1]);
	        mainPanel.add(textField[2]);
	        JButton button = new JButton("Anwenden");
	        
	        button.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent e) {
	            	
	            	
	         
	            		saveSet.pathMemory[0]=textField[0].getText();
	            		saveSet.pathMemory[1]=textField[1].getText();
	            		saveSet.pathMemory[2]=textField[2].getText();
	            	
	        	        File res = new File(PathGUI.this.saveSet.pathMemory[0]);
	        	    	res.mkdir();
	        	        File texte = new File(PathGUI.this.saveSet.pathMemory[1]);
	        	    	
    					//res.mkdir();
    			       texte.mkdir();
	        	    	
	            	PathGUI.this.saveSet=saveSet;
	            	
	            	
	            	PathGUI.this.pathFrame.dispose();
	            }
	        });
	        JButton standard = new JButton("Standard wiederherstellen");
	        standard.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent e) {
	            	//FeatureGUI.this.saveSet= new SaveSettings();
	    	
	            	String path = wIwatWMain.class.getProtectionDomain().getCodeSource().getLocation().getPath();
	    			String decodedPath="";
	    			try {
	    				decodedPath = URLDecoder.decode(path, "UTF-8");
	    			} catch (UnsupportedEncodingException e2) {
	    				e2.printStackTrace();
	    			}
	    			
	    			File test= new File(decodedPath);
	    			decodedPath=test.getParent();
	    			PathGUI.this.saveSet.pathMemory[0]=decodedPath+File.separator+"res";
	    	        File res = new File(PathGUI.this.saveSet.pathMemory[0]);
	    	
	    					//res.mkdir();
	    			       res.mkdir();
	    	
	    	        
	    			       PathGUI.this.saveSet.pathMemory[1]=decodedPath+File.separator+"texte";
	    	        File texte = new File(PathGUI.this.saveSet.pathMemory[1]);
	    	   
	    					texte.mkdir();
	    	
	    	        
	    					PathGUI.this.saveSet.pathMemory[2]="wiwatWmeta1.ser";
	            	

	  
	    	        	PathGUI.this.pathFrame.dispose();
	    	        	@SuppressWarnings("unused")
						PathGUI pg= new PathGUI(PathGUI.this.saveSet, meta);
	            }
	        });
	        
	        
        	dialogButtons[2]=new JButton("Auswahl der Corpora-Hauptliste");
        	dialogButtons[2].addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent e) {
	            	 // Dateiauswahldialog wird erzeugt...
	                JFileChooser fc = new JFileChooser();
	                fc.setMultiSelectionEnabled(true);
	                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	                // ... und angezeigt
	                fc.showOpenDialog(null);
	                File[] chosenCorpora = fc.getSelectedFiles();
	                PathGUI.this.saveSet.chosenCorporaNames.clear();
	                for(int i=0;i<chosenCorpora.length;i++)
	                {
	                	String name=chosenCorpora[i].getName();
	                	PathGUI.this.saveSet.chosenCorporaNames.add(name);
	                
	                }
	                PathGUI.this.label1.setText(PathGUI.this.saveSet.chosenCorporaNames.toString());
	            }
	        });
        	dialogButtons[3]=new JButton("Auswahl der Corpora-Referenzliste");
        	dialogButtons[3].addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent e) {
	            	 // Dateiauswahldialog wird erzeugt...
	                JFileChooser fc = new JFileChooser();
	                fc.setMultiSelectionEnabled(true);
	                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	                // ... und angezeigt
	                fc.showOpenDialog(null);
	                File[] chosenCorpora = fc.getSelectedFiles();
	                PathGUI.this.saveSet.chosenCorporaNames2.clear();
	                for(int i=0;i<chosenCorpora.length;i++)
	                {
	                	String name=chosenCorpora[i].getName();
	                	PathGUI.this.saveSet.chosenCorporaNames2.add(name);
	                	//label aktualisieren<------------------------------------------------------------------------
	                }
	                PathGUI.this.label2.setText(PathGUI.this.saveSet.chosenCorporaNames2.toString());
	            }
	        });
	        mainPanel.add(button);
	        mainPanel.add(standard);
	         label1= new JLabel(this.saveSet.chosenCorporaNames.toString());
	         label2= new JLabel(this.saveSet.chosenCorporaNames2.toString());
	        mainPanel.add(label1);
	        
	        mainPanel.add(dialogButtons[2]);
	        mainPanel.add(label2);
	        mainPanel.add(dialogButtons[3]);
	        mainPanel.setLayout( new javax.swing.BoxLayout(
	                mainPanel, javax.swing.BoxLayout.Y_AXIS ) );
	        pathFrame.add(mainPanel);
	   
 try{
				
				
				Image image = ImageIO.read(SimpleGUI.getIconFile());
				pathFrame.setIconImage(image);
				} catch(IOException ex){
				ex.printStackTrace();
				}
 
	        pathFrame.setVisible(true);
	 }
	 
	 /**
	  * saves the options
	  * @return the saveset
	  */
	 public SaveSettings getSaveSettings()
	 {
		 return saveSet;
	 }
}
