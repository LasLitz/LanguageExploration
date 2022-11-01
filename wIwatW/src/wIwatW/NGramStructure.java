package wIwatW;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Em-algorithm: calculating of ngrams
 * @author Lasse
 *
 */
public class NGramStructure {
private double[][] array;
private HashMap<ArrayList<String>,Integer> map= new HashMap<ArrayList<String>,Integer> ();
private MetaCorpus meta;
private int nMax;
private int nGramStart=0;
/**
 * Constructo
 * @param meta a given metacorpus
 * @param nMax the maximal deepness of ngrams
 */
public NGramStructure(MetaCorpus meta, int nMax)
{
	this.nMax=nMax;
	this.meta=meta;
	array= new double[meta.sizeInTokens()/meta.getTokenMapNumber(0, 0, 0, 0)][nMax-nGramStart];
	fillArray();
}
/**
 * Returns an array with frequencies to each ngramm of all words.
 * @return the ngramm array
 */
public double[][] getArray()
{
	return array;
}

/**
 * Computes and returns an array with frequencies to each ngramm of all words.
 * @return the ngramm array
 */
public double[][] fillArray()
{
	
	int tokenNumber=0;
	fillMap(nMax);
	
	for(int j=0; j<meta.getCorpusNumber();j++)//jedes Token
	{
		for(int k=0; k<meta.getStoryNumber(j);k++)//jedes Token
		{
			for(int l=0; l<meta.getSentenceNumber(j, k);l++)//jedes Token
			{
				for(int m=0; m<meta.getTokenNumber(j, k, l);m++)//jedes Token
				{
					for(int deepth=nGramStart;deepth<nMax; deepth++)
					{
						array[tokenNumber][deepth-nGramStart]=compute(j,k,l,m,deepth);
					}
					
					tokenNumber++;
					System.out.println();
				}
			}
		}
	}
	return array;
}


private double compute(int j, int k, int l, int m, int n)
{
	int over =0;
	if(m-n>-1 )over=nGrams(j,k,l,m-n,n);
	else over=0;
	int under = 0;
	System.out.println("---");
	if(m-n>-1 )under=nGrams(j,k,l,m-n,n-1);
	else under=meta.sizeInTokens()/meta.getTokenMapNumber(0, 0, 0, 0);
	double erg= (double)over/under;
	System.out.println(m+" "+n+" erg "+erg+" ::: "+over+" / "+under+meta.getToken(j, k, l, m, 0));
	return erg;
}

//0 0 0 10 n=0
//0 0 0 10  0 | 0 0 0 10 -1 	1.
//0 0 0 10 n=1
//0 0 0 9  n=1| 0 0 0 9 0 		2.
//0 0 0 10 n=2
//0 0 0 8 n=2 | 0 0 0 8 n=1 	3.
private int nGrams(int j, int k, int l, int m, int n)
{
	
	ArrayList<String> key = new ArrayList<String>();
	if(n>-1)
	{
		//System.out.println(n);
		for(int o=0;o<=n;o++)
		{
			if(m+o<meta.getTokenNumber(j, k, l))
			{
				String word=meta.getToken(j, k, l, m+o, 0);
				key.add(word);
			}
			
		}
	}
	else
	{
		return meta.sizeInTokens()/meta.getTokenMapNumber(0, 0, 0, 0);
	}
	System.out.println(key+" "+map.get(key));
	return map.get(key);
}

//1.a)0 0 0 10 
//2.a) o=0,1: 0 0 0 9 , 0 0 0 10 2.b) 0 0 0 9
//3.a) o=0,1,2: 0 0 0 8, 0 0 0 9, 0 0 0 10 3.b) 0 0 0 8, 0 0 09

private HashMap<ArrayList<String>,Integer> fillMap(int nMax)
{
	//n=0 unigramme
	//n=1 bigramme
	//n=2 trigramme
	//n=3
	int count=0;
	for(int j=0; j<meta.getCorpusNumber();j++)//jedes Token
	{
		for(int k=0; k<meta.getStoryNumber(j);k++)//jedes Token
		{
			for(int l=0; l<meta.getSentenceNumber(j, k);l++)//jedes Token
			{
				for(int m=0; m<meta.getTokenNumber(j, k, l);m++)//jedes Token
				{
		

					
	
					for(int n=nGramStart;n<nMax;n++)
					{
						if(n+m<meta.getTokenNumber(j, k, l))
						{
							ArrayList<String> key = new ArrayList<String>();
							for(int o=0;o<=n;o++)
							{
								if(m+o<meta.getTokenNumber(j, k, l))
								{
									String word=meta.getToken(j, k, l, m+o, 0);
									
									key.add(word);
								}
								
							}
						
							System.out.println(key);
							int oldValue=0;
							try
							{
								oldValue=map.get(key);
							}catch(Exception e)
							{
								
							}
							
							int newValue=1;
							if(oldValue!=0)
							{
										newValue=map.get(key)+1;
							}
							map.put(key, newValue);
						}
						//System.out.println(key+"||||"+newValue);
					}
					count++;
					System.out.println();
				}
			}
		}
		
	}

	System.out.println("map filled, words:"+count);
	return map;
}



}