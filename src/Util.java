/**
 * Created by minhduong on 3/15/17.
 */


public class Util {
    /**
     Converts a string of 16-characters to 16-bytes
     @param input a string of 16 characters e.g. "abc def ghj jmk"
     @return an array of 16 bytes
     */
    public static int[] strToBytes(String input){
        byte[] byteArray =  input.getBytes(); //an array of 16 bytes, e.g. {97, 97, 99, ...}
        int[] intArray = new int[byteArray.length];
        for (int i = 0; i < byteArray.length; intArray[i] = byteArray[i++]);
        return intArray;
    }
    /**
     Inverse of strToBytes
     @param input 16-bytes
     @return string representation of input bytes
     */
    public static String bytesToStr(int[] input){
        byte[] byteArray = new byte[input.length];
        for(int i=0; i<input.length; byteArray[i] = (byte) input[i++]);
        return new String(byteArray);
    }
    /**
     Convert a one-dim array to a two-dime array
     @param datapath an array of 16 bytes
     @return a 4x4 matrix
     */
    public static int[][] vectorToMatrix(int[] datapath){
        int[][] B = new int[4][4];
        for(int row = 0; row<4; row++){
            for(int col = 0; col<4; col++){
                B[row][col] = datapath[row+col*4];
            }
        }
        return B;
    }
    /**
     Convert a two-dim array to a one-dim array
     @param C a 4 x 4 matrix
     @return an array of 16 bytes
     */
    public static int[] matrixToVector(int[][] C){
        int[] datapath = new int[16];
        for(int col = 0; col<4; col++){
            for(int row = 0; row<4; row++){
                datapath[row + col*4] = C[row][col];
            }
        }
        return datapath;
    }
    /**
     Split a word into 4 bytes
     Used in the key schedule
     @param W a word of 32 bits
     @return an array of 4 bytes
     */
    public static int[] wordTo4Bytes(long W){
        int[] V = new int[4];
        V[0] = (int)((W & 0xFF000000) >> 24);
        V[1] = (int)((W & 0x00FF0000) >> 16);
        V[2] = (int)((W & 0x0000FF00) >> 8);
        V[3] = (int)(W & 0x000000FF);
        return V;
    }
    /**
     Split four words into 16 bytes
     Used in the KeySchedule
     */
    public static int[] fourWordsTo16Bytes(long W0, long W1, long W2, long W3){
        int[] V = new int[16];
        long[] W = {W0, W1, W2, W3};
        for(int i=0; i<4; i++){
            V[4*i+0] = (int)((W[i] & 0xFF000000) >> 24);
            V[4*i+1] = (int)((W[i] & 0x00FF0000) >> 16);
            V[4*i+2] = (int)((W[i] & 0x0000FF00) >> 8);
            V[4*i+3] = (int)(W[i] & 0x000000FF);
        }
        return V;
    }

    /*
       Galois Field multiplication
       You will need to use this method in the mixColumn layer
       Complete the multiply method
    */
    public static int multiply(int x, int y){
        int result = 0;
        if(x == 0x01){
            result = y;
        }
        else if(x == 0x02){
            result = y << 1; //shift left 1 bit
            if ((result & 256) == 256){ //Reduction mod P(x) = x^8 + x^4 + x^3 + x + 1
                result = (result - 256) ^ Integer.parseInt("11011", 2);
            }
        }
        else if(x == 0x03){
            result = multiply(0x02, y) ^ y;
        }
        else if(x == 0x09){}
        else if(x == 0x0B){}
        else if(x == 0x0D){}
        else if(x == 0x0E){}

        return result;
    }
}
