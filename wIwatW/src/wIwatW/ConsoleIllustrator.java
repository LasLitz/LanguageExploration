package wIwatW;


import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;

/***
 * This class is used for result presentation in the console. Also the results are saved in the return parameters.
 * @author Lasse
 *
 */
public class ConsoleIllustrator {

	/**
	 * Searches and shows the results with additional parameters
	 * @param metaCorp Your meta corpus.
	 * @param string Your searchstring.
	 * @param list
	 * @param list2
	 * @param corpusList1
	 * @param corpusList2
	 * @param numberOfFollowers
	 * @param multipleMatchesWeight
	 * @param sameStoryWeight
	 * @return The results as string
	 */
//	public static String showSeekerResultsFromMetaCorpus(MetaCorpus metaCorp, String string,ArrayList<TranslationAlgorithmValue> list, ArrayList<TranslationAlgorithmValue> list2, ArrayList<String> corpusList1, ArrayList<String> corpusList2,int numberOfFollowers,int multipleMatchesWeight, int sameStoryWeight )
//	{
////nicht genutzt
//		  String erg="";
//		  long time1 = System.currentTimeMillis( );
//			//sinnvolle Kombinationen, stets gilt aber NONE c/ von TranslationAlgorithm.T 
//			//n: und u:oder
//			//für weniger Ergebnisse
//			//1-fach sinnvoll: 	None, THESAURUSSYNONYMS, BASEFORM
//			//2-fach sinnvoll: 	NONEnPOSTAGS(POSTAGSANDTEXT),SACHGEBIETEnPOSTAGS, COLOGNEPHONETICnPOSTAGS, THESAURUSSYNONYMSnPOSTAGS
//			//x-fach sinnvoll: SYLLABLESnONLYVOWELSANDONLYCONSONANTSnCHARNUMBER			
//			//
//			// für mehr Ergebnisse (a>b:a sehr viel besser als b)
//			//1-fach: POSTAGS> COLOGNEPHONETIC
//			//2-fach: SACHGEBIETE
//			ArrayList<FrequencyList> frListMeta=SeekerCaller.call(metaCorp, string, list, list2,  corpusList1, corpusList2, numberOfFollowers,  multipleMatchesWeight,  sameStoryWeight);
//			FrequencyList frList1=frListMeta.get(0).reduce().collect().sortAfterFrequency();
//			FrequencyList frList2=frListMeta.get(1).reduce().collect().sortAfterFrequency();
//			FrequencyList frList=frList1.frequenciesToSignificanceWithReferenceListParallel8(frList2, 0).sortAfterFrequency();
//
//			double count=0, bigCount=0;;
//			for(int i=0; i<frList.size();i++)
//			{
//				String str="<:";
//				for(int j=0; j<frList.get(i).size();j++)
//				{
//					str+=frList.get(i).get(j)+" ";
//					
//					
//				}
//				count=frList.get(i).getValue();
//				bigCount+=count;
//				str+=" > "+"|"+count+"|";
//				erg+=str+"\n";
//				System.out.println(str);
//				
//			}
//			System.out.println(frList.size()+"|"+bigCount);
//			erg+="Gesamttokens:"+metaCorp.sizeInTokens()/TranslationAlgorithm.length();
//			
//				System.out.println("Zeit: "+ (System.currentTimeMillis( ) - time1)/1000 +"s" );
//				
//			return erg;
//	}
//	/**
//	 * Searches and shows the results with additional parameters
//	 * @param metaCorp
//	 * @param string
//	 * @param list
//	 * @param list2
//	 * @param corpusList1
//	 * @param corpusList2
//	 * @param numberOfFollowers
//	 * @param multipleMatchesWeight
//	 * @param sameStoryWeight
//	 * @param saveSet
//	 * @return
//	 */
//	public static ArrayList<String> showSeekerResultsFromMetaCorpusList(MetaCorpus metaCorp, String string,ArrayList<TranslationAlgorithmValue> list, ArrayList<TranslationAlgorithmValue> list2, ArrayList<String> corpusList1, ArrayList<String> corpusList2,int numberOfFollowers,int multipleMatchesWeight, int sameStoryWeight, SaveSettings saveSet )
//	{
////nicht genutzt
//		ArrayList<String> erg=new ArrayList<String>();
//		  long time1 = System.currentTimeMillis( );
//			//sinnvolle Kombinationen, stets gilt aber NONE c/ von TranslationAlgorithm.T 
//			//n: und u:oder
//			//für weniger Ergebnisse
//			//1-fach sinnvoll: 	None, THESAURUSSYNONYMS, BASEFORM
//			//2-fach sinnvoll: 	NONEnPOSTAGS(POSTAGSANDTEXT),SACHGEBIETEnPOSTAGS, COLOGNEPHONETICnPOSTAGS, THESAURUSSYNONYMSnPOSTAGS
//			//x-fach sinnvoll: SYLLABLESnONLYVOWELSANDONLYCONSONANTSnCHARNUMBER			
//			//
//			// für mehr Ergebnisse (a>b:a sehr viel besser als b)
//			//1-fach: POSTAGS> COLOGNEPHONETIC
//			//2-fach: SACHGEBIETE
//			ArrayList<FrequencyList> frListMeta=SeekerCaller.call(metaCorp, string, list, list2,  corpusList1, corpusList2, numberOfFollowers,  multipleMatchesWeight,  sameStoryWeight);
//			FrequencyList frList1=frListMeta.get(0).reduce().collect().sortAfterFrequency();
//			
//			FrequencyList frList2=frListMeta.get(1).reduce().collect().sortAfterFrequency();
//			
//			FrequencyList frList=frList1
//					;//.frequenciesToSignificanceWithReferenceListParallel8(frList2, 0).sortAfterFrequency();
//
////			frList1=frList1.tfidfForFrequencyListResultsParallel8(metaCorp,TfidfEnum.STORYBASED).selectByFrequencyOver(0);
////			frList2=frList2.tfidfForFrequencyListResultsParallel8(metaCorp,TfidfEnum.STORYBASED).selectByFrequencyOver(0);
////			frList=frList1.frequenciesToSignificanceWithReferenceListParallel8(frList2, 0);
////			frList=frList.shortenListToNElements(60).giveNRandomElements(20);
//			if(saveSet.postProcessingBooleanMemoryForLists[0][0])
//			{
//				frList1=frList1.relativeOccurencyParallel8();
//			}
//			if(saveSet.postProcessingBooleanMemoryForLists[0][1]&&saveSet.postProcessingBooleanMemoryForLists[0][2])
//			{
//				frList1=frList1.tfidfForFrequencyListResultsParallel8(metaCorp,TfidfEnum.SENTENCEBASED);
//			}
//			if(saveSet.postProcessingBooleanMemoryForLists[0][1]&&saveSet.postProcessingBooleanMemoryForLists[0][3])
//			{
//				frList1=frList1.tfidfForFrequencyListResultsParallel8(metaCorp,TfidfEnum.STORYBASED);
//			}
//			if(saveSet.postProcessingBooleanMemoryForLists[0][4]&&saveSet.postProcessingBooleanMemoryForLists[0][5])
//			{
//				frList1=frList1.frequenciesToSignificance(metaCorp, 0, string, SignificanceMeasureEnum.DICE);
//			}
//			if(saveSet.postProcessingBooleanMemoryForLists[0][4]&&saveSet.postProcessingBooleanMemoryForLists[0][6])
//			{
//				frList1=frList1.frequenciesToSignificance(metaCorp, 0, string, SignificanceMeasureEnum.TANIMOTO);
//			}
//			if(saveSet.postProcessingBooleanMemoryForLists[0][4]&&saveSet.postProcessingBooleanMemoryForLists[0][7])
//			{
//				frList1=frList1.frequenciesToSignificance(metaCorp, 0, string, SignificanceMeasureEnum.GTEST);
//			}
//			if(saveSet.postProcessingBooleanMemoryForLists[0][4]&&saveSet.postProcessingBooleanMemoryForLists[0][8])
//			{
//				frList1=frList1.frequenciesToSignificance(metaCorp, 0, string, SignificanceMeasureEnum.MUTUTALINFORMATION);
//			}
//			if(saveSet.postProcessingBooleanMemoryForLists[0][9])
//			{
//				frList1=frList1.trimTo01();
//			}
//			if(saveSet.postProcessingBooleanMemoryForLists[0][10])
//			{
//				frList1=frList1.multipleWithGivenWeights();
//			}
//			if(saveSet.postProcessingBooleanMemoryForLists[0][11])
//			{
//				int border=Integer.parseInt(saveSet.postProcessingNumbersMemory[0][0]);
//				frList1=frList1.selectByFrequencyOver(border);
//			}
//			if(saveSet.postProcessingBooleanMemoryForLists[0][13])
//			{
//				frList1=frList1.reduceToNVA();
//			}
////			if(saveSet.postProcessingBooleanMemoryForLists[0][12])
////			{
////				System.out.println("bf");
////				frList1=frList1.reduceWordToBaseform(0);
////			}
//			if(saveSet.postProcessingBooleanMemoryForLists[0][14])
//			{
//				int n=Integer.parseInt(saveSet.postProcessingNumbersMemory[0][1]);
//				frList1=frList1.giveNRandomElements(n);
//			}
//
//	
//			if(saveSet.postProcessingBooleanMemoryForLists[1][0])
//			{
//				frList2=frList2.relativeOccurencyParallel8();
//			}
//			if(saveSet.postProcessingBooleanMemoryForLists[1][1]&&saveSet.postProcessingBooleanMemoryForLists[1][2])
//			{
//				frList2=frList2.tfidfForFrequencyListResultsParallel8(metaCorp,TfidfEnum.SENTENCEBASED);
//			}
//			if(saveSet.postProcessingBooleanMemoryForLists[1][1]&&saveSet.postProcessingBooleanMemoryForLists[1][3])
//			{
//				frList2=frList2.tfidfForFrequencyListResultsParallel8(metaCorp,TfidfEnum.STORYBASED);
//			}
//			if(saveSet.postProcessingBooleanMemoryForLists[1][4]&&saveSet.postProcessingBooleanMemoryForLists[1][5])
//			{
//				frList2=frList2.frequenciesToSignificance(metaCorp, 0, string, SignificanceMeasureEnum.DICE);
//			}
//			if(saveSet.postProcessingBooleanMemoryForLists[1][4]&&saveSet.postProcessingBooleanMemoryForLists[1][6])
//			{
//				frList2=frList2.frequenciesToSignificance(metaCorp, 0, string, SignificanceMeasureEnum.TANIMOTO);
//			}
//			if(saveSet.postProcessingBooleanMemoryForLists[1][4]&&saveSet.postProcessingBooleanMemoryForLists[1][7])
//			{
//				frList2=frList2.frequenciesToSignificance(metaCorp, 0, string, SignificanceMeasureEnum.GTEST);
//			}
//			if(saveSet.postProcessingBooleanMemoryForLists[1][4]&&saveSet.postProcessingBooleanMemoryForLists[1][8])
//			{
//				frList2=frList2.frequenciesToSignificance(metaCorp, 0, string, SignificanceMeasureEnum.MUTUTALINFORMATION);
//			}
//			if(saveSet.postProcessingBooleanMemoryForLists[1][9])
//			{
//				frList2=frList2.trimTo01();
//			}
//			if(saveSet.postProcessingBooleanMemoryForLists[1][10])
//			{
//				frList2=frList2.multipleWithGivenWeights();
//			}
//			if(saveSet.postProcessingBooleanMemoryForLists[1][11])
//			{
//				int border=Integer.parseInt(saveSet.postProcessingNumbersMemory[1][0]);
//				frList2=frList2.selectByFrequencyOver(border);
//			}
//			if(saveSet.postProcessingBooleanMemoryForLists[1][13])
//			{
//				frList2=frList2.reduceToNVA();
//			}
////			if(saveSet.postProcessingBooleanMemoryForLists[1][12])
////			{
////				
////				frList2=frList2.reduceWordToBaseform(0);
////			}
//			if(saveSet.postProcessingBooleanMemoryForLists[1][14])
//			{
//				int n=Integer.parseInt(saveSet.postProcessingNumbersMemory[1][1]);
//				frList2=frList2.giveNRandomElements(n);
//			}
//			
//			if(saveSet.postProcessingBooleanMemory[0])
//			{
//				frList=frList1;
//			}
//			if(saveSet.postProcessingBooleanMemory[1])
//			{
//				frList=frList2;
//			}
//			if(saveSet.postProcessingBooleanMemory[2])
//			{
//				frList=frList1.union(frList2);
//			}
//			if(saveSet.postProcessingBooleanMemory[3])
//			{
//				frList=frList1.intersect(frList2);
//			}
//			if(saveSet.postProcessingBooleanMemory[4])
//			{
//				frList=frList1.minus(frList2);
//			}
//			if(saveSet.postProcessingBooleanMemory[5])
//			{
//				frList=frList1.without(frList2);
//			}
//			if(saveSet.postProcessingBooleanMemory[6])
//			{
//				frList=frList1.frequenciesToSignificanceWithReferenceListParallel8(frList2, 0);
//			}
//		frList=frList.sortAfterFrequency();
//			//frequenciesToSignificance(metaCorp, 0, string, SignificanceMeasureEnum.DICE)
//			double count=0, bigCount=0;;
//			for(int i=0; i<frList.size();i++)
//			{
//				String str="";
//				for(int j=0; j<frList.get(i).size();j++)
//				{
//					str+=frList.get(i).get(j)+" ";
//					
//					
//				}
//				count=frList.get(i).getValue();
//				bigCount+=count;
//				str+=" "+"|"+count+"|";
//				erg.add(str);
//				System.out.println(str);
//				
//			}
//			System.out.println(frList.size()+"|"+bigCount);
//			//erg+="Gesamttokens:"+metaCorp.sizeInTokens()/TranslationAlgorithm.length();
//			
//				System.out.println("Zeit: "+ (System.currentTimeMillis( ) - time1)/1000 +"s" );
//				
//			return erg;
//	}
	
	public static ArrayList<String> showSeekerResultsFromMetaCorpusListForSpeechGeneration(MetaCorpus metaCorp, String string,ArrayList<TranslationAlgorithmValue> list, ArrayList<TranslationAlgorithmValue> list2, ArrayList<String> corpusList1, ArrayList<String> corpusList2,int numberOfFollowers,int multipleMatchesWeight, int sameStoryWeight, SaveSettings saveSet )
	{
	
	
		
		
		ArrayList<String> erg=TokenObserver.getCache(string);
		
	
		
		if(erg!=null&&erg.isEmpty()==false)
			
			{
			//System.out.println("cach worked "+string);
			return erg;
			}
		
		erg= new ArrayList<String>();
//		System.out.println("uncach worked "+string);
		  long time1 = System.currentTimeMillis( );
			//sinnvolle Kombinationen, stets gilt aber NONE c/ von TranslationAlgorithm.T 
			//n: und u:oder
			//für weniger Ergebnisse
			//1-fach sinnvoll: 	None, THESAURUSSYNONYMS, BASEFORM
			//2-fach sinnvoll: 	NONEnPOSTAGS(POSTAGSANDTEXT),SACHGEBIETEnPOSTAGS, COLOGNEPHONETICnPOSTAGS, THESAURUSSYNONYMSnPOSTAGS
			//x-fach sinnvoll: SYLLABLESnONLYVOWELSANDONLYCONSONANTSnCHARNUMBER			
			//
			// für mehr Ergebnisse (a>b:a sehr viel besser als b)
			//1-fach: POSTAGS> COLOGNEPHONETIC
			//2-fach: SACHGEBIETE
		  ArrayList<FrequencyList> frListMeta=new ArrayList<FrequencyList>();
		  FrequencyList frList1=new FrequencyList();
		  FrequencyList frList2=new FrequencyList();
		  //System.out.println("davor "+string);
		if(string.endsWith(SimpleGUI.SENTENCEEND))
		{
			
			//frList1=Seeker.searchSentenceStartNaive(metaCorp,1);
//			for(int i=0; i<frList1.size();i++)
//			{
//				System.out.println("s "+frList1.get(i));
//			}
			
			Seeker proxy = new Seeker(metaCorp, erg, string, list2);
			frListMeta.add(proxy.searchSentenceStartNaive(metaCorp,1,numberOfFollowers));
			frListMeta.add(proxy.searchSentenceStartNaive(metaCorp,0,numberOfFollowers));
			 frList1=frListMeta.get(0).sortAfterFrequency();
			 frList2=frListMeta.get(1).sortAfterFrequency();
		}
		else
		{	
			frListMeta=SeekerCaller.call(metaCorp, string, list, list2,  corpusList1, corpusList2, numberOfFollowers,  multipleMatchesWeight,  sameStoryWeight);
			 
			frList1=frListMeta.get(0).reduce().collect().sortAfterFrequency();
			
			 frList2=frListMeta.get(1).reduce().collect().sortAfterFrequency();
		}
			//ArrayList<FrequencyList> frListMeta=SeekerCaller.call(metaCorp, string, list, list2,  corpusList1, corpusList2, numberOfFollowers,  multipleMatchesWeight,  sameStoryWeight);
			
			// frList1=frListMeta.get(0).reduce().collect().sortAfterFrequency();
			
			 //frList2=frListMeta.get(1).reduce().collect().sortAfterFrequency();
			
			FrequencyList frList=frList1
					;//.frequenciesToSignificanceWithReferenceListParallel8(frList2, 0).sortAfterFrequency();
			
//			frList1=frList1.tfidfForFrequencyListResultsParallel8(metaCorp,TfidfEnum.STORYBASED).selectByFrequencyOver(0);
//			frList2=frList2.tfidfForFrequencyListResultsParallel8(metaCorp,TfidfEnum.STORYBASED).selectByFrequencyOver(0);
//			frList=frList1.frequenciesToSignificanceWithReferenceListParallel8(frList2, 0);
//			frList=frList.shortenListToNElements(60).giveNRandomElements(20);
			//System.out.println(saveSet);
			if(saveSet.postProcessingBooleanMemoryForLists[0][0])
			{
				frList1=frList1.relativeOccurencyParallel8();
			}
			if(saveSet.postProcessingBooleanMemoryForLists[0][1]&&saveSet.postProcessingBooleanMemoryForLists[0][2])
			{
				frList1=frList1.tfidfForFrequencyListResultsParallel8(metaCorp,TfidfEnum.SENTENCEBASED);
			}
			if(saveSet.postProcessingBooleanMemoryForLists[0][1]&&saveSet.postProcessingBooleanMemoryForLists[0][3])
			{
				frList1=frList1.tfidfForFrequencyListResultsParallel8(metaCorp,TfidfEnum.STORYBASED);
			}
			if(saveSet.postProcessingBooleanMemoryForLists[0][4]&&saveSet.postProcessingBooleanMemoryForLists[0][5])
			{
				frList1=frList1.frequenciesToSignificance(metaCorp, 0, string, SignificanceMeasureEnum.DICE);
			}
			if(saveSet.postProcessingBooleanMemoryForLists[0][4]&&saveSet.postProcessingBooleanMemoryForLists[0][6])
			{
				frList1=frList1.frequenciesToSignificance(metaCorp, 0, string, SignificanceMeasureEnum.TANIMOTO);
			}
			if(saveSet.postProcessingBooleanMemoryForLists[0][4]&&saveSet.postProcessingBooleanMemoryForLists[0][7])
			{
				frList1=frList1.frequenciesToSignificance(metaCorp, 0, string, SignificanceMeasureEnum.GTEST);
			}
			if(saveSet.postProcessingBooleanMemoryForLists[0][4]&&saveSet.postProcessingBooleanMemoryForLists[0][8])
			{
				frList1=frList1.frequenciesToSignificance(metaCorp, 0, string, SignificanceMeasureEnum.MUTUTALINFORMATION);
			}
			if(saveSet.postProcessingBooleanMemoryForLists[0][9])
			{
				frList1=frList1.trimTo01();
			}
			if(saveSet.postProcessingBooleanMemoryForLists[0][10])
			{
				frList1=frList1.multipleWithGivenWeights();
			}
			if(saveSet.postProcessingBooleanMemoryForLists[0][11])
			{
				int border=Integer.parseInt(saveSet.postProcessingNumbersMemory[0][0]);
				frList1=frList1.selectByFrequencyOver(border);
			}
			if(saveSet.postProcessingBooleanMemoryForLists[0][13])
			{
				frList1=frList1.reduceToNVA();
			}
//			if(saveSet.postProcessingBooleanMemoryForLists[0][12])
//			{
//				//System.out.println("bf");
//				frList1=frList1.reduceWordToBaseform(0);
//			}
			if(saveSet.postProcessingBooleanMemoryForLists[0][14])
			{
				int n=Integer.parseInt(saveSet.postProcessingNumbersMemory[0][1]);
				frList1=frList1.giveNRandomElements(n);
			}

	
			if(saveSet.postProcessingBooleanMemoryForLists[1][0])
			{
				frList2=frList2.relativeOccurencyParallel8();
			}
			if(saveSet.postProcessingBooleanMemoryForLists[1][1]&&saveSet.postProcessingBooleanMemoryForLists[1][2])
			{
				frList2=frList2.tfidfForFrequencyListResultsParallel8(metaCorp,TfidfEnum.SENTENCEBASED);
			}
			if(saveSet.postProcessingBooleanMemoryForLists[1][1]&&saveSet.postProcessingBooleanMemoryForLists[1][3])
			{
				frList2=frList2.tfidfForFrequencyListResultsParallel8(metaCorp,TfidfEnum.STORYBASED);
			}
			if(saveSet.postProcessingBooleanMemoryForLists[1][4]&&saveSet.postProcessingBooleanMemoryForLists[1][5])
			{
				frList2=frList2.frequenciesToSignificance(metaCorp, 0, string, SignificanceMeasureEnum.DICE);
			}
			if(saveSet.postProcessingBooleanMemoryForLists[1][4]&&saveSet.postProcessingBooleanMemoryForLists[1][6])
			{
				frList2=frList2.frequenciesToSignificance(metaCorp, 0, string, SignificanceMeasureEnum.TANIMOTO);
			}
			if(saveSet.postProcessingBooleanMemoryForLists[1][4]&&saveSet.postProcessingBooleanMemoryForLists[1][7])
			{
				frList2=frList2.frequenciesToSignificance(metaCorp, 0, string, SignificanceMeasureEnum.GTEST);
			}
			if(saveSet.postProcessingBooleanMemoryForLists[1][4]&&saveSet.postProcessingBooleanMemoryForLists[1][8])
			{
				frList2=frList2.frequenciesToSignificance(metaCorp, 0, string, SignificanceMeasureEnum.MUTUTALINFORMATION);
			}
			if(saveSet.postProcessingBooleanMemoryForLists[1][9])
			{
				frList2=frList2.trimTo01();
			}
			if(saveSet.postProcessingBooleanMemoryForLists[1][10])
			{
				frList2=frList2.multipleWithGivenWeights();
			}
			if(saveSet.postProcessingBooleanMemoryForLists[1][11])
			{
				int border=Integer.parseInt(saveSet.postProcessingNumbersMemory[1][0]);
				frList2=frList2.selectByFrequencyOver(border);
			}
			if(saveSet.postProcessingBooleanMemoryForLists[1][13])
			{
				frList2=frList2.reduceToNVA();
			}
//			if(saveSet.postProcessingBooleanMemoryForLists[1][12])
//			{
//				
//				frList2=frList2.reduceWordToBaseform(0);
//			}
			if(saveSet.postProcessingBooleanMemoryForLists[1][14])
			{
				int n=Integer.parseInt(saveSet.postProcessingNumbersMemory[1][1]);
				frList2=frList2.giveNRandomElements(n);
			}
			
			if(saveSet.postProcessingBooleanMemory[0])
			{
				frList=frList1;
			}
			if(saveSet.postProcessingBooleanMemory[1])
			{
				frList=frList2;
			}
			if(saveSet.postProcessingBooleanMemory[2])
			{
				frList=frList1.union(frList2);
			}
			if(saveSet.postProcessingBooleanMemory[3])
			{
				frList=frList1.intersect(frList2);
			}
			if(saveSet.postProcessingBooleanMemory[4])
			{
				frList=frList1.minus(frList2);
			}
			if(saveSet.postProcessingBooleanMemory[5])
			{
				frList=frList1.without(frList2);
			}
			if(saveSet.postProcessingBooleanMemory[6])
			{
				frList=frList1.frequenciesToSignificanceWithReferenceListParallel8(frList2, 0);
			}
			
		frList=frList.sortAfterFrequency()/*.shortenListToNElements(30).giveNRandomElements(20)*/;
		
			//frequenciesToSignificance(metaCorp, 0, string, SignificanceMeasureEnum.DICE)
			double count=0, bigCount=0;;
			//System.out.println("size "+frList.size());
			for(int i=0; i<frList.size();i++)
			{
				String str="";
				
				for(int j=0; j<frList.get(i).size();j++)
				{
					
					str+=frList.get(i).get(j)+" ";
					
					
				}
				count=frList.get(i).getValue();
				
				NumberFormat count2= NumberFormat.getInstance();
				count2.setMaximumFractionDigits(4);
				count2.setMinimumFractionDigits(4);
				
				String countRounded=count2.format(count);
				
				bigCount+=count;
				int woerter=numberOfFollowers-1;
//					if(str.length()<8)
//					{
//						str+="\t";
//					}
//					if(str.length()<16)
//					{
//						str+="\t";
//					}
//					if(str.length()<24)
//					{
//						str+="\t";
//					}
					
					
			
				str="|"+countRounded+"| "+str;
				erg.add(str);
				//System.out.println(str);
				
			}
			
			//System.out.println(frList.size()+"|"+bigCount);
			//erg+="Gesamttokens:"+metaCorp.sizeInTokens()/TranslationAlgorithm.length();
			
				//System.out.println("Zeit: "+ (System.currentTimeMillis( ) - time1)/1000 +"s" );
			//System.out.println(erg+" "+erg.size());
			return erg;
	}

	/**
	 * Fills a meta corpus by the given parameters.
	 * @param path The path where your stories are stored.
	 * @param metaCorp Your meta corpus in which you want to add the stories.
	 * @param savePath The destination path for persistence.
	 * @return The filled meta corpus.
	 * @throws IOException
	 */
//	public static MetaCorpus fillMetaCorp(String path, MetaCorpus metaCorp, String savePath) throws IOException
//	{
//		TextImporter textImporter= new TextImporter();
//		
//		ArrayList<String> liss= textImporter.importFolder(path);
//		
//		for(int i=0;i<liss.size();i++)
//		{
//			String cname=textImporter.getFolderName(i);
//			
//			String s=liss.get(i);
//			if(metaCorp.storyExistsInCorpus(s, cname)==false) 
//			{
//				metaCorp.addStoryinCorpusButOnlyByNoExistence(cname,s);
//				System.out.println("fillmetacorp / Story add");
//				Serializator.saveMetaCorpus(metaCorp, savePath);
//				System.out.println("fillmetacorp / save");
//				
//			}
//		}
//		return metaCorp;
//	}

	/**
	 * Fills a meta corpus by the given parameters.
	 * @param path The path where your stories are stored.
	 * @param metaCorp Your meta corpus in which you want to add the stories.
	 * @path savePath the destinationsavepath
	 * @return The filled meta corpus.
	 * @throws IOException
	 */
	public static MetaCorpus fillMetaCorpParallel(String path, MetaCorpus metaCorp,  String savePath) throws IOException
	{
		
		TextImporter textImporter= new TextImporter();
		
		ArrayList<String> liss= textImporter.importFolder(path);
		

		for(int i=0;i<liss.size();i++)
		{
			String cname=textImporter.getFolderName(i);
			String s=liss.get(i);
			if(!metaCorp.storyExistsInCorpus(s, cname)) 
			{
				if(metaCorp.corpusNameExists(cname)==false)
				{
				
					Corpus c= new Corpus(metaCorp.giveIDtoExtern(), cname);
					metaCorp.addCorpus(c); //Noch nicht existierenden Corpus hinzufügen
					metaCorp.addStoryinCorpusButOnlyByNoExistence(cname,liss.get(i) );
					//System.out.println("Corpusname:"+cname);
				}
				else
				{
					metaCorp.addStoryinCorpusButOnlyByNoExistence(cname,liss.get(i) );
					//System.out.println("Corpusname:"+cname);
				}
			}
		}
		
		System.out.println("");
		System.out.println("Falls Wörter zu berechnen sind, dann jetzt...");
		System.out.println("");
		metaCorp.getCorporaList().parallelStream().forEach( c->{
			
			//metaCorp.addStoryinCorpusButOnlyByNoExistence(textImporter.getFolderName(liss.indexOf(s)),s );
			c.computeAllStories();
//		Serializator.saveMetaCorpus(metaCorp, savePath);
		});
		Serializator.saveMetaCorpus(metaCorp, savePath);
		//System.out.println("hier");
		
		return metaCorp;
	}

	/**
	 * Loads an old metacorpus or constructs a new one, saves the filled metacorpus persistent and returns the metacorpus.
	 * @param path of textfiles
	 * @param savepath save path of metacorpus
	 * @return The meta corpus.
	 * @throws IOException
	 */
	public static MetaCorpus loadAndSaveFilledMetaCorp(String path,  String savePath) throws IOException
	{
		MetaCorpus metaCorp =Serializator.loadMetaCorpus(savePath);//lädt bestehendes Metacorpus oder erstellt leeres.
		//System.out.println("between");
		metaCorp=fillMetaCorpParallel(path, metaCorp, savePath);
		//Serializator.saveMetaCorpus(metaCorp, savePath);
		return metaCorp;
	}
}
