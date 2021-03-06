Tabulae
=======

![screenshots](https://raw.githubusercontent.com/emdete/Tabulae/master/art/function.jpg)

Übersicht
---------

Tabulae ist eine App zum Anzeigen von verschiedensten Karten. Es zeigt darauf
die eigene Position, POIs, aufgezeichnete oder importierte Wegstrecken und
Streckenplanungen.

Es ist mit Gesten steuerbar (zoomen mit zwei Fingern) aber wesentlich wurde
Wert auf die Einhandbedienung gelegt um die Nutzung beim Sport zu vereinfachen.

Die Karten können vorab heruntergeladen werden, was die Nutzung in Gegenden mit
schlecher Netzabdeckung erlaubt. Auch die nach Bedarf heruntergeladenen Karten
werden gespeichert um Bandbreite zu sparen.

Auf verschiedene Weise können Positionen mit anderen Apps und Nutzern geteilt
oder angezeigt werden. Die Wegstrecken und POIs können als gpx im- und
exportiert werden.

Tabulae benötigt keinen Account zu irgendeinem Dienst, speichert keine Daten
welcher Art auch immer im Internet ("in der cloud"). Das Zurverfügung-Stellen
von Daten liegt in der Kontrolle des Benutzers. Tabulae erlaubt das Senden und
Empfangen dieser Daten, der Nutzer jedoch bestimmt, mit wem diese geteilt oder
weiterverarbeitet werden.

Tabulae meldet keine privaten Informationen des Nutzers zu einem zentralen
Server. Die einzige Möglichkeit, private Daten von Nutzern zu erhalten wäre aus
den heruntergeladenen Kartenteile die grobe Position des Nuzters zu ermitteln.

Tabulae enthält keine Werbung.

Die Quelltexte des Programms sind für jeden einsehbar und damit überprüfbar.

#### Hauptbildschirm

<img alt="screenshot main" src="https://raw.githubusercontent.com/emdete/Tabulae/master/art/screenshot.png" height="800px" width="450px">

#### Querformat

<img alt="screenshot portrait" src="https://raw.githubusercontent.com/emdete/Tabulae/master/art/screenshot-portrait.png" height="450px" width="800px">

#### Track Statistik

<img alt="screenshot statistics" src="https://raw.githubusercontent.com/emdete/Tabulae/master/art/screenshot-statistic.png" height="800px" width="450px">

#### Verkehrsnachrichten / Staureport

Zum Testen der Tracks wurde eine Anzeige von Staureports hinzugefügt:

<img alt="screenshot portrait" src="https://raw.githubusercontent.com/emdete/Tabulae/master/art/screenshot-traffic.png" height="800px" width="450px">

Bedienung
---------

### Kartenauswahl

Der Anbieter der Karte kann aus einem Menü gewählt werden. Einige Anbieter sind
verfügbar und es können Strassenkarten, Topographische Karten,
Satellitenbilder und andere ausgewählt werden.

Hinweis: Dem Benutzer obligt zu prüfen, ob der ausgewählte Kartenanbieter der
Nutzung zustimmt.

### Vektor

Die App nutzt wo es geht Vektor-Grafiken. Alle Icons sind Vektoren (die aus
SVGs konvertiert werden) um die leidige DPI Abhängigkeit zu umgehen
(https://developer.android.com/training/material/drawables.html). Dies ist ein
Grund, warum die Unterstützung älterer Android Verionen fallengelassen wurde.

Auch als Karten werden Vektorkarten genutzt (das Projekt
[mapsforge](https://github.com/mapsforge/mapsforge/) liefert diese Funktion).
In "Map Download" wird beschrieben wie diese installiert werden.

### Instrumentenbrett

Das Instrumentenbrett (Anzeige von verschiedensten Werten über der Karte) kann
über das Menu ab- und angeschaltet werden.

Auch jedes einzelne Element kann entfernt und neue hinzugefügt werden (lange
auf ein Element klicken) und die Art des Wertes ausgewählt werden.

### Wegstrecke

Wegstrecken können aufgezeichnet werden. Die Aufzeichnung kann pausiert werden.
Die Strecke ist in einer internen Datenbank gespeichert werden und in übliche
Formate (kml und gpx) exportiert werden und in anderen Programmen
weiterverarbeitet werden.

### POI

POI (Position von Interesse) können markiert werden. Sie können "geteilt",
"gesendet" und exportiert werden.

### Vermessen

Zur Planung einer Strecke kann diese vorab erfasst und vermessen werden.

### Kooperation

Die App kann die aktuelle Position mit einem Chatprogramm teilen (siehe
"[Conversations](market://search?q=pname:eu.siacs.conversations)").

Map Download
------------

Zur Vereinfachung des Installierens von Karten stellt pyneo rechteckige Karten
zum Download bereit. Sie sind unter

[maps](https://pyneo.org/maps/)

verfügbar (Maps nach Ländergrenzen können von
[mapsforge](http://download.mapsforge.org/) bezogen werden). Die Namen der
Rechtecke werden aus lat/lon der unteren, linken Ecke gebildet. Die Größe ist
1°. Später sollen diese Dateien von der App direkt geladen werden (darum
Rechtecke und Namen, die ein Program einfacher bestimmen kann als "bremen.map"
aus gegebener lat/lon). Im moment müssen diese manuell heruntergeladen werden
und and die richtige Stelle installiert werden. Tabulae nutzt das Verzeichnis,
das getExternalFilesDirs() zurückgibt. Initial wird dasjenige Verzeichnis
gewählt, das am meisten freien Platz bereitstellt. In einem Gerät mit
eingelegter zweiten SD müssen die map-Dateien für mapsforge dann in

	/storage/sdcard1/Android/data/de.emdete.tabulae/files/maps/mapsforge

installiert werden. Diejenigen für openandromaps (siehe
[openandromaps](http://www.openandromaps.org/)) entsprechend in

	/storage/sdcard1/Android/data/de.emdete.tabulae/files/maps/openandromaps

Da diese map-Dateien eigene Themes benötigen, müssen auch diese installiert
werden. Der Pfad dahin ist dann:

	/storage/sdcard1/Android/data/de.emdete.tabulae/files/maps/openandromaps/themes

Diese Bedingungen kann im Grunde nur ein Android-Entwickler herstellen (upload
via adb), jeder andere muss sich im Moment mit den Pixeltiles begnügen.

Fehlersuche
-----------

Stürzt Tabulae ab, logged es den Fehler. Tabulae logged mit den üblichen
Android Board-Mitteln. Mit dem Befehl:

	adb logcat -v time -s de.emdete.tabulae

lässt sich beobachten, was Tabulae zu sagen hat. Ein Debug-Build (siehe
nächstes Kapitel) logged mehr Informationen.

Selbst Compilieren
------------------

Die Quellen von Tabulae lassen sich per

	git clone https://github.com/emdete/tabulae

herunterladen und mit dem Befehl

	./gradlew assembleRelease

übersetzen.

Kontakt
-------

Mails mit Fehlerberichten, Erweiterungswünschen, Bargeld und
Danksagungen an:

	tabulae@pyneo.org

Best practices
--------------

Das Projekt wurde unter

[bestpractices](https://bestpractices.coreinfrastructure.org/projects/174)

registiert, im wesentlichen weil ich neugierig war, wie die Erteilung abläuft.

