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
        datapath = SBox.substitute(datapath);
            //2. ShiftRow layer
        int[][] shift_datapath = DiffusionLayer.shiftRow(datapath);
        int [] mix_datapath = {};
        if (round != ROUND_NUMBERS) {
            //3. MixColumn layer
            mix_datapath = DiffusionLayer.mixColumn(shift_datapath);
            //last round there is no mix column layer calculation
        } else {
            mix_datapath = Util.matrixToVector(shift_datapath);
        }
        //4. KeyAddition layer
        int [] addition_layer = KeyAdditionLayer.keyAddition(mix_datapath,subkey);

        return addition_layer;
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
        int [] addition_layer = KeyAdditionLayer.keyAddition(datapath, subkey);

        //2. MixColumn layer
        int [][] mixColum = {};
        if (round == ROUND_NUMBERS) {
            mixColum = Util.vectorToMatrix(addition_layer);
        } else {
            mixColum = invDiffusionLayer.mixColumn(addition_layer);
        }
        //3. Inverse ShiftRow layer
        int [] shiftRow = invDiffusionLayer.shiftRow(mixColum);

        //4. Inverse ByteSubstitute layer
        int [] new_datapath = invSBox.substitute(shiftRow);


        return new_datapath;
    }
}
