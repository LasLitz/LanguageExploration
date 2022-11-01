package wIwatW;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class provides some preproccessing methods, to edit strings. They are not associative.
 * @author Lasse
 *
 */

public class PreprocessingMethods {
	
	/**
	 * Deletes all signs that look like minus (-,_,–).
	 * @param text The input string.
	 * @return The output string.
	 */
	public static String deleteMinus(String text)
	{
		Matcher matcher = Pattern.compile("-|_|–" ).matcher( text );
//		StringBuffer sb = new StringBuffer();
//		while ( matcher.find())
//		  matcher.appendReplacement( sb, "");
//		matcher.appendTail( sb );
//		//System.out.println( sb );
//		text=sb.toString();
		text=matcher.replaceAll("");
		
		return text;
	}
	
	/**
	 * Reduces all kind of whitespace
	 * @param text
	 * @return the manipulated input without whitespace
	 */
	public static String deleteWhitespace(String text)
	{
		Matcher matcher = Pattern.compile("\\s" ).matcher( text );
//		StringBuffer sb = new StringBuffer();
//		while ( matcher.find())
//			//System.out.println(matcher.group(0));
//		  matcher.appendReplacement( sb, "" ); //Leerzeilen
//		matcher.appendTail( sb );
//		//System.out.println( sb );
//		text=sb.toString();
		text=matcher.replaceAll("");		return text;
	}
	
	/**
	 * Adds space for and after each punctuation sign.
	 * @param text The input string.
	 * @return The output string.
	 */
	public static String makeSpace(String text)
	{
		
		//X(?!Y)
		Matcher matcher = Pattern.compile("\\p{Punct}+" ).matcher( text );
		StringBuffer sb = new StringBuffer();
		while ( matcher.find())
		{
			//System.out.println(matcher.group(0));
			  matcher.appendReplacement( sb, " "+Matcher.quoteReplacement(matcher.group(0)) +" " ); //Leerzeilen
		}
	
		matcher.appendTail( sb );
		//System.out.println( sb );
		text=sb.toString();
		
		return text;
	}
	
	/**
	 * Deletes all kinds of quotation marks.
	 * @param text The input string.
	 * @return The output string.
	 */
	public static String deleteExtraSigns(String text)
	{
		
		//X(?!Y)
		Matcher matcher = Pattern.compile("[«»%&@„“*+/\"')(]" ).matcher( text );
//		StringBuffer sb = new StringBuffer();
//		while ( matcher.find())
//			//System.out.println(matcher.group(0));
//		  matcher.appendReplacement( sb, "" ); //Leerzeilen
//		matcher.appendTail( sb );
//		//System.out.println( sb );
//		text=sb.toString();
		text=matcher.replaceAll("");
		return text;
	}
	
	/**
	 * no function.
	 * @param text
	 * @return
	 */
//	public static String deleteEverythingButNumbersLatinsAndSentenceMarks(String text)
//	{
//		Matcher matcher = Pattern.compile("[^qwertzuiopüaßsdf\\sghjklööäyxcvbnmQWERTZUIOPÜASDFGHJKLÖÄYXCVBNM1234567890:.,;?!()']" ).matcher( text );
//		StringBuffer sb = new StringBuffer();
//		while ( matcher.find())
//			//System.out.println(matcher.group(0));
//		  matcher.appendReplacement( sb, "" ); //Leerzeilen
//		matcher.appendTail( sb );
//		//System.out.println( sb );
//		text=sb.toString();
//		
//		return text;
//	}
	/**
	 * Deletes all Points when they are followed by a number
	 * @param text inputtext
	 * @return the manipulated input
	 */
	public static String deletePointsAndNumbersAfterNumbers(String text)
	{
		Matcher matcher = Pattern.compile("[\\d+][\\p{Punct}]" ).matcher( text );
		

			
			text=matcher.replaceAll("");
		return text;
	}
	/**
	 * deletes all ponits between two numbers
	 * @param text input
	 * @return manipulated input
	 */
	public static String deletePointsBetweenNumbers(String text)
	{
		Matcher matcher = Pattern.compile("[\\d+][\\p{Punct}]+[\\d+]" ).matcher( text );
		StringBuffer sb = new StringBuffer();
		while ( matcher.find())
		{
			Matcher matcher2 = Pattern.compile("\\p{Punct}+" ).matcher( matcher.group(0) );
			String neu=matcher2.replaceAll("");
		
			
			//text=matcher.replaceAll("neu")
		  matcher.appendReplacement( sb, neu); //Leerzeilen
		 
		
		
			
		}
		matcher.appendTail( sb );
		text=sb.toString();
		return text;
	}
	
	/**
	 * deletes all numbers
	 * @param text input
	 * @return output
	 */
	public static String deleteNumbers(String text)
	{

		Matcher matcher = Pattern.compile("\\d" ).matcher( text );

		text=matcher.replaceAll("");
		return text;
	}
	/**
	 * Reduces multiple white space to one single gap.
	 * @param text The input string.
	 * @return The output string.
	 */
	public static String reduceSpace(String text)
	{
		Matcher matcher = Pattern.compile("\\s+" ).matcher( text );
//		StringBuffer sb = new StringBuffer();
//		while ( matcher.find())
//			//System.out.println(matcher.group(0));
//		  matcher.appendReplacement( sb, " ");
//		matcher.appendTail( sb );
//		//System.out.println( sb );
//		text=sb.toString();
		text=matcher.replaceAll(" ");
		
		return text;
	}
	
	/**
	 * Reduces multiple punctuation signs to the first of this punctuation signs.
	 * @param text The input string.
	 * @return The output string.
	 */
	public static String reduceMultipleSignsToOne(String text)
	{	//reduziert zu Punkt
//		Matcher matcher = Pattern.compile("\\p{Punct}+" ).matcher( text );
//		StringBuffer sb = new StringBuffer();
//		//System.out.println(text);
//		while ( matcher.find())
//			//
//		{
//			matcher.appendReplacement( sb, "."/*matcher.group(0).substring(0, 1)*/);
//	}
//		matcher.appendTail( sb );
//		//System.out.println( sb );
//		text=sb.toString();
//		
		return text.replaceAll("[\\.]\\p{Punct}+" , ".");
	}
	
	/**
	 * Adds after each end of sentence sign an additional EOS-sequence(%§%EOS%§%).
	 * @param text The input string.
	 * @return The output string.
	 */
	public static String addEndOfSentence(String text)
	{ //[“!?:\\.](?![“,])
		Matcher matcher = Pattern.compile("[!\\?:\\.]" ).matcher( text );
		StringBuffer sb = new StringBuffer();
		while ( matcher.find())
			//System.out.println(matcher.group(0));
		  matcher.appendReplacement( sb, " "+matcher.group(0)+"%§%EOS%§%");
		matcher.appendTail( sb );
		//System.out.println( sb );
		text=sb.toString();
		
		return text;
	}
	
	public static String addEndOfSentenceBAready(String text)
	{ //[“!?:\\.](?![“,])
		Matcher matcher = Pattern.compile("[!\\?:\\.]" ).matcher( text );
		StringBuffer sb = new StringBuffer();
		while ( matcher.find())
			//System.out.println(matcher.group(0));
		  matcher.appendReplacement( sb, " "+matcher.group(0)+"%§%EOS%§%");
		matcher.appendTail( sb );
		//System.out.println( sb );
		text=sb.toString();
		
		return text;
	}
	/**
	 * Replaces all German extra letters to their Latin correspondent.
	 * @param text The input string.
	 * @return The output string.
	 */
	public static String reduceVocals(String text)
	{
		
		text= text.replaceAll("ä", "ae");
		text= text.replaceAll("ö", "oe");
		text= text.replaceAll("ü", "ue");
		text= text.replaceAll("ß", "ss");
		text= text.replaceAll("Ä", "Ae");
		text= text.replaceAll("Ö", "Oe");
		text= text.replaceAll("Ü", "Ue");
		
		text= text.replaceAll("à", "a");
		text= text.replaceAll("À", "A");
		text= text.replaceAll("Á", "A");
		text= text.replaceAll("á", "a");
		text= toFamiliarLetters(text);
		return text;
	}
	
	/**
	 * Reduces characters to easier, more common characters like á to a
	 * @param text input
	 * @return output
	 */
	public static String toFamiliarLetters(String text)
	{
	      
     
        String erg="";
        for(char c : text.toCharArray())
        {
        	if(Character.isLetter(c))
        	{
        		String name = Character.getName(c);
                erg+=konvertExtraSignsToLatin(name);
        	}
        	else{
        		erg+=c;
        	}
        	
        }
        
        return erg;
	}
	
	
	private static char konvertExtraSignsToLatin(String name)
	{
		
		 boolean small = name.matches(".*SMALL.LETTER.*");
	        String base = name.replaceFirst(".*LETTER ([A-Z]).*", "$1");
	        if(small)
	        {
	            base = base.toLowerCase();
	        }
	        return base.charAt(0);
	}
	/**
	 * Replaces all Sentence Marks to "." except commata.
	 * @param text The input string.
	 * @return The output string.
	 */
	public static String reduceSentenceMarks(String text)
	{
		
		text= text.replaceAll("!", ".");
		text= text.replaceAll("\\?", ".");
		text= text.replaceAll(":", ".");
		text= text.replaceAll(";", ".");
		text= text.replaceAll(",", "");

		return text;
	}
	/**
	 * Each lower Case letter, which is directly followed by an upper Case letter is interpreted as the break from the title of a story
	 * to its storytext. Words that match these regular expression are cutted. A colon is printed after the headline.
	 * @param text The input string.
	 * @return The output string.
	 */
	public static String gapToHeadline(String text)
	{
				Matcher matcher = Pattern.compile( "\\p{Lower}\\p{Upper}" ).matcher( text );
				while ( matcher.find() )
				{	
		
			char eins=text.charAt(matcher.start());
			char zwei=text.charAt(matcher.end()-1);
			/*System.out.println(eins+"!"+zwei);
			System.out.println(matcher.start()+"!"+matcher.end());*/
			text= text.replaceFirst("\\p{Lower}\\p{Upper}", eins+": "+zwei );
		}
		
		return text;
	}
	
	/**
	 * Cuts of weird stuff.
	 * @param text The input string.
	 * @return The output string.
	 */
	public static String reducePDF(String text)
	{

		if(text.contains("%PDF"))text=text.substring(0, text.indexOf("%PDF"));
		
		return text;
	}
	

}
