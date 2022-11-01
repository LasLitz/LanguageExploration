package wIwatW;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.function.Consumer;
import java.util.stream.Stream;

import javax.swing.AbstractListModel;
//import javax.swing.DefaultListModel;

public class AddAllListModel<T> extends AbstractListModel<T>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<T> arrayList = new ArrayList<T>();

	
//	 public AddAllListModel() {
//		 ArrayList arr = new ArrayList();
//		 
//		    for (int i = 0; i < 10000; i++) {
//		      arr.add("bib"+i);
//		    }
//		    this.addAll(arr);
//	 }
		    
	@Override
	public T getElementAt(int arg0) {
		
		try{
		return arrayList.get(arg0);
		}catch(Exception e)
		{
			
		}
		return null;
	}

	@Override
	public int getSize() {
		
		return arrayList.size();
	}
	
	public void forEach(Consumer<? super T> action)
	{
		try{
		arrayList.forEach(action);
		}
		catch(ConcurrentModificationException e)
		{
			
		}
	}
	public Stream<T> parallelStream()
	{
		return arrayList.parallelStream();
	}

	public void add(T e)
	{
		arrayList.add(e);
//		DefaultListModel dfl = new DefaultListModel ();
//		dfl.
		//return this;
	}
	
	public void clear()
	{
	
		arrayList.clear();
		//this.fireContentsChanged(this, 0, getSize());
	}
	public boolean contains(Object o)
	{
		return arrayList.contains(o);
	}
	public String containsWord(String word, int numberOfFollowers)
	{
		String temp="";
		String tmpArray[];
		ArrayList<String> ergList= new ArrayList<String>();
		
	
	
		for(Object e: arrayList)
		{
//			System.out.println("");
//			System.out.println("-----------");
//			System.out.println("");
			temp="";
			tmpArray=e.toString().split(" ");
			//temp=tmpArray[0];
			//temp=tmpArray[tmpArray.length-2];
			
//		System.out.println("e "+e.toString());
			
			for(int i=1;i<tmpArray.length;i++)
			{
			
				if(i==1)
					temp+=tmpArray[i];
				else
					temp+=" "+tmpArray[i];
			}
			
//			System.out.println("temp "+temp);
			
			//System.out.println(wordArray.length+"|"+tmpArray.length);
//			System.out.println("temp und word: |"+temp+"|"+word+"|");
			if(temp.equals(word)) 
			{
//				System.out.println("return 1");
				return temp;
			}
				
				
			
			if(word.endsWith(" ."))
			{
				String wordArray[]=word.split(" ");
				String newWord="";
				
				for(int i=wordArray.length-1;i>-1;i--)
				{
					
					if(i==wordArray.length-1)
					{
						newWord=wordArray[i];
					}
					else
					{
						newWord=wordArray[i]+" "+newWord;
					}
					
//					System.out.println("newWord "+newWord);
					
					ergList.add(newWord);
				
//				|Tür .|die Tür .|
//				3|3
//				|Gestalt vor mir|die Tür .|
				}
				
				for(int i=ergList.size()-1;i>0;i--)
				{
//					System.out.println("tempUNDnewWord: "+i+" |"+temp+"|"+newWord+"|"+ergList.get(i)+"|");
					if(temp.equals(ergList.get(i))) 
					{
//						System.out.println("return 2");
						return temp;
					}
					
				}
			}
			
			
		}
//		System.out.println("return 3");
		return null;
	}
	public void addElement(T e)
	{
		arrayList.add(e);
	}
	public void addAll(Collection<T> c)
	{
		//System.out.println("now!");	
		arrayList.addAll(c);
		this.fireContentsChanged(this, 0, getSize());

	}

}
