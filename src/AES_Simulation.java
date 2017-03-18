/**
 * Created by minhduong on 3/15/17.
 */

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class AES_Simulation {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length != 2) {
            System.out.println("Usage: java AES_Simulation plaintext-filename key-filename");
            return;
        }

        String plaintextFileName = args[0];
        String keyFileName = args[1];

        //Open file to read plaintext and key
        Scanner input0 = new Scanner(new File(plaintextFileName));
        Scanner input1 = new Scanner(new File(keyFileName));

      /*A key of 64-bits*/
        String key = input1.nextLine();

        String inputText = input0.nextLine();
        int[] x = Util.strToBytes(inputText); //plaintext, a block of 16 bytes
        int[] y = AES.encrypt(x, Util.strToBytes(key)); //ciphertext, a block of 16 bytes

        //Your ciphertext will be tested
        //Uncomment the following test code later
      for(int i : y){
         System.out.print(Integer.toHexString(i) + " ");
      }
      System.out.println();


        int[] decrypted = AES.decrypt(y, Util.strToBytes(key));


        //Decrypted bytes must match plaintext x
        //Uncomment the following test code later
      for(int i=0; i<16; i++){
         System.out.print((x[i] == decrypted[i]) + " ");//This will be tested
      }
      System.out.println();


        String recoveredText = Util.bytesToStr(decrypted);
        System.out.println("recoverted Text: ");
        System.out.println(recoveredText);
        System.out.println(recoveredText.equals(inputText));
    }
}
