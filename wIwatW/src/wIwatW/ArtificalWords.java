package wIwatW;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ArtificalWords {
public static Map<Double, String> map= new HashMap<Double, String>();

	public static String naiveGeneration() //weniger naiv wäre bedingte wahrscheinlichkeit mit trigrammen für jeden buchstaben und wahrscheinlichkeiten für wortlängen
	{
		String s="";
		double prop=Math.random();
		double intervall=0.174;
		if(prop<=intervall)return s="e";
		intervall+=0.0978;
		if(prop<=intervall)return s="n";
		intervall+=0.0755;
		if(prop<=intervall)return s="i";
		intervall+=0.0727;
		if(prop<=intervall)return s="s";
		intervall+=0.07;
		if(prop<=intervall)return s="r";
		intervall+=0.0651;
		if(prop<=intervall)return s="a";
		intervall+=0.0615;
		if(prop<=intervall)return s="t";
		intervall+=0.0508;
		if(prop<=intervall)return s="d";
		intervall+=0.0476;
		if(prop<=intervall)return s="h";
		intervall+=0.0435;
		if(prop<=intervall)return s="u";
		intervall+=0.0344;
		if(prop<=intervall)return s="l";
		intervall+=0.0306;
		if(prop<=intervall)return s="c";
		intervall+=0.0301;
		if(prop<=intervall)return s="g";
		intervall+=0.0253;
		if(prop<=intervall)return s="m";
		intervall+=0.0251;
		if(prop<=intervall)return s="o";
		intervall+=0.0189;
		if(prop<=intervall)return s="b";
		intervall+=0.0189;
		if(prop<=intervall)return s="w";
		intervall+=0.0166;
		if(prop<=intervall)return s="f";
		intervall+=0.0121;
		if(prop<=intervall)return s="k";
		intervall+=0.0113;
		if(prop<=intervall)return s="z";
		intervall+=0.0079;
		if(prop<=intervall)return s="p";
		intervall+=0.0067;
		if(prop<=intervall)return s="v";
		intervall+=0.0031;
		if(prop<=intervall)return s="ß";
		intervall+=0.0027;
		if(prop<=intervall)return s="j";
		intervall+=0.0004;
		if(prop<=intervall)return s="y";
		intervall+=0.0003;
		if(prop<=intervall)return s="x";
		intervall+=0.00002;
		if(prop<=intervall)return s="q";
		
		
		
		s=Math.random()+"";
		return s;
	}
	
	public static String callNaiveGenerationSomeRandomTimes()
	{
		String erg="";
		double prop=Math.random()*100%12+1;
		System.out.println(prop);
		for (int i=0;i<prop;i++)
		{
			erg+=naiveGeneration();
		}
		
		return erg;
	}
	
	public static void main(String[] args) throws IOException 
	{
		System.out.println(callNaiveGenerationSomeRandomTimes());
	}
}
