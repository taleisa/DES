package com.company;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
    public static HashMap<String,String> keyExpansion(char[] keyArray){
        HashMap<String,String> roundKeys;
        char[] permutatedKeyArray =
                {
                        keyArray[56], keyArray[48], keyArray[40], keyArray[32], keyArray[24], keyArray[16], keyArray[8]
                        , keyArray[0], keyArray[57], keyArray[49], keyArray[41], keyArray[33], keyArray[25], keyArray[17]
                        , keyArray[9], keyArray[1], keyArray[58], keyArray[50], keyArray[42], keyArray[34], keyArray[26]
                        , keyArray[18], keyArray[10], keyArray[2], keyArray[59], keyArray[51], keyArray[43], keyArray[35]
                        , keyArray[62], keyArray[54], keyArray[46], keyArray[38], keyArray[30], keyArray[22], keyArray[14]
                        , keyArray[6], keyArray[61], keyArray[53], keyArray[45], keyArray[37], keyArray[29], keyArray[21]
                        , keyArray[13], keyArray[5], keyArray[60], keyArray[52], keyArray[44], keyArray[36], keyArray[28]
                        , keyArray[20], keyArray[12], keyArray[4], keyArray[27], keyArray[19], keyArray[11], keyArray[3]};
        roundKeys = splitAndJoin(permutatedKeyArray);// Method that will split, rotate, join, permutate and store the round keys in a hashmap. This will yield the final form of all round keys 
        
        
        
        return roundKeys;


    }
    public static HashMap<String,String> splitAndJoin(char[] keyArray){
        HashMap<String,String> roundKeys = new HashMap<>();
        char[] leftHalf = Arrays.copyOfRange(keyArray, 0,(keyArray.length/2)-1);// The left half also known as C0
        char[] rightHalf = Arrays.copyOfRange(keyArray, keyArray.length/2,keyArray.length-1);// The right half also known as D0
        for(int i=0;i<16;i++){
            if(i==1||i==2||i==9||i==16){// In the 1st 2nd 9th and 16th round rotate by one position
                leftHalf = rotate(leftHalf, 1);
                rightHalf = rotate(rightHalf, 1);
            }
            else{// Else rotate by two positions
                leftHalf = rotate(leftHalf, 2);
                rightHalf = rotate(rightHalf, 2);
            }
            char[] joined = new char[56];
            for(int j=0;j<56;j++){
                if(i<28){//To add the left array(first 28 bits)
                    joined[i] = leftHalf[i];
                }else{// To add the right array(28 bits) which start at index 28
                    joined[i] = rightHalf[i-28];// We subtract 28 because at iteration 28 we want the first index of the right array
                }
            }
            joined = permutate(joined);
            roundKeys.put("key"+i,Arrays.toString(joined));
            
        }
        return roundKeys;
        



    }
    //Method that will left rotate by int rotation
    public static char[] rotate(char[] halfKey, int rotation ){
        for(int i=0;i<rotation;i++){
            char temp = halfKey[i];
            halfKey[i] = halfKey[halfKey.length-(i+1)];
            halfKey[halfKey.length-(i+1)] = temp;
        }
        return halfKey;


    }
    public static char[] permutate(char[] keyArray){
        char[] permutatedKeyArray =
                {
                        keyArray[13], keyArray[16], keyArray[10], keyArray[23], keyArray[0], keyArray[4]
                        , keyArray[2], keyArray[27], keyArray[14], keyArray[5], keyArray[20], keyArray[9]
                        , keyArray[22], keyArray[18], keyArray[11], keyArray[3], keyArray[25], keyArray[7]
                        , keyArray[15], keyArray[6], keyArray[26], keyArray[19], keyArray[12], keyArray[1]
                        , keyArray[40], keyArray[51], keyArray[30], keyArray[36], keyArray[46], keyArray[54]
                        , keyArray[29], keyArray[39], keyArray[50], keyArray[44], keyArray[32], keyArray[47]
                        , keyArray[43], keyArray[48], keyArray[38], keyArray[55], keyArray[33], keyArray[52]
                        , keyArray[45], keyArray[41], keyArray[49], keyArray[35], keyArray[28], keyArray[31]};
        return permutatedKeyArray;
                        

    }

}
