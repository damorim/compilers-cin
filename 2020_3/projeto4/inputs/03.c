
int half (int x) {
	return x / 2;
}

int scalar (int a, int b) {
	a *= -b;
	b++;

	return (half(a * a + b + (b - a) * b) + 3)/2;
}

float fscalar (float a, float b) {
	float result = ((a + b) / (a - b)) * (a * b) / (a / b);
	return result;
}


int main () {
	int quantas_trincas = 33, valor1 = 821;
	int tk2 = 1, tk0 = -1;
	valor1 = scalar(quantas_trincas - tk2, valor1) - tk0;

	return valor1;
}
