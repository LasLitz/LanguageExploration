package wIwatW;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Properties;

import de.uni_leipzig.asv.toolbox.viterbitagger.Tagger;

/**
 * The class uses the viterbitagger to compute postags
 * @author Lasse
 *
 */
public class POSTags {

	/**
	 * Returns postags as string array.
	 * @param text
	 * @return string array
	 */
	public static String[] givePostags(String text)
	{
		
		ArrayList<String> alt= POSTags.POSTagOfSentence(text);
		String[]postags= new String[alt.size()];
	
		for(int i=0;i<alt.size();i++)
		{
			postags[i]=alt.get(i);
		}


		return postags;
	}
	
	/**
	 * Returns postags of a Text. It can also be more than one sentence, which reduces computation time
	 * @param sentence Input sentence or text
	 * @return ArrayList<String> which holds the postags.
	 */
	public static ArrayList<String> POSTagOfSentence(String sentence) 
	{

		String path = wIwatWMain.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		String decodedPath="";

		
//		System.out.println("0"+test.getParent());
		//C:\Users\Lasse\workspace\wIwatW\src\Ressourcen\taggermodels\deTaggerModel.model
		//InputStream input=ResLoader.load("taggermodels/deTaggerModel.model");
		//decodedPath=ResLoader.loadPath("taggermodels/deTaggerModel.model");
		//decodedPath=ResLoader.class.getResource("deTaggerModel.model").getPath();
		//decodedPath=POSTags.class.getResource("res\\deTaggerModel.model").getPath();
		
		//String tmFile = "C:/Users/Lasse/workspace/ASV_Toolbox_completev1.0l/toolbox/resources/taggermodels/deTaggerModel.model";
		//String tmFile = "C:/Users/Lasse/workspace/wIwatW/src/Ressourcen/taggermodels/deTaggerModel.model";
	
		try {
			decodedPath = URLDecoder.decode(path, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		File test= new File(decodedPath);
//		System.out.println("0"+test.getParent());
		decodedPath=test.getParent();				
		
		String tmFile = decodedPath+"/taggermodels/deTaggerModel.model";
		String tmp="";
		//System.out.println(tmFile);
		ArrayList<String> ergls = new ArrayList<String>();


        Properties props = new Properties();


        String tmDir = new File(tmFile).getParent();
//System.out.println("-->"+tmDir);//C:\Users\Lasse\workspace\wIwatW\bin\taggermodels
        try {

              props.load(new FileInputStream(tmFile));
        	//props.load(input);
//        	System.out.println(tmDir+"/"+("deTaggerModel.taglist"));
//        	System.out.println(tmDir+"/"+props.getProperty("taglist"));
//        	System.out.println(tmDir+"/"+("deTaggerModel.lexicon"));
//        	System.out.println(tmDir+"/"+props.getProperty("lexicon"));
//        	System.out.println(tmDir+"/"+("deTaggerModel.transitions"));
//        	System.out.println(tmDir+"/"+props.getProperty("transitions"));
//              Tagger tagger = new Tagger(tmDir+"/"+"deTaggerModel.taglist",
//
//                         tmDir+"/"+"deTaggerModel.lexicon",
//
//                         tmDir+"/"+"deTaggerModel.transitions",null, false);
            Tagger tagger = new Tagger(tmDir+"/"+props.getProperty("taglist"),

                    tmDir+"/"+props.getProperty("lexicon"),

                    tmDir+"/"+props.getProperty("transitions"),null, false);
              tagger.setExtern(false);

              tagger.setReplaceNumbers(props.getProperty("ReplaceNumbers").equals("true"));

              tagger.setUseInternalTok(true);
              tmp=tagger.tagSentence(sentence);
              String erg1[]=tmp.split("\\|| ");
              //System.out.println(sentence);
              for(int i=2; i<erg1.length;i++)
              {
            	  //System.out.println(erg1[i]);
    
            	  ergls.add(erg1[i]);
            	  i++;
            	  }
              
        } catch (FileNotFoundException e) {

              e.printStackTrace();

        } catch (IOException e) {

              e.printStackTrace();

        }
        
        return ergls;

	}
}
