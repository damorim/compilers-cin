// Int Expression type checking Test

// Test: return int
// Should Pass
int square(int num1) {
	return num1 * num1;
}

int fatorial(int n) {
	if (n > 0)
		return fatorial(n-1) * n;
	else
		return 1;
}

float fsquare(float val) {
	return val * val;
}

void doing(float f, int ik) {
	return f;
}

int main () {
	// Test: int op int
	// Should Pass
	int num = 2;
	num = 3 + 2;
	num = num - 2;
	num = square(num) * num;
	num = num / 3;
	num = 2 + 5 * 3 / 2 - num * 4 / 3;
	
	int num2 = 0;
	num2 += 20 * num - fatorial(square(2));
	num2 -= num;
	num2 /= 10 / 3 + 2*3;
	num2 *= 3 + num2;
	
	// Test: float = int
	// Should Pass
	float t = num2;
	t = fatorial(4);
	t = num2*3 + num/2 - 4;
	t = num2 / num * 3;
	
	// Test: int = float
	// Emit Warning - Possible loss of information (converting float to int)
	int f = 2.0;
	f = num + (2 - t);
	f = fsquare(5) * t;
	
	int hello = 450 + 5.0;
	hello += 10.5;
	hello *= hello / 5.0;

	
	// Test: func. parameter type
	// Should Pass
	int x = square(4);
	int y = fatorial(5);
	int z = x * y;
	
	// Emit Warning - possible loss of information (expecting int, passed float)
	x = square(2.0);
	y = fatorial(t);
	z = square(fsquare(2)/2);
	
	// Test: var = VOID
	// Emit Error
	int k = doing(t, x);
	float u = (doing(4, 2) + 4)*2;
	
	uv = 1.0;
	return doing(2.0, x);
}