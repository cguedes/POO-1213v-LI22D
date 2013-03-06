# Objectivo

Implementar o método estático `Uri createUri(String uri)` da classe `Uri` que retorna um objecto do tipo `Uri` a partir de uma representação em *String*. 

A classe `Uri` tem os seguintes membros: 

```java
    public class Uri 
    { 
      public String getHost(); 
      public short getPort();
      public String getPath(); 
      public String getQueryString();

      public static Uri createUri(String uri);
    }
```

Observações: 

 * Se não for possível criar o Uri, o método `createUri` deve retornar valor `null`
 * O campo `port` é opcional. Se não estiver definido deve ser retornado valor `-1`
 * O campo `path` deve começar por `/`
 * O campo `query` é opcional. Se não estiver definido deve ser retornada uma string vazia.

## Exemplo de Uri HTTP

* `http://www.isel.pt/`
* `http://www.isel.pt:8080/`
* `http://thoth.cc.e.ipl.pt/classes/POO/1213v/LI22D`
* `http://www.youtube.com/watch?v=WyzJ2Qq9Abs`



# Compilação através da linha de comandos

    /> cls & javac ap1\*.java & java -ea ap1.UriTests


  Esta execução limpa o ecrã (`cls`), compila o código java do package ap1 (`javac ap1\*.java`) e executa o método main do tipo `ap1.UriTests` com as asserções ligadas (java -ea ap1.UriTests).

