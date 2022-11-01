package wIwatW;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import org.jfree.chart.ChartFactory;


public class WordCount {

	
	public static FrequencyList compute()
	{
			ArrayList<String> stories = new ArrayList<String>();
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			//Texte auswählen
			TextImporter importer = new TextImporter();
			
//			System.out.println("0"+test.getParent());
			String path = WordCount.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			String decodedPath="";
			try {
				decodedPath = URLDecoder.decode(path, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			File test= new File(decodedPath);
			decodedPath=test.getParent();
			try {
				stories=importer.importFolder(decodedPath+File.separator+"texte");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			for(int i=0; i<stories.size();i++)
			{
			
			//preprocessing	
				String text=stories.get(i);
				text=PreprocessingMethods.reducePDF(text);
				text=PreprocessingMethods.gapToHeadline(text);
//				text=PreprocessingMethods.deletePointsAndNumbersAfterNumbers(text);
//				text=PreprocessingMethods.deletePointsBetweenNumbers(text);
//				text=PreprocessingMethods.deleteNumbers(text);
//				text=PreprocessingMethods.reduceVocals(text);
				
//				text=PreprocessingMethods.deleteExtraSigns(text);
				
				
				//text=PreprocessingMethods.reduceMultipleSignsToOne(PreprocessingMethods.deleteMinus(text));//bisher
				//text=PreprocessingMethods.deleteEverythingButNumbersLatinsAndSentenceMarks(text);
//				text=PreprocessingMethods.reduceSentenceMarks(text);
				text=PreprocessingMethods.reduceMultipleSignsToOne(PreprocessingMethods.deleteMinus(text));
				
				text=PreprocessingMethods.reduceSpace(PreprocessingMethods.makeSpace(text));
				//zählen
			StringTokenizer st = new StringTokenizer(text);
		

				 while (st.hasMoreTokens()) 
				    {
				    	String str=st.nextToken();
				    	 
				    	 
				    	  Integer value=map.get(str);
				    	  if(value==null)value=0;
				    	  value++;
					    map.put(str,value);
				    	
				
				    	


				    }
			}
			
			//ordnen
			Set<String> set= map.keySet();
		
			List<String> list = new ArrayList<String>();
			FrequencyList fl = new FrequencyList();
			list.addAll(set);
			for(int j=0;j<list.size();j++)
			{
				FrequencyListField flf = new FrequencyListField();
				flf.add(list.get(j));
				flf.setFrequency(map.get(list.get(j)));
				fl.add(flf);
			}
			fl.sortAfterFrequency();
			return fl;
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> stories = new ArrayList<String>();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		//Texte auswählen
		TextImporter importer = new TextImporter();
		
//		System.out.println("0"+test.getParent());
		String path = WordCount.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		String decodedPath="";
		try {
			decodedPath = URLDecoder.decode(path, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		File test= new File(decodedPath);
		decodedPath=test.getParent();
		try {
			stories=importer.importFolder(decodedPath+File.separator+"texte");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i=0; i<stories.size();i++)
		{
		
		//preprocessing	
			String text=stories.get(i);
			text=PreprocessingMethods.reducePDF(text);
			text=PreprocessingMethods.gapToHeadline(text);
//			text=PreprocessingMethods.deletePointsAndNumbersAfterNumbers(text);
//			text=PreprocessingMethods.deletePointsBetweenNumbers(text);
//			text=PreprocessingMethods.deleteNumbers(text);
//			text=PreprocessingMethods.reduceVocals(text);
			
//			text=PreprocessingMethods.deleteExtraSigns(text);
			
			
			//text=PreprocessingMethods.reduceMultipleSignsToOne(PreprocessingMethods.deleteMinus(text));//bisher
			//text=PreprocessingMethods.deleteEverythingButNumbersLatinsAndSentenceMarks(text);
//			text=PreprocessingMethods.reduceSentenceMarks(text);
			text=PreprocessingMethods.reduceMultipleSignsToOne(PreprocessingMethods.deleteMinus(text));
			
			text=PreprocessingMethods.reduceSpace(PreprocessingMethods.makeSpace(text));
			//zählen
		StringTokenizer st = new StringTokenizer(text);
	

			 while (st.hasMoreTokens()) 
			    {
			    	String str=st.nextToken();
			    	 
			    	 
			    	  Integer value=map.get(str);
			    	  if(value==null)value=0;
			    	  value++;
				    map.put(str,value);
			    	
			
			    	


			    }
		}
		
		//ordnen
		Set<String> set= map.keySet();
	
		List<String> list = new ArrayList<String>();
		FrequencyList fl = new FrequencyList();
		list.addAll(set);
		for(int j=0;j<list.size();j++)
		{
			FrequencyListField flf = new FrequencyListField();
			flf.add(list.get(j));
			flf.setFrequency(map.get(list.get(j)));
			fl.add(flf);
		}
		fl=fl.sortAfterFrequency().selectByFrequencyOver(2);
		
		//darstellen
		
		for(int k=0; k<fl.size();k++)
		{
			System.out.println(fl.get(k)+" |"+ fl.getFrequencyOfEntry(k)+"|");
		}
	}

}
