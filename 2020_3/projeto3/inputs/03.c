#include <stdio.h>
#include <math.h>
#include "meh.h"
#define PI 3.14159265

// comment
int unum = 1;

float half (float x) {
	return x / 2;
}

int scalar (int a, int b) {
	int array[10];
	for (int i = 0; i < 10; i++) {
		if (a < b) a -= b;
		array[i] = a;
	}
	/***********
	 * comment *
	 ***********/
	if (a > b) {
		a /= b - unum * 2.5 - (4 <= a - b);
	} else if (a < b) {
		b += a / -unum + (8 >= b * a);
	} else {
		b = a;
	}

	return half(a * a + b + (b - a) * b);
}

float pi = 3.14159265;

int main () {
	int quantas_trincas = 33, valor1 = 821;
	float tk[3] = { -1.0, 0.0, 1.0 };
	valor1 = scalar(quantas_trincas - tk[2], valor1) - tk[0];
	return 0;
}

// in variable definition either assign to each an expression or to none
// variable and function are first defined, then the remainder are processed
// no scope considered: every variable is has a unique name
// check for the correct number of parameters in a function call
// undeclared variable/function/array -> void expression
// do not use exit -> void propragate through expression and takes it all
