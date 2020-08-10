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
| 01 | 17/08/2020 (seg) | [Introdução, admin](/slides-aulas/intro.pptx) | --- |
| 02 | 19/08/2020 (qua) | [Introdução](/slides-aulas/intro.pptx) | --- |
| 03 | 22/08/2020 (sáb) | [Conceitos Básicos](/slides-aulas/conceitos-basicos.pptx) | - [linguagens formais e autômatos](https://youtu.be/sAp6p1fCHCg)<br>- [ambiguidade, precedência, e associatividade](https://youtu.be/JZ_ZeZvO-gA) |
| 04 | 24/08/2020 (seg) | [Conceitos Básicos](/slides-aulas/conceitos-basicos.pptx) | - [topdown e bottomup parsing, recursive-descent parsing](https://youtu.be/T3HvXoWhdj8)<br>- [AST e visitors](https://www.youtube.com/watch?v=3PMzwvr441U) |
| 05 | 26/08/2020 (qua) | [Análise Léxica](/slides-aulas/analise-lexica.pptx) | --- |
| 06 | 29/08/2020 (sáb) | [Análise Sintática](/slides-aulas/analise-sintatica.pptx)| --- |
| 07 | 31/08/2020 (seg) | [Análise Sintática](/slides-aulas/analise-sintatica.pptx) | --- |
| 08 | 02/09/2020 (qua) | [Demo](https://github.com/damorim/compilers-cin/tree/master/demos) | --- |
| 09 | 05/09/2020 (sáb) | [Aula Prática 1](https://github.com/damorim/compilers-cin/tree/master/ap1) | --- |
| 10 | 07/09/2020 (seg) | Feriado da Independência do Brasil | --- |
| 11 | 09/09/2020 (qua) | [Análise Semântica](/slides-aulas/analise-semantica.pptx) | --- |
| 12 | 12/09/2020 (sáb) | Feriado de N. Sra. Aparecida | --- |
| 13 | 14/09/2020 (seg) | [Análise Semântica](/slides-aulas/analise-semantica.pptx) | --- |
| 14 | 16/09/2020 (qua) | [Análise Semântica](/slides-aulas/analise-semantica.pptx)  | --- |
| 15 | 19/09/2020 (sáb) | [Aula Prática 2](https://github.com/damorim/compilers-cin/tree/master/ap2) | --- |
| 16 | 21/09/2020 (seg) | [Aula Prática 3](https://github.com/damorim/compilers-cin/tree/master/ap3) | --- |
| 17 | 24/09/2020 (qua) | Aula de Revisão | --- |
| 18 | 26/09/2020 (sáb) | **1º Exercício Escolar** | --- |
| 19 | 28/09/2020 (seg) | [Representações Intermediárias](/slides-aulas/representacoes-intermediarias.pptx) | --- |
| 20 | 30/09/2020 (qua) | [Representações Intermediárias](/slides-aulas/representacoes-intermediarias.pptx) | --- |
| 21 | 03/10/2020 (sáb) | [Análise Estática e Otimização]() | --- |
| 22 | 05/10/2020 (seg) | [Apresentação da Especificação do Mini Projeto](https://github.com/damorim/compilers-cin/tree/master/mini-projeto) | --- |
| 23 | 07/10/2020 (qua) | [Análise Estática e Otimização]() | --- |
| 24 | 10/10/2020 (sáb) | [Análise Estática e Otimização]()| --- |
| 25 | 12/10/2020 (seg) | [Aplicações  de Compiladores]() | --- |
| 26 | 14/10/2020 (qua) | [Gerenciamento de Memória e Geração de Código](/slides-aulas/ambiente-exec-e-geracao-codigo.pptx) | --- |
| 27 | 17/10/2020 (sáb) | [Gerenciamento de Memória e Geração de Código](/slides-aulas/ambiente-exec-e-geracao-codigo.pptx) | --- |
| 28 | 19/10/2020 (seg) | Aula de Revisão| --- |
| 29 | 21/10/2020 (qua) | Aula Reservada para a Entrega do Mini Projeto| --- |
| 30 | 24/10/2020 (sáb) | Aula Reservada para a Entrega do Mini Projeto | --- |
| 31 | 26/10/2020 (seg) | **2º Exercício Escolar** | --- |
| 32 | 28/10/2020 (qua) | Revisão de Provas | --- |
| 33 | 31/10/2020 (sáb) | **Prova Final e Segunda Chamada**  | --- |
| 34 | 02/11/2020 (seg) | Feriado de Finados | --- |
| 35 | 04/11/2020 (qua) |
| 36 | 07/11/2020 (sáb) |
| 37 | 09/11/2020 (seg) |
| 38 | 11/11/2020 (qua) |
| 39 | 14/11/2020 (sáb) |
| 40 | 16/11/2020 (seg) |
| -- | 17/11/2020 (ter) | Último dia de aulas no Siga | --- |
| -- | 01/12/2020 (ter) | Último dia para realizar provas finais no Siga | --- |
| -- | 09/12/2020 (sex) | Último dia para lançamento de notas no Siga | --- |

