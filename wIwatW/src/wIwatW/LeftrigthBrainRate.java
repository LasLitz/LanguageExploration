package wIwatW;

import java.util.ArrayList;

public class LeftrigthBrainRate {
	
	public static double ratePhraseDistance(String phrase)
	{
		double value=0;
		phrase=phrase.toLowerCase();
		String[] phrasenArray =phrase.split(" ");
		double sum=0;
		for(int i=0; i<phrasenArray.length;i++)
		{
			sum+=rateWordDistance(phrasenArray[i]);
			
		}
		value=sum/phrasenArray.length;
		
		return value;
	}
	
	public static double ratePhrase(String phrase)
	{
		double value=0;
		phrase=phrase.toLowerCase();
		String[] phrasenArray =phrase.split(" ");
		double sum=0;
		for(int i=0; i<phrasenArray.length;i++)
		{
			sum+=rateWord(phrasenArray[i]);
			
		}
		value=sum/phrasenArray.length;
		
		return value;
	}

	public static double rateWord(String word)
	{
		word=word.toLowerCase();
		
		double value=0;
		for(int i=0; i<word.length();i++)
		{
			value+=rateLetter(word.charAt(i));
			
		}
		value=value/word.length();
		return value;
	}
	
	public static double rateWordDistance(String word)
	{
		word=word.toLowerCase();
		
		double value=0;
		for(int i=0; i<word.length();i++)
		{
			value+=rateLetterDistance(word.charAt(i));
			
		}
		value=value/word.length();
		return value;
	}
	
	private static int rateLetterDistance(char c)
	{
		if(c=='q')
		{
			return -5;
		}
		if(c=='a')
		{
			return -5;
		}
		if(c=='y')
		{
			return -5;
		}
		if(c=='w')
		{
			return -4;
		}
		if(c=='s')
		{
			return -4;
		}
		if(c=='x')
		{
			return -4;
		}
		if(c=='e')
		{
			return -3;
		}
		if(c=='d')
		{
			return -3;
		}
		if(c=='c')
		{
			return -3;
		}
		if(c=='r')
		{
			return -2;
		}
		if(c=='f')
		{
			return -2;
		}
		if(c=='v')
		{
			return -2;
		}
		if(c=='t')
		{
			return -1;
		}
		if(c=='g')
		{
			return -1;
		}
		if(c=='b')
		{
			return -1;
		}
		if(c=='z')
		{
			return 1;
		}
		if(c=='h')
		{
			return 1;
		}
		if(c=='n')
		{
			return 1;
		}
		if(c=='u')
		{
			return 2;
		}		
		if(c=='j')
		{
			return 2;
		}
		if(c=='m')
		{
			return 2;
		}
		if(c=='i')
		{
			return 3;
		}
		if(c=='k')
		{
			return 3;
		}
		if(c=='o')
		{
			return 4;
		}
		if(c=='l')
		{
			return 4;
		}
		if(c=='p')
		{
			return 5;
		}
		if(c=='�')
		{
			return 5;
		}
		if(c=='�')
		{
			return 6;
		}
		if(c=='�')
		{
			return 6;
		}
		if(c=='�')
		{
			return 6;
		}
		return 0;
	}
	
	private static int rateLetter(char c)
	{
	
		if(c=='q')
		{
			return -1;
		}
		if(c=='a')
		{
			return -1;
		}
		if(c=='y')
		{
			return -1;
		}
		if(c=='w')
		{
			return -1;
		}
		if(c=='s')
		{
			return -1;
		}
		if(c=='x')
		{
			return -1;
		}
		if(c=='e')
		{
			return -1;
		}
		if(c=='d')
		{
			return -1;
		}
		if(c=='c')
		{
			return -1;
		}
		if(c=='r')
		{
			return -1;
		}
		if(c=='f')
		{
			return -1;
		}
		if(c=='v')
		{
			return -1;
		}
		if(c=='t')
		{
			return -1;
		}
		if(c=='g')
		{
			return -1;
		}
		if(c=='b')
		{
			return -1;
		}
		if(c=='z')
		{
			return 1;
		}
		if(c=='h')
		{
			return 1;
		}
		if(c=='n')
		{
			return 1;
		}
		if(c=='u')
		{
			return 1;
		}		
		if(c=='j')
		{
			return 1;
		}
		if(c=='m')
		{
			return 1;
		}
		if(c=='i')
		{
			return 1;
		}
		if(c=='k')
		{
			return 1;
		}
		if(c=='o')
		{
			return 1;
		}
		if(c=='l')
		{
			return 1;
		}
		if(c=='p')
		{
			return 1;
		}
		if(c=='�')
		{
			return 1;
		}
		if(c=='�')
		{
			return 1;
		}
		if(c=='�')
		{
			return 1;
		}
		if(c=='�')
		{
			return 1;
		}
		return 0;
	}
//	weiblich 15 1
//	BDO�S 5
//	cos  5
//	CU�	  3
//	mnu�  4
//	380
	
//neutral 14 0
//	HKX MNVWZ 9 
//	xk vwz	  5
//  	
//m�nnlich 14 -1
//	EFIJLTY    7 
//	fijlrty	  7
//  17	
	
//	gemischt 15 -1+1=0
//	A�PQRG	  6 
//	a�bdempqg�  9
//  24569
	public static String binarySexOfPhrase(String phrase)
	{
		double value=sexOfPhrase(phrase);
		if(value>0) return "weiblich"/*+value*/;
		else if(value<0) return "m�nnlich"/*+value*/;
		else return "neutral"/*+value*/;
		
	}
	
	public static double sexOfPhrase(String phrase)
	{
		String[] split = phrase.split(" ");		
		double value=0;
		for(String word:split)
		{
			value+=sexOfWord(word);
		}
		
		value=value/split.length;
		return value;
	}
	
	public static double sexOfPhraseSumme(String phrase)
	{
		String[] split = phrase.split(" ");		
		double value=0;
		for(String word:split)
		{
			value+=sexOfWord(word);
		}
		
		
		return value;
	}
	public static double sexOfWord(String word)
	{
				
		double value=0;
		for(int i=0; i<word.length();i++)
		{
			value+=sexOfLetter(word.charAt(i));
			
		}
		//System.out.println(value+" "+word.length()+word);
		value=value/word.length();
		return value;
	}
	
	
	private static int sexOfLetter(char c)
	{
	
		if(c=='E')
		{
			return -1;
		}
		if(c=='F')
		{
			return -1;
		}
		if(c=='I')
		{
			return -1;
		}
		if(c=='J')
		{
			return -1;
		}
		if(c=='L')
		{
			return -1;
		}
		if(c=='T')
		{
			return -1;
		}
		if(c=='Y')
		{
			return -1;
		}
		if(c=='f')
		{
			return -1;
		}
		if(c=='i')
		{
			return -1;
		}
		if(c=='j')
		{
			return -1;
		}
		if(c=='l')
		{
			return -1;
		}
		if(c=='r')
		{
			return -1;
		}
		if(c=='t')
		{
			return -1;
		}
		if(c=='y')
		{
			return -1;
		}
		if(c=='1')
		{
			return -1;
		}
		if(c=='7')
		{
			return -1;
		}
		if(c=='B')
		{
			return 1;
		}
		if(c=='D')
		{
			return 1;
		}
		if(c=='O')
		{
			return 1;
		}		
		if(c=='�')
		{
			return 1;
		}
		if(c=='S')
		{
			return 1;
		}
		if(c=='c')
		{
			return 1;
		}
		if(c=='o')
		{
			return 1;
		}
		if(c=='s')
		{
			return 1;
		}
		if(c=='C')
		{
			return 1;
		}
		if(c=='U')
		{
			return 1;
		}
		if(c=='�')
		{
			return 1;
		}
		if(c=='m')
		{
			return 1;
		}
		if(c=='u')
		{
			return 1;
		}
		if(c=='�')
		{
			return 1;
		}
		if(c=='n')
		{
			return 1;
		}
		if(c=='3')
		{
			return 1;
		}
		if(c=='8')
		{
			return 1;
		}
		if(c=='0')
		{
			return 1;
		}
		return 0;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String ratedWord="Faust";
		ArrayList<String> list= new ArrayList<String>();
		list.add("Rom, Villa Massimo");
		list.add("Der Stift und das Papier");
		list.add("Die Berlinreise");
		list.add("Das Kind, das nicht fragte");
		list.add("Die Erfindung des Lebens");
		list.add("Im Licht der Lagune");
		list.add("Faustinas K�sse");
		list.add("Das Joshua-Profil");
		list.add("Noah");
		list.add("Der Nachtwandler");
		list.add("Abgeschnitten ");
		list.add("Nicht einschlafen");
		list.add("Faust");
		list.add("Faust. Der Trag�die zweiter Teil");
		list.add("Die Leiden des jungen Werthers");
		list.add("lol");
		list.add("omg");
		list.add("rofl");
		list.add("Ein Hologramm f�r den K�nig");
		list.add("Das wetter in geschlossenen R�umen");
		list.add("Tatort: im gelobten Land");
		list.add("Ich �ndere das �l");
		list.add("�lnot in Polska");
		list.add("Er ist wieder da");
		list.add("Ein ganzes halbes Jahr");
		list.add("Bretonische Flut");
		list.add("Die dunklen Krieger");
		list.add("Wenn du mich sehen k�nntest");
		list.add("Eine zuf�llige Liebe");
		list.add("Auf der Liste");
		list.add("Die Erinnerung so kalt");
		list.add("Der Fremde neben dir");
		list.add("Gott");
		list.add("Teufel");
		list.add("Die unendliche Geschichte");
		list.add("M�dchen");
		list.add("Junge");
		list.add("Dame");
		list.add("Herr");
		list.add("Vater");
		list.add("Sex");
		list.add("Gender");
		list.add("Ursprung");
		list.add("Mutter");
		list.add("Technik");
		list.add("Natur");
		list.add("Terminator");
		list.add("Rambo");
		list.add("Mann");
		list.add("Frau");
		list.add("Rambo");
		list.add("Schon vor 2016 hat mich das Thema besch�ftigt was ich eigentlich so lese. Wie ich feststellte war es viel amerikanische Literatur und vor allem B�cher die M�nner geschrieben hatten. Franzen, Eugenides, Irving, Boyle, Murakami aber auch Suter, Schlink, Arjouni usw. Mir fiel auf das ich wenig Literatur von Frauen las obwohl ich auch hier Favoriten habe wie D�rrie, Hustvedt, Allende, Niffenegger, Lewycka. Dieses Jahr wurde ich wieder f�rmlich auf das Thema gestossen � es ging mehrfach um das weibliche Schreiben in den B�chern die ich las, B�chern von Autorinnen. Es ging vor allem aber auch darum wie Sie hintenanstehn mit ihrem schreiben, als Ehefrauen und M�tter und Assistentinnen des Mannes, mit eigentlich anderen Aufgaben bedacht. Die Ehefrau� hat mir noch einmal nachdr�cklich zu denken gegeben � hier wird davon gesprochen wie die Frauen im Literaturbetrieb in der zweiten Reihe stehen. Und das best�tigt sich f�r mich wenn ich dorthin schaue wo es Wettbewerbe und Preise gibt, Literaturzirkel und andere �ffentlichkeiten. Ich schaue nun auch im Buchladen ein wenig anders hin.");
		list.add("F�r das Herz ist das Leben einfach: Es schl�gt, solange es kann. Dann stoppt es. Fr�her oder sp�ter, an dem einen oder anderen Tag, h�rt seine stampfende Bewegung ganz von alleine auf, und das Blut flie�t zum niedrigsten Punkt des K�rpers, wo es sich in einer kleinen Lache sammelt, von au�en sichtbar als dunkle und feuchte Fl�che unter der best�ndig wei�er werden-den Haut, w�hrend die Temperatur sinkt, die Glieder erstar-ren und die Ged�rme sich entleeren. Diese Ver�nderungen der ersten Stunden geschehen so langsam und werden mit solcher Sicherheit vollzogen, dass ihnen fast etwas Rituelles inne-wohnt, als kapitulierte das Leben festen Regeln folgend, in ei-ner Art gentlemen�s agreement, an das sich auch die Repr�-sentanten des Todes halten, indem sie stets abwarten, bis sich das Leben zur�ckgezogen hat, ehe sie ihre Invasion der neuen Landschaft beginnen. Dann jedoch ist sie unwiderruflich. Die riesigen Bakterienschw�rme, die sich im Inneren des K�rpers ausbreiten, h�lt nichts mehr auf. H�tten sie es nur ein paar Stunden fr�her versucht, w�ren sie augenblicklich auf Wider-stand gesto�en, doch nun ist ringsum alles still, und sie dringen fortw�hrend tiefer in das Feuchte und Dunkle vor. Sie erreichen die Haversschen Kan�le, die Lieberk�hnschen Dr�sen, die Lan-gerhansschen Inseln. Sie erreichen die Bowman-Kapseln in der Niere, die Stilling-Clarkes�sche S�ule im Spinalis, die schwarze Substanz im Mesencephalon. Und sie erreichen das Herz. Noch ist es intakt; aber der Bewegung beraubt, auf die seine gesamte Konstruktion abzielt, wirkt es eigent�mlich verlassen, wie eine Fabrikanlage, zum Beispiel, die von den Arbeitern in Windes-eile ger�umt werden musste, die still liegenden Fuhrwerke, die sich gelb abheben vor dem Dunkel des Waldes, die leer stehen-den Baracken, die Loren an der Seilbahn, die voll beladen, in Reih und Glied, parallel zur Felswand h�ngen.Wenn das Leben den K�rper verl�sst, geh�rt dieser im selben Moment zum Toten. Die Lampen, Koffer, Teppiche, T�rklin-ken, Fenster. Die Felder, Moore, B�che, Berge, Wolken, der Himmel. Nichts von all dem ist uns fremd. Die Gegenst�nde und Ph�nomene der toten Welt umgeben uns kontinuierlich. Dennoch gibt es nur wenige Dinge, die uns unangenehmer be-r�hren, als einen Menschen in ihr gefangen zu sehen, zumin-dest wenn man die M�hen bedenkt, die wir auf uns nehmen, um die toten K�rper unseren Augen zu entziehen. In gr��eren Krankenh�usern werden sie nicht blo� in eigenen, unzug�ng-lichen R�umen vor uns verborgen, nein, auch die Wege dort-hin sind verdeckt, haben eigene Aufz�ge und Kellerg�nge, und selbst wenn man sich zuf�llig in einen von ihnen verirren sollte, sind die toten K�rper, die vorbeigeschoben werden, doch im-mer verh�llt. Sollen sie vom Krankenhaus abtransportiert wer-den, geschieht dies von einem gesonderten Ausgang aus, in Wa-gen mit ru�igen Scheiben; auf dem Friedhofsgel�nde gibt es f�r sie einen eigenen, fensterlosen Raum; bei der Bestattungszere-monie liegen sie in verschlossenen S�rgen, bis sie schlie�lich in die Erde hinabgesenkt oder in �fen verbrannt werden. Es f�llt einem schwer, in dieser Vorgehensweise einen praktischen Sinn zu entdecken. So k�nnten die toten K�rper ebenso gut offen durch die Krankenhausflure geschoben und in einem gew�hn-lichen Taxi abtransportiert werden, ohne eine Gefahr f�r an-dere darzustellen. Der �ltere Mann, der w�hrend eines Kinobe-suchs stirbt, k�nnte genauso gut auf seinem Platz sitzenbleiben, bis der Film vorbei ist und die komplette n�chste Vorstellung noch dazu. Der Lehrer, der auf dem Schulhof einen Hirnschlag erleidet, muss nicht zwingend auf der Stelle weggeschafft wer-den, es passiert nichts Schlimmes, wenn er liegen bleibt, bis der Hausmeister die Zeit findet, sich um ihn zu k�mmern, selbst wenn dies erst am Nachmittag oder Abend der Fall sein sollte. Wenn sich ein Vogel auf ihn setzt und pickt, was macht das schon? Soll das, was ihn im Grab erwartet, besser sein, nur weil wir es nicht sehen? Solange die Toten einem nicht im Weg liegen, besteht kein Grund zur Eile, sie k�nnen ja nicht erneut sterben. Insbesonders winterliche K�ltewellen m�ssten so ge-sehen eigentlich von Vorteil sein. Penner, die auf Parkb�nken und in Hauseing�ngen erfrieren, Selbstm�rder, die von Hoch-h�usern und Br�cken springen, �ltere Frauen, die in Treppen-h�usern ums Leben kommen, Unfallopfer, die in ihren Auto-wracks eingeklemmt sind, der Junge, der nach einem Abend in der Stadt angetrunken in den See f�llt, das kleine M�dchen, das unter die R�der eines Busses ger�t, warum diese Eile, sie unseren Augen zu entziehen? Anstand? Was w�re anst�ndiger, als dass die Eltern des M�dchens es dort ein oder zwei Stun-den sp�ter sehen d�rften, im Schnee neben der Ungl�cksstelle liegend, sowohl ihre blutbesudelten Haare als auch die saubere Steppjacke? Offen f�r die Welt, ohne Geheimnisse, so w�rde sie dort liegen. Doch selbst diese eine Stunde im Schnee ist un-denkbar. Eine Stadt, die ihre Toten nicht aus dem Blickfeld ent-fernt, in der man sie auf Stra�en und Gassen, in Parks und auf Parkpl�tzen liegen sieht, ist keine Stadt, sondern eine H�lle. Dass diese H�lle unsere Lebensbedingungen realistischer und letztlich wahrhaftiger widerspiegelt, spielt keine Rolle. Wir wis-sen, dass es so ist, wollen es aber nicht sehen. Daher r�hrt der kollektive Akt der Verdr�ngung, f�r den das Wegschleusen der Toten ein Ausdruck ist.Was genau verdr�ngt wird, l�sst sich dagegen nicht so leicht sagen. Der Tod an sich kann es nicht sein, dazu ist seine Pr�senz in unserer Gesellschaft zu gro�. Wie viele Tote t�glich in den Zeitungen oder Fernsehnachrichten genannt werden, schwankt den Umst�nden entsprechend etwas, aber auf ein Jahr hochge-rechnet d�rfte die durchschnittliche Zahl einigerma�en kons-tant sein, und da sie auf zahlreiche Informationskan�le verteilt ist, erscheint es praktisch unm�glich, ihr zu entgehen. Dieser Tod wirkt allerdings nicht bedrohlich. Im Gegenteil, er ist et-was, was wir haben m�chten, und wir bezahlen gern, um ihn zu sehen. Nimmt man die immensen Mengen von Tod hinzu, die fiktional produziert werden, f�llt es umso schwerer, das System zu verstehen, das die Toten unserem Blickfeld entzieht. Wenn uns der Tod als Ph�nomen nicht �ngstigt, woher r�hrt dann dieses Unbehagen angesichts der toten K�rper? Es muss entweder bedeuten, dass es zwei Arten von Tod gibt, oder dass ein Widerspruch existiert zwischen unserer Vorstellung vom Tod und dem Tod, wie er in Wahrheit beschaffen ist, was im Grunde auf dasselbe hinausl�uft: Entscheidend ist, dass un-sere Vorstellung von ihm so fest in unserem Bewusstsein veran-kert ist, dass wir nicht nur ersch�ttert sind, wenn wir die Wirk-lichkeit davon abweichen sehen, sondern dies auch mit allen Mitteln zu verbergen suchen. Nicht als Folge einer irgendwie gearteten, bewussten �berlegung, wie es bei Riten geschieht, zum Beispiel der Beerdigung, deren Inhalt und Sinn heutzu-tage verhandelbar sind und somit von der Sph�re des Irratio-nalen in die des Rationalen �berf�hrt, vom Kollektiven zum Individuellen � nein, die Art und Weise, in der wir die Toten entfernen, ist niemals Gegenstand von Diskussionen gewesen, es war schon immer etwas, was wir einfach getan haben, aus einer Notwendigkeit heraus, die keiner begr�nden kann, aber jeder kennt: Stirbt dein Vater an einem st�rmischen Sonntag im Herbst drau�en auf dem Hof, deckst du ihn zumindest zu. Dies ist jedoch nicht der einzige Impuls, der uns im Umgang mit den Toten ereilt. Ebenso auff�llig wie das Verbergen aller Leichen ist die Tatsache, dass sie schnellstm�glich auf Erdbodenniveau gebracht werden. Ein Krankenhaus, das seine Toten nach oben verfrachtet, seine Obduktionss�le und Leichenhallen in den obersten Etagen des Geb�udes unterbringt, ist nahezu undenk-bar. Die Toten bewahrt man m�glichst weit unten auf. Und das gleiche Prinzip wird auf die Firmen �bertragen, die sich ihrer annehmen: eine Versicherung kann ihre R�umlichkeiten getrost in der achten Etage einrichten, ein Beerdigungsinstitut dagegen nicht. Alle Bestatter haben ihre B�ros m�glichst nahe am Erdgeschoss. Woher das kommt, ist schwer zu sagen; man k�nnte versucht sein zu glauben, dass es an einer alten Konven-tion liegt, die urspr�nglich ein praktisches Ziel verfolgte, etwa, dass der Keller kalt war und deshalb am besten zur Aufbewah-rung der Leichen geeignet, und dass dieses Prinzip bis in un-sere Zeit der K�hlschr�nke und K�hlr�ume erhalten blieb, und sollte dies nicht so sein, dass der Gedanke, die Toten in Geb�u-den nach oben zu transportieren, widernat�rlicherscheint, als schl�ssen H�he und Tod einander gegenseitig aus. Als verf�g-ten wir �ber eine Art chtonischen Instinkt, irgendetwas tief in uns, das unsere Toten zu jener Erde hinabf�hren muss, aus der wir gekommen sind.Es mag folglich den Anschein haben, als w�rde der Tod �ber zwei unterschiedliche Systeme vertrieben. Das eine ist mit Ge-heimhaltung und Schwere, Erde und Dunkelheit verkn�pft, das andere mit Offenheit und Leichtigkeit, �ther und Licht. Ein Vater und sein Kind werden get�tet, als der Vater versucht, das Kind in einer Stadt irgendwo im Nahen Osten aus der Schuss-linie zu ziehen, und das Bild der beiden, eng umschlungen, w�h-rend die Kugeln ins Fleisch einschlagen und die K�rper gleich-sam erbeben lassen, wird von einer Kamera eingefangen und zu einem der tausenden Satelliten gesendet, die unseren Pla-neten umkreisen, und von dort auf Fernsehapparate in aller Welt verteilt, wo es sich als ein weiteres Bild von Tod oder Ster-ben in unser Bewusstsein schiebt. Diese Bilder haben kein Ge-wicht, keine Ausdehnung, keine Zeit und keinen Ort und auch keine Verbindung zu den K�rpern, aus denen sie einmal kamen. Sie sind �berall und nirgendwo. Die meisten von ihnen gleiten lediglich durch uns hindurch und verschwinden, einige wenige bleiben aus unterschiedlichen Gr�nden gegenw�rtig und le-ben in der Dunkelheit unseres Gehirns. Eine Abfahrtsl�uferin st�rzt, und die Schlagader in ihrem Oberschenkel wird durch-trennt, Blut str�mt hinter ihr in einer roten Linie den wei�en Hang hinunter, und sie ist bereits tot, noch ehe der K�rper zum Stillstand kommt. Ein Flugzeug hebt ab und beim Auf-steigen der Maschine schlagen Flammen aus den Tragfl�chen, der Himmel �ber den H�usern der Vorstadt ist blau, das Flug-zeug explodiert darunter in einem Feuerball. Ein Fischerboot sinkt eines Abends vor der nordnorwegischen K�ste, die sie-benk�pfige Besatzung ertrinkt, am n�chsten Morgen berichten alle Zeitungen �ber das Ereignis, da es sich um ein so genann-tes Mysterium handelt, das Wetter war ruhig, und das Boot hatte keinen Notruf abgesetzt, es verschwand einfach, was die Fernsehredaktionen am Abend zus�tzlich betonen, indem sie mit einem Hubschrauber die Ungl�cksstelle �berfliegen und Bilder von der leeren See zeigen. Der Himmel ist bew�lkt, die graugr�ne D�nung ruhig und schwer, gleichsam im Besitz ei-nes anderen Temperaments als die j�hen, weithin sch�umenden K�mme, die an manchen Stellen hochschlagen. Ich sitze alleine davor und sehe es, vermutlich irgendwann im Fr�hling, denn mein Vater arbeitet im Garten. Ohne zu h�ren, was der Repor-ter sagt, starre ich auf die Meeresoberfl�che und pl�tzlich tau-chen die Umrisse eines Gesichtes auf. Ich wei� nicht, wie lange es da ist, ein paar Sekunden vielleicht, jedenfalls lange genug, um mich ungeheuer zu beeindrucken. Als das Gesicht verschwindet, stehe ich auf, um jemanden zu suchen, dem ich davon erz�hlen kann. Meine Mutter hat Sp�tdienst, mein Bruder ist bei einem Fu�ballspiel, und die anderen Kinder in unserer Siedlung wollen mir nicht zuh�ren, bleibt also nur Vater, denke ich und eile die Treppe hinunter und laufe ums Haus herum. Wir d�rfen auf un-serem Grundst�ck nicht rennen, weshalb ich, bevor ich in sein Blickfeld gelange, abbremse und gehe. Er steht auf der R�ckseite des Hauses, mitten in dem, was einmal der Gem�segarten wer-den soll, und schl�gt mit einem Vorschlaghammer auf einen Fels-brocken ein. Obwohl die Ausschachtung nur einen Meter tief ist, haben die schwarze, hochgeschaufelte Erde, auf der er steht, und die Gruppe von Vogelbeerb�umen, die gleich jenseits des Zauns hinter ihm wachsen, daf�r gesorgt, dass die Abendd�mmerung dort unten bereits weiter fortgeschritten ist. Als er sich aufrich-tet, liegt sein Gesicht fast vollst�ndig im Dunkeln.Trotzdem verf�ge ich �ber mehr als genug Informationen, um zu wissen, woran ich bei ihm bin. Man erkennt es nicht am Gesichtsausdruck, sondern an seiner K�rperhaltung, und diese deutet man nicht mit Gedanken, sondern intuitiv.Er stellt den Hammer ab, zieht die Handschuhe aus.�Und?�, sagt er.�Ich habe im Fernsehen ein Gesicht im Meer gesehen�, sage ich und bleibe auf dem Rasen �ber ihm stehen. Unser Nachbar hat am fr�hen Nachmittag eine Fichte gef�llt, und der intensive Harzgeruch, den die Holzscheiben verstr�men, die auf der an-deren Seite der Steinmauer lagern, h�ngt in der Luft.�Einen Taucher?�, sagt mein Vater. Er wei�, dass ich mich f�r Taucher interessiere, und kann sich wahrscheinlich nicht vorstellen, dass ich etwas anderes spannend genug finden k�nnte, um zu ihm zu kommen und ihm davon zu erz�hlen.Ich sch�ttele den Kopf. �Es war kein Mensch. Es war eine Art Bild in der See.��Eine Art Bild�, sagt er und zieht die Zigarettenschachtel aus der Tasche auf seiner Hemdbrust.Ich nicke und mache kehrt, um zur�ckzugehen.�Warte mal kurz�, sagt er.Er l�sst ein Streichholz aufflammen und schiebt den Kopf ein wenig vor, um die Zigarette anzuz�nden. Die Flamme gr�bt ein kleines Loch aus Licht in das graue Zwielicht.�So�, sagt er.Nachdem er einen tiefen Zug genommen hat, setzt er einen Fu� auf den Fels und starrt zum Wald auf der anderen Stra�en-seite hin�ber. Vielleicht starrt er aber auch den Himmel �ber den B�umen an.�War das, was du da gesehen hast, ein Bild von Jesus?�, sagt er und sieht zu mir hoch. W�re seine Stimme noch freundlich gewesen und h�tte es die lange Pause vor der Frage nicht gege-ben, h�tte ich angenommen, dass er mich auf den Arm nehmen will. Er findet es ein bisschen peinlich, dass ich gl�ubig bin; sein gr��ter Wunsch ist, dass ich mich nicht von den anderen Kin-dern unterscheide, und unter all den Kindern, von denen die Siedlung nur so wimmelt, gibt es niemanden sonst als seinen j�ngsten Sohn, der sich als Christ bezeichnet.Aber er m�chte es tats�chlich wissen. Ich versp�re einen Anflug von Freude, weil es ihn wirklich interessiert, bin aber auch ein bisschen beleidigt dar�ber, dass er mich so untersch�tzt.Ich sch�ttele den Kopf.�Es war nicht Jesus�, sage ich.�Es freut mich fast, das zu h�ren�, erwidert Vater und l�-chelt. Oben auf dem Hang h�rt man das schwache Wispern von Fahrradreifen auf Asphalt. Das Ger�usch wird schnell lau-ter, und es ist so still in unserer Siedlung, dass der leise, sin-gende Ton, der in dem Rauschen entsteht, klar und deutlich zu h�ren ist, als das Fahrrad im n�chsten Moment auf der Stra�e hinter uns vorbeirollt.Vater zieht noch einmal an seiner Zigarette, wirft sie halb geraucht �ber die Steinmauer, hustet ein paar Mal, zieht die Handschuhe an und greift wieder nach dem Hammer.�Denk nicht mehr daran�, sagt er und blickt zu mir hoch.Ich war an jenem Abend acht, mein Vater drei�ig. Auch wenn ich selbst heute noch nicht behaupten kann, ihn zu verstehen oder zu wissen, was f�r ein Mensch er war, ergibt sich aus der Tatsache, dass ich mittlerweile sieben Jahre �lter bin, als er da-mals war, dass mir einzelne Dinge leichter verst�ndlich erschei-nen. Zum Beispiel, wie gro� der Unterschied zwischen unseren Tagen war. W�hrend meine Tage bis zum Rand mit Sinn ge-f�llt waren und jeder Schritt mir neue M�glichkeiten er�ffnete und jede M�glichkeit mich restlos ausf�llte, und zwar in einer Weise, die mir heute letztlich unverst�ndlich ist, war der Sinn seiner Tage nicht in einzelnen Begebenheiten geb�ndelt, sondern �ber so gro�e Fl�chen verstreut, dass es kaum m�glich ist, ihn mit etwas anderem als abstrakten Begriffen greifbar werden zu lassen. �Familie� war so einer, �Karriere� ein anderer. Wenige oder auch gar keine unvorhergesehenen M�glichkeiten d�rften sich ihm im Laufe seiner Tage geboten haben, er muss immer in groben Z�gen gewusst haben, was sie ihm bringen w�rden und wie er dazu stehen sollte. Er war seit zw�lf Jahren verheiratet, von denen er acht Jahre als Lehrer in einer Gesamtschule ge-arbeitet hatte, er hatte zwei Kinder, ein Haus und ein Auto. Er war in den Gemeinderat gew�hlt worden und sa� als Vertreter der Partei Venstre im Gemeindevorstand. Im Winterhalbjahr besch�ftigte er sich durchaus erfolgreich mit Philatelie, binnen kurzer Zeit war er einer der kundigsten Briefmarkensammler der Region geworden, w�hrend er seine Freizeit im Sommer-halbjahr mit Gartenarbeit verbrachte. Was er an diesem Fr�h-lingsabend dachte, wei� ich nicht, ebenso wenig, welches Bild er von sich hatte, als er sich mit dem Hammer in den H�nden im Zwielicht aufrichtete, aber ich bin mir einigerma�en sicher, dass es in ihm das Gef�hl gab, die Welt, die ihn umgab, recht gut zu verstehen. Er kannte alle Nachbarn in unserer Siedlung und wusste, wo sie im Verh�ltnis zu ihm selbst gesellschaftlich standen, und vermutlich wusste er auch einiges �ber Dinge, die sie lieber f�r sich behalten h�tten, zum einen, weil er ihre Kinder unterrichtete, zum anderen, weil er einen Blick f�r die Schw�chen anderer Menschen hatte. Als Mitglied der neuen, gut ausgebildeten Mittelschicht wusste er zudem viel �ber die gro�e Welt, �ber die ihn Zeitung, Rundfunk und Fernse-hen t�glich auf dem Laufenden hielten. Er wusste einiges �ber Botanik und Zoologie, da er sich in seiner Jugend daf�r inte-ressiert hatte, und auch wenn er in den �brigen naturwissen-schaftlichen F�chern nicht so bewandert zu sein schien, waren ihm doch ihre grundlegenden Prinzipien aus dem Gymnasium bekannt. Besser stand es um seine Kenntnisse in Geschichte, da er das Fach neben Norwegisch und Englisch studiert hatte. Er war mit anderen Worten kein Experte f�r irgendetwas, abgese-hen von P�dagogik vielleicht, konnte jedoch von allem etwas. So gesehen war er ein typischer Lehrer, wohlgemerkt zu einer Zeit, in der es noch mit einem gewissen Status verbunden war, an einer Gesamtschule zu unterrichten. Unser Nachbar hinter der Steinmauer, Prestbakmo, arbeitete als Lehrer an derselben Schule, genau wie der Nachbar, der oberhalb des bewaldeten Hangs hinter dem Haus wohnte, Olsen, w�hrend ein anderer Nachbar, der am anderen Ende der Ringstra�e wohnte, Knud-sen, stellvertretender Direktor an einer anderen Gesamtschule war. Als mein Vater an jenem Fr�hlingsabend Mitte der sieb-ziger Jahre den Vorschlaghammer �ber den Kopf hob und ihn auf den Fels hinabsausen lie�, tat er dies folglich in einer Welt, die er kannte und die ihm vertraut war. Erst als ich selbst in das gleiche Alter kam, begriff ich, dass man daf�r auch einen Preis bezahlt. Wenn der �berblick �ber die Welt gr��er wird, schwin-det nicht nur der Schmerz, den sie verursacht, sondern auch der Sinn. Die Welt zu verstehen hei�t, einen bestimmten Abstand zu ihr einzunehmen. Was zu klein ist, um mit dem blo�en Auge wahrgenommen zu werden, wie Molek�le und Atome, vergr�-�ern wir, und was zu gro� ist, wie Wolkengebilde, Flussdeltas, Sternbilder, verkleinern wir. Wenn wir den Gegenstand so in die Reichweite unserer Sinne gebracht haben, fixieren wir ihn. Das Fixierte nennen wir Wissen. In unserer gesamten Kindheit und Jugend streben wir danach, den korrekten Abstand zu Dingen und Ph�nomenen einzunehmen. Wir lesen, wir lernen, wir er-fahren, wir korrigieren. Dann gelangen wir eines Tages an den Punkt, an dem alle notwendigen Abst�nde bestimmt, alle not-wendigen Systeme etabliert sind. Es ist der Punkt, ab dem die Zeit schneller zu vergehen beginnt. Sie st��t auf keine Hinder-nisse mehr, alles ist festgelegt, die Zeit durchstr�mt unser al-ler Leben, die Tage verschwinden in einem rasenden Tempo, und ehe wir uns versehen, sind wir vierzig, f�nfzig, sechzig ... Sinn erfordert F�lle, F�lle erfordert Zeit, Zeit erfordert Wider-stand. Wissen ist Abstand, Wissen ist Stillstand und der Feind des Sinns. Mein Bild von Vater an jenem Abend 1976 ist mit anderen Worten eine Doppelbelichtung: Einerseits sehe ich ihn, wie ich ihn damals sah, mit den Augen des Achtj�hrigen, unbe-rechenbar und be�ngstigend, andererseits sehe ich ihn als einen Gleichaltrigen, durch dessen Leben die Zeit weht und unabl�s-sig gr��ere St�cke Sinn mit sich rei�t.Der Klang eines Hammers auf Stein hallte durch die Siedlung. Ein Wagen fuhr von der Hauptstra�e kommend den sanften Anstieg herauf, passierte mit eingeschalteten Scheinwerfern. Die T�r des Nachbarhauses �ffnete sich, und Prestbakmo blieb auf der T�rschwelle stehen und zog sich Arbeitshandschuhe an, w�hrend er gleichzeitig die klare Abendluft einsog, ehe er die Schubkarre nahm und diese vor sich herschiebend �ber den Rasen ging. Es roch nach Pulver von dem Fels, auf den Vater einschlug, nach Fichte von den Holzkl�tzen hinter der Mauer, nach frisch umgegrabener Erde und Wald, und in der schwa-chen Brise aus Norden hing der Duft von Salz. Ich dachte an das Gesicht, das ich im Meer gesehen hatte. Obwohl nur we-nige Minuten vergangen waren, seit es mir zuletzt in den Sinn gekommen war, hatte es sich bereits ver�ndert. Jetzt sah ich das Gesicht meines Vaters.Unten in der Senke h�rte er auf zu schlagen.�Stehst du da immer noch herum, Junge?�Ich nickte.�Nun geh schon rein.�Ich setzte mich in Bewegung.�Und du?�, sagte er.Ich blieb stehen und drehte mich fragend zu ihm um.�Diesmal wird nicht gerannt.�Ich starrte ihn an. Woher wusste er, dass ich gelaufen war?�Und mach den Mund zu, es zieht�, sagte er. �Du siehst aus wie ein Idiot.�Ich gehorchte, schloss den Mund und ging langsam um das Haus herum. Als ich zur Vorderseite kam, war die Stra�e voller Kinder. Die �ltesten standen in einer Traube zusammen, auf Fahrr�dern, die in der D�mmerung wirkten, als w�ren sie Teil ihrer K�rper. Die j�ngsten spielten Verstecken. Wer gefangen worden war, stand in einem Kreidekreis auf dem B�rgersteig, die anderen lagen ringsum im Wald unterhalb der Stra�e ver-steckt, f�r den Suchenden, der gleichzeitig die bereits Gefange-nen bewachen musste, nicht zu sehen, wohl aber f�r mich.�ber den schwarzen Baumwipfeln leuchteten rot die Lichter an den Br�ckenpfeilern. Auf dem Anstieg n�herte sich erneut ein Auto. Im Licht der Scheinwerfer wurden erst die Fahrrad-fahrer deutlich sichtbar, ein kurzes Aufblitzen von Reflektoren, Metall, Steppjacken, schwarzen Augen und wei�en Gesichtern, danach die spielenden Kinder, die nur den notwendigen Schritt seitlich ausgewichen waren, damit das Auto passieren konnte, und nun geisterhaft dastanden und es anstarrten.Es war das Ehepaar Trollnes, die Eltern von Sverre, einem Jungen aus meiner Klasse. Er schien nicht bei ihnen zu sein.Ich wandte mich um und sah den roten R�cklichtern nach, bis sie �ber die H�gelkuppe verschwanden. Dann ging ich hi-nein. Eine Weile versuchte ich auf dem Bett liegend zu lesen, aber es wollte sich nicht die n�tige Ruhe einstellen, so dass ich stattdessen in Yngves Zimmer trottete, von wo aus ich auf Vater hinabschauen konnte. Wenn ich ihn sah, wusste ich, wo er war, und im Grunde war diese Gewissheit das wichtigste. Ich kannte seine Launen und hatte l�ngst gelernt, sie mit Hilfe eines unterbewussten Kategorisierungssystems vorherzusehen, wie mir sp�ter klar wurde, bei dem das Verh�ltnis zwischen wenigen festen Gr��en ausreichte, um zu entscheiden, was mich erwartete, so dass ich die n�tigen Vorkehrungen treffen konnte. Eine Art Meteorologie des Gem�ts ... Die Geschwin-digkeit des Wagens auf dem sanften Anstieg zu unserem Haus, die Zeit, die er ben�tigte, um den Motor auszuschalten, seine Sachen zu packen und auszusteigen, die Art, wie er sich um-sah, wenn er die Autot�r abschloss, die Nuancen in den un-terschiedlichen Lauten, die aus dem Flur hochdrangen, wenn er den Mantel ablegte � das alles waren Zeichen, das alles lie� sich deuten. Erg�nzt wurde es durch Informationen dar�ber, wo er und wie lange er und mit wem er zusammen gewesen war, ehe die Schlussfolgerung, der einzige Teil des Prozesses, den ich bewusst wahrnahm, gezogen wurde. Am meisten f�rchtete ich mich deshalb, wenn er einfach kam... Wenn ich aus ir-gendeinem Grund unaufmerksamgewesen war ...Woher in aller Welt hatte er gewusst, dass ich gelaufen war?Es war nicht das erste Mal, dass er mich in unerkl�rlicher Weise ertappt hatte. So hatte ich an einem Herbstabend eine T�te S��igkeiten unter dem Plumeau meines Betts versteckt, weil ich schon ahnte, dass er in mein Zimmer kommen und mir nie und nimmer glauben w�rde, wie ich an das Geld geraten war, um sie mir zu kaufen. Als er wie erwartet hereinkam, sah er mich einige Sekunden an.�Was hast du da im Bett versteckt?�, sagte er.Woher wussteer das?Drau�en schraubte Prestbakmo die starke Gl�hlampe fest, die �ber der Platte angebracht war, an der er regelm��ig arbeitete. Das neue Auge aus Licht, das aus der Dunkelheit hervorstach, war voller Sachen und Dinge, die Prestbakmo stehend, vollkom-men reglos anstarrte. Stapelweise Farbt�pfe, Gl�ser mit Pin-seln, Holzkl�tze, Bretterenden, zusammengelegte Planen, Auto-reifen, ein Fahrradrahmen, ein paar Werkzeugk�sten, Boxen voller Schrauben und N�gel in allen Gr��en und Formen, Bret-ter mit Milcht�ten voller �ppig sprie�ender Blumen, S�cke mit Kalk, ein aufgerollter Gartenschlauch und an die Wand gelehnt eine Platte, auf der sich alle m�glichen Werkzeuge abzeichneten, wahrscheinlich war sie f�r den Hobbykeller im Haus gedacht.");
		list.add("Brust");
		list.add("Br�ste");
		list.add("Penis");
		list.add("Vagina");
		list.add("Hoden");
		list.add("Uterus");
		
	
		
		
		list.add("Donald Trump");
		
		list.add("Angela Merkel");
		
		list.add("Das B�se");
		
		list.add("Das Gute");
		
list.add("Liebe");
		
		list.add("Hass");
		

		
list.add("Sehr geehrte Damen und Herren");
		
		list.add("Zerst�rung");
		
list.add("Sch�pfung");
		
		list.add("Erhaltung");
		
		list.add("M�nnlich");
		
		list.add("Weiblich");
		
		list.add("Daher fordere ich, wie so viele vor mir, eine Abschaffung der Geschlechter.");
		
		
		for(String ratedWord: list)
		{
			//System.out.println(ratedWord+":     nur links/rechts["+ratePhrase(ratedWord)+"] rechts/rechter["+ratePhraseDistance(ratedWord)+"]");
			System.out.println(ratedWord+":     ["+binarySexOfPhrase(ratedWord)+"] ");
			
		
		}
		//System.out.println(ratedWord+": nur links/rechts["+ratePhrase(ratedWord)+"] rechts/rechter["+ratePhraseDistance(ratedWord)+"]");
	}

}
