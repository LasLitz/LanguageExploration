package wIwatW;

import java.io.Serializable;

/**
 * 
 * This class models a token map. A token map is a token and each translation of it, organized as list. A token by itself is a simple oneword string.
 * @author Lasse Kohlmeyer
 *
 */

public  class TokenMap implements Serializable
{
	private static final long serialVersionUID = 2288254519585729213L;
	private Token[] tokens= new Token[TranslationAlgorithm.length()];
	private String tokenMapID="";
	private String[][] postags;
	private boolean saveFlag=false;
	private String token;

	/**
	 * Creates an token map.
	 * @param token The nontranslated Base Token.
	 * @param postags Information from higher structures.
	 * @param id The ID given by the sentence.
	 * @param saveflag if false the map will not be written into the tokenoberserver
	 */
	public TokenMap(String token, String[][] postags, String id, boolean saveFlag)//token gewöhnlich, im satz
	{
		this.setTokenMapID(id);
		if(saveFlag==true)
		{
			System.out.println(token);
		}
			
		this.postags=postags;
		this.saveFlag=saveFlag;
		this.token=token;
		//addTokenMapEntry(token, postags, saveFlag);
	}
	
/**
 * sets the text / calls addtokenmapentry
 */
	public void setText()
	{
		//System.out.println(token);
		addTokenMapEntry(token, postags, saveFlag);
	}
	/**
	 * Sets the tokenmaps ID.
	 * @param id given id
	 */
	public void setTokenMapID(String id)
	{
		this.tokenMapID=id;
	}
	/**
	 * Gets the tokenmaps ID.
	 * @return the id
	 */
	public String getTokenMapID()
	{
		return tokenMapID;
	}

	/**
	 * Returns by a given index the entry to this index as string. That means, you can chose with the index the translation.
	 * @param index The translation you want to choose. i=0 means no translation.
	 * @return The translated token.
	 */
	public Token getEntry(int index)
	{
		return tokens[index];
	}
	
	/**
	 * Adds a new token with all translations.
	 * @param token The string token you want to add.
	 * @param postags the postags that are computed earlier
	 * @param saveFlag if false, this will not be written into to tokenobserver
	 * @return The updated token list.
	 */
	private void addTokenMapEntry(String token,String[][] postags,boolean saveFlag)
	{
		//System.out.println(token+" |   "+this.sentence.toString());
		Translator t= new Translator(token, postags,tokenMapID,saveFlag);
		//System.out.println(token.getStringContent());
		tokens=t.getTokens();
	}
	
	/**
	 * Returns the size of the token map.
	 * @return Number of translations.
	 */
	public int size()
	{
		return tokens.length;
	}
	

}
