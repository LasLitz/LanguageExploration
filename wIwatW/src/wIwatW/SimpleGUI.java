package wIwatW;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

/**
 * GUI: Mainclass for test-gui
 * @author Lasse
 *
 */
public class SimpleGUI  {
	 /**
	 * 
	 */
	public final static String SENTENCEEND=".";
	public final static String SENTENCECONNECTOR="und";
	//private static final Color HINTERGRUND = new Color(54,61,68);
	//private static final Color HINTERGRUND = new Color(47,79,79);
	private static final Color HINTERGRUND = new Color(51,95,96);
	//private static final Color HINTERGRUND = new Color(0,128,128);
	//private static final Color HINTERGRUND = new Color(46,139,87);
	
	
	private static File iconFile = new File("iconMaschine.png");
	
	private JFrame mainFrame = new JFrame("MASCHINE");
	private JSplitPane mainPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
	private JScrollPane leftScrollPane;
	private JScrollPane rightScrollPane;
	private JPanel leftPanel = new JPanel();
	private JPanel rightPanel = new JPanel();
	
	private boolean mcloaded=false;
	private MetaCorpus meta;
	SaveSettings saveSet = new SaveSettings();
	
	private int menuBarSize=3;
	private JMenuBar bar = new JMenuBar();
	private JMenu[] menuBar = new JMenu[menuBarSize];
	private  JMenuItem[][] items = new  JMenuItem[menuBarSize][];
	
	Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize ();
    private JTextPane textArea;
   // private ArrayList<String> arrayList = new ArrayList<String>();
    private AddAllListModel<String> data = new AddAllListModel<>();  //defaultlistModel
    private JList<String> listGUI=new JList<String>(data);
    private SearchThread st= new SearchThread();
    private FutureSearchThread fst=new FutureSearchThread();
    private ArrayList<String> clickedWords = new ArrayList<String>();

    protected static File getIconFile()
    {
    	return iconFile;
    }
    /**
     * sets metacorpus
     * @param metaCorpus
     */
    public void setMetaCorpus(MetaCorpus metaCorpus)
    {
    	this.meta=metaCorpus;
    }
    private boolean isMaximized(int state) {
        return (state & mainFrame.MAXIMIZED_BOTH) == mainFrame.MAXIMIZED_BOTH;
    }

    
    /**
     * constructs a demo gui
     * @throws IOException
     */
	public SimpleGUI(String decodedPath) throws IOException
	{
		//System.out.println(screensize.getHeight()+" "+screensize.getWidth());
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		System.out.println(decodedPath+File.separator+"iconMaschine.png");
		SimpleGUI.iconFile=new File(decodedPath+File.separator+"iconMaschine.png");
		
		Font headlineFont = new Font("Cambria", Font.BOLD,16);
		Font listFont = new Font("Courier", Font.BOLD,16);
		Font font = new Font("Cambria", Font.PLAIN,14);
		UIManager.put("Button.opaque", true);
		
		UIManager.put("Button.background", Color.gray);
		UIManager.put("Button.font",headlineFont);
		UIManager.put("Panel.background",HINTERGRUND);
		UIManager.put("List.background",HINTERGRUND);
		//UIManager.put("InternalFrame.background",Color.black);
		UIManager.put("List.foreground",Color.white);
//		UIManager.put("List.selectionBackground",Color.white);
//		UIManager.put("List.selectionForeground",Color.red);
//		UIManager.put("List.font",font);
		UIManager.put("RadioButton.background",HINTERGRUND);
		UIManager.put("RadioButton.font",font);
		UIManager.put("CheckBox.background",HINTERGRUND);
		UIManager.put("CheckBox.font",font);
		UIManager.put("InternalFrame.font",font);
		UIManager.put("List.font",font);
			UIManager.put("MenuItem.opaque", true);
			UIManager.put("Menu.opaque", true);
		//UIManager.put("MenuBar.opaque", true);
			UIManager.put("Menu.useMenuBarBackgroundForTopLevel", true); 	
		
			UIManager.put("TextPane.font",font);
			UIManager.put("Label.font",headlineFont);
			
		//UIManager.put("Menu.background", Color.RED);
		UIManager.put("MenuBar.background", Color.WHITE);
		UIManager.put("Menu.font", headlineFont);
		UIManager.put("MenuItem.background", Color.WHITE);
		UIManager.put("MenuItem.font", font);
		
		//UIManager.put("ScrollBar.thumb", new ColorUIResource( Color.RED));
//		UIManager.put("ScrollBar.track", Color.green);
//		UIManager.put("ScrollBar.trackForeground", Color.green);
//		UIManager.put("ScrollBar.thumbShadow", Color.green);
//		UIManager.put("ScrollBar.trackHighlight", Color.green);
		
		
		
//		String pathSave=".\\res\\wiwatWmeta1.ser";
		
//		ConsoleIllustrator.loadAndSaveFilledMetaCorp(".\\Testfiles",  pathSave);
		

		menuBar[0]= new JMenu("Datei", true);
		data.addListDataListener( new ListDataListener(){
			
			 
			@Override
			public void contentsChanged(ListDataEvent e) {
				
				ArrayList<TranslationAlgorithmValue> list = new ArrayList<TranslationAlgorithmValue>();
            	ArrayList<TranslationAlgorithmValue> list2 = new ArrayList<TranslationAlgorithmValue>();
            	TranslationAlgorithm[] tAarr=TranslationAlgorithm.values();
            	for(int i=0;i<tAarr.length;i++)
            	{
            		if(saveSet.featureBooleanMemory[0][i])
            		{
            			String content=saveSet.featureNumbersMemory[0][i];
            			if(content.equals(""))content="1";
            			list.add(new TranslationAlgorithmValue(tAarr[i],Integer.parseInt(content)));
            		}
              		if(saveSet.featureBooleanMemory[1][i])
            		{
              			String content=saveSet.featureNumbersMemory[0][i];
            			if(content.equals(""))content="1";
            			list2.add(new TranslationAlgorithmValue(tAarr[i],Integer.parseInt(content)));
            		}
            	}
            	if(list.isEmpty())
            	{
            		list.add(new TranslationAlgorithmValue(TranslationAlgorithm.NONE,1));
            	}
            	if(list2.isEmpty())
            	{
            		list2.add(new TranslationAlgorithmValue(TranslationAlgorithm.NONE,1));
            	}
				 ArrayList<String> corpusList1=new ArrayList<String>(); 
				 ArrayList<String> corpusList2=new ArrayList<String>();
				 for (String s:saveSet.chosenCorporaNames)
				 {
					 corpusList1.add(s);
				 }
				 for (String s:saveSet.chosenCorporaNames2)
				 {
					 corpusList2.add(s);
				 }
				int numberOfFollowers=1+Integer.parseInt(saveSet.darstellungNumbersMemory[0]);
				int multipleMatchesWeight=1;
				int sameStoryWeight=1;
				
				// TODO Auto-generated method stub
				//System.out.println("change");
				//fst=new FutureSearchThread(data);
				if(fst!=null&&fst.isAlive()==true)
				{
					
					//fst.stop();
					fst.interrupt();
				}
				//fst.set(listGUI,data,meta, list, list2,corpusList1,corpusList2,numberOfFollowers,multipleMatchesWeight,sameStoryWeight, saveSet);
				
			
				fst= new FutureSearchThread(listGUI,data,meta, list, list2,corpusList1,corpusList2,numberOfFollowers,multipleMatchesWeight,sameStoryWeight, saveSet);
				fst.setPriority(1);
				
		fst.start();
		
			}

			@Override
			public void intervalAdded(ListDataEvent e) {
				// TODO Auto-generated method stub
				//System.out.println("add");
				
		
			}

			@Override
			public void intervalRemoved(ListDataEvent e) {
				//System.out.println("remove");
				
			}
			

		});
		//data.addElement("test");
		

		//Menüs
		items[0]= new JMenuItem[3];
		items[0][0]=new  JMenuItem("Speichern");
		items[0][0].addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                // Dateiauswahldialog wird erzeugt...
                JFileChooser fc = new JFileChooser();
                // ... und angezeigt
                
                fc.showSaveDialog(mainFrame);
                
                String path="";
                try {
                	path=fc.getSelectedFile().getAbsolutePath()+".txt";
                    FileWriter f = new FileWriter (path);
                    f.write(textArea.getText());
                    f.close();
                  }
                catch (Exception e1) {
                    System.out.println("Fehler: "+e.toString());
                  }
                
                
            }
        });
		items[0][1]=new  JMenuItem("Öffnen");
		items[0][1].addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                // Dateiauswahldialog wird erzeugt...
                JFileChooser fc = new JFileChooser();
                // ... und angezeigt
                fc.showOpenDialog(mainFrame);
                
                String path="";
               
                String newText="";
                TextImporter t= new TextImporter();
                try {
                	 path=fc.getSelectedFile().getAbsolutePath();
                	newText=t.importFileAsString(path);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
				}
                textArea.setText(newText);
            }
        });
		items[0][2]=new  JMenuItem("Leeren");
		items[0][2].addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
            	textArea.setText("");
				//arrayList.clear(); 
				data.clear();
				//data.addElement("Test");
				//listGUI.setModel(data);
				//listGUI.setListData(arrayList.toArray());
            	//listGUI.setVisibleRowCount(-1);

            }
        });
		menuBar[1]= new JMenu("Parameter-Optionen");
		items[1]= new JMenuItem[3];
		items[1][0]=new  JMenuItem("Feature-Auswahl");
		items[1][0].addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
            	FeatureGUI fg= new FeatureGUI(saveSet);
            	saveSet=fg.getSaveSettings();
            }
        });
		items[1][1]=new  JMenuItem("Postprocessing");
		items[1][1].addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
            	PostProcessingGUI pg= new PostProcessingGUI(saveSet);
            	saveSet=pg.getSaveSettings();
            }
        });
		items[1][2]=new  JMenuItem("Darstellung");
		items[1][2].addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
            	DarstellungsGUI dg= new DarstellungsGUI(saveSet);
            	saveSet=dg.getSaveSettings();
            }
        });
		
		
		menuBar[2]= new JMenu("Pfad-Einstellungen");
		items[2]= new JMenuItem[2];
		items[2][0]=new  JMenuItem("Pfad-Einstellungen");
		items[2][0].addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
            	PathGUI pag= new PathGUI(saveSet,meta);
            	saveSet=pag.getSaveSettings();
            	
            	//SimpleGUI.this.meta=Serializator.loadMetaCorpus(saveSet.pathMemory[0]+"\\"+saveSet.pathMemory[2]);
            }
        });
		items[2][1]=new  JMenuItem("Corpus Auswahl");
		items[2][1].addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
            	
            	if(mcloaded==false)
            	{
            		String pathSave=saveSet.pathMemory[0]+File.separator+saveSet.pathMemory[2];
            		try {
    					ConsoleIllustrator.loadAndSaveFilledMetaCorp(saveSet.pathMemory[1],  pathSave);
    				} catch (IOException e1) {
    					e1.printStackTrace();
    				}
            		MetaCorpus meta=Serializator.loadMetaCorpus(pathSave);
            		setMetaCorpus(meta);
            		mcloaded=true;
            	}
            	ChooseCorporaGUI pag= new ChooseCorporaGUI(saveSet,meta);
            	saveSet=pag.getSaveSettings();
            	
            	//SimpleGUI.this.meta=Serializator.loadMetaCorpus(saveSet.pathMemory[0]+"\\"+saveSet.pathMemory[2]);
            }
        });

		//mainFrame.setFont(headlineFont);
		if(screensize.height<750||screensize.width<950)
		{
			mainFrame.setSize(screensize.width, screensize.height);
		}
		else
		{
			mainFrame.setSize(950, 750);
		}
		for(int i=0; i<menuBar.length;i++)	
		{
			
			for(int j=0;j<items[i].length;j++)
			{
				menuBar[i].add(items[i][j]);
				//menuBar[i].setFont(headlineFont);
				//items[i][j].setBackground(Color.white);
				//items[i][j].setFont(font);
				//menuBar[i].setBackground(Color.white);
				
			}
			bar.add(menuBar[i]);
			menuBar[i].setFocusPainted(false);
		}

	
		
//		bar.set
//		bar.setBackground(Color.white);
//		bar.setBorder(new LineBorder(Color.white));
		mainFrame.setJMenuBar(bar);
		

		
		 

		
		try{
			
			//System.out.println(iconFile.exists());
			Image image = ImageIO.read(iconFile);
			mainFrame.setIconImage(image);
			} catch(IOException ex){
			ex.printStackTrace();
			}
//mainFrame.setUndecorated(true);
		
		//JPanel
//		mainPanel.setBackground(Color.gray);
//		leftPanel.setBackground(Color.GRAY);
//		
//		rightPanel.setBackground(Color.gray);
//		mainPanel.setBackground(Color.white);
//		leftPanel.setBackground(Color.black);
//		
//		rightPanel.setBackground(Color.black);

		 DocumentListener documentListener = new DocumentListener() 
	        {

			// private SearchThread st=new SearchThread(); //<-funktioniert an dieser Stelle
			 //private FutureSearchThread fst;
			
	            public void changedUpdate(DocumentEvent documentEvent) 
	            {
	              printIt(documentEvent);
	            }
	            public void insertUpdate(DocumentEvent documentEvent) 
	            {
	              printIt(documentEvent);
	            }
	            public void removeUpdate(DocumentEvent documentEvent) 
	            {
	              printIt(documentEvent);
	            }
	  
	           
				private void printIt(DocumentEvent documentEvent) 
	            {
	            	//SearchThread st=null;
//	    			if(fst!=null&&fst.isAlive()==true)
//					{
//						
//						//fst.stop();
//						fst.interrupt();
//					}
	              DocumentEvent.EventType type = documentEvent.getType();
	              //String typeString = null;
	              if (type.equals(DocumentEvent.EventType.CHANGE)) 
	              {
	                //typeString = "Change";
	              }  
	              else if (type.equals(DocumentEvent.EventType.INSERT)) 
	              {
	                //typeString = "Insert";
	                String str= textArea.getText();
	                
	                if(str.endsWith(" ")||str.endsWith(String.format("%n")))
	                {
	                	
	               	ArrayList<TranslationAlgorithmValue> list = new ArrayList<TranslationAlgorithmValue>();
                	ArrayList<TranslationAlgorithmValue> list2 = new ArrayList<TranslationAlgorithmValue>();
                	TranslationAlgorithm[] tAarr=TranslationAlgorithm.values();
                	for(int i=0;i<tAarr.length;i++)
                	{
                		if(saveSet.featureBooleanMemory[0][i])
                		{
                			String content=saveSet.featureNumbersMemory[0][i];
                			if(content.equals(""))content="1";
                			list.add(new TranslationAlgorithmValue(tAarr[i],Integer.parseInt(content)));
                		}
                  		if(saveSet.featureBooleanMemory[1][i])
                		{
                  			String content=saveSet.featureNumbersMemory[0][i];
                			if(content.equals(""))content="1";
                			list2.add(new TranslationAlgorithmValue(tAarr[i],Integer.parseInt(content)));
                		}
                	}
                	if(list.isEmpty())
                	{
                		list.add(new TranslationAlgorithmValue(TranslationAlgorithm.NONE,1));
                	}
                	if(list2.isEmpty())
                	{
                		list2.add(new TranslationAlgorithmValue(TranslationAlgorithm.NONE,1));
                	}
//            		list.add(new TranslationAlgorithmValue(TranslationAlgorithm.NONE,1));
//            		//list.add(new TranslationAlgorithmValue(TranslationAlgorithm.POSTAGS,1));
//            		//list.add(new TranslationAlgorithmValue(TranslationAlgorithm.SYLLABLES,1));
//            		//list.add(new TranslationAlgorithmValue(TranslationAlgorithm.CHARNUMBER,1));
//            		//list.add(new TranslationAlgorithmValue(TranslationAlgorithm.SYLLABLESANDCHARNUMBER,1));
//            		//list.add(new TranslationAlgorithmValue(TranslationAlgorithm.COLOGNEPHONETIC,1));
//            		//list.add(new TranslationAlgorithmValue(TranslationAlgorithm.POSTAGSFRE,1));
//            		list.add(new TranslationAlgorithmValue(TranslationAlgorithm.POSTAGSANDTEXT,20));
//            		//list.add(new TranslationAlgorithmValue(TranslationAlgorithm.ONLYVOWELSANDONLYCONSONANTS,1));
//            		//list.add(new TranslationAlgorithmValue(TranslationAlgorithm.THESAURUSSYNONYMS,1));
//            		//list.add(new TranslationAlgorithmValue(TranslationAlgorithm.BASEFORMS,1));
//            		//list.add(new TranslationAlgorithmValue(TranslationAlgorithm.SACHGEBIETE,1));
//            		//list.add(new TranslationAlgorithmValue(TranslationAlgorithm.SACHGEBIETEANDPOSTAGS,1));//immer *anzahl der baseformen
//            		//list.add(new TranslationAlgorithmValue(TranslationAlgorithm.COLOGNEPHONETICANDPOSTAGS,1));
//            		//list.add(new TranslationAlgorithmValue(TranslationAlgorithm.THESAURUSSYNONYMSANDPOSTAGS,1));
//            		
//            		
//            		//list2.add(new TranslationAlgorithmValue(TranslationAlgorithm.NONE,1));
//            		//list2.add(new TranslationAlgorithmValue(TranslationAlgorithm.CHARNUMBER,1));
//            		//list2.add(new TranslationAlgorithmValue(TranslationAlgorithm.SOUNDEX,1));
//            		//list2.add(new TranslationAlgorithmValue(TranslationAlgorithm.NONE,1));
//            		//list2.add(new TranslationAlgorithmValue(TranslationAlgorithm.POSTAGS,1));
//            		//list2.add(new TranslationAlgorithmValue(TranslationAlgorithm.COLOGNEPHONETIC,2));
//            		//list2.add(new TranslationAlgorithmValue(TranslationAlgorithm.ONLYCONSONANTS,1));
//            		//list2.add(new TranslationAlgorithmValue(TranslationAlgorithm.ONLYVOWELS,1));
//            		//list2.add(new TranslationAlgorithmValue(TranslationAlgorithm.SYLLABLESANDCHARNUMBER,1));
//            		//list2.add(new TranslationAlgorithmValue(TranslationAlgorithm.SYLLABLES,1));
//            		//list2.add(new TranslationAlgorithmValue(TranslationAlgorithm.POSTAGSANDTEXT,1));
//            		//list2.add(new TranslationAlgorithmValue(TranslationAlgorithm.POSTAGSFRE,1));
//            		list2.add(new TranslationAlgorithmValue(TranslationAlgorithm.COLOGNEPHONETICANDPOSTAGS,1));
    				 ArrayList<String> corpusList1=new ArrayList<String>(); 
    				 ArrayList<String> corpusList2=new ArrayList<String>();
    				 for (String s:saveSet.chosenCorporaNames)
    				 {
    					 corpusList1.add(s);
    				 }
    				 for (String s:saveSet.chosenCorporaNames2)
    				 {
    					 corpusList2.add(s);
    				 }
//    				 corpusList1.add("Testfiles1");
//    				 corpusList2.add("ReferenceCorpus");
    				int numberOfFollowers=1+Integer.parseInt(saveSet.darstellungNumbersMemory[0]);
    				int multipleMatchesWeight=1;
    				int sameStoryWeight=1;
    				
    				//hier
    				String[] splittedArray = str.split(" ");
                	//System.out.println(saveSet.darstellungNumbersMemory[1]);
                	
                	if(saveSet.darstellungNumbersMemory[1].equals("1"))
                	{
	                	String word=splittedArray[splittedArray.length-1];
	                	word="";
	                	for(int i=Integer.parseInt(saveSet.darstellungNumbersMemory[1])-1;i>-1;i--)
	                		
	                	{
	                		int sub=splittedArray.length-1-i;
	                		if(sub>-1)
	                		{
	                			if(i==Integer.parseInt(saveSet.darstellungNumbersMemory[1])-1)
	                				word+=splittedArray[sub];	
	                			else
	                				word+=" "+splittedArray[sub];
	                		}
	                		
	                		
	                	}
	    			
	    				
	    			//	System.out.println(word);
	    				//word=splittedArray[splittedArray.length-1];
	            		String path = SimpleGUI.class.getProtectionDomain().getCodeSource().getLocation().getPath();
	 	       			String decodedPath="";
	 	       			try {
	 	       				decodedPath = URLDecoder.decode(path, "UTF-8");
	 	       			} catch (UnsupportedEncodingException e) {
	 	       				e.printStackTrace();
	 	       			}
	 	       			File test= new File(decodedPath);
	 	       			String write=data.containsWord(word, numberOfFollowers);
	 	       		//	System.out.println(write);
	               		if(write!=null)
	            		{
	     	       			decodedPath=test.getParent()+File.separator+"analyse"+File.separator+"clicked.txt";
	     	       			AnalyzeWordUsage.writeFile(decodedPath,write);
	            		}
	               		else
	               		{
	               			decodedPath=test.getParent()+File.separator+"analyse"+File.separator+"selbstgenommen.txt";
	     	       			AnalyzeWordUsage.writeFile(decodedPath,word);
	               		}
                	}
               		//bis hier
               		
    				
	                	if(str.endsWith(SENTENCEEND+" ")==false)
	                	{
	                		
	                		if(str.contains(","))str=str.replaceAll(",", "");
	                		String[] tokens=Sentence.tokenizeSimple(str);
	                		if(tokens.length>5)
	                		{
	                			str="";
	                			for(int i=4;i>-1;i--)
	                			{
	                				str+=" "+tokens[tokens.length-i-1];
	                			}
	                			str+=" ";
	                		}
	                		
	         

	                	
	                	//textfeld2.setText( ConsoleIllustrator.showSeekerResultsFromMetaCorpus(meta, str, list, list2,corpusList1,corpusList2,numberOfFollowers,multipleMatchesWeight,sameStoryWeight));
	                		
	         
//	                		arrayList.clear();  vielleicht wichtig
	                	
	    				//arrayList=ConsoleIllustrator.showSeekerResultsFromMetaCorpusList(meta, str, list, list2,corpusList1,corpusList2,numberOfFollowers,multipleMatchesWeight,sameStoryWeight, saveSet);
	    				//System.out.println("------------------->"+Thread.activeCount());
//    				ExecutorService executor = Executors.newSingleThreadExecutor();
//    				executor.submit(new Runnable() {
//    				
//					@Override
//					public void run() {
//						 st= new SearchThread(listGUI,meta, suche, list, list2,corpusList1,corpusList2,numberOfFollowers,multipleMatchesWeight,sameStoryWeight, saveSet,fst);
//	    					
//						st.start();
//						
//					}});
//	    			executor.submit(new Runnable() {
//
//						@Override
//						public void run() {
//							 fst= new FutureSearchThread(listGUI,meta, list, list2,corpusList1,corpusList2,numberOfFollowers,multipleMatchesWeight,sameStoryWeight, saveSet);
//		 		    			
//							fst.start();
//							
//						}});
	    				
	    				if(st!=null&&st.isAlive()==true)
	    				{
	    					
	    					//st.stop();
	    					st.interrupt();
	    				}
	    				// st.set(listGUI,data, meta, str, list, list2,corpusList1,corpusList2,numberOfFollowers,multipleMatchesWeight,sameStoryWeight, saveSet);
	    				 
	    				System.out.println("Letztes Suchwort: "+str.split(" ")[str.split(" ").length-1]);
	    				st=new SearchThread(listGUI,data, meta, str, list, list2,corpusList1,corpusList2,numberOfFollowers,multipleMatchesWeight,sameStoryWeight, saveSet);
	    				 st.setPriority(5);
	    				st.start();
	    	
	    				
//	    					 fst= new FutureSearchThread(listGUI,meta, list, list2,corpusList1,corpusList2,numberOfFollowers,multipleMatchesWeight,sameStoryWeight, saveSet);
//	 		    			
//	    				fst.start();
//	    				
	    				
	                	//listGUI.setVisibleRowCount(-1);

	        	        //add(listGUI);
	                }
	                	else{
	                		//FrequencyList naiveTest=Seeker.searchSentenceStartNaive(meta,0);
	                		ArrayList<String> ergList=new ArrayList<String>();
	                		ergList=ConsoleIllustrator.showSeekerResultsFromMetaCorpusListForSpeechGeneration(meta, SENTENCEEND, list, list2,corpusList1,corpusList2,numberOfFollowers,multipleMatchesWeight,sameStoryWeight, saveSet);
	                		
	                		//naiveTest=naiveTest.sortAfterFrequency();
	            			//frequenciesToSignificance(metaCorp, 0, string, SignificanceMeasureEnum.DICE)
//	            			double count=0;
//	            			for(int i=0; i<naiveTest.size();i++)
//	            			{
//	            				String str1="";
//	            				for(int j=0; j<naiveTest.get(i).size();j++)
//	            				{
//	            					str1+=naiveTest.get(i).get(j)+" ";
//	            					
//	            					
//	            				}
//	            				count=naiveTest.get(i).getValue();
//	            				
//	            				str1+=" "+"|"+count+"|";
//	            				ergList.add(str1);
//	            				//System.out.println(str1);
//	            				
//	            			}
	                		
	                   		
	                   		
	                		data.clear();
	                		
	                		
	                		
	                			data.addAll(ergList);
	                			
	                		//data=data2;
	                		//listGUI.setModel(data);
	                		// listGUI.setListData(ergList.toArray());
	                		
	                	}
	                
	              }  
	        
//	              System.out.print("Type : " + typeString);
//	              Document source = documentEvent.getDocument();
//	              
//	              int length = source.getLength();
//	              System.out.println("Length: " + length);
	                
	            }
	              else if (type.equals(DocumentEvent.EventType.REMOVE)) 
	              {
	                //typeString = "Remove";
	              }
	              //System.out.println(data.getElementAt(0));
	            }
				
	        };
		
        textArea = new JTextPane();
        //textArea.setOpaque(true);
        textArea.setPreferredSize(new Dimension(400,800));
    //    textArea.
        
       // textArea.revalidate();
//        textArea.setLineWrap(true);
//        textArea.setWrapStyleWord(true);
        textArea.getDocument().addDocumentListener(documentListener);
   //    textArea.setS
        //System.out.println(leftPanel.getHeight());
        //System.out.println(leftPanel.getWidth());
		leftPanel.add(textArea);
		data.clear();
//		for(String e: arrayList)vielleichtz wichtig
//		{
//			data.addElement(e);
//		}
		
		
//		DefaultListModel data2= new DefaultListModel();
//		
//		for(String e: arrayList)
//		{
//			data2.addElement(e);
//		}
//		data=data2;
		//listGUI.setModel(data);
		// listGUI.setListData(arrayList.toArray());
     	listGUI.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
     	listGUI.setLayoutOrientation(JList.VERTICAL);
     	listGUI.setFont(listFont);
     	listGUI.setForeground(Color.white);
    	listGUI.setBackground(HINTERGRUND);
     	listGUI.setSelectionBackground(Color.white);
     	listGUI.setSelectionForeground(Color.red);
     	listGUI.setFixedCellHeight(20);
     	listGUI.setFixedCellWidth(2000);
     	listGUI.setVisibleRowCount(-1);
     	listGUI.setCellRenderer(new TabCellRenderer(listGUI));
     	listGUI.addMouseListener(new MouseAdapter() {
     	    
			public void mouseClicked(MouseEvent evt) {
     	        if (evt.getClickCount() == 1) {
     	        	
     	        	String erg="";
     	        	int wortanzahl=Integer.parseInt(saveSet.darstellungNumbersMemory[1]);
     	        	String[] tempStringArray=listGUI.getSelectedValue().toString().split(" ");
     	        	//System.out.println(tempStringArray[1]);
     	        	for(int i=1;i<tempStringArray.length;i++)
     	        	{
     	        		//System.out.println(tempStringArray.length+" "+i);
//     	        		if(tempStringArray.length<=i+1)
//     	        		{
//     	        			//System.out.println(tempStringArray+" "+i);
//     	        			break;
//     	        		}
     	        			
     	        	if(i>1)
     	        	{
     	        		
     	        
     	        			
     	        		
     	        		erg+=" ";
     	        	}
     	        		erg+=tempStringArray[i];
     	        	}
     	        	
//     	        	String erg="";
//     	        	int wortanzahl=Integer.parseInt(saveSet.darstellungNumbersMemory[1]);
//     	        	String[] tempStringArray=listGUI.getSelectedValue().toString().split(" ");
//     	        	for(int i=0;i<wortanzahl;i++)
//     	        	{
//     	        		//System.out.println(tempStringArray.length+" "+i);
//     	        		if(tempStringArray.length<=i+1)
//     	        		{
//     	        			//System.out.println(tempStringArray+" "+i);
//     	        			break;
//     	        		}
//     	        			
//     	        	if(i>0)
//     	        	{
//     	        		
//     	        
//     	        			
//     	        		
//     	        		erg+=" ";
//     	        	}
//     	        		erg+=tempStringArray[i];
//     	        	}
     	        	
//     				String path = SimpleGUI.class.getProtectionDomain().getCodeSource().getLocation().getPath();
// 	       			String decodedPath="";
// 	       			try {
// 	       				decodedPath = URLDecoder.decode(path, "UTF-8");
// 	       			} catch (UnsupportedEncodingException e) {
// 	       				e.printStackTrace();
// 	       			}
// 	       			File test= new File(decodedPath);
// 	       			decodedPath=test.getParent()+File.separator+"analyse"+File.separator+"clicked.txt";
// 	       			//System.out.println(decodedPath);
// 	       			AnalyzeWordUsage.writeFile(decodedPath,erg);
// 	       			
     	            textArea.setText(textArea.getText()+erg+" ");
     	           listGUI.clearSelection();
     	            
     	        } 
     	    }
     	});
     	rightPanel.add(listGUI);
		JButton metaCorpusLoad= new JButton("Texte laden");
		metaCorpusLoad.setToolTipText("Lädt bereits eingelesene Texte und/oder liest neue Texte ein");
		//metaCorpusLoad.setBackground(Color.white);
		//metaCorpusLoad.setFont(new Font("Cambria", Font.BOLD,16));
		//metaCorpusLoad.setBorderPainted(false);
//		metaCorpusLoad.setHorizontalAlignment(SwingConstants.SOUTH);
//		metaCorpusLoad.setVerticalAlignment(SwingConstants.SOUTH);
//		metaCorpusLoad.addMouseListener(new MouseAdapter(){
//		
//             
//              @Override
//              public void mouseExited(MouseEvent e) {
//            	  metaCorpusLoad.setBackground(Color.white);
//            	  metaCorpusLoad.setForeground(Color.black);
//                  }
//                 
//              
//             
//              @Override
//              public void mouseEntered(MouseEvent e) {
//            	  metaCorpusLoad.setForeground(Color.white);
//            	  metaCorpusLoad.setBackground(Color.blue);
//                  }
//                 
//              
//			
//		});
		metaCorpusLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
            	
            	long time = System.currentTimeMillis( );
        		String pathSave=saveSet.pathMemory[0]+File.separator+saveSet.pathMemory[2];
        		System.out.println(pathSave);
        		try {
        			
					ConsoleIllustrator.loadAndSaveFilledMetaCorp(saveSet.pathMemory[1],  pathSave);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
        	
        		MetaCorpus meta=Serializator.loadMetaCorpus(pathSave);
        		
        		setMetaCorpus(meta);
        		mcloaded=true;
        		//System.out.println("Zeit: "+ (System.currentTimeMillis( ) - time)/1000 +"s" );
        		
        		System.out.println(TokenObserver.getTranslationsToWord("der", "po"));
        		
        		
        		System.out.println("Erstes Wort: (Referenz) "+meta.getToken(0, 0, 0, 0, 0));
        		System.out.println("Erstes Wort: (Haupt) "+meta.getToken(1, 0, 0, 0, 0));
        		
        		ArrayList<TranslationAlgorithmValue> list = new ArrayList<TranslationAlgorithmValue>();
            	ArrayList<TranslationAlgorithmValue> list2 = new ArrayList<TranslationAlgorithmValue>();
            	TranslationAlgorithm[] tAarr=TranslationAlgorithm.values();
            	for(int i=0;i<tAarr.length;i++)
            	{
            		if(saveSet.featureBooleanMemory[0][i])
            		{
            			String content=saveSet.featureNumbersMemory[0][i];
            			if(content.equals(""))content="1";
            			list.add(new TranslationAlgorithmValue(tAarr[i],Integer.parseInt(content)));
            		}
              		if(saveSet.featureBooleanMemory[1][i])
            		{
              			String content=saveSet.featureNumbersMemory[0][i];
            			if(content.equals(""))content="1";
            			list2.add(new TranslationAlgorithmValue(tAarr[i],Integer.parseInt(content)));
            		}
            	}
            	if(list.isEmpty())
            	{
            		list.add(new TranslationAlgorithmValue(TranslationAlgorithm.NONE,1));
            	}
            	if(list2.isEmpty())
            	{
            		list2.add(new TranslationAlgorithmValue(TranslationAlgorithm.NONE,1));
            	}
//        		list.add(new TranslationAlgorithmValue(TranslationAlgorithm.NONE,1));
//        		//list.add(new TranslationAlgorithmValue(TranslationAlgorithm.POSTAGS,1));
//        		//list.add(new TranslationAlgorithmValue(TranslationAlgorithm.SYLLABLES,1));
//        		//list.add(new TranslationAlgorithmValue(TranslationAlgorithm.CHARNUMBER,1));
//        		//list.add(new TranslationAlgorithmValue(TranslationAlgorithm.SYLLABLESANDCHARNUMBER,1));
//        		//list.add(new TranslationAlgorithmValue(TranslationAlgorithm.COLOGNEPHONETIC,1));
//        		//list.add(new TranslationAlgorithmValue(TranslationAlgorithm.POSTAGSFRE,1));
//        		list.add(new TranslationAlgorithmValue(TranslationAlgorithm.POSTAGSANDTEXT,20));
//        		//list.add(new TranslationAlgorithmValue(TranslationAlgorithm.ONLYVOWELSANDONLYCONSONANTS,1));
//        		//list.add(new TranslationAlgorithmValue(TranslationAlgorithm.THESAURUSSYNONYMS,1));
//        		//list.add(new TranslationAlgorithmValue(TranslationAlgorithm.BASEFORMS,1));
//        		//list.add(new TranslationAlgorithmValue(TranslationAlgorithm.SACHGEBIETE,1));
//        		//list.add(new TranslationAlgorithmValue(TranslationAlgorithm.SACHGEBIETEANDPOSTAGS,1));//immer *anzahl der baseformen
//        		//list.add(new TranslationAlgorithmValue(TranslationAlgorithm.COLOGNEPHONETICANDPOSTAGS,1));
//        		//list.add(new TranslationAlgorithmValue(TranslationAlgorithm.THESAURUSSYNONYMSANDPOSTAGS,1));
//        		
//        		
//        		//list2.add(new TranslationAlgorithmValue(TranslationAlgorithm.NONE,1));
//        		//list2.add(new TranslationAlgorithmValue(TranslationAlgorithm.CHARNUMBER,1));
//        		//list2.add(new TranslationAlgorithmValue(TranslationAlgorithm.SOUNDEX,1));
//        		//list2.add(new TranslationAlgorithmValue(TranslationAlgorithm.NONE,1));
//        		//list2.add(new TranslationAlgorithmValue(TranslationAlgorithm.POSTAGS,1));
//        		//list2.add(new TranslationAlgorithmValue(TranslationAlgorithm.COLOGNEPHONETIC,2));
//        		//list2.add(new TranslationAlgorithmValue(TranslationAlgorithm.ONLYCONSONANTS,1));
//        		//list2.add(new TranslationAlgorithmValue(TranslationAlgorithm.ONLYVOWELS,1));
//        		//list2.add(new TranslationAlgorithmValue(TranslationAlgorithm.SYLLABLESANDCHARNUMBER,1));
//        		//list2.add(new TranslationAlgorithmValue(TranslationAlgorithm.SYLLABLES,1));
//        		//list2.add(new TranslationAlgorithmValue(TranslationAlgorithm.POSTAGSANDTEXT,1));
//        		//list2.add(new TranslationAlgorithmValue(TranslationAlgorithm.POSTAGSFRE,1));
//        		list2.add(new TranslationAlgorithmValue(TranslationAlgorithm.COLOGNEPHONETICANDPOSTAGS,1));
				 ArrayList<String> corpusList1=new ArrayList<String>(); 
				 ArrayList<String> corpusList2=new ArrayList<String>();
				 for (String s:saveSet.chosenCorporaNames)
				 {
					 corpusList1.add(s);
				 }
				 for (String s:saveSet.chosenCorporaNames2)
				 {
					 corpusList2.add(s);
				 }
//Andere Anwendungen				 
//				 corpusList1.add("Testfiles1");
//				 corpusList2.add("ReferenceCorpus");
//				int numberOfFollowers=2;
//				int multipleMatchesWeight=100;
//				int sameStoryWeight=1;
//	    		String firstword=ConsoleIllustrator.showSeekerResultsFromMetaCorpusListForSpeechGeneration(meta, SENTENCEEND, list, list2,corpusList1,corpusList2,numberOfFollowers,multipleMatchesWeight,sameStoryWeight, saveSet).get(0).split(" ")[0];
	 
				 
//häufigePhrasen
//				 Seeker skr= new Seeker(meta, corpusList1, "", list);
//	 	 			skr.findCommonVerbPhrasesOnSentenceStart(1);
//	 			System.out.print("");
//	    		//System.out.println("fff "+firstword); //Konsolenvorführung
//
//
//	    		
//	    		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
//	    		
//	    		System.out.print("      ["+sdf.format(new Date())+"]      ");
//	    		int linecount=0;
//	    		int charcountInLine=0;
//	    		String anfang=" "+firstword;
//	    		boolean pointFlag=false;
//	    		int grenze=2;
//				for(int i=0;i<grenze;i++)
//				{
//					//firstword kürzen
//					//System.out.println(i);
//					String str=firstword;
//					String[] tokens=Sentence.tokenizeSimple(str);
//            		if(tokens.length>grenze)
//            		{
//            			str="";
//            			for(int j=grenze-1;j>-1;j--)
//            			{
//            				str+=" "+tokens[tokens.length-j-1];
//            			}
//            			str+=" ";
//            		}
//            		//System.out.println("");
//				ArrayList<String> listtemp=	ConsoleIllustrator.showSeekerResultsFromMetaCorpusListForSpeechGeneration(meta, str, list, list2,corpusList1,corpusList2,numberOfFollowers,multipleMatchesWeight,sameStoryWeight, saveSet);
//        		//if(listtemp.size()==0)firstword+=" und"; //andere Verbindungen
//        		
//				
//				
//				if(listtemp.size()==0)
//       			{
//       				firstword=" "+SENTENCECONNECTOR; //andere Verbindungen
//       			}
//        		
//        		else
//        		{
//        			String temp2=listtemp.get(0).split(" ")[0];
//        			
//        			if(temp2.equals(" "+SENTENCEEND+" ")||temp2.equals(SENTENCEEND))
//        			{
//        				
//        				
//        				firstword=temp2;
//        			}
//        			else
//        			{
//        				
//        					//System.out.println("woanders"+i+" firstword: "+firstword+" temp2: |"+temp2+"|");	
//        				
//            				firstword=" "+temp2;
//        				
//        			}
//        		
//        		}
//				
//				
//				firstword=anfang+firstword;
//				if(anfang!=null) anfang="";
//				if(pointFlag==true&&firstword.contains(SENTENCEEND)==false)
//				{
//					System.out.println("");
//					System.out.print("                   ");
//					linecount++;
//					charcountInLine=0;
//					pointFlag=false;
//				}
//				char[] carray=firstword.toCharArray();
//				
//				for(int z=0; z<carray.length;z++)
//				{
//				     try {
//				            Thread.sleep((int)(Math.random()*120) );
//				        } catch (Exception ex) {
//				            ex.printStackTrace();
//				        }  
//				     
//					System.out.print(carray[z]);
//					charcountInLine++;
//		
//				}
//				if((pointFlag==true&&firstword.contains(SENTENCEEND)))
//				{
//					System.out.println("");
//					System.out.print("                   ");
//					linecount++;
//					charcountInLine=0;
//					pointFlag=false;
//				}
//        		//System.out.print("Zuse schreibt... : "+firstword);
//				//System.out.print(" "+firstword);
//				if(linecount>5+Math.random()*15&&firstword.contains( SENTENCEEND))
//				{
//					
//						System.out.println("");
//						//System.out.println(firstword);
//						System.out.println("");
//						//System.out.print("Zuse schreibt... : ");
//						//System.out.print("                   ");
//						//System.out.print("Zuse schreibt... : ");
//			    		 sdf = new SimpleDateFormat("HH:mm");
//			    		
//			    		System.out.print("      ["+sdf.format(new Date())+"]      ");
//						linecount=0;
//						charcountInLine=0;
//					
//				}
//					
//				else if(/*(i%20==0&&i!=0)||*/charcountInLine>85)
//				{
//					//System.out.print("|");
////					System.out.println("");
////					System.out.print("                   ");
////					linecount++;
////					charcountInLine=0;
//					pointFlag=true;
//					//System.out.print("Zuse schreibt... : ");
//				}
//				}
				metaCorpusLoad.setEnabled(false);
				metaCorpusLoad.setVisible(false);
				//System.out.println(TokenObserver.getCache("der"));
            }
        });
		listGUI.setAlignmentX(Component.LEFT_ALIGNMENT);
		rightPanel.add(metaCorpusLoad);
		rightPanel.setLayout( new javax.swing.BoxLayout(
				rightPanel, javax.swing.BoxLayout.Y_AXIS ));
		//textArea.setBorder(new EmptyBorder(50,75,50,75));
        leftScrollPane = new JScrollPane (textArea, 
	            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
	            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
		rightScrollPane = new JScrollPane (rightPanel, 
	            ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
	            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		Border border = new MatteBorder(50,70,50,70,HINTERGRUND);
		rightScrollPane.setBorder( new MatteBorder(50,10,00,00,HINTERGRUND));
		leftScrollPane.setBorder(border);
		mainPanel.setLeftComponent(leftScrollPane);
		mainPanel.setRightComponent(rightScrollPane);
		
		if(screensize.height<750||screensize.width<950)
		{
			mainPanel.setDividerLocation(screensize.width/4);
		}
		else
		{
			mainPanel.setDividerLocation(500);
		}
		

//		mainPanel.addPropertyChangeListener(JSplitPane.DIVIDER_LOCATION_PROPERTY,  new PropertyChangeListener() {
//			        @Override
//			        public void propertyChange(PropertyChangeEvent pce) {
//			        	
//			        	System.out.println("-->LPw " +leftPanel.getWidth());
//			        	System.out.println("-->LPh " +leftPanel.getHeight());
//			        	System.out.println("-->LSPw " +leftScrollPane.getWidth());
//			        	System.out.println("-->LsPh " +leftScrollPane.getHeight());
//			        	System.out.println("RPw " +rightPanel.getWidth());
//			        	System.out.println("RPh " +rightPanel.getHeight());
//			        	System.out.println("RSPw " +rightScrollPane.getWidth());
//			        	System.out.println("RSPh " +rightScrollPane.getHeight());
//			        	
//			        	textArea.setBounds(50, 50, leftScrollPane.getWidth()-50, leftScrollPane.getHeight()-50);//.setPrefferedSize(new Dimension(leftScrollPane.getWidth()-50,leftScrollPane.getHeight()-50));
//			        	//SwingUtilities.updateComponentTreeUI( mainFrame );
//			        }
//			});
		mainFrame.add(mainPanel);
		
		
		mainFrame.addWindowStateListener(new WindowStateListener(){
			public void windowStateChanged(WindowEvent event) {
	            boolean isMaximized = isMaximized(event.getNewState());
	            boolean wasMaximized = isMaximized(event.getOldState());

	            if (isMaximized && !wasMaximized) {
	            	mainPanel.setDividerLocation(screensize.width-screensize.width/3);
	            } else if (wasMaximized && !isMaximized) {
	            	mainPanel.setDividerLocation(500);
	            }
	            SwingUtilities.updateComponentTreeUI( mainFrame );
	        }
		});
		SwingUtilities.updateComponentTreeUI( mainFrame );
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	
		
	}
	 @SuppressWarnings({ "rawtypes", "serial" })
	class TabCellRenderer extends JTextField implements ListCellRenderer {
	      
	       
	        public TabCellRenderer(JList list) 
	        {
	            this.setFont(list.getFont());
	            this.setBackground(list.getBackground());
	            this.setBorder(null);
	        }
	       
	        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) 
	        {
	             setText(value.toString());
	             if (isSelected) {
	                 setBackground(list.getSelectionBackground());
	                 setForeground(list.getSelectionForeground());
	             }
	             else {
	                 setBorder(null);
	                 setBackground(list.getBackground());
	                 setForeground(list.getForeground());
	             }
	             return this;
	         }
	     }
	 
}
