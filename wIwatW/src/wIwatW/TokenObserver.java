package wIwatW;


//import java.io.DataInput;
//import java.io.DataOutput;
//import java.io.File;
//import java.io.IOException;
//import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;








//import java.util.NavigableSet;
//
//import org.mapdb.BTreeKeySerializer;
//import org.mapdb.DB;
//import org.mapdb.DBMaker;
//import org.mapdb.Serializer;




import javolution.util.FastList;
import javolution.util.FastMap;




/**
 * This class is a registry for tokens and is called each time a token is constructed. 
 * It adds for each token all its positions given by the id the metacorpus. It is rather used for storing old Translations in the Hashmap
 * @author Lasse
 *
 */
public class TokenObserver /*implements Serializable, implements Serializer<Object>*/
{

	//private static final long serialVersionUID = -4710824656501022594L;
//	private static	DB db = DBMaker.newMemoryDirectDB()
	
//			//.newFileDB(new File("testdb"))
//			
//	.closeOnJvmShutdown()
//	.transactionDisable()
//	.make();
//	
//	private static  Map<String,FastList<FastList<String>>> map = db.getHashMap("testmap");
	
//	private static Map<String,FastList<FastList<String>>> map = db.createHashMap("map1")
//			 .keySerializer(db.getDefaultSerializer())
//			 .valueSerializer( db.getDefaultSerializer())
//             .makeOrGet();
	
	
//	private  static Map<String, FastList<FastList<String>>> map = new FastMap<String, FastList<FastList<String>>>();
	private  static Map<String, FastList<FastList<String>>> map = new FastMap<String, FastList<FastList<String>>>();
	private static Map<String, ArrayList<String>> searchCache = new FastMap<String, ArrayList<String>>();
	
	public synchronized static void putInCache(String key, ArrayList<String> value)
	{
		String[] arr= key.split(" ");
		String erg=arr[arr.length-1];
				searchCache.put(erg, value);
				//System.out.println(key+"|"+erg);
		//System.out.println("upload complete: "+erg+" "+getCache(key));
	}
	
	public synchronized static ArrayList<String> getCache(String key)
	{
		String[] arr= key.split(" ");
		String erg=arr[arr.length-1];
				
				//System.out.println("Anfrage: "+key+"|->"+erg+"|"+searchCache.get(erg));
		return searchCache.get(erg);
	}
	public synchronized static void clearCache()
	{
		searchCache.clear();
	}
	
	
//	DB db;
//	private  static HTreeMap<String, FastList<FastList<String>>> map =  db.hashMapCreate("map")
//	        .keySerializer(Serializer.STRING)
////	        .valueSerializer(Serializer.LONG)
//	        .makeOrGet();
	
	//private static Map<String, List<String>> mapi = new HashMap<String, List<String>>();
	/**
	 * Adds the token as key and its position as value to a HasMap. The index controlls the sublists
	 * @param token The token is the key and mostly a token or a word
	 * @param value Hashmaps value as String, e.g a word or a wordposition as String
	 * @param index For Sublistcontroll
	 */
	public synchronized static void observe(String token, String value, int index)
	{
//		DB db=DBMaker.newMemoryDB();
//		Map  treeMap = DBMaker.newTempHashMap();
//	       treeMap.put(111,"some value");
		token=token.toLowerCase();
		value=value.toLowerCase();
		//System.out.println(position+"        "+token);
		FastList<FastList<String>>str=map.get(token);
		if(str==null)
			{
			str= new FastList<FastList<String>>();
			str.add(new FastList<String>());
			str.add(new FastList<String>());
			str.add(new FastList<String>());
			}
		if(str.get(index).contains(value)==false)
		{
			str.get(index).add(value);
		}
		map.put(token, str);
		//System.out.println(token+"        "+str);
//		if(str!=null) { position=str+" && "+position; }
//		map.put(token, position);
	}
	
	/**
	 * Adds two words in both ways to a HasMap to register already performed featuretranslations.
	 * @param token First word
	 * @param value Second word like the translation of the firstword
	 */
	public static void addMultipleCodesandTranslationsToWord(String token, String value)
	{
		token=token.toLowerCase();
		value=value.toLowerCase();
		observe(token,value,1);
		observe(value,token,2);
		//System.out.println("TO "+value+" "+token);
	}
	/**
	 * Returns all Positions of the given token as List of Strings.
	 * @param token The input token
	 * @return A list with positions as string
	 */
	public static FastList<String> getPositionOfString(String token)
	{
		token=token.toLowerCase();
		//System.out.println("V "+token+" "+map.get(token));
		//return map.get(token);
		if(map.get(token)!=null){return map.get(token).get(0);}
		
		return null;
	}
	
	/**
	 * Returns all Translations of a given string.
	 * @param token The input string.
	 * @return A list containing the multiple codes.
	 */
	public static FastList<String> getMultipleCodesOfString(String token)
	{
		token=token.toLowerCase();
		//System.out.println("V "+token+" "+map.get(token).get(1));
		//return map.get(token);
		try{
			//System.out.println(token+" "+map.get(token).get(1));
			return  map.get(token).get(1);
		}catch(NullPointerException e)
		{
			return null;
		}
		
	}
	
	/**
	 * Returns to a given translationcode and a given translation the translations as list of possible translations
	 * @param token the input word
	 * @param translationCode the Translation in which the input word should be translated as translationcode
	 * @return the List with the results for one word and one translation code
	 */
	public static FastList<String> getTranslationsToWord(String token, String translationCode)
	{
		token=token.toLowerCase();
		translationCode=translationCode.toLowerCase();
		//System.out.println("V "+token+" "+map.get(token).get(2));
		//return map.get(token);
		try{
			FastList<String> erg= map.get(token).get(2);
			FastList<String> output= new FastList<String>();
			for(int i=0;i<erg.size();i++)
			{
				String t= erg.get(i);
				//System.out.println(t+"hier "+i+translationCode+erg.size());
				if(t.startsWith(translationCode, 0))
				{
					//System.out.println(t+"falsch"+translationCode);
					output.add(t);
				}
				
			}
			//System.out.println(output);
			return  output;
		}catch(NullPointerException e)
		{
			return null;
		}
		
	}
	
	public static String postagOf(String input)
	{
		String[] temp=input.split(" ");
		input=temp[temp.length-1];
		//input=input.replaceAll(" ", "");
		FastList<String> translation=TokenObserver.getTranslationsToWord(input, "po");
	
		String out="LEER";
//		for(String erg: translation)
//		{
//			//System.out.println(erg);
//		}
		//translation.get(0);
		//meta.getToken(wal[0], wal[1], wal[2], sub, 1);
		if(translation==null||translation.isEmpty()){
			//System.out.println("KEIN POSTAG GEFUNDEN: "+input);
			//System.out.println(input+"|"+out);
		return "LEER";
		
		}
		out=translation.get(0);
		//System.out.println(input+"|"+out);
		return out;
	}
	
	/**
	 * Sets this map.
	 * @param hashMap Inputhashmap.
	 */
	public static void setMap(Map<String,  FastList<FastList<String>>> hashMap)
	{
		map=hashMap;
		
		//this.updateMap();
	}
	
	/**
	 * Returns this hashmap.
	 * @return Hashmap.
	 */
	public static Map<String,  FastList<FastList<String>>> getMap()
	{
		return map;
	}

//	@Override //for serializing
//	public Object deserialize(DataInput arg0, int arg1) throws IOException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public int fixedSize() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public void serialize(DataOutput arg0, Object arg1) throws IOException {
//		// TODO Auto-generated method stub
//		
//	}
}
