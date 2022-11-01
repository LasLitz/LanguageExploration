package wIwatW;

import java.io.Serializable;

/**
 * This class represents a single token. The token has information about its translationcodesname.
 * @author Lasse
 *
 */
/*
 * sollte funktionieren
 */
public class Token implements Serializable, Comparable<Token>{
/**
	 * 
	 */
	private static final long serialVersionUID = -4875519654751468284L;
	private String string="";
	private TranslationAlgorithm tA;
	private String tokenID="";
	private int relativeID=0;
	/**
	 * Constructs a token by a given string and given translationAlgorithm.
	 * @param string The tokens content.
	 * @param tA The name of the tokens translationcode.
	 * @param id The ID of the tokenMap.
	 * @param saveflag if false, this is not written into the TokenObserver
	 */
	public Token(String string, TranslationAlgorithm tA, String id,boolean saveFlag )
	{
		this.string=string;
		this.tA=tA;
		this.setTokenID(id);
//		if(tA.equals(TranslationAlgorithm.NONE))
//		{
//			TokenObserver.observe(string, tokenID);
//		}
		if(saveFlag==true)
		{
			TokenObserver.observe(string, tokenID, 0);
		}
	}
	
	/**
	 * Constructs a token by an given id and translationalgorithm
	 * @param id the tokens id
	 * @param tA the tokens translationalgorithm
	 */
	public Token(String id, TranslationAlgorithm tA)
	{

		this.tA=tA;
		this.setTokenID(id);

	}
	
	/**
	 * adds to a constructed token its string content and saveflaf
	 * @param string text of the token
	 * @param saveFlag if false, the token will not stored in the Tokenobserver
	 */
	public void setConstructorDetails(String string, boolean saveFlag)
	{
		this.string=string;
//		if(tA.equals(TranslationAlgorithm.NONE))
//		{
//			TokenObserver.observe(string, tokenID);
//		}
		if(saveFlag==true)
		{
			TokenObserver.observe(string, tokenID, 0);
		}
	}
	/**
	 * Sets the tokens ID.
	 * @param id
	 */
	public void setTokenID(String id)
	{
		this.tokenID=id;
		String[] arr=tokenID.split("__");
		relativeID= Integer.parseInt(arr[arr.length-1]);
		//System.out.println("Token"+relativeID);
	}
	
	/**
	 * Gets the tokens ID.
	 * @return id
	 */
	public String getTokenID()
	{
		return tokenID;
	}
	
	/**
	 * Returns an relative id which is the last number of the position id
	 * @return the last number of position id
	 */
	public int getRelativeTokenID()
	{
		return relativeID;
	}
	/**
	 * Returns the string of the token.
	 * @return The tokens content.
	 */
	public String getStringContent()
	{
		return string;
	}
	
	/**
	 * Returns the translationcode of the token.
	 * @return The translationAlgorithm.
	 */
	public TranslationAlgorithm getTranslationAlgorithm()
	{
		return tA;
	}
	
	/**
	 * compares two tokens with each other. 
	 * @return 0 if identical, -1 if id of referenced token is bigger, 1 if the  id is smaller
	 * 
	 * 	
	 */
	public int compareTo(Token o) {
		 if (o.getRelativeTokenID() == this.getRelativeTokenID()) 
		 {
		      return 0;
		 }
		 if (o.getRelativeTokenID() <  this.getRelativeTokenID()) 
		 {
		      return 1;
		 }
		 if (this.getRelativeTokenID() <  o.getRelativeTokenID()) 
		 {
		      return -1;
		 }
		    return 0;
	}
}
