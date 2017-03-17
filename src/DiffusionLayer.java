/**
 * Created by minhduong on 3/15/17.
 */
public class DiffusionLayer {
    /**
     The matrix M used in the mixColumn layer
     */
    private static final int[][] M = {{0x02, 0x03, 0x01, 0x01},
            {0x01, 0x02, 0x03, 0x01},
            {0x01, 0x01, 0x02, 0x03},
            {0x03, 0x01, 0x01, 0x02}};
    /**
     ShiftRow layer
     */
    public static int[][] shiftRow(int[] d){
        //Shift row left
        int[][] B = {{d[0],d[4],d[8],d[12]},
                {d[5],d[9],d[13],d[1]},
                {d[10],d[14],d[2],d[6]},
                {d[15],d[3],d[7],d[11]}};
        return B;
    }

    public static int calculate_C(int col, int row ,int[][] B) {
        int result = 0;
        for (int index = 0; index<4; index++)
            result = result ^ Util.multiply(M[row][index],B[index][col]);
        return result;
    }

    /**
     Complete the MixColumn layer
     You will need to use the Util.multiply function
     @return 16-bytes output
     */
    public static int[] mixColumn(int[][] B){
        int[][] C = new int[4][4];

        for ( int col = 0; col< 4; col++) {
            for (int row = 0; row<4; row++) {
                C[row][col] = calculate_C(col, row, B);
            }
        }
        // Put your code here
        // ...

        return Util.matrixToVector(C);
    }
}
