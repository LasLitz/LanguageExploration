package wIwatW;

/**
 * This class is a structure for saving a double value to a TranslationAlgorithm. It is used to modify the frequency of a founded item.
 * @author Lasse
 *
 */
public class TranslationAlgorithmValue 
{
	private double value;
	private TranslationAlgorithm tA;
	
	/**
	 * Contructs a TranslationAlgorithmValue-structure.
	 * @param tA The TranslationAlgorithm.
	 * @param value The value.
	 */
	public TranslationAlgorithmValue(TranslationAlgorithm tA, double value)
	{
		this.tA= tA;
		this.value =value; 
	}
	
	/**
	 * Returns the value.
	 * @return The double value of the Algorithm.
	 */
	public double getValue()
	{
		return value;
	}
	
	/**
	 * Returns the TranslationAlgorithm.
	 * @return The TranslationAlgorithm.
	 */
	public TranslationAlgorithm getTranslationAlgorithm()
	{
		return tA;
	}
}
