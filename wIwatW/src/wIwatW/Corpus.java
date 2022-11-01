package wIwatW;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import javolution.util.FastList;
import javolution.util.FastMap;

/**
 * This class represents a textcorpus, which consists of stories.
 * @author Lasse Kohlmeyer
 * 
 * */
public class Corpus implements Serializable
{
	private static final long serialVersionUID = -6736855235942353674L;
	private ArrayList<Story> listOfStorys = new ArrayList<Story>();
	private PositionIDGenerator pIDG= new PositionIDGenerator();
	private String corpusID="";
	private int sentenceNumber=0;
	String name="";

	private Map<String, FastList<FastList<String>>> corpusMap = new FastMap<String,  FastList<FastList<String>>>();
	
	/**
	 * Creates an corpus, which should be filled with stories.
	 * @param id The corpus unique ID, given by the metacorpus.
	 * @param name The corpus name. You should avoid same names.
	 */
	public Corpus(String id, String name)
	{
		this.setCorpusID(id);
		this.name=name;
	}
	/**
	 * sorts the stories after ids
	 */
	public void correctOrderOfStories()
	{
		Collections.sort(listOfStorys);
	}
	/**
	 * Sets the corpus ID.
	 * @param id The new ID.
	 */
	public void setCorpusID(String id)
	{
		this.corpusID=id;
	}
	
	/**
	 * Attachs a tokenpositionmap to this metacorpus.
	 * @param map The map you want to attach to the metacorpus.
	 */
	void setMap(Map<String,  FastList<FastList<String>>> map)
	{
		this.corpusMap=map;
	}
	
	/**
	 * Returns the attached map of this metacorpus.
	 * @return The attached map.
	 */
	public Map<String,  FastList<FastList<String>>> getMap()
	{
		return this.corpusMap;
	}
	
	/**
	 * Returns the corpus ID.
	 * @return The corpus ID.
	 */
	public String getCorpusID()
	{
		return corpusID;
	}
	/**
	 * Returns the number of sentences in this corpus.
	 * @return The number of sentences as int.
	 */
	public int getSentenceNumber()
	{
		return sentenceNumber;
	}
	
	/**
	 * Returns the number of sentences in this corpus.
	 * @return The number of sentences as int.
	 */
	public int getTokenNumber()
	{
		int erg=0;
		for(int i=0; i<listOfStorys.size();i++)
		{
			erg+=listOfStorys.get(i).getTokenNumber();
		}
		return erg;
	}
	/**
	 * Adds a story to a corpus given as string.
	 * @param story The whole story as one string.
	 */
	public void addStoryAsSentence(String story)
	{
		//System.out.println("addStoryAsSentence");
		Story story1 = new Story(story,corpusID+"__"+pIDG.generateID());
		listOfStorys.add(story1);
		//System.out.println("addStoryAsSentence");
		correctOrderOfStories();
//		for(Story s: listOfStorys)
//		{
//			//System.out.println(s.getrelativeID()+" "+s.getStoryID()+" "+s.identifier());
//		}
		sentenceNumber+=story1.getSentenceNumber();
	}
	
	/**
	 * Adds a story to a corpus given as string.
	 * @param story The whole story as one string.
	 */
	public void addStoryAsSentenceWithoutComputing(String story)
	{
		//System.out.println("addStoryAsSentence");
		Story story1 = new Story(story,corpusID+"__"+pIDG.generateID(),0);
		listOfStorys.add(story1);
		//System.out.println("addStoryAsSentence");
		correctOrderOfStories();
//		for(Story s: listOfStorys)
//		{
//			//System.out.println(s.getrelativeID()+" "+s.getStoryID()+" "+s.identifier());
//		}
		
	}
	
	private synchronized void increaseSentenceNumber(int i)
	{
		sentenceNumber+=i;
	}
	
	/**
	 * computes all stories
	 */
	public void computeAllStories()
	{
		
		listOfStorys.parallelStream().forEach(s->{
			if(s.isComputed()==false) 
			{
			//System.out.println("			"+s.getStoryID());
			s.computeStory();
			
			increaseSentenceNumber(s.getSentenceNumber());
			}
//		Serializator.saveMetaCorpus(metaCorp, savePath);
		});
	}
	
	/**
	 * Gets the element i of the Corpus
	 * @param i should be >0
	 * @return the i-th Story
	 */
	public Story getStoryByIndex(int i)
	{
		return listOfStorys.get(i);
	}
	
	/**
	 * Returns the size of the Corpus, which is defined as numbers of stories in it.
	 * @return The corpus size.
	 */
	public int size()
	{
		return listOfStorys.size();
	}
	
	/**
	 * Checks if the story, given as string, already exists in this corpus.
	 * @param story The checked story.
	 * @return A boolean representating the existence.
	 */
	public boolean storyExists(String story)
	{
		for(int i=0; i<listOfStorys.size();i++)
		{
			if(listOfStorys.get(i).equalsString(story)) return true;
		}
		return false;
	}
	
	/**
	 * Returns a token of this corpus.
	 * @param storyIndex
	 * @param sentenceIndex
	 * @param tokenIndex
	 * @param translationAlgorithmIndex
	 * @return The token as String.
	 */
	public String getToken( int storyIndex, int sentenceIndex, int tokenIndex, int translationAlgorithmIndex)
	{
		return this.getStoryByIndex(storyIndex).getSentenicedStory()[sentenceIndex].getTokenizedSentence()[tokenIndex].getEntry(translationAlgorithmIndex).getStringContent();

	}

//	public boolean equals(Corpus corpus)
//	{
//		for(int i=0; i<corpus.size();i++)
//		{
//			for(int j=0; j<this.size();j++)
//			{
//				if(corpus.getStoryByIndex(i).equals(this.getStoryByIndex(j).identifier())==false) return false;
//			}
//		}
//
//	return true;
//	}

}
