package wIwatW;

/**
 * This enum declares the Translation Algorithms you can use to code your tokens.
 * @author Lasse
 *
 */
public enum TranslationAlgorithm 
{	
	NONE,
	POSTAGS, SYLLABLES,
	/*CHARNUMBER, SYLLABLESANDCHARNUMBER,*/
	COLOGNEPHONETIC,/*POSTAGSFRE,*/POSTAGSANDTEXT, /*ONLYVOWELSANDONLYCONSONANTS,THESAURUSSYNONYMS, BASEFORMS, SACHGEBIETE,
	SACHGEBIETEANDPOSTAGS, THESAURUSSYNONYMSANDPOSTAGS,*/ COLOGNEPHONETICANDPOSTAGS;
	/**
	 * Returns the number of translationAlgorithms in this enum.
	 * @return The number of translationAlgorithms.
	 */
	public static int length()
	{
		return TranslationAlgorithm.class.getEnumConstants().length;
	}
}

