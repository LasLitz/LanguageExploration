package wIwatW;

import java.io.Serializable;

/**
 * This class generates position IDs. 
 * @author Lasse
 *
 */
public class PositionIDGenerator implements Serializable
{
	private static final long serialVersionUID = 1942986080642153937L;
	int count=0;
	
	/**
	 * Generates a new ID. The IDs are representating the position of a token in the text. It is not planned to delete storys in the metacorpus.
	 * @return the ID
	 */
	public synchronized  String  generateID()
	{
		String erg="";
//			try {
//				Thread.sleep(Math.round((Math.random()*100)));
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
			erg=""+count;
			count++;
	

		return erg;
	}
}
