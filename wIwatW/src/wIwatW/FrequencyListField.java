package wIwatW;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javolution.util.FastList;
/**
 * This class represents a listfield/listelement, which is by itself a ArrayList of a String valie with an integer index, that is use to count the frequency of the listelement.
 * @author Lasse
 *
 */
public class FrequencyListField extends ArrayList<String> implements Comparable<FrequencyListField>
{
	private static final long serialVersionUID = 8652245613855702655L;
	private double frequency=0;
	private double shownValue=0;
	//private double relativeFrequency=0;
	private ArrayList<Double> frequencyModifiers = new ArrayList<Double>();
	private HashSet<String> positions=new HashSet<String>();
	private HashMap<Integer, Double[]>  occurrencies = new HashMap<Integer, Double[]>();
	private boolean hasNounFlag=false, hasVerbFlag=false, hasAdjectiveFlag=false;
	
	
//	/**
//	 * Adds a new list to this list, with a given start frequency, usually 1.
//	 * @param sublist The added item.
//	 * @param frequency The frequency of the item.
//	 */
//	public void add(ArrayList<String> sublist, int frequency)
//	{
//		this.addAll(sublist);
//		this.frequency=frequency;
//	}
	
//	/**
//	 * Increases the frequency of the listelement by one.
//	 */
//	public void increaseFrequencyByOne()
//	{
//		
//		frequency=frequency+1;
//	}
	public void clear()
	{
		super.clear();
		frequency=0;
		shownValue=0;
		frequencyModifiers.clear();
		positions.clear();
		occurrencies.clear();
	}
	/**
	 * Returns all positions of this field string content as hashset
	 * @return the hashset with the positions as string
	 */
	

	public void addPositions(String position)
	{
		this.positions.add(position);
	}
	public HashSet<String> getPositions()
	{
		return this.positions;
	}
	public void setPositionsEmpty()
	{
		positions.add("0__0__0__0__0");
	}
	/**
	 * Computes the tfidf value for sentences in this field
	 * @param meta the metacorpus
	 * @return the resulting double value
	 */
	public  double tfidfSentence(MetaCorpus meta)
	{
		//Sentence=Dokument
		//corpusIndex=0; storyIndex=0;sentenceIndex=0;
		
		HashSet<Integer> corpora = new HashSet<Integer>();
		HashSet<String> corporaStorySentence = new HashSet<String>();
		for(String pos: positions)
		{
			String[] arrP=pos.split("__");
			int corpusIndex=Integer.parseInt(arrP[0]);
			int storyIndex=Integer.parseInt(arrP[1]);
			int sentenceIndex=Integer.parseInt(arrP[2]);
			corpora.add(corpusIndex);
			corporaStorySentence.add(corpusIndex+"__"+storyIndex+"__"+sentenceIndex);

		}
		String term=this.getContent().get(0);
		int tfdt=tfdtgetFrequencyOfTokenInSentenceOfStory(term, corporaStorySentence); //Vorkommen des Wortes im Satz j der Story i
		
		int dft =0;
		int d=0;
		for(Integer corpusIndex : corpora)
		{
			d+= meta.getCorpusByIndex(corpusIndex).getSentenceNumber();
			dft+=dftgetSentenceNumberContainingTokenInCorpus(term, corpusIndex);//check
		
		}
		//System.out.println(tfdt+" "+ dft+" "+ d);
		double tfidf= tfidf(tfdt, dft, d);
		
		return tfidf;
	}
	
	/**
	 * Computes the tfidf value for stories in this field
	 * @param meta the metacorpus
	 * @return the resulting double value
	 */
	public double tfidfStory(MetaCorpus meta)
	{
		String term=this.getContent().get(0);
		HashSet<Integer> corpora = new HashSet<Integer>();
		HashSet<String> corporaStory = new HashSet<String>();
		for(String pos: positions)
		{
			String[] arrP=pos.split("__");
			int corpusIndex=Integer.parseInt(arrP[0]);
			int storyIndex=Integer.parseInt(arrP[1]);
			corpora.add(corpusIndex);
			corporaStory.add(corpusIndex+"__"+storyIndex);

		}
		
		//Story=Dokument
		//corpusIndex=0; storyIndex=0;sentenceIndex=0;
		int tfdt=tfdtgetFrequencyOfTokenInStoryOfCorpus(term,  corporaStory ); //Vorkommen des Wortes in der Story i
		int dft =0;
		int d=0;
		for(Integer corpusIndex : corpora)
		{
			d+= meta.getCorpusByIndex(corpusIndex).size();
			dft+=dftgetStoryNumberContainingTokenInCorpus(term, corpusIndex);//check
		}
		
		double tfidf= tfidf(tfdt, dft, d);
		
		return tfidf;
	}
	private static int dftgetSentenceNumberContainingTokenInCorpus(String token, int corpusIndex)
	{
		//dft, Dokument=Sentence
		//corpus|story|sentence|tokenmap|token
		//token=term
		//tfidf=tfdt*log(|D|/dft)
		//tfdt	= Häufigkeit von Token im aktuellen Satz
		//D		= Menge aller Sätze in Corpus: metaCorp.getCorpus(auswahl).getSentenceNumber()
		//dft	= Anzahl der Sätze aus Corpus, die token enthalten: Summe(distinct(AuswahlCorpus|story|sentence|0|0)) x-->return
		//
		token=token.toLowerCase();
		HashSet<String> tempSet= new HashSet<String>();
		FastList<String> positions =TokenObserver.getPositionOfString(token);
		for(int i=0; i<positions.size();i++)
		{
			String position=positions.get(i);
			if(position.startsWith(""+corpusIndex))
			{
				String[] arr=position.split("__");
				String neu=arr[1]+"__"+arr[2];
				
				tempSet.add(neu);
			}
		}
		

		return tempSet.size();
	}
	
	
	private static int dftgetStoryNumberContainingTokenInCorpus(String token, int corpusIndex)
	{
		//dft, Dokument=Story
		//Dokument =Story
		//corpus|story|sentence|tokenmap|token
		//token=term
		//tfidf=tfdt*log(|D|/dft)
		//tfdt	= Häufigkeit von Token in aktueller Story AuswahlCorpus|aktuelleStory|sentence|tokenmap|0)
		//D		= Menge aller Storys in Corpus: metaCorp.getCorpus(auswahl).size()
		//dft	= Anzahl der Storys aus Corpus, die token enthalten: Summe(distinct(AuswahlCorpus|story|0|0|0)) x-->return
		//
		token=token.toLowerCase();
		HashSet<String> tempSet= new HashSet<String>();
		FastList<String> positions =TokenObserver.getPositionOfString(token);
		
		if(positions!=null)
		{
		for(int i=0; i<positions.size();i++)
		{
			String position=positions.get(i);
			if(position.startsWith(""+corpusIndex))
			{
				String[] arr=position.split("__");
				String neu=arr[1];
				
				tempSet.add(neu);
			}
		}
		return tempSet.size();
		}
		return 1;
		
	}
	private static double tfidf(int tfdt, int dft, int d)
	{
	
		double tfidf= tfdt*Math.log(d/dft);
		return tfidf;
	}
	private static int tfdtgetFrequencyOfTokenInSentenceOfStory(String token, HashSet<String> sentences)
	{
		//tfdt,Dokument =Satz-zu klein
		//Dokument =Satz
		//corpus|story|sentence|tokenmap|token
		//token=term
		//tfidf=tfdt*log(|D|/dft)
		//tfdt	= Häufigkeit von Token im aktuellen Satz AuswahlCorpus|aktuelleStory|aktuellerSentence|tokenmap|0)
		//D		= Menge aller Sätze in Corpus: metaCorp.getCorpus(auswahl).getSentenceNumber()
		//dft	= Anzahl der Sätze aus Corpus, die token enthalten: Summe(distinct(AuswahlCorpus|story|sentence|0|0)) x
		//
		
		//Dokument =Story----> Beispielhaft
		//corpus|story|sentence|tokenmap|token
		//token=term
		//tfidf=tfdt*log(|D|/dft)
		//tfdt	= Häufigkeit von Token in aktueller Story (AuswahlCorpus|aktuelleStory|sentence|tokenmap|0)
		//D		= Menge aller Storys in Corpus: metaCorp.getCorpus(auswahl).size()
		//dft	= Anzahl der Storys aus Corpus, die token enthalten: Summe(distinct(AuswahlCorpus|story|0|0|0)) x
		//
		
		//Dokument =Corpus - zu willkürlich
		//corpus|story|sentence|tokenmap|token
		//token=term
		//tfidf=tfdt*log(|D|/dft)
		//tfdt	= Häufigkeit von Token in aktuellem Corpus (aktuellerCorpus|story|sentence|tokenmap|0)
		//D		= Menge aller Corpora: metaCorp.size()
		//dft	= Anzahl der Corpora, die token enthalten: Summe(distinct(corpus|0|0|0|0)) x
		//
		token=token.toLowerCase();
		FastList<String> positions =TokenObserver.getPositionOfString(token);
		int count=0;
		for(String pos: sentences)
		{
			String[] arrP=pos.split("__");
			int corpusIndex=Integer.parseInt(arrP[0]);
			int storyIndex=Integer.parseInt(arrP[1]);
			int sentenceIndex=Integer.parseInt(arrP[2]);
			for(int i=0; i<positions.size();i++)
			{
				String position=positions.get(i);
				String[] arr=position.split("__");
				int corpusIndex1=Integer.parseInt(arr[0]);
				int storyIndex1=Integer.parseInt(arr[1]);
				int sentenceIndex1=Integer.parseInt(arr[2]);
				if(corpusIndex==corpusIndex1&&storyIndex==storyIndex1&&sentenceIndex==sentenceIndex1)
				{
					count++;
				}
			}
		}

		return count;
	}
	private static int tfdtgetFrequencyOfTokenInStoryOfCorpus(String token, HashSet<String> stories)
	{
		//tfdt Dokument =Story
		
		//Dokument =Story
		//corpus|story|sentence|tokenmap|token
		//token=term
		//tfidf=tfdt*log(|D|/dft)
		//tfdt	= Häufigkeit von Token in (aktueller) Story 
		//D		= Menge aller Storys in Corpus: metaCorp.getCorpus(auswahl).size()
		//dft	= Anzahl der Storys aus Corpus, die token enthalten: Summe(distinct(AuswahlCorpus|story|0|0|0)) x
		//
		token=token.toLowerCase();
		FastList<String> positions =TokenObserver.getPositionOfString(token);
		if(positions!=null)
		{
		int count=0;
		
		for(String pos: stories)
		{
			String[] arrP=pos.split("__");
			int corpusIndex=Integer.parseInt(arrP[0]);
			int storyIndex=Integer.parseInt(arrP[1]);
			for(int i=0; i<positions.size();i++)
			{
				String position=positions.get(i);
				String[] arr=position.split("__");
				int corpusIndex1=Integer.parseInt(arr[0]);
				int storyIndex1=Integer.parseInt(arr[1]);
				//System.out.println(arr[0]+" "+corpusIndex+"|"+arr[1]+" "+storyIndex+" ");
				if(corpusIndex==corpusIndex1&&storyIndex==storyIndex1)
				{
					//System.out.println(count);
					count++;
				}
			}
			
		}
		
		

		return count;
		}
		else return 0;
		}
	
	/**
	 * Adds to this field the values of another field
	 * @param flf the other field
	 */
	public void addFieldValuesOfAnotherField(FrequencyListField flf)
	{
		this.frequency+=flf.frequency;
		this.shownValue+=flf.shownValue;
		this.positions.addAll(flf.getPositions());
		
		Set<Integer> set2= flf.occurrencies.keySet();
		//für jeden key aus  2 prüfe ob in 1; falls ja: update, falls nein: integration
		for(Integer key:set2)
		{
			if(this.occurrencies.containsKey(key))
			{
				this.occurrencies=helpAddField(key,  this.occurrencies,flf.occurrencies);
			}
			else
			{
				this.occurrencies.put(key, flf.occurrencies.get(key));
			}
		}
		//if(flf.getContent().get(1).equalsIgnoreCase("duftendem"))System.out.println(flf.getContent()+" "+frequency+" "+shownValue);
		
		
		
	}
	
	private HashMap<Integer, Double[]>  helpAddField(int key, HashMap<Integer, Double[]> hashmap, HashMap<Integer, Double[]> hashmap2)
	{
		Double[] array =hashmap.get(key);
		Double[] array2 =hashmap2.get(key);
		array[0]+=array2[0];
		hashmap.put(key, array);
		return hashmap;
	}
	
	/**
	 * sets the flags for this field
	 * @param hasNounFlag
	 * @param hasVerbFlag
	 * @param hasAdjectiveFlag
	 */
	public void setAllFlags(boolean hasNounFlag, boolean hasVerbFlag, boolean hasAdjectiveFlag)
	{
		this.hasNounFlag=hasNounFlag;
		this.hasVerbFlag=hasVerbFlag;
		this.hasAdjectiveFlag=hasAdjectiveFlag;
	}
//	public void setNounFlag(boolean hasNounFlag)
//	{
//		this.hasNounFlag=hasNounFlag;
//	}
//	public void setVerbFlag( boolean hasVerbFlag)
//	{
//		this.hasVerbFlag=hasVerbFlag;
//	}
//	public void setAdjectiveFlag(boolean hasAdjectiveFlag)
//	{
//		this.hasAdjectiveFlag=hasAdjectiveFlag;
//	}
	/**
	 * Returns the nounflag
	 * @return the nounflag, true if noun
	 */
	public boolean getNounFlag()
	{
		return hasNounFlag;
	}
	/**
	 *  returns the verbflag
	 * @return the verbflag, true if verb
	 */
	public boolean getVerbFlag()
	{
		return hasVerbFlag;
	}
	/**
	 * returns the adjective flag, true if adjektive
	 * @return the adjective flag
	 */
	public boolean getAdjectiveFlag()
	{
		return hasAdjectiveFlag;
	}
	
	/**
	 * Returns a list with modifiers
	 * @return the modifier list
	 */
	public ArrayList<Double> getFrequencyModifiers()
	{
		return frequencyModifiers;
	}
	
	/**
	 * returns the occurencies as hasmap
	 * @return the hashmap with occurencies
	 */
	public HashMap<Integer, Double[]> getOccurrencies()
	{
//		System.out.println();
		//System.out.println("getOcc: "+this.occurrencies.get(0)[0]);
		//System.out.println("getOcc: "+this.occurrencies.get(1)[0]);


		return occurrencies;
	}
	/**
	 * Increases the frequency of the listelement by the given increasement.
	 * @param increasement The number, you want to add, to the actual frequency.
	 * @param frequencyModifiers the modifiers
	 * @param occurrencies the occurencies
	 * @param key the key
	 * @param positions the positions
	 */

	public void increaseFrequency(double increasement, ArrayList<Double> frequencyModifiers, HashMap<Integer, Double[]> occurrencies, int key, HashSet<String> positions)
	{	
		this.positions=positions;

		this.frequencyModifiers=frequencyModifiers;
		//this.occurrencies.clear();
		this.occurrencies.put(key,occurrencies.get(key));
		frequency+=increasement;
		
		shownValue=frequency;
//		System.out.println("update: "+this.occurrencies.get(0)[0]+" "+frequency);
//		System.out.println("update2: "+getOccurrencies().get(0)[0]);
//		try{
//		System.out.println("1oc: "+this.occurrencies.get(1)[0]);System.out.println("update2: "+getOccurrencies().get(1)[0]);}catch(Exception e){}
//		System.out.println("ende: ");
//		System.out.println();
	}
//	public void setFrequency(double frequency, ArrayList<Double> frequencyModifiers)
//	{
//		this.frequencyModifiers=frequencyModifiers;
//		this.frequency=frequency;
//	}
	/**
	 * sets the frequency to the given value
	 * @param frequency the new value
	 */
	public void setFrequency(double frequency)
	{
	
		this.frequency=frequency;
		this.setValue(frequency);
	}
	
	/**
	 * Gives back the frequency of the listelement.
	 * @return The frequency of the listelement.
	 */
	public double getFrequency()
	{
		return frequency;
	}
	
	/**
	 * Gives back the shownvalue of the listelement.
	 * @return The shownvalue of the listelement.
	 */
	public double getValue()
	{
		return shownValue;
	}
	
	/**
	 * Sets the value of this field
	 * @param value the shownvalue
	 */
	public void setValue(double value)
	{
		this.shownValue=value;
	}
	/**
	 * Returns the relative frequency of this entry
	 * @param relationValue The value which is used for relativation
	 * @return the relative frequenvy
	 */
	public double getRelativeFrequency(int relationValue)
	{
		return frequency/relationValue;
	}
	
	/**
	 * Returns the string content of this field as arraylist
	 * @return
	 */
	public ArrayList<String> getContent()
	{
		ArrayList<String> list= new ArrayList<String>();
		for(int i=0;i<this.size();i++)
		{
			list.add(this.get(i));
		}
		return this;
	}

	/**
	 * Removes the first string of the stringcontent
	 * @return
	 */
	public FrequencyListField removeFirstString()
	{
		if(this.size()!=0)	this.remove(0);
		else return null;
		return this;
	}
	/**
	 * Compares two listfields with each other by using its frequency.
	 * @param o The other field you want to compare.
	 * @return 0:equals, 1: o has a higher frequency, -1: this has a higher frequency.
	 */
	public int compareTo(FrequencyListField o) 
	{
		 if (o.getValue() == this.getValue()) 
		 {
		      return 0;
		 }
		 if (o.getValue() >  this.getValue()) 
		 {
		      return 1;
		 }
		 if (this.getValue() >  o.getValue()) 
		 {
		      return -1;
		 }
		    return 0;
		  
	}
	
	/**
	 * Checks if two field equals each other
	 * @param o second field
	 * @return true if the string content of this field equals the second field string content
	 */
	public boolean equals(FrequencyListField o) 
	{
		
		 if (o.getContent().containsAll(this.getContent())) 
		 {
		      return true;
		 }
		    return false;
		  
	}
}
