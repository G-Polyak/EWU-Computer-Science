#include <stdio.h> //charBug.c
int main( )
{
char c;
char dummy[10];
printf( "Enter a value :");
c = getchar( )  ;
while( c != 'q' && c != 'Q') {
if (c != '\n') { //this condition not being there was the problem
printf( "\nYou entered: ");
putchar( c );
printf("\nEnter a value :");
}
c = getchar();
}
return 0;
}
