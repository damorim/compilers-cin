#include <stdio.h>
#include <math.h>
//#include "meh.h"
#define PI 3.14159265

// comment
//int unum = 1;

int half (int x) {
	return x / 2;
}

int scalar (int a, int b) {
	//int array[10];
	/*
	for (int i = 0; i < 10; i++) {
		if (a < b) a -= b;
		array[i] = a;
	}
	// ***********
	// * comment *
	// ***********
	if (a > b) {
		a /= b - unum * 2.5 - (4 <= a - b);
	} else if (a < b) {
		b += a / -unum + (8 >= b * a);
	} else {
		b = a;
	}
	*/
	a *= -b;
	b++;
	//array[7 - 3] = 3;
	//array[array[2 * 3 - 2]] = 2;

	return half(a * a + b + (b - a) * b) + 3/*+ array[3] + array[4]*/;
}

//int pi = 3;

int main () {
	int quantas_trincas = 33, valor1 = 821;
	//int tk[3] = { -1, 0, 1 };
	int tk2 = 1, tk0 = -1;
	valor1 = scalar(quantas_trincas - tk2, valor1) - tk0;
	return valor1;
}
