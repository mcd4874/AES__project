/**
 * Created by minhduong on 3/15/17.
 */
public class AES {
    public static final int ROUND_NUMBERS = 10;
    /**
     AES Encryption
     @param datapath 16-bytes plaintext block
     @param key 16-bytes main key
     */
    public static int[] encrypt(int[] datapath, int[] key){
        //Generate initial key and 10 round keys
        int[][] subkey = KeySchedule.generateKey(key);
        //Initial KeyAddition layer
        datapath = KeyAdditionLayer.keyAddition(datapath, subkey[0]);
        //Iterate the round function for encrpytion
        for(int round = 1; round<=ROUND_NUMBERS; round++){
            datapath = roundFunctionEncrypt(round, datapath, subkey[round]);
        }
        return datapath;
    }

    /**
     Complete the following method
     Round function for encryption constists of 4-layers
     @param round  current round
     @param datapath current 16-bytes datapath
     @param subkey subkey used in the current round
     */
    public static int[] roundFunctionEncrypt(int round, int[] datapath, int[] subkey){
        //1. ByteSubstitute layer

        //2. ShiftRow layer

        //3. MixColumn layer

        //4. KeyAddition layer


        return datapath;
    }
    /**
     AES Decryption
     @param datapath 16-bytes ciphertext block
     @param key 16-bytes main key
     */
    public static int[] decrypt(int[] datapath, int[] key){
        int[][] subkey = KeySchedule.generateKey(key);

        for(int round = ROUND_NUMBERS; round >=1; round--){
            datapath = roundFunctionDecrypt(round, datapath, subkey[round]);
        }
        datapath = KeyAdditionLayer.keyAddition(datapath, subkey[0]);
        return datapath;
    }
    /**
     Complete the following method.
     Round function for decryption inverts each encryption layer
     in reverse order.
     @param round  current round
     @param datapath current 16-bytes datapath
     @param subkey subkey used in the current round
     */

    public static int[] roundFunctionDecrypt(int round, int[] datapath, int[] subkey){
        //1. KeyAddition layer


        //2. MixColumn layer


        //3. Inverse ShiftRow layer


        //4. Inverse ByteSubstitute layer



        return datapath;
    }
}
