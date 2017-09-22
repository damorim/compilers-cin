Arquivo Readme para 1ª Aula Prática de Compiladores de 2017.2
Última atualização em 05 de Setembro de 2017 - 13:50 por Ihago Henrique Lucena e Silva - ihls@cin.ufpe.br.



====================================================================================================
VISÃO GERAL
====================================================================================================
    Este documento explica como configurar o ambiente para execução do ANTLR no seu Windows.



====================================================================================================
REQUERIMENTOS
====================================================================================================
    - Complete ANTLR 4.5.3 Tool.
    - Java Development Kit (JDK) Versão 6 ou Superior.



====================================================================================================
CONFIGURAÇÃO DO AMBIENTE
====================================================================================================
    1. É necessário especificar temporariamente na variável de ambiente CLASSPATH o caminho do 
    ANTLR. Para isto, execute o comando abaixo substituindo o caminho 
    C:\compilers-cin\aulas-praticas\ap1\antlr-4.5.3-complete.jar pelo caminho do ANTLR do seu
    computador.
    
    > SET CLASSPATH=.;C:\compilers-cin\aulas-praticas\ap1\antlr-4.5.3-complete.jar;%CLASSPATH%
    
    2. Caso o caminho para os arquivos binários do JDK não esteja presente na variável de ambiente
    PATH, torna-se necessário especifica-lo temporariamente. Para isto, execute o comando abaixo
    substituindo o caminho C:\Program Files\Java\jdk1.8.0_65\bin\ pelo caminho do seu computador.
    
    > SET PATH=.;C:\Program Files\Java\jdk1.8.0_65\bin\;%PATH%



====================================================================================================
COMPILAÇÃO E EXECUÇÃO
====================================================================================================
    Como exemplo para essa etapa, utilizaremos a gramática Hello que se encontra no diretório 
    compilers-cin\aulas-praticas\ap1\grammars\.
    
    1. Abra a linha de comando do Windows (cmd.exe) no diretório compilers-cin\aulas-praticas\ap1\grammars\.
    2. Execute em sequência os comandos abaixo para gerar os lexers e parsers em Java:
    
    > java org.antlr.v4.Tool Hello.g4
    > javac Hello*.java
    
    3. Execute o comando abaixo para testar a gramática Hello e exibir a Parse Tree Inspector da GUI do ANTLR:
    
    > java org.antlr.v4.runtime.misc.TestRig Hello r -gui
    
    4. Ao executar o comando anterior, a ferramenta ficará a espera de uma entrada, digite a entrada abaixo e
    em seguida aperte a tecla ENTER e depois a combinação CTRL+Z para que a ferramenta processe a entrada:
    
    hello world
    
    5. Será exibida a Parse Tree Inspector da GUI do ANTLR mostrando que símbolo não terminal 'r' é decomposto
    no símbolo terminal 'hello' seguido pelo identificador 'world'.