package ua.com.alevel;

import java.util.Comparator;

public class SortHelper<Value> {

    Comparator<Value> compartor;

    public SortHelper(Comparator<Value> compartor) {
        this.compartor = compartor;
    }

    void mergeSort(Value[] arr, int start, int end, boolean isDescending)       //helper function that creates the sub cases for sorting
    {
        int mid = (start + end) / 2;
        if (start < end) {
            // Sort left half
            mergeSort(arr, start, mid,isDescending);
            // Sort right half
            mergeSort(arr, mid + 1, end,isDescending);
            // Merge left and right half
            merge(arr, start, mid, end,isDescending);
        }
    }

    void merge(Value[] arr, int start, int mid, int end, boolean isDescending) {
        Value[] tempArray = (Value[]) new Object[arr.length];
        int tempArrayIndex = start;
        int startIndex = start;
        int midIndex = mid + 1;
        while (startIndex <= mid && midIndex <= end) {
            if ((compartor.compare(arr[startIndex], arr[midIndex])<0 && isDescending)||(compartor.compare(arr[startIndex], arr[midIndex])>0 && !isDescending)) {
                tempArray[tempArrayIndex++] = arr[startIndex++];
            } else {
                tempArray[tempArrayIndex++] = arr[midIndex++];
            }
        }
        while (startIndex <= mid) {
            tempArray[tempArrayIndex++] = arr[startIndex++];
        }
        while (midIndex <= end) {
            tempArray[tempArrayIndex++] = arr[midIndex++];
        }
        if (end + 1 - start >= 0) System.arraycopy(tempArray, start, arr, start, end + 1 - start);
    }

}
