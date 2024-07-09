public class DiagonalSum {
    public static int diagonalSum(int[][] m){
        int l = m.length-1;
        int sum = 0;
        for(int i = 0;i<=l;i++){
            sum += m[i][i];
            if(i != m.length-1-i){
                sum += m[i][m.length-i-1];
            }
        }
        return sum;
    }
    public static void main(String[] args) {
        int martix[][] = {{1,2,3,4}
                        ,{5,6,7,8},
                         {9,10,11,12}, 
                         {13,14,15,16}};
        int ans = diagonalSum(martix);
        System.out.println(ans);
        
    }
}
