package wIwatW;


import java.awt.Dimension;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
/**
 * GUI: window for postprocessing options
 * @author Lasse
 *
 */
public class PostProcessingGUI {
	 private  JDialog postProcessingFrame = new JDialog();
	 private  JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
	private JPanel mainPanel= new JPanel();
	private SaveSettings saveSet;
	
	/**
	 * constructs the postprocessing window
	 * @param saveSet existing savesettings for postprocessing
	 */
	 public PostProcessingGUI(SaveSettings saveSet)
	 {
		 this.saveSet=saveSet;
		 postProcessingFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		 //postProcessingFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	        postProcessingFrame.setTitle("Postprocessing Operatoren");
	        postProcessingFrame.setSize(1000,550);
	        postProcessingFrame.setModal(true);
	        
	        boolean[] memoA = saveSet.postProcessingBooleanMemoryForLists[0];
	        boolean[] memoB = saveSet.postProcessingBooleanMemoryForLists[1];
	        boolean[] memo = saveSet.postProcessingBooleanMemory;
	        String[] numbersA = saveSet.postProcessingNumbersMemory[0]; 
	        String[] numbersB = saveSet.postProcessingNumbersMemory[1];
	       
	         JPanel leftPanel= subPanels("Hauptliste",memoA, numbersA);
	         JPanel rightPanel= subPanels("Referenzliste",memoB,numbersB);
	        splitPane.setLeftComponent(leftPanel);
	        splitPane.setRightComponent(rightPanel);
	        mainPanel.add(splitPane);
	        JRadioButton [] checkBoxes = new JRadioButton [7]; 
	        checkBoxes[0]=new JRadioButton("nur A",memo[0]);
	        checkBoxes[1]=new JRadioButton("nur B",memo[1]);
	        checkBoxes[2]=new JRadioButton("Vereinigung von A und B",memo[2]);
	        checkBoxes[3]=new JRadioButton("Schnitt von A und B", memo[3]);
	        checkBoxes[4]=new JRadioButton("A Minus B", memo[4]);
	        checkBoxes[5]=new JRadioButton("A Ohne B", memo[5]);
	        checkBoxes[6]=new JRadioButton("Signifikanzen durch Referenzliste", memo[6]);
	        ButtonGroup group = new ButtonGroup();
	        
	        //JRadioButtons werden zur ButtonGroup hinzugefügt
	        
	        for(int i=0; i<checkBoxes.length;i++)
	        {
	        	group.add(checkBoxes[i]);
	        	mainPanel.add( checkBoxes[i]);
	        }
	        JButton button = new JButton("Anwenden");
	        
	        button.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent e) {
	            	
	            	saveSet.postProcessingBooleanMemory[0]= checkBoxes[0].isSelected();
	            	saveSet.postProcessingBooleanMemory[1]=checkBoxes[1].isSelected();
	            	saveSet.postProcessingBooleanMemory[2]=checkBoxes[2].isSelected();
	            	saveSet.postProcessingBooleanMemory[3]=checkBoxes[3].isSelected();
	            	saveSet.postProcessingBooleanMemory[4]=checkBoxes[4].isSelected();
	            	saveSet.postProcessingBooleanMemory[5]=checkBoxes[5].isSelected();
	            	saveSet.postProcessingBooleanMemory[6]=checkBoxes[6].isSelected();
	            	
	            	int index=0;
	            	int numbers=0;
	            	for(int i=1;i<leftPanel.getComponentCount();i++)
	            	{
	            		Box temp2=(Box)leftPanel.getComponent(i);
	            		if(temp2.getComponentCount()<2)
	            		{
	            			JCheckBox temp = (JCheckBox)temp2.getComponent(0);
			            	saveSet.postProcessingBooleanMemoryForLists[0][index]=temp.isSelected();
			            	//System.out.println(index+" "+temp.isSelected()+" "+i);
			            	index++;
	            		}
	            		
	            		else if(temp2.getComponentCount()==2)
	            		{
	            			JCheckBox temp =(JCheckBox)temp2.getComponent(0);
	            			saveSet.postProcessingBooleanMemoryForLists[0][index]=temp.isSelected();
	            			//System.out.println(index+" "+temp.isSelected()+" "+i);
	            			JTextField r1 =(JTextField)temp2.getComponent(1);
	            			saveSet.postProcessingNumbersMemory[0][numbers]=r1.getText();
	            			//System.out.println(r1.getText()+" "+numbers+" "+i);
	            			numbers++;
	            			index++;
	            		}
	            		
	            		else if(temp2.getComponentCount()==3)
	            		{
	            			JCheckBox temp =(JCheckBox)temp2.getComponent(0);
	            			saveSet.postProcessingBooleanMemoryForLists[0][index]=temp.isSelected();
	            			//System.out.println(index+" "+temp.isSelected()+" "+i);
	            			JRadioButton r1 =(JRadioButton)temp2.getComponent(1);
	            			JRadioButton r2 =(JRadioButton)temp2.getComponent(2);
	            			saveSet.postProcessingBooleanMemoryForLists[0][index+1]=r1.isSelected();
	            			//System.out.println("-> "+(index+1)+" "+r1.isSelected()+"> "+i+saveSet.postProcessingBooleanMemoryForLists[0][index+1]);
	            			saveSet.postProcessingBooleanMemoryForLists[0][index+2]=r2.isSelected();
	            			//System.out.println("-> "+(index+2)+" "+r2.isSelected()+"> "+i+saveSet.postProcessingBooleanMemoryForLists[0][index+2]);
	           //System.out.println("-> "+(index+4)+" "+r4.isSelected()+"> "+i+saveSet.postProcessingBooleanMemoryForLists[0][index+4]);
	            			index+=3;
	            		}
	            		
//	               		else if(temp2.getComponentCount()==4)
//	            		{
//	            			JCheckBox temp =(JCheckBox)temp2.getComponent(0);
//	            			saveSet.postProcessingBooleanMemoryForLists[0][index]=temp.isSelected();
//	            			//System.out.println(index+" "+temp.isSelected()+" "+i);
//	            			JRadioButton r1 =(JRadioButton)temp2.getComponent(1);
//	            			JRadioButton r2 =(JRadioButton)temp2.getComponent(2);
//	            			JRadioButton r3 =(JRadioButton)temp2.getComponent(3);
//	            			saveSet.postProcessingBooleanMemoryForLists[0][index+1]=r1.isSelected();
//	            			//System.out.println("-> "+(index+1)+" "+r1.isSelected()+"> "+i+saveSet.postProcessingBooleanMemoryForLists[0][index+1]);
//	            			saveSet.postProcessingBooleanMemoryForLists[0][index+2]=r2.isSelected();
//	            			//System.out.println("-> "+(index+2)+" "+r2.isSelected()+"> "+i+saveSet.postProcessingBooleanMemoryForLists[0][index+2]);
//	            			saveSet.postProcessingBooleanMemoryForLists[0][index+3]=r3.isSelected();
//	            			//System.out.println("-> "+(index+3)+" "+r3.isSelected()+"> "+i+saveSet.postProcessingBooleanMemoryForLists[0][index+3]);
//	            			index+=4;
//	            		}
	            		
	            		else if(temp2.getComponentCount()==5)
	            		{
	            			JCheckBox temp =(JCheckBox)temp2.getComponent(0);
	            			saveSet.postProcessingBooleanMemoryForLists[0][index]=temp.isSelected();
	            			//System.out.println(index+" "+temp.isSelected()+" "+i);
	            			JRadioButton r1 =(JRadioButton)temp2.getComponent(1);
	            			JRadioButton r2 =(JRadioButton)temp2.getComponent(2);
	            			JRadioButton r3 =(JRadioButton)temp2.getComponent(3);
	            			JRadioButton r4 =(JRadioButton)temp2.getComponent(4);
	            			saveSet.postProcessingBooleanMemoryForLists[0][index+1]=r1.isSelected();
	            			//System.out.println("-> "+(index+1)+" "+r1.isSelected()+"> "+i+saveSet.postProcessingBooleanMemoryForLists[0][index+1]);
	            			saveSet.postProcessingBooleanMemoryForLists[0][index+2]=r2.isSelected();
	            			//System.out.println("-> "+(index+2)+" "+r2.isSelected()+"> "+i+saveSet.postProcessingBooleanMemoryForLists[0][index+2]);
	            			saveSet.postProcessingBooleanMemoryForLists[0][index+3]=r3.isSelected();
	            			//System.out.println("-> "+(index+3)+" "+r3.isSelected()+"> "+i+saveSet.postProcessingBooleanMemoryForLists[0][index+3]);
	            			saveSet.postProcessingBooleanMemoryForLists[0][index+4]=r4.isSelected();
	            			//System.out.println("-> "+(index+4)+" "+r4.isSelected()+"> "+i+saveSet.postProcessingBooleanMemoryForLists[0][index+4]);
	            			index+=5;
	            		}
	            		
	            		
		            	
	            	}
	            	
	            	 index=0;
	            	 numbers=0;
	            	for(int i=1;i<rightPanel.getComponentCount();i++)
	            	{
	            		Box temp2=(Box)rightPanel.getComponent(i);
	            		if(temp2.getComponentCount()<2)
	            		{
	             			JCheckBox temp = (JCheckBox)temp2.getComponent(0);
			            	saveSet.postProcessingBooleanMemoryForLists[1][index]=temp.isSelected();
			            	//System.out.println(index+" "+temp.isSelected()+" "+i);
			            	index++;
	            		}
	            		
	            		else if(temp2.getComponentCount()==2)
	            		{
	            			JCheckBox temp =(JCheckBox)temp2.getComponent(0);
	            			saveSet.postProcessingBooleanMemoryForLists[1][index]=temp.isSelected();
	            			//System.out.println(index+" "+temp.isSelected()+" "+i);
	            			JTextField r1 =(JTextField)temp2.getComponent(1);
	            			saveSet.postProcessingNumbersMemory[1][numbers]=r1.getText();
	            			//System.out.println(r1.getText()+" "+numbers+" "+i);
	            			numbers++;
	            			index++;
	            		}
	            		else if(temp2.getComponentCount()==3)
	            		{
	            			JCheckBox temp =(JCheckBox)temp2.getComponent(0);
	            			saveSet.postProcessingBooleanMemoryForLists[1][index]=temp.isSelected();
	            			//System.out.println(index+" "+temp.isSelected()+" "+i);
	            			JRadioButton r1 =(JRadioButton)temp2.getComponent(1);
	            			JRadioButton r2 =(JRadioButton)temp2.getComponent(2);
	            			saveSet.postProcessingBooleanMemoryForLists[1][index+1]=r1.isSelected();
	            			//System.out.println("-> "+(index+1)+" "+r1.isSelected()+"> "+i+saveSet.postProcessingBooleanMemoryForLists[0][index+1]);
	            			saveSet.postProcessingBooleanMemoryForLists[1][index+2]=r2.isSelected();
	            			//System.out.println("-> "+(index+2)+" "+r2.isSelected()+"> "+i+saveSet.postProcessingBooleanMemoryForLists[0][index+2]);
	           //System.out.println("-> "+(index+4)+" "+r4.isSelected()+"> "+i+saveSet.postProcessingBooleanMemoryForLists[0][index+4]);
	            			index+=3;
	            		}
	            		
	            		else if(temp2.getComponentCount()==5)
	            		{
	            			JCheckBox temp =(JCheckBox)temp2.getComponent(0);
	            			saveSet.postProcessingBooleanMemoryForLists[1][index]=temp.isSelected();
	            			//System.out.println(index+" "+temp.isSelected()+" "+i);
	            			JRadioButton r1 =(JRadioButton)temp2.getComponent(1);
	            			JRadioButton r2 =(JRadioButton)temp2.getComponent(2);
	            			JRadioButton r3 =(JRadioButton)temp2.getComponent(3);
	            			JRadioButton r4 =(JRadioButton)temp2.getComponent(4);
	            			saveSet.postProcessingBooleanMemoryForLists[1][index+1]=r1.isSelected();
	            			//System.out.println("-> "+(index+1)+" "+r1.isSelected()+"> "+i+saveSet.postProcessingBooleanMemoryForLists[0][index+1]);
	            			saveSet.postProcessingBooleanMemoryForLists[1][index+2]=r2.isSelected();
	            			//System.out.println("-> "+(index+2)+" "+r2.isSelected()+"> "+i+saveSet.postProcessingBooleanMemoryForLists[0][index+2]);
	            			saveSet.postProcessingBooleanMemoryForLists[1][index+3]=r3.isSelected();
	            			//System.out.println("-> "+(index+3)+" "+r3.isSelected()+"> "+i+saveSet.postProcessingBooleanMemoryForLists[0][index+3]);
	            			saveSet.postProcessingBooleanMemoryForLists[1][index+4]=r4.isSelected();
	            			//System.out.println("-> "+(index+4)+" "+r4.isSelected()+"> "+i+saveSet.postProcessingBooleanMemoryForLists[0][index+4]);
	            			index+=5;
	            		}
	            		
	            		
		            	
	            	}
//	            	Box temp2=(Box)leftPanel.getComponent(1);
//	            	JCheckBox temp = (JCheckBox)temp2.getComponent(0);
//	            	saveSet.postProcessingBooleanMemoryForLists[0][0]=temp.isSelected();
//	            	temp=(JCheckBox)leftPanel.getComponent(2);
//	            	saveSet.postProcessingBooleanMemoryForLists[0][1]=temp.isSelected();
//	            	temp=(JCheckBox)leftPanel.getComponent(3);
//	            	saveSet.postProcessingBooleanMemoryForLists[0][2]=temp.isSelected();
//	            	temp=(JCheckBox)leftPanel.getComponent(4);
//	            	saveSet.postProcessingBooleanMemoryForLists[0][3]=temp.isSelected();
//	            	temp=(JCheckBox)leftPanel.getComponent(5);
//	            	saveSet.postProcessingBooleanMemoryForLists[0][4]=temp.isSelected();
//	            	temp=(JCheckBox)leftPanel.getComponent(6);
//	            	saveSet.postProcessingBooleanMemoryForLists[0][5]=temp.isSelected();
//	            	temp=(JCheckBox)leftPanel.getComponent(7);
//	            	saveSet.postProcessingBooleanMemoryForLists[0][6]=temp.isSelected();
//	            	temp=(JCheckBox)leftPanel.getComponent(8);
//	            	saveSet.postProcessingBooleanMemoryForLists[0][7]=temp.isSelected();
//	            	temp=(JCheckBox)leftPanel.getComponent(9);
//	            	saveSet.postProcessingBooleanMemoryForLists[0][8]=temp.isSelected();
//	            	temp=(JCheckBox)leftPanel.getComponent(10);
//	            	saveSet.postProcessingBooleanMemoryForLists[0][9]=temp.isSelected();
//	            	temp=(JCheckBox)leftPanel.getComponent(11);
//	            	saveSet.postProcessingBooleanMemoryForLists[0][10]=temp.isSelected();
//	            	temp=(JCheckBox)leftPanel.getComponent(12);
//	            	saveSet.postProcessingBooleanMemoryForLists[0][11]=temp.isSelected();
//	            	temp=(JCheckBox)rightPanel.getComponent(1);
//	            	saveSet.postProcessingBooleanMemoryForLists[1][0]=temp.isSelected();
//	            	temp=(JCheckBox)rightPanel.getComponent(2);
//	            	saveSet.postProcessingBooleanMemoryForLists[1][1]=temp.isSelected();
//	            	temp=(JCheckBox)rightPanel.getComponent(3);
//	            	saveSet.postProcessingBooleanMemoryForLists[1][2]=temp.isSelected();
//	            	temp=(JCheckBox)rightPanel.getComponent(4);
//	            	saveSet.postProcessingBooleanMemoryForLists[1][3]=temp.isSelected();
//	            	temp=(JCheckBox)rightPanel.getComponent(5);
//	            	saveSet.postProcessingBooleanMemoryForLists[1][4]=temp.isSelected();
//	            	temp=(JCheckBox)rightPanel.getComponent(6);
//	            	saveSet.postProcessingBooleanMemoryForLists[1][5]=temp.isSelected();
//	            	temp=(JCheckBox)rightPanel.getComponent(7);
//	            	saveSet.postProcessingBooleanMemoryForLists[1][6]=temp.isSelected();
//	            	temp=(JCheckBox)rightPanel.getComponent(8);
//	            	saveSet.postProcessingBooleanMemoryForLists[1][7]=temp.isSelected();
//	            	temp=(JCheckBox)rightPanel.getComponent(9);
//	            	saveSet.postProcessingBooleanMemoryForLists[1][8]=temp.isSelected();
//	            	temp=(JCheckBox)rightPanel.getComponent(10);
//	            	saveSet.postProcessingBooleanMemoryForLists[1][9]=temp.isSelected();
//	            	temp=(JCheckBox)rightPanel.getComponent(11);
//	            	saveSet.postProcessingBooleanMemoryForLists[1][10]=temp.isSelected();
//	            	temp=(JCheckBox)rightPanel.getComponent(12);
//	            	saveSet.postProcessingBooleanMemoryForLists[1][11]=temp.isSelected();
//	            	
//	            	saveSet.postProcessingNumbersMemory[0][0]="100";//splitPane.getLeftComponent()
//	            	saveSet.postProcessingNumbersMemory[0][1]="100";
//	            	saveSet.postProcessingNumbersMemory[1][0]="100";//splitPane.getRightComponent()
//	            	saveSet.postProcessingNumbersMemory[1][1]="100";
	            	PostProcessingGUI.this.saveSet=saveSet;
	            	TokenObserver.clearCache();
	            	PostProcessingGUI.this.postProcessingFrame.dispose();
	            }
	        });
	        JButton standard = new JButton("Standard wiederherstellen");
	        standard.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent e) {
	            	//PostProcessingGUI.this.saveSet= new SaveSettings();
	            	PostProcessingGUI.this.saveSet.postProcessingNumbersMemory[0][0]="0";
	            	PostProcessingGUI.this.saveSet.postProcessingNumbersMemory[0][1]="10";
	            	PostProcessingGUI.this.saveSet.postProcessingNumbersMemory[1][0]="0";
	            	PostProcessingGUI.this.saveSet. postProcessingNumbersMemory[1][1]="10";
	       	        for(int i=1;i<PostProcessingGUI.this.saveSet.postProcessingBooleanMemoryForLists[0].length;i++)
	       	        {
	       	        	PostProcessingGUI.this.saveSet.postProcessingBooleanMemoryForLists[0][i]=false;
	       	        	PostProcessingGUI.this.saveSet.postProcessingBooleanMemoryForLists[1][i]=false;
	       	        }
	       	     PostProcessingGUI.this.saveSet.postProcessingBooleanMemoryForLists[0][0]=true;
	       	  PostProcessingGUI.this.saveSet.postProcessingBooleanMemoryForLists[1][0]=true;
	       	PostProcessingGUI.this.saveSet.postProcessingBooleanMemoryForLists[0][2]=true;
	       	PostProcessingGUI.this.saveSet.postProcessingBooleanMemoryForLists[1][2]=true;
	        
	       	PostProcessingGUI.this.saveSet.postProcessingBooleanMemoryForLists[0][5]=true;
	       	PostProcessingGUI.this.saveSet.postProcessingBooleanMemoryForLists[1][5]=true;
	       	        for(int i=1;i<PostProcessingGUI.this.saveSet.postProcessingBooleanMemory.length;i++)
	       	        {
	       	        	PostProcessingGUI.this.saveSet.postProcessingBooleanMemory[i]=false;
	       	        }
	       	     PostProcessingGUI.this.saveSet.postProcessingBooleanMemory[0]=true;
	            	PostProcessingGUI.this.postProcessingFrame.dispose();
	            	@SuppressWarnings("unused")
					PostProcessingGUI pg= new PostProcessingGUI(PostProcessingGUI.this.saveSet);
	            }
	        });
	        //hier
	        
	        
	        mainPanel.add(button);
	        mainPanel.add(standard);
	        mainPanel.setLayout( new javax.swing.BoxLayout(
	                mainPanel, javax.swing.BoxLayout.Y_AXIS ) );
	        postProcessingFrame.add(mainPanel);
 try{
				
				
				Image image = ImageIO.read(SimpleGUI.getIconFile());
				postProcessingFrame.setIconImage(image);
				} catch(IOException ex){
				ex.printStackTrace();
				}
 
	        //postProcessingFrame.add(mainFrame);
	        postProcessingFrame.setVisible(true);
	 }
	 
	 /**
	  * creates subpanels
	  * @param headlineName The Name of a subpanel
	  * @param memo the savesettings for the subpannel
	  * @param numbers the savesettingnumbers for the subpannel
	  * @return
	  */
	 public JPanel subPanels(String headlineName, boolean[] memo, String[] numbers)
	 {
		 
		  JPanel panel = new JPanel();
		  JCheckBox[] checkBoxes = new JCheckBox[9];
		  Box[] panels = new Box[9];
		 //postProcessingFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

	        checkBoxes[0]=new JCheckBox("relative Häufigkeiten",memo[0]);
	        checkBoxes[1]=new JCheckBox("TFIDF-Werte",memo[1]);
	        checkBoxes[2]=new JCheckBox("Signifikanzwerte",memo[4]);//verschiedene maße
	        checkBoxes[3]=new JCheckBox("Extrema-Normierung",memo[9]);
	        checkBoxes[4]=new JCheckBox("Verrechnung mit Gewichten",memo[10]);
	        checkBoxes[5]=new JCheckBox("Frequenz Minimum",memo[11]);//textfeld
	        checkBoxes[6]=new JCheckBox("Ohne Funktion",memo[12]);//textfeld
	        checkBoxes[7]=new JCheckBox("Reduktion auf NVA",memo[13]);
	        checkBoxes[8]=new JCheckBox("Zufällige Ergebnisse",memo[14]);//textfeld
//	        checkBoxes[6]=new JCheckBox("Reduktion auf NVA",memo[13]);
//	        checkBoxes[7]=new JCheckBox("Zufällige Ergebnisse",memo[14]);//textfeld

	        //unter allem
//	        checkBoxes[8]=new JCheckBox("Vereinigung");
//	        checkBoxes[9]=new JCheckBox("Schnitt");
//	        checkBoxes[10]=new JCheckBox("Minus");
//	        checkBoxes[11]=new JCheckBox("Ohne");
//	        checkBoxes[12]=new JCheckBox("Signifikanzen durch Referenzliste");
	        
	        for(int i=0;i<checkBoxes.length;i++)   
	        {
	        	checkBoxes[i].setAlignmentX(0);
	            panels[i]= new Box(BoxLayout.X_AXIS);
		        panels[i].add(checkBoxes[i]);
		        panels[i].setAlignmentX(0);

		    
		        
	        }
	        JRadioButton[] radio0 = new JRadioButton[2];
	         radio0[0] = new JRadioButton ("Satz basiert", memo[2]);
	         radio0[1] = new JRadioButton ("Story basiert", memo[3]);
	         ButtonGroup group0 = new ButtonGroup();
	         for(int i=0; i<radio0.length;i++)
	         {
	        	 group0.add(radio0[i]);
	        	 panels[1].add(radio0[i]);
	         }
	        JRadioButton[] radio = new JRadioButton[4];
	         radio[0] = new JRadioButton ("Dice", memo[5]);
	         radio[1] = new JRadioButton ("Tanimoto", memo[6]);
	         radio[2] = new JRadioButton ("G-Test", memo[7]);
	         radio[3] = new JRadioButton ("Mutual Information", memo[8]);
	         ButtonGroup group = new ButtonGroup();
	         for(int i=0; i<radio.length;i++)
	         {
	        	 group.add(radio[i]);
	        	 panels[2].add(radio[i]);
	         }
	        
	        JTextField frequencMin = new JTextField(numbers[0]);
	        Dimension maximumSize = new Dimension();
	        maximumSize.setSize(50, 20);
	        frequencMin.setMaximumSize(maximumSize);
	        JTextField random = new JTextField(numbers[1]);
	        random.setMaximumSize(maximumSize);
	        panels[5].add(frequencMin);
	        panels[8].add(random);
	        //panel.setAlignmentX(0);
	        panel.setLayout(new javax.swing.BoxLayout(
	        		panel, javax.swing.BoxLayout.Y_AXIS ));
	        JLabel headline = new JLabel (headlineName);

	        panel.add(headline);
	        for(int i=0;i<panels.length;i++)   
	        {
	        	
	        	panel.add(panels[i]);
	        }


	        return panel;
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
