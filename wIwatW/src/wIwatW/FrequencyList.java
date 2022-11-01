package wIwatW;
import java.util.ArrayList;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import javolution.util.FastList;

/**
 * This class represents a frequencylist, which saves the frequency of its elements. It doesnt reduce duplikates by itself and counts them as different elements.
 * The elements of this list are FrequencyListFields, which are stringarraylists with an integer value.
 * @author Lasse
 *
 */
public class FrequencyList extends ArrayList<FrequencyListField> 
{
	int frequencySum=-1;
	private static final long serialVersionUID = 4322170112009574329L;
	
	/**
	 * Gives back the frequency of a specific entry.
	 * @param index The entry you want to have.
	 * @return The frequency.
	 */
	
	public FrequencyList setFrequencySum()
	{
		int sum=0;
		
		//for(FrequencyListField i :this)
		for(int j=0;j<this.size();j++)
		{
			FrequencyListField i=this.get(j);
			if(i==null)
			{
				continue;
			}
			//System.out.println(i);
			double value=1;
			try{

				value=i.getFrequency();
			}catch(Exception e)
			{
				System.out.println(i);
				System.out.println("");
				System.out.println("hier<");
				System.out.println(" >>"+i.getContent());
				System.out.println(">da");
				value=i.getFrequency();
			}
			
	
			sum+=value;
		}

		
		this.frequencySum=sum;
		
		return this;
				
	}
	
	
	/**
	 * Returns the frequency of an entry given by its index
	 * @param index the chosen entry index
	 * @return the frequency value
	 */
	public double getFrequencyOfEntry(int index)
	{
		return this.get(index).getFrequency();
	}
	
	/**
	 * Returns the sum of all frequencies in this list
	 * @return the frequency sum
	 */
	public int getFrequencySum()
	{			
		return frequencySum;
	}
	
	/**
	 * Adds a new field to this list. If this field is already in the list, checked by the string content, it will be merged with the old field by adding its numbers
	 * @param e The field that should be added
	 * @return The resulting list
	 */
	public FrequencyList addDistinct(FrequencyListField e)
	
	{
		if(e!=null&&e.isEmpty()==false)
		{
			
			//if(e.getContent().get(1).equalsIgnoreCase("duftenden"))System.out.println(e.getContent()+" "+e.getFrequency()+" "+e.getValue());
			if(this.containsMine(e))
			{
				
				this.get(indexOf(e)).addFieldValuesOfAnotherField(e);
				
				
			
			}
			else
			{
			
				this.add(e);
			}
			
		}
		return this;
	}
//	public FrequencyList addAllDistinct(FrequencyList fl)
//	{
//		FrequencyList neu=fl;
//	//this.stream().forEach(thisflf->helpaddAllPD(thisflf,fl));	
//		for(FrequencyListField i: this)
//		{
//			//FrequencyList neu=fl;
//			if(fl.containsMine(i)){
//				//add werte
//				neu.get(indexOf(i)).addFieldValuesOfAnotherField(i);
//				
//				
//			}
//			else{
//				//add komplett
//				neu.add(i);
//			}
//		}
//		return neu;
//			
//		
//	//this.stream().forEach(thisflf->helpaddAllPD(thisflf,fl));	
////		for(FrequencyListField i: fl)
////		{
////			//FrequencyList neu=fl;
////			if(this.containsMine(i)){
////				//add werte
////				this.get(indexOf(i)).addFieldValuesOfAnotherField(i);
////				
////				
////			}
////			else{
////				//add komplett
////				this.add(i);
////			}
////		}
////		return this;
//	}
//	
//	private FrequencyList helpaddAllPD(FrequencyListField thisflf, FrequencyList fl)
//	{
//		FrequencyList neu=fl;
//		if(fl.containsMine(thisflf)){
//			//add werte
//			neu.get(indexOf(thisflf)).addFieldValuesOfAnotherField(thisflf);
//			
//			
//		}
//		else{
//			//add komplett
//			neu.add(thisflf);
//		}
//		return neu;
//	}
//	/**
//	 * Increases the frequency of a specific entry by one.
//	 * @param index The entry you want to increase.
//	 */
//	public void increaseFrequencyOfEntry(int index)
//	{
//		this.get(index).increaseFrequencyByOne();
//	}
	

	

	
	

	/**
	 * Sorts this list after its frequency, so that more frequent entrys are on top.
	 */
	public FrequencyList sortAfterFrequency()
	{
		
		Collections.sort(this);
		return this;
	}
	
	public FrequencyList removeFirstIfMoreThanOne()
	{
		if(this.size()>1)this.remove(0);
		return this;
	}

	private boolean containsMine(FrequencyListField flf)
	{
		boolean tmp=false;
		if(this.duplicateWithoutFrequency().contains(flf.getContent())) tmp=true;
		//System.out.println(tmp);
		return tmp;
	}
	
/**
 * Unions two lists
 * @param frequencyList the second list
 * @return the resulting list
 */
	
	//Anmerkung: bei verschiedenen Frequenzen, aber gleichen Listeneinträgen, werden die Elemente als verschieden angesehen?
	//auch erreichbar durch Suche auf beiden zugrundeliegenden Corpora
	public FrequencyList union(FrequencyList frequencyList)
	{
		
		FrequencyList fl= new FrequencyList();
		fl.addAll(this);
		
//		list.forEach((String element) ->    System.out.println(element));
//		for(int i=0;i<list.size();i++)
//		{
//			System.out.println(list.get(i));
//		}
		
		//frequencyList.forEach((FrequencyListField element) ->  test(fl, element));

		for(int i=0; i<frequencyList.size();i++)
		{
			if(fl.containsMine(frequencyList.get(i))==false)
			{
				FrequencyListField flf= frequencyList.get(i);				
				fl.add(flf);
				
			}
			else
			{
				//fl.setFrequency(Math.max(this.getFrequencyOfEntry(i), frequencyList.getFrequencyOfEntry(i)));
				fl.get(fl.indexOf(frequencyList.get(i))).setFrequency(fl.getFrequencyOfEntry(fl.indexOf(frequencyList.get(i)))+ frequencyList.getFrequencyOfEntry(i));
			}
		}
		
		
		return fl;
	}
	
/**
 * Intersects two lists
 * @param frequencyList the second list
 * @return the resulting list
 */
	public FrequencyList intersect(FrequencyList frequencyList)
	{
	
		FrequencyList fl= new FrequencyList();
//		fl.addAll(this);
//		fl.retainAll(frequencyList);
//		for(int i=0;i<fl.size();i++)
//		{
//			int frequency1=Math.min(this.getFrequencyOfEntry(this.indexOf(fl.get(i))), frequencyList.getFrequencyOfEntry(frequencyList.indexOf(fl.get(i))));
//			fl.get(i).setFrequency(frequency1);
//		}
		for(int i=0; i<frequencyList.size();i++)
		{
			if(this.containsMine(frequencyList.get(i))==true)
			{
				frequencyList.get(i).setFrequency(Math.min(frequencyList.get(i).getFrequency(), this.getFrequencyOfEntry(this.indexOf(frequencyList.get(i)))));
				fl.add(frequencyList.get(i));
				//fl.get(i).setFrequency(Math.min(frequencyList.getFrequencyOfEntry(i), this.getFrequencyOfEntry(this.indexOf(frequencyList.get(i)))));
				//fl.increaseFrequencyOfEntry(i, this.getFrequencyOfEntry(i)+frequencyList.getFrequencyOfEntry(i));
			}
		}
		
		
		return fl;
	}
	
	/**
	 * subtracts all frequencies to same string content of the second list from the first list
	 * @param frequencyList the second list
	 * @return the resulting list
	 */
	public FrequencyList minus(FrequencyList frequencyList)
	{
		
		FrequencyList fl= new FrequencyList();
		fl.addAll(this);
		for(int i=0; i<frequencyList.size();i++)
		{
			if(fl.containsMine(frequencyList.get(i))==true)
			{
				double freqThis=0, freqThat=0;
				freqThat=frequencyList.get(i).getFrequency();
				freqThis=fl.getFrequencyOfEntry(fl.indexOf(frequencyList.get(i)));
				double minus= freqThis-freqThat;
				if(minus>0)
				{
					fl.get(fl.indexOf(frequencyList.get(i))).setFrequency(minus);
				}
				else
				{
					fl.remove(frequencyList.get(i));
				}
				
			}
	
		}
		
		
		return fl;
	}
	
	/**
	 * Eliminates all entrys which are also in the second list
	 * @param frequencyList the second list
	 * @return the resulting list
	 */
	public FrequencyList without(FrequencyList frequencyList)
	{
		
		FrequencyList fl= new FrequencyList();
		fl.addAll(this);
		fl.removeAll(frequencyList);
		
		
		
		return fl;
	}
	
	/**
	 * Transforms frequencies to significane values given by an significance measure
	 * @param metaCorpus the metacorpus to operate on
	 * @param index the index, which means the index of the string content list of the frequency feild
	 * @param muster a second string content, which is compared to each field
	 * @param significanceMeasure the enum which holds the significance measure
	 * @return
	 */
	public FrequencyList frequenciesToSignificance(MetaCorpus metaCorpus, int index, String muster, SignificanceMeasureEnum significanceMeasure)
	{
		//nGes=metaCorpus.getSentenceNumber();
		//nA=für alle i: TokenObserver.get(A).get(i).split("__")[2], addiere zu nA, falls nicht schon dieser Satz addiert wurde, dito nB
		//nAB= score, problem, da möglich: nAB>>nA+nB
		//FrequencyList fl= new FrequencyList();
		double nGes=metaCorpus.getSentenceNumber(), nB=0;
		//System.out.println("nGes: "+nGes);
		FastList<String> arrayListB=new FastList<String>();
		String[] arr= Sentence.tokenizeSimple(muster);
		//muster=PreprocessingMethods.reduceVocals(arr[arr.length-1]);
		muster=PreprocessingMethods.deleteMinus(muster);
		muster=PreprocessingMethods.reduceSpace(PreprocessingMethods.makeSpace(muster));
		
		

		//System.out.println("muster: "+muster);
		if(TokenObserver.getPositionOfString(muster)!=null)	arrayListB=TokenObserver.getPositionOfString(muster);
		nB=arrayListB.size();
		double min=999999999999.9, max=0;
		for(int i=0; i<this.size();i++)
		{
			ArrayList<Double> frequencyModifiers = this.get(i).getFrequencyModifiers();
			HashMap<Integer, Double[]> occurrencies = this.get(i).getOccurrencies();
//			System.out.println("");
//			System.out.println("funk"+this.get(i).getOccurrencies().get(0)[0]);
//			System.out.println("funk"+this.get(i).getOccurrencies().get(0)[0]);
			//0:frequencyModify/Gewicht des Algorithmus
			//1:mM= multipleMatches*multipleMatchesWeight
			
			String a="";
			
			while(index>=this.get(i).size()) {index--;}
				a=this.get(i).get(index);
//			System.out.println("oc: "+occurrencies.get(0)[0]);
//			System.out.println("B: "+muster);
//			System.out.println("A: "+a);
				FastList<String> arrayListA=TokenObserver.getPositionOfString(a);
			double nA=arrayListA.size(), nAB=0;
			ArrayList<Double> sigList= new ArrayList<Double>();
			double newValue=0;
			for(int j=0;j<occurrencies.size(); j++)
			{
				
				Double[] tmparr=occurrencies.get(j);
				if(tmparr!=null)
				{
					nAB=tmparr[0];
					//System.out.println("funk"+occurrencies.get(j)[1]);
					Double tmp=0.0;
					switch(significanceMeasure) {
					case TANIMOTO:
						tmp=SignificanceMeasures.tanimoto(nA, nB, nGes, nAB)*occurrencies.get(j)[1];
						break;
					case BASE:
						tmp=SignificanceMeasures.base(nA, nB, nGes, nAB)*occurrencies.get(j)[1];
						break;
					case BASELINE:
						tmp=SignificanceMeasures.baseline(nA, nB, nGes, nAB)*occurrencies.get(j)[1];
						break;
					case DICE:
						tmp=SignificanceMeasures.dice(nA, nB, nGes, nAB)*occurrencies.get(j)[1];
						break;
					case GTEST:
						tmp=SignificanceMeasures.gTest(nA, nB, nGes, nAB)*occurrencies.get(j)[1];
						break;
					case LOGLIKELIHOODAPPRX:
						tmp=SignificanceMeasures.tanimoto(nA, nB, nGes, nAB)*occurrencies.get(j)[1];
						break;
					case MIN:
						tmp=SignificanceMeasures.min(nA, nB, nGes, nAB)*occurrencies.get(j)[1];
						break;
					case MUTUTALINFORMATION:
						tmp=SignificanceMeasures.mutualInformation(nA, nB, nGes, nAB)*occurrencies.get(j)[1];
						break;
					case POISSON:
						tmp=SignificanceMeasures.poisson(nA, nB, nGes, nAB)*occurrencies.get(j)[1];
						break;
					default:
						break;
					}
				
				
					
					sigList.add(tmp);
					newValue+=tmp;
				}
				
				
				
			}
			newValue=(newValue+frequencyModifiers.get(1))/sigList.size();
			
			
	
			//double newValue=SignificanceMeasures.loglikelihoodApprx(nA, nB, nGes, nAB);
			
			//newValue=newValue*(frequencyModifiers.get(0)+frequencyModifiers.get(1));
			this.get(i).setValue(newValue);
			if(newValue>max)max=newValue;
			if(newValue<min)min=newValue;
		}
		//if(countFalse>=0)System.out.println(countFalse+"/"+(countTrue+countFalse)+" Fehler");
//		for(int i=0; i<this.size();i++)
//		{
//			//System.out.println(this.getFrequencyOfEntry(i));
//			this.get(i).setFrequency((this.getFrequencyOfEntry(i)-min)/(max-min));//alle niedrigsten Ergebnisse fallen raus
//		}
		
		
		return this;
	}
	
//	private static double newValuetanimoto(FrequencyListField fll, double nB, double nGes, int index)
//	{
//		ArrayList<Double> frequencyModifiers = fll.getFrequencyModifiers();
//		HashMap<Integer, Double[]> occurrencies = fll.getOccurrencies();
////		System.out.println("");
////		System.out.println("funk"+this.get(i).getOccurrencies().get(0)[0]);
////		System.out.println("funk"+this.get(i).getOccurrencies().get(0)[0]);
//		//0:frequencyModify/Gewicht des Algorithmus
//		//1:mM= multipleMatches*multipleMatchesWeight
//		String a=fll.get(index);
////		System.out.println("oc: "+occurrencies.get(0)[0]);
////		System.out.println("B: "+muster);
////		System.out.println("A: "+a);
//		FastList<String> arrayListA=TokenObserver.getPositionOfString(a);
//		double nA=arrayListA.size(), nAB=0;
//		ArrayList<Double> sigList= new ArrayList<Double>();
//		double newValue=0;
//		for(int j=0;j<occurrencies.size(); j++)
//		{
//			
//			Double[] tmparr=occurrencies.get(j);
//			if(tmparr!=null)
//			{
//				nAB=occurrencies.get(j)[0];
//				Double tmp=SignificanceMeasures.tanimoto(nA, nB, nGes, nAB)*occurrencies.get(j)[1]+frequencyModifiers.get(1);
//				sigList.add(tmp);
//				newValue+=tmp;
//			}
//			
//			
//			
//		}
//		newValue=newValue/sigList.size();
//		
//		
//
//		//double newValue=SignificanceMeasures.loglikelihoodApprx(nA, nB, nGes, nAB);
//		
//		//newValue=newValue*(frequencyModifiers.get(0)+frequencyModifiers.get(1));
//		 return newValue;
////		if(newValue>max)max=newValue;
////		if(newValue<min)min=newValue;
//	}
	
	//langsamer
//	public FrequencyList frequenciesToSignificanceParallel8(MetaCorpus metaCorpus, int index, String muster)
//	{
//		//nGes=metaCorpus.getSentenceNumber();
//		//nA=für alle i: TokenObserver.get(A).get(i).split("__")[2], addiere zu nA, falls nicht schon dieser Satz addiert wurde, dito nB
//		//nAB= score, problem, da möglich: nAB>>nA+nB
//		//FrequencyList fl= new FrequencyList();
//		double nGes=metaCorpus.getSentenceNumber(); 
//		//System.out.println("nGes: "+nGes);
//		FastList<String> arrayListB=new FastList<String>();
//		
//		if(TokenObserver.getPositionOfString(muster)!=null)	arrayListB=TokenObserver.getPositionOfString(muster);
//		final double nB=arrayListB.size();
//		//double min=999999999999.9, max=0;
//		
//		this.parallelStream().forEach(es-> es.setValue(newValuetanimoto(es,nB,nGes,index)));
//		
//		return this;
//	}
	
//	//parallelisierbar
//	public FrequencyList frequenciesToSignificanceWithReferenceList( FrequencyList reference, int index)
//	{
//		this.setFrequencySum();
//		reference.setFrequencySum();
//		//log likelihood
//		int n1=this.getFrequencySum();
//		int n2=reference.getFrequencySum();
//		//System.out.println(n2);
//		for(int i=0; i<this.size();i++)
//		{
////			boolean matchFlag=false;
////			double newValue=this.get(i).getFrequency();
////			for(int j=0; j<reference.size();j++)
////			{
////				if(this.get(i).get(index).equals(reference.get(j).get(index)))
////				{
////					double k1= this.get(i).getFrequency();
////					double k2= reference.get(j).getFrequency();
////
////					//System.out.println(this.get(i).get(index)+" k1 "+k1+" k2 "+k2+" n1 "+n1+" n2 "+n2);
////					newValue=TokenObserver.likelihoodRatio(n1, n2, k1, k2);
////					System.out.println(newValue);
////					matchFlag=true;
////					break;
////				}
////			}
////			if(matchFlag==false)
////			{
////				int k1= (int) Math.round(this.get(i).getFrequency());
////				int k2= 0;
////				//System.out.println(this.get(i).get(index)+" k1 "+k1+" k2 "+k2+" n1 "+n1+" n2 "+n2);
////				newValue=TokenObserver.likelihoodRatio(n1, n2, k1, k2);
////			}
////			this.get(i).setValue(newValue);
//			this.get(i).setFrequency(valueFrequenciesToSignificanceWithReferenceList( reference,  this.get(i), index,  n1,  n2));
//		}
//		return this;
//	}
	
	/**
	 * Computes significance values based on a referencecorpus frequency list
	 * @param reference the reference list
	 * @param index the index, which means the index of the string content list of the frequency field
	 * @return the resulting list
	 */
	public FrequencyList frequenciesToSignificanceWithReferenceListParallel8( FrequencyList reference, int index)
	{
		this.setFrequencySum();
		reference.setFrequencySum();
		//log likelihood
		int n1=this.getFrequencySum();
		int n2=reference.getFrequencySum();
		
		this.parallelStream().forEach(es-> es.setValue(valueFrequenciesToSignificanceWithReferenceList( reference,  es, index,  n1,  n2)));
			//this.get(i).setFrequency(valueFrequenciesToSignificanceWithReferenceList( reference,  this.get(i), index,  n1,  n2));
		
		return this;
	}
	
	private double valueFrequenciesToSignificanceWithReferenceList(FrequencyList reference, FrequencyListField es, int index, int n1, int n2)
	{
		boolean matchFlag=false;
		double newValue=es.getFrequency();
		for(int j=0; j<reference.size();j++)
		{
			if(es.get(index).equals(reference.get(j).get(index)))
			{
				double k1= es.getFrequency();
				double k2= reference.get(j).getFrequency();

				//System.out.println(es.get(index)+" k1 "+k1+" k2 "+k2+" n1 "+n1+" n2 "+n2);
				newValue=SignificanceMeasures.likelihoodRatio(n1, n2, k1, k2);
				matchFlag=true;
				break;
			}
		}
		if(matchFlag==false)
		{
			int k1= (int) Math.round(es.getFrequency());
			int k2= 0;
			//System.out.println(this.get(i).get(index)+" k1 "+k1+" k2 "+k2+" n1 "+n1+" n2 "+n2);
			newValue=SignificanceMeasures.likelihoodRatio(n1, n2, k1, k2);
		}
		return newValue;
	}
	
	//parallelisierbar
//	public FrequencyList frequenciesToSignificanceWithReferenceListEasy( FrequencyList reference, int index)
//	{
//		//relative ferquency ratios (ManSchü 177)
//		int k1=this.getFrequencySum();
//		int k2=reference.getFrequencySum();
//		
//		for(int i=0; i<this.size();i++)
//		{
//			boolean flag=false;
//			for(int j=0; j<reference.size();j++)
//			{
//				if(this.get(i).get(index).equalsIgnoreCase(reference.get(j).get(index)))
//				{
//					double n1=this.getFrequencyOfEntry(i)/k1;
//					double n2=reference.getFrequencyOfEntry(j)/k2;
//					double newValue= n2/n1;
//					this.get(i).setFrequency(newValue);
//					flag=true;
//					break;
//				}
//			}
//			if(flag==false)
//			{
//				this.get(i).setFrequency(0);
//			}
//		}
//		return this;
//	}
	
	/**
	 * Reduces the list to n elements
	 * @param n the number of elements that should remain
	 * @return the resulting list
	 */
	public FrequencyList shortenListToNElements(int n)
	{
		FrequencyList fl= new FrequencyList();
		for(int i=0; i<this.size();i++)
		{
			if(i>=n)break;
			fl.add(this.get(i));
			
		}

		
		return fl;
	}
	
	/**
	 * Deletes all fields with a string content that starts with a lower case letter
	 * @return the resulting list
	 */
	public FrequencyList deleteAllLowerCases()
	{
		FrequencyList fl= new FrequencyList();
		for(int i=0; i<this.size();i++)
		{
			String normal=this.get(i).getContent().get(0);
			String lowercase=this.get(i).getContent().get(0).toLowerCase();
			if(normal.equals(lowercase)==false)fl.add(this.get(i));
			
		}

		
		return fl;
	}
	
	//parallelisierbar
//	public FrequencyList tfidfForFrequencyListResults()
//	{
//		//double newValue=0;
//		for(int i=0; i<this.size();i++)
//		{
////			int tfdt=(int)Math.round(this.getFrequencyOfEntry(i));
////			int d=this.size();
////			int dft= 1;
////			newValue=TokenObserver.tfidf(tfdt, dft, d);
//			this.get(i).setValue(valueTfidfForFrequencyListResults(this.get(i)));
//		}
//		return this;
//	}
	
	private static double valueTfidfForFrequencyListResults(FrequencyListField es,MetaCorpus meta, TfidfEnum tfidfEnum)
	{		
	
		if(tfidfEnum==TfidfEnum.SENTENCEBASED)return es.tfidfSentence(meta);
		if(tfidfEnum==TfidfEnum.STORYBASED)return es.tfidfStory(meta);
	 
			return 0;
		
	}
	

	/**
	 * Computes the tfidf value for each list entry. the tfidf is either story or sentence bases
	 * @param meta the metacorpus
	 * @param tfidfEnum to controll if story or sentence based
	 * @return The resulting list
	 */
	public FrequencyList tfidfForFrequencyListResultsParallel8(MetaCorpus meta,TfidfEnum tfidfEnum)
	{
		
		this.parallelStream().forEach(es-> es.setValue(valueTfidfForFrequencyListResults( es,  meta, tfidfEnum)));
		
			
		
		return this;
	}
	
	/**
	 * Transforms each frequency value in a relative value by dividing with the sum off all frequency values
	 * @return the resulting list
	 */
	public FrequencyList relativeOccurency()
	{
		double newValue=0;
		this.setFrequencySum();
		int allSum=this.getFrequencySum();
		for(int i=0; i<this.size();i++)
		{
			
			//System.out.println(this.getFrequencyOfEntry(i));
			newValue=this.get(i).getRelativeFrequency(allSum);
			this.get(i).setValue(newValue);
			//System.out.println(newValue);
		}
		return this;
	}
	
	

	/**
	 * Transforms each frequency value in a relative value by dividing with the sum off all frequency values
	 * @return the resulting list
	 */
	public FrequencyList relativeOccurencyParallel8()
	{
	//durchlaufe Liste, berechne für jeden listenwert neuen Wert
		this.setFrequencySum();
		final int allSum=this.getFrequencySum();
		FrequencyList frlist= this;
		this.parallelStream().forEach(es-> es.setValue(es.getRelativeFrequency(allSum)));
		return frlist;
	}
	
	/**
	 * Reduxes the list to n random choosen elements
	 * @param n the number of the remaining random elements
	 * @return The resulting list
	 */
	public FrequencyList giveNRandomElements(int n)
	{
		
		FrequencyList fl= new FrequencyList();
		if(n>this.size()-1) n=this.size();
		for(int i=0; i<n;i++)
		{
			int random=(int)Math.round((Math.random()*100)%(this.size()-1));
			if(fl.contains(this.get(random))==false){fl.add(this.get(random));}
			else{i--;}
			
			
		}

		
		return fl;
	}
	
	//parallelisierbar
	private ArrayList<ArrayList<String>> duplicateWithoutFrequency()
	{
		ArrayList<ArrayList<String>>  list = new ArrayList<ArrayList<String>> ();
		
		//this.parallelStream().forEach(es-> list.add(es.getContent()));
		//list=(ArrayList<ArrayList<String>>) listcopy.parallelStream().map(es-> es.getContent()).collect(Collectors.toList());
		for(int i=0; i<this.size();i++)
		{
			try
			{
				list.add(this.get(i).getContent());	
			}
			catch(Exception e)
			{
				//System.out.println("exc"+e+list.get(i).get(0));
			}
			//System.out.println(list.get(i).get(0));
		}
		
//		for(int i=0; i<list.size();i++)	
//		{	String str="";
//			for(int j=0; j<list.get(i).size();j++)
//			{
//			
//			str+=list.get(i).get(j);
//			}
//		System.out.println(str);
//		}
		
		return list;
	}

	//parallelisierbar
	
//	public FrequencyList deleteNullFields()
//	{
//		for(int i=0; i<this.size();i++)
//		{
//			if(this.get(i)==null);
//				{
//					this.remove(i);
//					i--;
//				}
//		
//		}
//		return this;
//	}
	
	/**
	 * eliminates the first string of each frequencylistfield in this list.
	 * @return The resulting list
	 */
	public FrequencyList reduce()
	{
		FrequencyList  list = new FrequencyList ();
		for(int i=0; i<this.size();i++)
		{
			if(this.get(i)==null){continue;}
			this.get(i).removeFirstString();
		}
		list=this;
		return list;
	}
	
	/**
	 * eliminates the first string of each frequencylistfield in this list.
	 * @return The resulting list
	 */
	public FrequencyList reduceParallel8()
	{
				
		this.parallelStream().forEach(es-> es.removeFirstString());


		return this;
	}
	

	/**
	 * Eliminates all fields of this list, which dont consist of a verb, noun or adjective
	 * @return The resulting list
	 */
	public FrequencyList reduceToNVA()
	{
		FrequencyList  list = new FrequencyList ();
		for(int i=0; i<this.size();i++)
		{
			//System.out.println(this.get(i).get(0));
			if(      this.get(i).getAdjectiveFlag()==true||
					this.get(i).getNounFlag()==true
					||this.get(i).getVerbFlag()==true
					)
					
				list.add(this.get(i));
		}
		//list=this;
		return list;
	}
	
	//parallelisierbar
	/**
	 * Transforms a word on the given position of each entry to its baseform
	 * @param position the position in the string list of the listfield
	 * @return The resulting list
	 */
//	public FrequencyList reduceWordToBaseform(int position)
//	{
//		FrequencyList  list = new FrequencyList ();
//		for(int i=0; i<this.size();i++)
//		{
//			FastList<String> tmplist=TokenObserver.getTranslationsToWord(this.get(i).get(position), "bf");
//			
//
//			if(tmplist!=null&&tmplist.size()>0)
//			{
////				String erg="";
////				for(int j=0; j<tmplist.size()-1;j++)
////				{
////					String tmp=tmplist.get(j);
////					tmp=tmp.substring(2, tmp.length());
////					
////					//erg+=tmp+"__";
////				}
////				//erg+=tmplist.get(tmplist.size()-1);
//				//System.out.println(tmplist.get(0));
//				String erg=Collections.max(tmplist, new Comparator<String> () {
//					
//
//					public int compare(String arg0,
//							String arg1) {
//						if(arg0.length()>arg1.length())return 1; //vertauschbar
//						if(arg0.length()<arg1.length())return -1;
//						return 0;
//					}
//				});
//				erg=erg.substring(2, erg.length());
//				char t=this.get(i).getContent().get(position).charAt(0);
//				if(Character.isUpperCase(t)){
//					String temp=erg.substring(0, 1).toUpperCase();
//					temp+=erg.substring(1, erg.length());
//					erg=temp;
//				}
//				FrequencyListField flf = new FrequencyListField();
//				flf=this.get(i);
//				
//				flf.set(position, erg);
//				
//		list.addDistinct(flf);
//			}
//		}
//		//list=this;
//		return list;
//	}
	
	//parallelisierbar
	/**
	 * Eliminates all entrys which have a sentence mark on the given list position
	 * @param position
	 * @return
	 */
	public FrequencyList removeSentenceMarksInPosition(int position)
	{
		FrequencyList  list = this;//new FrequencyList ();
		for(int i=0; i<this.size();i++)
		{
			
				if(list.get(i).getContent().get(position).equals(".")||list.get(i).getContent().get(position).equals(",")
						||list.get(i).getContent().get(position).equals(":")||list.get(i).getContent().get(position).equals("!")
						||list.get(i).getContent().get(position).equals("?")||list.get(i).getContent().get(position).equals("'")
						||list.get(i).getContent().get(position).equals(";"))
				{
					System.out.println(list.get(i).getContent().get(position)+"!D");
					list.remove(i);
					
				}
				//list.add(this.get(i));
		}
		//list=this;
		return list;
	}
	
	//parallelisierbar...filter
	/**
	 * Deletes all fields which frequency is smaller or equals the bordernumber
	 * @param border the excluded minimum
	 * @return the resulting list
	 */
	public FrequencyList  selectByFrequencyOver(int border)
	{
		FrequencyList freqList1 = new FrequencyList();
		for(int i=0;i<this.size();i++)
		{
			if(this.get(i).getValue()>border)
			{
				freqList1.add(this.get(i));
			}
		}
		return freqList1;
	}
	
	//parallelisierbar
	/**
	 * checks if the list consist of matching fields with the same string content. in that case the values are added to each other and one if the fields is deleted
	 * @return The resulting list
	 */
	public FrequencyList collect()
	{
//		FrequencyList  list = new FrequencyList ();
//		
////		list.addAllDistinct(this);
//		for(FrequencyListField i:this)
//		{
//		
//			
//			if(list.contains(i)==false) 
//			{
//				list.add(i);
//				
//			}
//			else
//			{
//				
//				list.get(list.indexOf(i)).increaseFrequency(i.getFrequency());//.get(i).getFrequency()
//				//list.get(list.indexOf(this.get(i))).addFieldValuesOfAnotherField(this.get(i));
//			}
//			//System.out.println("");
//			//this.get(i).removeFirstString();
//		}
		
		FrequencyList  list = new FrequencyList ();
		
//		list.addAllDistinct(this);
		for(FrequencyListField i:this)
		{
		
			list.addDistinct(i);
		}
		//list=this;
		return list;
	}
	
	//parallelisierbar
	/**
	 * Transforms a word given by its position in the field to a random synonym.
	 * @param position the words position in the frequencylist field
	 * @return the resulting list
	 */
//	public FrequencyList makeRandomSynonymsOf(int position)
//	{
//		FrequencyList  list = new FrequencyList ();
//		
//			this.parallelStream().forEach(flf->{
//				String neu=Wortschatz.getRandomSynonym(flf.getContent().get(position));
//				System.out.println("VVV"+flf.getContent().get(position)+"|"+neu);
//				flf.remove(position);
//				flf.add(position, neu);
//				
//				list.add(flf);
//				
//
//		});
//		//list=this;
//		return list;
//	}
	
	/**
	 * Puts the weights into the frequencuies
	 * @return the resulting list
	 */
	public FrequencyList multipleWithGivenWeights()
	{
		
		this.parallelStream().forEach(field->
		{
			
				

		});
		for(int i=0; i<this.size();i++)
		{
			ArrayList<Double> frequencyModifiers = this.get(i).getFrequencyModifiers();
			HashMap<Integer, Double[]> occurrencies = this.get(i).getOccurrencies();

			double newValue=0, min=0;
			double relationValue=999999999;
			for(int j=0;j<occurrencies.size(); j++)
			{ 	if(occurrencies.get(j)!=null)
				{
					min=occurrencies.get(j)[0];
					if(min<relationValue)relationValue=min;
				}
			}
			if(relationValue==999999999) return this;
			for(int j=0;j<occurrencies.size(); j++)
			{
				//relationValue=occurrencies.get(0)[0];
				Double[] ocArr=occurrencies.get(j);
				

				
				//33 33 | 33/33=1|33/33=1 |
				//1*1+1*1=2
				//
				
				//66 33 99 1 1/2 3/2 <>2 1 3
				//1*1+2*2+3*1=8
				if(ocArr!=null&&relationValue>0)
				{
					double normedValue=ocArr[0]/relationValue;
					System.out.println("name "+this.get(i).getContent().get(0));
					System.out.println("häufigkeit "+ocArr[0]+" "+normedValue);
					System.out.println("gewicht "+ocArr[1]);
					System.out.println("1 "+frequencyModifiers.get(1));
					
					
					Double tmp=normedValue*ocArr[1];
					System.out.println("temp "+tmp);
					newValue+=tmp;
					System.out.println("nv "+newValue);
				
				}
				
				
			}
			newValue=newValue/occurrencies.size();
			//System.out.println(newValue+" "+this.get(i).getValue());
			newValue=this.get(i).getValue()*newValue+frequencyModifiers.get(1);
			this.get(i).setValue(newValue);

			
		}
		

		return this;
	}

	/**
	 * Norms the frequencies by using maximum and minimum
	 * @return
	 */
	public FrequencyList trimTo01()
	{
			double min=999999, max=-1;
			this.sortAfterFrequency();
	//		double max=this.parallelStream().max(FrequencyListField::compareTo).get().getValue();
	//		double min=this.parallelStream().min(FrequencyListField::compareTo).get().getValue();
	//		this.parallelStream().forEach(flf->{
	//			flf.setValue((flf.getValue()-(min))/(max-(min)));
	//		});
		for(FrequencyListField flf: this)
		{
			double value=flf.getValue();
			if(value>max) max=value;
			if(value<min) min=value;
				
		}
		for(FrequencyListField flf: this)
		{
			double value=(flf.getValue()-(min))/(max-(min));
			if(value<=0)value=0.000000001;
			flf.setValue(value);
				
		}
	
	
		return this;
	}
	
	public FrequencyList putAllEntrysWithPointsToOne()
	{
		FrequencyList fl = new FrequencyList();
		
		for(FrequencyListField flf: this)
		{
			boolean hasPoint=false;
			int mem=-1;
			for(String token: flf)
			{
				if(token.equals("."))
				{
					hasPoint=true;
					mem=flf.getContent().indexOf(token);
				}
			}
			if(hasPoint==true)
			{
				flf.remove(mem);
			}
			fl.addDistinct(flf);
			
		}
		return fl;
	}
}
