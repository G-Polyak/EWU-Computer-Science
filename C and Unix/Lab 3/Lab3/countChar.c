#include <stdio.h>
#include <ctype.h>
#define MAXSIZE 100
int main(){
char ch;
int i;
char sentence[MAXSIZE];
int alphabetsCount = 0;
printf("Type a sentence: \n");
// write the code that reads a sentence from the keyboard 2pts
fgets(sentence, sizeof(sentence), stdin);
printf("Type a character that you'd like to find the number of occurrences in a sentence:\n");
// write the code that reads a character from the keyboard 2 pts
ch = getchar();
// write the code that counts the number of character in the sentence; 2 pts
for (i = 0; i < sizeof(sentence)/sizeof(int); i++) {
    if (tolower(sentence[i]) == tolower(ch)) {
        alphabetsCount++;
    }
}
// ignore uppercase or lowercase
printf("Alphabet %c has a frequency %d\n", ch, alphabetsCount);
return 0;
}
