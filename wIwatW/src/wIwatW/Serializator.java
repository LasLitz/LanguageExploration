package wIwatW;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
//import java.util.Map;
//
//import javolution.util.FastList;
//
//import org.mapdb.DB;
//import org.mapdb.DBMaker;


/**
 * This class is for saving and loading your metacorpus.
 * @author Lasse
 *
 */
public class Serializator {
	/**
	 * Saves the given meta corpus.
	 * @param metaCorp given metacorpus
	 * @param path savepath
	 */
	public static synchronized void saveMetaCorpus(MetaCorpus metaCorp,String path)
	{
		//6884s mti FST
		metaCorp.setMap(TokenObserver.getMap());
		FileOutputStream fos = null;
		//ObjectOutputStream oos = null;
		OutputStream bufferedOut = null; 
		ObjectOutputStream oos = null;
	
		try {
		  fos = new FileOutputStream(path);
		  bufferedOut = new BufferedOutputStream(fos); 
		  oos = new ObjectOutputStream(bufferedOut);
		  //oos = new FSTObjectOutput(bufferedOut);
		  //metaCorp.writeObject(oos);
		  oos.writeObject(metaCorp);
//		  Map<String, FastList<FastList<String>>> map= TokenObserver.getMap();
//		  Set<String> keySet =map.keySet();
//		  oos.writeInt(keySet.size());
//		  for(Object key:keySet){
//			  oos.writeObject(key);
//			  oos.writeObject(map.get(key));
//			}
		  oos.reset(); 
		  oos.close();
		  fos.close();
		}
		catch (IOException e) {
		  e.printStackTrace();
		}


		
	}
//	public static synchronized void saveMetaCorpus(MetaCorpus metaCorp,String path)
//	{
//		SerializerPojo p = new SerializerPojo(null);
//		
//		metaCorp.setMap(TokenObserver.getMap());
//		FSTObjectOutput oos = null;
//		FileOutputStream fos = null;
//	
//		try {
//		  fos = new FileOutputStream(path);
//		  oos = new FSTObjectOutput(fos);
//		  oos.writeObject(metaCorp);
//		  
//		}
//		catch (IOException e) {
//		  e.printStackTrace();
//		}
//		  catch (ConcurrentModificationException e) {
//			  e.printStackTrace();
////				try {
////					
////					Thread.sleep(Math.round(Math.random()*100));
////				} catch (InterruptedException e2) {
////					e2.printStackTrace();
////				}
//			  saveMetaCorpus( metaCorp, path);
//		}
//		finally {
//		  if (oos != null) try { oos.close(); } catch (IOException e) {}
//		  if (fos != null) try { fos.close(); } catch (IOException e) {}
//		}
//		
//	}
	
//	public static void saveCorpusInFile(Corpus corpus, String path)
//	{
//		
//		//corpus.setMap(TokenObserver.getMap());Corpus-Einträge müssen Map hinzugefügt werden, aber noch nicht gelöst!
//		FSTObjectOutput oos = null;
//		FileOutputStream fos = null;
//		try {
//		  fos = new FileOutputStream(path);
//		  oos = new FSTObjectOutput(fos);
//		  oos.writeObject(corpus);
//		}
//		catch (IOException e) {
//		  e.printStackTrace();
//		}
//		finally {
//		  if (oos != null) try { oos.close(); } catch (IOException e) {}
//		  if (fos != null) try { fos.close(); } catch (IOException e) {}
//		}
//	}
	
	/**
	 * Loads the given meta corpus.
	 * @param path The path.
	 * @return The metaCorpus.
	 */
	public static MetaCorpus loadMetaCorpus(String path) 
	{
		MetaCorpus meta= new MetaCorpus();
//		DB db = DBMaker.newMemoryDirectDB()
//				
//		.closeOnJvmShutdown()
//		.transactionDisable()
//		.make();
//		 Map<String,FastList<FastList<String>>> map = db.getHashMap("testmap");
		//ObjectInputStream ois = null;
		ObjectInputStream ois = null;
				FileInputStream fis = null;
				InputStream bufferedIn =null;
				try {
				  fis = new FileInputStream(path);
				  bufferedIn=new BufferedInputStream(fis);
				  //ois = new ObjectInputStream(bufferedIn);
				  ois = new ObjectInputStream(bufferedIn);
				  //System.out.println("1");
				  Object obj = ois.readObject();
//				  int size= ois.readInt();
//				  for(int i=0;i<size;i++)
//				  {
//					  Object obj2 =  ois.readObject();
//					  Object obj3 =  ois.readObject();
//					  if (obj2 instanceof String) {
//						  String key = (String)obj2;
//						  if (obj3 instanceof FastList<?>)
//						  {
//							  FastList<FastList<String>> list = (FastList<FastList<String>>) obj3;
//							  map.put(key, list);
//						  }
//						
//						
//					  }	
//				  }
				  
				  
				  //ois.reset();
				  ois.close();
				  fis.close();
				  //System.out.println("2");
				  if (obj instanceof MetaCorpus) {
					  MetaCorpus metaCorp = (MetaCorpus)obj;
					  
					meta=metaCorp;  
					TokenObserver.setMap(meta.getMap());
					//System.out.println(meta.getSentenceNumber());
					//TokenObserver.setMap(map);
				  }	
				}
				catch (FileNotFoundException e) {
					  //e.printStackTrace();
					
					System.out.println("MetaCorpus-Datei nicht gefunden, deshalb wird ein neues MetaCorpus erzeugt ");
					
					}
				catch (Exception e) {
					e.printStackTrace();
				}
					
					finally {
					  if (ois != null) try { ois.close(); } catch (IOException e) {}
					  if (fis != null) try { fis.close(); } catch (IOException e) {}
					  
					}
	
//				for(int i=0;i<meta.getCorpusNumber();i++)
//				{
//					System.out.println(meta.getCorpusByIndex(i).name);
//					for(int j=0;j<meta.getCorpusByIndex(j).size();j++)
//					{
//						System.out.println(meta.getCorpusByIndex(i).getStoryByIndex(0).identifier());
//					}
//				}
		return meta;
	}
	
//	public static MetaCorpus loadMetaCorpus(String path)
//	{
//		MetaCorpus meta= new MetaCorpus();
//
//		FSTObjectInput  ois = null;
//				FileInputStream fis = null;
//				try {
//				  fis = new FileInputStream(path);
//				  ois = new FSTObjectInput (fis);
//				  //System.out.println("1");
//				  Object obj = ois.readObject();
//				  //System.out.println("2");
//				  if (obj instanceof MetaCorpus) {
//					  MetaCorpus metaCorp = (MetaCorpus)obj;
//					  
//					meta=metaCorp;  
//					TokenObserver.setMap(meta.getMap());
//				  }	
//				}
//				catch (Exception e) {
//					  //e.printStackTrace();
//					System.out.println("MetaCorpus laden fehlgeschlagen, neues MetaCorpus wird erzeugt");
//					e.printStackTrace();
//					}
//					
//					finally {
//					  if (ois != null) try { ois.close(); } catch (IOException e) {}
//					  if (fis != null) try { fis.close(); } catch (IOException e) {}
//					  
//					}
////				for(int i=0;i<meta.getCorpusNumber();i++)
////				{
////					System.out.println(meta.getCorpusByIndex(i).name);
////					for(int j=0;j<meta.getCorpusByIndex(j).size();j++)
////					{
////						System.out.println(meta.getCorpusByIndex(i).getStoryByIndex(0).identifier());
////					}
////				}
//		return meta;
//	}
	
//	public static Corpus loadCorpusFromFile(String path)
//	{
//		Corpus corpus= null;
//
//				FSTObjectInput ois = null;
//				FileInputStream fis = null;
//				try {
//				  fis = new FileInputStream(path);
//				  ois = new FSTObjectInput(fis);
//				  //System.out.println("1");
//				  Object obj = ois.readObject();
//				  //System.out.println("2");
//				  if (obj instanceof Corpus) {
//					  Corpus corpu = (Corpus)obj;
//					  //corpus= new Corpus(id, name);
//					  corpus=corpu;  
//					//TokenObserver.setMap(meta.getMap());//setze Map---->Lösung
//				  }	
//				}
//				catch (IOException e) {
//					  e.printStackTrace();
//					}
//					catch (ClassNotFoundException e) {
//					  e.printStackTrace();
//					}
//					finally {
//					  if (ois != null) try { ois.close(); } catch (IOException e) {}
//					  if (fis != null) try { fis.close(); } catch (IOException e) {}
//					}
//		return corpus;
//	}

}
