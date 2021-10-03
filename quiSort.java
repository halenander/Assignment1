public class quiSort {

    public double[] sort(double[] inputArr, int botInd, int topInd){
        if(botInd < topInd){
            int temp = partition(inputArr,botInd,topInd);
            //recursive step for iterating from the left and from the right
            sort(inputArr,botInd,(temp-1));
            sort(inputArr,(temp+1),topInd);
        }
        return inputArr;


    }

    public int partition(double[] inputArr, int botInd, int topInd){
        if(botInd < topInd){
            int pivot = topInd--;
            while(botInd <= topInd){
                //check if left side has something greater than pivot
                while(botInd < pivot && inputArr[botInd] < inputArr[pivot]){
                    botInd++;
                }
                //check if right side has something less than pivot
                while(topInd >= botInd && inputArr[topInd] >= inputArr[pivot]){
                    topInd--;
                }
                //swap elements if so
                if (botInd < topInd){
                    swap(inputArr, botInd, topInd);
                }
                else{
                    swap(inputArr, botInd, pivot);
                }
            }
        }
        return botInd;
    }

    public void swap(double[] arr, int ind1, int ind2){
        double temp = arr[ind1];
        arr[ind1] = arr[ind2];
        arr[ind2] = temp;
    }


}