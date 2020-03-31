import java.util.Scanner;
import java.util.Random;

public class Hangman
{
	public static void main(String [] args)
	{
		Scanner input=new Scanner(System.in);
		Random random=new Random();

		System.out.println("\nWELCOME TO THE HANGMAN GAME!\n");
		System.out.println("Hangman Goal:");
		System.out.println("The goal of Hangman is the user must guess all the letters in a given phrase with a preset amount of 8 lives.\n");
		
		System.out.println("Hangman Overview:");
		System.out.println("	1. A random phrase is generated for the user from a list of phrases.");
		System.out.println("	2. The phrase is hidden behind asterisks (*) and shown to the user.");
		System.out.println("	3. The user guesses characters to see if they can guess the phrase correctly in the allotted amount of lives (8).");
		System.out.println("	4. If they guess the phrase correctly in the given amount of lives (8), they win the game!");
		System.out.println("	5. If they do not guess the phrase correctly in the given amount of lives (8), they loose the game!\n");

		System.out.println("Hangman Rules:");
		System.out.println("	1. The user is given a preset amount of 8 lives (guesses) for which they will guess characters in a randomly generated phrase.");
		System.out.println("	2. The user is allowed to enter in guesses embodying uppercase or lowercase letters, along with digits."); 
		System.out.println("		- If a guess is not an uppercase or lowercase letter or a digit (e.g., ‘%’) the user will be notified but not penalized a life.");
		System.out.println("	3. If the user guesses a character in the phrase correctly, the asterisk (*) behind the correctly guessed character(s)");
		System.out.println(" 	is removed and no lives (guesses) are deducted from their current total of lives.");
		System.out.println("	4. However, if the player guesses a character incorrectly, the user is notified of their wrong guess and one life is");
		System.out.println("	deducted from their current total of lives.");
		System.out.println("	5. If a user enters a guess that they have already previously entered, whether it is incorrect or correct, the user is");
		System.out.println("	notified of their repeated guess, however; they are not penalized a life.");
		System.out.println("	1. The previous three rules will remain applicable until:");
		System.out.println("		a) the user uses up their lives or");
		System.out.println("		b) the user guesses the entire phrase correctly.");
		System.out.println("	1. The user is given a preset amount of 8 lives (guesses) for which they will guess characters in a randomly generated phrase.\n");

		String[] phraseList = {
			"hello world", 
			"i like dogs", 
			"airplanes fly", 
			"boats float", 
			"people sit", 
			"hotdogs on buns", 
			"fried chicken",
			"hot sauce", 
			"morgan freeman", 
			"charlie brown"
		};

		int randomPhraseNumber = (int)(Math.random() * phraseList.length);// generates a random number from 0 to the length of phrase list (10)

		String phrase = phraseList[randomPhraseNumber]; // picks a phrase from the String [] phraseList at the integer that was randomly generated

		int i=0;
		int index=0;
		char ch = '*'; // randomized phrase will be replaced with asterick (*)
		System.out.println("Guess the Phrase Below: \n");
		StringBuilder newStr = new StringBuilder(phrase);
		while(i<phrase.length())
		{
			index = i;
			if(phrase.charAt(i)!=' ') 
        		newStr.setCharAt(index, ch);
			i++;
		}
		System.out.println(newStr);// prints phrase with astericks(*)
		StringBuilder newStr2 =new StringBuilder(newStr); // new instance of class StringBuilder, making str equal to str2 (acts as temp variable)
		String attempts = ""; 
		
		int numGuess = 0; // lives counter intiatilized at 0
		while(numGuess<8) // places limit of lives at 8
		{
			StringBuilder lastStr =new StringBuilder(newStr2); // new instance of class StringBuilder, to check guesses based upon the temp variable 
			boolean already = false; 
			System.out.println("\nLives left: " + (8-numGuess));
			System.out.print("Guess a letter: ");
			String g = input.next();
			String a = input.nextLine(); // safety precaution
			char guess = g.charAt(0);

			// checking to see if guess is a letter/number
			boolean typeGuess=false;
			int ascii = guess;
			if((ascii>=65)&&(ascii<=90))
				guess = Character.toLowerCase(guess);
			else if((ascii>=97)&&(ascii<=122))
				typeGuess=false;
			else if((ascii>=48)&&(ascii<=57))
				typeGuess=false;
			else
				typeGuess=true;

			// checking to see if user already guessed the character
			int y=0;
			while(y<attempts.length())
			{
				if(attempts.charAt(y)==guess)
					already=true;
				y++;
			}
			attempts+=guess;// stores guesses in String attempts

			// checking to see if user guesses the right character or not
			int x=0;
			while(x<phrase.length())
			{
				if(phrase.charAt(x)==guess)// checks if guess equals a character at index 'x' in the randomly generated phrase
	        		newStr2.setCharAt(x,guess);
				x++;//increments through phrase
			}


			if((newStr2.toString()).equals(phrase)) // if user guesses all characters correctly
			{
				System.out.println(newStr2 + "\nYou won!!!");
				break;
			}
			else if(typeGuess==true) // if user didn't enter a letter/number
				System.out.println("\nInvalid input.\n" + newStr2);

			else if(already==true) // if user already guessed this character
				System.out.println("\nYou already guessed this.\n" + newStr2);
			
			else if((lastStr.toString()).equals((newStr2.toString()))) // if user guesses the wrong character
			{

				System.out.println("\nYou guessed incorrectly.\n"+newStr2);
				numGuess++;

			}
			else // if user guesses correctly
			{
				System.out.println(newStr2);
				lastStr = newStr2;
			}
		}
		System.out.println("Game over.");
	}
}