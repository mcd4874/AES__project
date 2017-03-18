/**
 * Created by minhduong on 3/17/17.
 */
public class test {

    public static void test_subsitution_byte () {
        int inputByte = 0xEF;
        int after_sub = SBox.byteSubstitute(inputByte);
        System.out.print("test single byte subsitution : "+inputByte+" -> "+after_sub);

        String [] data = { "00" ,"3C" ,"6E" ,"47" ,"1F" ,"4E" ,"22" ,"74" ,"0E" ,"08" ,"1B" ,"31", "54", "59","0B", "1A" };
        int [] output = new int[16];
        int index = 0;
        for ( String hex : data ) {
            output[index] =  Integer.parseInt( hex, 16 ) ;
            index++;
        }
        index = 0;
        int [] data_after_sbox = SBox.substitute(output);
        for ( int number : data_after_sbox){
            System.out.print(" before S-box : "+data[index]+"| after S-box : "+Integer.toHexString(number)+" \n");
            index++;
        }

    }

}
