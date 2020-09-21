#include <stdio.h>
#include <math.h>
#include "meh.h"
#define PI 3.14159265

// comment
int unum = 1;

int scalar (int a, int b) {
	for (int i = 0; i < 10; i++) {
		printf("boo\n");
		if (a < b) a -= b;
	}
	/***********
	 * comment *
     **********/

	if (a > b) {
		printf("a > b\n");
	} else if (a < b) {
		printf("a < b\n");
	} else {
		printf("a == b\n");
	}

	return sqrt(a * a + b * b - a * b);
}

float pi = 3.14159265;

int main () {
	int quantas_trincas = 33, valor1 = 821;
	float tk[3] = { -1.0, 0.0, 1.0 };
	printf("scalar(%d, %d) = %d\n", quantas_trincas, valor1 - tk[0], scalar(quantas_trincas, valor1));
	return 0;
}
