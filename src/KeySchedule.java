/**
 * Created by minhduong on 3/15/17.
 */
public class KeySchedule {
    private static final int[] RC = {0x01, 0x02, 0x04, 0x08, 0x10,
            0x20, 0x40, 0x80, 0x1B, 0x36};

    public static int[][] generateKey(int[] key){

        int[][] subkeys = new int[AES.ROUND_NUMBERS+1][16];//11 subkeys will be generated
        long[] W = new long[4*(AES.ROUND_NUMBERS+1)]; //44 words

        //Generate initial key (round key 0): subkeys[0]
        for(int i=0; i<4; i++){
            W[i] = (key[4*i+0]<<24) + (key[4*i+1] << 16) + (key[4*i+2] << 8) + key[4*i+3]; //concatenate 4 bytes to form a word
        }
        subkeys[0] = Util.fourWordsTo16Bytes(W[0], W[1], W[2], W[3]);
        //Generate round keys: subkeys[1], ..., subkeys[10]
        for(int r=1; r<=AES.ROUND_NUMBERS; r++){
            W[4*r] = W[4*(r-1)] ^ g(W[4*(r-1)+3], r);
            for(int j=1; j<4; j++){
                W[4*r+j] = W[4*(r-1)+j-1] ^ W[4*(r-1)+j];
            }
            subkeys[r] = Util.fourWordsTo16Bytes(W[4*r+0],W[4*r+1],W[4*r+2],W[4*r+3]);
        }
        return subkeys;
    }
    /**
     @param V 4-bytes input
     @param r current round
     */
    public static long g(long W, int round){
        //Split W into 4 bytes
        int[] V = Util.wordTo4Bytes(W);

        //V[i] is shifted and substituted
        for(int i=0; i<4; i++){
            V[i] = V[(i+1)%4];
            V[i] = SBox.byteSubstitute(V[i]);
        }
        //V[0] is XORed with the round coefficient
        V[0] = V[0] ^ RC[round-1];

        //4 bytes are combined into a word
        long output = (V[0]<<24) + (V[1] << 16) + (V[2] << 8) + V[3];
        return output;
    }
}
