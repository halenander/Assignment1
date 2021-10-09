import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class BetterMerge {

    public double[] sort(double[] inputArr) {
        BetterMerge merSort = new BetterMerge();
        ArrayList<ArrayList<Integer>> indexes = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> holder = new ArrayList<Integer>();
        Integer temp = new Integer(0);
        int start = 0;
        int end = 0;
        int index =0;

        //program logic:
        //find sorted portions of array via for loop
        //create an arraylist of pairs (start/end) then sort pair[0] pair [1] and put those index into pair[0] (delete pair[1])
        //then sort pair[1] and pair[2] and put into pair [1] (and delete pair[2]), and so on
        //should be a while loop until arraylist has one element left over

        for (int i = 0; i < inputArr.length; i++) {
            //find start and end indexes of sorted portions
            if(i == (inputArr.length-1)){
                //if end of list, add start and end variables
                holder.add(temp.valueOf(start));
                holder.add(temp.valueOf(end));
                ArrayList<Integer> tempArr = new ArrayList<Integer>(holder);
                indexes.add(tempArr);
                holder.remove(0);
                holder.remove(0);
            }
            else if (inputArr[i] <= inputArr[i + 1]) {
                //if int to right is still greater/= then keep going, i.e. increase end var
                end++;
            } else{
                //if less than, add start and end int and then log them as the next index pos
                holder.add(temp.valueOf(start));
                holder.add(temp.valueOf(end));
                ArrayList<Integer> tempArr = new ArrayList<Integer>(holder);
                indexes.add(tempArr);
                holder.remove(0);
                holder.remove(0);
                start = i+1;
                end = i+1;

            }
        }
        //run until arraylist only has one element left (should be [0,inputArr.length])
        while(indexes.size()>1){
            //run through arraylist
            for(int i = 0; i < indexes.size()-1; i++){
                //generate two arrays to merge based off first two sorted sections (then next two, etc)
                double[] arr1 = Arrays.copyOfRange(inputArr,indexes.get(i).get(0).intValue(),indexes.get(i).get(1).intValue()+1);
                double[] arr2 = Arrays.copyOfRange(inputArr,indexes.get(i+1).get(0).intValue(),indexes.get(i+1).get(1).intValue()+1);
                //merge them together into inputArr, and include the (starting) index of where they are located in the array
                merge(inputArr,arr1,arr2,indexes.get(i).get(0).intValue());
                //replace the first two elements (and later next two elements, etc.) in the arrayList
                //with new coordinates (of the newly sorted section)
                holder.add(indexes.get(i).get(0).intValue());
                holder.add(indexes.get(i+1).get(1).intValue());
                ArrayList<Integer> tempArr = new ArrayList<Integer>(holder);
                indexes.set(i,tempArr);
                holder.remove(0);
                holder.remove(0);
                indexes.remove(i+1);
            }
        }
        return inputArr;
    }

    public void merge ( double[] arr, double[] left, double[] right, int start){
        //set arrIndex to start just in case the section to be sorted does not start at the beginning of the array
        int leftIndex = 0, rightIndex = 0, arrIndex = start;
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
        BetterMerge newMerge = new BetterMerge();
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