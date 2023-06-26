package TestKata;

import java.util.Scanner;

public class Calc {
    private static String wordOne;
    private static String wordTwo;
    private static String text;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        text = scanner.nextLine().trim();
        
        if (!text.startsWith("\"")) {
            throw new IllegalArgumentException("Первым аргументом должна быть строка в кавычках!");
        }


        String result = getResult();

        if (result.length() > 41) {
            System.out.println("Результат: " + "\"" + result + "..." + "\"");
        } else {
            System.out.println("Результат: " + "\"" + result + "\"");
        }
        if (wordOne.length() > 10 || wordTwo.length() > 10) {
            throw new IllegalArgumentException("Длина строки не должна превышат 10 символов");
        }    
    }

    public static String sub() {
        if (text.contains("-")) {
            String[] textSplit = text.replaceAll("\"", "").split(" - ");
            wordOne = textSplit[0];
            wordTwo = textSplit[1];
            if (wordOne.contains(wordTwo)) {
                return wordOne.replace(wordTwo, "");
            }
            if (isNumber(wordOne) || isNumber(wordTwo)) {
                throw new RuntimeException("Вычитание работает только со строками");
            }
        }
        return wordOne;
    }


    public static String add() {
        if (text.contains("+")) {
            String[] textSplit = text.split(" \\+ ");
            wordOne = textSplit[0];
            wordTwo = textSplit[1];
            if (wordTwo.contains("\"")) {
                return (wordOne + wordTwo).replaceAll("\"", "");
            } else {
                throw new RuntimeException("Сложение работает только со строками");
            }
        }
        return wordOne + wordTwo;
    }

    public static String multi() {
        int number = 0;
        if (text.contains("*")) {
            String[] textSplit = text.split(" \\* ");
            wordOne = textSplit[0];
            wordTwo = textSplit[1];
            number = Integer.parseInt(wordTwo);

            if (number < 1 || number > 10)
                throw new IllegalArgumentException("Калькулятор принимает на вход числа от 1 до 10 включительно, не более");
        }
        return wordOne.repeat(number).replaceAll("\"", "");
    }

    public static String div() {
        int number = 0;
        if (text.contains("/")) {
            String[] textSplit = text.split(" / ");
            wordOne = textSplit[0];
            wordTwo = textSplit[1];
            number = Integer.parseInt(wordTwo);
            if (number < 1 || number > 10) {
                throw new IllegalArgumentException("Калькулятор принимает на вход числа от 1 до 10 включительно, не более");
            }
        }
        int lengthOne = wordOne.length() / number;
        return wordOne.substring(0, lengthOne).replaceAll("\"", "");
    }

    private static String getResult() {
        if (text.contains("+")) {
            return add();
        } else if (text.contains("-")) {
            return sub();
        } else if (text.contains("*")) {
            return multi();
        } else if (text.contains("/")) {
            return div();
        } else {
            throw new IllegalArgumentException("Неверный оператор");
        }
    }


    private static boolean isNumber(String word) {
        try {
            Integer.parseInt(word);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}





