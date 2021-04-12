float glob1 = 4.9;
int glob2 = 49;

float return3() {
	return 3.0;
}

float operate(int k1, int k2) {
	float valor = (k1 * 3.0 + k2 * 7.0)/10;
	k2++;
	return valor + 5;
}


int main () {

	int a = 3 * 5 - 2 + 1;

	int b = 4 + a * 3;

	int c = a * b * - 1;

	int d = c * a + b * c - a;

	float e = a - b + c * -d;

	float f = a * b + c / d;

	float g = a + f;

	float h = glob1 + g;

	float i = (return3() + 5) * h;

	return a + b + c - d + glob2;
}


