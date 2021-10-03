public class insSort{

    public double[] sort(double[] inputArr){
        for (int i = 1; i < inputArr.length; i++) {
            double temp = inputArr[i];
            int x = i - 1;
            //puts the leftmost item (unsorted) in the correct position
            while (x >= 0 && inputArr[x] > temp) {
                inputArr[x+1] = inputArr[x];
                --x;
            }
            inputArr[x+1] = temp;
        }
        return inputArr;
    }

}