#include <stdio.h>
#include <math.h>

int scalar (int a, int b) {
	return sqrt(a * a + b * b - a * b);
}

int main () {
	int quantas_trincas = 33, valor1 = 821;
	printf("scalar(%d, %d) = %d\n", quantas_trincas, valor1, scalar(quantas_trincas, valor1));
	return 0;
}
