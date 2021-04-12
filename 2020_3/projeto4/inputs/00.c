int square (int x) {
	return x * x;
}

void splash (int k) {
	return;
}

float ResDiv(float k1, float k2) 
{
    float k3 = k1 / (k1 + k2);
    return k3;
}


int main () {
	int a = 8;
	int b = 2;

	splash(a);

	float k1 = 50.0 * a;
	float k2 = 150.0 * b;
	float k = ResDiv(k1, k2);

	int c = a / b;
	c /= 3 * (2 - b) + 4;
	return square(c + 1);
}
