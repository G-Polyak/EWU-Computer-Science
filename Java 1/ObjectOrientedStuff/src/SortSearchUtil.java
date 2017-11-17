//George Polyak
//SortSearchUtil Class

import java.util.*;

public class SortSearchUtil {

    public static int linearSearch(int[] ara, int num) {

        int j = -1;

        for (int i = 0; i < ara.length; i++) {

            if (ara[i] == num) {
                j = i;
            }

        }

        return j;

    }

    public static int linearSearch(double[] ara, double num) {

        int j = -1;

        for (int i = 0; i < ara.length; i++) {

            if (ara[i] == num) {
                j = i;
            }

        }

        return j;

    }

    public static int linearSearch(Comparable[] ara, Comparable s) {

        int j = -1;

        for (int i = 0; i < ara.length; i++) {

            if (ara[i].equals(s)) {
                j = i;
            }

        }

        return j;

    }


    public static int binarySearch(int[] ara, int num) {

        int j = -1;
        int first = 0;
        int last = ara.length - 1;
        int mid = (first + last) / 2;

        while (first <= last) {

            if (ara[mid] < num) {
                first = mid + 1;
            } else if (ara[mid] == num) {
                j = mid;
                break;
            } else {
                last = mid - 1;
            }

            mid = (first + last) / 2;

        }

        return j;

    }

    public static int binarySearch(double[] ara, double num) {

        int j = -1;
        int first = 0;
        int last = ara.length - 1;
        int mid = (first + last) / 2;

        while (first <= last) {

            if (ara[mid] < num) {
                first = mid + 1;
            } else if (ara[mid] == num) {
                j = mid;
                break;
            } else {
                last = mid - 1;
            }

            mid = (first + last) / 2;

        }

        return j;

    }

    public static int binarySearch(Comparable[] ara, Comparable s) {

        int j = -1;
        int first = 0;
        int last = ara.length - 1;
        int mid = (first + last) / 2;

        while (first <= last) {

            if (ara[mid].compareTo(s) < 0) {
                first = mid + 1;
            } else if (ara[mid].equals(s)) {
                j = mid;
                break;
            } else {
                last = mid - 1;
            }

            mid = (first + last) / 2;

        }

        return j;

    }


    public static void selectionSort(int[] ara) {

        int current;
        int smallestIndex;
        int posToFill;
        int temp;

        for (posToFill = 0; posToFill < ara.length - 1; posToFill++) {

            smallestIndex = posToFill;

            for (current = posToFill + 1; current < ara.length; current++) {

                if (ara[current] < ara[smallestIndex]) {
                    smallestIndex = current;
                }

            }

            temp = ara[posToFill];
            ara[posToFill] = ara[smallestIndex];
            ara[smallestIndex] = temp;

        }

    }

    public static void selectionSort(ArrayList<Integer> ara) {

        int current;
        int smallestIndex;
        int posToFill;
        int temp;

        for (posToFill = 0; posToFill < ara.size() - 1; posToFill++) {

            smallestIndex = posToFill;

            for (current = posToFill + 1; current < ara.size(); current++) {

                if (ara.get(current) < ara.get(smallestIndex)) {
                    smallestIndex = current;
                }

            }

            temp = ara.get(posToFill);
            ara.set(posToFill, ara.get(smallestIndex));
            ara.set(smallestIndex, temp);

        }

    }

    public static void selectionSort(double[] ara) {

        int current;
        int smallestIndex;
        int posToFill;
        double temp;

        for (posToFill = 0; posToFill < ara.length - 1; posToFill++) {

            smallestIndex = posToFill;

            for (current = posToFill + 1; current < ara.length; current++) {

                if (ara[current] < ara[smallestIndex]) {
                    smallestIndex = current;
                }

            }

            temp = ara[posToFill];
            ara[posToFill] = ara[smallestIndex];
            ara[smallestIndex] = temp;

        }

    }

    public static void selectionSort(Comparable[] ara) {

        int current;
        int smallestIndex;
        int posToFill;
        Comparable temp;

        for (posToFill = 0; posToFill < ara.length - 1; posToFill++) {

            smallestIndex = posToFill;

            for (current = posToFill + 1; current < ara.length; current++) {

                if (ara[current].compareTo(ara[smallestIndex]) < 0) {
                    smallestIndex = current;
                }

            }

            temp = ara[posToFill];
            ara[posToFill] = ara[smallestIndex];
            ara[smallestIndex] = temp;

        }

    }

    public static void insertionSort(int[] ara) {

        for (int i = 1; i < ara.length; i++)
        {
            int index = ara[i];
            int j = i;
            while (j > 0 && ara[j - 1] > index) {
                ara[j] = ara[j - 1];
                j--;
            }
            ara[j] = index;
        }
    }

    public static void insertionSort(double[] ara) {

        for (int i = 1; i < ara.length; i++)
        {
            double index = ara[i];
            int j = i;
            while (j > 0 && ara[j - 1] > index) {
                ara[j] = ara[j - 1];
                j--;
            }
            ara[j] = index;
        }

    }

    public static void insertionSort(Comparable[] ara) {

        for (int i = 1; i < ara.length; i++)
        {
            Comparable index = ara[i];
            int j = i;
            while (j > 0 && ara[j - 1].compareTo(index) < 0) {
                ara[j] = ara[j - 1];
                j--;
            }
            ara[j] = index;
        }

    }

}