package wIwatW;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class AnalyzeWordUsage {
	public static void writeFile(String path, String word)
	{
		//path=fc.getSelectedFile().getAbsolutePath()+".txt";
        
        try {
       
        		String newText="";
        		TextImporter t= new TextImporter();
            	newText=t.importFileAsStringNewLine(path);
			
            	if(newText.equals(""))newText=word;
            	else  	newText+=String.format("%n")+word;
//        	FileWriter f = new FileWriter (path);
//			f.write(newText+String.format("%n")+word);
//			f.close();
//			
		 	Writer out = new BufferedWriter(new OutputStreamWriter(
        		    new FileOutputStream(path), "UTF-8"));
        		try {
        			
        			
        		    out.write(newText);
        		} finally {
        		    out.close();
        		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
}
