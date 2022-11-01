package wIwatW;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * splits filecontent and creates new files
 * @author Lasse
 *
 */
public class ReferenceCorpusSplitter {

	/**
	 * Splits an input file in 100 line files to make smaller files.
	 * @param path the files path
	 * @throws IOException
	 */
	public static  void split(String path) throws IOException
	{
		
		int size=100;
		File file = new File(path);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		ArrayList<String> erg= new ArrayList<String>();
		String str="", zeile = "";
		int count=0;
		while( (zeile = br.readLine()) != null )
		{	
			String temp="";
			temp=zeile.replaceFirst("\\d*", "");
			//System.out.println(temp+"\n"+zeile);
			str+=" "+temp+"\n";
			
			if(count==size-1)
			{
				erg.add(str);
				str="";
				count=-1;
			}
			count++;
		}
		
		fr.close();
		br.close();
		
		for(int i =0; i<erg.size();i++){
			 PrintWriter printWriter = null;
			 File datei= new File(file.getParent()+"/rc"+i+".txt");
		        
		            printWriter = new PrintWriter(new FileWriter(datei));
		            printWriter.println(erg.get(i));
		            
		            printWriter.close();
		
		}
	}
	
	public static  void splitPegida(String path) throws IOException
	{
		
		int size=10000;
		File file = new File(path);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		ArrayList<String> erg= new ArrayList<String>();
		String str="", zeile = "";
		int count=0;
		while( (zeile = br.readLine()) != null )
		{	
			
			String s = zeile;//"2013-12-17T19:38:55+0000";
			Matcher matcher = Pattern.compile( "201\\d-\\d\\d-\\d\\dT").matcher( s );
//			while ( matcher.find() )
//			  System.out.printf( "%s an Position [%d,%d]%n", matcher.group(), matcher.start(), matcher.end() );
	
	//System.out.println();
	
		//	Matcher matcher1 = Pattern.compile("[https?\\p{Punct}\\p{Punct}\\p{Punct}]?\\w+" ).matcher( text );
	
			
			
	
	
			
			if(zeile.equals("###############################################")==false&&matcher.find()==false)
			{
			String temp="";
			
			temp=zeile.replaceFirst("\\d*", "");
			
			String  text    = temp;

			String  regex   = "https?://[a-zA-Z0-9\\-\\.]+\\.[a-zA-Z]{2,3}(\\S*)?";
			Matcher matcher1 = Pattern.compile(  regex ).matcher( text );
			StringBuffer sb = new StringBuffer( text.length() );

			while ( matcher1.find() )
			  matcher1.appendReplacement( sb, "" );

			matcher1.appendTail( sb );

			//System.out.println( sb );
			temp=sb.toString();
//			text=temp; //"[^\\s+^\\p{Alpha}^\\d^\\p{Punct}^ö^ä^ü^Ö^Ä^Ü^ß]""[^\\p{Alnum}\\s\\p{Punct}\\p{L}]"
//			  regex   = "^[a-zA-ZöäüÖÄÜß]";
//			 matcher1 = Pattern.compile(  regex ).matcher( text );
//			 sb = new StringBuffer( text.length() );
//
//			while ( matcher1.find() )
//			  matcher1.appendReplacement( sb, "[PIEP]" );
//
//			matcher1.appendTail( sb );
//
//			//System.out.println( sb );
//			temp=sb.toString();
			
			//System.out.println(temp+"\n"+zeile);
			str+=" "+temp+"\n";
			
			if(count==size-1)
			{
				erg.add(str);
				str="";
				count=-1;
			}
			count++;
			}
		}
		
		fr.close();
		br.close();
		
		for(int i =0; i<erg.size();i++){
			 PrintWriter printWriter = null;
			 File datei= new File(file.getParent()+"/rcx"+i+".txt");
		        
		            printWriter = new PrintWriter(new FileWriter(datei));
		            printWriter.println(erg.get(i));
		            
		            printWriter.close();
		
		}
	}
	
//	201\d-\d\d-\d\dT
	//2014-12-17T19:38:55+0000
	
	public static void main(String[] args) throws IOException 
	{
		//ReferenceCorpusSplitter.split(".\\refcor\\sentence30k.txt");
		ReferenceCorpusSplitter.splitPegida(".\\refcor\\pegida_korpus.txt");
	}
	
}
