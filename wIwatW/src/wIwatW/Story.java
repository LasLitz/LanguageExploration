package wIwatW;


import java.io.Serializable;
import java.util.ArrayList;
/**
 * This class represents a story, which consists of sentences.
 * @author Lasse Kohlmeyer
 *
 */
public class Story implements Serializable, Comparable<Story>
{
	private static final long serialVersionUID = -5955844751577637227L;
	private Sentence[] storyAsArray;
	private String story="";
	private boolean computed=false;
	private String identifier="";
	private PositionIDGenerator pIDG= new PositionIDGenerator();
	private String storyID="";

	private int relativeID=-1;
	private int sentenceNumber=0;
	
	/**
	 * Returns the Content of a story as a single string
	 * @return The content of this story
	 */
	public String getStoryString()
	{
		return story;
	}
	/**
	 * Contructs a story by a given parameters, divides the story in its sentences.
	 * @param wholeStory The given story as string.
	 * @param id The ID of the story given by the corpus.
	 */
	public Story(String wholeStory, String id)
	{	
		identifier=makeIdentifier(wholeStory);
		this.setStoryID(id);
		//System.out.println("alles "+wholeStory);
		story=preprocessing(wholeStory);
		
		String[] postags = POSTags.givePostags(story);//-->eventuell nach unten schieben
//		String[] baseforms= Wortschatz.getBaseforms(Sentence.tokenizeSimple(story));
//		for(int i=0;i<baseforms.length;i++)
//		{
//			System.out.println(baseforms[i]);
//		}
		
		story=PreprocessingMethods.addEndOfSentence(story);
		
		sentenice(postags);
		
		
		//System.out.println("Story.Story()| ID:"+id+"|"+identifier);
	}
	/**
	 * Contructs a story by a given parameters, divides the story in its sentences. This dont computes features.
	 * @param wholeStory The given story as string.
	 * @param id The ID of the story given by the corpus.
	 * @param nul Additional Parameter for not computing
	 */
	public Story(String wholeStory, String id, int nul)
	{	
		identifier=makeIdentifier(wholeStory);
		this.setStoryID(id);
		story=preprocessing(wholeStory);
		
//		String[] postags = POSTags.givePostags(story);
//		story=PreprocessingMethods.addEndOfSentence(story);
//		sentenice(postags);
		
		
		//System.out.println("Hinzugefügt mit Story.Story()| ID:"+id+"|"+identifier);
	}
	
	/**
	 * Sets the ID.
	 * @param id The Storys id
	 */
	public void setStoryID(String id)
	{
		this.storyID= id;
		String[] arr=id.split("__");
		this.relativeID=Integer.parseInt(arr[arr.length-1]);
	}
	
	/**
	 * Returns the ID.
	 * @return The ID as String.
	 */
	public String getStoryID()
	{
		return storyID;
	}
	public synchronized boolean isComputed()
	{
		return computed;
	}
	/**
	 * Computes all Features of a story, which means, it will be senteniced
	 */
	public  void computeStory()
	{
		String[] postags = POSTags.givePostags(story);
		story=PreprocessingMethods.addEndOfSentence(story);
		sentenice(postags);
		computed=true;
	}
	/**
	 * Returns the relative story id (2nd id-number of positionid)
	 * @return The relative id
	 */
	public int getrelativeID()
	{
		return this.relativeID;
	}
	
	/**
	 * Returns the number of tokens of this story
	 * @return The Number of tokens
	 */
	public int getTokenNumber()
	{
		int erg=0;
		for(int i=0; i<storyAsArray.length;i++)
		{
			erg+=storyAsArray[i].getTokenNumber();
		}
		return erg;
	}
	
	/**
	 * Returns the number of sentences in this story.
	 * @return The number of sentences as int.
	 */
	public int getSentenceNumber()
	{
		return sentenceNumber;
	}
	/**
	 * This method divides a text into its individual sentences. Each sentence is saved in one element of the storylist.
	 * @param postags The postags of this story.
	 */
	private void sentenice(String[] postags)//<---parallelisieren?
	{
		String str=story;
		//System.out.println("!!!!!!"+str);
		
		//String[] mem=str.split(Pattern.quote( "." ) );
		String[] mem=str.split("%§%EOS%§%" );
		storyAsArray=new Sentence[mem.length];
		//System.out.println("Story.:" + story + mem.length);
		ArrayList<Sentence> storyAsList = new ArrayList<Sentence>();
		//System.out.println("!!!!!!");

		for(int j=0; j<mem.length;j++)
		{
			String[][] tmp=POSTagArrayOfSentence(mem[j],postags);
			//System.out.println("Satz:"+mem[j]);
			Sentence sentence= new Sentence(PreprocessingMethods.reduceSpace((" "+ mem[j])+""),tmp[0],storyID+"__"+pIDG.generateID(),1);
			postags=tmp[1];
			storyAsArray[j]=sentence;
			storyAsList.add(sentence);
		}
		

		//System.out.println("---------------------------->S");//883s parallel, 1375 sonst
		storyAsList.parallelStream().forEach(sentence-> //wieder parallel machen!
		{
		
			sentence.setSentence();
		});
		//System.out.println("!!!");
		storyAsArray = storyAsList.toArray(new Sentence[0]);
		sentenceNumber=storyAsArray.length;
	
	}
	
	/**
	 * Divides the postags of the story. After this each sentence will have its postags and not more.
	 * @param sentence The input sentence.
	 * @param arr1 The postags array.
	 * @return The correct postags of the sentence.
	 */
	private String[][] POSTagArrayOfSentence(String sentence, String[] arr1)
	{
		
		
		String[][] erg=new String[2][];
		
		String[] tmpArr0=Sentence.tokenizeSimple(sentence);
		int TokensInArr0i=tmpArr0.length;
		String[] array=new String[TokensInArr0i];
		for(int j=0;j<TokensInArr0i;j++)
		{
			try{
//				for(int i=0;i<arr1.length;i++)
//				{
//					System.out.println(arr1[i]+" "+tmpArr0[i]);
//				}
				array[j]=arr1[j];
			
			
			}catch(Exception e)
			{
				System.out.println("--------> J: "+j+"Satz: "+sentence+" <> "+array.length+" <> "+arr1.length+" "+arr1[arr1.length-1]);
				
				for(int i=0;i<arr1.length;i++)
				{
					System.out.println(arr1[i]+" "+tmpArr0[i]);
				}
				e.printStackTrace();
				break;
			}
		}
		String[] postagsNew=new String[arr1.length-TokensInArr0i];
		for(int j=TokensInArr0i;j<arr1.length;j++)
		{
			postagsNew[j-TokensInArr0i]=arr1[j];
		}
		
		erg[0]=array;
		erg[1]=postagsNew;
		return erg;
	}


	/**
	 * Returns the  story as a sequence of sentences in a list of sentence objects.
	 * @return ArrayList<Sentence> of sentences.
	 */
	public Sentence[] getSentenicedStory()
	{
		return storyAsArray;
	}
	

	/**
	 * returns the size of the story. This size is defined as the number of sentences.
	 * @return Number of sentences.
	 */
	public int size()
	{
		return storyAsArray.length;
	}
	
	/**
	 * Returns the preprocessed text.
	 * @param text The input text.
	 * @return The output.
	 */
	private String preprocessingCode(String text)
	{
		//System.out.println("preprocessingAlles "+text); //funktioniert nicht gut
	//	text=PreprocessingMethods.reducePDF(text);
		//text=PreprocessingMethods.gapToHeadline(text);
//		text=PreprocessingMethods.deletePointsAndNumbersAfterNumbers(text);
//		text=PreprocessingMethods.deletePointsBetweenNumbers(text);
//		text=PreprocessingMethods.deleteNumbers(text);
		//text=PreprocessingMethods.reduceVocals(text);
		
//		text=PreprocessingMethods.deleteExtraSigns(text);
		
		
		//text=PreprocessingMethods.reduceMultipleSignsToOne(PreprocessingMethods.deleteMinus(text));//bisher
		//text=PreprocessingMethods.deleteEverythingButNumbersLatinsAndSentenceMarks(text);
//		text=PreprocessingMethods.reduceSentenceMarks(text);
//		text=PreprocessingMethods.reduceMultipleSignsToOne(PreprocessingMethods.deleteMinus(text));
		
		text=PreprocessingMethods.reduceSpace(PreprocessingMethods.makeSpace(text));
		

		//text=PreprocessingMethods.addEndOfSentence(text);
		//text=PreprocessingMethods.reduceSpace(text);
		//System.out.println(text);
		//System.out.println("preprocessingAlles "+text);
		return text;
	}
	
	private String preprocessing(String text)
	{
		text=PreprocessingMethods.reducePDF(text);
		//text=PreprocessingMethods.gapToHeadline(text);
		text=PreprocessingMethods.deletePointsAndNumbersAfterNumbers(text);
		text=PreprocessingMethods.deletePointsBetweenNumbers(text);
		text=PreprocessingMethods.deleteNumbers(text);
		//text=PreprocessingMethods.reduceVocals(text);
		
		text=PreprocessingMethods.deleteExtraSigns(text);
		
		
		//text=PreprocessingMethods.reduceMultipleSignsToOne(PreprocessingMethods.deleteMinus(text));//bisher
		//text=PreprocessingMethods.deleteEverythingButNumbersLatinsAndSentenceMarks(text);
		text=PreprocessingMethods.reduceSentenceMarks(text);
		text=PreprocessingMethods.reduceMultipleSignsToOne(PreprocessingMethods.deleteMinus(text));
		
		text=PreprocessingMethods.reduceSpace(PreprocessingMethods.makeSpace(text));
		

		//text=PreprocessingMethods.addEndOfSentence(text);
		//text=PreprocessingMethods.reduceSpace(text);
		//System.out.println(text);
		return text;
	}
	
	/**
	 * Returns the identifier of the story. An identifier is a unique short string, that represents the story and is computed by the text. It is not version save.
	 * @return the storys identifier
	 */
	public String identifier()
	{
		return this.identifier;
	}
	
	/**
	 * Generates an identifier by a simple function. An identifier is a unique short string, that represents the story and is computed by the text.  It is not version save, can be improved.
	 * @param string The wholestory.
	 * @return The identifier.
	 */
	public static String makeIdentifier(String string)
	{
		String erg="";
		int i= Math.round(string.length()/2);
		if(i<=30)return PreprocessingMethods.deleteWhitespace(string);
		while(i>30)
		{
			i=Math.round(i/2);
		}
			erg+= string.substring(0, i );
			erg+=string.substring(Math.round(string.length()/2)-i, Math.round(string.length()/2)+i );//rausnehmen bei nächstem neuen Metakorpus
			erg+=string.substring(string.length()-1-i, string.length()-1);
		return PreprocessingMethods.deleteWhitespace(erg);
	}
		
	/**
	 * Checks if this story equals another story given by a string.
	 * @param story The other story.
	 * @return A boolean representating the equality.
	 */
	public boolean equalsString(String story)
	{

		story=makeIdentifier(story);
		if (this.identifier().equalsIgnoreCase(story)) 
			{
				return true;
			}
		return false;
	}

	@Override
	/**
	 * Compares two stories with each other. The Comparison is based on the relative story ids.
	 * @return 0 if equals, 1 if this story id is bigger, else -1
	 */
	public int compareTo(Story o) {
		 if (o.relativeID == this.relativeID) 
		 {
		      return 0;
		 }
		 if (o.relativeID <  this.relativeID) 
		 {
		      return 1;
		 }
		 if (this.relativeID <  o.relativeID) 
		 {
		      return -1;
		 }
		    return 0;
	}
}
