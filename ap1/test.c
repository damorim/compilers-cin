#include <stdio.h>

int scalar (int a, int b) {
	return sqrt(a * a + b * b - a * b);
}

int main () {
	int a = 3, b = 8;
	printf("scalar(%d, %d) = %d\n", a, b, scalar(a, b));
	return 0;
}
