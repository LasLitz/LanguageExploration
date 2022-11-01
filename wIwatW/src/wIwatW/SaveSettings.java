package wIwatW;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
/**
 * GUI: representation of used options, all attributes are public
 * @author Lasse
 *
 */
public class SaveSettings {
      String[][] postProcessingNumbersMemory = new String[2][2];
      String[] darstellungNumbersMemory = new String[2];
      boolean[][] postProcessingBooleanMemoryForLists = new boolean[2][15];
      boolean[] postProcessingBooleanMemory = new boolean[7];
      String[][] featureNumbersMemory = new String[2][TranslationAlgorithm.values().length];
      boolean[][] featureBooleanMemory = new boolean[2][TranslationAlgorithm.values().length];
      String[] pathMemory = new String[3];
      ArrayList<String> chosenCorporaNames = new  ArrayList<String>();
      ArrayList<String> chosenCorporaNames2 = new  ArrayList<String>();
      /**
       * constructs standard settings
       */
      public SaveSettings()
      {
    	  darstellungNumbersMemory[0]="1";
    	  darstellungNumbersMemory[1]="1";
    	  postProcessingNumbersMemory[0][0]="0";
    	  postProcessingNumbersMemory[0][1]="10";
    	  postProcessingNumbersMemory[1][0]="0";
    	  postProcessingNumbersMemory[1][1]="10";
	        for(int i=1;i<postProcessingBooleanMemoryForLists[0].length;i++)
	        {
	        	postProcessingBooleanMemoryForLists[0][i]=false;
	        	postProcessingBooleanMemoryForLists[1][i]=false;
	        }
	        postProcessingBooleanMemoryForLists[0][0]=true;
	        postProcessingBooleanMemoryForLists[1][0]=true;
	      	postProcessingBooleanMemoryForLists[0][2]=true;
	      	postProcessingBooleanMemoryForLists[1][2]=true;
      	postProcessingBooleanMemoryForLists[0][5]=true;
      	postProcessingBooleanMemoryForLists[1][5]=true;
      	
	        for(int i=1;i<postProcessingBooleanMemory.length;i++)
	        {
	        	postProcessingBooleanMemory[i]=false;
	        }
	        postProcessingBooleanMemory[0]=true;
	        
	        for(int i=0; i<featureNumbersMemory[0].length; i++)
	        {
	        	featureNumbersMemory[0][i]="1";
	        	featureNumbersMemory[1][i]="1";
	        }
	        for(int i=0; i<featureBooleanMemory[0].length; i++)
	        {
	        	featureBooleanMemory[0][i]=false;
	        	featureBooleanMemory[1][i]=false;
	        }
	        featureBooleanMemory[0][0]=true;
	        featureBooleanMemory[1][0]=true;
			String path = wIwatWMain.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			String decodedPath="";
			try {
				decodedPath = URLDecoder.decode(path, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
			File test= new File(decodedPath);
//			System.out.println("0"+test.getParent());
			decodedPath=test.getParent();
	        pathMemory[0]=decodedPath+File.separator+"res";
//	        System.out.println("1"+pathMemory[0]);
	        File res = new File(pathMemory[0]);
	
					//res.mkdir();
			       res.mkdir();
			
	        
	        pathMemory[1]=decodedPath+File.separator+"texte";
	        File texte = new File(pathMemory[1]);
	   
					texte.mkdir();
					
			File file1 =  new File(pathMemory[1]+File.separator+"Testfiles1");
			File file2 =  new File(pathMemory[1]+File.separator+"ReferenceCorpus");
			file1.mkdir();
			file2.mkdir();
			   
	
	        
	        pathMemory[2]="wiwatWmeta1.ser";
	        chosenCorporaNames.add(file1.getName());
	        chosenCorporaNames2.add(file2.getName());
      }
}
