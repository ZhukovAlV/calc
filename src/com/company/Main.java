package com.company;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.company.RomanConverter.*;

public class Main {

    // Проверка, что выражение содержит римские символы
    static boolean checkRoman(String testString){
        Pattern p = Pattern.compile("^[IVXLCDM]+\\D[IVXLCDM]+$");
        Matcher m = p.matcher(testString);
        return m.matches();
    }

    // Проверка, что выражение содержит арабские символы
    static boolean checkArabic(String testString){
        Pattern p = Pattern.compile("^\\d+\\D\\d+$");
        Matcher m = p.matcher(testString);
        return m.matches();
    }

    // Проверка, что числа от 1 до 10
    static int checkNum(int num) {
        if (num < 1)
            throw new NumberFormatException("Число должно быть положительным.");
        if (num > 10)
            throw new NumberFormatException("Число должно быть от 1 до 10 включительно, не более.");
    return num;
    }

    // Считаем результат
    static int countRes (int a, char z, int b) {
        switch (z) {
            case ('+'):
                return a + b;
            case ('-'):
                return a - b;
            case ('*'):
                return a * b;
            case ('/'):
                return a / b;
            default:
                throw new NumberFormatException("Возникла ошибка инициализации арифметического знака");
        }
    }

    public static void main(String[] args) {
        String pattern = "[+-/*]";
        char aSign;

        // Вводим выражение
        System.out.println("Введите ваше выражение в виде 2 чисел: a + b, a - b, a * b, a / b");
        Scanner scanner= new Scanner(System.in);
        String expression = scanner.nextLine();

        // Сохраняем члены выражения в массив
        String[] splitResult = expression.split(pattern);
        System.out.println(Arrays.toString(splitResult));

        // Извлекаем арифметический знак
        aSign = expression.charAt(splitResult[0].length());

        // Считаем результат
        if (checkRoman(expression)) {
            System.out.println(toRoman(countRes(checkNum(toArabic(splitResult[0])), aSign, checkNum(toArabic(splitResult[1])))));
        } else if (checkArabic(expression)) {
            System.out.println(countRes(checkNum(Integer.parseInt(splitResult[0])),aSign,checkNum(Integer.parseInt(splitResult[1]))));
        } else {
            throw new NumberFormatException("Не правильно введено выражение");
        }
    }
}
