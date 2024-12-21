import java.util.Scanner;

public class ex1 {

  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    System.out.println("Enter your ID: ");
    String id = scan.nextLine();

    // 1. numOfDigits
    id = numOfDigits(id); // שמירת התז התקין

    // 2. isThe9digitsIdValid
    if (id.length() == 9) {
      boolean valid = is9digitsIdValid(id);
      while (valid == false) {
        System.out.println("Invalid input. Try again: ");
        id = scan.nextLine();
        valid = is9digitsIdValid(id); // שמירת התז התקין
      }
      if (valid == true) {
        System.out.println("Valid.");
      }
    }

    // 3. the8digitsIdWithCheckDigit
    if (id.length() == 8) {
      id = the8digitsIdWithCheckDigit(id);
      System.out.println("Your full ID is: " + id);
    }

    // 4. addZerosIfSmallerThan8digits
    if (id.length() < 8 && id.length() > 1) {
      id = addZerosToIdSmallerThan8digits(id);
      System.out.println("Your full ID is: " + id);
    }

  }// main

  public static String numOfDigits(String id) {
    Scanner scan = new Scanner(System.in);

    boolean valid = false;
    if (id.length() > 9 || id.length() < 1) {
      valid = false;
    } else {
      valid = true;
    }
    while (valid == false) {
      System.out.println("Invalid input. Try again: ");
      id = scan.nextLine();
    }
    return id;
  } // numOfDigits

  public static int sumOfId(String id) { // בדיקת תקינות
    char[] idArray = id.toCharArray();
    int sumOfId = 0;

    for (int i = 0; i < idArray.length; i++) { // אידקס מתחיל מ-0 אבל הספירה מתחילה מ-1
      int digit = idArray[i] - '0'; // המרת תו למספר
      // הכפלה ב-2 במקומות הזוגיים בתז (אינדקס אי זוגי)
      if (i % 2 != 0) {
        digit *= 2;
        // אם הספרה גדולה מ-9
        if (digit >= 10) {
          int units = digit % 10;
          int tens = digit / 10;
          sumOfId += (units + tens);
        } else { // אם הספרה לא גדולה מ-9
          sumOfId += digit;
        }
      } else { // אם האינדקס זוגי
        sumOfId += digit;
      }
    }
    return sumOfId;
  } // sumOfId

  public static boolean is9digitsIdValid(String id) {
    int sumOfId = sumOfId(id);

    if (sumOfId % 10 == 0) {
      return true;
    } else
      return false;
  } // is9digitsIdValid

  public static String the8digitsIdWithCheckDigit(String id) {
    int sumOfId = sumOfId(id);

    String fullId = ""; // אתחול
    // הוספת ספרת הביקורת:
    if (sumOfId % 10 != 0) {
      int checkDigit = 10 - (sumOfId % 10);
      char checkDig = (char) (checkDigit + '0');
      fullId = id + checkDig;

    } else { // אם התז מתחלקת ב10 נוסיף רק 0 בהתחלה
      fullId = addZerosToIdSmallerThan8digits(id);
    }
    return fullId;
  } // the8digitsIdWithCheckDigit

  public static String addZerosToIdSmallerThan8digits(String id) {
    // אם מתחלק ב10 נוסיף רק אפסים אם לא מתחלק ב10 נוסיף קודם ספרת ביקורת ואז אפסים
    char[] idArray = id.toCharArray();
    int sumOfId = sumOfId(id);

    String fullId = "";

    // אם התז לא תקינה:
    if (sumOfId % 10 != 0) {
      // הוספת ספרת ביקורת:
      int checkDigit = 10 - (sumOfId % 10);
      char checkDig = (char) (checkDigit + '0');
      id = id + checkDig;

      int count = 0;
      // נספור את כמות הספרות:
      for (int i = 0; i < idArray.length + 1; i++) { // +1 כי הוספנו ספרת ביקורת
        count++;
      }
      // נוסיף אפסים בהתאם:
      for (int i = 0; i < (9 - count); i++) {
        id = '0' + id; // שרשור האפסים משמאל
      }
      fullId = id;
    }

    // אם התז תקינה:
    else if (sumOfId % 10 == 0) {
      int count = 0;
      // נספור את כמות הספרות:
      for (int i = 0; i < idArray.length; i++) {
        count++;
      }
      // נוסיף אפסים בהתאם:
      for (int i = 0; i < (9 - count); i++) {
        id = '0' + id;
      }
      fullId = id;
    }

    return fullId;
  } // addZerosIfSmallerThan8digits
}