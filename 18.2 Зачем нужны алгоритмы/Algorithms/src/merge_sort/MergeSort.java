package merge_sort;

public class MergeSort
{
    public static void mergeSort(int[] array)
    {
        int n = array.length;
        if (n == 1) {
            return;
        }
        int middle = n / 2;
        int[] leftArray = new int[middle];
        int[] rightArray = new int[n - middle];

        for (int i = 0; i < middle; i++) {
            leftArray[i] = array[i];
        }
        for (int i = middle; i < n; i++) {
            rightArray[i - middle] = array[i];
        }
        mergeSort(leftArray);
        mergeSort(rightArray);

        merge(array, leftArray, rightArray);
    }

    private static void merge(int[] array, int[] left, int[] right)
    {
        int lenLeft = left.length;
        int lenRight = right.length;
        int counterLeft = 0;
        int counterRight = 0;
        int i = 0;

        while (true) {
            if (counterLeft == lenLeft) {
                for (int j = counterRight; j < lenRight; j++) {
                    array[i] = right[j];
                    i++;
                }
                break;
            }
            else if (counterRight == lenRight) {
                for (int j = counterLeft; j < lenLeft; j++) {
                    array[i] = left[j];
                    i++;
                }
                break;
            }
            else if (left[counterLeft] <= right[counterRight]) {
                array[i] = left[counterLeft];
                counterLeft++;
                i++;
            }
            else if (left[counterLeft] > right[counterRight]) {
                array[i] = right[counterRight];
                counterRight++;
                i++;
            }
        }

    }
}
