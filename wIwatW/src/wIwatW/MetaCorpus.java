package wIwatW;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

import javolution.util.FastList;
import javolution.util.FastMap;

/*
 * Meta Corpus: fasst mehrere Corpora zusammen
 * CorporaReader/einzelner Korpus, bestehend aus mehreren Texten
 * Text: besteht mindestens aus einem Satz
 * Satz: Stringabfolge, die durch Punkt getrennt wird
 * 
 * */

/**
 * This class represents your metacorpus which consists of all stories you have loaded to the programm.
 * @author Lasse
 *
 */
public class MetaCorpus implements Serializable {
	
	private static final long serialVersionUID = 1942986080642153948L;
	private ArrayList<Corpus> metaCorpus = new ArrayList<Corpus>();
	private Map<String, FastList<FastList<String>>> metaMap = new FastMap<String,  FastList<FastList<String>>>();
	private PositionIDGenerator pIDG= new PositionIDGenerator();
	@SuppressWarnings("unused")
	private int sentenceNumber=0;
	
	/**
	 * Adds a new story in a chosen corpus.
	 * @param corpusName The name of the corpus in which you want to store the new story.
	 * @param story The story in a single string.
	 */
	
	public ArrayList<String> getCorporaListAsString()
	{
		ArrayList<String> temp = new ArrayList<String>();
		for (Corpus c: metaCorpus)
		{
			temp.add(c.name);
		}
		return temp;
	}
	
	/**
	 * Returns all corpora in this metacorpus
	 * @return the lsit with the corpora
	 */
	public ArrayList<Corpus> getCorporaList()
	{

		return metaCorpus;
	}
	
	/**
	 * Adds a story to a corpus but only, if this story is not already added
	 * @param corpusName the corpus name
	 * @param story the story as string
	 */
	public void addStoryinCorpusButOnlyByNoExistence(String corpusName, String story)
	{
		//if(this.storyExistsInCorpus(story, corpusName)==false) 
		if(this.storyExists(story)==false) 
		{
			String temp= Story.makeIdentifier(story);
			if(temp.length()>20)
			{
				
				System.out.println(">> Story: <"+temp.substring(0, 20)+"> wird hinzugefügt zu: <"+corpusName+">");
			}
			else
			{
				System.out.println(">> Story: <"+temp.substring(0, temp.length())+"> wird hinzugefügt zu: <"+corpusName+">");
			}
			//metaCorp.addStoryInCorpus(corpusName,s);
			this.addStoryInCorpus(corpusName,story);
			//Serializator.saveMetaCorpus(metaCorp, savePath);
			
		}
	}
	
//	public void addStoryInCorpus(String corpusName, String story)
//	{
////		System.out.println("addStoryinCorpusMetaCorp");
////		int index=this.getCorpusIndexByName(corpusName);
////		if(index>-1) 
////		{
////			
////			metaCorpus.get(index).addStoryAsSentence(story);
////			
////		}
////		else
////		{
////			//System.out.println("addStoryinCorpusMetaCorp");
////			
////			Corpus c = new Corpus(pIDG.generateID(),corpusName);
////			
////			c.addStoryAsSentence(story);
////			
////			metaCorpus.add(c);
////			
////		}
////		for(int i=0; i<this.getCorpusNumber();i++)
////		{
////			this.sentenceNumber+=this.getCorpusByIndex(i).getSentenceNumber();
////		}
//		//System.out.println(corpusName);
//		int index;
//		try {
//			index = this.getCorpusIndexByName(corpusName);
//			//System.out.println("fülle alten Corpus");
//			metaCorpus.get(index).addStoryAsSentence(story);
//		} catch (NoCorpusException nce) {
//			
//			System.out.println("erstelle neuen Corpus"+corpusName);
//			String idCorpus=pIDG.generateID();
//			Corpus c = new Corpus(idCorpus,corpusName);
//			//System.out.println("add Story vor"+idCorpus);
//			c.addStoryAsSentence(story);
//			metaCorpus.add(c);
//			//System.out.println("add Story nach"+idCorpus);
//
//			
//		} catch (ToManyCorporaException tmce) {
//			
//			System.out.println("zu viele Corpora");
//
//
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
////		for(int i=0; i<this.getCorpusNumber();i++)
////		{
////			this.sentenceNumber+=this.getCorpusByIndex(i).getSentenceNumber();
////		}
//	}
	/**
	 * Adds a story in a corpus
	 * @param corpusName the corpus name
	 * @param story the story as string
	 */
	public void addStoryInCorpus(String corpusName, String story)
	{
//		System.out.println("addStoryinCorpusMetaCorp");
//		int index=this.getCorpusIndexByName(corpusName);
//		if(index>-1) 
//		{
//			
//			metaCorpus.get(index).addStoryAsSentence(story);
//			
//		}
//		else
//		{
//			//System.out.println("addStoryinCorpusMetaCorp");
//			
//			Corpus c = new Corpus(pIDG.generateID(),corpusName);
//			
//			c.addStoryAsSentence(story);
//			
//			metaCorpus.add(c);
//			
//		}
//		for(int i=0; i<this.getCorpusNumber();i++)
//		{
//			this.sentenceNumber+=this.getCorpusByIndex(i).getSentenceNumber();
//		}
		//System.out.println(corpusName);
		int index;
		try {
			index = this.getCorpusIndexByName(corpusName);
			//System.out.println("fülle alten Corpus");
			metaCorpus.get(index).addStoryAsSentenceWithoutComputing(story);
			
		} catch (NoCorpusException nce) {
			
			System.out.println("erstelle neuen Corpus"+corpusName);
			String idCorpus=pIDG.generateID();
			Corpus c = new Corpus(idCorpus,corpusName);
			//System.out.println("add Story vor"+idCorpus);
			c.addStoryAsSentenceWithoutComputing(story);
			metaCorpus.add(c);
			//System.out.println("add Story nach"+idCorpus);

			
		} catch (ToManyCorporaException tmce) {
			
			System.out.println("zu viele Corpora");


			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		for(int i=0; i<this.getCorpusNumber();i++)
//		{
//			this.sentenceNumber+=this.getCorpusByIndex(i).getSentenceNumber();
//		}
	}
	
	/**
	 * Adds a corpus ti the metacorpus
	 * @param c the added corpus
	 */
	public void addCorpus(Corpus c)
	{
		if(true/*corpus existiert noch nicht=corpus mit verschiedenem namen aber gleichen geschichten oder corpus mit gleichem Namen*/)
		this.metaCorpus.add(c);
		this.metaMap.putAll(c.getMap()); //so regeln, dass alte werte erhalten bleiben
	}
	/**
	 * Attachs a tokenpositionmap to this metacorpus.
	 * @param map The map you want to attach to the metacorpus.
	 */
	synchronized void  setMap(Map<String,  FastList<FastList<String>>> map)
	{
		this.metaMap=map;
		
	}
	
	/**
	 * Returns the position id for extern use
	 * @return the position id
	 */
	public String giveIDtoExtern()
	{
		return pIDG.generateID();
	}
	
	/**
	 * Checks if a corpus name already exist in the corpus
	 */
	public boolean corpusNameExists(String c)
	{
		for(int i=0; i<metaCorpus.size();i++)
		{
			if(metaCorpus.get(i).name.equals(c)) return true;
		}
		return false;
	}
	/**
	 * Returns the attached map of this metacorpus.
	 * @return The attached map.
	 */
	public synchronized  Map<String,  FastList<FastList<String>>> getMap()
	{
		return this.metaMap;
	}
	
	/**
	 * Returns a corpus by a given index.
	 * @param index The position of the corpus.
	 * @return The chosen corpus.
	 */
	public Corpus getCorpusByIndex(int index)
	{
		if (index==-1) System.out.println("Corpus existiert nicht");
		return metaCorpus.get(index);
	}	
	
	/**
	 * Returns the number of sentences in this story.
	 * @return The number of sentences as int.
	 */
	public int getSentenceNumber()
	{
		int erg=0;
	
		for(int i=0; i<this.getCorpusNumber();i++)
		{
			for(int j=0; j<this.getStoryNumber(i);j++)
			{
				erg+=this.getSentenceNumber(i, j);
			}
			//sentenceNumber+=this.getCorpusByIndex(i).getSentenceNumber();
		}
		return erg;
	}
	/**
	 * Returns the index of the corpus with the given name.
	 * @param name The corpus name.
	 * @return The integer value of the index. It is -1 if there is no corpus or more than one of that name.
	 */
	public int getCorpusIndexByName(String name) throws Exception
	{
//		int index=-1;
//		int count=0;
//		for(int i=0;i<metaCorpus.size();i++)
//		{
//			if(metaCorpus.get(i).name.equals(name))
//			{
//				index=i;
//				count++;
//			}
//		}
//		
//		if(count>1) index=-1;
//		if(index==-1) System.out.println("Achtung, Corpus: "+name+" nicht 1-mal vorhanden");
//		return index;
		
		int index=-1;
		int count=0;
		for(int i=0;i<metaCorpus.size();i++)
		{
			if(metaCorpus.get(i).name.equals(name))
			{
				index=i;
				count++;
			}
		}
		
		if(count>1)throw new ToManyCorporaException();
		if(index==-1) {
			/*System.out.println("Achtung, Corpus: "+name+" nicht 1-mal vorhanden");*/
			throw new NoCorpusException(); 
			}
		return index;
	}
	
	
	/**
	 * Returns the size of the metacorpus. The size is defined as the number of corpora in it.
	 * @return The number of corpora.
	 */
	public int getCorpusNumber()
	{
		return metaCorpus.size();
	}
	
	/**
	 * Returns the size of the metacorpus. The size is defined as the number of tokens in it.
	 * @return The number of tokens.
	 */
	public int sizeInTokens()
	{
		int count=0;
		for(int i=0; i<this.getCorpusNumber();i++)
		{
			for(int j=0; j<this.getStoryNumber(i);j++)
			{
				for(int k=0; k<this.getSentenceNumber(i, j);k++)
				{
					for(int l=this.getTokenNumber(i, j, k)-1; l>-1;l--)
					{
						count+=this.getTokenMapNumber(i, j, k, l);
						
					}
				}
			}
		}
		return count;
	}
	

	/**
	 * Returns a token of the given multidimensional position.
	 * @param corpusIndex The corpus you navigate in.
	 * @param storyIndex The story you navigate in.
	 * @param sentenceIndex The sentence you navigate in.
	 * @param tokenIndex The token you navigate in.
	 * @param translationAlgorithmIndex The tokenmap you navigate in.
	 * @return The token of the given position.
	 */
	public String getToken(int corpusIndex, int storyIndex, int sentenceIndex, int tokenIndex, int translationAlgorithmIndex)
	{
		return this.getCorpusByIndex(corpusIndex).getStoryByIndex(storyIndex).getSentenicedStory()[sentenceIndex].getTokenizedSentence()[tokenIndex].getEntry(translationAlgorithmIndex).getStringContent();

	}
	
	/**
	 * Returns the number of stories in the chosen corpus.
	 * @param corpusIndex
	 * @return The number of stories in the chosen corpus.
	 */
	public int getStoryNumber(int corpusIndex)
	{
		return this.getCorpusByIndex(corpusIndex).size();
	}
	
	/**
	 * Returns the number of sentences in the chosen story in the chosen corpus.
	 * @param corpusIndex
	 * @param storyIndex
	 * @return The number of sentences in the chosen story in the chosen corpus.
	 */
	public int getSentenceNumber(int corpusIndex, int storyIndex)
	{
		return this.getCorpusByIndex(corpusIndex).getStoryByIndex(storyIndex).size();
	}
	
	/**
	 * Returns the number of tokens in the chosen sentence in the chosen story in the chosen corpus.
	 * @param corpusIndex
	 * @param storyIndex
	 * @param sentenceIndex
	 * @return The number of tokens in the chosen sentence in the chosen story in the chosen corpus.
	 */
	public int getTokenNumber(int corpusIndex, int storyIndex, int sentenceIndex)
	{
		
		return this.getCorpusByIndex(corpusIndex).getStoryByIndex(storyIndex).getSentenicedStory()[sentenceIndex].size();
	}
	
	/**
	 * Returns the number of tokens in the chosen sentence in the chosen story in the chosen corpus.
	 * @param corpusIndex
	 * @param storyIndex
	 * @param sentenceIndex
	 * @return The number of tokens in the chosen sentence in the chosen story in the chosen corpus.
	 */
	public int getTokenNumberInCorpus(int corpusIndex)
	{
		
		return getCorpusByIndex(corpusIndex).getTokenNumber();
	}
	/**
	 * Returns the number of different translations.
	 * @param corpusIndex
	 * @param storyIndex
	 * @param sentenceIndex
	 * @param tokenIndex
	 * @return The number of different translations.
	 */
	public int getTokenMapNumber(int corpusIndex, int storyIndex, int sentenceIndex, int tokenIndex)
	{
		return this.getCorpusByIndex(corpusIndex).getStoryByIndex(storyIndex).getSentenicedStory()[sentenceIndex].getTokenizedSentence()[tokenIndex].size();
		
	}
	
	/**
	 * Checks if the given story as string already exists in the metacorpus.
	 * @param story The story you want to check
	 * @return A boolean representating the existence.
	 */
	public boolean storyExists(String story)
	{
		for(int i=0; i<metaCorpus.size();i++)
		{
			if(metaCorpus.get(i).storyExists(story)) return true;
		}
		return false;
	}
	
//	public String getTokenOfFlatMetaCorpus(int id)
//	{
//		int count=0;
//		for(int j=0; j<getCorpusNumber();j++)//jedes Token
//		{
//			for(int k=0; k<getStoryNumber(j);k++)//jedes Token
//			{
//				for(int l=0; l<getSentenceNumber(j, k);l++)//jedes Token
//				{
//					for(int m=0; m<getTokenNumber(j, k, l);m++)//jedes Token
//					{
//						if(count==id)
//						{
//							return this.getToken(j, k, l, m, 0);
//						}
//						count++;
//					}
//				}
//			}
//		}
//		
//		return "";
//	}
	
	/**
	 * Checks if the story exists in the corpus
	 * @param story the story which should be checked
	 * @param corpusName the corpus name
	 * @return true by existence
	 */
	public boolean storyExistsInCorpus(String story, String corpusName)
	{
//		int index=getCorpusIndexByName(corpusName);
//		
//		
//		if(index>-1&&metaCorpus.get(index).storyExists(story)) return true;
		
		int index;
		try {
			index = getCorpusIndexByName(corpusName);
			if(index>-1&&metaCorpus.get(index).storyExists(story)) return true;	
//			System.out.println("...."+index+corpusName);
//			System.out.println("...."+story.substring(0, 10));
		} catch (Exception e) {
			//System.out.println(e);
			return false;
		}
		return false;
	}
//	public void writeObject(java.io.ObjectOutputStream stream)
//            throws IOException {
//        stream.writeObject(metaCorpus);
//        stream.writeObject(metaMap);
//        stream.writeObject(pIDG);
//        stream.writeInt(sentenceNumber);
//        System.out.println("biiiiiiiiiiiiiiiiiingo");
//    }
//
//	public void readObject(java.io.ObjectInputStream stream)
//            throws IOException, ClassNotFoundException {
//    	metaCorpus = (ArrayList<Corpus>) stream.readObject();
//    	metaMap = (Map<String, FastList<FastList<String>>>) stream.readObject();
//    	pIDG = (PositionIDGenerator)stream.readObject();
//    	sentenceNumber=stream.readInt();
//    }
//	/**
//	 * flats the metacorpus to a single arraylist.
//	 * @return
//	 */
//	private ArrayList<String> flat()
//	{
//		ArrayList<String> flatening = new ArrayList<String>();
//		for(int i=0; i<this.getCorpusNumber();i++)
//		{
//			for(int j=0; j<this.getStoryNumber(i);j++)
//			{
//				for(int k=0; k<this.getSentenceNumber(i, j);k++)
//				{
//					for(int l=this.getTokenNumber(i, j, k)-1; l>-1;l--)
//					{
//						for(int m=0; m<this.getTokenMapNumber(i, j, k, l);m++)
//						{
//									flatening.add(this.getToken(i, j, k, l, m));
//						}
//					}
//				}
//			}
//		}
//		
//		
//		
//		return flatening;
//		
//	}
//	
//	private String[] toFlatArray()
//	{
//		String[] flatening = new String[this.sizeInTokens()];
//		int z=0;
//		for(int i=0; i<this.getCorpusNumber();i++)
//		{
//			for(int j=0; j<this.getStoryNumber(i);j++)
//			{
//				for(int k=0; k<this.getSentenceNumber(i, j);k++)
//				{
//					for(int l=this.getTokenNumber(i, j, k)-1; l>-1;l--)
//					{
//						for(int m=0; m<this.getTokenMapNumber(i, j, k, l);m++)
//						{
//									flatening[z]=(this.getToken(i, j, k, l, m));
//									z++;
//						}
//					}
//				}
//			}
//		}
//		
//		
//
//		
//		return flatening;
//		
//	}


//	public boolean corpusExists(Corpus corpus)
//	{
//	
//		if(metaCorpus.contains(corpus)) return true;
//		
//		return false;
//	}


}
/**
 * exception if a corpus does not exist
 * @author Lasse
 *
 */
class NoCorpusException extends Exception
{
    /**
	 * 
	 */
	private static final long serialVersionUID = -278543861747158382L;

	NoCorpusException()
    {
        super("No such Corpus!");
    }
}

/**
 * exception of too many copora with the same name exist
 * @author Lasse
 *
 */
class ToManyCorporaException extends Exception
{
    /**
	 * 
	 */
	private static final long serialVersionUID = -278543861747158382L;

	ToManyCorporaException()
    {
        super("To many Corpora!");
    }
}
