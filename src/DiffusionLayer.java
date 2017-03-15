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
    /**
     Complete the MixColumn layer
     You will need to use the Util.multiply function
     @return 16-bytes output
     */
    public static int[] mixColumn(int[][] B){
        int[][] C = new int[4][4];
        // Put your code here
        // ...

        return Util.matrixToVector(C);
    }
}
