# CodeScrambler
A simple java command line tool to scramble up boring java files. <p>
(Works just fine with most other languages that aren't line sensitive)

Example:<br>
This incredibly powerful and useful program can turn this boring java code:
```java
package de.edgelord.codescrambler.example;

public class Example {
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
```
into this incredible source code:
```java
package de.edgelord.codescrambler.example;

public   class Example      {
              public  static void     main          (String[]                args                     )                                                           {
                                                                                                                                                                                                                                                                                                                                     System.out.println                              ("Hello World!"                                 )                                     ;
                                                                                                                                           }
                         }
```
(It looks more impressive on bigger files!)

How to use:<br>
```bash
# clone the repo
git clone https://github.com/edgelord314/CodeScrambler

# cd into it
cd CodeScrambler/src/de/edgelord/codescrambler
javac Main.java
java Main path/to/file
```