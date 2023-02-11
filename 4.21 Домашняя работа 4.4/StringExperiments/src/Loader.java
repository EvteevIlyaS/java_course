import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Scanner;

public class Loader {
    public static void main(String[] args) {
//        Calendar calendar = Calendar.getInstance();
//        DateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy", new Locale("ru"));
//        int day = 3;
//        int month = Calendar.NOVEMBER;
//
//        int num = 0;
//        for (int year = 1999; year <= 2022; year++) {
//            calendar.set(year, month, day);
//            System.out.println(num + " - " + dateFormat.format(calendar.getTime()));
//            num++;
//        }
//        String text = "Возьмите английский   текст (не менее 100 слов) и напишите программу, которая будет разбивать" +
//                " его на слова и печатать слова в консоли. Знаки препинания не являются частью слова.";
//        System.out.println(text.split(" +").length);
//        for (String el : text.replaceAll("\\.", "").split("\s")) {
//            System.out.println(el);
//        }
//        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей 12000";
//
//        String[] textArr = text.trim().split("\s");
//        int res = 0;
//        for (int i = 0; i < textArr.length; i++) {
//            if (textArr[i].matches("[0-9]+")) {
//                res += Integer.parseInt(textArr[i]);
//            }
//        }
//        System.out.println(res);
//        String alpha = "abcdefABCDEF";
//        for (int i = 0; i < alpha.length(); i++) {
//            System.out.println((int) alpha.charAt(i));
//        }

//        int vasya = Integer.parseInt(text.substring(text.indexOf(' ', text.indexOf("заработал")),
//                text.indexOf("рублей")).trim());
//        int masha = Integer.parseInt(text.substring(text.lastIndexOf('-') + 1,
//                text.lastIndexOf("рублей")).trim());
//        int tmpPetyaRub = text.substring(text.indexOf(' ', text.indexOf("Петя"))).indexOf("рубля");
//        int petya = Integer.parseInt(text.substring(text.indexOf(' ', text.indexOf("Петя")) + 2).
//                substring(0, tmpPetyaRub - 2).trim());
//        System.out.println(vasya + masha + petya);

//        correctNumber(" 8 ++)( 9297asd1302  44");

//        Scanner scanner = new Scanner(System.in);
//        String line = scanner.nextLine();
//        line = line.trim();
//        for (String el :
//                line.split("\s")) {
//            if (el.matches("[А-Я][а-я]+")) {
//                System.out.println(el);
//            }
//
//        }
//        System.out.println("Имя: " + line.substring(0, line.indexOf("\s")));
//        System.out.println("Фамилия: " + line.substring(line.indexOf("\s") + 1, line.lastIndexOf("\s")));
//        System.out.println("Отчество: " + line.substring(line.lastIndexOf("\s") + 1));

//        System.out.println(searchAndReplaceDiamonds("Номер <   >кредит<>ной карты <4008 asdasda&(^(((/\\\\asd1234 5678> 8912", "***"));
    }

    static String searchAndReplaceDiamonds(String text, String placeholder) {
//        int idxStart = text.indexOf('<');
//        int idxEnd = text.indexOf('>');
//        return text.substring(0, idxStart) + placeholder + text.substring(idxEnd + 1);
        return text.replaceAll("<.*?>", placeholder);
    }

    static void correctNumber(String number) {
        number = number.replaceAll("[^0-9]", "");
        if ((number.charAt(0) == '8' || number.charAt(0) == '7' && number.length() == 11) || (number.charAt(0) == '9' &&
                number.length() == 10)) {
            System.out.println(number);
        } else {
            System.out.println("Неверный формат номера");
        }
    }
}