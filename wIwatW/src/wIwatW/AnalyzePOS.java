package wIwatW;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

public class AnalyzePOS {

	public static double[] POSOccurency(String phrase)	{
		double[] erg = new double[7];
		String[] postags=POSTags.givePostags(phrase);
		int verbCount=0;
		int adjCount=0;
		int nounCount=0;
		
		for(int i=0;i<postags.length;i++)
		{
			
			
			if(postags[i].equalsIgnoreCase("NE")||postags[i].equalsIgnoreCase("NN")||postags[i].equalsIgnoreCase("NN\\*"))
			{//System.out.println("Klammer: N"+meta.getToken(i, j, k, temp+n, m));
				postags[i]="N";
				nounCount++;
			}
			if(postags[i].equalsIgnoreCase("VVFIN")||postags[i].equalsIgnoreCase("VVINF")||postags[i].equalsIgnoreCase("VVIMP")||
					postags[i].equalsIgnoreCase("VVIZU")||postags[i].equalsIgnoreCase("VVPP")||
					postags[i].equalsIgnoreCase("VAFIN")||postags[i].equalsIgnoreCase("VAIMP")||
					postags[i].equalsIgnoreCase("VAINF")||postags[i].equalsIgnoreCase("VAPP")||
					postags[i].equalsIgnoreCase("VMFIN")||postags[i].equalsIgnoreCase("VMINF")||
					postags[i].equalsIgnoreCase("VMPP"))
			{//System.out.println("Klammer: V"+meta.getToken(i, j, k, temp+n, m));
				postags[i]="V";
				verbCount++;
			}
			if(postags[i].equalsIgnoreCase("ADJA")||postags[i].equalsIgnoreCase("ADJD")||postags[i].equalsIgnoreCase("ADJA*")||postags[i].equalsIgnoreCase("ADJD*"))
			{//System.out.println("Klammer: A"+meta.getToken(i, j, k, temp+n, m));
				postags[i]="A";
				adjCount++;
			}
			
			
		}
		int restCount=postags.length-nounCount-verbCount-adjCount;
		int ignoringAllOthers=nounCount+verbCount+adjCount;
		System.out.println(postags.length+"  "+nounCount);
		erg[0]=(double)nounCount/postags.length*100;
		erg[1]=(double)verbCount/postags.length*100;
		erg[2]=(double)adjCount/postags.length*100;
		erg[3]=(double)restCount/postags.length*100;
		erg[4]=(double)nounCount/ignoringAllOthers*100;
		erg[5]=(double)verbCount/ignoringAllOthers*100;
		erg[6]=(double)adjCount/ignoringAllOthers*100;
		
		
		return erg;
	}
    public static double round(final double value, final int frac) {
        return Math.round(Math.pow(10.0, frac) * value) / Math.pow(10.0, frac);
    }
    
	public static void main(String[] args) {
		
TextImporter importer = new TextImporter();
		
//		System.out.println("0"+test.getParent());
ArrayList<String> stories = new ArrayList<String>();

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
			double[] occ=POSOccurency(text);
			
			System.out.println(text.substring(0, 10)+" Nomenanteil: "+round(occ[0],2)+" Reduziert: "+round(occ[4],2));
			System.out.println(text.substring(0, 10)+" Verbanteil: "+round(occ[1],2)+" Reduziert: "+round(occ[5],2));
			System.out.println(text.substring(0, 10)+" Adjektivanteil: "+round(occ[2],2)+" Reduziert: "+round(occ[6],2));
			System.out.println(text.substring(0, 10)+" Restanteil: "+round(occ[3],2));
			
		}
		
		double[] occ=POSOccurency("");
		
		
		System.out.println("Nomenanteil: "+round(occ[0],2)+" Reduziert: "+round(occ[4],2));
		System.out.println("Verbanteil: "+round(occ[1],2)+" Reduziert: "+round(occ[5],2));
		System.out.println("Adjektivanteil: "+round(occ[2],2)+" Reduziert: "+round(occ[6],2));
		System.out.println("Restanteil: "+round(occ[3],2));
		
	}

}
