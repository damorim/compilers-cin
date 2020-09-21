// nenhum operando de '%' pode ser float
// int se converte para float, e vice-versa
// string não pode ser operando de qualquer operador
// o typo retornado por return deve ser o mesmo declarado para a função
// função sem retorno ou com retorno sem expressão é void
// variáveis devem ser declaradas antes de ser usadas
/* 
	em caso de algum erro acima, imprimir:
	"nome_do_arquivo:número_da_linha:número_da_coluna: semantic error: mensagem_opcional
	 número_da_linha | linha"
*/


void empty () {
	return;
}

int function (int a, int b) {
	int c = a % b;
	b = -a * (b + c); // b = "oi" * 3;
	return c - b/2;   // return 3.5;
}

float aster (float red, float green, float blue) {
	float bright = 0.3 * red + 0.5 * green + 0.2 * blue;
	red = red / bright;
	green = green / (bright * bright); // green = bright * (arroz - 3.5);
	blue = (1 - blue) / bright;        // blue = blue % bright;
	return red + green + blue;         // comente esta linha
}

int main () {
	float color = aster(0.5, 0.2, 1) * 3.14159265;
	return moi(1, 2);
}
