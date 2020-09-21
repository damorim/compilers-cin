#include <stdio.h>

int main () {
	int a = 3 * 5 - 2 + 1;
	printf("a = %d\n", a);

	int b = 4 + a * 3;
	printf("b = %d\n", b);

	int c = a * b * - 1;
	printf("c = %d\n", c);

	int d = c * a + b * c - a;
	printf("d = %d\n", d);

	int e = a - b + c * -d;
	printf("e = %d\n", e);
	return 0;
}


