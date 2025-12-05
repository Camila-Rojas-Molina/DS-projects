package sorting;
/*  
    Overall time complexity:  ALWAYS O(n(log n)), (best, worst, avr)
    Space complexity: O(n), because we create subarrays.
    depending on the og array size.
*/

public class MergeSort{
    public static void main(String args[]){
        int[] array = {8, 2, 5, 3, 4, 7, 6, 1};
        for(int i = 0; i < array.length; i++ ){
            System.out.print(array[i] + " "  );
        }
        System.out.println();
        mergeSort(array);

        for(int i = 0; i < array.length; i++ ){
            System.out.print(array[i] + " " );
        }
    }
// Splitting only the splitting takes O(log n)
    private static void mergeSort(int[] array) { 
        int length = array.length;
        if(length <= 1) return; //base case
          
        int middle = length / 2;
        int[] leftArray = new int[middle];
        int[] rightArray = new int[length - middle];

        int i = 0; //left array
        int j = 0; //right array
        for(; i < length; i++) {
            if(i < middle) {
                leftArray[i] = array[i];
            }
            else{
                rightArray[j] = array[i];
                j++;
            }
        }
        mergeSort(leftArray); //O(logn)
        mergeSort(rightArray);  //O(logn)
        merge(leftArray, rightArray, array); //O(n)

    }
// You do this n times, you touch every element once at each log n level. 
// So this said, complexity takes O(n log n)
    private static void merge(int[] leftArray, int[] rightArray, int[] array){
        int leftSize = array.length / 2;
        int rightSize = array.length - leftSize;
        int i = 0, l = 0, r = 0; //indices
        //check the ocnditions for merging
        while(l < leftSize && r < rightSize){
            if(leftArray[l] < rightArray[r]){
                array[i] = leftArray[l];
                i++;
                l++;
            }
            else{
                array[i] = rightArray[r];
                i++;
                r++;
            }
        }
        while(l < leftSize){
            array[i] = leftArray[l];
            i++;
            l++;

        }
        while(r < rightSize){
            array[i] = rightArray[r];
            i++;
            r++;
        }
    }


}
