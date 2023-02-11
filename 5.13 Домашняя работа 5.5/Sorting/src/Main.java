import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) {
        String[] letters = "АВЕМНОРСТУ".split("");
        ArrayList<String> numbers = new ArrayList<>();

        String tmpVar4;
        for (String firstLetter :
                letters) {
            tmpVar4 = firstLetter;
            for (int i = 0; i < 10; i++) {
                StringBuilder tmpVar3 = new StringBuilder(tmpVar4);
                tmpVar3.append(String.valueOf(i).repeat(3));
                for (String secondLetter :
                        letters) {
                    String tmpVar2 = tmpVar3.toString();
                    tmpVar2 += secondLetter;
                    for (String thirdLetter :
                            letters) {
                        String tmpVar1 = tmpVar2;
                        tmpVar1 += thirdLetter;
                        for (int j = 0; j < 198; j++) {
                            String tmpVar = tmpVar1;
                            tmpVar += j;
                            numbers.add(tmpVar);
                        }
                    }
                }
            }
        }

        enumerationSearch(numbers, "С444ЕА112");
        binarySearch(numbers, "С444ЕА112");
        treeSetSearch(numbers, "С444ЕА112");
        hashSetSearch(numbers, "С444ЕА112");
    }

    static void enumerationSearch(ArrayList<String> list, String number) {
        long start = System.currentTimeMillis();
        list.contains(number);
        System.out.println("Enumeration search: " + (System.currentTimeMillis() - start));

    }

    static void binarySearch(ArrayList<String> list, String number) {
        long start = System.currentTimeMillis();
        Collections.binarySearch(list, number);
        System.out.println("Binary search: " + (System.currentTimeMillis() - start));
    }

    static void treeSetSearch(ArrayList<String> list, String number) {
        TreeSet<String> set = new TreeSet<>(list);
        long start = System.currentTimeMillis();
        set.contains(number);
        System.out.println("Tree set search: " + (System.currentTimeMillis() - start));
    }

    static void hashSetSearch(ArrayList<String> list, String number) {
        HashSet<String> set = new HashSet(list);
        long start = System.currentTimeMillis();
        set.contains(number);
        System.out.println("Hash set search: " + (System.currentTimeMillis() - start));
    }
}
