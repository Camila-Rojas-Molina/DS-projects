package sorting;

// quick sort = moves smaller elements to left of a pivot.
//              recursively divide array in 2 partitions

//   run-time complexity = Best case O(n log(n))
//	                       Average case O(n log(n))
//                         Worst case O(n^2) if already sorted

//  space complexity     = O(log(n)) due to recursion

public class QuickSort {
    public static void main(String[] args) {
        int[] array = {8, 2, 5, 3, 9, 4, 7, 6, 1};

        for(int i: array){
            System.out.print(i + " ");
        }
        System.out.println();

        quickSort(array, 0, array.length - 1);

         for(int i: array){
            System.out.print(i + " ");
        }
    }

    private static void quickSort(int[] array, int start, int end) {
       
        if(end <= start) return; //base case

        int pivot = partition(array, start, end);

        quickSort(array, start, pivot - 1);
        quickSort(array, pivot + 1, end);


    }
    // This will sort our array, and find our pivot
    private static int partition(int[] array, int start, int end) {
        int pivot = array[end];
        int i = start - 1; //i start before j (the beginning of array)
        for(int j = start; j<= end-1; j++){
            if(array[j] < pivot){ //if j is less than pivot
                i++; //increment i
                int temp = array[i]; //and swap with j
                array[i] = array[j];
                array[j] = temp;

            }
        }
        i++; // inserting our pivot where it should be (the middle)
        int temp = array[i];
        array[i] = array[end];
        array[end] = temp;

        return i; //location of our pivot

    }   
}
