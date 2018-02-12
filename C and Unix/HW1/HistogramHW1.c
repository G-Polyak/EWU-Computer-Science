#include <stdio.h>
#include <ctype.h>

#define MAXROWS 100
#define NUM_ALPHABETS 26
void readSentence(char sentence[]);
void countLetters(char sentence[], int frequency[], char alphabets[]);

int main(){

	int count, i, j, k;

	char sentence[MAXROWS];
	char alphabets[] = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
	int frequency[NUM_ALPHABETS];

	for (i = 0; i < NUM_ALPHABETS; i++){
		frequency[i] = 0;
	}

	// step 1:Write a function that reads a sentence from the keyboard (2 pts)
    readSentence(sentence);
        
	// step 2:Find out the frequency of the alphabets  in the sentence, ignore uppercase or lowercase in the sentence(8 pts)
    countLetters(sentence, frequency, alphabets);
    


	printf("%s%12s%15s\n\n", "Alphabets", "Frequency", "Histogram");

	for (j = 0; j < 26; j++){
	     printf("%8c %10d            ", alphabets[j], frequency[j]);
	     for (k = 0; k < frequency[j]; k++)
		  printf("*");
		  printf("\n");
	}

	return 0;
}

void countLetters(char sentence[], int frequency[], char alphabets[]) {
    
    int i; int j; char select;
    for (i = 0; i < sizeof(sentence)/sizeof(char); i++) {
        
        select = toupper(sentence[i]);
        for (j = 1; j < 26; j++) {
            
            if (alphabets[j] == select) {
                frequency[j]++;
            }
            
        }

    }
    
}

void readSentence(char sentence[]) {
    fgets(sentence, sizeof(sentence), stdin);
}
