import array_max_value.ArrayMaxValue;
import binary_search.BinarySearch;
import bubble_sort.BubbleSort;
import merge_sort.MergeSort;
import quick_sort.QuickSort;
import rabin_karp.RabinKarpExtended;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int[] arr = {4,1,6,2,5,5,3,4,8,9};
        int[] arr2 = {1,2,3,4,4,5,5,6,8,9};
        int[] arr3 = {31, 22, 1, 3123, 2323, 222, 123};


//        System.out.println(ArrayMaxValue.getMaxValue(arr));

//        String[] arr = {"asd", "asd", "bcd", "bcd", "uuu", "yyyy", "zz"};
//        ArrayList<String> strArr = new ArrayList<>(Arrays.asList(arr));
//        BinarySearch binarySearch = new BinarySearch(strArr);
//        System.out.println(binarySearch.search("asd"));

//        BubbleSort.sort(arr);
//        System.out.println(Arrays.equals(arr, arr2));

//        QuickSort.sort(arr3);
//        System.out.println(Arrays.toString(arr3));

//        MergeSort.mergeSort(arr);
//        System.out.println(Arrays.toString(arr));

        RabinKarpExtended rabinKarpExtended = new RabinKarpExtended("qwessddq ddd d");
        rabinKarpExtended.search(" ").forEach(System.out::println);
        rabinKarpExtended.search(" ").forEach(System.out::println);

    }

}
