import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class BetterMer {

    public double[] sort(double[] inputArr) {
        merSort merSort = new merSort();
        ArrayList<Integer> holder = new ArrayList<Integer>();
        Integer temp = new Integer(0);
        int start = 0;
        int end = 0;
        int index =0;

        for (int i = 0; i < inputArr.length; i++) {
            //find start and end indexes of sorted portions
            if(i == (inputArr.length-1)){
                //if end of list, add start and end variables
                holder.add(temp.valueOf(start));
                holder.add(temp.valueOf(end));
            }
            else if (inputArr[i] <= inputArr[i + 1]) {
                //if int to right is still greater/= then keep going, i.e. increase end var
                end++;
            } else{
                //if less than, add start and end int and then log them as the next index pos
                holder.add(temp.valueOf(start));
                holder.add(temp.valueOf(end));
                start = i+1;
                end = i+1;

            }
        }
        //get the index of the end of the first sorted section
        index = holder.get(1).intValue();
        //get index of the start of the last sorted section
        int backIndex = holder.get(holder.size()-2).intValue();
        //remove the indices of the first sorted portion
        holder.remove(0);
        holder.remove(0);
        holder.remove(holder.size()-1);
        holder.remove(holder.size()-1);
        int accum = holder.size();
        while(accum >= 0){
            if(holder.size() >= 4) {
                //create first array from the range of (non-zero) elements in tempArr
                double[] arr1 = Arrays.copyOfRange(inputArr, 0, index + 1);
                //create second array from the indices logged in holder
                double[] arr2 = Arrays.copyOfRange(inputArr, holder.get(0).intValue(), holder.get(1).intValue() + 1);
                holder.remove(0);
                holder.remove(0);
                //create arrays coming from the back (using same methods as above)
                double[] arr3 = Arrays.copyOfRange(inputArr, backIndex, inputArr.length);
                double[] arr4 = Arrays.copyOfRange(inputArr, holder.get(holder.size()-2).intValue(), holder.get(holder.size() - 1).intValue()+1);
                holder.remove(holder.size() - 1);
                holder.remove(holder.size() - 1);
                //change index variable to keep up with new elements to be added to tempArr
                index = (arr1.length + arr2.length) - 1;
                backIndex = (inputArr.length-(arr3.length + arr4.length));
                //run both merges
                merge(inputArr, arr1, arr2);
                backMerge(inputArr, arr3, arr4);
            }
            //for last sorted set indicated in the arraylist
            else if (holder.size() == 2){
                double[] arr1 = Arrays.copyOfRange(inputArr, 0, index + 1);
                //create second array from the indices logged in holder
                double[] arr2 = Arrays.copyOfRange(inputArr, holder.get(0).intValue(), holder.get(1).intValue() + 1);
                holder.remove(0);
                holder.remove(0);
                index = (arr1.length + arr2.length) - 1;
                merge(inputArr, arr1, arr2);
            }
            //once the arraylist is empty, merge the two arrays (front and back) together
            else{
                index++;
                double[] arr1 = Arrays.copyOfRange(inputArr, 0, index);
                double[] arr3 = Arrays.copyOfRange(inputArr, index, inputArr.length);
                merge(inputArr, arr1, arr3);
                accum= -1;
            }
        }
        return inputArr;
    }

    public void merge ( double[] arr, double[] left, double[] right){
        int leftIndex = 0, rightIndex = 0, arrIndex = 0;
        while (leftIndex < left.length && rightIndex < right.length) {
            //if element in left array is less than element in right array, add to arr
            if (left[leftIndex] < right[rightIndex]) {
                arr[arrIndex++] = left[leftIndex++];
            }
            //otherwise add right element to arr
            else {
                arr[arrIndex++] = right[rightIndex++];
            }
        }
        //if right is empty, add rest of left to arr
        while (leftIndex < left.length) {
            arr[arrIndex++] = left[leftIndex++];
        }
        //" ^^  " for right arr
        while (rightIndex < right.length) {
            arr[arrIndex++] = right[rightIndex++];
        }

    }
    //same as above merge, just starts in the last section of the array
    public void backMerge ( double[] arr, double[] left, double[] right){
        int leftIndex = 0, rightIndex = 0, arrIndex = arr.length-(left.length+right.length);
        while (leftIndex < left.length && rightIndex < right.length) {
            //if element in left array is less than element in right array, add to arr
            if (left[leftIndex] < right[rightIndex]) {
                arr[arrIndex++] = left[leftIndex++];
            }
            //otherwise add right element to arr
            else {
                arr[arrIndex++] = right[rightIndex++];
            }
        }
        //if right is empty, add rest of left to arr
        while (leftIndex < left.length) {
            arr[arrIndex++] = left[leftIndex++];
        }
        //" ^^  " for right arr
        while (rightIndex < right.length) {
            arr[arrIndex++] = right[rightIndex++];
        }

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

    public static void main(String[] args) {
        Random rand = new Random();
        BetterMer newMerge = new BetterMer();
        merSort merge = new merSort();
        insSort insert = new insSort();

        //generate array
        double[] testArr = new double[500000];
        for (int i = 0; i < testArr.length; i++) {
            testArr[i] = rand.nextDouble();
        }

        double[] testArr1 = Arrays.copyOf(testArr, testArr.length);
        double[] testArr2 = Arrays.copyOf(testArr, testArr.length);
        double[] testArr3 = Arrays.copyOf(testArr, testArr.length);

        long start = System.currentTimeMillis();
        merge.sort(testArr1);
        long end = System.currentTimeMillis();

        if(newMerge.isSorted(testArr1)){
            System.out.println("Old merge:" + (end-start));
            start = System.currentTimeMillis();
            testArr2 = newMerge.sort(testArr2);
            end = System.currentTimeMillis();
        }

        if(newMerge.isSorted(testArr2)){
            System.out.println("New merge: "+(end-start));
            start = System.currentTimeMillis();
            testArr3 = insert.sort(testArr3);
            end = System.currentTimeMillis();
        }
        if(newMerge.isSorted(testArr3)) {
            System.out.println("insertion sort: " + (end - start));
        }

    }
    }