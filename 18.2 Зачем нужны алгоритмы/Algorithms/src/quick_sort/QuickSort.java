package quick_sort;

import java.lang.reflect.Array;
import java.util.Arrays;

public class QuickSort {
    public static void sort(int[] array) {
        if (array.length <= 1) {
            return;
        }
        sort(array, 0, array.length - 1);
    }

    private static void sort(int[] array, int from, int to) {
//        System.out.println(from + " " + to);
        if (from < to) {
//            System.out.println(Arrays.toString(array));
            int pivot = partition(array, from, to);
            sort(array, from, pivot - 1);
            sort(array, pivot + 1, to);
        }
    }

    private static int partition(int[] array, int from, int to) {
        int moveCounter = 0;
        int startEl = array[from];
        for (int i = from + 1; i <= to; i++) {
            int el = array[i];
            if (el < startEl) {
                System.arraycopy(array, from, array, from + 1, i - from);
                array[from] = el;
                moveCounter++;
            }
        }
        return from + moveCounter;
    }
}
