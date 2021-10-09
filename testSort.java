import java.util.*;
import java.lang.*;
//author: Haley Lenander

public class testSort{

    public testSort(){
    }

    public boolean isSorted(double[] inputArr){
        for (int i = 0; i < (inputArr.length-1); i++){
            //if a number has a number immediately to the right of it that is less, the list is unsorted
            if (inputArr[i+1]< inputArr[i]){
                return false;
            }
        }
        //otherwise list is sorted
        return true;
    }

    public static void main(String[] args){
        testSort newTest = new testSort();
        selSort selSort = new selSort();
        bubSort bubSort = new bubSort();
        insSort insSort = new insSort();
        merSort merSort = new merSort();
        quiSort quiSort = new quiSort();
        Random rand = new Random();

        //generate array
        double[] testArr = new double[50000];
        for (int i = 0; i < testArr.length; i++) {
            testArr[i] = rand.nextDouble();
        }
        //array for selection sort
        double[] testArr1 = Arrays.copyOf(testArr, testArr.length);
        //array for bubble sort
        double[] testArr2 = Arrays.copyOf(testArr, testArr.length);
        //array for insertion sort
        double[] testArr3 = Arrays.copyOf(testArr, testArr.length);
        //array for merge sort
        double[] testArr4 = Arrays.copyOf(testArr, testArr.length);
        //array for quick sort
        double[] testArr5 = Arrays.copyOf(testArr, testArr.length);

        long start = System.currentTimeMillis();
        selSort.sort(testArr1);
        long end = System.currentTimeMillis();

        //check if array is sorted
        //if array is sorted, print time and sort using next method
        if (newTest.isSorted(testArr1)) {
            System.out.println("selection sort: " + (end-start));
            start = System.currentTimeMillis();
            bubSort.sort(testArr2);
            end = System.currentTimeMillis();
        }

        //cont. for remaining methods

        if (newTest.isSorted(testArr2)) {
            System.out.println("bubble sort: " + (end - start));
            start = System.currentTimeMillis();
            insSort.sort(testArr3);
            end = System.currentTimeMillis();
        }


        if (newTest.isSorted(testArr3)) {
            System.out.println("insertion sort: " + (end-start));
            start = System.currentTimeMillis();
            merSort.sort(testArr4);
            end = System.currentTimeMillis();
        }



        if (newTest.isSorted(testArr4)) {
            System.out.println("merge sort: " + (end - start));
            start = System.currentTimeMillis();
            quiSort.sort(testArr5, 0, (testArr5.length - 1));
            end = System.currentTimeMillis();
        }


        if (newTest.isSorted(testArr5)) {
            System.out.println("quick sort: " + (end - start));
        }



    }
}