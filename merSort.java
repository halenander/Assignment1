import java.util.Arrays;

public class merSort {

    public double[] sort(double[] inputArr){
        merSort merSort = new merSort();

        if(inputArr.length > 1){
            int size = ((inputArr.length)/2);
            double[] left = new double[size];
            double[] right = new double[(inputArr.length - size)];
            //create left side array
            for (int i = 0; i < size; i++){
                left[i] = inputArr[i];
            }
            //create right side array
            for(int x = 0; x < right.length; x++){
                right[x] = inputArr[size];
                size++;
            }
            //recursive sort
            sort(left);
            sort(right);
            merge(inputArr, left, right);
        }
        return inputArr;
    }

    public void merge(double[]arr, double[]left, double[]right){
        int leftIndex = 0, rightIndex = 0, arrIndex=0;
        while (leftIndex<left.length && rightIndex<right.length){
            //if element in left array is less than element in right array, add to arr
            if(left[leftIndex]<right[rightIndex]){
                arr[arrIndex++]=left[leftIndex++];
            }
            //otherwise add right element to arr
            else{
                arr[arrIndex++]=right[rightIndex++];
            }
        }
        //if right is empty, add rest of left to arr
        while (leftIndex<left.length){
            arr[arrIndex++]=left[leftIndex++];
        }
        //" ^^  " for right arr
        while(rightIndex<right.length){
            arr[arrIndex++]=right[rightIndex++];
        }

    }
}