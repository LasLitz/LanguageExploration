package wIwatW;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 * GUI: Threading to type new words when searching, cancelling of old searches
 * @author Lasse
 *
 */
public class SearchThread  extends Thread {
	private ArrayList<String> ergList;
	private MetaCorpus metaCorp;
	private String string;
	private ArrayList<TranslationAlgorithmValue> list;
	private ArrayList<TranslationAlgorithmValue> list2;
	private ArrayList<String> corpusList1;
	private ArrayList<String> corpusList2;
	private int numberOfFollowers;
	private int multipleMatchesWeight;
	private int sameStoryWeight;
	private SaveSettings saveSet;
	private AddAllListModel data;
	private JList<String> listGUI;
	//private FutureSearchThread fst;
	
	public SearchThread(){}
	
	public SearchThread(JList<String> listGUI,AddAllListModel data,   MetaCorpus metaCorp, String string,ArrayList<TranslationAlgorithmValue> list, ArrayList<TranslationAlgorithmValue> list2, ArrayList<String> corpusList1, ArrayList<String> corpusList2,int numberOfFollowers,int multipleMatchesWeight, int sameStoryWeight, SaveSettings saveSet)
	{
		this.setName("SearchThread");

		this.listGUI=listGUI;
		this.metaCorp=metaCorp;
		this.string=string;
		this.list=list;
		this.list2=list2;
		this.corpusList1=corpusList1;
		this.corpusList2=corpusList2;
		this.numberOfFollowers=numberOfFollowers;
		this.multipleMatchesWeight=multipleMatchesWeight;
		this.sameStoryWeight=sameStoryWeight;
		this.saveSet=saveSet;
		//this.fst=fst;
		this.setDaemon(true);
		this.data=data;
	}
	
	public void set(JList<String> listGUI,AddAllListModel data,   MetaCorpus metaCorp, String string,ArrayList<TranslationAlgorithmValue> list, ArrayList<TranslationAlgorithmValue> list2, ArrayList<String> corpusList1, ArrayList<String> corpusList2,int numberOfFollowers,int multipleMatchesWeight, int sameStoryWeight, SaveSettings saveSet)
	{
		this.setName("SearchThread");

		this.listGUI=listGUI;
		this.metaCorp=metaCorp;
		this.string=string;
		this.list=list;
		this.list2=list2;
		this.corpusList1=corpusList1;
		this.corpusList2=corpusList2;
		this.numberOfFollowers=numberOfFollowers;
		this.multipleMatchesWeight=multipleMatchesWeight;
		this.sameStoryWeight=sameStoryWeight;
		this.saveSet=saveSet;
		//this.fst=fst;
		this.setDaemon(true);
		this.data=data;
	}
	
	/**
	 * runs the thread
	 */
	 public void run() {
		 
//		 ergList=ConsoleIllustrator.showSeekerResultsFromMetaCorpusList(metaCorp, string, list, list2,corpusList1,corpusList2,numberOfFollowers,multipleMatchesWeight,sameStoryWeight, saveSet);
		 ergList=ConsoleIllustrator.showSeekerResultsFromMetaCorpusListForSpeechGeneration(metaCorp, string, list, list2,corpusList1,corpusList2,numberOfFollowers,multipleMatchesWeight,sameStoryWeight, saveSet);
		 
		 System.out.println("Ergebnisse: "+ergList.size());
		 TokenObserver.putInCache(string, ergList);
	   data.clear();
	   listGUI.clearSelection();
//		 for (String e: ergList)
//		 {
//			 data.addElement(e);
//		 }
		 data.addAll(ergList);
//		 
// 		DefaultListModel data2= new DefaultListModel();
// 		
// 		for(String e: ergList)
// 		{
// 			data2.addElement(e);
// 		}
// 		data=data2;
		 
		 //listGUI.setModel(data);
		 //listGUI.setListData(ergList.toArray());
//System.out.println("klappt");
//		 if(fst!=null&&fst.isAlive())
//		 fst.start();
		 
		 }
	 
	 public String getWord()
	 {
		if(ergList==null)
			return ".";
					
		return ergList.get(0);
	 }
	 
//	 public ArrayList<String> getResultList()
//	 {
//		 System.out.println("hier "+ergList);
//		 return ergList;
//	 }
	 
}
