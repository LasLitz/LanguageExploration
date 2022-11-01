package wIwatW;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringTokenizer;
/**
 * 
 * This class represents a sentence, which consists of TokenMaps. TokenMaps are lists of tokens with their translations.
 * @author Lasse Kohlmeyer
 *
 */
public class Sentence implements Serializable
{
	private static final long serialVersionUID = 7439387549121587595L;
	private String sentence="";
	private String[] postags;
	private TokenMap[] tokenizedSentence; 
	private double asw, asl=0;
	private double ms=0, iw=0, es=0;
	private PositionIDGenerator pIDG= new PositionIDGenerator();
	private String sentenceID="";
	
	/**
	 * Creates a new sentence. It will be tokenized.
	 * @param sentence The new sentence.
	 * @param postags storybased Information 
	 * @param id The ID given by the story.
	 */
	public Sentence(String sentence, String[] postags, String id)
	{
		this.setSentenceID(id);
		this.sentence=sentence;
		tokenize(postags, true);
	}
	/**
	 * Creates a new sentence. It wont be tokenized.
	 * @param sentence The new sentence.
	 * @param postags storybased Information 
	 * @param id The ID given by the story.
	 * @param i paramter for not tokenizing
	 */
	public Sentence(String sentence, String[] postags, String id, int i)
	{
		this.setSentenceID(id);
		this.sentence=sentence;
		this.postags=postags;
	}
	/**
	 * Creates a new sentence without string content.
	 * @param postags storybased Information 
	 * @param id The ID given by the story.
	 */
	public Sentence(String[] postags, String id)
	{
		this.setSentenceID(id);
		this.postags=postags;
	}
	/**
	 * Tokenizes the sentence
	 */
	public void setSentence(/*String sentence*/)
	{
		
		//System.out.println("setSentence!");
		if(postags==null)System.out.println("nulL!");
		tokenize(postags, true);
	}

	/**
	 * Creates a new sentence. It will be tokenized.
	 * @param sentence The new sentence.
	 * @param postags storybased Information 
	 * @param id The ID given by the story.
	 * @param saveFlag if the sentence should saved this is true
	 */
	public Sentence(String sentence, String[] postags, String id, boolean saveFlag)
	{
		this.setSentenceID(id);
		this.sentence=sentence;
		tokenize(postags,saveFlag);
	}
	/**
	 * Sets the ID.
	 * @param id
	 */
	public void setSentenceID(String id)
	{
		this.sentenceID=id;
	}
	
	/**
	 * Returns the ID.
	 * @return id of this sentence.
	 */
	public String getSentenceID()
	{
		return sentenceID;
	}
	
	/**
	 * Returns the number of tokens in this sentence
	 * @return the tokens number
	 */
	public int getTokenNumber()
	{
		return tokenizedSentence.length;
	}
	
	/**
	 * Tokenize a sentence by using the Java string tokenizer.
	 * @param postags storybased Information 
	 */
	private void tokenize(String[] postags, boolean saveFlag)
	{
		//System.out.println("Sent "+sentence);
		StringTokenizer st = new StringTokenizer(sentence);
		int tokenNumber=st.countTokens();
		tokenizedSentence= new TokenMap[tokenNumber];
	
		int kommacount=0;
		ArrayList<TokenMap> tokenMaps = new ArrayList<TokenMap>();
	    while (st.hasMoreTokens()) 
	    {
	    	String str=st.nextToken();
	    	  if(str.equals(",")){kommacount++;}
		    	 int numSyl= Translator.numberOfSyllables(str);
		    	 //System.out.println("NS"+numSyl);
		    	 if(numSyl>2){ms++;}
		    	 if(numSyl==1){es++;}
		    	 if(str.length()>6){iw++;}
		    	 //System.out.println("es"+es);
		    	 asw+=numSyl;
	    	//Token token = new Token(str,TranslationAlgorithm.NONE, "tmp");
	    	String[][] tmp= POSTagsOfToken(str,postags);
	    	String[][] arr = new String[2][];
	    	arr[0]=tmp[0];
  	     		asl= tokenNumber;
   	     		if(asl>1){asl--;}
   	     		asl=asl-kommacount;
   	     		asw=asw/asl;
   	     		ms=ms/asl*100;
   	     		es=es/asl*100;
   	     		iw=iw/asl*100;
   	     		String tmp1=FRE()+"";// FRE häufig 7
   	     		String[] tmp2= new String[1];
   	     		tmp2[0]=tmp1;
   	     	arr[1]=tmp2;
//    			tokenizedSentence[i].updateSentenceInfortmation(asl, asw, ms, es, iw);
	   
	    	TokenMap tokenMap= new TokenMap(str, arr, sentenceID+"__"+pIDG.generateID(),saveFlag); 
	    	tokenMaps.add(tokenMap);
	    	postags=tmp[1];
	    	//tokenizedSentence[i]=tokenMap;
	    	


	    }
	   
	    tokenMaps.parallelStream().forEach(entry->entry.setText());
	    tokenizedSentence=tokenMaps.toArray(new TokenMap[0]);
	     asl= tokenizedSentence.length;
	     if(asl>1){asl--;}
	     asl=asl-kommacount;
	     asw=asw/asl;
	     ms=ms/asl*100;
	     es=es/asl*100;
	     iw=iw/asl*100;
	}
	  
	/**
	 * 
	 * @param token
	 * @param arr1
	 * @return
	 */
	private String[][] POSTagsOfToken(String token, String[] arr1)
	{
		
		
		String[][] erg=new String[2][];

		String posTagsToToken=arr1[0];

		String[] postagsNew=new String[arr1.length-1];
		for(int j=1;j<arr1.length;j++)
		{
			postagsNew[j-1]=arr1[j];
		}
		
		String[] array=new String[1];
		array[0]=posTagsToToken;
		erg[0]=array;
		erg[1]=postagsNew;
		//System.out.println(posTagsToToken+" : "+token);
		return erg;
	}
	
	/**
	 * Tokenizes a sentence as String by using the java string tokenizer.
	 * @param sentence The sentence you want to tokenize.
	 * @return The tokenzied sentence as string array.
	 */
	public static String[] tokenizeSimple(String sentence)
	{
		StringTokenizer st = new StringTokenizer(sentence);
		String[] tokenizedSentence= new String[st.countTokens()];
		int i=0;
		while (st.hasMoreTokens()) 
		{
			String str=st.nextToken();
			tokenizedSentence[i]=str;
		    i++;
		} 
		return tokenizedSentence;     
	}
	
	/**
	 * Returns the tokenizedSentence as a TokenMap array.
	 * @return The tokenizedSentence as a TokenMap array.
	 */
	public TokenMap[] getTokenizedSentence()
	{
		return tokenizedSentence;
	}

	
	/**
	 * Returns the size of the sentence. The size is defined here as the number of tokens.
	 * @return The Number of Token.
	 */
	public int size()
	{
		return tokenizedSentence.length;
	}
	
	/**
	 * Returns the sentence as string.
	 * @return the sentence.
	 */
	public String toString()
	{
		return sentence;
	}
	
	
	
	
	//Flesh Reading ease
	private double FRE()
	{
		double t=0;
				
		t=180-asl-(58.5*asw);
		if(t<0){t=-1;}
		if(t>0&&t<=30){t=0;}
		if(t>30&&t<=50){t=1;}
		if(t>50&&t<=60){t=2;}
		if(t>60&&t<=70){t=3;}
		if(t>70&&t<=80){t=4;}
		if(t>80&&t<=90){t=5;}
		if(t>90&&t<=100){t=6;}
		if(t>100){t=7;}
		return t;
	}
	
//	//Wiener-Sachtextformel
//	 double WSF1()
//	{
//		double t=0;
//		
//		
//		t=0.1935*ms+0.1672*asl+0.1297*iw-0.0327*es-0.875;
//		return Math.round(t);
//	}
//	
//	double WSF2()
//	{
//		double t=0;
//		
//		t=0.2007*ms+0.1682*asl+0.1373*iw-2.779;
//		return Math.round(t);
//	}
//	double WSF3()
//	{
//		double t=0;
//		
//		t=0.2963*ms+0.1905*asl-1.1144;
//		return Math.round(t);
//	}
//	double WSF4()
//	{
//		double t=0;
//		t=0.2744*ms+0.2656*asl-1.693;
//		return Math.round(t);
//	}
//	
}
