public class bubSort {

    public double[] sort(double[] inputArr){
        testSort newTest = new testSort();
        int j = 0;
        while (j < inputArr.length){
            for(int i = 0; i < (inputArr.length-1); i++){
                //if element is greater than the one to the right of it, switch them
                if (inputArr[i]> inputArr[i+1]){
                    double temp = inputArr[i];
                    inputArr[i] = inputArr[i+1];
                    inputArr[i+1] = temp;
                }
            }
            j++;

        }
        return inputArr;
    }

}