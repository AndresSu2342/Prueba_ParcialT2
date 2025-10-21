package edu.eci.arep;

public class MathService {
    public static int linearSearch(String[] list, String value){
        for(int i=0; i<list.length; i++){
            if(list[i].equals(value)){
                return i;
            }
        }
        return -1;
    }

    public static int binarySearch(String[] list, String value){
        return binarySearchRecursive(list, value, 0, list.length-1);
    }

    public static int binarySearchRecursive(String[] list, String value, int low, int high){
        if (low > high) {
            return -1;
        }
        int mid = (low + high)/2;
        if (list[mid].equals(value)){
            return mid;
        }
        else if (list[mid].compareTo(value) > 0){
            return binarySearchRecursive(list, value, low, mid - 1);
        }
        else if (list[mid].compareTo(value) < 0){
            return binarySearchRecursive(list, value, mid + 1, high);
        }
    }
}
