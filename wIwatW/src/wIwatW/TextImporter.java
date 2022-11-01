package wIwatW;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.exceptions.ODFNotOfficeXmlFileException;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.jopendocument.dom.text.*;


/**
 * This class imports a story by a given file name.
 * @author Lasse
 *
 */

public class TextImporter 
{
	public static final String UTF8_BOM = "\uFEFF";
	 private ArrayList<String> folderNames= new ArrayList<String>(); 
	/**
	 * Imports a file as a string.
	 * @param path The filepath.
	 * @return The file content as string.
	 * @throws IOException
	 */
	public  String importFileAsString (String path) throws IOException
	{
		String str="";
	
		   

		         


	//
//				BufferedReader br = new BufferedReader(
//				   new InputStreamReader(
//		                      new FileInputStream(files.get(i)), "UTF8")
//				   );
			
				String zeile = "";
				if(path.endsWith(".doc")||path.endsWith(".docx"))
				{
					return docPathToString(path);
				    
				}
			   	
				if(path.endsWith(".pdf"))
				{
					return pdfPathToString(path);
					
				}
				
				if(path.endsWith(".odt"))
				{
					return odtPathToString(path);
					
				}
				FileReader fr = new FileReader(path);
				BufferedReader br = new BufferedReader(fr);
				if(path.endsWith(".txt"))
				{
				while( (zeile = br.readLine()) != null )
				{	
					
					String temp="";
					
					temp=zeile.replace(UTF8_BOM, "").replaceFirst("\\d*", "");
				//System.out.println(temp+"\n"+zeile);
					str+=" "+temp;
				}
				}
				  
				br.close();
				fr.close();
				
	 
		return str+".";
	 }
	
	
	public  String importFileAsStringNewLine (String path) throws IOException
	{
		String str="";
	
		   

		         


	//
//				BufferedReader br = new BufferedReader(
//				   new InputStreamReader(
//		                      new FileInputStream(files.get(i)), "UTF8")
//				   );
			
				String zeile = "";
				if(path.endsWith(".doc")||path.endsWith(".docx"))
				{
					return docPathToString(path);
				    
				}
			   	
				if(path.endsWith(".pdf"))
				{
					return pdfPathToString(path);
					
				}
				
				if(path.endsWith(".odt"))
				{
					return odtPathToString(path);
					
				}
				FileReader fr = new FileReader(path);
				
				//BufferedReader br = new BufferedReader(fr);
				BufferedReader br = new BufferedReader(
						   new InputStreamReader(
				                      new FileInputStream(path), "UTF8")
						   );
				boolean firstLine=true;
				if(path.endsWith(".txt"))
				{
				while( (zeile = br.readLine()) != null )
				{	
					
					String temp="";
					
					temp=zeile.replace(UTF8_BOM, "").replaceFirst("\\d*", "");
				//System.out.println(temp+"\n"+zeile);
					if(firstLine)
					{
						str=temp;
					}
					else
					{
						str+=String.format("%n")+temp;
					}
					firstLine=false;
				}
				}
				  
				br.close();
				fr.close();
				
	 
		return str;
	 }
	
/**
 * Returns a folders name by its index
 * @param index
 * @return the folders name
 */
	public String getFolderName(int index)
	{
		return folderNames.get(index);
	}
	/**
	 * Imports all .doc, .pdf, .odt, .txt of a given Folder and of its subfolders.
	 * @param path The folders path.
	 * @return a list of all files. Each entry is the whole file content
	 * @throws IOException
	 */
	public ArrayList<String> importFolder (String path) throws IOException
	{		
	   
		ArrayList<String> list= new ArrayList<String>();
		ArrayList<String> files = startFolderSearch(new File(path));
	         
		for(int i=0; i<files.size();i++)
		{
			FileReader fr = new FileReader(files.get(i));
			//BufferedReader br = new BufferedReader(fr);
			

			BufferedReader br = new BufferedReader(
			   new InputStreamReader(
	                      new FileInputStream(files.get(i)), "UTF8")
			   );
			String str="";
			String zeile = "";
			if(files.get(i).endsWith(".doc")||files.get(i).endsWith(".docx"))
			{
				list.add(docPathToString(files.get(i))+".");
				
			    continue;
			}
		   	
			if(files.get(i).endsWith(".pdf"))
			{
				list.add(pdfPathToString(files.get(i))+".");
				//System.out.println(files.get(i)+".");
				continue;
			}
			
			if(files.get(i).endsWith(".odt"))
			{
				list.add(odtPathToString(files.get(i))+".");
				continue;
			}
			
			if(files.get(i).endsWith(".txt"))
			{
			while( (zeile = br.readLine()) != null )
			{	
				
				String temp=zeile.replace(UTF8_BOM, "").replaceFirst("\\d*", "");
			//System.out.println(temp+"\n"+zeile);
				str+=" "+temp;
			}
			}
			  
			br.close();
			fr.close();
			list.add(str+".");
		}	      
		return list;
	 } 
	
	/**
	 * Rekursive folder and file search.
	 * @param dir the file
	 * @return a list with filepaths
	 */
	private  ArrayList<String> startFolderSearch(File dir)
	{
		
		ArrayList<String> list = new ArrayList<String>();
		
		if(dir.exists()&&dir.isDirectory())
    	{
    		//System.out.println(dir.getParent());
			String[] dirlist=dir.list();
    		for(int i=0;i<dirlist.length;i++)
    		{
    			//System.out.println("D: "+dirlist[i]);
    			File file= new File(dirlist[i]);
    			//Rekursion falls Datei Ordner ist
    			
    			String temp=dir.getAbsolutePath()+File.separator+file;
    			File file2 = new File(temp);
    			
    			if(file2.isDirectory())
    			{
    				list.addAll(startFolderSearch(file2));
    			}
    			else
    			{
    				folderNames.add(file2.getParentFile().getName());
	    			//add if anforderungen an Storydatei erfüllt
	    			if(TextImporter.hasTextFormat(file.toString()))
	    			{
	    				System.out.println("Textdateipfad: "+dir.getAbsolutePath()+File.separator+dirlist[i]+" wird hinzugefügt");
	        			list.add(dir.getAbsolutePath()+File.separator+dirlist[i]);
	        			
	    			}
    			}
    			
    		}	
    	}
		
		
		else if(TextImporter.hasTextFormat(dir.toString()))
		{
			list.add(dir.getAbsolutePath());
		}
		
		
		return list;
	}
	
	/**
	 * Checks if the given file has the textformat(doc, odt, pdf, txt)
	 * @param filename the files name
	 * @return true if at least one of the specified formats is true
	 */
	private static boolean hasTextFormat(String filename)
	{
		//System.out.println("Name: "+filename);
		String[] array=filename.split("\\.");
		if((	array[array.length-1].equals("doc")||
				array[array.length-1].equals("docx")||
				array[array.length-1].equals("odt")||
				array[array.length-1].equals("pdf")||
				array[array.length-1].equals("txt"))
					
		)
		{
				
			return true;
		}
			
		return false;
	}
	
	/**
	 * Reads file content given by a path
	 * @param path
	 * @return
	 * @throws IOException
	 */
	private String docPathToString(String path) throws IOException
	{ //Pfad!!!!
		String str="";
		FileInputStream file = new FileInputStream(new File(path));
		WordExtractor wE=null;
		 

		try{
			wE = new WordExtractor(file);
			
			String[] arr=wE.getParagraphText();
			str=arr[0]+": ";
			str+=wE.getText().replaceFirst(arr[0], "");
			
		}
		catch(Exception e)
		{
			//e.printStackTrace();
			str=docxPathToString(path);
		}
		
	file.close();
	
	if(wE!=null)	wE.close();

		return str;  
	}
	
	
	private String docxPathToString(String path) throws IOException
	{
		String str="";
		FileInputStream file = new FileInputStream(new File(path));
		XWPFWordExtractor wEX =null;
		 

			try{
				 wEX =  new XWPFWordExtractor(new XWPFDocument(file));
					String text=wEX.getText();
					str=text;
					
				}
				 catch(ODFNotOfficeXmlFileException eO)
					{
					 //eO.printStackTrace();
					 str =odtPathToString(path);
					}
		
		
			file.close();
		if(wEX!=null)wEX.close();

		return str;  
	}
	
	/**
	 * Reads file content given by a path
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public String pdfPathToString(String path) throws IOException
	{
		String str="";
		PDDocument pd;
		try 
		{
			File input = new File(path);  
			pd = PDDocument.load(input);
			//PDDocumentInformation inf= pd.getDocumentInformation();
			PDFTextStripper pdfs = new PDFTextStripper();
			str=pdfs.getText(pd);
			str=str.replaceFirst(pdfs.getLineSeparator(), ":");
			//System.out.println("--"+pdfs.g+"--");
			pd.close();
		 } catch (Exception e)
		{
			 e.printStackTrace();
		}
		return str;  
	}
		
	/**
	 * Reads file content given by a path
	 * @param path
	 * @return
	 */
	public String odtPathToString(String path) 
	{
		String str="";
		String tmp="";
		File file = new File(path);
		TextDocument text;
		try 
		{
			text=TextDocument.createFromFile(file);
			tmp=text.getParagraph(0).getCharacterContent(true);
			str=text.getCharacterContent(true).replaceFirst(tmp, "");
			str=tmp+" : "+str;
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		//System.out.println(str);
		return str;  
	}
		
}
