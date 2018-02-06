#include <stdio.h>
int main() {

int a =3;
int b =4;
double c = ++a + b++;
int size = sizeof(c/a);
printf("a = %d \t b = %d \t c = %.2f \t size = %d \n", a, b, c, size);

}

