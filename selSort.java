public class selSort{

    public double[] sort(double[] inputArr){
        int accum = 0;
        //runs til the end of the array
        while (accum < inputArr.length){
            double min = inputArr[accum];
            int minIndex = accum;
            //finds the lowest element and records its index
            for (int i = accum; i < inputArr.length; i++){
                if (inputArr[i]< min){
                    min = inputArr[i];
                    minIndex = i;
                }
            }
            //swaps the lowest element with the element in the front of the unsorted section
            double temp = inputArr[accum];
            inputArr[accum] = inputArr[minIndex];
            inputArr[minIndex] = temp;
            accum++;
        }
        return inputArr;
    }
}