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
2. Diese Datei (protobuf.jar) muss wie gewohnt zum Build Path jedes Eclipse-Projektes hinzugefügt werden, das Protocol Buffers verwendet.

## C++: Einbinden der Protocol-Buffers-Bibliothek

### (Debian- und Ubuntu-)Linux

```
sudo apt-get install libprotobuf-dev
```

# Entwicklung mit Protocol Buffers

## TODO

Java (Eclipse-Projekt):

-> https://developers.google.com/protocol-buffers/docs/javatutorial

cd protobuf1
 
protoc -I=./protocol --java_out=java/src ./protocol/addressbook.proto
 
C++:

-> https://developers.google.com/protocol-buffers/docs/cpptutorial

protoc -I=./protocol --cpp_out=cpp ./protocol/addressbook.proto
