public class SearchInSortedMatrix{
    public static boolean search(int[][] m, int key){
        int row = 0;
        int col = m.length-1;
        while(row<= m.length-1 && col >= 0){
            if(m[row][col]==key){
                return true;
            }
            else if(key < m[row][col]){
                col--;
            }
            else{
                row++;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        int martix[][] = {{1,2,3,4}
                        ,{5,6,7,8},
                         {9,10,11,12}, 
                         {13,14,15,16}};
        int key = 20;
        
        System.out.println(search(martix,key));
        
    }
}