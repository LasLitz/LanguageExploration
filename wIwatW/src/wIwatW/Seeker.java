package wIwatW;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.apache.pdfbox.util.MetaUtil;

import javolution.util.FastList;
/**
 * This class provides a searching method for your metacorpus.
 * @author Lasse
 *
 */


public class Seeker {
	private MetaCorpus meta = new MetaCorpus();
	private Corpus[] corpi;
	private String muster ="";
	private Sentence musterAsSentence;
	private ArrayList<TranslationAlgorithm> translationAlgorithmList = new ArrayList<TranslationAlgorithm>();
	private ArrayList<TranslationAlgorithmValue> translationAlgorithmValueList = new ArrayList<TranslationAlgorithmValue>();
	private ArrayList<TranslationAlgorithm> AllTranslationAlgorithms = new ArrayList<TranslationAlgorithm>();
	private ArrayList<TranslationAlgorithm>NoneOfThisTranslationAlgorithms= new ArrayList<TranslationAlgorithm>();
	private boolean nFlag=false, vFlag=false, aFlag= false;
	boolean synonymFlag=false;
//*Auswahl aus verschiedenen String-Matching-Verfahren, Suche nach vorgegebenem Text-Muster, */


//	public Seeker(MetaCorpus metaCorpus, String lookUp, ArrayList<TranslationAlgorithmValue> translationAlgorithmValueList )
//	{
//		meta= metaCorpus;
//		muster=lookUp;
//		String t=preprocessing(muster);
//		System.out.println("EingabeMuster(Seeker):<<<!<<<"+t);
//		musterAsSentence= new Sentence(t,POSTags.givePostags(muster),"");
//		TranslationAlgorithm[] tAs = TranslationAlgorithm.class.getEnumConstants();
//		for(int i=0;i<tAs.length;i++)
//		{
//			AllTranslationAlgorithms.add(tAs[i]);
//			for(int j=0; j<translationAlgorithmValueList.size();j++)
//			{
//				if(AllTranslationAlgorithms.get(i).equals(translationAlgorithmValueList.get(j).getTranslationAlgorithm()))
//				{
//					this.translationAlgorithmValueList.add(translationAlgorithmValueList.get(j));
//				}
//			}
//		}
//		
//		for(int i=0; i<this.translationAlgorithmValueList.size();i++)
//		{
//			translationAlgorithmList.add(this.translationAlgorithmValueList.get(i).getTranslationAlgorithm());
//		}
//		
//		
//		NoneOfThisTranslationAlgorithms.addAll(AllTranslationAlgorithms);
//		NoneOfThisTranslationAlgorithms.removeAll(translationAlgorithmList);
//
//	}
	
	/**
	 * Initiates the seeker.
	 * @param meta the metacorpus with all corpora
	 * @param corpus the corpuslist (names) in which shall be searched
	 * @param lookUp the word for that should be searched
	 * @param translationAlgorithmValueList the list with translationalgorithms and values
	 */
	public Seeker(MetaCorpus meta, ArrayList<String>corpus, String lookUp, ArrayList<TranslationAlgorithmValue> translationAlgorithmValueList )
	{
		
		this.meta=meta;
		corpi = new Corpus[corpus.size()];
		for(int i=0; i<corpus.size();i++)
		{
			try {
				//System.out.println(corpus.get(i));
				corpi[i]=meta.getCorpusByIndex(meta.getCorpusIndexByName(corpus.get(i)));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println(this.corpi[i].name);
		}
		
		muster=lookUp;
		String t=preprocessing(muster);
		
		muster=t;
		
		musterAsSentence= new Sentence(t,POSTags.givePostags(muster),"",false);
		
		TranslationAlgorithm[] tAs = TranslationAlgorithm.class.getEnumConstants();
		for(int i=0;i<tAs.length;i++)
		{
			AllTranslationAlgorithms.add(tAs[i]);
			for(int j=0; j<translationAlgorithmValueList.size();j++)
			{
				if(AllTranslationAlgorithms.get(i).equals(translationAlgorithmValueList.get(j).getTranslationAlgorithm()))
				{
					this.translationAlgorithmValueList.add(translationAlgorithmValueList.get(j));
				}
			}
		}
		
		for(int i=0; i<this.translationAlgorithmValueList.size();i++)
		{
//			if(this.translationAlgorithmValueList.get(i).getTranslationAlgorithm()==TranslationAlgorithm.SYNONYMS)
//			{
//				synonymFlag=true;
//				//this.translationAlgorithmValueList.remove(i);
//				//System.out.println("syn!!!");
//				
//			}
			translationAlgorithmList.add(this.translationAlgorithmValueList.get(i).getTranslationAlgorithm());

			
		}
		
		
		NoneOfThisTranslationAlgorithms.addAll(AllTranslationAlgorithms);
		NoneOfThisTranslationAlgorithms.removeAll(translationAlgorithmList);
		
		/*for(int i=0; i<NoneOfThisTranslationAlgorithms.size();i++)
			System.out.println(NoneOfThisTranslationAlgorithms.get(i));*/
	
		/*for(int i=0; i<musterAsSentence.size();i++)
			System.out.println(i+musterAsSentence.getTokenizedSentence(1).get(i));*/
	}
	
	
	
//	public FrequencyList searchReallyComplexBack2Fast(int numberOfFollowers)
//	{
//		
//		FrequencyList ergcplx= new FrequencyList();
//		ArrayList<ArrayList<String>> musterAsList= new ArrayList<ArrayList<String>>();
//		//ArrayList<Integer> memoryList= new ArrayList<Integer>();
//
//		//
//		//for(int h=musterAsSentence.getTokenizedSentence().length-1; h>-1;h--)
//		for(int h=0; h<musterAsSentence.getTokenizedSentence().length;h++)
//		{
//			
//			ArrayList<String> mus= new ArrayList<String>();
//			
//			for(int g=0; g<musterAsSentence.getTokenizedSentence()[h].size();g++)
//			{
//				
//				if(NoneOfThisTranslationAlgorithms.contains(musterAsSentence.getTokenizedSentence()[h].getEntry(g).getTranslationAlgorithm())) continue;
//				mus.add(musterAsSentence.getTokenizedSentence()[h].getEntry(g).getStringContent());
//				//System.out.println(musterAsSentence.getTokenizedSentence()[h].getEntry(g).getStringContent());
//			}
//			musterAsList.add(mus);
//			if(h>10) musterAsList.remove(0);
//		}
//
//			int n=0;
//			int x=0;
//			List<String> posList= new ArrayList<String>();
//			for(int m=0; m<AllTranslationAlgorithms.size();m++)
//			{
//				
//				if(NoneOfThisTranslationAlgorithms.contains(musterAsSentence.getTokenizedSentence()[musterAsList.size()-1].getEntry(m).getTranslationAlgorithm())) {continue;}
//				String positions=TokenObserver.getPositionOfString(musterAsList.get(musterAsList.size()-1).get(x));
//				if(positions!=null)
//				{
//					String[] pos=positions.split("!!!");
//					for(int i=0; i<pos.length;i++)
//					{
//						posList.add(pos[i]);
//						String[] val=posList.get(i).split("##");
////						if(h<musterAsList.size()-1)
////							{
////								if(Seeker.isPrecursor(last, pos[i])==false) { breakflag=true;}
////							}
//						if(val.length==5)
//						{
//							FrequencyListField erg= new FrequencyListField();
//							int[] wal= new int[5];
//							for(int z=0;z<val.length;z++)
//							{
//								wal[z]=Integer.parseInt(val[z]);
//							}
//								
//							erg.addAll(followers(wal[0], wal[1],  wal[2], wal[3], numberOfFollowers,  0));
//							int frequencyModify=(int)translationAlgorithmValueList.get(n).getValue();
//							int multipleMatches=0;
//							//System.out.println(frequencyModify);
//							if(musterAsList.size()>1&&wal[3]>0)
//							{
//								int count=1;
//								for(int h=musterAsList.size()-2; h>-1;h--)
//								{
//									if(wal[3]-count<0)break;
//									if(meta.getToken(wal[0], wal[1], wal[2], wal[3]-count, wal[4]).equals(musterAsList.get(h).get(x)))
//									{
//										multipleMatches++;
//									}
//									else{
//										break;
//									}
//								count++;
//								}
//							}
//							if(ergcplx.contains(erg)==false)
//								{
//									erg.setAllFlags(nFlag, vFlag, aFlag);
//									ergcplx.add(erg);
//									ergcplx.increaseFrequencyOfEntry(ergcplx.indexOf(erg),frequencyModify+multipleMatches);
//									//System.out.println(erg.get(0)+translationAlgorithmValueList.get(n).getTranslationAlgorithm()+" "+n+" "+frequencyModify);
//									
//									//falls ergcplx enthällt erg noch nicht, füge hinzu mit Häufigkeit1
//								}
//							//else ergcplx.increaseFrequencyOfEntry(ergcplx.indexOf(erg));// falls ergcplx enthält erg bereits, greife auf enthaltenes element zu, erhöhe häufigkeit um 1
//							else 
//								{
//									//System.out.println(frequencyModify+multipleMatches);
//								
//									ergcplx.increaseFrequencyOfEntry(ergcplx.indexOf(erg),(frequencyModify+multipleMatches+1));
//									
//								}
//							
//							
//						}
//					}
//					n++;
//				}
//				
//				x++;
//			}
//		
//
//		
//		return ergcplx;
//	}
	
//	public FrequencyList searchReallyComplexBack2FastCorpus(int numberOfFollowers)
//	{
//		int multipleMatchesWeight=2;
//		FrequencyList ergcplx= new FrequencyList();
//		ArrayList<ArrayList<String>> musterAsList= new ArrayList<ArrayList<String>>();
//		//ArrayList<Integer> memoryList= new ArrayList<Integer>();
//
//		//
//		//for(int h=musterAsSentence.getTokenizedSentence().length-1; h>-1;h--)
//		for(int h=0; h<musterAsSentence.getTokenizedSentence().length;h++)
//		{
//			
//			ArrayList<String> mus= new ArrayList<String>();
//			
//			for(int g=0; g<musterAsSentence.getTokenizedSentence()[h].size();g++)
//			{
//				
//				if(NoneOfThisTranslationAlgorithms.contains(musterAsSentence.getTokenizedSentence()[h].getEntry(g).getTranslationAlgorithm())) continue;
//				mus.add(musterAsSentence.getTokenizedSentence()[h].getEntry(g).getStringContent());
//				//System.out.println(musterAsSentence.getTokenizedSentence()[h].getEntry(g).getStringContent());
//			}
//			musterAsList.add(mus);
//			if(h>10) musterAsList.remove(0);
//		}
//
//			int n=0;
//			int x=0;
//			//List<String> posList= new ArrayList<String>();
//			for(int m=0; m<AllTranslationAlgorithms.size();m++)
//			{
//				
//				if(NoneOfThisTranslationAlgorithms.contains(musterAsSentence.getTokenizedSentence()[musterAsList.size()-1].getEntry(m).getTranslationAlgorithm())) {continue;}
//				//System.out.println(AllTranslationAlgorithms.get(m).name()+m);
//				
//				 ArrayList<String> positions=TokenObserver.getPositionOfString(musterAsList.get(musterAsList.size()-1).get(x));
//				//System.out.println(positions);
//				if(positions!=null)
//				{
////					String[] pos=positions.split("!!!");
////					for(int i=0; i<pos.length;i++)
////					{
//					for(int i=0; i<positions.size();i++)
//						{
//						//System.out.println("posi"+pos[i]);
//						//posList.add(pos[i]);
//						//System.out.println("poslist"+posList.get(i));
//						String[] val=positions.get(i).split("##");
////						if(h<musterAsList.size()-1)
////							{
////								if(Seeker.isPrecursor(last, pos[i])==false) { breakflag=true;}
////							}
//						if(val.length==5)
//						{
//							FrequencyListField erg= new FrequencyListField();
//							int[] wal= new int[5];
//							for(int z=0;z<val.length;z++)
//							{
//								wal[z]=Integer.parseInt(val[z]);
//								//System.out.println(val[z]+"|"+wal[z]);
//							}
//							for(int j=0; j<corpi.length;j++)
//							{
//								if(wal[0]==meta.getCorpusIndexByName(corpi[j].name))
//								{
//								//System.out.println(wal[2]+" "+wal[3]+" "+corpi[wal[0]].getToken( wal[1], wal[2], wal[3]+1, 0));
//								erg.addAll(followers(wal[0], wal[1],  wal[2], wal[3], numberOfFollowers,  0));
//								int frequencyModify=(int)translationAlgorithmValueList.get(n).getValue();
//								int multipleMatches=0;
//								//System.out.println(frequencyModify);
//								if(musterAsList.size()>1&&wal[3]>0)
//								{
//									int count=1;
//									for(int h=musterAsList.size()-2; h>-1;h--)
//									{
//										if(wal[3]-count<0)break;
//										//System.out.println(corpi[j].getToken( wal[1], wal[2], wal[3]-count, 0));
//										if(corpi[j].getToken( wal[1], wal[2], wal[3]-count, wal[4]).equalsIgnoreCase(musterAsList.get(h).get(x)))
//										{
//											multipleMatches++;
//										}
//										else{
//											break;
//										}
//									count++;
//									}
//								}
//								
//								if(ergcplx.contains(erg)==false)
//									{
//										erg.setAllFlags(nFlag, vFlag, aFlag);
//										ergcplx.add(erg);
//										ergcplx.increaseFrequencyOfEntry(ergcplx.indexOf(erg),frequencyModify+multipleMatchesWeight*multipleMatches);
//										//System.out.println(erg.get(1)+translationAlgorithmValueList.get(n).getTranslationAlgorithm()+" "+n+" "+frequencyModify+" "+multipleMatches);
//										
//										//falls ergcplx enthällt erg noch nicht, füge hinzu mit Häufigkeit1
//									}
//								//else ergcplx.increaseFrequencyOfEntry(ergcplx.indexOf(erg));// falls ergcplx enthält erg bereits, greife auf enthaltenes element zu, erhöhe häufigkeit um 1
//								else 
//									{
//										//System.out.println("test"+frequencyModify+erg.get(1)+multipleMatchesWeight*multipleMatches);
//									
//										ergcplx.increaseFrequencyOfEntry(ergcplx.indexOf(erg),(frequencyModify+multipleMatchesWeight*multipleMatches));
//										
//									}
//								}
//							}
//						}
//					}
//					n++;
//				}
//				
//				x++;
//			}
//		
//
//		
//		return ergcplx;
//	}
	
//	public FrequencyList searchReallyComplexBack2FastCorpusMixed(int numberOfFollowers)
//	{
//		int multipleMatchesWeight=50;
//		int memSyn=-1;
//		//if(synonymFlag==true){memSyn=AllTranslationAlgorithms.indexOf(TranslationAlgorithm.SYNONYMS);}
//		FrequencyList ergcplx= new FrequencyList();
//		ArrayList<ArrayList<String>> musterAsList= new ArrayList<ArrayList<String>>();
//		//ArrayList<Integer> memoryList= new ArrayList<Integer>();
//
//		//
//		//for(int h=musterAsSentence.getTokenizedSentence().length-1; h>-1;h--)
//		for(int h=0; h<musterAsSentence.getTokenizedSentence().length;h++)
//		{
//			
//			ArrayList<String> mus= new ArrayList<String>();
//			
//			for(int g=0; g<musterAsSentence.getTokenizedSentence()[h].size();g++)
//			{
//				
//				if(NoneOfThisTranslationAlgorithms.contains(musterAsSentence.getTokenizedSentence()[h].getEntry(g).getTranslationAlgorithm())) continue;
//				mus.add(musterAsSentence.getTokenizedSentence()[h].getEntry(g).getStringContent());
//				//System.out.println(musterAsSentence.getTokenizedSentence()[h].getEntry(g).getStringContent());
//			}
//			musterAsList.add(mus);
//			if(h>10) musterAsList.remove(0);
//		}
//
//			int n=0;
//			int x=0;
//
//			//List<String> posList= new ArrayList<String>();
//			for(int m=0; m<AllTranslationAlgorithms.size();m++)
//			{
//				
//				if(NoneOfThisTranslationAlgorithms.contains(musterAsSentence.getTokenizedSentence()[musterAsList.size()-1].getEntry(m).getTranslationAlgorithm())) {continue;}
//				//System.out.println(translationAlgorithmList.get(m));
//				//System.out.println(AllTranslationAlgorithms.get(m).name()+m);
//				System.out.println(musterAsList.get(musterAsList.size()-1).get(x));
//				ArrayList<String> positions=TokenObserver.getPositionOfString(musterAsList.get(musterAsList.size()-1).get(x));
//				System.out.println(musterAsList.get(musterAsList.size()-1).get(x));
////				if(AllTranslationAlgorithms.get(m).equals(TranslationAlgorithm.BASEFORMS))
////				{
////					String[]mus=musterAsList.get(musterAsList.size()-1).get(x).split("//");
////					
////					for(int j=0;j<mus.length;j++)
////					{
////						System.out.println(mus[j]);
////						String tmp = MultipleCodeObserver.getMultiplesOfMultipleCode(mus[j]);
////						System.out.println(tmp);
////						String[] mapping=tmp.split(" && ");
////						for(int i=0; i<mapping.length;i++)
////						{
////							System.out.println("posi"+mapping[i]);
////							
////						}
////					}
////	
////				}
//				if(synonymFlag==true&&m==memSyn)
//				{
//					
//					String[] arr=Wortschatz.getSynonyms(musterAsList.get(musterAsList.size()-1).get(0));
//					for(int i=0;i<arr.length;i++)
//					{
//						//System.out.println("posi"+arr[i]);
//						ArrayList<String> tmp=TokenObserver.getPositionOfString(arr[i]);
//						if (tmp!=null)
//						{
//							if(positions!=null)
//							{
//								positions.addAll(tmp);//+=" && "+tmp;
//							}
//							else
//							{
//								positions=tmp;
//							}
//						}
//					}
//					System.out.println("VVFV"+positions);
//				}
//				
//				if(positions!=null)
//				{
//					
////					String[] pos=positions.split(" && ");
////					for(int i=0; i<pos.length;i++)
////					{
//					for(int i=0; i<positions.size();i++)
//					{
//						//System.out.println("posi"+pos[i]);
//						//posList.add(pos[i]);
//						//System.out.println("poslist"+posList.get(i));
//						String[] val=positions.get(i).split("__");
////						if(h<musterAsList.size()-1)
////							{
////								if(Seeker.isPrecursor(last, pos[i])==false) { breakflag=true;}
////							}
//						if(val.length==5)
//						{
//							FrequencyListField erg= new FrequencyListField();
//							int[] wal= new int[5];
//							for(int z=0;z<val.length;z++)
//							{
//								wal[z]=Integer.parseInt(val[z]);
//								//System.out.println(val[z]+"|"+wal[z]);
//							}
//							for(int j=0; j<corpi.length;j++)
//							{
//								if(wal[0]==meta.getCorpusIndexByName(corpi[j].name))
//								{
//								//System.out.println(wal[2]+" "+wal[3]+" "+corpi[wal[0]].getToken( wal[1], wal[2], wal[3]+1, 0));
//								erg.addAll(followers(wal[0], wal[1],  wal[2], wal[3], numberOfFollowers,  0));
//								int frequencyModify=(int)translationAlgorithmValueList.get(n).getValue();
//								
//								if(m==memSyn)
//								{
//								
//									System.out.println(m);
//									System.out.println(frequencyModify);
//									System.out.println(positions);
//								}
//								
//						
//								int multipleMatches=0;
//								//System.out.println(frequencyModify);
//								if(musterAsList.size()>1&&wal[3]>0)
//								{
//									int count=1;
//									for(int h=musterAsList.size()-2; h>-1;h--)
//									{
//										if(wal[3]-count<0)break;
//										//System.out.println(corpi[j].getToken( wal[1], wal[2], wal[3]-count, 0));
//										for(int g=0; g<musterAsList.get(h).size();g++)
//										{
//											//System.out.println(h+musterAsList.get(h).get(x));
//											
//											if(corpi[j].getToken( wal[1], wal[2], wal[3]-count, wal[4]).equalsIgnoreCase(musterAsList.get(h).get(x)))
//											{
//												multipleMatches++;
//											}
//											else{
//												break;
//											}
//										}
//									count++;
//									}
//								}
//								
//								if(ergcplx.contains(erg)==false)
//									{
//										erg.setAllFlags(nFlag, vFlag, aFlag);
//										ergcplx.add(erg);
//										ergcplx.increaseFrequencyOfEntry(ergcplx.indexOf(erg),frequencyModify+multipleMatchesWeight*multipleMatches);
//										//System.out.println(erg.get(1)+translationAlgorithmValueList.get(n).getTranslationAlgorithm()+" "+n+" "+frequencyModify+" "+multipleMatches);
//										
//										//falls ergcplx enthällt erg noch nicht, füge hinzu mit Häufigkeit1
//									}
//								//else ergcplx.increaseFrequencyOfEntry(ergcplx.indexOf(erg));// falls ergcplx enthält erg bereits, greife auf enthaltenes element zu, erhöhe häufigkeit um 1
//								else 
//									{
//										//System.out.println("test"+frequencyModify+erg.get(1)+multipleMatchesWeight*multipleMatches);
//									
//										ergcplx.increaseFrequencyOfEntry(ergcplx.indexOf(erg),(frequencyModify+multipleMatchesWeight*multipleMatches));
//										
//									}
//								}
//							}
//						}
//					}
//					//n stand mal hier...
//				}
//				else System.out.println("no matches for this");
//				n++;
//				x++;
//			}
//		
//
//		
//		return ergcplx;
//	}
	
	
//	public FrequencyList searchReallyComplexBack2FastCorpusMixedMult(int numberOfFollowers)
//	{
//		int multipleMatchesWeight=1000; int sameStoryWeight=100;
////		int memSyn=-1;
////		if(synonymFlag==true){memSyn=AllTranslationAlgorithms.indexOf(TranslationAlgorithm.SYNONYMS);}
//		FrequencyList ergcplx= new FrequencyList();
//		ArrayList<ArrayList<String>> musterAsList= new ArrayList<ArrayList<String>>();
//		//ArrayList<Integer> memoryList= new ArrayList<Integer>();
//
//		//
//		//for(int h=musterAsSentence.getTokenizedSentence().length-1; h>-1;h--)
//		for(int h=0; h<musterAsSentence.getTokenizedSentence().length;h++)
//		{
//			
//			ArrayList<String> mus= new ArrayList<String>();
//			
//			for(int g=0; g<musterAsSentence.getTokenizedSentence()[h].size();g++)
//			{
//				
//				if(NoneOfThisTranslationAlgorithms.contains(musterAsSentence.getTokenizedSentence()[h].getEntry(g).getTranslationAlgorithm())) continue;
//				mus.add(musterAsSentence.getTokenizedSentence()[h].getEntry(g).getStringContent());
//				//System.out.println(musterAsSentence.getTokenizedSentence()[h].getEntry(g).getStringContent());
//			}
//			musterAsList.add(mus);
//			if(h>20) musterAsList.remove(0);
//		}
//
//			int n=0;
//			int x=0;
//
//			//List<String> posList= new ArrayList<String>();
//			for(int m=0; m<AllTranslationAlgorithms.size();m++)
//			{
//				
//				if(NoneOfThisTranslationAlgorithms.contains(musterAsSentence.getTokenizedSentence()[musterAsList.size()-1].getEntry(m).getTranslationAlgorithm())) {continue;}
//				String mus=musterAsList.get(musterAsList.size()-1).get(x);
//				String[] arr1= mus.split("&&");
//				
//				ArrayList<String> positions = new ArrayList<String>();
//				for(int j=0;j<arr1.length;j++)
//				{
//					System.out.println("-->"+mus+" split "+arr1[j]);
//					ArrayList<String>words=TokenObserver.getMultipleCodesOfString(arr1[j]);
//					//if (words!=0)
//					
//					for(int i=0;i<words.size();i++)
//					{
//						System.out.println("------>"+words.get(i));
////						System.out.println(TokenObserver.getPositionOfString("sie"));
//						ArrayList<String> tmp=TokenObserver.getPositionOfString(words.get(i));
//						if(tmp!=null&&tmp.isEmpty()==false)
//						{
//							System.out.println("----------->"+tmp);
//							positions.addAll(tmp);
//						}
//							
//					}
//				}	
//				//ArrayList<String> positions=TokenObserver.getPositionOfString(musterAsList.get(musterAsList.size()-1).get(x));
//
//
//				
//				
//				if(positions!=null)
//				{
//					
////					String[] pos=positions.split(" && ");
////					for(int i=0; i<pos.length;i++)
////					{
//					for(int i=0; i<positions.size();i++)
//					{
//						//System.out.println("posi"+pos[i]);
//						//posList.add(pos[i]);
//						//System.out.println("poslist"+posList.get(i));
//						String[] val=positions.get(i).split("__");
////						if(h<musterAsList.size()-1)
////							{
////								if(Seeker.isPrecursor(last, pos[i])==false) { breakflag=true;}
////							}
//						if(val.length==5)
//						{
//							FrequencyListField erg= new FrequencyListField();
//							int[] wal= new int[5];
//							for(int z=0;z<val.length;z++)
//							{
//								wal[z]=Integer.parseInt(val[z]);
//								//System.out.println(val[z]+"|"+wal[z]);
//							}
//							for(int j=0; j<corpi.length;j++)
//							{
//								if(wal[0]==meta.getCorpusIndexByName(corpi[j].name))
//								{
//								//System.out.println(wal[2]+" "+wal[3]+" "+corpi[wal[0]].getToken( wal[1], wal[2], wal[3]+1, 0));
//								erg.addAll(followers(wal[0], wal[1],  wal[2], wal[3], numberOfFollowers,  0));
//								int frequencyModify=(int)translationAlgorithmValueList.get(n).getValue();
//								
////								if(m==memSyn)
////								{
////								
////									System.out.println(m);
////									System.out.println(frequencyModify);
////									System.out.println(positions);
////								}
////								
//						
//								int multipleMatches=0;
//								//System.out.println(frequencyModify);
//								if(musterAsList.size()>1&&wal[3]>0)
//								{
//									int count=1;
//									
//									for(int h=musterAsList.size()-2; h>-1;h--)
//									{
//										if(wal[3]-count<0)break;
//										//System.out.println(corpi[j].getToken( wal[1], wal[2], wal[3]-count, 0));
//										boolean breakcount=false;
//										for(int g=0; g<musterAsList.get(h).size();g++)
//										{
//											//System.out.println(h+musterAsList.get(h).get(x));
//											
//											if(corpi[j].getToken( wal[1], wal[2], wal[3]-count, wal[4]).equalsIgnoreCase(musterAsList.get(h).get(x)))
//											{
//												
//												if(breakcount==false){multipleMatches++;}
//												//else{
//													ArrayList<String> arrlist=TokenObserver.getPositionOfString(musterAsList.get(h).get(x));
//													for(int u=0;u<arrlist.size();u++)
//													{
//														System.out.println("multiplematches->sameweight in mixedmult");
//														int memo=Integer.parseInt(arrlist.get(u).split("__")[1]);
//														if(wal[1]==memo){
//															multipleMatches+=sameStoryWeight;
//														}
//													}
//													
//												
//													//corpi[j].getToken( wal[1], wal[2], wal[3]-count, wal[4])
//												//}
//											}
//											else{
//												
//												breakcount=true;
//												//break;
//											}
//										}
//									count++;
//									}
//								}
//								
//								if(ergcplx.contains(erg)==false)
//									{
//										erg.setAllFlags(nFlag, vFlag, aFlag);
//										ergcplx.add(erg);
//										ergcplx.increaseFrequencyOfEntry(ergcplx.indexOf(erg),frequencyModify+multipleMatchesWeight*multipleMatches);
//										//System.out.println(erg.get(1)+translationAlgorithmValueList.get(n).getTranslationAlgorithm()+" "+n+" "+frequencyModify+" "+multipleMatches);
//										
//										//falls ergcplx enthällt erg noch nicht, füge hinzu mit Häufigkeit1
//									}
//								//else ergcplx.increaseFrequencyOfEntry(ergcplx.indexOf(erg));// falls ergcplx enthält erg bereits, greife auf enthaltenes element zu, erhöhe häufigkeit um 1
//								else 
//									{
//										//System.out.println("test"+frequencyModify+erg.get(1)+multipleMatchesWeight*multipleMatches);
//									
//										ergcplx.increaseFrequencyOfEntry(ergcplx.indexOf(erg),(frequencyModify+multipleMatchesWeight*multipleMatches));
//										
//									}
//								}
//							}
//						}
//					}
//					//n stand mal hier...
//				}
//				//else System.out.println("no matches for this");
//				n++;
//				x++;
//			}
//		
//
//		
//		return ergcplx;
//	}
//	
//	public FrequencyList searchReallyComplexBack2FastCorpusMixedMultSignificance(int numberOfFollowers)
//	{
//
//		//Frequencen/score
//		//veränderbare Parameter:multipleMatchesWeight, sameStoryWeight, algorithmvalue=frequencyModify, 
//		//unveränderebare Parameter: multiplematches
//		int multipleMatchesWeight=1000; int sameStoryWeight=100;
////		int memSyn=-1;
////		if(synonymFlag==true){memSyn=AllTranslationAlgorithms.indexOf(TranslationAlgorithm.SYNONYMS);}
//		FrequencyList ergcplx= new FrequencyList();
//		ArrayList<ArrayList<String>> musterAsList= new ArrayList<ArrayList<String>>();
//		//ArrayList<Integer> memoryList= new ArrayList<Integer>();
//
//		//
//		//for(int h=musterAsSentence.getTokenizedSentence().length-1; h>-1;h--)
//		for(int h=0; h<musterAsSentence.getTokenizedSentence().length;h++)
//		{
//			
//			ArrayList<String> mus= new ArrayList<String>();
//			
//			for(int g=0; g<musterAsSentence.getTokenizedSentence()[h].size();g++)
//			{
//				
//				if(NoneOfThisTranslationAlgorithms.contains(musterAsSentence.getTokenizedSentence()[h].getEntry(g).getTranslationAlgorithm())) continue;
//				mus.add(musterAsSentence.getTokenizedSentence()[h].getEntry(g).getStringContent());
//				//System.out.println(musterAsSentence.getTokenizedSentence()[h].getEntry(g).getStringContent());
//			}
//			musterAsList.add(mus);
//			if(h>20) musterAsList.remove(0);
//		}
//
//			int n=0;
//			int x=0;
//
//			//List<String> posList= new ArrayList<String>();
//			HashMap<Integer, Double[]> occurrencies= new HashMap<Integer, Double[]>();
//			for(int m=0; m<AllTranslationAlgorithms.size();m++)
//			{
//				
//				if(NoneOfThisTranslationAlgorithms.contains(musterAsSentence.getTokenizedSentence()[musterAsList.size()-1].getEntry(m).getTranslationAlgorithm())) {continue;}
//				String mus=musterAsList.get(musterAsList.size()-1).get(x);
//				String[] arr1= mus.split("&&");
//				
//				ArrayList<String> positions = new ArrayList<String>();
//				for(int j=0;j<arr1.length;j++)
//				{
//					//System.out.println("-->"+mus+" split "+arr1[j]);
//					ArrayList<String>words=TokenObserver.getMultipleCodesOfString(arr1[j]);
//					//if (words!=0)
//					
//					for(int i=0;i<words.size();i++)
//					{
//						//System.out.println("------>"+words.get(i));
////						System.out.println(TokenObserver.getPositionOfString("sie"));
//						ArrayList<String> tmp=TokenObserver.getPositionOfString(words.get(i));
//						if(tmp!=null&&tmp.isEmpty()==false)
//						{
//							//System.out.println("----------->"+tmp);
//							positions.addAll(tmp);
//						}
//							
//					}
//				}	
//				//ArrayList<String> positions=TokenObserver.getPositionOfString(musterAsList.get(musterAsList.size()-1).get(x));
//
//
//				
//				
//				if(positions!=null)
//				{
//					
////					String[] pos=positions.split(" && ");
////					for(int i=0; i<pos.length;i++)
////					{
//	
//					//--------------------------------->parallelisieren
//					for(int i=0; i<positions.size();i++)
//					{
//						ArrayList<Double> frequencyModifiers= new ArrayList<Double>();
//						
//						//System.out.println("posi"+pos[i]);
//						//posList.add(pos[i]);
//						//System.out.println("poslist"+posList.get(i));
//						String[] val=positions.get(i).split("__");
////						if(h<musterAsList.size()-1)
////							{
////								if(Seeker.isPrecursor(last, pos[i])==false) { breakflag=true;}
////							}
//						if(val.length==5)
//						{
//							FrequencyListField erg= new FrequencyListField();
//							int[] wal= new int[5];
//							for(int z=0;z<val.length;z++)
//							{
//								wal[z]=Integer.parseInt(val[z]);
//								//System.out.println(val[z]+"|"+wal[z]);
//							}
//							for(int j=0; j<corpi.length;j++)
//							{
//								if(wal[0]==meta.getCorpusIndexByName(corpi[j].name))
//								{
//								//System.out.println(wal[2]+" "+wal[3]+" "+corpi[wal[0]].getToken( wal[1], wal[2], wal[3]+1, 0));
//								erg.addAll(followers(wal[0], wal[1],  wal[2], wal[3], numberOfFollowers,  0));
//								int frequencyModify=(int)translationAlgorithmValueList.get(n).getValue();
//								
////								if(m==memSyn)
////								{
////								
////									System.out.println(m);
////									System.out.println(frequencyModify);
////									System.out.println(positions);
////								}
////								
//						
//								int multipleMatches=0;
//								//System.out.println(frequencyModify);
//								if(musterAsList.size()>1&&wal[3]>0)
//								{
//									int count=1;
//									
//									for(int h=musterAsList.size()-2; h>-1;h--)
//									{
//										if(wal[3]-count<0)break;
//										//System.out.println(corpi[j].getToken( wal[1], wal[2], wal[3]-count, 0));
//										boolean breakcount=false;
//										for(int g=0; g<musterAsList.get(h).size();g++)
//										{
//											//System.out.println(h+musterAsList.get(h).get(x));
//											
//											if(corpi[j].getToken( wal[1], wal[2], wal[3]-count, wal[4]).equalsIgnoreCase(musterAsList.get(h).get(x)))
//											{
//												
//												if(breakcount==false){multipleMatches++;}
//												//else{
//													ArrayList<String> arrlist=TokenObserver.getPositionOfString(musterAsList.get(h).get(x));
//													for(int u=0;u<arrlist.size();u++)
//													{
//														//System.out.println("multiplematches->sameweight in mixedmult");
//														int memo=Integer.parseInt(arrlist.get(u).split("__")[1]);
//														if(wal[1]==memo){
//															multipleMatches+=sameStoryWeight;
//														}
//													}
//													
//												
//													//corpi[j].getToken( wal[1], wal[2], wal[3]-count, wal[4])
//												//}
//											}
//											else{
//												
//												breakcount=true;
//												//break;
//											}
//										}
//									count++;
//									}
//								}
//								double mM=multipleMatches*multipleMatchesWeight;
//								//frequencyModify(pro Überstezung neu)
//								//Anzahl Treffer für diese Übersetzung(pro Übersetzung neu)
//								//mM=multipleMatchesWeight*multipleMatches
//									
//							
//									
//									
//							
//								
//								frequencyModifiers.add((double)frequencyModify);
//								frequencyModifiers.add(mM);
//								//System.out.println("listemia"+erg+" "+x);
//								if(ergcplx.containsMine(erg)==false)
//									{
//									
//										erg.setAllFlags(nFlag, vFlag, aFlag);
//										Double[] ocArr={1.0,(double)frequencyModify};
//										//System.out.println("containsFalse"+erg+" "+x);
//										occurrencies.put(x,ocArr);
//										//System.out.println("containsFalse"+occurrencies.get(x)[0]);
//										//erg.increaseFrequency(1.0, frequencyModifiers, occurrencies);
//										ergcplx.add(erg);
//										
//										
//										//System.out.println("mia"+ergcplx.get(ergcplx.indexOf(erg)).getOccurrencies().get(x)[0]);
//										
//										//System.out.println(erg.get(1)+translationAlgorithmValueList.get(n).getTranslationAlgorithm()+" "+n+" "+frequencyModify+" "+multipleMatches);
//										
//										//falls ergcplx enthällt erg noch nicht, füge hinzu mit Häufigkeit1
//									}
//								
//								else{
//									//System.out.println("containsTrue"+erg+" "+x);
//									Double[] hm=ergcplx.get(ergcplx.indexOf(erg)).getOccurrencies().get(x);
//									if(hm!=null)
//									{
//										//System.out.println("listemia"+erg+" "+x);
//										Double[] ocArr={hm[0]+1,(double)frequencyModify};
//										occurrencies.put(x, ocArr);
//										//System.out.println(occurrencies.get(x)[0]);
//										
//									}
//									else
//									{
//										Double[] ocArr={1.0,(double)frequencyModify};
//										occurrencies.put(x, ocArr);
////										System.out.println("listemia"+erg+" "+x);
////										System.out.println("r?"+occurrencies.get(x)[0]);
//									}
//									
//								}
//								//System.out.println("aussergalb1"+occurrencies.get(x)[0]);
//								ergcplx.increaseFrequencyOfEntry(ergcplx.indexOf(erg),1,frequencyModifiers, occurrencies, x);
//								//System.out.println("aussergalb2"+ergcplx.get(ergcplx.indexOf(erg)).getOccurrencies().get(x)[0]);
////								System.out.println("r?!"+occurrencies.get(x)[0]);
////								System.out.println("r?!!"+ergcplx.get(ergcplx.indexOf(erg)).getOccurrencies().get(x)[0]);
//								//ergcplx.increaseFrequencyOfEntry(ergcplx.indexOf(erg),1*frequencyModify+multipleMatchesWeight*multipleMatches);
//								}
//							}
//						}
//					}
//					//n stand mal hier...
//				}
//				//else System.out.println("no matches for this");
//				n++;
//				x++;
//			}
//		
//		//ergcplx=ergcplx.reduce().collect().frequenciesToSignificance(meta, 0, musterAsList.get(musterAsList.size()-1).get(0));
//		return ergcplx;
//	}
	
	/**
	 * Searches and returns a frequencylist with results
	 * @param numberOfFollowers number of string elements in each resulting entry of the list
	 * @param multipleMatchesWeight weight for multiple matches
	 * @param sameStoryWeight weight for same story matches
	 * @return The resulting frequencylist
	 */
	public FrequencyList searchReallyComplexBack2FastCorpusMixedMultSignificanceParallel(int numberOfFollowers,int multipleMatchesWeight, int sameStoryWeight)
	{
		long time = System.currentTimeMillis( );
//System.out.println("SUCHE: "+muster );
		//Frequencen/score
		//veränderbare Parameter:multipleMatchesWeight, sameStoryWeight, algorithmvalue=frequencyModify, 
		//unveränderebare Parameter: multiplematches
		//int multipleMatchesWeight=3; int sameStoryWeight=5;
//		int memSyn=-1;
//		if(synonymFlag==true){memSyn=AllTranslationAlgorithms.indexOf(TranslationAlgorithm.SYNONYMS);}
		FrequencyList ergcplx= new FrequencyList();
		ArrayList<ArrayList<String>> musterAsList= new ArrayList<ArrayList<String>>();
		//ArrayList<Integer> memoryList= new ArrayList<Integer>();

		//
		//for(int h=musterAsSentence.getTokenizedSentence().length-1; h>-1;h--)
		
		for(int h=0; h<musterAsSentence.getTokenizedSentence().length;h++)
		{
			
			ArrayList<String> mus= new ArrayList<String>();
			
			for(int g=0; g<musterAsSentence.getTokenizedSentence()[h].size();g++)
			{
				
				if(NoneOfThisTranslationAlgorithms.contains(musterAsSentence.getTokenizedSentence()[h].getEntry(g).getTranslationAlgorithm())) continue;
				mus.add(musterAsSentence.getTokenizedSentence()[h].getEntry(g).getStringContent());
				//System.out.println(musterAsSentence.getTokenizedSentence()[h].getEntry(g).getStringContent());
			}
			musterAsList.add(mus);
			if(h>10) musterAsList.remove(0);
		}
		

			int n=0;
			int x=0;
			
			//List<String> posList= new ArrayList<String>();
			HashMap<Integer, Double[]> occurrencies= new HashMap<Integer, Double[]>();
			
			for(int m=0; m<AllTranslationAlgorithms.size();m++)
			{
				
				if(NoneOfThisTranslationAlgorithms.contains(musterAsSentence.getTokenizedSentence()[musterAsList.size()-1].getEntry(m).getTranslationAlgorithm())) {continue;}
				String mus=musterAsList.get(musterAsList.size()-1).get(x);
				String[] arr1= mus.split("&&");
				
				ArrayList<String> positions = new ArrayList<String>();
				for(int j=0;j<arr1.length;j++)
				{
					//System.out.println("-->"+mus+" split "+arr1[j]);
					FastList<String>words=TokenObserver.getMultipleCodesOfString(arr1[j]);
					//if (words!=0)
					
					for(int i=0;i<words.size();i++)
					{
						//System.out.println("------>"+words.get(i));
//						System.out.println(TokenObserver.getPositionOfString("sie"));
						FastList<String> tmp=TokenObserver.getPositionOfString(words.get(i));
						if(tmp!=null&&tmp.isEmpty()==false)
						{
							//System.out.println("----------->"+tmp);
							positions.addAll(tmp);
						}
							
					}
					
				}	
				//ArrayList<String> positions=TokenObserver.getPositionOfString(musterAsList.get(musterAsList.size()-1).get(x));


				
				
				if(positions!=null)
				{
					
//					String[] pos=positions.split(" && ");
//					for(int i=0; i<pos.length;i++)
//					{
	
					//--------------------------------->parallelisieren
					final int nfin=n;
					final int xfin=x;
					//positions.parallelStream().forEach(position->ergcplx.addAllDistinct(positioncheck(position, numberOfFollowers, nfin, musterAsList, xfin, multipleMatchesWeight, sameStoryWeight, ergcplx, occurrencies)));
					//positions.stream().forEach(position->ergcplx.addAllDistinct(positioncheck(position, numberOfFollowers, nfin, musterAsList, xfin, multipleMatchesWeight, sameStoryWeight, ergcplx, occurrencies)));
					positions./*parallelStream().*/forEach(position->{
						//System.out.println(position+" "+muster);
					ergcplx.addDistinct(positioncheckMR(position, numberOfFollowers, nfin, musterAsList, xfin, multipleMatchesWeight, sameStoryWeight, occurrencies));
						});//				for(String position: positions)
//					{
//						//ergcplx.addAllDistinct(positioncheck(positions.get(i), numberOfFollowers, nfin, musterAsList, xfin, multipleMatchesWeight, sameStoryWeight, ergcplx, occurrencies));
//						
//											
//						ergcplx.addDistinct(positioncheckMR(position, numberOfFollowers, nfin, musterAsList, xfin, multipleMatchesWeight, sameStoryWeight, ergcplx, occurrencies));
//						
//						
////						System.out.println("1");
//					}
										//ergcplx=ergcplx.deleteNullFields();
					//n stand mal hier...
//										System.out.println("2");
				}
				//else System.out.println("no matches for this");
//				System.out.println("3");
				n++;
				x++;
				//System.out.println("Zeit(fortrans): "+muster+"| "+  (System.currentTimeMillis( ) - time) +"ms |"+m );
			}
			//System.out.println("Zeit(ende): "+muster+"| "+  (System.currentTimeMillis( ) - time) +"ms" );
//			System.out.println("4");
		//ergcplx=ergcplx.reduce().collect().frequenciesToSignificance(meta, 0, musterAsList.get(musterAsList.size()-1).get(0));
		
//		for(FrequencyListField e: ergcplx)
//		{
//			System.out.println(e.getContent()+" "+e.getValue()+" "+e.getFrequency());
//		}
		return ergcplx;
	}
	
	
//	private FrequencyList positioncheck(String position, int numberOfFollowers, int n, ArrayList<ArrayList<String>> musterAsList, int x, double multipleMatchesWeight,
//			double sameStoryWeight, FrequencyList ergcplx, HashMap<Integer, Double[]> occurrencies
//			
//			)
//	{
//
//		ArrayList<Double> frequencyModifiers= new ArrayList<Double>();
//		
//		//System.out.println("posi"+pos[i]);
//		//posList.add(pos[i]);
//		//System.out.println("poslist"+posList.get(i));
//		String[] val=position.split("__");
////		if(h<musterAsList.size()-1)
////			{
////				if(Seeker.isPrecursor(last, pos[i])==false) { breakflag=true;}
////			}
//		if(val.length==5)
//		{
//			FrequencyListField erg= new FrequencyListField();
//			int[] wal= new int[5];
//			for(int z=0;z<val.length;z++)
//			{
//				wal[z]=Integer.parseInt(val[z]);
//				//System.out.println(val[z]+"|"+wal[z]);
//			}
//			for(int j=0; j<corpi.length;j++)
//			{
//				try {
//					if(wal[0]==meta.getCorpusIndexByName(corpi[j].name))
//					{
//					//System.out.println(wal[2]+" "+wal[3]+" "+corpi[wal[0]].getToken( wal[1], wal[2], wal[3]+1, 0));
//					erg.addAll(followers(wal[0], wal[1],  wal[2], wal[3], numberOfFollowers,  0));
//					int frequencyModify=(int)translationAlgorithmValueList.get(n).getValue();
//					
////				if(m==memSyn)
////				{
////				
////					System.out.println(m);
////					System.out.println(frequencyModify);
////					System.out.println(positions);
////				}
////				
//
//					int multipleMatches=0;
//					//System.out.println(frequencyModify);
//					if(musterAsList.size()>1&&wal[3]>0)
//					{
//						int count=1;
//						
//						for(int h=musterAsList.size()-2; h>-1;h--)
//						{
//							if(wal[3]-count<0)break;
//							//System.out.println(corpi[j].getToken( wal[1], wal[2], wal[3]-count, 0));
//							boolean breakcount=false;
//							for(int g=0; g<musterAsList.get(h).size();g++)
//							{
//								//System.out.println(h+musterAsList.get(h).get(x));
//								
//								if(corpi[j].getToken( wal[1], wal[2], wal[3]-count, wal[4]).equalsIgnoreCase(musterAsList.get(h).get(x)))
//								{
//									
//									if(breakcount==false){multipleMatches++;}
//									//else{
//										ArrayList<String> arrlist=TokenObserver.getPositionOfString(musterAsList.get(h).get(x));
//										for(int u=0;u<arrlist.size();u++)
//										{
//											//System.out.println("multiplematches->sameweight in mixedmult");
//											int memo=Integer.parseInt(arrlist.get(u).split("__")[1]);
//											if(wal[1]==memo){
//												multipleMatches+=sameStoryWeight;
//											}
//										}
//										
//									
//										//corpi[j].getToken( wal[1], wal[2], wal[3]-count, wal[4])
//									//}
//								}
//								else{
//									
//									breakcount=true;
//									//break;
//								}
//							}
//						count++;
//						}
//					}
//					double mM=multipleMatches*multipleMatchesWeight;
//					//frequencyModify(pro Überstezung neu)
//					//Anzahl Treffer für diese Übersetzung(pro Übersetzung neu)
//					//mM=multipleMatchesWeight*multipleMatches
//						
//
//						
//						
//
//					
//					frequencyModifiers.add((double)frequencyModify);
//					frequencyModifiers.add(mM);
//					//System.out.println("listemia"+erg+" "+x);
//					if(ergcplx.containsMine(erg)==false)
//						{
//						
//							erg.setAllFlags(nFlag, vFlag, aFlag);
//							Double[] ocArr={1.0,(double)frequencyModify};
//							//System.out.println("containsFalse"+erg+" "+x);
//							occurrencies.put(x,ocArr);
//							//System.out.println("containsFalse"+occurrencies.get(x)[0]);
//							//erg.increaseFrequency(1.0, frequencyModifiers, occurrencies);
//							ergcplx.add(erg);
//							
//							
//							//System.out.println("mia"+ergcplx.get(ergcplx.indexOf(erg)).getOccurrencies().get(x)[0]);
//							
//							//System.out.println(erg.get(1)+translationAlgorithmValueList.get(n).getTranslationAlgorithm()+" "+n+" "+frequencyModify+" "+multipleMatches);
//							
//							//falls ergcplx enthällt erg noch nicht, füge hinzu mit Häufigkeit1
//						}
//					
//					else
//					{
//						//System.out.println("containsTrue"+erg+" "+x);
//						Double[] hm=ergcplx.get(ergcplx.indexOf(erg)).getOccurrencies().get(x);
//						if(hm!=null)
//						{
//							//System.out.println("listemia"+erg+" "+x);
//							Double[] ocArr={hm[0]+1,(double)frequencyModify};
//							occurrencies.put(x, ocArr);
//							//System.out.println(occurrencies.get(x)[0]);
//							
//						}
//						else
//						{
//							Double[] ocArr={1.0,(double)frequencyModify};
//							occurrencies.put(x, ocArr);
////						System.out.println("listemia"+erg+" "+x);
////						System.out.println("r?"+occurrencies.get(x)[0]);
//						}
//						
//					}
//					//System.out.println("aussergalb1"+occurrencies.get(x)[0]);
//					ergcplx.increaseFrequencyOfEntry(ergcplx.indexOf(erg),1,frequencyModifiers, occurrencies, x);
//					//System.out.println("aussergalb2"+ergcplx.get(ergcplx.indexOf(erg)).getOccurrencies().get(x)[0]);
////				System.out.println("r?!"+occurrencies.get(x)[0]);
////				System.out.println("r?!!"+ergcplx.get(ergcplx.indexOf(erg)).getOccurrencies().get(x)[0]);
//					//ergcplx.increaseFrequencyOfEntry(ergcplx.indexOf(erg),1*frequencyModify+multipleMatchesWeight*multipleMatches);
//					}
//				} catch (NumberFormatException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
//		return ergcplx;
//	}
	
	private FrequencyListField positioncheckMR(String position, int numberOfFollowers, int n, ArrayList<ArrayList<String>> musterAsList, int x, double multipleMatchesWeight,
			double sameStoryWeight, HashMap<Integer, Double[]> occurrencies
			
			)
	{

		ArrayList<Double> frequencyModifiers= new ArrayList<Double>();
		

		String[] val=position.split("__");
		FrequencyListField erg= new FrequencyListField();
		

			int[] wal= new int[5];
			for(int z=0;z<val.length;z++)
			{
				wal[z]=Integer.parseInt(val[z]);
			}
			for(int j=0; j<corpi.length;j++)
			{
				try {
					if(wal[0]==meta.getCorpusIndexByName(corpi[j].name))
					{
					//System.out.println(wal[2]+" "+wal[3]+" "+corpi[wal[0]].getToken( wal[1], wal[2], wal[3]+1, 0));
						ArrayList<String> at=followers(wal[0], wal[1],  wal[2], wal[3], numberOfFollowers,  0);
						if(at!=null){
							//System.out.println("--at-->"+at);
							erg.addAll(at);
						}
					//System.out.println("-erg--->"+erg.getContent());
					int frequencyModify=(int)translationAlgorithmValueList.get(n).getValue();
					int multipleMatches=0;
					
					
					//alle Auftreten von Fund
					try{
						multipleMatches=TokenObserver.getPositionOfString(corpi[j].getToken( wal[1], wal[2], wal[3]+1, wal[4])).size();
					}catch (Exception e)
					{
						
					}
					//System.out.println(corpi[j].getToken( wal[1], wal[2], wal[3]+1, wal[4])+" "+multipleMatches);
					//System.out.println(frequencyModify);
					if(musterAsList.size()>1&&wal[3]>0)
					{
						int count=1;
						
						for(int h=musterAsList.size()-2; h>-1;h--)
						{
							if(wal[3]-count<0)break;
							//System.out.println(corpi[j].getToken( wal[1], wal[2], wal[3]-count, 0));
							boolean breakcount=false;
							for(int g=0; g<musterAsList.get(h).size();g++)
							{
								//System.out.println(h+musterAsList.get(h).get(x));
								
								if(corpi[j].getToken( wal[1], wal[2], wal[3]-count, wal[4]).equalsIgnoreCase(musterAsList.get(h).get(x)))
								{
									
									if(breakcount==false){multipleMatches++;}
									FastList<String> arrlist=TokenObserver.getPositionOfString(musterAsList.get(h).get(x));
									for(int u=0;u<arrlist.size();u++)
									{
											//System.out.println("multiplematches->sameweight in mixedmult");
										int memo=Integer.parseInt(arrlist.get(u).split("__")[0]);
										int memo1=Integer.parseInt(arrlist.get(u).split("__")[1]);
										if(wal[0]==memo&&wal[1]==memo1)//wall0 ergänzt
										{
											multipleMatches+=sameStoryWeight;
										}
									}
								}
								else{breakcount=true;}
							}
						count++;
						}
					}
					double mM=multipleMatches*multipleMatchesWeight;
					//frequencyModify(pro Überstezung neu)
					//Anzahl Treffer für diese Übersetzung(pro Übersetzung neu)
					//mM=multipleMatchesWeight*multipleMatches
						

						
						

					
					frequencyModifiers.add((double)frequencyModify);
					frequencyModifiers.add(mM);
					//System.out.println("listemia"+erg+" "+x);
					
						//System.out.println(nFlag+" "+ vFlag+" "+ aFlag);
							erg.setAllFlags(nFlag, vFlag, aFlag);
							Double[] ocArr={1.0,(double)frequencyModify};
							//System.out.println("containsFalse"+erg+" "+x);
							occurrencies.put(x,ocArr);
							HashSet<String> tempset = new HashSet<String>();
							tempset.add(position);
							erg.increaseFrequency(1, frequencyModifiers, occurrencies, x, tempset);
//							if(erg.getContent().get(1).equalsIgnoreCase("notwendigen"))System.out.println(erg.getFrequency()+" "+erg.getContent()+" "+erg.getValue()+position+j);
//							if(erg.getContent().get(1).equalsIgnoreCase("duftenden"))System.out.println(erg.getFrequency()+" "+erg.getContent()+" "+erg.getValue()+position+j);
//							
					}
					else
					{
						erg=null;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		//System.out.println(erg+"<");
		return erg;
	}
	/**
	 * A method to preprocess the muster similiar to the metacorpus.
	 * @param text The input.
	 * @return The output.
	 */
	private String preprocessing(String text)
	{
		text=text.toLowerCase();
		//text=PreprocessingMethods.reduceVocals(text);
		//text=PreprocessingMethods.reduceMultipleSignsToOne(PreprocessingMethods.deleteMinus(text));
		//text=PreprocessingMethods.reduceSentenceMarks(text);
		text=PreprocessingMethods.reduceSpace(PreprocessingMethods.makeSpace(text));
		return text;
	}
	
	private String preprocessingBAready(String text)
	{
		text=text.toLowerCase();
		text=PreprocessingMethods.reduceVocals(text);
		text=PreprocessingMethods.reduceMultipleSignsToOne(PreprocessingMethods.deleteMinus(text));
		//text=PreprocessingMethods.reduceSentenceMarks(text);
		text=PreprocessingMethods.reduceSpace(PreprocessingMethods.makeSpace(text));
		return text;
	}
	
	/**
	 * Returns the followers to a given position 
	 * @param i position
	 * @param j position
	 * @param k position
	 * @param temp maximum follower number (ends at end of sentence)
	 * @param numberOfFollowers number of followers
	 * @param m position
	 * @return
	 */
	public FrequencyList paradigmaticSearch( ArrayList<String>corpus, String wordInput,int depth, int numberOfFollowers,int multipleMatchesWeight, int sameStoryWeight)
	{
		 long time = System.currentTimeMillis( );
		 //System.out.println("Zeit(start): "+wordInput+"| "+  (System.currentTimeMillis( ) - time) +"ms" );
		 
		FrequencyList fl = new FrequencyList();
		FrequencyList erg= new FrequencyList();
		
		//Paradigmatische Tiefensuche:
	String memo=this.muster;
		//Suche nach allen Nachfolgern N eines Wortes W
		this.muster=wordInput;
		fl=searchReallyComplexBack2FastCorpusMixedMultSignificanceParallel(numberOfFollowers,multipleMatchesWeight,sameStoryWeight);
		//System.out.println("Zeit(Nachfolger gefunden): "+wordInput+"| "+ (System.currentTimeMillis( ) - time) +"ms" );
		//Dann Suche nach allen Vorgängern V von allen N
		for(FrequencyListField flf: fl)
		{
//		fl./*parallelStream().*/forEach(flf-> //wieder parallel machen!
//		{
			String nachfolgerErster=flf.getContent().get(1);
			//System.out.println("\n\nNachfolger( "+wordInput+")"+nachfolgerErster);
			FastList<String> positions=TokenObserver.getPositionOfString(nachfolgerErster);
			
//			positions./*parallelStream().*/forEach(position-> //wieder parallel machen!
//			{
			for(String position: positions)
			{
				String[] val=position.split("__");
				
				
				 //System.out.println("hier");
					int[] wal= new int[5];
					for(int z=0;z<val.length;z++)
					{
						wal[z]=Integer.parseInt(val[z]);
					}
					
					int sub=wal[3]-1;
					if(sub>=0)
					{
						FrequencyListField ergflf= new FrequencyListField();
						String paradigmaWord=meta.getToken(wal[0], wal[1], wal[2], sub, wal[4]);
						//System.out.println(meta.getToken(wal[0], wal[1], wal[2], sub, 1));
						//System.out.println(TokenObserver.postagOf(paradigmaWord)+"|"+TokenObserver.postagOf(wordInput));
						if(TokenObserver.postagOf(paradigmaWord).equalsIgnoreCase(TokenObserver.postagOf(wordInput)))
						{
							//System.out.println("behalten: "+paradigmaWord+" |"+TokenObserver.postagOf(paradigmaWord)+" |---| "+wordInput+" |"+TokenObserver.postagOf(wordInput));
							//postagOf("ich");
							ergflf.add(paradigmaWord);
							//System.out.println("\nparadigma( "+wordInput+")"+paradigmaWord);
							ergflf.setFrequency(1);
							erg.addDistinct(ergflf);
						}
						else
						{
							//System.out.println("verschieden: "+paradigmaWord+" |"+TokenObserver.postagOf(paradigmaWord)+" |---| "+wordInput+" |"+TokenObserver.postagOf(wordInput));
							//System.out.println("aussortiert: "+paradigmaWord+" |"+TokenObserver.postagOf(paradigmaWord)+" |---| "+wordInput+" |"+TokenObserver.postagOf(wordInput));
							
						}
						
					}
							//erg.addAll(followers(wal[0], wal[1],  wal[2], wal[3], numberOfFollowers,  0));
					
							
			}//);
			
		}//);
		//System.out.println("Zeit(TTT): "+wordInput+"| "+  (System.currentTimeMillis( ) - time) +"ms "+erg.size() );
		erg=erg.sortAfterFrequency()/*.giveNRandomElements(10)*/.shortenListToNElements(15);
		//System.out.println("Zeit(___): "+wordInput+"| "+  (System.currentTimeMillis( ) - time) +"ms "+erg.size() );
		
		//Dann Suche nach allen Nachfolgern N' von V
		FrequencyList testfl=new FrequencyList();
		//for(FrequencyListField vorgaenger: erg)
		//erg.sortAfterFrequency().shortenListToNElements(10);
			erg.parallelStream().forEach( vorgaenger-> //wieder parallel machen!
			
		{
			this.muster=vorgaenger.getContent().get(0);
			Seeker skr2=new Seeker(this.meta, corpus,vorgaenger.getContent().get(0), this.translationAlgorithmValueList);
			//System.out.println(muster);
			FrequencyList flerg=skr2.searchReallyComplexBack2FastCorpusMixedMultSignificanceParallel(numberOfFollowers,multipleMatchesWeight,sameStoryWeight);
			for(FrequencyListField flferg: flerg)
			{
			//System.out.println(flferg.getContent());
				testfl.addDistinct(flferg);
			}
		
			
		});
			//System.out.println("Zeit(Nachfolger2 gefunden): "+wordInput+"| "+  (System.currentTimeMillis( ) - time) +"ms" );
		testfl.addAll(fl);
		//System.out.println("Zeit(listmerge): "+wordInput+"| "+  (System.currentTimeMillis( ) - time) +"ms" );
		this.muster=memo;
		return testfl;
	}
	

	public ArrayList<String> followers( int i, int j, int k, int temp, int numberOfFollowers,  int m)
	{
		nFlag=false;
		vFlag=false; 
		aFlag= false;
		ArrayList<String> erg = new ArrayList<String>();
		for(int n=0; n<numberOfFollowers;n++)
		{
			if(temp+n<meta.getTokenNumber(i, j, k))
			{
						//System.out.println("Klammer: "+memoryList.get(0)+n);
						erg.add(meta.getToken(i, j, k, temp+n, m)); 
						String postag=meta.getCorpusByIndex(i).getStoryByIndex(j).getSentenicedStory()[k].getTokenizedSentence()[temp+n].getEntry(AllTranslationAlgorithms.indexOf(TranslationAlgorithm.POSTAGS)).getStringContent();
						if(postag.equalsIgnoreCase("poNE")||postag.equalsIgnoreCase("poNN")||postag.equalsIgnoreCase("poNN\\*"))
						{//System.out.println("Klammer: N"+meta.getToken(i, j, k, temp+n, m));
							nFlag=true;
						}
						if(postag.equalsIgnoreCase("poVVFIN")||postag.equalsIgnoreCase("poVVINF")||postag.equalsIgnoreCase("poVVIMP")||postag.equalsIgnoreCase("poVVIZU")||postag.equalsIgnoreCase("poVVPP")||postag.equalsIgnoreCase("poVAFIN")||postag.equalsIgnoreCase("poVAIMP")||postag.equalsIgnoreCase("poVAINF")||postag.equalsIgnoreCase("poVAPP")||postag.equalsIgnoreCase("poVMFIN")||postag.equalsIgnoreCase("poVMINF")||postag.equalsIgnoreCase("poVMPP"))
						{//System.out.println("Klammer: V"+meta.getToken(i, j, k, temp+n, m));
							vFlag=true;
						}
						if(postag.equalsIgnoreCase("poADJA")||postag.equalsIgnoreCase("poADJD"))
						{//System.out.println("Klammer: A"+meta.getToken(i, j, k, temp+n, m));
							aFlag=true;
						}
						//System.out.println("-------------------->"+meta.getToken(i, j, k, temp+n, m));
				
			}
		
		}

		return erg;
	}
	
//	/**
//	 * Returns the followers to a given position 
//	 * @param i position
//	 * @param j position
//	 * @param k position
//	 * @param temp maximum follower number (ends at end of sentence)
//	 * @param numberOfFollowers number of followers
//	 * @param m position
//	 * @return
//	 */
//	public ArrayList<String> followersCplx( int i, int j, int k, int temp, int numberOfFollowers,  int m)
//	{
//		nFlag=false;
//		vFlag=false; 
//		aFlag= false;
//		ArrayList<String> erg = new ArrayList<String>();
//		for(int n=0; n<numberOfFollowers;n++)
//		{
//			if(temp+n<meta.getTokenNumber(i, j, k))
//			{
//						//System.out.println("Klammer: "+memoryList.get(0)+n);
//						erg.add(meta.getToken(i, j, k, temp+n, m)); 
//						String postag=meta.getCorpusByIndex(i).getStoryByIndex(j).getSentenicedStory()[k].getTokenizedSentence()[temp+n].getEntry(AllTranslationAlgorithms.indexOf(TranslationAlgorithm.POSTAGS)).getStringContent();
//						if(postag.equalsIgnoreCase("poNE")||postag.equalsIgnoreCase("poNN")||postag.equalsIgnoreCase("poNN\\*"))
//						{//System.out.println("Klammer: N"+meta.getToken(i, j, k, temp+n, m));
//							nFlag=true;
//						}
//						if(postag.equalsIgnoreCase("poVVFIN")||postag.equalsIgnoreCase("poVVINF")||postag.equalsIgnoreCase("poVVIMP")||postag.equalsIgnoreCase("poVVIZU")||postag.equalsIgnoreCase("poVVPP")||postag.equalsIgnoreCase("poVAFIN")||postag.equalsIgnoreCase("poVAIMP")||postag.equalsIgnoreCase("poVAINF")||postag.equalsIgnoreCase("poVAPP")||postag.equalsIgnoreCase("poVMFIN")||postag.equalsIgnoreCase("poVMINF")||postag.equalsIgnoreCase("poVMPP"))
//						{//System.out.println("Klammer: V"+meta.getToken(i, j, k, temp+n, m));
//							vFlag=true;
//						}
//						if(postag.equalsIgnoreCase("poADJA")||postag.equalsIgnoreCase("poADJD"))
//						{//System.out.println("Klammer: A"+meta.getToken(i, j, k, temp+n, m));
//							aFlag=true;
//						}
//						//System.out.println("-------------------->"+meta.getToken(i, j, k, temp+n, m));
//				
//			}
//		
//		}
//		return erg;
//	}
	
	/**
	 * Experiment: returns all words, thats are used to start a sentence
	 * @param meta the metacorpus
	 * @return a list with sentence starts
	 */
	public FrequencyList searchSentenceStartNaive(MetaCorpus meta, int corpusIndex, int numberOfFollowers)//sehr simpel und schnell zu Testzwecken implementiert, deshalb nicht vollständig
	{
		FrequencyList fl= new FrequencyList();
//		for(int corpusIndex=0;corpusIndex<meta.getCorpusNumber();corpusIndex++)
//		{
			for(int j=0;j<meta.getCorpusByIndex(corpusIndex).size();j++)
			{
				//System.out.println(meta.getCorpusByIndex(i).getStoryByIndex(j).getSentenceNumber());//zu viele arrayelemente
				for(int k=0; k<meta.getCorpusByIndex(corpusIndex).getStoryByIndex(j).getSentenceNumber();k++)
				{
			
					FrequencyListField flf= new FrequencyListField();
					try{
					//System.out.println(i+" "+j+" "+k+" "+meta.getCorpusByIndex(i).getStoryByIndex(j).getSentenicedStory()[k].getTokenizedSentence()[0].getEntry(0).getStringContent());
					ArrayList<String>al=followers(corpusIndex,j,k,0,numberOfFollowers-1,0);
					//System.out.println(al);
					if(!al.isEmpty())
					{
						//System.out.println(al);
						//System.out.println("ci: "+corpusIndex+"J: "+j+" K:"+k);
					
						
						flf.addAll(al);
						//flf.add(meta.getCorpusByIndex(corpusIndex).getStoryByIndex(j).getSentenicedStory()[k].getTokenizedSentence()[0].getEntry(0).getStringContent());
					
						
					
					flf.setFrequency(1);
					//System.out.println(flf);
					fl.addDistinct(flf);
					}
					}
					catch (Exception e)
					{
						System.out.println(meta.getToken(corpusIndex, j, k, 0, 0));
					}
					
				}
			}
		//}
//			for(int i=0; i<fl.size();i++)
//			{
//				System.out.println("d "+fl.get(i));
//			}
		return fl/*.deleteAllLowerCases().sortAfterFrequency().relativeOccurency().shortenListToNElements(100).giveNRandomElements(20)*/;
		//return fl./*deleteAllLowerCases().*/sortAfterFrequency().relativeOccurency().shortenListToNElements(100).giveNRandomElements(20);
	}
	
	public String searchSentences(String input)
	{
		String erg="";
		//System.out.println("los");
		for(int i=0;i<meta.getCorpusNumber();i++)
		{
			
			for(int j=0;j<meta.getStoryNumber(i);j++)
				{
					for(int k=0;k<meta.getSentenceNumber(i,j);k++)
					{
						String sentence="";
						for(int l=0;l<meta.getTokenNumber(i, j, k);l++)
						{
							sentence+=meta.getToken(i, j, k, l, 0)+" ";
						}
//						System.out.println("");
//						System.out.println(sentence+" | "+k);
//						System.out.println("");
					}
				}
		}
		return "";
	}
	public String searchSentence(String input)
	{

		
		String erg="";
		//System.out.println("los");
		String sentence="";
		for(int h=0;h<50;h++)
		{
			FrequencyList fl=new FrequencyList();
			if(h!=0)input=sentence;
			String[] tokens=Sentence.tokenizeSimple(input);
			for(String token: tokens)
			{
				//System.out.println(token);
				List<String> list=TokenObserver.getPositionOfString(token);
				if(list!=null)
				{
					for(String position: list)
					{
						//System.out.println(position);
						String[] posArray =position.split("__");
						
						FrequencyListField flf= new FrequencyListField();
						if(posArray[2].equals(h+"")&&posArray[0].equals(1+""))
						{
							flf.add(posArray[0]+"__"+posArray[1]+"__"+posArray[2]);
							
							flf.setFrequency(1);
							
							flf.setPositionsEmpty();
							
							fl.addDistinct(flf);
						}
						
					}
				}
				
			}
			
			fl=fl.sortAfterFrequency().shortenListToNElements(20).removeFirstIfMoreThanOne();
			try{
				String sentencePosition=fl.giveNRandomElements(10).get(0).get(0);//war 10
				String[] posArray=sentencePosition.split("__");
				//System.out.println(sentencePosition);
				int i=(int) (Math.random()*meta.getCorpusNumber());
				i=1;
				i=Integer.parseInt(posArray[0]);
				int j=(int) (Math.random()*meta.getStoryNumber(i));
				j=Integer.parseInt(posArray[1]);
				int k=(int) (Math.random()*meta.getSentenceNumber(i,j));
				if(h==0)k=0;
				k=Integer.parseInt(posArray[2]);
				
			
				sentence="";
				for(int l=0;l<meta.getTokenNumber(i, j, k);l++)
				{
					sentence+=meta.getToken(i, j, k, l, 0)+" ";
				}
				
				System.out.println(sentence/*+" | "+i+j+k*/);
			}catch (Exception e)
			{
				//e.printStackTrace();
			}
			
					
				
		}
		return "";
	}
	
	public String findCommonVerbPhrasesOnSentenceStart( int phraseLength)
	{
		
		FrequencyList fl= new FrequencyList();
		
		for (int i=0; i<meta.getCorpusNumber();i++) // durchlaufe alle corpora
		{
			for (int j=0; j<meta.getCorpusByIndex(i).size();j++) //durchlaufe geschichten eine korpus
			{
				for (int k=0; k<meta.getCorpusByIndex(i).getStoryByIndex(j).size();k++) //durchlaufe sätze eines korpus
				{
					for (int l=0; l<meta.getCorpusByIndex(i).getStoryByIndex(j).getSentenicedStory()[k].getTokenNumber();l++) //durchaufe wörter eines satzes
					{
						
						TokenMap candidate=meta.getCorpusByIndex(i).getStoryByIndex(j).getSentenicedStory()[k].getTokenizedSentence()[l];
						String postag =candidate.getEntry(AllTranslationAlgorithms.indexOf(TranslationAlgorithm.POSTAGS)).getStringContent();
						if(postag.equalsIgnoreCase("poADJA")||postag.equalsIgnoreCase("poADJD")/*||postag.equalsIgnoreCase("poNE")||postag.equalsIgnoreCase("poNN")||postag.equalsIgnoreCase("poNN\\*")*/||postag.equalsIgnoreCase("poVVFIN")||postag.equalsIgnoreCase("poVVINF")||postag.equalsIgnoreCase("poVVIMP")||postag.equalsIgnoreCase("poVVIZU")||postag.equalsIgnoreCase("poVVPP")||postag.equalsIgnoreCase("poVAFIN")||postag.equalsIgnoreCase("poVAIMP")||postag.equalsIgnoreCase("poVAINF")||postag.equalsIgnoreCase("poVAPP")||postag.equalsIgnoreCase("poVMFIN")||postag.equalsIgnoreCase("poVMINF")||postag.equalsIgnoreCase("poVMPP"))
						{
							FrequencyListField flf= new FrequencyListField();
							//String candidateWord=candidate.getEntry(0).getStringContent();
							for(int n=-phraseLength;n<phraseLength;n++)
							{
								try{
								if(l+n>-1)
								{
//									System.out.println(i+"__"+j+"__"+k+"__"+l+"__"+n);
//									System.out.println(meta.getToken(0, 0, 0, 1, 0));
//									System.out.println(meta.getToken(i, j, k,l+n, 0));
									flf.add(meta.getToken(i, j, k,l+n, 0));
									flf.setFrequency(1);
								}
									
									//flf.setPositionsEmpty();
								}
								 catch (Exception e)
								 {
									// e.printStackTrace();
								 }
							}
							flf.addPositions(i+"__"+j+"__"+k+"__"+l+"__"+0);
							fl.addDistinct(flf);
							l+=phraseLength;
								
					}
							
					}
				}
			}
		}
			
		//Suche nach Verben bis Wort nummer wordNumber->Positionsliste für Verben
		
		//Suche häufigste Weiterführungen (erster Nachfolger und zweiter Nachfolger: n)
		//Suche häufigste Vorgänger kombination (n-2, n-1)
//		fl=fl.sortAfterFrequency().selectByFrequencyOver(2);
//		for(FrequencyListField flf: fl)
//		{
//			System.out.println(flf+" |"+flf.getFrequency()+"|");
//		}
		
		fl=fl.putAllEntrysWithPointsToOne().sortAfterFrequency().selectByFrequencyOver(4);
		//System.out.println(fl.size());
		
		
			 PrintWriter printWriter = null;
			 File datei= new File("out.txt");
		        
		            try {
						printWriter = new PrintWriter(new FileWriter(datei));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		            
		            
		    		FrequencyList flneu= new FrequencyList();    
		
		for(FrequencyListField flf: fl)
		{
			
			System.out.println(flf+" |"+flf.getFrequency()+"|");
			printWriter.println(flf+" |"+flf.getFrequency()+"|");
			HashSet<String> hashSet=flf.getPositions();
			flneu.addDistinct(flf);
			for(String position: hashSet)
			{
				String positions[]=position.split("__");
				int pos[]=new int[5];
				for(int i=0;i<5;i++)
				{
					pos[i]=Integer.parseInt(positions[i]);
				}
				
				int size=meta.getCorpusByIndex(pos[0]).getStoryByIndex(pos[1]).getSentenicedStory()[pos[2]].size();
				String out="";
				
				FrequencyListField flfneu = new FrequencyListField();
				for(int i=0;i<size;i++)
				{
					String token=meta.getToken(pos[0], pos[1], pos[2], i, pos[4]);
					out+=token+" ";
					flfneu.add(token);
				}
				flfneu.setFrequency(1);
				flneu.addDistinct(flfneu);
				out+="| "+pos[0]+"__"+pos[1]+"__"+pos[2]+"__"+pos[3]+"__"+pos[4];
				System.out.println(out);
				
				printWriter.println(out);
			}
			
		}
		//flneu.sortAfterFrequency();
		for(int i=0;i<flneu.size();i++)
		{
			String out=flneu.get(i)+"";
			System.out.println(out);
			printWriter.println(out);
			
		}
		  printWriter.close();
			
		//

		return "";
	}
}



