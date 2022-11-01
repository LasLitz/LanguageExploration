package wIwatW;

import java.awt.Dimension;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

/**
 * GUI: window for feature options
 * @author Lasse
 *
 */
public class FeatureGUI {
	 private  JDialog featureFrame = new JDialog();
	 private  JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
	private JPanel mainPanel= new JPanel();
	private JPanel leftPanel= new JPanel();
	private JPanel rightPanel= new JPanel();
	private SaveSettings saveSet;
	private  int featureNumber=TranslationAlgorithm.values().length;;
	
	private  Box[] boxes = new Box[featureNumber];
	private  Box[] boxes2 = new Box[featureNumber];
	private JCheckBox [] checkBoxes = new JCheckBox [featureNumber]; 
	private JCheckBox [] checkBoxes2 = new JCheckBox [featureNumber]; 
	private JTextField [] textField = new JTextField [featureNumber];
	private JTextField [] textField2 = new JTextField [featureNumber];
	
	/**
	 * creates a feature gui window with given savesettings
	 * @param saveSet
	 */
	 public FeatureGUI(SaveSettings saveSet)
	 {
	        Dimension maximumSize = new Dimension();
	        maximumSize.setSize(30, 20);
		 this.saveSet=saveSet;
		 featureFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		 //featureFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	        featureFrame.setTitle("Feature Auswahl");
	        featureFrame.setSize(700,525);
	        featureFrame.setModal(true);
	        

	        boolean[] memo = saveSet.featureBooleanMemory[0];
	        String[] memo2 = saveSet.featureNumbersMemory[0];
	  
	        TranslationAlgorithm[] tAarr=TranslationAlgorithm.values();
	        JLabel headline = new JLabel ("Hauptliste");
	        JLabel headline2 = new JLabel ("Referenzliste");
	        leftPanel.add(headline);
		       rightPanel.add(headline2);
		       leftPanel.setLayout(new javax.swing.BoxLayout(
		    		   leftPanel, javax.swing.BoxLayout.Y_AXIS ));
		       rightPanel.setLayout(new javax.swing.BoxLayout(
		    		   rightPanel, javax.swing.BoxLayout.Y_AXIS ));
	        for(int i=0;i<featureNumber;i++)
	        {
	        	 checkBoxes[i]= new JCheckBox(tAarr[i].name(),memo[i]);
	        	
	        	textField[i]= new JTextField(memo2[i]);
	        	textField[i].setMaximumSize(maximumSize);
	        	boxes[i] = new Box(BoxLayout.X_AXIS);
	        	boxes[i].setAlignmentX(0);
	        	boxes[i].add(checkBoxes[i]);
	        	boxes[i].add(textField[i]);
	        	leftPanel.add( boxes[i]);
	        	
	        }
	        
	        boolean[] memo1 = saveSet.featureBooleanMemory[1];
	        String[] memo21 = saveSet.featureNumbersMemory[1];

	 
	        for(int i=0;i<featureNumber;i++)
	        {
	        	 checkBoxes2[i]= new JCheckBox(tAarr[i].name(),memo1[i]);
	        	
	        	textField2[i]= new JTextField(memo21[i]);
	        	textField2[i].setMaximumSize(maximumSize);
	        	boxes2[i] = new Box(BoxLayout.X_AXIS);
	        	boxes2[i].setAlignmentX(0);
	        	boxes2[i].add(checkBoxes2[i]);
	        	boxes2[i].add(textField2[i]);
	        	rightPanel.add( boxes2[i]);
	        	
	        }
	        splitPane.setDividerLocation(300);
	        splitPane.setLeftComponent(leftPanel);
	        splitPane.setRightComponent(rightPanel);
	        mainPanel.add(splitPane);
	        JButton button = new JButton("Anwenden");
	        
	        button.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent e) {
	            	
	            	for(int i=0;i<checkBoxes.length;i++)
	            	{
	            		saveSet.featureBooleanMemory[0][i]=checkBoxes[i].isSelected();
	            		saveSet.featureNumbersMemory[0][i]=textField[i].getText();
	            		saveSet.featureBooleanMemory[1][i]=checkBoxes2[i].isSelected();
	            		saveSet.featureNumbersMemory[1][i]=textField2[i].getText();
	            	}
	            	
	            	
	            	
	            	FeatureGUI.this.saveSet=saveSet;
	            	TokenObserver.clearCache();
	            	FeatureGUI.this.featureFrame.dispose();
	            }
	        });
	        JButton standard = new JButton("Standard wiederherstellen");
	        standard.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent e) {
	            	//FeatureGUI.this.saveSet= new SaveSettings();
	    	        for(int i=0; i<FeatureGUI.this.saveSet.featureNumbersMemory.length; i++)
	    	        {
	    	        	FeatureGUI.this.saveSet.featureNumbersMemory[0][i]="1";
	    	        }
	    	        for(int i=0; i<FeatureGUI.this.saveSet.featureBooleanMemory.length; i++)
	    	        {
	    	        	FeatureGUI.this.saveSet.featureBooleanMemory[0][i]=false;
	    	        }
	    	        FeatureGUI.this.saveSet.featureBooleanMemory[0][0]=true;
	            	FeatureGUI.this.featureFrame.dispose();
	            	@SuppressWarnings("unused")
					FeatureGUI pg= new FeatureGUI(FeatureGUI.this.saveSet);
	            }
	        });
	       
	        mainPanel.add(button);
	        mainPanel.add(standard);
	        mainPanel.setLayout( new javax.swing.BoxLayout(
	                mainPanel, javax.swing.BoxLayout.Y_AXIS ) );
	        featureFrame.add(mainPanel);
	        //featureFrame.add(mainFrame);
 try{
				
				
				Image image = ImageIO.read(SimpleGUI.getIconFile());
				featureFrame.setIconImage(image);
				} catch(IOException ex){
				ex.printStackTrace();
				}
	        featureFrame.setVisible(true);
	 }
	 
	 /**
	  * save the options
	  * @return the saveset
	  */
	 public SaveSettings getSaveSettings()
	 {
		 return saveSet;
	 }
}
