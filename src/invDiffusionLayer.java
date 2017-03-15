/**
 * Created by minhduong on 3/15/17.
 */
public class invDiffusionLayer {
    /**
     Inverse matrix of M
     */
    private static final int[][] InvM = {{0x0E, 0x0B, 0x0D, 0x09},
            {0x09, 0x0E, 0x0B, 0x0D},
            {0x0D, 0x9, 0x0E, 0x0B},
            {0x0B, 0x0D, 0x09, 0x0E}};
    /**
     Inverse shiftRow layer
     */
    public static int[] shiftRow(int[][] B){
        //Shift row right
        int[] d = {B[0][0],B[1][3], B[2][2], B[3][1],
                B[0][1], B[1][0], B[2][3], B[3][2],
                B[0][2], B[1][1], B[2][0], B[3][3],
                B[0][3], B[1][2], B[2][1], B[3][0]};
        return d;
    }
    /**
     Complete the inverse mixColumn layer
     You will need to use the Util.multiply function
     @param 16-bytes datapath
     @return a 4x4 matrix
     */
    public static int[][] mixColumn(int[] datapath){
        int[][] C = Util.vectorToMatrix(datapath);
        int[][] B = new int[4][4];
        //Put your code here
        //...
        return B;
    }
}
