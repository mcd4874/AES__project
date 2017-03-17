/**
 * Created by minhduong on 3/15/17.
 */
public class KeyAdditionLayer {
    /**
     Complete the keyAddition layer
     Input datapath is XORed with subkey
     datapath: 16 bytes
     */
    public static int[] keyAddition(int[] datapath, int[] subkey){
        int[] output = new int[datapath.length];
        // loop through each byte to perform xor
        for (int index=0; index<datapath.length; index++)
            output[index] = datapath[index] ^ subkey[index];
        return output;
    }
}
