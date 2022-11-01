package wIwatW;


import java.util.ArrayList;

import javolution.util.FastList;
import de.uni_leipzig.wortschatz.webservice.client.baseform.BaseformClient;
import de.uni_leipzig.wortschatz.webservice.client.sachgebiet.SachgebietClient;
import de.uni_leipzig.wortschatz.webservice.client.thesaurus.ThesaurusClient;

/**
 * Uses different methods of the wortschatz projekt, needs internet connection
 * @author Lasse
 *
 */
public class Wortschatz {
	
	/**
	 * creates a random synonym of a word
	 * @param word inputword
	 * @return random synonym of inputword
	 */
	public static String getRandomSynonym(String word)
	{
		String erg="";
		try {
			word=getBaseform(word);
			ThesaurusClient s = new ThesaurusClient();
			s.setPassword("anonymous");
			s.setUsername("anonymous");
			s.addParameter("Wort", word);
			s.addParameter("Limit", "100");

			s.execute();
			
		
			if( s.isEmptyResult() )
			{
				
				erg=word;
            }
			else
			{
            	String[][] strResult = s.getResult();
            	int i=(int)(Math.random()*100)%(strResult.length);
               
                	erg=strResult[i][0];
                
            }
            
			//System.out.println(s.getResult()[0]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return erg;
	}
	
	/**
	 * Returns one baseform to a word.
	 * @param word input word
	 * @return the Baseform
	 */
	private static String getBaseform(String word)//65s
	{
		//falls public dann flag
		String erg="";
		try {
			BaseformClient s = new BaseformClient();
			s.setPassword("anonymous");
			s.setUsername("anonymous");
			s.addParameter("Wort", word);

			s.execute();
			
		
			if( s.isEmptyResult() )
			{
                erg=word;
            }
			else 
			{
            	String[][] strResult = s.getResult();
            	
            	if(strResult.length==1)
            	{
                	erg=strResult[0][0];
            	}
            	else{
            		int mem=-1;
            		int memIndex=-1;
            		for(int i=0;i<strResult.length;i++)
            		{	int length=strResult[i][0].length();
            			if(length>=mem)
            			{
            				mem=length;
            				memIndex=i;
            			}
            		}
            		erg=strResult[memIndex][0];
            	}
            }
			//System.out.println(word+" "+erg);
			if(erg.equalsIgnoreCase(word)) return erg;
			
			
				//erg=getBaseform(erg);rekursion
		
				
			//System.out.println(s.getResult()[0]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return erg;
	}
	
	
	/**
	 * Returns all computed baseforms to a word
	 * @param word the input word
	 * @return all baseforms as lust
	 */
	public static FastList<String> getBaseforms(String word, boolean databaseQueryFlag)
	{
		final String translationCode="bf";
		FastList<String> erglist= new FastList<String>();
		FastList<String> tmplist=TokenObserver.getTranslationsToWord(word,translationCode);//TokenObserver.getMultipleCodesOfString(translationCode+word);
		//System.out.println("alt: "+tmplist);
	//	System.out.println("Flag: "+databaseQueryFlag);
	
		if((tmplist==null||tmplist.isEmpty()))
		{
			if(databaseQueryFlag)
			{
			try {
				BaseformClient s = new BaseformClient();
				s.setPassword("anonymous");
				s.setUsername("anonymous");
				s.addParameter("Wort", word);
	
				s.execute();
				
	
	          	if(erglist.contains(translationCode+word)==false) 
	        	{
	          		erglist.add(translationCode+word);
	          		TokenObserver.addMultipleCodesandTranslationsToWord(translationCode+word, word);
	          	//	ArrayList<String> tmplist1=TokenObserver.getTranslationsToWord(word,translationCode);
	          		//System.out.println("------------>"+tmplist1);
	        	}
	       
		         	String[][] strResult = s.getResult();
	            	for( int i = 0; i < strResult.length; i++ )
	                {
	                	//MultipleCodeObserver.observe("BF"+strResult[i][0], word);
//	            		TokenObserver.observe("BF"+strResult[i][0], word, 1);
//	            		TokenObserver.observe(word, "BF"+strResult[i][0], 2);
	            		TokenObserver.addMultipleCodesandTranslationsToWord(translationCode+strResult[i][0], word);
	            		String tmp=translationCode+strResult[i][0];
	                	if(erglist.contains(tmp)==false) 
	                	{
	                		erglist.add(tmp);
	                	}
	                		
	                }
	            
				//System.out.println(s.getResult()[0]);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
//				
				sleep();
				erglist.add(translationCode+word);
				//erglist.addAll(getBaseforms(word, databaseQueryFlag)); Rekursion zum neuen Aufruf
				//erglist.add()
			}
//			catch(Exception e1)
//			{
//				e1.printStackTrace();
//				erglist.add(translationCode+word);
//			}
			}
			else
			{
				erglist.add(translationCode+word);
				//System.out.println("ieeks: "+word);
			}
		}
		else
		{	
			//tmplist=TokenObserver.getTranslationsToWord(word,translationCode);
			//System.out.println(word+erglist+tmplist);
			erglist=tmplist;
		}
		//System.out.println("getBaseforms: "+word+erglist+tmplist);
		return erglist;
	}
	
	/**
	 * returns all Synonyms of a word
	 * @param word the input word
	 * @return The list of synonyms
	 */
	public static FastList<String> getThesaurusSynonyms(String word,boolean databaseQueryFlag)
	{
		//falls public, dann flag einfügen
		final String translationCode="ts";
		FastList<String> erglist= new FastList<String>();
		FastList<String> tmplist=TokenObserver.getTranslationsToWord(word,translationCode);//TokenObserver.getMultipleCodesOfString(translationCode+word);
		//System.out.println(tmplist==null);
		if(tmplist==null||tmplist.isEmpty())
		{
			tmplist=TokenObserver.getTranslationsToWord(word,"ts");//TokenObserver.getMultipleCodesOfString(translationCode+word);
			if(databaseQueryFlag)
			{			
				ThesaurusClient s;
					try {
						s = new ThesaurusClient();
			
					s.setPassword("anonymous");
					s.setUsername("anonymous");
					s.addParameter("Limit", "50");
				       String t=word.substring(0, 1).toUpperCase();
			            String wordU=t+word.substring(1, word.length());
			            t=word.substring(0, 1).toLowerCase();
			            String wordL=t+word.substring(1, word.length());
					String[] upperLowerCase={wordL, wordU};
					for(int j=0;j<upperLowerCase.length;j++)//parallelisieren
					{
						s.addParameter("Wort", upperLowerCase[j]);
						s.addParameter("Limit", "50");
						try {
							s.execute();
						} catch (Exception e) {	}
			
								
				         	String[][] strResult = s.getResult();
				         	//if(word.equals("Trauerspiel")) System.out.println("jetzt!!!!!----------------<------------------"+strResult.length);
			            	//System.out.println("-->"+word+" "+strResult.length);
				         	if(strResult==null)
				         	{
				         		strResult= new String[0][0];
				         	}
				         	for( int i = 0; i < strResult.length; i++ )
			                {
			                	//MultipleCodeObserver.observe("BF"+strResult[i][0], word);
			            		//TokenObserver.observe(translationCode+strResult[i][0], word, 1);
			            		
			            		String tmp=translationCode+strResult[i][0];
			                	if(erglist.contains(tmp)==false) 
			                	{
			                		erglist.add(tmp);
			                		TokenObserver.addMultipleCodesandTranslationsToWord(tmp.toLowerCase(), word);
			                	}
			                		
			                }					
					}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						erglist.add(translationCode+word);
						sleep();
						//erglist.addAll(getThesaurusSynonyms(word,databaseQueryFlag)); rekursion
					}
//					catch(Exception e)
//					{
//						e.printStackTrace();
//						erglist.add(translationCode+word);
//					}
			}
			else
			{
				erglist.add(translationCode+word);
				//System.out.println("ieeks: "+word);
			}

		}
		else
		{
			for(int i=0;i<tmplist.size();i++)
			{
				erglist.add(tmplist.get(i));
			}
			
		}
		if(erglist.isEmpty())
		{
			erglist.add(translationCode+word);
			TokenObserver.addMultipleCodesandTranslationsToWord(translationCode+word.toLowerCase(), word);
			
		}
		//System.out.println("-------------------------------->"+erglist);
		return erglist;
	}
	
	/**
	 * Returns all synonyms of a word and concatinates it with postag
	 * @param word inputword
	 * @param tag postag of the input word
	 * @return a list in which each entry is a synonym concatenated with a postag
	 */
	public static FastList<String> getThesaurusSynonyms(String word, String tag,boolean databaseQueryFlag)
	{
		final String translationCode="tp";
		FastList<String> erglist= new FastList<String>();
		FastList<String> tmplist=TokenObserver.getTranslationsToWord(word,translationCode);//TokenObserver.getMultipleCodesOfString(translationCode+word);
		if(tmplist==null||tmplist.isEmpty())
		{
			tmplist=TokenObserver.getTranslationsToWord(word,"ts");//TokenObserver.getMultipleCodesOfString(translationCode+word);
			if(tmplist==null||tmplist.isEmpty())
			{
				if(databaseQueryFlag)
				{
				//System.out.println("------------------------------------------->Wortschatz: thesaurusepostags meldet, dass wörter von thesaurus nicht gefunden");
				ThesaurusClient s;
					try {
						s = new ThesaurusClient();
			
					s.setPassword("anonymous");
					s.setUsername("anonymous");
					s.addParameter("Limit", "50");
				       String t=word.substring(0, 1).toUpperCase();
			            String wordU=t+word.substring(1, word.length());
			            t=word.substring(0, 1).toLowerCase();
			            String wordL=t+word.substring(1, word.length());
					String[] upperLowerCase={wordL, wordU};
					for(int j=0;j<upperLowerCase.length;j++)
					{
						String[][] strResult = s.getResult();
						s.addParameter("Wort", upperLowerCase[j]);
						try {
							s.execute();
							strResult = s.getResult();
						} catch (Exception e) {	strResult = new String[0][0];}
						
								
				         	
			            	for( int i = 0; i < strResult.length; i++ )
			                {
			                	//MultipleCodeObserver.observe("BF"+strResult[i][0], word);
			            		//TokenObserver.observe(translationCode+strResult[i][0], word, 1);
			            		
			            		String tmp=translationCode+strResult[i][0]+tag;
			                	if(erglist.contains(tmp)==false) 
			                	{
			                		erglist.add(tmp);
			                		TokenObserver.addMultipleCodesandTranslationsToWord(tmp.toLowerCase(), word);
			   //---->eigentlich praktisch             		//TokenObserver.addMultipleCodesandTranslationsToWord("ts"+strResult[i][0], word);
			                	}
			                		
			                }					
					}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
					e1.printStackTrace();
					erglist.add(translationCode+word);
						sleep();
						//erglist.addAll(getThesaurusSynonyms(word,tag, databaseQueryFlag));
					}
					}
//					catch(Exception e)
//					{
//						e.printStackTrace();
//						erglist.add(translationCode+word);
//					}
					else
					{
						erglist.add(translationCode+word);
						//System.out.println("ieeks: "+word);
					}
			}
			else{
				for(int i=0;i<tmplist.size();i++)
				{
					String add=translationCode+tmplist.get(i).substring(2, tmplist.get(i).length())+tag;
					//System.out.println("----------------------------------------------------------------->"+add);
					erglist.add(add);
					TokenObserver.addMultipleCodesandTranslationsToWord(add.toLowerCase(), word);
				}
			}
		}
		else
		{
			for(int i=0;i<tmplist.size();i++)
			{
				erglist.add(tmplist.get(i));
			}
			
		}
		if(erglist.isEmpty())
		{
			erglist.add(translationCode+word);
			TokenObserver.addMultipleCodesandTranslationsToWord(translationCode+word.toLowerCase(), word);
			
		}
		return erglist;
	}
	
	/**
	 * Returns all topics of a word
	 * @param word input word
	 * @return list with topics
	 */
	public static FastList<String> getSachgebiete(String word, boolean databaseQueryFlag)
	{
		final String translationCode="gs";
		FastList<String> erglist= new FastList<String>();
		sleep();
		FastList<String> tmplist=TokenObserver.getTranslationsToWord(word,translationCode);//TokenObserver.getMultipleCodesOfString(translationCode+word);
	
		if(tmplist==null||tmplist.isEmpty())
		{
			if(databaseQueryFlag)
			{
			
					SachgebietClient s;
					try {
						s = new SachgebietClient();
			
					s.setPassword("anonymous");
					s.setUsername("anonymous");
					ArrayList<String> baseformList=new ArrayList<String>();
					sleep();
					FastList<String> tmp1=TokenObserver.getTranslationsToWord(word,"bf");
					FastList<String> tmp2=TokenObserver.getTranslationsToWord(word,"ts");
					if(tmp1==null||tmp1.isEmpty())
					{
						//System.out.println("!!!!");
						tmp1= new FastList<String>();
						tmp1=getBaseforms(word,databaseQueryFlag);
						if(tmp1==null||tmp1.isEmpty())
						{
							tmp1= new FastList<String>();
							tmp1.add(word);
						}
					}
					baseformList.addAll(tmp1);
					
					if(tmp2==null||tmp2.isEmpty())
					{
						//System.out.println("!????!!");
						tmp2= new FastList<String>();
						tmp2=getThesaurusSynonyms(word,databaseQueryFlag);
						if(tmp2==null||tmp2.isEmpty())
						{
							tmp2= new FastList<String>();
							tmp2.add(word);
						}
					}
				
				//baseformList.addAll(tmp2);
					
					//baseformList.add(word);
					
					ArrayList<String> upperLowerCaseList= new ArrayList<String>();
					for(int i=0;i<baseformList.size();i++)
					{
						String baseform=baseformList.get(i);
						//System.out.println(".................. "+baseform.substring(0, 1));
						if(baseform.length()>2)
						{
							baseform=baseform.substring(2, baseform.length());
							if(baseform.length()>0)
							{
						String t=baseform.substring(0, 1).toUpperCase();//---
			            String wordU=t+baseform.substring(1, baseform.length());
			            t=word.substring(0, 1).toLowerCase();
			            String wordL=t+baseform.substring(1, baseform.length());
			            
			            upperLowerCaseList.add( wordU);
			            upperLowerCaseList.add( wordL);
							}
						}
					}
//				       String t=word.substring(0, 1).toUpperCase();
//			            String wordU=t+word.substring(1, word.length());
//			            t=word.substring(0, 1).toLowerCase();
//			            String wordL=t+word.substring(1, word.length());
//					String[] upperLowerCase={wordL, wordU};
					
					upperLowerCaseList.parallelStream().forEach(entry->
					{
						s.addParameter("Wort", entry);
						try {
							s.execute();
						} catch (Exception e) {	}
			
								
				         	String[][] strResult = s.getResult();
			            	for( int i = 0; i < strResult.length; i++ )
			                {
			                	//MultipleCodeObserver.observe("BF"+strResult[i][0], word);
			            		//TokenObserver.observe(translationCode+strResult[i][0], word, 1);
			            		
			            		String tmp=translationCode+strResult[i][0];
			                	if(erglist.contains(tmp)==false&&tmp.equalsIgnoreCase(translationCode+"nachname")==false&&tmp.equalsIgnoreCase(translationCode+"Vorname")==false) 
			                	{
			                		erglist.add(tmp);
			                		TokenObserver.addMultipleCodesandTranslationsToWord(tmp.toLowerCase(), word);
			                	}
			                		
			                }		
					});
					

					} catch (Exception e1) {
						// TODO Auto-generated catch block
					e1.printStackTrace();
					erglist.add(translationCode+word);
						sleep();
						//erglist.addAll(getSachgebiete(word, databaseQueryFlag));rekursion
					}
//					catch(Exception e)
//					{
//						e.printStackTrace();
//						erglist.add(translationCode+word);
//					}
					
			}
			else
			{
				erglist.add(translationCode+word);
				//System.out.println("ieeks: "+word);
			}
		}
		else
		{
			
			for(int i=0;i<tmplist.size();i++)
			{
				erglist.add(tmplist.get(i));
				//TokenObserver.addMultipleCodesandTranslationsToWord(translationCode+word.toLowerCase(), word);
			}
			
		}
		if(erglist.isEmpty())
		{
			erglist.add(translationCode+word);
			TokenObserver.addMultipleCodesandTranslationsToWord(translationCode+word.toLowerCase(), word);
			
		}
//		erglist.add("gs"+word);
//		TokenObserver.addMultipleCodesandTranslationsToWord("gs"+word.toLowerCase(), word);
		return erglist;
	}
	

	/**
	 * Returns all topics of a word and concatinates it with postag
	 	
	 * @param word input word
	 * @param tag postag of the word
	 *  @return list with topics
	 */
	public static ArrayList<String> getSachgebietePostags(String word, String tag, boolean databaseQueryFlag)
	{
//		//count1++;
		final String translationCode="sp";
		ArrayList<String> erglist= new ArrayList<String>();
		sleep();
		FastList<String> tmplist=TokenObserver.getTranslationsToWord(word,translationCode);//TokenObserver.getMultipleCodesOfString(translationCode+word);
		if(tmplist==null||tmplist.isEmpty())
		{
			sleep();
			tmplist=TokenObserver.getTranslationsToWord(word,"gs");//TokenObserver.getMultipleCodesOfString(translationCode+word);
			//System.out.println(tmplist.size());
			if(tmplist==null||tmplist.isEmpty())
			{
				//System.out.println("Wortschatz: sachgebietepostags meldet, dass wörter von sachgebiete nicht gefunden");
				if(databaseQueryFlag)
				{
					SachgebietClient s;
					try {
						s = new SachgebietClient();
			
					s.setPassword("anonymous");
					s.setUsername("anonymous");
					ArrayList<String> baseformList=new ArrayList<String>();
					sleep();
					FastList<String> tmp1=TokenObserver.getTranslationsToWord(word,"bf");
					FastList<String> tmp2=TokenObserver.getTranslationsToWord(word,"ts");
					if(tmp1==null||tmp1.isEmpty())
					{
						tmp1= new FastList<String>();
						tmp1=getBaseforms(word, databaseQueryFlag);
						if(tmp1==null||tmp1.isEmpty())
						{
							tmp1= new FastList<String>();
							tmp1.add(word);
						}
					}
					baseformList.addAll(tmp1);
					
					if(tmp2==null||tmp2.isEmpty())
					{
						tmp2= new FastList<String>();
						tmp2=getThesaurusSynonyms(word,databaseQueryFlag);
						if(tmp2==null||tmp2.isEmpty())
						{
							tmp2= new FastList<String>();
							tmp2.add(word);
						}
					}
					
					//baseformList.addAll(tmp2);
					
					//baseformList.add(word);
					ArrayList<String> upperLowerCaseList= new ArrayList<String>();
					for(int i=0;i<baseformList.size();i++)
					{
						String baseform=baseformList.get(i);
						
						if(baseform.length()>2)
						{
							baseform=baseform.substring(2, baseform.length());
							if(baseform.length()>0)
							{
						String t=baseform.substring(0, 1).toUpperCase();//---
			            String wordU=t+baseform.substring(1, baseform.length());
			            t=word.substring(0, 1).toLowerCase();
			            String wordL=t+baseform.substring(1, baseform.length());
			            
			            upperLowerCaseList.add( wordU);
			            upperLowerCaseList.add( wordL);
							}
						}
					}
//				       String t=word.substring(0, 1).toUpperCase();
//			            String wordU=t+word.substring(1, word.length());
//			            t=word.substring(0, 1).toLowerCase();
//			            String wordL=t+word.substring(1, word.length());
//					String[] upperLowerCase={wordL, wordU};
					//for(int j=0;j<upperLowerCaseList.size();j++)
						upperLowerCaseList.parallelStream().forEach(string->
						{
							s.addParameter("Wort", string);
							try {
								s.execute();
							} catch (Exception e) {	}
				
									
					         	String[][] strResult = s.getResult();
				            	for( int i = 0; i < strResult.length; i++ )
				                {
				                	//MultipleCodeObserver.observe("BF"+strResult[i][0], word);
				            		//TokenObserver.observe(translationCode+strResult[i][0], word, 1);
				            		
				            		String tmp=translationCode+strResult[i][0]+tag;
				                	if(erglist.contains(tmp)==false&&tmp.equalsIgnoreCase(translationCode+"nachname")==false&&tmp.equalsIgnoreCase(translationCode+"Vorname")==false) 
				                	{
				                		erglist.add(tmp);
				                		TokenObserver.addMultipleCodesandTranslationsToWord(tmp.toLowerCase(), word);

				                		TokenObserver.addMultipleCodesandTranslationsToWord("gs"+strResult[i][0], word);
				                	}
				                		
				                }					
						}
								);
			
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						erglist.add(translationCode+word);
						sleep();
						//erglist.addAll(getSachgebietePostags(word,tag, databaseQueryFlag));
					}
//					catch(Exception e)
//					{
//						e.printStackTrace();
//						erglist.add(translationCode+word);
//					}
				}
				else
				{
					erglist.add(translationCode+word);
					//System.out.println("ieeks: "+word);
				}
			}
			else{
				for(int i=0;i<tmplist.size();i++)
				{
					
					String add=translationCode+tmplist.get(i).substring(2, tmplist.get(i).length())+tag;
					erglist.add(add);
					TokenObserver.addMultipleCodesandTranslationsToWord(add.toLowerCase(), word);
				}
			}
		}
		else
		{
			for(int i=0;i<tmplist.size();i++)
			{
				erglist.add(tmplist.get(i));
			}
			
		}
		if(erglist.isEmpty())
		{
			erglist.add(translationCode+word);
			TokenObserver.addMultipleCodesandTranslationsToWord(translationCode+word.toLowerCase(), word);
			
		}
		return erglist;
	}
	
	private static void sleep()
	{
		try {
			
			Thread.sleep(50);
			//System.out.println("sleep");
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}
	
}
