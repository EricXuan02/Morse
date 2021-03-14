import java.util.Scanner;

public class Morse
{
    //Array of string representations of characters in morse code
    private static final String[] morseList = {
        ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--",
        "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."
    };

    //Converts a string representation of a morse character into an english character
    //Returns null if parameter is not a valid morse character
    public static char morseToChar(String morse)
    {
        for (int i = 0; i < morseList.length; i++)  //Loops through the list of morse code characters
            if (morse.equals(morseList[i]))         //Checks if the parameter is equal to the item at index i
                return (char) (i+65);               //Returns the index + 65 (ASCII representation of the char)
        return (char) (0);                          //Returns null character if parameter is not found in array
    }

    //Converts an english character into a string representation of a morse character
    //Returns empty string if parameter is not a valid english letter
    public static String charToMorse(char english)
    {
        if (english == ' ')                         //Checks if the parameter is a space
            return " / ";                           //Returns " / ", morse for a space between words
        int l = (int) english;                      //ASCII value of char parameter
        if (l >= 97 && l <= 122)                    //Checks if param is lowercase letter
            return morseList[(int) english - 97];   //Returns value at index - 97
        if (l >= 65 && l <= 90)                     //Checks if param is uppercase letter
            return morseList[(int) english - 65];   //Returns value at index - 65
        return "";                                  //Returns empty string if param is not a letter
    }

    //Converts a sequence of morse letters (separated by spaces) into a string of english letters
    //Words are separated by two spaces and a slash (" / ") between them, letters are separated by one space
    //If a morse character is invalid, a question mark ("?") is inserted into the resulting string
    public static String morseSeqToChar(String morseSeq)
    {
        String result = "";                                 //Initializes an empty string

        String[] words = morseSeq.split(" / ");             //Splits the morseSeq into an array of morse words
        for (String word : words)                           //Loops through the words in the array of words
        {
            String[] tempWord = word.split(" ");            //Splits the morse word into an array of morse letters
            for (String mLetter : tempWord)                 //Loops through the letters of the morse word
            {
                char eLetter = morseToChar(mLetter);        //Converts morse letter into english letter
                result += (eLetter != 0) ? eLetter : "?";   //If letter is valid, add the translated letter, else add "?"
            }
            result += " ";                                  //Add a space after the word
        }

        return result;                                      //Return the final result
    }

    //Converts a sequence of english words into a string of morse letters
    //Words are separated by spaces (" "), letters within words are not separated
    //If a letter is invalid, it is replaced by a question mark ("?")
    public static String charSeqToMorse(String charSeq)
    {
        String result = "";                                             //Initializes an empty string

        String[] words = charSeq.split(" ");                            //Splits the charSeq into an array of english words
        for (int wordCount = 0; wordCount < words.length; wordCount++)  //Loops through the words in the word array
        {
            String word = words[wordCount];                             //Sets the 'word' variable to the current word
            char[] tempWord = word.toCharArray();                       //Splits the current word into an array of characters
            for (int letterCount = 0; letterCount < tempWord.length; letterCount++) //Loops through the letters in the letter array
            {
                char eLetter = tempWord[letterCount];                   //Sets the 'eLetter' variable to the current letter
                String mLetter = charToMorse(eLetter);                  //Converts english letter to morse letter
                result += (!mLetter.equals("")) ? mLetter : "?";        //If letter is valid, add the translated letter, else add "?"
                if (letterCount != tempWord.length - 1) result += " ";  //Add a space if the letter is not the last letter in the word
            }
            if (wordCount != words.length - 1) result += " / ";         //Add a " / " if the word is not the last word
        }

        return result;                                                  //Return the final result
    }

    public static void startTranslator()
    {
        Scanner scanner = new Scanner(System.in);       //Initialize a scanner

        int choice = 1;                                 //Initialize a variable for user's choice
        while (choice != 1 || choice != 2)              //Loops while the choice is not 1 or 2
        {
            System.out.println("1) Morse to English");              //Prompt for user input
            System.out.println("2) English to Morse");
            System.out.println("Type any other number to quit.");

            choice = Integer.parseInt(scanner.nextLine());          //Read user input

            if (choice != 1 && choice != 2) break;                  //Breaks out of loop if choice is not valid
            
            System.out.print("Enter ");                             //Prompt for user input
            if (choice == 1) System.out.println("Morse:");
            else System.out.println("English:");

            String input = scanner.nextLine();                      //Read user input

            System.out.println("Translation:");
            if (choice == 1) System.out.println(morseSeqToChar(input));     //Print out translated sequence
            else System.out.println(charSeqToMorse(input));                 //Print out translated sequence
        }

        scanner.close();                                //Close the scanner
    }

    public static void main(String[] args)
    {
        startTranslator();
    }
}
