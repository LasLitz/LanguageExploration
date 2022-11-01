package wIwatW;




import java.util.ArrayList;
import java.util.Collections;

import org.apache.commons.codec.language.ColognePhonetic;


/**
 * This class creates a transcripted version of a string. The transcription is choosable. It is recommended to only transcript simple tokens.
 * @author Lasse
 *
 */
public class Translator {
//**erstellt ein kodiertes Abbild eines Textes, Kodierung einstellbar*/

private TranslationAlgorithm[] allTranslationAlgorithms= TranslationAlgorithm.class.getEnumConstants();
private int lengthTA =allTranslationAlgorithms.length;
private Token[] tokens=new Token[lengthTA];
private ArrayList<Token> tokenList = new ArrayList<Token>();
	/**
	 * Constructs a translator for a given string in every translation.
	 * @param text The string you want to translate.
	 * @param postags Some given information of higher structures like sentence, story information.
	 * @param tokenMapid The ID of the TokenMap.
	 * @param saveFlag if false, this will not be saved persistent
	 */
	public Translator(String text, String[][] postags, String tokenMapid,boolean saveFlag)
	{
		//alt
//		for(int i=0; i<lengthTA;i++)
//		{
//			Token token= new Token(translate(text,allTranslationAlgorithms[i],postags),allTranslationAlgorithms[i], tokenMapid+"__"+i,saveFlag);
//		  	tokens[i]=token;
//			
//		}

		for(int i=0; i<lengthTA;i++)
		{
			Token t=new Token(tokenMapid+"__"+i, allTranslationAlgorithms[i]);
			tokenList.add(t);
		}
//System.out.println(saveFlag);
		tokenList.parallelStream().forEach(t->parallelHelper( t,  text, postags,  tokenMapid, saveFlag));
		Collections.sort(tokenList);
		
		
		for(int i=0; i<tokenList.size();i++)
		{
//			System.out.println(tokenList.get(i).getRelativeTokenID()+" "+tokenList.get(i).getStringContent());
			tokens[i]=tokenList.get(i);
		}	
		
		
//		int i=0;
//		for(TranslationAlgorithm tA: allTranslationAlgorithms)
//		{
//			
//			Token token= new Token(translate(text,tA,postags),tA, tokenMapid+"__"+i,saveFlag);
//		  	tokens[i]=token;
//			i++;
//		}
				
	}
	
	private void parallelHelper(Token t, String text, String[][] postags, String tokenMapid,boolean saveFlag)
	{
		//System.out.println(t.getRelativeTokenID());
		
		TranslationAlgorithm tA=t.getTranslationAlgorithm();
		//System.out.println(tA);
		t.setConstructorDetails(translate(text,tA,postags, saveFlag), saveFlag);
	}
	
	/**
	 * Gives back each translation.
	 * @return Each translation as stringlist.
	 */
	public Token[] getTokens()
	{
		
		return this.tokens;
	}
	

	/**
	 * Translates a string by a given translationAlgorithm.
	 * @param text The input.
	 * @param translationAlgorithm The translation code.
	 * @param postags earlier computed postags, this reduces computation time
	 * @return The output.
	 */
	public String translate(String text, TranslationAlgorithm translationAlgorithm, String[][] postags, boolean databaseQueryFlag)
	{
		text=text.trim();
		boolean digitFlag=false;
		ArrayList<String> chiffre=new ArrayList<String>();
		String chif="";
		for(char c: text.toCharArray())
		{
			if(Character.isDigit(c)||c=='.')
			{
				digitFlag=true;
				//System.out.println("text");
				break;
			}
				
		}
		switch(translationAlgorithm) {
			case NONE:
				chiffre.add(text);
				break;
			
				
//			case SOUNDEX:
//				Soundex s = new Soundex("012301200224550126230102020002");
//				chiffre=s.soundex(text);
//				if(chiffre.equals(""))
//				{
//					chiffre=text;
//				}
//				chiffre="S"+chiffre;
//				break;
				
			case POSTAGS:
				if(digitFlag==false)	
				{
					chiffre.add("po"+postags[0][0]);
					TokenObserver.addMultipleCodesandTranslationsToWord("po"+postags[0][0], text);
				}
				else chiffre.add("po"+text);
				break;
				
			case SYLLABLES:
				if(digitFlag==false)	
					{
						chiffre.add("sl"+numberOfSyllables(text));
					
					}
				else chiffre.add("sl"+text);
				break;
				
		
//			case ONLYVOWELS:
//				chiffre=deleteAllConsonants(text);
//				if(chiffre.equals(""))
//				{
//					chiffre=text;
//				}
//				chiffre="OV"+chiffre;
//				break;
//				
//			case ONLYCONSONANTS:
//				chiffre=deleteAllVowels(text);
//				if(chiffre.equals(""))
//				{
//					chiffre=text;
//				}
//				chiffre="OC"+chiffre;
//				break;
				
			case COLOGNEPHONETIC:
				ColognePhonetic  cp = new ColognePhonetic ();
				if(digitFlag==false)	
				{
				chif=cp.colognePhonetic(text);
				if(chiffre.equals(""))
				{
					chif=text;
				}
				chiffre.add("co"+chif);
				}
				else chiffre.add("co"+text);
				//System.out.println(text+chiffre);
				break;
//			case POSTAGSFRE:
//				if(digitFlag==false)	chiffre.add("pf"+postags[1][0]+postags[0][0]);
//				else chiffre.add("pf"+text);
//				//System.out.println(chiffre);
//				break;
//				
//			case POSTAGSANDTEXT:
//				if(digitFlag=false)	chiffre.add("pt"+text+postags[0][0]);
//				else chiffre.add("pt"+text);
//				//System.out.println(chiffre);
//				break;
//				
//				case BASEFORMS:
//					if(digitFlag==false)	
//					{
//				//chiffre.addAll(Wortschatz.getBaseforms(text, databaseQueryFlag)); 
//				chiffre.add("bf"+text);
//					}
//					else chiffre.add("bf"+text);
////				if(chiffre.equals("BF"))
////				{
////					chiffre+=text;
////				}
//				//System.out.println(chiffre);
//				break;
//				case SACHGEBIETE:
//					if(digitFlag==false)	{
//				//chiffre.addAll(Wortschatz.getSachgebiete(text, databaseQueryFlag));
//				chiffre.add("gs"+text);
//					}else chiffre.add("gs"+text);
//				break;
//				
//			case THESAURUSSYNONYMS:
//				
//				if(digitFlag==false)	{
//					//chiffre.addAll(Wortschatz.getThesaurusSynonyms(text,databaseQueryFlag));
//					chiffre.add("ts"+text);
//				}
//				else {chiffre.add("ts"+text);}
//				
//				break;	
//			case CHARNUMBER:
//				if(digitFlag==false)	chiffre.add("cn"+numberOfCharacters(text));
//				else chiffre.add("cn"+text);
//				break;
//				
//			case SYLLABLESANDCHARNUMBER:
//				if(digitFlag==false)	chiffre.add("sc"+CharactersAndSyllables(text));
//				else chiffre.add("sc"+text);
//				break;
//				
//			case ONLYVOWELSANDONLYCONSONANTS:
//				if(digitFlag==false)	
//				{
//				chif=deleteAllConsonants(text)+deleteAllVowels(text);
//				if(chiffre.equals(""))
//				{
//					chif=text;
//				}
//				chif="vc"+chif;
//				chiffre.add(chif);
//				}
//				else chiffre.add("vc"+text);
//				break;	
////			case BASEFORMTHENSYNONYMS:
////				if(digitFlag==false)	
////				{
////				chiffre.addAll(Wortschatz.getSynonymeAfterBaseforms(text));
////				//chiffre.add("bs"+text);
////				}else chiffre.add("bs"+text);
////				break;	
//
//			case SACHGEBIETEANDPOSTAGS:
//				if(digitFlag==false)	{
//				//chiffre.addAll(Wortschatz.getSachgebietePostags(text, postags[0][0], databaseQueryFlag));
//				chiffre.add("sp"+text);
//				}else chiffre.add("sp"+text);
//				//System.out.println(chiffre);
//				break;
//		
//			case THESAURUSSYNONYMSANDPOSTAGS:
//				if(digitFlag==false)	
//				{
//				//chiffre.addAll(Wortschatz.getThesaurusSynonyms(text, postags[0][0], databaseQueryFlag));//muss wieder rein wenn es wieder geht
//				chiffre.add("tp"+text);
//				}
//				else chiffre.add("tp"+text);
//				//System.out.println(chiffre);
//				break;
			case COLOGNEPHONETICANDPOSTAGS:
				if(digitFlag==false)	
				{
				cp = new ColognePhonetic ();
				
				chif=cp.colognePhonetic(text);
				if(chiffre.equals(""))
				{
					chif=text;
				}
				
				chiffre.add("cp"+chif+postags[0][0]);
				}
				else chiffre.add("cp"+text);
				//System.out.println(chiffre);
				break;
//			case BASEFORMTHENSYNONYMSANDPOSTAGS:
//				if(digitFlag==false)	
//				{
//				chiffre.addAll(Wortschatz.getSynonymeAfterBaseforms(text, postags[0][0]));
//				//chiffre.add("bp"+text);
//				}
//				else chiffre.add("bp"+text);
//				//System.out.println(chiffre);
//				break;
				
		}
		chif="";
		for(int i=0; i<chiffre.size();i++)
		{
			String tmp=chiffre.get(i);
			//System.out.println("-------->"+tmp);
			TokenObserver.observe(tmp, text, 1);
			if(i<chiffre.size()-1)chif+=tmp+"&&";
			else chif+=tmp;
		}
		//System.out.println(chif);
		return chif;		
	}
	
	/**
	 * Returns the number of characters in a token
	 * @param token input word
	 * @return wordlength by characters as string
	 */
	private static String numberOfCharacters(String token) 
	{

		  
	    return ""+token.length();
	}
	
	/**
	 * Returns a concatenation of characterlength of a word and syllable length
	 * @param token
	 * @return
	 */
	private static String CharactersAndSyllables(String token) 
	{
	 
	    return 	"c"+numberOfCharacters(token)+"s"+numberOfSyllables(token); 
	}
	
	/**
	 * Returns the number of syllables in a token.
	 * @param token
	 * @return The syllables number.
	 */
	public static int numberOfSyllables(String token) 
	{
		//http://shiffman.net/, by daniel shiffmann
	    int      syl    = 0;
	    boolean  vowel  = false;
	    int      length = token.length();

	    //check each word for vowels (don't count more than one vowel in a row)
	    for(int i=0; i<length; i++) {
	      if        (isVowel(token.charAt(i)) && (vowel==false)) {
	        vowel = true;
	        syl++;
	      } else if (isVowel(token.charAt(i)) && (vowel==true)) {
	        vowel = true;
	      } else {
	        vowel = false;
	      }
	    }

	  
	    return syl;
	}
	
	/**
	 * Deletes all characters that are vowels
	 * @param token inputword
	 * @return inputword without vowels
	 */
	private static String deleteAllVowels(String token) 
	{
		
	    return token.replaceAll("[aeiuoyAEIUO]", "");
	}
	
	/**
	 * Deletes all characters that are consonants
	 * @param token inputword
	 * @return inputword without consonsants
	 */
	private static String deleteAllConsonants(String token) 
	{
		
	    return token.replaceAll("[^aeiuoyAEIUO]", "");
	}

	
	/**
	 * checks if a char is a vowel
	 * @param c inputchar
	 * @return true if vowel
	 */
	private static boolean isVowel(char c) 
	{
		if      ((c == 'a') || (c == 'A')) { return true;  }
	    else if ((c == 'e') || (c == 'E')) { return true;  }
	    else if ((c == 'i') || (c == 'I')) { return true;  }
	    else if ((c == 'o') || (c == 'O')) { return true;  }
	    else if ((c == 'u') || (c == 'U')) { return true;  }
	    else                               { return false; }
	  }
	


}