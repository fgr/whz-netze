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

cd protobuf1
 
protoc -I=./protocol --java_out=java/src ./protocol/addressbook.proto
 
### C++:

(Vgl. [Protocol-Buffers-Tutorial für C++](https://developers.google.com/protocol-buffers/docs/cpptutorial).)

protoc -I=./protocol --cpp_out=cpp ./protocol/addressbook.proto

# Übung

Erzeugen Sie aus folgender Datenstruktur eine Protocol-Buffers-Implementierung:

![Diagram](http://yuml.me/fcaf7b77) ([Edit-Link](http://yuml.me/edit/fcaf7b77))

Führen Sie folgende Schritte aus, um für diese Datenstruktur in eine Protocol-Buffers-Implementierung zu erhalten:

1. Neues Eclipse-Java-Projekt erzeugen.
2. Eine Protocol-Buffers-Datei (geodata.proto) erzeugen
  1. Datenstruktur im [Protocol-Buffers-Format](https://developers.google.com/protocol-buffers/docs/proto) in dieser Datei beschreiben.
3. Erzeugen Sie aus dieser Datei mithilfe von *protoc* eine der Datenstruktur entsprechende Java-Implementierung.
4. Verwenden Sie diese Implementierung, um GeoData-Objekte zu serialisieren, über eine TCP-Verbindung zu übertragen und beim (Java-) Empfänger wieder in GeoData-Objekte zu de-serialisieren.

