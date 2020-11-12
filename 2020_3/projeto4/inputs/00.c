int square (int x) {
	return x * x;
}

void splash () {
	// nothing happens
}

int main () {
	int a = 8;
	int b = 2;

	splash();

	int c = a / b;
	c /= 3 * (2 - b) + 4;
	return square(c + 1);
}
