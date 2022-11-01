package wIwatW;

import java.io.File;
import java.util.ArrayList;

/**
 * not in use and not ready but maybe will help to build corpus manegment
 * @author Lasse
 *
 */
public class CorpusManager {
	//nie benutzt aber vielleicht noch nützlich
public MetaCorpus getData(String path)
{
	MetaCorpus meta=null;
	
	
	return meta;
}


/**
 * Imports all .doc, .pdf, .odt, .txt of a given Folder and of its subfolders.
 * @param path The folders path.
 * @return
 * @throws IOException
 */
//public MetaCorpus importFolder (String path) throws IOException
//{		
//   MetaCorpus metaCorp=null;
//	ArrayList<String> files = CorpusManager.startFolderSearch(new File(path));
//	ArrayList<String> metaCorpusList= new ArrayList<String>();
//	ArrayList<String> lonlyCorpusList= new ArrayList<String>();
////	ArrayList<MetaCorpus> metaCorpusList= new ArrayList<MetaCorpus>();
////	ArrayList<Corpus> lonlyCorpusList= new ArrayList<Corpus>();
//         
//	for(int i=0; i<files.size();i++)
//	{
//	   	
//		if(files.get(i).endsWith("meta.ser"))
//		{
//			metaCorpusList.add(files.get(i));
//			continue;
//		}
//		
//		if(files.get(i).endsWith("corpus.ser"))
//		{
//			lonlyCorpusList.add(files.get(i));
//			continue;
//		}
//		
//
//	}	  
//	
//	boolean metaCorpusConflict=false;
//	if(metaCorpusList.size()>1)
//	{
//		metaCorpusConflict=true;
//	}
//	if(metaCorpusList.size()==0)
//	{
//		metaCorp= new MetaCorpus();
//		if(lonlyCorpusList.size()==0)
//		{
//			String pathSave=".\\src\\res\\wiwatWMeta.ser";
//			ConsoleIllustrator.loadAndSaveFilledMetaCorp("path",  pathSave);
//		}
//	}
//	for(int i=0;i<metaCorpusList.size();i++)
//	{
//		if(metaCorpusConflict==true)
//		{
//			//Konflikt lösen
//		}
//		else{
//			metaCorp=Serializator.loadMetaCorpus(metaCorpusList.get(i));
//		}
//		
//	}
//	
//	for(int i=0;i<lonlyCorpusList.size();i++)
//	{
//		Corpus c=Serializator.loadCorpusFromFile(lonlyCorpusList.get(i));
//		metaCorp.addCorpus(c);
//			
//	}
//	
//	return metaCorp;
// } 

/**
 * Rekursive folder and file search.
 * @param dir
 * @return
 */
 static ArrayList<String> startFolderSearch(File dir)
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
			
			String temp=dir.getAbsolutePath()+"\\"+file;
			File file2 = new File(temp);
			if(file2.isDirectory())
			{
				list.addAll(CorpusManager.startFolderSearch(file2));
			}
			else
			{
    			//add if anforderungen an Storydatei erfüllt
    			if(CorpusManager.hasSerializationFormat(file.toString()))
    			{
        			list.add(dir.getAbsolutePath()+"\\"+dirlist[i]);
        			//System.out.println(dir.list()[i]);
    			}
			}
			
		}	
	}
	
	
	else if(CorpusManager.hasSerializationFormat(dir.toString()))
	{
		list.add(dir.getAbsolutePath());
	}
	
	
	return list;
}

/**
 * Checks if the given file has the textformat(doc, odt, pdf, txt)
 * @param filename
 * @return
 */
private static boolean hasSerializationFormat(String filename)
{
	//System.out.println("Name: "+filename);
	String[] array=filename.split("\\.");
	if((	array[array.length-1].equals("ser"))
				
	)
	{
			
		return true;
	}
		
	return false;
}
}
