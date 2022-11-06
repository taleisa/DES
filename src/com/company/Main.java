package com.company;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter message to encrypt: (In Binary)");
        String messageString = input.nextLine();// The message in string format

        System.out.println("Enter the key (In Binary 56-bit)");
        String keyString = input.nextLine();// String that will hold the key
        while (keyString.length() != 56) {// Exception handling for key size larger than 56
            System.out.println("Key length must be exactly 56 bits, retype the key");
            keyString = input.nextLine();
        }

        char[] messageArray = messageString.toCharArray();// Converting string to array to easily manipulate
        char[] keyArray = keyString.toCharArray();// Converting string to array to easily manipulate
        keyExpansion(keyArray);// Method to handle key expansion

        char[] messageAfterIP =
                {
                        messageArray[57], messageArray[49], messageArray[41], messageArray[33], messageArray[25], messageArray[17], messageArray[9], messageArray[1]
                        , messageArray[59], messageArray[51], messageArray[43], messageArray[35], messageArray[27], messageArray[19], messageArray[11], messageArray[3]
                        , messageArray[61], messageArray[53], messageArray[45], messageArray[37], messageArray[29], messageArray[21], messageArray[13], messageArray[5]
                        , messageArray[63], messageArray[55], messageArray[47], messageArray[39], messageArray[31], messageArray[23], messageArray[15], messageArray[7]
                        , messageArray[56], messageArray[48], messageArray[40], messageArray[32], messageArray[24], messageArray[16], messageArray[8], messageArray[1]
                        , messageArray[58], messageArray[50], messageArray[42], messageArray[34], messageArray[26], messageArray[18], messageArray[10], messageArray[2]
                        , messageArray[60], messageArray[52], messageArray[44], messageArray[36], messageArray[28], messageArray[20], messageArray[12], messageArray[4]
                        , messageArray[62], messageArray[54], messageArray[46], messageArray[38], messageArray[30], messageArray[22], messageArray[14], messageArray[6]};

        System.out.println("Step 1 (Initial Permutation): Input: " + Arrays.toString(messageArray) + " Output: " + Arrays.toString(messageAfterIP));

        char[] messageLeftHalf =
                {
                        messageAfterIP[0], messageAfterIP[1], messageAfterIP[2], messageAfterIP[3], messageAfterIP[4], messageAfterIP[5], messageAfterIP[6], messageAfterIP[7]
                        , messageAfterIP[8], messageAfterIP[9], messageAfterIP[10], messageAfterIP[11], messageAfterIP[12], messageAfterIP[13], messageAfterIP[14], messageAfterIP[15]
                        , messageAfterIP[16], messageAfterIP[17], messageAfterIP[18], messageAfterIP[19], messageAfterIP[20], messageAfterIP[21], messageAfterIP[22], messageAfterIP[23]
                        , messageAfterIP[24], messageAfterIP[25], messageAfterIP[26], messageAfterIP[27], messageAfterIP[28], messageAfterIP[29], messageAfterIP[30], messageAfterIP[31]};

        char[] messageRightHalf =
                {
                        messageAfterIP[32], messageAfterIP[33], messageAfterIP[34], messageAfterIP[35], messageAfterIP[36], messageAfterIP[37], messageAfterIP[38], messageAfterIP[39]
                        , messageAfterIP[40], messageAfterIP[41], messageAfterIP[42], messageAfterIP[43], messageAfterIP[44], messageAfterIP[45], messageAfterIP[46], messageAfterIP[47]
                        , messageAfterIP[48], messageAfterIP[49], messageAfterIP[50], messageAfterIP[51], messageAfterIP[52], messageAfterIP[53], messageAfterIP[54], messageAfterIP[55]
                        , messageAfterIP[56], messageAfterIP[57], messageAfterIP[58], messageAfterIP[59], messageAfterIP[60], messageAfterIP[61], messageAfterIP[62], messageAfterIP[63]};

        System.out.println("Step 2 (Expansion Permutation): Input: " + Arrays.toString(messageAfterIP) + " Left-Half: " +messageLeftHalf+ " Right-Half: " +messageRightHalf+ " Output: ");

    }
    public static char[] keyExpansion(char[] keyArray){
        keyArray = removeParity(keyArray);// Method that will remove parity bits and return 56 bit char array
        
        
        
        return keyArray;


    }
    public static char[] removeParity(char[] key64bit){
        String key56bitString = "";// Use string to concatenate and not worry about index
        char[] key56bit;// Array that will be returned
        for(int i = 0;i<key64bit.length;i++){
            if(i%7==0)continue;// The 8th bit is a parity bit
            key56bitString.concat(key64bit[i]+""); // Add bit to arraylist
        
        }
        key56bit = key56bitString.toCharArray();// Convert string to char array
        return key56bit;
    }
}
