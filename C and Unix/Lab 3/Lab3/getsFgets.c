#include <stdio.h>
//getsFgets.c
int main() {
char name[100];
printf("Please enter a name: ");
gets(name); //no longer specify size and input, instead uses standard input
printf("The name you entered is %s \n", name);
return 0;
}
//gets() doesn't allow to specify the length of the buffer to store the string in and because of that you can keep entering data past the end of your buffer

//fgets() allows you to specify a max string length as well as which input buffer you use
