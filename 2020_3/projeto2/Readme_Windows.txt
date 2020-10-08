Tutorial para Execução no Windows utilizando Python

====================================================================================================
Geração de Lexer/Parser/Visitor
====================================================================================================

1. Execute o comando 
	'java -jar antlr-4.7.2-complete.jar -Dlanguage=Python3 grammars/SuaGramatica.g4 -visitor -no-listener' 
	trocando 'SuaGramatica.g4' pelo nome do arquivo da sua gramática.
	
2. Mova os arquivos gerados para o diretório 'antlr4-python3-runtime-4.7.2/src/autogen'

3. Edite o arquivo 'GrammarCheckerVisitor.py' para implementar os requisitos pedidos.
obs.: O arquivo GrammarCheckerVisitor.py também deve estar no diretório 'antlr4-python3-runtime-4.7.2/src/autogen'.

4. Execute o comando:
	'python antlr4-python3-runtime-4.7.2/src/main.py < ArquivoTeste.c' 
	trocando 'ArquivoTeste.c' pelo nome do arquivo teste que você queira executar.