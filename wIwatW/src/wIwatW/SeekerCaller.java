package wIwatW;

import java.util.ArrayList;

public class SeekerCaller {

	/**
	 * Starts two searches on the maincorpus and the reference corpus
	 * @param metaCorp the metacorpus with all other corpora
	 * @param string the lookup
	 * @param featureList1 the first feature list
	 * @param featureList2 the second feature list
	 * @param corpusList1 the first list of corpora which are summed up as main corpus
	 * @param corpusList2 the second list of corpora which are summed up as reference corpus
	 * @param numberOfFollowers the number of followers after a word
	 * @param multipleMatchesWeight the weight for multiple matches
	 * @param sameStoryWeight the weight for words wich are part of the same story
	 * @return a list of frequencylists
	 */
	public static ArrayList<FrequencyList> call(MetaCorpus metaCorp, String string,ArrayList<TranslationAlgorithmValue> featureList1, ArrayList<TranslationAlgorithmValue>featureList2, ArrayList<String> corpusList1, ArrayList<String> corpusList2, int numberOfFollowers, int multipleMatchesWeight, int sameStoryWeight)
	{
		//System.out.println(metaCorp.getSentenceNumber());
		
		//System.out.println("EingabeMuster(Seeker):<<<!!!<<<"+string);

		
		 ArrayList<FrequencyList> erg = new  ArrayList<FrequencyList>();
			
			MainCorpusThread freqList1Thread= new MainCorpusThread(numberOfFollowers,metaCorp, corpusList1, string, featureList1, multipleMatchesWeight,sameStoryWeight);
			MainCorpusThread freqList2Thread= new MainCorpusThread(numberOfFollowers,metaCorp, corpusList2, string, featureList2,multipleMatchesWeight,sameStoryWeight);
			
		  	freqList1Thread.start();
		  	freqList2Thread.start();
		  	try {
				freqList1Thread.join();
				freqList2Thread.join();
			} catch (InterruptedException e) {/*e.printStackTrace();*/}
		  	
//		  	FrequencyList frList1=freqList1Thread.getFrequencyList().reduce().collect().sortAfterFrequency();
//		  	FrequencyList frList2=freqList2Thread.getFrequencyList().reduce().collect().sortAfterFrequency();
//
//			FrequencyList frList=frList1.selectByFrequencyOver(0).setFrequencySum().multipleWithGivenWeights().frequenciesToSignificanceWithReferenceListParallel8(frList2, 0).sortAfterFrequency();	
//			
		  	FrequencyList frList1=freqList1Thread.getFrequencyList()/*.setFrequencySum()*/;
		  	
		  	FrequencyList frList2=freqList2Thread.getFrequencyList()/*.setFrequencySum()*/;
		  	//System.out.println("->"+frList1.get(0)); 
			//FrequencyList frList=frList1.selectByFrequencyOver(0).setFrequencySum().sortAfterFrequency()./*.tfidfForFrequencyListResults().*/frequenciesToSignificanceWithReferenceListParallel8(frList2, 0)./*.reduce().collect().*/shortenListToNElements(100)./*.reduceWordToBaseform(0).*/giveNRandomElements(30).multipleWithGivenWeights().trimTo01().sortAfterFrequency()/*.sortAfterFrequency()/*.makeRandomSynonymsOf(0)*/;
			//FrequencyList frList=frList1.frequenciesToSignificance(metaCorp, 0, string, SignificanceMeasureEnum.POISSON).sortAfterFrequency();
//		
			erg.add(frList1);
			erg.add(frList2);
		  	return erg;
	}	
	
	
}
