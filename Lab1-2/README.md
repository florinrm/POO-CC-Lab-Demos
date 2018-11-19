# Intro to Java - String
## Despre clasa String
- String-urile din Java reprezinta siruri de caractere, avand un comportament similar (dar nu identic) cu array-ul de char-uri din C / C++ (char[] / char*)
- String este o clasa predefinita in Java, care mosteneste clasa Object (clasa parinte / clasa absoluta din Java) -> despre mostenire - mai incolo
## String vs char[] in Java
- Spre deosebire de char[] (prezent de asemenea in Java, care imprumuta sintaxa C), obiectele de tip String se afla in memoria heap (char[] sunt valori, salvate in stiva)
- Modalitatea de accesare a caracterelor difera: 
1) String: str.charAt(x) - x e pozitia caracterului (0-indexed)
2) char[]: str[x]

- Stringurile sunt imutabile in Java, in sensul ca nu putem schimba in mod direct valoarea unui caracter, insa putem sa schimbam valoarea stringului:
```java
String str = "sexy malone";
str = "sexy bubu"; // merge
str.chatAt(0) = 'f'; // nu o sa mearga
```
- Daca vrem totusi sa modificam caractere din string-uri, folosim StringBuilder / StringBuffer [(seamana foarte mult ca functionalitate)](https://stackoverflow.com/questions/355089/difference-between-stringbuilder-and-stringbuffer) sau char[] -> detalii [aici](https://stackoverflow.com/questions/11588916/java-replace-character-at-specific-position-of-string)
