package wIwatW;


import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;






/*Text-Explorationstool
alternative Suchen?

Selector
speichern alter Übersetzungen, geparste Textsammlung zwischen speichern

weitere Kodierungsverfahren recherchieren, anwenden
Effizienz
Graphische Oberfläche

Wort features:
String-Gleichheit/Ähnlichkeit	:		umgesetzt durch soundex und equalsignorecase 									x
Wortklang						:		Soundex, nur vokale, nur Konsonanten, Kölner Phonetik							x
POStags/Syntax					: 		funktioniert, aber verlangsamt, erfordert speichern der getaggten sammlung		x

Buchstabenzahl					:		umgestezt über stinglength (hohe Häufigkeiten)									x
Silbenzahl						:		umgesetzt durch vokale in einem wort (hohe Häufigkeiten) 						x
Thema/Semantik					:		Sachgebiete, Synynome, (verlangsamt)
Sentiment/Stimmung:				:		



Schnitt durch Code, der aus Konkatenationen der Einzelcodes besteht
Vereinigung ist default 
A/B durch Anfrage von AvereinigtB und neuer Anfrage B, dann removeAll B von A+B
vereinheitlichen von Translator/Sentence/Token, halbwegs umgesetzt durch runterreichen der Satzüberstezungen
Mengenoperationen auf frequencylists: funktionieren
POSTAGs + Wort hinzufügen
FRE nach unten geben
Umstellung in Suche auf Corpus-Suche gelungen
odt funktioniert jetzt
Unterschiede Sort und Fast Sort
Sonderzeichen behandeln
Satzendezeichen reduzieren
TokenObserver(String, String) zu TokenObserver(String, ArrayList<String>)
Nutzbarkeit von Synonymen(nutzt Synonyme für suche und Ergebnismodifikation) Problem Deklination/Konjunktion
Baseform+Synonym/Thesaurus
Postprocessing auf baseform reduzieren, eventuell dann passenden fall für satz wählen(komplex, vorerst nicht): klappt, baseform ist letztes baseform listen element
flags in postprocessing operationen erhalten
(Texte finden)
MultipleTokenObserver einbinden (oder nicht?)-->Performanztest, ansonsten gut

höhere Gewichtung von Wörten im Zusammenhang aus gleicher Geschichte(implizit bereits umgesetzt? ja für 2 Wörter, nein für drei Wörter): funktioniert vermutlich

Normierung der Frequenzen/Signifikanz
Problem: score und keine Frequenzen verwenden
Problem: signifikanz bisher in suche erforderlich, erfordert außerdem reduce/collect
Corpusverwaltung jetzt so: lade alten metacorpus, füge geschichten nur hinzu, wenn noch nicht in entsprechendem Corpus vorhanden,
							speichere nach jeder hinzugefügten Geschichte aktuellen metaCorpus ab (überschreibt alten)
- Signifikanz für Referenzkorpus (selbst einstellbar oder deutscher Wortschatz) - auf Frequenzlisten bezogen

N:A, negative Werte bei referenzsignifikanz
tfidf
Parallelisieren von Suche durch: PostProcessing Parallel und jede Position parallel behandeln, markiert durch parallelisierbar
deleteNullfields oder altenrative-->distinctaddprüfung
collect/reduce so schreiben, dass alle werte addiert werden, die addiert werden sollen (betrifft nicht: Indexe, Gewichte) 
Parallelisieren der Voverarbeitung durch parallele behandlung jeder Datei um auf Story zu kommen,
außerdem translator parallel, aber wegen Auslastung aller Kerne fast nicht gewichtvoll--> preprocessing bisschen effizienter
Abstürzen bei Wortschatz-verwenden durch Synonymfunktion - Thesaurus funktion macht, die aber nicht mehr notwendig, also weggelassen
postags werden schneller gefunden wegen parallelisieren im preprocessing, ids werden richtig zugewiesen, durch vorige zuweisung und dann sortierung der listen
Gewichte gehen ein durch methode, die aktuellen wert mit anderem faktor multipliziert
optional:
-Sentiment/FRE durch bessere Berechung ersetzen / eigene translation algorithmen
-Nutzen von CorpusManager?
MetaCorpus konflikte lösen (2 MetaCorpora vorhanden): Ansatz: Merge der MetaCorpora, d.h füge duplikatfrei Corpora hinzu. Falls es Duplikate gibt, füge duplikatfrei Geschichten hinzu
aber: wie soll PositionMap gemergt werden?
- Höhere Gewichtung von bereits geschriebenen Wörtern
-Denkbar wäre auch: suchen des 1. Vorgängers des letzten Musterwortes nach zweiten Nachfolger
					suche des 2. Vorgängers des letzten Musterwortes nach dritten Nachfolger
					usw.
Tests: siehe Funktionen in banotizen
Umlaute 
Tests(Gemixte Suche prüfen, MultipleTokenObserver-Performanz)
 aufräumen
 Komma eingabe bringt programm zum absturz
 
ToDO:

Gute Sucheinstellungen finden

mitte aus story identifikator rausnehmen bei nächstem neuen metakorpus

 
 Performanz parallel:
 Parameter:											consoleillustrator			story						sentence					translator
 Ergebnis	|mit1/ohne0 richtigen Sachgebietmethoden|jede Datei nicht0/parallel1|jeder Satz nicht0/parallel1|jedes Wort nicht0/parallel1|jede Übersetzung nicht0/parallel1
 	833	1	|0										|0							|0							|0							|0				
 -	763	2	|0										|0							|0							|0							|1						
 -	949	3	|0										|0							|0							|1							|0				
 	604	4	|0										|0							|0							|1							|1				
 -	332	5	|0										|0							|1							|0							|0				
 	362	6	|0										|0							|1							|0							|1				
 	344	7	|0										|0							|1							|1							|0				
 	340	8	|0										|0							|1							|1							|1				
 -	432	9	|0										|1							|0							|0							|0				
 	489	10	|0										|1							|0							|0							|1				
 	342	11	|0										|1							|0							|1							|0				
 	381	12	|0										|1							|0							|1							|1				
 	324	13	|0										|1							|1							|0							|0				
 -->287	14	|0										|1							|1							|0							|1				
 	385	15	|0										|1							|1							|1							|0				
 	353	16	|0										|1							|1							|1							|1				
 		17	|1										|0							|0							|0							|0
 		18	|1										|0							|0							|0							|1								
 		19 	|1										|0							|0							|1							|0
 		20	|1										|0							|0							|1							|1
 		21	|1										|0							|1							|0							|0				
 		22	|1										|0							|1							|0							|1				
 		23	|1										|0							|1							|1							|0				
 		24	|1										|0							|1							|1							|1	
 		25	|1										|1							|0							|0							|0				
 		26	|1										|1							|0							|0							|1				
 		27	|1										|1							|0							|1							|0				
 		28	|1										|1							|0							|1							|1				
 		29	|1										|1							|1							|0							|0				
 		30	|1										|1							|1							|0							|1				
 		31	|1										|1							|1							|1							|0				
		32	|1										|1							|1							|1							|1				

*/











/**
 * Mainclass, starts the tool
 * @author Lasse
 *
 */
public class wIwatWMain {
//wIwaTW:What I Was About To Write
	public static void main(String[] args) throws IOException 
	{
		
		long time = System.currentTimeMillis( );
//System.out.println(Levenshtein.distance("Mann", "Dannoo"));

		String path = wIwatWMain.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		String decodedPath="";
		try {
			decodedPath = URLDecoder.decode(path, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		
		
		File test= new File(decodedPath);
//		System.out.println("0"+test.getParent());
		decodedPath=test.getParent();
		System.out.println("Zeit: "+ (System.currentTimeMillis( ) - time)/1000 +"s | "+(System.currentTimeMillis( ) - time)+"ms" ); 
		@SuppressWarnings("unused")
		SimpleGUI s = new SimpleGUI(decodedPath);
		System.out.println("Zeit: "+ (System.currentTimeMillis( ) - time)/1000 +"s | "+(System.currentTimeMillis( ) - time)+"ms" );
//		 long time = System.currentTimeMillis( );
//		//System.out.println("Zeit: "+ (System.currentTimeMillis( ) - time)/1000 +"s" );
	}

	

}
