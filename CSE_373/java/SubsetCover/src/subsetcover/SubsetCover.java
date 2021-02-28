/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package subsetcover;

/**
 *
 * @author John
 */
public class SubsetCover {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        12
//        6
//        1 2 3 4 5 6
//        5 6 8 9
//        1 4 7 10
//        2 5 7 8 11
//        3 6 9 12
//        10 11
        SubsetCover n = new SubsetCover();
        int subsets[][] = {{1, 2,3, 4, 5, 6},
                            {5,6 ,8, 9}, 
                            {1, 4, 7, 10},
                            {2 ,5 ,7 ,8 ,11},
                            {3,6,9,12},
                            {10,11}};
        n.mstCover(12, 6, subsets);
    }
    
    /*
        PYTHON BIT VECTOR
        def combo(arr, marker):
	if (marker >= len(arr)):
		print(arr);
	else:
		temp = arr.copy()
		temp[marker]= 0
		combo(temp, marker+1)
		temp[marker] = 1
		combo(temp, marker+1)
    */
    
    int[][] bitArray;
    int bitCounter=0;
    
    int[] selectArr;
    int minCount;
    int givenCount;
    
    public void mstCover(int size, int numSets, int[][] subsets){
        //FOR RIGHT NOW
        //INITIALIZE UNIVERSAL SET
        int USet[] = new int[size];
        for(int i=1; i<=size; i++){
            USet[i-1] = i-1;
        }
        boolean bitBool[] = new boolean[numSets];
        for(int i=0; i<bitBool.length; i++){
            bitBool[i] = true;
        }
        bitArray = new int[(int)Math.pow(2, numSets)][numSets];
        int length = subsets.length;
        int arr[] = new int[length];
        for(int i=0; i<length; i++){
            arr[i] = -1;
        }
        bitVector(arr, 0);
        for(int i=0; i<bitArray.length; i++){
            int[] uSetCpy = copy(USet);
            for(int j=0; j<bitArray[i].length; j++){
               if(bitArray[i][j]==1){
                   for(int y=0; y<uSetCpy.length; y++){
                       if(uSetCpy[y]==subsets[i][j]){
                           uSetCpy[y]=0;
                       }
                   }
               }
            }
            for(int j=0; j<uSetCpy.length; j++){
                if(uSetCpy[j] != 0){
                    bitBool[i] = false;
                }
            }
            
            for(int j=0; j<bitBool.length; j++){
                if(bitBool[j]){
                    givenCount = minCount;
                    minCount = 0;
                    for(int y=0; y<bitArray[j].length; y++){
                        if(bitArray[j][y]==1){
                            minCount++;
                        }
                    }
                    if(j==0 || minCount < givenCount){
                        selectArr = bitArray[j];
                    }
                }
            }
            
        }
        for(int i=0; i<selectArr.length; i++){
            System.out.println("Element " + selectArr[i]);
        }
    }
    
    public int[] bitVector(int[] arr, int marker){
        if (marker >= arr.length){
            bitArray[bitCounter] = arr;
            bitCounter++;
        }
        else{
		int[] temp = copy(arr);
		temp[marker]= 0;
		bitVector(temp, marker+1);
		temp[marker] = 1;
		bitVector(temp, marker+1);
        }
        return arr;
    }
    
    public int[] copy(int[] temp){
        int[] temp_arr = new int[temp.length];
        for(int i=0; i<temp.length; i++){
            temp_arr[i] = temp[i];
        }
        return temp_arr;
    }
    
    public boolean testSet(int[] USet, int[][] subsets){
        boolean[] boolArr = new boolean[USet.length];
        int[] testArr = new int[USet.length];
        int arrCounter=0;
        
       for(int i=0; i<subsets.length; i++){
           for(int j=0; j<subsets[i].length; j++){
                if(!checkDup(testArr, subsets[i][j])){
                    testArr[arrCounter] = subsets[i][j];
                    arrCounter++;
                }
           }
       }
       
       for(int i=0; i<testArr.length; i++){
            boolArr[testArr[i]]=true;
       }
       for(int i=0; i<boolArr.length; i++){
           if(!boolArr[i]){
                return false;
           }
       }
       return true;
    }
    
    public boolean checkDup(int[] arr, int value){
        for(int i=0; i<arr.length; i++){
            if(arr[i]==value){
                return true;
            }
        }
        return false;
    }
}
