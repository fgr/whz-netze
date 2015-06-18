# Details

## [CreadentialsServer0](CreadentialsServer0.java) und [CreadentialsClient0](CreadentialsClient0.java)

Der [Client](CreadentialsClient0.java) sendet *die Bytes* zweier Strings (c.login und c.password) zum Server:

```Java
out.write(c.login.getBytes());
out.write(c.password.getBytes());
```

Der [Server](CreadentialsServer0.java) versucht, diese Bytes wieder in zwei Strings (login und password) zu lesen:

```Java
byte buf[] = new byte[1024];
int len = s.getInputStream().read(buf);
String login = new String(buf, 0, len);

len = s.getInputStream().read(buf);
String password = (len == -1) ? null : new String(buf, 0, len);
```

Es gelingt dem Server allerdings nicht, die Bytes des ersten gesendeten String von den Zeichen des zweiten Strings zu trennen. Im String `login` landen alle Zeichen der *beiden* gesendeten Strings, da das erste `read()` alle gesendeten Bytes liest -- und da das zweite `read()` keine weitern Daten (`len == -1`) liefert, ist `password` ein leerer String.

**Die Anzahl der writes()/reads() beeinflusst die Struktur/Anordnung/Trennung der gesendeten Daten nicht! Da nur die Zeichen der beiden Strings gesendet werden (aber kein Trennzeichen oder die Länger der Strings oder andere Zusatzinformationen, um die beiden Strings von einander abzugrenzen), sind diese Zeichen für den Empfänger nicht wieder in zwei Strings trennbar.**
