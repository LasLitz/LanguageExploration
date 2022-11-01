package wIwatW;

import java.awt.Dimension;


import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;



import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
/**
 * GUI: Window for choose-corpora-option of the test-GUI
 * @author Lasse
 *
 */

public class ChooseCorporaGUI {
	 private  JDialog chooseCFrame = new JDialog();
	private JPanel mainPanel= new JPanel();
	private JLabel label1;
    private JLabel label2; 
	private SaveSettings saveSet;
	private JButton[] dialogButtons = new JButton[2];
	

private MetaCorpus meta;


    private ArrayList<String> arrayList = new ArrayList<String>();
    private JList<Object> listGUI=new JList<Object>(arrayList.toArray());
    
    /**
     * constructs the option window: "choose corpora"
     * @param saveSet represents the taken options
     * @param meta the metacorpus for displaying all corpora within it
     */
	public ChooseCorporaGUI(SaveSettings saveSet, MetaCorpus meta)
	{
		this.meta=meta;
    Dimension maximumSize = new Dimension();
    maximumSize.setSize(400, 20);
 this.saveSet=saveSet;
 chooseCFrame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
 //featureFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
 chooseCFrame.setTitle("Pfad-Angaben");
 try{
		
		
		Image image = ImageIO.read(SimpleGUI.getIconFile());
		chooseCFrame.setIconImage(image);
		} catch(IOException ex){
		ex.printStackTrace();
		}
 
 chooseCFrame.setSize(700,500);
 chooseCFrame.setModal(true);
    
JButton click = new JButton("Anwenden");
click.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
    	    	
        	ChooseCorporaGUI.this.saveSet=saveSet;
        	
        	
        	ChooseCorporaGUI.this.chooseCFrame.dispose();
            }
        });

arrayList.clear(); 

arrayList=ChooseCorporaGUI.this.meta.getCorporaListAsString();
listGUI.setListData(arrayList.toArray());
	listGUI.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
	listGUI.setLayoutOrientation(JList.VERTICAL);
	listGUI.setFixedCellHeight(20);
	listGUI.setFixedCellWidth(250);
	listGUI.setVisibleRowCount(-1);

    
	
    label1= new JLabel(this.saveSet.chosenCorporaNames.toString());
    label2= new JLabel(this.saveSet.chosenCorporaNames2.toString());
  
   
   dialogButtons[0]=new JButton("Auswahl der Corpora-Hauptliste");
	dialogButtons[0].addActionListener(new java.awt.event.ActionListener() {
       public void actionPerformed(java.awt.event.ActionEvent e) {
       	 // Dateiauswahldialog wird erzeugt...

           ChooseCorporaGUI.this.saveSet.chosenCorporaNames.clear();
           for(Object s:listGUI.getSelectedValuesList())
           {
           	String name=s.toString();
           	ChooseCorporaGUI.this.saveSet.chosenCorporaNames.add(name);
           }
           ChooseCorporaGUI.this.label1.setText(ChooseCorporaGUI.this.saveSet.chosenCorporaNames.toString());
       }
   });
	dialogButtons[1]=new JButton("Auswahl der Corpora-Referenzliste");
	dialogButtons[1].addActionListener(new java.awt.event.ActionListener() {
       public void actionPerformed(java.awt.event.ActionEvent e) {
       	 // Dateiauswahldialog wird erzeugt...
           
           //List<String> chosenCorpora = listGUI.getSelectedValuesList();
           ChooseCorporaGUI.this.saveSet.chosenCorporaNames2.clear();
           for(Object s:listGUI.getSelectedValuesList())
           {
           	String name=s.toString();
           	ChooseCorporaGUI.this.saveSet.chosenCorporaNames2.add(name);
           }
           ChooseCorporaGUI.this.label2.setText(ChooseCorporaGUI.this.saveSet.chosenCorporaNames2.toString());
       }
   });

  Box b = new Box(BoxLayout.X_AXIS);
  Box b1 = new Box(BoxLayout.Y_AXIS);
  Box b2 = new Box(BoxLayout.Y_AXIS);
  b.setAlignmentX(0);
  b1.setAlignmentX(0);
  b2.setAlignmentX(0);
  b1.add(label1);
  b1.add(dialogButtons[0]);
  b2.add(label2);
  b2.add(dialogButtons[1]);
  b.add(b1);
  b.add(new JLabel("   "));
  b.add(b2);
    mainPanel.add(listGUI);
    
	 mainPanel.add(b);
	 mainPanel.add(click);
    mainPanel.setLayout( new javax.swing.BoxLayout(
            mainPanel, javax.swing.BoxLayout.Y_AXIS ) );
    chooseCFrame.add(mainPanel);
    //featureFrame.add(mainFrame);
    chooseCFrame.setVisible(true);
}


	/**
	 * 
	 * @return the savesettings
	 */
public SaveSettings getSaveSettings()
{
 return saveSet;
}
}
