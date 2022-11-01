package wIwatW;

import java.util.ArrayList;
/**
 * Threading for multiple parallel searches in the same time
 * @author Lasse
 *
 */
public class MainCorpusThread extends Thread {
	private FrequencyList frList1= new FrequencyList();
	private MetaCorpus metaCorp;
	private ArrayList<String> cora;
	private String string;
	private int sameStoryWeight, multipleMatchesWeight,numberOfFollowers;
	private ArrayList<TranslationAlgorithmValue>  list;
	
	/**
	 * constructs the thread with necessary information
	 * @param numberOfFollowers
	 * @param mataCorp
	 * @param cora
	 * @param string
	 * @param list
	 * @param multipleMatchesWeight
	 * @param sameStoryWeight
	 */
public MainCorpusThread(int numberOfFollowers, MetaCorpus mataCorp, ArrayList<String> cora, String string, ArrayList<TranslationAlgorithmValue>  list, int multipleMatchesWeight, int sameStoryWeight)
{
	this.metaCorp=mataCorp;
	this.cora=cora;
	this.string=string;
	this.list=list;
	this.sameStoryWeight=sameStoryWeight;
	this.multipleMatchesWeight=multipleMatchesWeight;
	this.numberOfFollowers=numberOfFollowers;
}
	
/**
 * runs the thread
 */
	 public void run() {
//		 int emptycount=0;
//		 while(frList1.size()==0)
//			{
//				emptycount++;
//				Seeker skr= new Seeker(metaCorp, cora, string, list);
//				
//				frList1 = skr.searchReallyComplexBack2FastCorpusMixedMultSignificanceParallel(2).reduce().collect().setFrequencySum()/*frequenciesToSignificance(metaCorp, 0, musterarr[musterarr.length-1])/*.removeSentenceMarksInPosition(1)*/;
////				if(emptycount==1){list.clear();list.add(new TranslationAlgorithmValue(TranslationAlgorithm.NONE,1));}
////				if(emptycount==2){list.clear();list.add(new TranslationAlgorithmValue(TranslationAlgorithm.POSTAGS,1));}
//				if(emptycount==1){break;}
//				//FrequencyList frList1 = skr.searchReallyComplexBack2(2);
//			}
		 Seeker skr= new Seeker(metaCorp, cora, string, list);
		// skr.findCommonVerbPhrasesOnSentenceStart(1);
			//skr.searchSentence("");
		 try{
			 
			 
			 frList1 = skr.searchReallyComplexBack2FastCorpusMixedMultSignificanceParallel(numberOfFollowers,multipleMatchesWeight,sameStoryWeight)/*.reduce().collect()*/.setFrequencySum()/*frequenciesToSignificance(metaCorp, 0, musterarr[musterarr.length-1])/*.removeSentenceMarksInPosition(1)*/;
			// frList1 = skr.paradigmaticSearch(cora,string,0,numberOfFollowers,multipleMatchesWeight,sameStoryWeight)/*.reduce().collect()*/.setFrequencySum()/*frequenciesToSignificance(metaCorp, 0, musterarr[musterarr.length-1])/*.removeSentenceMarksInPosition(1)*/;
			
		 }catch(Exception e)
		 {
			 //System.out.println(" fehler in Suche, MainCorpusThread, Z 62 "+e);
			 e.printStackTrace();
			
			 //frList1 = skr.searchReallyComplexBack2FastCorpusMixedMultSignificanceParallel(numberOfFollowers,multipleMatchesWeight,sameStoryWeight)/*.reduce().collect()*/.setFrequencySum()/*frequenciesToSignificance(metaCorp, 0, musterarr[musterarr.length-1])/*.removeSentenceMarksInPosition(1)*/;
			  
		 }
		
		 
		 
		 }   
	 
	 /**
	  * returns the frequencylist
	  * @return the frequencylist
	  */
	 public FrequencyList getFrequencyList()
	 {
		 return frList1;
	 }
}
