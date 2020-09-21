1- Clonar Repositório da cadeira
2- Baixar o JDK e colocar seu caminho na variável de ambiente PATH
3- Colocar o caminho do arquivo .jar do ANTLR na variável de ambiente CLASSPATH
4- Comando para gerar lexer e parser: 
	java org.antlr.v4.Tool Grammar.g4
5- Comando para compilar: 
	javac Grammar*.java
6- Comando para gerar a árvore: 
	java org.antlr.v4.runtime.misc.TestRig Grammar file -gui