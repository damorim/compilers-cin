/* nome da gramática -- deve ser o mesmo nome do arquivo .g4 */
grammar C;

/* regra raiz da gramática */
file :  ;


/* lexer */  


/*
MANUAL

substitua 'implementar' por uma implementação

caracteres especiais para expressões regulares {
	'xyz'   ->  os caracteres rodeados por ' ' são interpretados literalmente 
	\x		->  altera a interpretação do caracter x, se ele tiver outra (\t: tab, \(: o caracter que abre parênteses)
	a(bc)d  ->  destaca a subexpressão bc
	x | y   ->  aceita a subexpressão x ou y
	[x\yz]	->  equivalente a ('x'|\y|'z'), tal que x, \y e z são caracteres
	[x]		->  equivalente a 'x'
	x*		->  aceita 0 ou mais x's
	x+		->  aceita 1 ou mais x's
	x?		->  aceita 0 ou 1 x
	.       ->  aceita qualquer caracter
	.*      ->  aceita 0 ou mais caracteres diferentes de \n (guloso)
	.*?     ->  aceita 0 ou mais caracteres diferentes de \n (não-guloso)

	no ANTLR esses caracteres especiais podem ser utilizados nas regras da gramática também
	ex.:
		expr ('+'|'-') expr
	estabelece que os dois sinais têm a mesma precedência
		'(' (expr (',' expr)*)? ')'
	indica que dentro destes parênteses pode haver zero ou mais expressões separadas por vírgulas
}

regras da gramática {
	nome_da_regra
		: uma seqüência de regras que satisfazem esta
		| outra
		| e mais outra
		;

	dentro de uma regra a primeira opção tem maior precedência (útil em expressões matemáticas)
}
*/
