O que fazer: modifique os arquivos grammars/Cymbol.g4 e CymbolCheckerVisitor.py 

make                    : compila, se necessário, e executa o interpretador - digite a entrada, [enter] e [ctrl]+[d]
make < arquivo.txt      : o mesmo, mas dá como entrada o conteúdo do arquivo, para não ter que digitá-la toda a vez
make tree		        : compila e desenha a árvore sintática das expressões dadas como entrada 
make tree < arquivo.txt : o mesmo, mas etc.

make sublime            : baixa, extrai e abre o editor de texto sublime (só abre, se já estiver baixado)
make vscode             : baixa, extrai e abre o visual studio code (só abre, se já estiver baixado)

make clean              : remove arquivos e pastas gerados durante a compilação e dos editores de texto

obs1.: além de redirecionar a entrada para um arquivo é possível recebê-la da saída de outro programa
echo "x = 2 + 3;" | make
echo "y = 5 * 2;" | make tree

obs2: [ctrl]+[d] insere EOF (end of file) no terminal

Quando terminar, anexe TestExtenndVisitor.py à atividade da ap2 no google classroom.

----------------------------------------------------------------------------------------------------------
----------------------------------------------VERSÃO-ANTIGA-----------------------------------------------
----------------------------------------------------------------------------------------------------------

Arquivo Readme para 2ª Aula Prática de Compiladores de 2017.2
Última atualização em 21 de Setembro de 2017 - 23:55 por Ihago Henrique Lucena e Silva - ihls@cin.ufpe.br.



====================================================================================================
VISÃO GERAL
====================================================================================================
    Este documento explica como configurar o ambiente para execução do ANTLR no seu Windows.



====================================================================================================
REQUERIMENTOS
====================================================================================================
    - Complete ANTLR 4.5.3 Tool
    - Java Development Kit (JDK) Versão 6 ou Superior
    - Eclipse IDE



====================================================================================================
CONFIGURAÇÃO DO AMBIENTE
====================================================================================================
    1. É necessário especificar temporariamente na variável de ambiente CLASSPATH o caminho do 
    ANTLR. Para isto, execute o comando abaixo substituindo o caminho 
    C:\compilers-cin\aulas-praticas\ap2\antlr-4.5.3-complete.jar pelo caminho do ANTLR do seu
    computador.
    
    > SET CLASSPATH=.;C:\compilers-cin\aulas-praticas\ap2\antlr-4.5.3-complete.jar;%CLASSPATH%



====================================================================================================
COMPILAÇÃO TESTE
====================================================================================================
    Como exemplo para essa etapa, utilizaremos a gramática Test que se encontra no diretório 
    compilers-cin\aulas-praticas\ap2\antlr-test-grammar-tree-walking\.
    
    1. Abra a linha de comando do Windows (cmd.exe) no diretório compilers-cin\aulas-praticas\ap2\antlr-test-grammar-tree-walking\.
    2. Execute o comando abaixo para gerar os lexers, parsers e visitors em Java:
    
    > java org.antlr.v4.Tool Test.g4 -visitor -no-listener
    
    3. Uma interface visitor será gerada (TestVisitor), bem como uma implementação padrão dela (TestBaseVisitor) além
    dos tokens e de outros arquivos em Java.



====================================================================================================
EXECUÇÃO TESTE
====================================================================================================
    1. Abra a IDE do Eclipse.
    2. Importe o projeto contido no diretório compilers-cin\aulas-praticas\ap2\antlr-test-grammar-tree-walking para o Eclipse.
    3. Na IDE do Eclipse, execute a classe TreeWalkerApplicationVisitor.java do pacote application do projeto ANTLR-Test-Grammar-Tree-Walking.
    4. Ao realizar o comando anterior, o console da IDE Eclipse ficará a espera de uma entrada, digite a entrada abaixo e
    em seguida aperte a tecla ENTER e depois a combinação CTRL+Z para que a ferramenta processe a entrada:
    
    20+5;
    
    5. Será exibido no console da IDE do Eclipse o resultado da expressão acima, ou seja, o valor 25.
