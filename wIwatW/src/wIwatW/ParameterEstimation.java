package wIwatW;

import java.io.File;
import java.io.IOException;
//import java.util.ArrayList;
//
//import javolution.util.FastList;

/**
 * EM-algorithm: for finding of optimal lambdas in linear interpolation
 * @author Lasse
 *
 */
public class ParameterEstimation  {
static double breaker=0.05;
static int matchNumber=0;
/**
 * 
 * @param lambdas initial lambda array
 * @param specificLambda old lambdas, used for recursice call
 * @param ng a computed ngramm structure of a text
 * @param ngLI a compzted ngramm structure of a different text
 * @return an array for the new lambdas
 */
public static double[] estimate(double[] lambdas, int specificLambda, NGramStructure ng, NGramStructure ngLI)
{ //n=Tiefe
	//Schritt 1
	//double[] lambdas = new double[n];
//	for(int i=0; i<n; i++)
//	{
//		lambdas[i]=1/n;
//	}

	int n= lambdas.length;
	
	System.out.println("");
	System.out.println("-->Lambda0"+ ": "+lambdas[0]);
	System.out.println("-->Lambda1"+ ": "+lambdas[1]);
	System.out.println("-->Lambda2"+ ": "+lambdas[2]);
	
	//Schritt 2
	double[] expLambdas = new double[n];
	int m= 5; //Wortanzahl
	
	double[][] ngrams = ng.getArray();//ParameterEstimation.getNgramValues(3);//new double[m][n];
	double[][] ngramsLI = ngLI.getArray();
//	
//	//Array belegen		
//	ngrams[0][0]=0;
//	ngrams[0][1]=50;	
//	ngrams[0][2]=100;	
//	
//	ngrams[1][0]=0;
//	ngrams[1][1]=0;	
//	ngrams[1][2]=2;
//	
//	ngrams[2][0]=69;
//	ngrams[2][1]=0;	
//	ngrams[2][2]=20;
//	
//	ngrams[3][0]=25;
//	ngrams[3][1]=9;	
//	ngrams[3][2]=4;
//			
//	ngrams[4][0]=58;
//	ngrams[4][1]=46;	
//	ngrams[4][2]=100;		
			
	boolean specificFlag=false;
	if(specificLambda>-1)
	{
		specificFlag=true;
	}
	for(int h=0; h<n; h++)//für jede Tiefe/für alle Lambdas
	{
		
		if(h==specificLambda||specificFlag==false)
		{
			for(int j=0; j<m; j++)	//für jedes Wort
			{
				
				double above=(double)lambdas[h]*ngrams[j][h];
				
				double down=0.0;
				for(int k=0;k<n;k++) //für alle Lamdas
				{
					
					down+=(double)lambdas[k]*ngramsLI[j][k];
				}
		
				double nextWordErg=(double)above/down;
				
		
					
				expLambdas[h]+=nextWordErg;
			}
		}
		else
		{
			expLambdas[h]=lambdas[h];
		}

	}
//Schritt 3
	double[] newLambdas = new double[n];
	double lambdasum=0;
	for(int h=0; h<n; h++)//für jede Tiefe/für alle Lambdas/Summe bestimmen
	{
		lambdasum+=expLambdas[h];
	}
	
	for(int h=0; h<n; h++)//für jede Tiefe/für alle Lambdas
	{
	
		
		
		newLambdas[h]=(double)expLambdas[h]/lambdasum;
		System.out.println("Lambda"+ h+": "+newLambdas[h]+" alt: "+lambdas[h]+"<----------");
	}
		for(int h=0; h<n; h++)//für jede Tiefe/für alle Lambdas
		{
		System.out.println("");
		//Schritt 4
		if(newLambdas[h]-lambdas[h]<breaker)
		{
			
		}
		else
		{
			System.out.println("LLLLL"+h);
			newLambdas= estimate(newLambdas, h, ng, ngLI);
			
		}
		
	}
	
	
	return newLambdas;
	
}
//
//public static double[][] getNgramValues(int max)
//{
//
//	String pathSave="."+File.separator+"src"+File.separator+"res"+File.separator+"wiwatWmeta.ser";
//	MetaCorpus meta=Serializator.loadMetaCorpus(pathSave);
//	//MetaCorpus meta = new MetaCorpus();
//	int tokenNumber=0;
//			double[][] erg= new double[meta.sizeInTokens()/meta.getTokenMapNumber(0, 0, 0, 0)][max];
//			//Suche nach dem Token
//			
//			FrequencyList list= new FrequencyList();//typeliste
//			ArrayList<FrequencyList> metalist= new ArrayList<FrequencyList>();
//			for(int i=0; i<max; i++)
//			{
//				
//				metalist.add(list);
//			
//			}
//			
//
//			ArrayList<FrequencyList> fr=frequencyOfBackSequenceN(max, meta);
//
//			
//				for(int j=0; j<meta.getCorpusNumber();j++)//jedes Token
//				{
//					for(int k=0; k<meta.getStoryNumber(j);k++)//jedes Token
//					{
//						for(int l=0; l<meta.getSentenceNumber(j, k);l++)//jedes Token
//						{
//							for(int m=0; m<meta.getTokenNumber(j, k, l);m++)//jedes Token
//							{
//								
//								
//								for(int n=0; n<max; n++)//--evtl weg
//								{
//									
//									
//									ArrayList<String> wordSequence= new ArrayList<String>();
//									FrequencyListField f1= new FrequencyListField();
//									//System.out.println((meta.getToken(j,k, l,m, 0)));
//									for(int i=0;i<n+1;i++)
//									{
//										//System.out.println(m+" m|i  "+i);
//										int newValue= m-i;
//										if(newValue>-1)
//										{
//											wordSequence.add(meta.getToken(j,k, l,newValue, 0));
//											
//											f1.add(meta.getToken(j,k, l,newValue, 0));
//											
//										}
//										
//									}
//									
//									
//
//									
//									
//									
//									double over=(double)fr.get(n).get(fr.get(n).indexOfMine(f1)).getFrequency();
//									System.out.println(tokenNumber+"/"+meta.sizeInTokens()/meta.getTokenMapNumber(0, 0, 0, 0)+" | "+n+"/"+max+": "+over);
//									double under=1;
////									if(m-1>-1)
////									{
////										under=(double)fr.get(n-1).get(fr.get(n-1).indexOfMine(f2)).getFrequency();
////										System.out.println(under);
////									}
//									
//									erg[tokenNumber][n]=(double)over/under;
//									
//								}
//								System.out.println();
//								tokenNumber++;
//						
//							}
//						}
//					}
//				}
//				
//		 
//		 return erg;
//}


//public static FastList<FastList<String>>  numberOfMatches(FastList<FastList<String>> list, int horizont)
//{
//	int count=0;
//	
//	
//	//System.out.println("Horzont:----------->"+horizont);
//	//HashSet<String>positionsMemory= new HashSet<String>();
//	for(int y=0; y<list.get(0).size();y++)
//	{
//		
//		String s1 = list.get(0).get(y);
//		//System.out.println("Positionen der ersten liste----------->"+s1);
//		boolean removeflag=true;
//		String[] val1=s1.split("__");
//		
//
//		int[] wal1= new int[5];
//		for(int z=0;z<val1.length;z++)
//		{
//			wal1[z]=Integer.parseInt(val1[z]);
//	
//		}
//		
//	double[]wal = new double[5];
//
//		for(int i=1;i<list.size();i++)
//		{
//		//positionen weiterer listen
//			for(String si: list.get(i))
//			{
//				//System.out.println("Positionen der letzten liste----------->"+si);
//				String[] val2=si.split("__");
//				
//				for(int z=0;z<val2.length;z++)
//				{
//					wal[z]=Integer.parseInt(val2[z]);
//			
//				}
//				
//				if(wal1[0]==wal[0]&&wal1[1]==wal[1]&&wal1[2]==wal[2]&&wal1[4]==wal[4]&&wal1[3]-wal[3]==i)
//				{
//					//System.out.println(i+"count-Positionen beider listen----------si->"+si+"----s1"+s1);
//					if(i==list.size()-1)
//					{
//						count++;
//					}
//					
//					removeflag=false;
//				}
//				else
//				{
//					//System.out.println("remove-Positionen beider listen----------si->"+si+"----s1"+s1);
//					//list.get(i).remove(s1);
//					
//				}
//				
//			}
//			if(removeflag==true)
//			{
//				list.get(0).remove(s1);
//			}
//			
//		}
//	
//	
//	}
//	matchNumber=count;
//	return list;
//}

//public static double[][] getAllNgramValues(int maxN, MetaCorpus meta)
//{
//	double tokenNumber=(double)meta.sizeInTokens();
//	double[][] ergArray;
//	double[][] ergArray2;
//	ergArray= new double[maxN][(int)tokenNumber];
//	ergArray2= new double[(int)tokenNumber][maxN];
//	for(int i=1; i<maxN;i++)
//	{
//		ergArray[i]=getNgramValuesN(i, meta);
//	}
//	
//	for(int i=0; i<ergArray.length;i++)
//	{
//		for(int j=0;j<ergArray[i].length;j++)
//		{
//			ergArray2[j][i]=ergArray[i][j];
//		}
//	}
//	return ergArray2;
//}

//public static double[][] getAllNgramValuesN(int maxN, MetaCorpus meta)
//{
//	double tokenNumber=(double)meta.sizeInTokens();
//	double[][] ergArray;
//	ergArray= new double[maxN][(int)tokenNumber];
//	for(int i=1; i<maxN;i++)
//	{
//		ergArray[i]=getNgramValuesN(i, meta);
//	}
//	
//
//	return ergArray;
//}
//public static double[] getNgramValuesN(int n, MetaCorpus meta)
//{//n=spezielles n
//	double tokenNumber=(double)meta.sizeInTokens()/meta.getTokenMapNumber(0, 0, 0, 0);
//	double[] ergArray;
//	ergArray= new double[(int)tokenNumber];
//	double erg=1;
//	
//	int tokencount=0;
//	for(int j=0; j<meta.getCorpusNumber();j++)//jedes Token
//	{
//		for(int k=0; k<meta.getStoryNumber(j);k++)//jedes Token
//		{
//			for(int l=0; l<meta.getSentenceNumber(j, k);l++)//jedes Token
//			{
//				for(int m=n; m<meta.getTokenNumber(j, k, l);m++)//jedes Token 
//				{
//					
//					System.out.println("meta.getToken(j, k, l, m-n, 0) "+meta.getToken(j, k, l, m-n, 0));
//					FastList<String> positionsW1=TokenObserver.getPositionOfString(meta.getToken(j, k, l, m-n, 0));
//					double sumW1=(double)positionsW1.size();
//					System.out.println("sumW1 "+sumW1);
//					System.out.println("tokenNumber"+tokenNumber);
//					double relativeFrequency=(double)sumW1/tokenNumber;//w1:alle wörter
//					System.out.println("relativeFrequency"+relativeFrequency);
//					
//					double over=1;
//					double under=1;
//						for(int i=n; i<=m;i++)//jeder Vorgänger von wm einschließlich wm
//						{
//							//gemeinsames auftreten von wi-n bis wi
//							
//							FastList<FastList<String>> olist= new FastList<FastList<String>>();
//							FastList<String> positionsWi=TokenObserver.getPositionOfString(meta.getToken(j, k, l, i, 0));
//							olist.add(positionsWi);
//							
//							for(int a=i-1;a>=i-n;a--)
//							{
//								olist.add(TokenObserver.getPositionOfString(meta.getToken(j, k, l, a, 0)));
//							}
//							numberOfMatches(olist, n);
//							over=matchNumber;
//							//System.out.println("over"+over);
//							//gemeinsames auftreten bis wi-n bis wi-1
//							FastList<FastList<String>> ulist= new FastList<FastList<String>>();
//							FastList<String> positionsWiminus1=TokenObserver.getPositionOfString(meta.getToken(j, k, l, i-1, 0));
//							ulist.add(positionsWiminus1);
//							for(int a=i-2;a>=i-n;a--)
//							{
//								olist.add(TokenObserver.getPositionOfString(meta.getToken(j, k, l, a, 0)));
//							}
//							numberOfMatches(ulist, n-1);
//							under=matchNumber;
//							
//							double temp= over/under;
//							temp=erg*relativeFrequency;
//							erg*=temp;
//							
//		
//						}
//		
//						ergArray[tokencount]=(double)erg;
//					//
//					
//					
//					//
//
//					tokencount++;
//				}
//			}
//		}
//	}
//	
//	
//
//
//
//	return ergArray;
//}

//
//
//public  static ArrayList<FrequencyList> frequencyOfBackSequenceN(int n, MetaCorpus meta)
//{
//	//frequencyOfBackSequence(i,n,meta)/frequencyOfBackSequence(i-1,n-1,meta);
//	ArrayList<FrequencyList> fr= new ArrayList<FrequencyList>();
//	
//	//ArrayList<String> wordSequence= new ArrayList<String>();
//	//System.out.println((meta.getToken(j,k, l,m, 0)));
//	
//	for(int i=0; i<n;i++)
//	{
//		FrequencyList lfr= new FrequencyList();
//	for(int j=0; j<meta.getCorpusNumber();j++)//jedes Token
//	{
//		
//		for(int k=0; k<meta.getStoryNumber(j);k++)//jedes Token
//		{
//			for(int l=0; l<meta.getSentenceNumber(j, k);l++)//jedes Token
//			{
//				for(int m=n; m<meta.getTokenNumber(j, k, l);m++)//jedes Token 
//				{
//					FrequencyListField flf= new FrequencyListField();
//					flf.setFrequency(1);
//					int newValue= m-i;
//					
//					for(int h=0;h<i;h++)
//					{
//						
//						flf.add(meta.getToken(j,k, l,m-h, 0));
//					}	
//						
//						String word=meta.getToken(j,k, l,newValue, 0);
//						
//						flf.add(word);
//						
//						lfr.addDistinct(flf);
//					
//			
//						
//					
//					
//				}
//			}
//		}
//	}
//	fr.add(lfr);
//}
//
//
//	
//	return fr;
//}

/**
 * loads a metacorpus and returns it
 * @return The matecorpus
 */
public static MetaCorpus computeLIMetaCorpus()
{
	  SaveSettings saveSet = new SaveSettings();
	  saveSet.pathMemory[2]="wiwatWmetaLI.ser";
	  saveSet.pathMemory[1]+="1";
	  String pathSave=saveSet.pathMemory[0]+File.separator+saveSet.pathMemory[2];
	MetaCorpus meta = new MetaCorpus();
	try {
		meta=ConsoleIllustrator.loadAndSaveFilledMetaCorp(saveSet.pathMemory[1],  pathSave);
	} catch (IOException e1) {
		e1.printStackTrace();
	}
	return meta;
}
public static void main(String[] args) throws IOException 
{
	
	long time = System.currentTimeMillis( );
	int tiefe=4;
	double[] lambdas = new double[tiefe];
	for(int i=0; i<tiefe; i++)
	{
		lambdas[i]=(double)1/tiefe;
	}
//	lambdas[0]=(double)3/8;
//	lambdas[1]=(double)1/8;
//	lambdas[2]=(double)4/8;
	String pathSave="."+File.separator+"res"+File.separator+"wiwatWmeta1.ser";
	//String pathSaveLI=".\\res\\wiwatWLI.ser";
	MetaCorpus meta=Serializator.loadMetaCorpus(pathSave);
	MetaCorpus metaLI= computeLIMetaCorpus();
	NGramStructure ng = new NGramStructure(meta,tiefe);
	NGramStructure ngLI = new NGramStructure(metaLI,tiefe);
	double[] newlambdas =ParameterEstimation.estimate(lambdas, -1, ng, ngLI);
	for(int h=0; h<newlambdas.length; h++)//für jede Tiefe/für alle Lambdas
	{
		System.out.println("Lambda"+ h+": "+newlambdas[h]+" alt: "+lambdas[h]);
	}
	System.out.println("Zeit: "+ (System.currentTimeMillis( ) - time)/1000 +"s" );
}
}