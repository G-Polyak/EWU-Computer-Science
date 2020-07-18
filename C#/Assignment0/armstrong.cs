//George Polyak
//Assignment 0 - Armstrong Numbers

using System;

public class Armstrong {
	
	public static void Main(string[] args) {
		
		Console.Write("Please input an integer whose value is 10 or greater: ");
		int input = Int32.Parse(Console.ReadLine());
		Console.WriteLine("ARMSTRONG NUMBERS FOUND FROM 10 THROUGH " + input + "\n");
		int d1, d2, d3, temp;
		int count = 0;
		for(int i = 10; i < input; i++) {
			
			d1 = i - ((i / 10) * 10);
			d2 = (i / 10) - ((i / 100) * 10);
			d3 = (i / 100) - ((i / 1000) * 10);
			temp = (d1 * d1 * d1) + (d2 * d2 * d2) + (d3 * d3 * d3);
			if (temp == i) {
				
				Console.WriteLine(temp);
				count++;
				
			}
			
		}
		Console.Write("\nTOTAL NUMBER OF ARMSTRONG NUMBERS FOUND WAS " + count);
		
	}
	
}