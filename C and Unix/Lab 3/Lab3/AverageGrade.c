#include <stdio.h>
// AverageGrade.c
int main(){
int i = 0;
int grade;
double avg;
int sum = 0;
int j = 0;
int grades[10];
printf("Please input 10 grades :\n");
while(i < 999) {
scanf("%d", &grade);
if (grade == -1000) {
i = 999;
} else {
    grades[i] = grade;
    sum += grade;
    avg = sum / (i+1);
    printf("Sum is: %d \nAverage is: %.2f \n", sum, avg);
    i ++;
    }
}
return 0;
}
