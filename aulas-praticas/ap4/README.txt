Arquivo Readme para 4ª Aula Prática de Compiladores de 2017.2
Última atualização em 30 de Novembro de 2017 - 00:42 por Ihago Henrique - ihls@cin.ufpe.br.



======================================================================
VISÃO GERAL
======================================================================
    Este documento explica como configurar o ambiente para execução do ILAsm no seu Windows.



======================================================================
REQUERIMENTOS
======================================================================
    - Microsoft.NET Framework v4.0



======================================================================
CONFIGURAÇÃO DO AMBIENTE
======================================================================
    1. Caso o caminho para os arquivos binários do ILAsm não esteja presente na variável de ambiente
    PATH, torna-se necessário especifica-lo temporariamente. Para isto, execute o comando abaixo
    substituindo o caminho C:\Windows\Microsoft.NET\Framework\v4.0.30319\ pelo caminho do seu computador.
    
    > SET PATH=.;C:\Windows\Microsoft.NET\Framework\v4.0.30319\;%PATH%



======================================================================
EXECUÇÃO TESTE
======================================================================
    Como exemplo para essa etapa, utilizaremos o código HelloWorld que se encontra no diretório 
    compilers-cin\aulas-praticas\ap4\.
    
    1. Abra a linha de comando do Windows (cmd.exe) no diretório compilers-cin\aulas-praticas\ap4\.
    2. Execute o comando abaixo para gerar um arquivo executável:
    
    > ilasm HelloWorld.il
    
    3. Ao executar o comando anterior será gerado um arquivo executável no mesmo diretório e com o
    mesmo nome do arquivo do código passado como entrada.
    4. Para executar o arquivo recém gerado, execute o comando abaixo:
    
    > HelloWorld.exe
    
    5. Será exibida a string "Hello World".