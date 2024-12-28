
// אוה יונין 213894496
//WORDLE GAME
import java.util.Random;
import java.util.Scanner;

public class ex2 {
  public static void main(String[] args) {
    Random ran = new Random();
    Scanner scan = new Scanner(System.in);

    String[] words = { "apple", "angle", "dance", "cloud", "storm", "house", "light", "bread", "flame", "river" };

    int randomIndex = ran.nextInt(words.length);
    String secretWord = words[randomIndex];

    System.out.println("Enter your guess (5-letter word): ");
    String guess = scan.nextLine();

    while (!(guess.length() == 5 && isWordInList(words, guess))) { // 1
      guess = keepAskingForWordInList(words); // 2
    }

    int chances = 0;
    while (!guess.equals("GGGGG") && chances <= 6) {
      String result = guessResult(guess, secretWord); // 3
      System.out.println(result);

      if (result.equals("GGGGG")) { // ניחש נכון
        System.out.println("You won!");
        break; // נגמר המשחק
      }

      chances++;
      if (chances > 6) { // אם נגמרו הנסיונות
        System.out.println("You lost!");
        break; // נגמר המשחק
      }

      guess = scan.nextLine(); // כל עוד לא ניצח/נגמרו הנסיונות, ימשיך לנחש

      // לניחושים הלא נכונים הראשונים:
      if (guess.length() != 5) {
        System.out.println("Invalid input. Please enter a 5-letter word.");
      }
      if (!isWordInList(words, guess)) {
        System.out.println("Not in the list. try again: ");
      }
      while (!(guess.length() == 5 && isWordInList(words, guess))) { // ימשיך לנחש
        guess = keepAskingForWordInList(words); // 3
      }
    }
  }// main

  // 1
  public static boolean isWordInList(String[] words, String guess) {
    for (int i = 0; i < words.length; i++) {
      if (words[i].equals(guess)) {
        return true;
      }
    }
    return false;
  } // wordIsInList

  // 2
  public static String keepAskingForWordInList(String[] words) {
    Scanner scan = new Scanner(System.in);
    String guess = "";

    while (true) { // לולאה אינסופית עד שתוכנס מילה מהרשימה
      System.out.println("Enter another guess: ");
      guess = scan.nextLine();

      if (guess.length() != 5) { // תקינות
        System.out.println("Invalid input. Please enter a 5-letter word.");
      } else if (isWordInList(words, guess)) { // המילה ברשימה
        break;
      } else {
        System.out.println("Not in the list. try again: ");
      }
    }
    return guess;
  } // keepAskingForWordInList

  // 3
  public static String guessResult(String guess, String secretWord) {
    char[] secretWordArray = secretWord.toCharArray();
    char[] guessArray = guess.toCharArray();
    String result = ""; // G/Y/_ מחרוזת חדשה עם

    for (int i = 0; i < guessArray.length; i++) {
      if (guessArray[i] == secretWordArray[i]) { // אות במקום הנכון
        result += "G";
        secretWordArray[i] = ' '; // סימון כדי שלא נבדוק את האות שוב (אם יש כפילות)
      } else {
        boolean letterFound = false;
        for (int j = 0; j < secretWordArray.length; j++) {
          if (guessArray[i] == secretWordArray[j] && secretWordArray[j] != ' ') { // אות במקום לא נכון (שלא בדקנו כבר)
            result += "Y";
            secretWordArray[j] = ' '; // סימון
            letterFound = true;
            break; // מצאנו, יפסיק לבדוק את השאר
          }
        }
        if (!letterFound) { // אות לא קיימת
          result += "_";
        }
      }
    }
    return result;
  } // guessResult
} // class
