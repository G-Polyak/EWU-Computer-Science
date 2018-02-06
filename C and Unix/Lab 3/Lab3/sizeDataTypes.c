#include <stdio.h>
int main() {

short a = 24;
int b = 24;
long c = 24;
long double d = 24.000000000000000000;
double e = 24.0;
printf("Variables a - e have all been initialized as 24\nsize of a is %zu bytes, b is %zu bytes, c is %zu bytes, d is %zu bytes, and e is %zu bytes", sizeof(a), sizeof(b), sizeof(c), sizeof(d), sizeof(e));
printf("\n a is a short, b is an int, c is a long, d is a long double, e is a double\n");

}

