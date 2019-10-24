int nom_nom = 88;
int arroz;

int aqua_baldo () {
	int a0 = 32 * 5;
	int a1 = a0 % 11 - 2 ;
	int a2 = 11 % (6 + a1);
	return (1 - a0) + a1 % a2;
}

int function7 (int a, int b) {
	int c = a % b;
	b = -a * (b + c);
	return c - b/2;
}

void kk (int hh) {
	arroz = hh / 2;
}

int aster (int red, int green, int blue) {
	int bright = (3 * red + 5 * green + 2 * blue) / 10;
	red = 255 * red / bright;
	green = 255 * green / bright;
	blue = 255 * (blue - 127) / bright;
	return red + green + blue;
}

int main () {
	int hmm;
	int color = aster(123, 111, 240);
	nom_nom = aqua_baldo();
	kk(color);
	hmm = function7(7, 38) - arroz;
	return 0;
}
