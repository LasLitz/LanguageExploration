package wIwatW;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListModel;

public class FutureSearchThread extends Thread {
	private ArrayList<String> ergList;
	private MetaCorpus metaCorp;
	private ArrayList<TranslationAlgorithmValue> list;
	private ArrayList<TranslationAlgorithmValue> list2;
	private ArrayList<String> corpusList1;
	private ArrayList<String> corpusList2;
	private int numberOfFollowers;
	private int multipleMatchesWeight;
	private int sameStoryWeight;
	private SaveSettings saveSet;
	private JList<String> listGUI;
	private  AddAllListModel<String> data;
//	public FutureSearchThread(AddAllListModel data)
//	{
//		this.data=data;
//	}
	public FutureSearchThread(JList<String> listGUI,  AddAllListModel<String> data, MetaCorpus metaCorp, ArrayList<TranslationAlgorithmValue> list, ArrayList<TranslationAlgorithmValue> list2, ArrayList<String> corpusList1, ArrayList<String> corpusList2,int numberOfFollowers,int multipleMatchesWeight, int sameStoryWeight, SaveSettings saveSet)
	{
		this.setName("FutureSearchThread");

		this.listGUI=listGUI;
		this.metaCorp=metaCorp;
		this.list=list;
		this.list2=list2;
		this.corpusList1=corpusList1;
		this.corpusList2=corpusList2;
		this.numberOfFollowers=numberOfFollowers;
		this.multipleMatchesWeight=multipleMatchesWeight;
		this.sameStoryWeight=sameStoryWeight;
		this.saveSet=saveSet;
		this.setDaemon(true);
		this.data=data;
	}
	public FutureSearchThread(){}
	
	public void set(JList<String> listGUI,  AddAllListModel<String> data, MetaCorpus metaCorp, ArrayList<TranslationAlgorithmValue> list, ArrayList<TranslationAlgorithmValue> list2, ArrayList<String> corpusList1, ArrayList<String> corpusList2,int numberOfFollowers,int multipleMatchesWeight, int sameStoryWeight, SaveSettings saveSet)
	{
		this.setName("FutureSearchThread");

		this.listGUI=listGUI;
		this.metaCorp=metaCorp;
		this.list=list;
		this.list2=list2;
		this.corpusList1=corpusList1;
		this.corpusList2=corpusList2;
		this.numberOfFollowers=numberOfFollowers;
		this.multipleMatchesWeight=multipleMatchesWeight;
		this.sameStoryWeight=sameStoryWeight;
		this.saveSet=saveSet;
		//this.setDaemon(true);
		this.data=data;
	}
	
	/**
	 * runs the thread
	 */
	 public void run() {
		 
//		 ergList=ConsoleIllustrator.showSeekerResultsFromMetaCorpusList(metaCorp, string, list, list2,corpusList1,corpusList2,numberOfFollowers,multipleMatchesWeight,sameStoryWeight, saveSet);
//		 ListModel<Object> list=listGUI.getModel();
//		 for(int i=0;i<list.getSize();i++)
//		 {
//			 System.out.println("fst "+list.getElementAt(i).toString().split(" ")[0]);
//		 }
		 for(int i=0;i<data.getSize();i++)
				{
		 //*data.parallelStream().forEach(e->{
			 String word="";
			 String array[]=data.getElementAt(i).toString().split(" ");
				word=array[array.length-1];
				
				//System.out.println("Wort "+word);
//				System.out.println(list);
				ergList=ConsoleIllustrator.showSeekerResultsFromMetaCorpusListForSpeechGeneration(metaCorp, word, list, list2,corpusList1,corpusList2,numberOfFollowers,multipleMatchesWeight,sameStoryWeight, saveSet);
				TokenObserver.putInCache(word, ergList);
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
		 }//);
//		 String word="";
//		 for(int i=0;i<data.getSize();i++)
//			{
//			 
//				word=data.getElementAt(i).toString().split(" ")[0];
////				System.out.println(word);
////				System.out.println(list);
//				ergList=ConsoleIllustrator.showSeekerResultsFromMetaCorpusListForSpeechGeneration(metaCorp, word, list, list2,corpusList1,corpusList2,numberOfFollowers,multipleMatchesWeight,sameStoryWeight, saveSet);
//
//			}
		 //ergList=ConsoleIllustrator.showSeekerResultsFromMetaCorpusListForSpeechGeneration(metaCorp, string, list, list2,corpusList1,corpusList2,numberOfFollowers,multipleMatchesWeight,sameStoryWeight, saveSet);

		 //listGUI.setListData(ergList.toArray());
		
		 
	 }
}