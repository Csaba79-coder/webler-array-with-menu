import java.util.Scanner;

public class ArrayWithMenuApp {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int[] numbers = {10, 20, 30, 40, 50};

        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };

        displayWelcomeText();

        int choice;

        do {
            displayMenu();
            /*System.out.println("Please enter you choice:");
            choice = scanner.nextInt(); // TODO error handling -> allow being a number only*/
            choice = getNumber(scanner, "Please enter you choice:");

            switch (choice) {
                case 1:
                    System.out.println("Array elements:");
                    printElementsOfArray(numbers);
                    break;
                case 2:
                    //System.out.println("Please enter the index of the element you want to get:");
                    // int index = scanner.nextInt();
                    int index = getNumber(scanner, "Please enter the index of the element you want to get:");
                    printElementOfArray(numbers, index);
                    break;
                case 3:
                    int element = getNumber(scanner, "Please enter the element you want to check:");
                    String result = displayResult(numbers, element);
                    System.out.println(result);
                    break;
                case 4:
                    printElementsOfMatrix(matrix);
                    break;
                case 5:
                    // Populate a matrix with random numbers
                    // int row = getNumber(getNumber(), "Enter the number of rows: "); <- this has extra line printed, whole code needs to be refactored! :)
                    int row = getNumber(scanner, "Enter the number of rows: ");
                    int col = getNumber(scanner, "Enter the number of columns: ");
                    int min = getNumber(scanner, "Enter the minimum number of random nums: ");
                    int max = getNumber(scanner, "Enter the maximum number of random nums: ");
                    int[][] populatedMatrix = generateRandomMatrix(row, col, min, max);
                    printElementsOfMatrix(populatedMatrix);
                    break;
                case 0:
                    System.out.println("Exiting program...");
                    System.out.println("Good bye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);

        scanner.close(); // I would not close, but here it works! :)
    }

    /*
    n the getRandomNumberInRange method you provided, when min is 0 and max is 99, the generated random number range is inclusive of both 0 and 99. Here's how it works:

    (max - min + 1) calculates the range size. In this case, it's (99 - 0 + 1), which is 100.

    (Math.random() * 100) generates a random floating-point number between 0 (inclusive) and 100 (exclusive).

    (int) is used to cast the result to an integer, effectively truncating the decimal part.

    + min is added to shift the range to start from min. In this case, min is 0, so it doesn't change the lower bound.

    So, when you call getRandomNumberInRange(0, 99), it will generate a random integer between 0 and 99, both inclusive.
     */

    private static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min); // int randomNum = getRandomNumberInRange(0, 99);
        // return new Random().nextInt(100); // Generates a number from 0 to 99
    }

    private static int[][] generateRandomMatrix(int numRows, int numCols, int min, int max) {
        int[][] matrix = new int[numRows][numCols];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                matrix[i][j] = getRandomNumber(min, max);
            }
        }
        return matrix;
    }

    private static void printElementsOfMatrix(int[][] matrix) {
        System.out.println("Matrix elements: ");
        for (int[] row : matrix) {
            for (int element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }
    private static String displayResult(int[] numbers, int target) {
        return doesElementExist(numbers, target) ? "Element exists in array" : "Element does not exist in array";
        /*if (isElementExists) {
            return "Element exists in array";
        } else {
            return "Element does not exist in array";
        }*/
    }

    private static boolean doesElementExist(int[] numbers, int target) {
        for (int currentNumber : numbers) {
            if (currentNumber == target) {
                return true;
            }
        }
        return false;
    }

    // TODO error handling -> allow being a number only (can be solved with regex pattern as well / regex pattern matching)
    private static int getNumber(Scanner scanner) {
        int number;

        while (true) {
            if (scanner.hasNextInt()) {
                number = scanner.nextInt();
                scanner.nextLine(); // consume the new line character
                break; // exit the loop
            } else {
                System.out.println("Invalid input. Please enter a number. Try again! Please enter a number:");
                scanner.nextLine(); // clear the invalid input
            }
        }
        return number;
        /*Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();*/
    }

    private static int getNumber(Scanner scanner, String message) {
        System.out.println(message);
        return getNumber(scanner);
        // return getNumber();
    }

    private static void printElementOfArray(int[] numbers, int index) {
        if (index >= 0 && index < numbers.length) {
            int element = numbers[index];
            System.out.println("Index of element: " + index + ", element: " + element);
            // System.out.println(numbers[index]);
        } else {
            System.out.println("Invalid index. Index should be between 0 and " + (numbers.length - 1));
        }
        /*int element = 0;
        for (int i = 0; i < numbers.length; i++) {
            element = numbers[index];
        }
        System.out.println(element);*/
    }

    private static void printElementsOfArray(int[] numbers) {
        for (int number : numbers) {
            System.out.println(number);
        }
    }

    private static void displayWelcomeText() {
        System.out.println("--------------------------");
        System.out.println("Welcome to the Array App!");
        System.out.println("--------------------------");
    }

    private static void displayMenu() {
        System.out.println("--------------------------");
        System.out.println("\t\t" + "Menu: "); // \t --> tab \n -> new line, escape characters (google search)
        System.out.println("1. Print array elements");
        System.out.println("2. Get element from array by index");
        System.out.println("3. Check if element exists in array");
        System.out.println("4. Print matrix elements");
        System.out.println("5. Print the matrix populated with random numbers");
        System.out.println("0. Exit (Enter 0)");
    }
}