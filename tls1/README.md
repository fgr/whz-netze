# Praktikum *Transport Layer Security* mit Java

# 1. Verschlüsselte Socket-Kommunikation mit *automatisch* generierten Schlüsseln

# 2. Verschlüsselte Socket-Kommunikation mit *eigenen, manuell generierten* Schlüsseln

## Erstellen von Schlüsseln

### Server-Schlüssel-Paar mit Self-Signed-Zertifikat erstellen

1. Schlüsselpaar erstellen: `<jdk>/bin/keytool -genkey -keyalg RSA -alias serverkey1 -keystore myserverkeystore.jks -storetype jks -storepass geheim123 -keypass ganzgeheim -validity 360 -keysize 2048`

Darauf achten, dass aktuelle Version von *keytool* verwendet wird. Am besten *keytool* verwenden, das Bestandteil des zum Einsatz kommenden JDKs ist. Es befindet sich im `bin`-Verzeichnis des JDKs.

  1.1 *Common Name*-Feld (CN) setzen:
  ```
  What is your first and last name?
  [Unknown]: server1
  ...
  Is CN=server1, OU=Unknown, O=Unknown, L=Unknown, ST=Unknown, C=Unknown correct?
  [no]:  yes
  ```

## Importieren eines Zertifikats

### (Self-Signed-)Zertifikat (auf Server-Seite) exportieren

Öffentlichen Schlüssel und Self-Signed-Zertifikat in Zertifikatdatei `serverkey1.cer` exportieren: `<jdk>/bin/keytool -exportcert -alias serverkey1 -file serverkey1.cer -keystore myserverkeystore.jks -storetype jks -storepass geheim123` 

### (Self-Signed-)Zertifikat (des Servers auf Client-Seite) importieren

`<jdk>/bin/keytool -importcert -alias serverkey1 -trustcacerts -file serverkey1.cer -keystore myclienttruststore.jks -storetype jks -storepass clientpw`

# 3. Client-Authentifizierung

Aufgabe: Setzen Sie ausgehend von Aufgabe 2 um, dass der Server die Authentifizierung des Clients verlangt und dem Server das Zertifikat des Clients kannt ist. 

# 4. Standardschlüsselformat PKCS#12 verwenden statt Java-Key-Store-Format (JKS)

## Self-Signed-Zertifikat auf Server-Seite exportieren

Öffentlichen Schlüssel und Self-Signed-Zertifikat in Zertifikatdatei `serverkey1.cer` exportieren: `<jdk>/bin/keytool -exportcert -alias serverkey1 -file serverkey1.cer -keystore myserverkeystore.p12 -storetype pkcs12 -storepass geheim123`

## Server-Schlüssel-Paar mit Self-Signed-Zertifikat erstellen

1. Schlüsselpaar erstellen: `<jdk>/bin/keytool -genkey -keyalg RSA -alias serverkey1 -keystore myserverkeystore.p12 -storetype pkcs12 -storepass geheim123 -keypass ganzgeheim -validity 360 -keysize 2048`

## Self-Signed-Zertifikat des Servers auf Client-Seite importieren

`<jdk>/bin/keytool -importcert -alias serverkey1 -trustcacerts -file serverkey1.cer -keystore myclienttruststore.p12 -storetype pkcs12 -storepass clientpw`

## Java-Code

KeyStore ksKeys = KeyStore.getInstance("PKCS12"); // statt: KeyStore ksKeys = KeyStore.getInstance("JKS");
TrustStore tsKeys = TrustStore.getInstance("PKCS12"); // statt: TrustStore tsKeys = TrustStore.getInstance("JKS");
