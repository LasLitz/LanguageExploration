package wIwatW;

/**
 * Provides computations of different significance measures
 * @author Lasse
 *
 */
public class SignificanceMeasures {
	//bag of words assumptuion: mehrmaliges auftreten eines wortes in einem satz wird ignoriert, ich ignoriere nicht
	//nGes=metaCorpus.getSentenceNumber();
	//nA=für alle i: TokenObserver.get(A).get(i).split("__")[2], addiere zu nA, falls nicht schon dieser Satz addiert wurde, dito nB
	//nAB= score, problem, da möglich: nAB>>nA+nB
	//nA, nB: Anzahl Sätze die A bzw. B enthalten
	//nAB: Anzahl Sätze die A und B enthalten
	//nGes: Gesamtanzahl aller Sätze
	/**
	 * Computes tanimoto value
	 * @param nA Frequence of word A
	 * @param nB Frequence of bord B
	 * @param nGes Frequence of all words
	 * @param nAB Frequence of word B after word A
	 * @return the value
	 */
	public static double tanimoto(double nA, double nB, double nGes, double nAB )
	{//zu empfehlen
//		System.out.println("nA"+nA);
//		System.out.println("nB"+nB);
//		System.out.println("nGes"+nGes);
//		System.out.println("nAB"+nAB);
		double temp=nA+nB-nAB;
//		System.out.println("temp"+temp);
		double erg=0;
		if(temp!=0){erg=nAB/temp;}
//		System.out.println("erg"+erg);
//		System.out.println();
		return erg;
	}
	

	/**
	 * Computes mutual information value
	 * @param nA Frequence of word A
	 * @param nB Frequence of bord B
	 * @param nGes Frequence of all words
	 * @param nAB Frequence of word B after word A
	 * @return the value
	 */
	public static double mutualInformation(double nA, double nB, double nGes, double nAB )
	{//zu empfehlen
		double temp=nA*nB;
		double erg=0;
		if(temp!=0){erg=Math.log(nAB*nGes/(temp));}
		return erg;
	}
	
	/**
	 * Computes gtest value
	 * @param nA Frequence of word A
	 * @param nB Frequence of bord B
	 * @param nGes Frequence of all words
	 * @param nAB Frequence of word B after word A
	 * @return the value
	 */
	public static double gTest(double nA, double nB, double nGes, double nAB )
	{
		double temp=nA*nB/nGes;
		if(2.5*temp>=nGes)return 0;
		double erg=temp-nAB*Math.log(temp)+Math.log(factorial(nAB));
		
		return erg;
	}
	
	/**
	 * Computes approcimated loglikelihood value
	 * @param nA Frequence of word A
	 * @param nB Frequence of bord B
	 * @param nGes Frequence of all words
	 * @param nAB Frequence of word B after word A
	 * @return the value
	 */
	public static double loglikelihoodApprx(double nA, double nB, double nGes, double nAB )
	{
		//zu empfehlen
//		System.out.println("nA"+nA);
//		System.out.println("nB"+nB);
//		System.out.println("nGes"+nGes);
//		System.out.println("nAB"+nAB);
		double temp=Math.log(nGes);
		double temp2=nA*nB;
//		System.out.println("temp"+temp);
//		System.out.println("temp2"+temp2);
		double erg=0;
//		System.out.println(nAB*Math.log(nAB*nGes/(temp2)));
		if(temp!=0&&temp2!=0){erg=(nAB*Math.log(nAB*nGes/(temp2)))-nAB/temp;}
//		System.out.println("erg"+erg);
//		System.out.println();
		
		if(erg<0)erg=erg*(-1);
		
		return erg;
	}
	
	/**
	 * Computes dice value
	 * @param nA Frequence of word A
	 * @param nB Frequence of bord B
	 * @param nGes Frequence of all words
	 * @param nAB Frequence of word B after word A
	 * @return the value
	 */
	public static double dice(double nA, double nB, double nGes, double nAB )
	{//zu empfehlen
		double temp=nA+nB;
		double erg=0;
		if(temp!=0){erg=2*nAB/temp;}
		return erg;
	}

	/**
	 * Computes minimum value
	 * @param nA Frequence of word A
	 * @param nB Frequence of bord B
	 * @param nGes Frequence of all words
	 * @param nAB Frequence of word B after word A
	 * @return the value
	 */
	public static double min(double nA, double nB, double nGes, double nAB )
	{
		double temp=Math.min(nA, nB);
		//System.out.println(nA+" "+nB);
		double erg=0;
		if(temp!=0){erg=nAB/temp;}
		return erg;
	}
	
	/**
	 * Computes base value
	 * @param nA Frequence of word A
	 * @param nB Frequence of bord B
	 * @param nGes Frequence of all words
	 * @param nAB Frequence of word B after word A
	 * @return the value
	 */
	public static double base(double nA, double nB, double nGes, double nAB )
	{
		//nur bei Gleichverteilung sinnvoll
		double erg=0;
		if(nAB>0){erg=1;}
		return erg;
	}
	/**
	 * Computes baseline value
	 * @param nA Frequence of word A
	 * @param nB Frequence of bord B
	 * @param nGes Frequence of all words
	 * @param nAB Frequence of word B after word A
	 * @return the value
	 */
	public static double baseline(double nA, double nB, double nGes, double nAB )
	{
		//nur bei Gleichverteilung sinnvoll
		return nAB;
	}
	
	/**
	 * Computes poisson value
	 * @param nA Frequence of word A
	 * @param nB Frequence of bord B
	 * @param nGes Frequence of all words
	 * @param nAB Frequence of word B after word A
	 * @return the value
	 */
	public static double poisson(double nA, double nB, double nGes, double nAB )
	{
		//sig(w)= k(logk -log lambda-1)/log(n)
		//k= Häufigkeit von w in T
		//lambda =np
		//n= Länge des Textes T
		//p= relative Häufigkeit von w in R
		//T=mein Text
		//R=Referenzcorpus (z.B deutscher wortschatz)
		double temp=Math.min(nA, nB);
		double erg=0;
		if(temp!=0){erg=nAB/temp;}
		return erg;
	}
	
	    private static int factorial(double n) {
	        int fact = 1; 
	        for (int i = 1; i <= n; i++) {
	            fact *= i;
	        }
	        return fact;
	    }
	
		/**
		 * Computes likelihood ratio value for given frequencies
		 * @param n1 frequency of all words in corpus 1
		 * @param n2 frequency of all words in corpus 2
		 * @param k1 frequency of specific words in corpus 1
		 * @param k2 frequency of specific  words in corpus 2
		 * @return the value
		 */
	    public static double likelihoodRatio(double n1, double n2, double k1, double k2)
		{
			if(n1==0)
			{
				k1=0;
				n1=1;
			}
			if(n2==0)
			{
				k2=0;
				n2=1;
			}
			if(n1!=0&&n2!=0)
			{
				double p1=k1/n1, p2=k2/n2, p=(k1+k2)/(n1+n2); //so angegeben
				//double p1=n1/k1, p2=n2/k2, p=(n1+n2)/(k1+k2);
				
				double erg=2*(Math.log(likelihood(p1, k1, n1))	+Math.log(likelihood(p2, k2, n2))
							  -Math.log(likelihood(p, k1, n1))	-Math.log(likelihood(p, k2, n2)));
				return erg;
			}
			
			return 0;
		}


		private static double likelihood(double p,double k,double n)
		{
			double erg=Math.pow(p,k)*Math.pow((1-p),n-k);
			//System.out.println("llh "+erg+"    | p "+p+" k "+k+" n "+n+" "+(1-p)+""+Math.pow((1-p),n-k)+"            "+Math.pow(p,k));
			return erg;
		}
}
