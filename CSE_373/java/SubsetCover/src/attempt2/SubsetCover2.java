/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package attempt2;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author John
 */
public class SubsetCover2 {
    
    //INT GLOBAL VARIABLES 
    static int[][] bitVectorArray;
    int bitArrayCounter = 0;
    static ArrayList<Integer> USet;
    int minCount=0;
    static int[][] subsets;
    
    //FINAL ARRA
    static int[] finalSelectArr;
    
    public static void main(String[] args){
          try{
            URL url = new URL("http://www3.cs.stonybrook.edu/~skiena/373/setcover/s-k-20-30");
            Scanner input = new Scanner(url.openStream());
            ArrayList<String> fileStr = new ArrayList<>();
            while(input.hasNext()){
                String str = input.nextLine();
                fileStr.add(str);
            }
            int size=0;
            int numSets=0;
            int fileSubSets[][] = {};
            for(int i=0; i<fileStr.size(); i++){
                if(i==0){
                    size = Integer.parseInt(fileStr.get(i));
                } else if(i==1){
                    numSets = Integer.parseInt(fileStr.get(i));
                } else{
                    String line = fileStr.get(i);
                    String[] lineArr = line.split(" ");
                    if(i==2){
                        fileSubSets = new int[numSets][size];
                    }
                    for(int j=0; j<lineArr.length; j++){
                        if(lineArr[j].equals("")){
                            fileSubSets[i-2][j] = 0;
                        } else{
                            fileSubSets[i-2][j] = Integer.parseInt(lineArr[j]);
                        }
                    }
                }
            }
            
            SubsetCover2 b = new SubsetCover2();
            //b.mstCover(8, 10, subsets2);   //origin:   b.mstCover(12, 6, subsets);
            b.mstCover(size, numSets, fileSubSets);
            input.close();
          } catch(MalformedURLException ex){
              System.out.print("What happened");
          } catch(IOException ex){
              System.out.print("Pick one");
          }
    }
    
    public void mstCover(int size, int numSets, int[][]subsets){
        //bitVectorArray = new int[(int)Math.pow(2, numSets)][numSets];
        ArrayList<int[]> jack = new ArrayList<>();
        int[] arr = {-1, -1, -1};
        SubsetCover2 bv = new SubsetCover2();
        //bitVectorArray = new int[8][3];
        USet = new ArrayList<>();
        for(int i=1; i<=size; i++){
            USet.add(i);
        }
        this.subsets = subsets;
        int sets[] = new int[numSets];
        
        bv.bitVector(sets, 0);

        for(int i=0; i<finalSelectArr.length; i++){
            if(finalSelectArr[i] == 1){
                System.out.print("Subset " + (i+1) + " [");
                for(int j=0; j<subsets[i].length; j++){
                    if(subsets[i][j]!=0){
                        System.out.print(subsets[i][j] + ", ");
                    }
                }
                System.out.println("]");
            }
        }
        System.out.println();
    }
    
    public void bitVector(int[] arr, int marker){
        if(marker>=arr.length){
            //bitVectorArray[bitArrayCounter] = arr;
            bitArrayCounter++;
            //DEBUG
//            System.out.print("Arr ");
//            for(int i=0; i<arr.length; i++){
//                System.out.print(arr[i]+" ");
//            }
//            System.out.println();
            ArrayList<Integer> candidate = constructCandidate(arr, subsets);
            if(testSet(candidate, USet)){
                int newMinCount=0;
                for(int i=0; i<arr.length; i++){
                    if(arr[i]==1){
                        newMinCount++;
                    }
                }
                if(minCount==0){
                    minCount=newMinCount;
                    this.finalSelectArr = copy(arr);
                } else if(newMinCount<minCount){
                    minCount = newMinCount;
                    this.finalSelectArr = arr;
                }
            }
        } else{
            int[] temp = copy(arr);
            temp[marker]=0;
            bitVector(temp, marker+1);
            temp[marker]=1;
            bitVector(temp, marker+1);
        }
    }
    
    public ArrayList<Integer> constructCandidate(int arr[], int subsets[][]){
        int arrCounter =0;
        ArrayList<Integer> finalArr = new ArrayList<>();
        for(int i=0; i<subsets.length; i++){
            if(arr[i]==1){
                for(int j=0; j<subsets[i].length; j++){
                    finalArr.add(subsets[i][j]);
                }
            }
        }
        return finalArr;
    }
    
    public boolean testSet(ArrayList<Integer> finalArr, ArrayList<Integer> USet){
        int counter=0;
        SubsetCover2 s = new SubsetCover2();
        ArrayList<Integer> USet2 = s.clone(USet);
        for(int i=0; i<finalArr.size(); i++){
            USet2.remove((Integer)finalArr.get(i));
        }
        if(USet2.isEmpty()){
            return true;
        } else{
            return false;
        }
    }
    
    public ArrayList<Integer> clone(ArrayList<Integer> arr){
        ArrayList<Integer> arr2 = new ArrayList<>();
        for(int i=0; i<arr.size(); i++){
            arr2.add(i, arr.get(i));
        }
        return arr2;
    }
    
    public int[] copy(int [] arr){
        int[] temp = new int[arr.length];
        for(int i=0; i<arr.length; i++){
            temp[i] = arr[i];
        }
        return temp;
    }
}
