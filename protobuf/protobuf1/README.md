Java (Eclipse-Projekt):

-> https://developers.google.com/protocol-buffers/docs/javatutorial

protobuf.jar -> http://mvnrepository.com/artifact/com.google.protobuf/protobuf-java/
 
cd protobuf1
 
protoc -I=./protocol --java_out=java/src ./protocol/addressbook.proto
 
C++:

-> https://developers.google.com/protocol-buffers/docs/cpptutorial

protoc -I=./protocol --cpp_out=cpp ./protocol/addressbook.proto


