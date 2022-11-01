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
		if(c=='ö')
		{
			return 5;
		}
		if(c=='ß')
		{
			return 6;
		}
		if(c=='ü')
		{
			return 6;
		}
		if(c=='ä')
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
		if(c=='ö')
		{
			return 1;
		}
		if(c=='ß')
		{
			return 1;
		}
		if(c=='ü')
		{
			return 1;
		}
		if(c=='ä')
		{
			return 1;
		}
		return 0;
	}
//	weiblich 15 1
//	BDOÖS 5
//	cos  5
//	CUÜ	  3
//	mnuü  4
//	380
	
//neutral 14 0
//	HKX MNVWZ 9 
//	xk vwz	  5
//  	
//männlich 14 -1
//	EFIJLTY    7 
//	fijlrty	  7
//  17	
	
//	gemischt 15 -1+1=0
//	AÄPQRG	  6 
//	aäbdempqgß  9
//  24569
	public static String binarySexOfPhrase(String phrase)
	{
		double value=sexOfPhrase(phrase);
		if(value>0) return "weiblich"/*+value*/;
		else if(value<0) return "männlich"/*+value*/;
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
		if(c=='Ö')
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
		if(c=='Ü')
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
		if(c=='ü')
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
		list.add("Faustinas Küsse");
		list.add("Das Joshua-Profil");
		list.add("Noah");
		list.add("Der Nachtwandler");
		list.add("Abgeschnitten ");
		list.add("Nicht einschlafen");
		list.add("Faust");
		list.add("Faust. Der Tragödie zweiter Teil");
		list.add("Die Leiden des jungen Werthers");
		list.add("lol");
		list.add("omg");
		list.add("rofl");
		list.add("Ein Hologramm für den König");
		list.add("Das wetter in geschlossenen Räumen");
		list.add("Tatort: im gelobten Land");
		list.add("Ich ändere das Öl");
		list.add("Ölnot in Polska");
		list.add("Er ist wieder da");
		list.add("Ein ganzes halbes Jahr");
		list.add("Bretonische Flut");
		list.add("Die dunklen Krieger");
		list.add("Wenn du mich sehen könntest");
		list.add("Eine zufällige Liebe");
		list.add("Auf der Liste");
		list.add("Die Erinnerung so kalt");
		list.add("Der Fremde neben dir");
		list.add("Gott");
		list.add("Teufel");
		list.add("Die unendliche Geschichte");
		list.add("Mädchen");
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
		list.add("Schon vor 2016 hat mich das Thema beschäftigt was ich eigentlich so lese. Wie ich feststellte war es viel amerikanische Literatur und vor allem Bücher die Männer geschrieben hatten. Franzen, Eugenides, Irving, Boyle, Murakami aber auch Suter, Schlink, Arjouni usw. Mir fiel auf das ich wenig Literatur von Frauen las obwohl ich auch hier Favoriten habe wie Dörrie, Hustvedt, Allende, Niffenegger, Lewycka. Dieses Jahr wurde ich wieder förmlich auf das Thema gestossen – es ging mehrfach um das weibliche Schreiben in den Büchern die ich las, Büchern von Autorinnen. Es ging vor allem aber auch darum wie Sie hintenanstehn mit ihrem schreiben, als Ehefrauen und Mütter und Assistentinnen des Mannes, mit eigentlich anderen Aufgaben bedacht. Die Ehefrau“ hat mir noch einmal nachdrücklich zu denken gegeben – hier wird davon gesprochen wie die Frauen im Literaturbetrieb in der zweiten Reihe stehen. Und das bestätigt sich für mich wenn ich dorthin schaue wo es Wettbewerbe und Preise gibt, Literaturzirkel und andere Öffentlichkeiten. Ich schaue nun auch im Buchladen ein wenig anders hin.");
		list.add("Für das Herz ist das Leben einfach: Es schlägt, solange es kann. Dann stoppt es. Früher oder später, an dem einen oder anderen Tag, hört seine stampfende Bewegung ganz von alleine auf, und das Blut fließt zum niedrigsten Punkt des Körpers, wo es sich in einer kleinen Lache sammelt, von außen sichtbar als dunkle und feuchte Fläche unter der beständig weißer werden-den Haut, während die Temperatur sinkt, die Glieder erstar-ren und die Gedärme sich entleeren. Diese Veränderungen der ersten Stunden geschehen so langsam und werden mit solcher Sicherheit vollzogen, dass ihnen fast etwas Rituelles inne-wohnt, als kapitulierte das Leben festen Regeln folgend, in ei-ner Art gentlemen’s agreement, an das sich auch die Reprä-sentanten des Todes halten, indem sie stets abwarten, bis sich das Leben zurückgezogen hat, ehe sie ihre Invasion der neuen Landschaft beginnen. Dann jedoch ist sie unwiderruflich. Die riesigen Bakterienschwärme, die sich im Inneren des Körpers ausbreiten, hält nichts mehr auf. Hätten sie es nur ein paar Stunden früher versucht, wären sie augenblicklich auf Wider-stand gestoßen, doch nun ist ringsum alles still, und sie dringen fortwährend tiefer in das Feuchte und Dunkle vor. Sie erreichen die Haversschen Kanäle, die Lieberkühnschen Drüsen, die Lan-gerhansschen Inseln. Sie erreichen die Bowman-Kapseln in der Niere, die Stilling-Clarkes’sche Säule im Spinalis, die schwarze Substanz im Mesencephalon. Und sie erreichen das Herz. Noch ist es intakt; aber der Bewegung beraubt, auf die seine gesamte Konstruktion abzielt, wirkt es eigentümlich verlassen, wie eine Fabrikanlage, zum Beispiel, die von den Arbeitern in Windes-eile geräumt werden musste, die still liegenden Fuhrwerke, die sich gelb abheben vor dem Dunkel des Waldes, die leer stehen-den Baracken, die Loren an der Seilbahn, die voll beladen, in Reih und Glied, parallel zur Felswand hängen.Wenn das Leben den Körper verlässt, gehört dieser im selben Moment zum Toten. Die Lampen, Koffer, Teppiche, Türklin-ken, Fenster. Die Felder, Moore, Bäche, Berge, Wolken, der Himmel. Nichts von all dem ist uns fremd. Die Gegenstände und Phänomene der toten Welt umgeben uns kontinuierlich. Dennoch gibt es nur wenige Dinge, die uns unangenehmer be-rühren, als einen Menschen in ihr gefangen zu sehen, zumin-dest wenn man die Mühen bedenkt, die wir auf uns nehmen, um die toten Körper unseren Augen zu entziehen. In größeren Krankenhäusern werden sie nicht bloß in eigenen, unzugäng-lichen Räumen vor uns verborgen, nein, auch die Wege dort-hin sind verdeckt, haben eigene Aufzüge und Kellergänge, und selbst wenn man sich zufällig in einen von ihnen verirren sollte, sind die toten Körper, die vorbeigeschoben werden, doch im-mer verhüllt. Sollen sie vom Krankenhaus abtransportiert wer-den, geschieht dies von einem gesonderten Ausgang aus, in Wa-gen mit rußigen Scheiben; auf dem Friedhofsgelände gibt es für sie einen eigenen, fensterlosen Raum; bei der Bestattungszere-monie liegen sie in verschlossenen Särgen, bis sie schließlich in die Erde hinabgesenkt oder in Öfen verbrannt werden. Es fällt einem schwer, in dieser Vorgehensweise einen praktischen Sinn zu entdecken. So könnten die toten Körper ebenso gut offen durch die Krankenhausflure geschoben und in einem gewöhn-lichen Taxi abtransportiert werden, ohne eine Gefahr für an-dere darzustellen. Der ältere Mann, der während eines Kinobe-suchs stirbt, könnte genauso gut auf seinem Platz sitzenbleiben, bis der Film vorbei ist und die komplette nächste Vorstellung noch dazu. Der Lehrer, der auf dem Schulhof einen Hirnschlag erleidet, muss nicht zwingend auf der Stelle weggeschafft wer-den, es passiert nichts Schlimmes, wenn er liegen bleibt, bis der Hausmeister die Zeit findet, sich um ihn zu kümmern, selbst wenn dies erst am Nachmittag oder Abend der Fall sein sollte. Wenn sich ein Vogel auf ihn setzt und pickt, was macht das schon? Soll das, was ihn im Grab erwartet, besser sein, nur weil wir es nicht sehen? Solange die Toten einem nicht im Weg liegen, besteht kein Grund zur Eile, sie können ja nicht erneut sterben. Insbesonders winterliche Kältewellen müssten so ge-sehen eigentlich von Vorteil sein. Penner, die auf Parkbänken und in Hauseingängen erfrieren, Selbstmörder, die von Hoch-häusern und Brücken springen, ältere Frauen, die in Treppen-häusern ums Leben kommen, Unfallopfer, die in ihren Auto-wracks eingeklemmt sind, der Junge, der nach einem Abend in der Stadt angetrunken in den See fällt, das kleine Mädchen, das unter die Räder eines Busses gerät, warum diese Eile, sie unseren Augen zu entziehen? Anstand? Was wäre anständiger, als dass die Eltern des Mädchens es dort ein oder zwei Stun-den später sehen dürften, im Schnee neben der Unglücksstelle liegend, sowohl ihre blutbesudelten Haare als auch die saubere Steppjacke? Offen für die Welt, ohne Geheimnisse, so würde sie dort liegen. Doch selbst diese eine Stunde im Schnee ist un-denkbar. Eine Stadt, die ihre Toten nicht aus dem Blickfeld ent-fernt, in der man sie auf Straßen und Gassen, in Parks und auf Parkplätzen liegen sieht, ist keine Stadt, sondern eine Hölle. Dass diese Hölle unsere Lebensbedingungen realistischer und letztlich wahrhaftiger widerspiegelt, spielt keine Rolle. Wir wis-sen, dass es so ist, wollen es aber nicht sehen. Daher rührt der kollektive Akt der Verdrängung, für den das Wegschleusen der Toten ein Ausdruck ist.Was genau verdrängt wird, lässt sich dagegen nicht so leicht sagen. Der Tod an sich kann es nicht sein, dazu ist seine Präsenz in unserer Gesellschaft zu groß. Wie viele Tote täglich in den Zeitungen oder Fernsehnachrichten genannt werden, schwankt den Umständen entsprechend etwas, aber auf ein Jahr hochge-rechnet dürfte die durchschnittliche Zahl einigermaßen kons-tant sein, und da sie auf zahlreiche Informationskanäle verteilt ist, erscheint es praktisch unmöglich, ihr zu entgehen. Dieser Tod wirkt allerdings nicht bedrohlich. Im Gegenteil, er ist et-was, was wir haben möchten, und wir bezahlen gern, um ihn zu sehen. Nimmt man die immensen Mengen von Tod hinzu, die fiktional produziert werden, fällt es umso schwerer, das System zu verstehen, das die Toten unserem Blickfeld entzieht. Wenn uns der Tod als Phänomen nicht ängstigt, woher rührt dann dieses Unbehagen angesichts der toten Körper? Es muss entweder bedeuten, dass es zwei Arten von Tod gibt, oder dass ein Widerspruch existiert zwischen unserer Vorstellung vom Tod und dem Tod, wie er in Wahrheit beschaffen ist, was im Grunde auf dasselbe hinausläuft: Entscheidend ist, dass un-sere Vorstellung von ihm so fest in unserem Bewusstsein veran-kert ist, dass wir nicht nur erschüttert sind, wenn wir die Wirk-lichkeit davon abweichen sehen, sondern dies auch mit allen Mitteln zu verbergen suchen. Nicht als Folge einer irgendwie gearteten, bewussten Überlegung, wie es bei Riten geschieht, zum Beispiel der Beerdigung, deren Inhalt und Sinn heutzu-tage verhandelbar sind und somit von der Sphäre des Irratio-nalen in die des Rationalen überführt, vom Kollektiven zum Individuellen – nein, die Art und Weise, in der wir die Toten entfernen, ist niemals Gegenstand von Diskussionen gewesen, es war schon immer etwas, was wir einfach getan haben, aus einer Notwendigkeit heraus, die keiner begründen kann, aber jeder kennt: Stirbt dein Vater an einem stürmischen Sonntag im Herbst draußen auf dem Hof, deckst du ihn zumindest zu. Dies ist jedoch nicht der einzige Impuls, der uns im Umgang mit den Toten ereilt. Ebenso auffällig wie das Verbergen aller Leichen ist die Tatsache, dass sie schnellstmöglich auf Erdbodenniveau gebracht werden. Ein Krankenhaus, das seine Toten nach oben verfrachtet, seine Obduktionssäle und Leichenhallen in den obersten Etagen des Gebäudes unterbringt, ist nahezu undenk-bar. Die Toten bewahrt man möglichst weit unten auf. Und das gleiche Prinzip wird auf die Firmen übertragen, die sich ihrer annehmen: eine Versicherung kann ihre Räumlichkeiten getrost in der achten Etage einrichten, ein Beerdigungsinstitut dagegen nicht. Alle Bestatter haben ihre Büros möglichst nahe am Erdgeschoss. Woher das kommt, ist schwer zu sagen; man könnte versucht sein zu glauben, dass es an einer alten Konven-tion liegt, die ursprünglich ein praktisches Ziel verfolgte, etwa, dass der Keller kalt war und deshalb am besten zur Aufbewah-rung der Leichen geeignet, und dass dieses Prinzip bis in un-sere Zeit der Kühlschränke und Kühlräume erhalten blieb, und sollte dies nicht so sein, dass der Gedanke, die Toten in Gebäu-den nach oben zu transportieren, widernatürlicherscheint, als schlössen Höhe und Tod einander gegenseitig aus. Als verfüg-ten wir über eine Art chtonischen Instinkt, irgendetwas tief in uns, das unsere Toten zu jener Erde hinabführen muss, aus der wir gekommen sind.Es mag folglich den Anschein haben, als würde der Tod über zwei unterschiedliche Systeme vertrieben. Das eine ist mit Ge-heimhaltung und Schwere, Erde und Dunkelheit verknüpft, das andere mit Offenheit und Leichtigkeit, Äther und Licht. Ein Vater und sein Kind werden getötet, als der Vater versucht, das Kind in einer Stadt irgendwo im Nahen Osten aus der Schuss-linie zu ziehen, und das Bild der beiden, eng umschlungen, wäh-rend die Kugeln ins Fleisch einschlagen und die Körper gleich-sam erbeben lassen, wird von einer Kamera eingefangen und zu einem der tausenden Satelliten gesendet, die unseren Pla-neten umkreisen, und von dort auf Fernsehapparate in aller Welt verteilt, wo es sich als ein weiteres Bild von Tod oder Ster-ben in unser Bewusstsein schiebt. Diese Bilder haben kein Ge-wicht, keine Ausdehnung, keine Zeit und keinen Ort und auch keine Verbindung zu den Körpern, aus denen sie einmal kamen. Sie sind überall und nirgendwo. Die meisten von ihnen gleiten lediglich durch uns hindurch und verschwinden, einige wenige bleiben aus unterschiedlichen Gründen gegenwärtig und le-ben in der Dunkelheit unseres Gehirns. Eine Abfahrtsläuferin stürzt, und die Schlagader in ihrem Oberschenkel wird durch-trennt, Blut strömt hinter ihr in einer roten Linie den weißen Hang hinunter, und sie ist bereits tot, noch ehe der Körper zum Stillstand kommt. Ein Flugzeug hebt ab und beim Auf-steigen der Maschine schlagen Flammen aus den Tragflächen, der Himmel über den Häusern der Vorstadt ist blau, das Flug-zeug explodiert darunter in einem Feuerball. Ein Fischerboot sinkt eines Abends vor der nordnorwegischen Küste, die sie-benköpfige Besatzung ertrinkt, am nächsten Morgen berichten alle Zeitungen über das Ereignis, da es sich um ein so genann-tes Mysterium handelt, das Wetter war ruhig, und das Boot hatte keinen Notruf abgesetzt, es verschwand einfach, was die Fernsehredaktionen am Abend zusätzlich betonen, indem sie mit einem Hubschrauber die Unglücksstelle überfliegen und Bilder von der leeren See zeigen. Der Himmel ist bewölkt, die graugrüne Dünung ruhig und schwer, gleichsam im Besitz ei-nes anderen Temperaments als die jähen, weithin schäumenden Kämme, die an manchen Stellen hochschlagen. Ich sitze alleine davor und sehe es, vermutlich irgendwann im Frühling, denn mein Vater arbeitet im Garten. Ohne zu hören, was der Repor-ter sagt, starre ich auf die Meeresoberfläche und plötzlich tau-chen die Umrisse eines Gesichtes auf. Ich weiß nicht, wie lange es da ist, ein paar Sekunden vielleicht, jedenfalls lange genug, um mich ungeheuer zu beeindrucken. Als das Gesicht verschwindet, stehe ich auf, um jemanden zu suchen, dem ich davon erzählen kann. Meine Mutter hat Spätdienst, mein Bruder ist bei einem Fußballspiel, und die anderen Kinder in unserer Siedlung wollen mir nicht zuhören, bleibt also nur Vater, denke ich und eile die Treppe hinunter und laufe ums Haus herum. Wir dürfen auf un-serem Grundstück nicht rennen, weshalb ich, bevor ich in sein Blickfeld gelange, abbremse und gehe. Er steht auf der Rückseite des Hauses, mitten in dem, was einmal der Gemüsegarten wer-den soll, und schlägt mit einem Vorschlaghammer auf einen Fels-brocken ein. Obwohl die Ausschachtung nur einen Meter tief ist, haben die schwarze, hochgeschaufelte Erde, auf der er steht, und die Gruppe von Vogelbeerbäumen, die gleich jenseits des Zauns hinter ihm wachsen, dafür gesorgt, dass die Abenddämmerung dort unten bereits weiter fortgeschritten ist. Als er sich aufrich-tet, liegt sein Gesicht fast vollständig im Dunkeln.Trotzdem verfüge ich über mehr als genug Informationen, um zu wissen, woran ich bei ihm bin. Man erkennt es nicht am Gesichtsausdruck, sondern an seiner Körperhaltung, und diese deutet man nicht mit Gedanken, sondern intuitiv.Er stellt den Hammer ab, zieht die Handschuhe aus.»Und?«, sagt er.»Ich habe im Fernsehen ein Gesicht im Meer gesehen«, sage ich und bleibe auf dem Rasen über ihm stehen. Unser Nachbar hat am frühen Nachmittag eine Fichte gefällt, und der intensive Harzgeruch, den die Holzscheiben verströmen, die auf der an-deren Seite der Steinmauer lagern, hängt in der Luft.»Einen Taucher?«, sagt mein Vater. Er weiß, dass ich mich für Taucher interessiere, und kann sich wahrscheinlich nicht vorstellen, dass ich etwas anderes spannend genug finden könnte, um zu ihm zu kommen und ihm davon zu erzählen.Ich schüttele den Kopf. »Es war kein Mensch. Es war eine Art Bild in der See.«»Eine Art Bild«, sagt er und zieht die Zigarettenschachtel aus der Tasche auf seiner Hemdbrust.Ich nicke und mache kehrt, um zurückzugehen.»Warte mal kurz«, sagt er.Er lässt ein Streichholz aufflammen und schiebt den Kopf ein wenig vor, um die Zigarette anzuzünden. Die Flamme gräbt ein kleines Loch aus Licht in das graue Zwielicht.»So«, sagt er.Nachdem er einen tiefen Zug genommen hat, setzt er einen Fuß auf den Fels und starrt zum Wald auf der anderen Straßen-seite hinüber. Vielleicht starrt er aber auch den Himmel über den Bäumen an.»War das, was du da gesehen hast, ein Bild von Jesus?«, sagt er und sieht zu mir hoch. Wäre seine Stimme noch freundlich gewesen und hätte es die lange Pause vor der Frage nicht gege-ben, hätte ich angenommen, dass er mich auf den Arm nehmen will. Er findet es ein bisschen peinlich, dass ich gläubig bin; sein größter Wunsch ist, dass ich mich nicht von den anderen Kin-dern unterscheide, und unter all den Kindern, von denen die Siedlung nur so wimmelt, gibt es niemanden sonst als seinen jüngsten Sohn, der sich als Christ bezeichnet.Aber er möchte es tatsächlich wissen. Ich verspüre einen Anflug von Freude, weil es ihn wirklich interessiert, bin aber auch ein bisschen beleidigt darüber, dass er mich so unterschätzt.Ich schüttele den Kopf.»Es war nicht Jesus«, sage ich.»Es freut mich fast, das zu hören«, erwidert Vater und lä-chelt. Oben auf dem Hang hört man das schwache Wispern von Fahrradreifen auf Asphalt. Das Geräusch wird schnell lau-ter, und es ist so still in unserer Siedlung, dass der leise, sin-gende Ton, der in dem Rauschen entsteht, klar und deutlich zu hören ist, als das Fahrrad im nächsten Moment auf der Straße hinter uns vorbeirollt.Vater zieht noch einmal an seiner Zigarette, wirft sie halb geraucht über die Steinmauer, hustet ein paar Mal, zieht die Handschuhe an und greift wieder nach dem Hammer.»Denk nicht mehr daran«, sagt er und blickt zu mir hoch.Ich war an jenem Abend acht, mein Vater dreißig. Auch wenn ich selbst heute noch nicht behaupten kann, ihn zu verstehen oder zu wissen, was für ein Mensch er war, ergibt sich aus der Tatsache, dass ich mittlerweile sieben Jahre älter bin, als er da-mals war, dass mir einzelne Dinge leichter verständlich erschei-nen. Zum Beispiel, wie groß der Unterschied zwischen unseren Tagen war. Während meine Tage bis zum Rand mit Sinn ge-füllt waren und jeder Schritt mir neue Möglichkeiten eröffnete und jede Möglichkeit mich restlos ausfüllte, und zwar in einer Weise, die mir heute letztlich unverständlich ist, war der Sinn seiner Tage nicht in einzelnen Begebenheiten gebündelt, sondern über so große Flächen verstreut, dass es kaum möglich ist, ihn mit etwas anderem als abstrakten Begriffen greifbar werden zu lassen. »Familie« war so einer, »Karriere« ein anderer. Wenige oder auch gar keine unvorhergesehenen Möglichkeiten dürften sich ihm im Laufe seiner Tage geboten haben, er muss immer in groben Zügen gewusst haben, was sie ihm bringen würden und wie er dazu stehen sollte. Er war seit zwölf Jahren verheiratet, von denen er acht Jahre als Lehrer in einer Gesamtschule ge-arbeitet hatte, er hatte zwei Kinder, ein Haus und ein Auto. Er war in den Gemeinderat gewählt worden und saß als Vertreter der Partei Venstre im Gemeindevorstand. Im Winterhalbjahr beschäftigte er sich durchaus erfolgreich mit Philatelie, binnen kurzer Zeit war er einer der kundigsten Briefmarkensammler der Region geworden, während er seine Freizeit im Sommer-halbjahr mit Gartenarbeit verbrachte. Was er an diesem Früh-lingsabend dachte, weiß ich nicht, ebenso wenig, welches Bild er von sich hatte, als er sich mit dem Hammer in den Händen im Zwielicht aufrichtete, aber ich bin mir einigermaßen sicher, dass es in ihm das Gefühl gab, die Welt, die ihn umgab, recht gut zu verstehen. Er kannte alle Nachbarn in unserer Siedlung und wusste, wo sie im Verhältnis zu ihm selbst gesellschaftlich standen, und vermutlich wusste er auch einiges über Dinge, die sie lieber für sich behalten hätten, zum einen, weil er ihre Kinder unterrichtete, zum anderen, weil er einen Blick für die Schwächen anderer Menschen hatte. Als Mitglied der neuen, gut ausgebildeten Mittelschicht wusste er zudem viel über die große Welt, über die ihn Zeitung, Rundfunk und Fernse-hen täglich auf dem Laufenden hielten. Er wusste einiges über Botanik und Zoologie, da er sich in seiner Jugend dafür inte-ressiert hatte, und auch wenn er in den übrigen naturwissen-schaftlichen Fächern nicht so bewandert zu sein schien, waren ihm doch ihre grundlegenden Prinzipien aus dem Gymnasium bekannt. Besser stand es um seine Kenntnisse in Geschichte, da er das Fach neben Norwegisch und Englisch studiert hatte. Er war mit anderen Worten kein Experte für irgendetwas, abgese-hen von Pädagogik vielleicht, konnte jedoch von allem etwas. So gesehen war er ein typischer Lehrer, wohlgemerkt zu einer Zeit, in der es noch mit einem gewissen Status verbunden war, an einer Gesamtschule zu unterrichten. Unser Nachbar hinter der Steinmauer, Prestbakmo, arbeitete als Lehrer an derselben Schule, genau wie der Nachbar, der oberhalb des bewaldeten Hangs hinter dem Haus wohnte, Olsen, während ein anderer Nachbar, der am anderen Ende der Ringstraße wohnte, Knud-sen, stellvertretender Direktor an einer anderen Gesamtschule war. Als mein Vater an jenem Frühlingsabend Mitte der sieb-ziger Jahre den Vorschlaghammer über den Kopf hob und ihn auf den Fels hinabsausen ließ, tat er dies folglich in einer Welt, die er kannte und die ihm vertraut war. Erst als ich selbst in das gleiche Alter kam, begriff ich, dass man dafür auch einen Preis bezahlt. Wenn der Überblick über die Welt größer wird, schwin-det nicht nur der Schmerz, den sie verursacht, sondern auch der Sinn. Die Welt zu verstehen heißt, einen bestimmten Abstand zu ihr einzunehmen. Was zu klein ist, um mit dem bloßen Auge wahrgenommen zu werden, wie Moleküle und Atome, vergrö-ßern wir, und was zu groß ist, wie Wolkengebilde, Flussdeltas, Sternbilder, verkleinern wir. Wenn wir den Gegenstand so in die Reichweite unserer Sinne gebracht haben, fixieren wir ihn. Das Fixierte nennen wir Wissen. In unserer gesamten Kindheit und Jugend streben wir danach, den korrekten Abstand zu Dingen und Phänomenen einzunehmen. Wir lesen, wir lernen, wir er-fahren, wir korrigieren. Dann gelangen wir eines Tages an den Punkt, an dem alle notwendigen Abstände bestimmt, alle not-wendigen Systeme etabliert sind. Es ist der Punkt, ab dem die Zeit schneller zu vergehen beginnt. Sie stößt auf keine Hinder-nisse mehr, alles ist festgelegt, die Zeit durchströmt unser al-ler Leben, die Tage verschwinden in einem rasenden Tempo, und ehe wir uns versehen, sind wir vierzig, fünfzig, sechzig ... Sinn erfordert Fülle, Fülle erfordert Zeit, Zeit erfordert Wider-stand. Wissen ist Abstand, Wissen ist Stillstand und der Feind des Sinns. Mein Bild von Vater an jenem Abend 1976 ist mit anderen Worten eine Doppelbelichtung: Einerseits sehe ich ihn, wie ich ihn damals sah, mit den Augen des Achtjährigen, unbe-rechenbar und beängstigend, andererseits sehe ich ihn als einen Gleichaltrigen, durch dessen Leben die Zeit weht und unabläs-sig größere Stücke Sinn mit sich reißt.Der Klang eines Hammers auf Stein hallte durch die Siedlung. Ein Wagen fuhr von der Hauptstraße kommend den sanften Anstieg herauf, passierte mit eingeschalteten Scheinwerfern. Die Tür des Nachbarhauses öffnete sich, und Prestbakmo blieb auf der Türschwelle stehen und zog sich Arbeitshandschuhe an, während er gleichzeitig die klare Abendluft einsog, ehe er die Schubkarre nahm und diese vor sich herschiebend über den Rasen ging. Es roch nach Pulver von dem Fels, auf den Vater einschlug, nach Fichte von den Holzklötzen hinter der Mauer, nach frisch umgegrabener Erde und Wald, und in der schwa-chen Brise aus Norden hing der Duft von Salz. Ich dachte an das Gesicht, das ich im Meer gesehen hatte. Obwohl nur we-nige Minuten vergangen waren, seit es mir zuletzt in den Sinn gekommen war, hatte es sich bereits verändert. Jetzt sah ich das Gesicht meines Vaters.Unten in der Senke hörte er auf zu schlagen.»Stehst du da immer noch herum, Junge?«Ich nickte.»Nun geh schon rein.«Ich setzte mich in Bewegung.»Und du?«, sagte er.Ich blieb stehen und drehte mich fragend zu ihm um.»Diesmal wird nicht gerannt.«Ich starrte ihn an. Woher wusste er, dass ich gelaufen war?»Und mach den Mund zu, es zieht«, sagte er. »Du siehst aus wie ein Idiot.«Ich gehorchte, schloss den Mund und ging langsam um das Haus herum. Als ich zur Vorderseite kam, war die Straße voller Kinder. Die ältesten standen in einer Traube zusammen, auf Fahrrädern, die in der Dämmerung wirkten, als wären sie Teil ihrer Körper. Die jüngsten spielten Verstecken. Wer gefangen worden war, stand in einem Kreidekreis auf dem Bürgersteig, die anderen lagen ringsum im Wald unterhalb der Straße ver-steckt, für den Suchenden, der gleichzeitig die bereits Gefange-nen bewachen musste, nicht zu sehen, wohl aber für mich.Über den schwarzen Baumwipfeln leuchteten rot die Lichter an den Brückenpfeilern. Auf dem Anstieg näherte sich erneut ein Auto. Im Licht der Scheinwerfer wurden erst die Fahrrad-fahrer deutlich sichtbar, ein kurzes Aufblitzen von Reflektoren, Metall, Steppjacken, schwarzen Augen und weißen Gesichtern, danach die spielenden Kinder, die nur den notwendigen Schritt seitlich ausgewichen waren, damit das Auto passieren konnte, und nun geisterhaft dastanden und es anstarrten.Es war das Ehepaar Trollnes, die Eltern von Sverre, einem Jungen aus meiner Klasse. Er schien nicht bei ihnen zu sein.Ich wandte mich um und sah den roten Rücklichtern nach, bis sie über die Hügelkuppe verschwanden. Dann ging ich hi-nein. Eine Weile versuchte ich auf dem Bett liegend zu lesen, aber es wollte sich nicht die nötige Ruhe einstellen, so dass ich stattdessen in Yngves Zimmer trottete, von wo aus ich auf Vater hinabschauen konnte. Wenn ich ihn sah, wusste ich, wo er war, und im Grunde war diese Gewissheit das wichtigste. Ich kannte seine Launen und hatte längst gelernt, sie mit Hilfe eines unterbewussten Kategorisierungssystems vorherzusehen, wie mir später klar wurde, bei dem das Verhältnis zwischen wenigen festen Größen ausreichte, um zu entscheiden, was mich erwartete, so dass ich die nötigen Vorkehrungen treffen konnte. Eine Art Meteorologie des Gemüts ... Die Geschwin-digkeit des Wagens auf dem sanften Anstieg zu unserem Haus, die Zeit, die er benötigte, um den Motor auszuschalten, seine Sachen zu packen und auszusteigen, die Art, wie er sich um-sah, wenn er die Autotür abschloss, die Nuancen in den un-terschiedlichen Lauten, die aus dem Flur hochdrangen, wenn er den Mantel ablegte – das alles waren Zeichen, das alles ließ sich deuten. Ergänzt wurde es durch Informationen darüber, wo er und wie lange er und mit wem er zusammen gewesen war, ehe die Schlussfolgerung, der einzige Teil des Prozesses, den ich bewusst wahrnahm, gezogen wurde. Am meisten fürchtete ich mich deshalb, wenn er einfach kam... Wenn ich aus ir-gendeinem Grund unaufmerksamgewesen war ...Woher in aller Welt hatte er gewusst, dass ich gelaufen war?Es war nicht das erste Mal, dass er mich in unerklärlicher Weise ertappt hatte. So hatte ich an einem Herbstabend eine Tüte Süßigkeiten unter dem Plumeau meines Betts versteckt, weil ich schon ahnte, dass er in mein Zimmer kommen und mir nie und nimmer glauben würde, wie ich an das Geld geraten war, um sie mir zu kaufen. Als er wie erwartet hereinkam, sah er mich einige Sekunden an.»Was hast du da im Bett versteckt?«, sagte er.Woher wussteer das?Draußen schraubte Prestbakmo die starke Glühlampe fest, die über der Platte angebracht war, an der er regelmäßig arbeitete. Das neue Auge aus Licht, das aus der Dunkelheit hervorstach, war voller Sachen und Dinge, die Prestbakmo stehend, vollkom-men reglos anstarrte. Stapelweise Farbtöpfe, Gläser mit Pin-seln, Holzklötze, Bretterenden, zusammengelegte Planen, Auto-reifen, ein Fahrradrahmen, ein paar Werkzeugkästen, Boxen voller Schrauben und Nägel in allen Größen und Formen, Bret-ter mit Milchtüten voller üppig sprießender Blumen, Säcke mit Kalk, ein aufgerollter Gartenschlauch und an die Wand gelehnt eine Platte, auf der sich alle möglichen Werkzeuge abzeichneten, wahrscheinlich war sie für den Hobbykeller im Haus gedacht.");
		list.add("Brust");
		list.add("Brüste");
		list.add("Penis");
		list.add("Vagina");
		list.add("Hoden");
		list.add("Uterus");
		
	
		
		
		list.add("Donald Trump");
		
		list.add("Angela Merkel");
		
		list.add("Das Böse");
		
		list.add("Das Gute");
		
list.add("Liebe");
		
		list.add("Hass");
		

		
list.add("Sehr geehrte Damen und Herren");
		
		list.add("Zerstörung");
		
list.add("Schöpfung");
		
		list.add("Erhaltung");
		
		list.add("Männlich");
		
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
