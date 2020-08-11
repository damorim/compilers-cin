# IF688 - Teoria e Implementação de Linguagens Computacionais

## Engenharia da Computação, [Centro de Informática](http://www.cin.ufpe.br), ([UFPE](http://www.ufpe.br))

### Instrutores

* **Professor** 
  * [Marcelo d'Amorim](http://cin.ufpe.br/~damorim/) (damorim@cin.ufpe.br)
* **Monitores** 
  * Felipe Cavalcanti Guerra Ramos (fcgr@cin.ufpe.br)
  * Rodrigo Brayner Lyra (rbl2@cin.ufpe.br)
  
### Horário e Local de Aulas
* Segunda-feira (10h-12h) e Quarta-feira (8h-10h), pela internet

### Ementa

Este curso explora os princípios, algoritmos, e estruturas de dados envolvidos na teoria e implementação de compiladores. 
O conteúdo planejado inclui uma introdução aos princípios e técnicas de construção de compiladores, conceitos básicos da teoria de linguagens, análise léxica, análise sintática, análise semântica, representação de código intermediário, ambientes de execução, análise estática, otimização de código, geração de código.

### Referências

#### Bibliografia
- [Alfred V. Aho, Monica S. Lam, Ravi Sethi, Jeffrey D. Ulmann, Compilers: Principles, Techniques and Tools , 2nd Edition, Addison Wesley, 2007](https://www.saraiva.com.br/compiladores-principios-tecnicas-e-ferramentas-1998960.html)
#### Online
- [MIT](https://ocw.mit.edu/courses/electrical-engineering-and-computer-science/6-035-computer-language-engineering-sma-5502-fall-2005/)

### Objetivos

O objetivo da disciplina é compreender detalhes do processo de compilação e como aplicar os conceitos em outros contextos.
A disciplina apresenta conceitos básicos sobre interpretação, análise, e síntese de programas. 

### Metodologia

Utilizaremos aulas tradicionais, aulas práticas e um mini projeto.

### Recursos

- [Google Classroom](https://classroom.google.com) - Código:  5fcvpvn
- [Repositório Github](https://github.com/damorim/compilers-cin)
- [Canal no Youtube](https://www.youtube.com/channel/UCgWmrlXTGDUpWV0RjlEum2w)

### Ferramentas
- [Antlr (ANother Tool for Language Recognition)](https://www.antlr.org/)
- [LLVM](https://llvm.org/)

### Avaliação
* (`N1`+`N2`)/2, onde:
  * `N1` = `Prova1` (70%) + `Aulas práticas` (30%)
    * `Prova1` = Teste com [assunto dado até o momento
    * `Aulas práticas` = 3 Tarefas passadas durante primeira unidade
  * `N2` = `Prova2` (70%) + `Mini projeto` (30%)
    * `Prova2` = Teste com [assunto dado a partir de Prova1 
    * `Mini Projeto` = Projeto da cadeira
* `Final`: Teste com todo o assunto da matéria

- Observação:
  - Trabalhos **“CTRL-C + CTRL-V”** terão nota **zero** (vale tanto para cópia de colegas, como para trabalhos copiados da internet).

### Provas anteriores
- [Clique aqui](https://github.com/damorim/compilers-cin/tree/master/provas)

### Notas
- [Clique aqui](https://docs.google.com/spreadsheets/d/1bBrZeFmS-fFnsUazjbqWenF_2S8vPdO3VOEtcmWQOMc)

### Plano de Ensino

**Atenção!** 
*Este plano de ensino está sujeito a alterações durante o semestre, visite frequentemente a página para obter a versão mais atualizada, ou acompanhe os updates no repositório.*

| # | Data | Assunto | Vídeos |
|:---:|:----:|:----------------------:|:----------------------|
| 01 | 17/08/2020 (seg) | [Introdução](/slides-aulas/intro.pptx) (Aula Síncrona) | --- |
| 02 | 19/08/2020 (qua) | [Introdução](/slides-aulas/intro.pptx) | --- |
| 03 | 24/08/2020 (seg) | [Conceitos Básicos](/slides-aulas/conceitos-basicos.pptx) | - [linguagens formais e autômatos](https://youtu.be/sAp6p1fCHCg)<br>- [ambiguidade, precedência, e associatividade](https://youtu.be/JZ_ZeZvO-gA) |
| 04 | 26/08/2020 (qua) | [Conceitos Básicos](/slides-aulas/conceitos-basicos.pptx) | - [topdown e bottomup parsing, recursive-descent parsing](https://youtu.be/T3HvXoWhdj8)<br>- [AST e visitors](https://www.youtube.com/watch?v=3PMzwvr441U) |
| 05 | 31/08/2020 (seg) | [Análise Léxica](/slides-aulas/analise-lexica.pptx) | --- |
| 06 | 02/09/2020 (qua) | [Análise Sintática](/slides-aulas/analise-sintatica.pptx)| --- |
| 07 | 07/09/2020 (seg) | Feriado da Independência do Brasil | --- |
| 08 | 09/09/2020 (qua) | [Análise Sintática](/slides-aulas/analise-sintatica.pptx) | --- |
| 09 | 14/09/2020 (seg) | [Demo](https://github.com/damorim/compilers-cin/tree/master/demos) | --- |
| 10 | 16/09/2020 (qua) | [**Projeto** - Módulo Lexer e Parser (Aula Síncrona)](https://github.com/damorim/compilers-cin/tree/master/ap1) | --- |
| 11 | 21/09/2020 (seg) | [Análise Semântica](/slides-aulas/analise-semantica.pptx) | --- |
| 12 | 23/09/2020 (qua) | [Análise Semântica](/slides-aulas/analise-semantica.pptx) | --- |
| 13 | 26/09/2020 (sáb) | **Projeto** - Entrega Módulo Lexer e Parser | --- |
| 14 | 28/09/2020 (seg) | [Análise Semântica](/slides-aulas/analise-semantica.pptx)  | --- |
| 15 | 30/09/2020 (qua) | [**Projeto** - Módulo de Análise Semântica (Aula Síncrona)](https://github.com/damorim/compilers-cin/tree/master/ap2) | --- |
| 16 | 05/10/2020 (seg) | [Representações Intermediárias](/slides-aulas/representacoes-intermediarias.pptx) | --- |
| 17 | 07/10/2020 (qua) | [Representações Intermediárias](/slides-aulas/representacoes-intermediarias.pptx) | --- |
| 18 | 10/09/2020 (sáb) | **Projeto** - Entrega Módulo de Análise Semântica | --- |
| 19 | 12/10/2020 (seg) | Feriado de Nossa Sra. de Aparecida | --- |
| 20 | 14/10/2020 (qua) | [Análise Estática e Otimização]() | --- |
| 21 | 19/10/2020 (seg) | [Análise Estática e Otimização]() | --- |
| 22 | 21/10/2020 (qua) | [Análise Estática e Otimização]() | --- |
| 23 | 24/10/2020 (sáb) | [**Projeto** - Módulo de Otimização (Aula Síncrona)](https://github.com/damorim/compilers-cin/tree/master/ap3) | --- |
| 24 | 26/10/2020 (seg) | [Aplicações  de Compiladores]() | --- |
| 25 | 28/10/2020 (qua) | [Gerenciamento de Memória e Geração de Código](/slides-aulas/ambiente-exec-e-geracao-codigo.pptx) | --- |
| 26 | 31/10/2020 (sáb) | **Projeto** - Entrega Módulo de Otimização | --- |
| 27 | 02/11/2020 (seg) | Feriado de Finados | --- |
| 28 | 04/11/2020 (qua) | [Gerenciamento de Memória e Geração de Código](/slides-aulas/ambiente-exec-e-geracao-codigo.pptx) | --- |
| 29 | 07/11/2020 (sáb) | [**Projeto** - Módulo de Geração de Código (Aula Síncrona)](https://github.com/damorim/compilers-cin/tree/master/mini-projeto)| --- |
| 30 | 09/11/2020 (seg) | 
| 31 | 11/11/2020 (qua) | **Prova Final e Segunda Chamada**  | --- |
| 32 | 16/11/2020 (seg) |
| 33 | 17/11/2020 (ter) | Último dia de aulas no Siga | --- |
| 34 | 18/11/2020 (qua) | **Projeto** - Entrega Módulo de Geração de Código | --- |
| 35 | 01/12/2020 (ter) | Último dia para realizar provas finais no Siga | --- |
| 36 | 09/12/2020 (sex) | Último dia para lançamento de notas no Siga | --- |

