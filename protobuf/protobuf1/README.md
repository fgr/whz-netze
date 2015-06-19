Dieses Verzeichnis beinhaltet die Java- und C++-Version eines [Beispiels](https://developers.google.com/protocol-buffers/docs/javatutorial) für [Google Protocol Buffers](https://developers.google.com/protocol-buffers).

# Setup

## Installation des Protocol-Buffers-Compilers

### (Debian- und Ubuntu-)Linux

Einfach das entsprechende Paket installieren. Für Debian- und Ubuntu-Linux heißt das:

```
sudo apt-get install protobuf-compiler
```

### Windows

Die entsprechende ZIP-Datei von [https://developers.google.com/protocol-buffers/docs/downloads](https://developers.google.com/protocol-buffers/docs/downloads) laden. Derzeit ist die [stabile Version hier zu finden](https://github.com/google/protobuf/releases/download/v2.6.1/protoc-2.6.1-win32.zip).

## Java: Einbinden der Protocol-Buffers-Bibliothek

1. Um Protocol Buffers mit Java zu verwenden, wird die Bibliothek *protobuf.jar* benötigt; sie befindet sich hier: [http://mvnrepository.com/artifact/com.google.protobuf/protobuf-java/](http://mvnrepository.com/artifact/com.google.protobuf/protobuf-java/)
  * Verwenden Sie dieselbe Version der Bibliothek, die Sie auch beim Compiler verwenden. Derzeit ist dies die stabile Version [http://central.maven.org/maven2/com/google/protobuf/protobuf-java/2.6.1/protobuf-java-2.6.1.jar](http://central.maven.org/maven2/com/google/protobuf/protobuf-java/2.6.1/protobuf-java-2.6.1.jar).
2. Diese Datei (protobuf.jar) muss wie gewohnt zum Build Path jedes Eclipse-Projektes hinzugefügt werden, das Protocol Buffers verwendet.

## C++: Einbinden der Protocol-Buffers-Bibliothek

### (Debian- und Ubuntu-)Linux

```
sudo apt-get install libprotobuf-dev
```

# Entwicklung mit Protocol Buffers

## Beispielprojekt

Das folgende Beispiel orientiert sich am [Protocol-Buffers-Tutorial](https://developers.google.com/protocol-buffers/docs/tutorials)

### Java (Eclipse-Projekt):

(Vgl. [Protocol-Buffers-Tutorial für Java](https://developers.google.com/protocol-buffers/docs/javatutorial).)

#### Beispielprogramme ausführen:

1. Eclipse-Projekt auschecken
 1. In Eclipse: File > Import... > Git > Project from Git
 2. Git-Clone-URI: https://github.com/fgr/whz-netze
 3. ...
 4. Import existing projects
  1. Projekt *protobuf1* auswählen und importieren.
2. Im Java-Package [`com.example.tutorial.main`](java/protobuf1/src/com/example/tutorial/main) befinden sich mehrere Beispiele, die die Serialisierung von `Person`-Objekten, den Transport der serialisierten Daten via TCP und die De-Serialisierung in `Person`-Objekten beim Empfänger demonstrieren.

#### Java-Implementierung der Protcol-Buffers-Klassen erzeugen:

In der Datei [addressbook.proto](protocol/addressbook.proto) werden die *Person*- und *AddressBook*-Datenstrukturen als Protocol-Buffers-Typen (sogenannte *Messages*) definiert. Diese Definition wird verwendet, um die Java-Implementierung (und die Implementierung für weitere Programmiersprachen wie C++) zu generieren, die für die Serialisierung und De-Serialisierung von  `Person`- und `AddressBook`-Objekten verantwortlich ist. Die Java-Implementierung befindet sich in der Klasse [`AddressBookProtos`](java/protobuf1/src/com/example/tutorial/AddressBookProtos.java).

Diese Klasse kann mit folgendem Befehl automatisch vom Protocol-Buffers-Compiler aus der Datei [addressbook.proto](protocol/addressbook.proto) generiert werden (das Verzeichnis [*protobuf1*](.) ist das dem [Java-Verzeichnis](protobuf/protobuf1/java/) übergeordnete Verzeichnis):
```
cd protobuf1
protoc -I=./protocol --java_out=java/src ./protocol/addressbook.proto
```
 
### C++:

(Vgl. [Protocol-Buffers-Tutorial für C++](https://developers.google.com/protocol-buffers/docs/cpptutorial).)

#### C++-Beispiele:

Im Verzeichnis [cpp](cpp/) befindet sich Beispiel-Code für einen TCP-Server, der ein mit Protocol Buffers serialisiertes `Person`-Objekt zum Client sendet.

#### C++-Implementierung der Protcol-Buffers-Klassen erzeugen:

Die C++-Implementierung kann mit folgendem Befehl automatisch vom Protocol-Buffers-Compiler aus der Datei [addressbook.proto](protocol/addressbook.proto) generiert werden (das Verzeichnis [*protobuf1*](.) ist das dem [C++-Verzeichnis](cpp/) übergeordnete Verzeichnis):

```
cd protobuf1
protoc -I=./protocol --cpp_out=cpp ./protocol/addressbook.proto
```

# Übung

Erzeugen Sie aus folgender Datenstruktur eine Protocol-Buffers-Implementierung:

![Diagram](http://yuml.me/fcaf7b77) ([Edit-Link](http://yuml.me/edit/fcaf7b77))

Führen Sie folgende Schritte aus, um für diese Datenstruktur in eine Protocol-Buffers-Implementierung zu erhalten:

1. Neues Eclipse-Java-Projekt erzeugen.
2. Eine Protocol-Buffers-Datei (geodata.proto) erzeugen
  1. Datenstruktur im [Protocol-Buffers-Format](https://developers.google.com/protocol-buffers/docs/proto) in dieser Datei beschreiben.
3. Erzeugen Sie aus dieser Datei mithilfe von *protoc* eine der Datenstruktur entsprechende Java-Implementierung.
4. Verwenden Sie diese Implementierung, um GeoData-Objekte zu serialisieren, über eine TCP-Verbindung zu übertragen und beim (Java-) Empfänger wieder in GeoData-Objekte zu de-serialisieren.

