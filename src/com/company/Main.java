package com.company;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

import javax.annotation.processing.SupportedOptions;

public class Main {

        public static void main(String[] args) {
                // Declaring and initializing a 2D array for each S-Box.
                int[][] sBoxOne = {
                                { 14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7 },
                                { 0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8 },
                                { 4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0 },
                                { 15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13 }
                };

                int[][] sBoxTwo = {
                                { 15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10 },
                                { 3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5 },
                                { 0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15 },
                                { 13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9 }
                };
                int[][] sBoxThree = {
                                { 10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8 },
                                { 13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1 },
                                { 13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7 },
                                { 1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12 }
                };
                int[][] sBoxFour = {
                                { 7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15 },
                                { 13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9 },
                                { 10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4 },
                                { 3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14 }
                };
                int[][] sBoxFive = {
                                { 2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9 },
                                { 14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6 },
                                { 4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14 },
                                { 11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3 }
                };
                int[][] sBoxSix = {
                                { 12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11 },
                                { 10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8 },
                                { 9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6 },
                                { 4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13 }
                };
                int[][] sBoxSeven = {
                                { 4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1 },
                                { 13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6 },
                                { 1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2 },
                                { 6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12 }
                };
                int[][] sBoxEight = {
                                { 13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7 },
                                { 1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2 },
                                { 7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8 },
                                { 2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11 }
                };
                Scanner input = new Scanner(System.in);
                System.out.println("Enter message to encrypt: (In Binary)");
                int blockSize = 64;
                String messageString = input.nextLine();// The message in string format
                String[] messageBlocks = messageString.split("(?<=\\G.{" + blockSize + "})");
                System.out.println("Enter the key (In Binary 64-bit)");
                String keyString = input.nextLine();// String that will hold the key
                while (keyString.length() != 64) {// Exception handling for key size larger than 64
                        System.out.println("Key length must be exactly 64 bits, retype the key");
                        keyString = input.nextLine();
                }
                for (int x = 0; x < messageBlocks.length; x++) {
                        messageString = messageBlocks[x];
                        if (messageString.length() < 64) { // if message is < 64 padding zeros to the left to make sure
                                // it is 64-bit
                                messageString = String.format("%64s", messageString).replace(" ", "0");

                        }
                        char[] messageArray = messageString.toCharArray();// Converting string to array to easily
                                                                          // manipulate
                        char[] keyArray = keyString.toCharArray();// Converting string to array to easily manipulate
                        HashMap<String, String> roundkeys = keyExpansion(keyArray);// Method to handle key expansion

                        char[] messageAfterIP = // Permutating based on the Initial Permutation fixed matrix (64-bit).
                                        {
                                                        messageArray[57], messageArray[49], messageArray[41],
                                                        messageArray[33],
                                                        messageArray[25], messageArray[17], messageArray[9],
                                                        messageArray[1],
                                                        messageArray[59], messageArray[51], messageArray[43],
                                                        messageArray[35],
                                                        messageArray[27], messageArray[19], messageArray[11],
                                                        messageArray[3],
                                                        messageArray[61], messageArray[53], messageArray[45],
                                                        messageArray[37],
                                                        messageArray[29], messageArray[21], messageArray[13],
                                                        messageArray[5],
                                                        messageArray[63], messageArray[55], messageArray[47],
                                                        messageArray[39],
                                                        messageArray[31], messageArray[23], messageArray[15],
                                                        messageArray[7],
                                                        messageArray[56], messageArray[48], messageArray[40],
                                                        messageArray[32],
                                                        messageArray[24], messageArray[16], messageArray[8],
                                                        messageArray[1],
                                                        messageArray[58], messageArray[50], messageArray[42],
                                                        messageArray[34],
                                                        messageArray[26], messageArray[18], messageArray[10],
                                                        messageArray[2],
                                                        messageArray[60], messageArray[52], messageArray[44],
                                                        messageArray[36],
                                                        messageArray[28], messageArray[20], messageArray[12],
                                                        messageArray[4],
                                                        messageArray[62], messageArray[54], messageArray[46],
                                                        messageArray[38],
                                                        messageArray[30], messageArray[22], messageArray[14],
                                                        messageArray[6] };

                        char[] messageLeftHalf = // Taking the left half (32-bit)
                                        {
                                                        messageAfterIP[0], messageAfterIP[1], messageAfterIP[2],
                                                        messageAfterIP[3], messageAfterIP[4], messageAfterIP[5],
                                                        messageAfterIP[6], messageAfterIP[7], messageAfterIP[8],
                                                        messageAfterIP[9], messageAfterIP[10], messageAfterIP[11],
                                                        messageAfterIP[12], messageAfterIP[13], messageAfterIP[14],
                                                        messageAfterIP[15], messageAfterIP[16], messageAfterIP[17],
                                                        messageAfterIP[18], messageAfterIP[19], messageAfterIP[20],
                                                        messageAfterIP[21], messageAfterIP[22], messageAfterIP[23],
                                                        messageAfterIP[24], messageAfterIP[25], messageAfterIP[26],
                                                        messageAfterIP[27], messageAfterIP[28], messageAfterIP[29],
                                                        messageAfterIP[30], messageAfterIP[31] };

                        char[] messageRightHalf = // Taking the right half (32-bit)
                                        {
                                                        messageAfterIP[32], messageAfterIP[33], messageAfterIP[34],
                                                        messageAfterIP[35], messageAfterIP[36], messageAfterIP[37],
                                                        messageAfterIP[38], messageAfterIP[39], messageAfterIP[40],
                                                        messageAfterIP[41], messageAfterIP[42], messageAfterIP[43],
                                                        messageAfterIP[44], messageAfterIP[45], messageAfterIP[46],
                                                        messageAfterIP[47], messageAfterIP[48], messageAfterIP[49],
                                                        messageAfterIP[50], messageAfterIP[51], messageAfterIP[52],
                                                        messageAfterIP[53], messageAfterIP[54], messageAfterIP[55],
                                                        messageAfterIP[56], messageAfterIP[57], messageAfterIP[58],
                                                        messageAfterIP[59], messageAfterIP[60], messageAfterIP[61],
                                                        messageAfterIP[62], messageAfterIP[63] };

                        System.out.println("Step 1 (Initial Permutation): Input: (Original plaintext block: "
                                        + arrayToString(messageArray) + " ) Output: (64-bit permutated text block) "
                                        + arrayToString(messageAfterIP) + "Left-Half: " + arrayToString(messageLeftHalf)
                                        + " Right-Half: "
                                        + arrayToString(messageRightHalf));

                        for (int i = 0; i < 16; i++) {
                                char[] messageAfterEP = // Permutating based on the Expansion Permutation fixed matrix
                                                        // (32-bit to 48-bit).
                                                {
                                                                messageRightHalf[31], messageRightHalf[0],
                                                                messageRightHalf[1],
                                                                messageRightHalf[2], messageRightHalf[3],
                                                                messageRightHalf[4],
                                                                messageRightHalf[3], messageRightHalf[4],
                                                                messageRightHalf[5],
                                                                messageRightHalf[6], messageRightHalf[7],
                                                                messageRightHalf[8],
                                                                messageRightHalf[7], messageRightHalf[8],
                                                                messageRightHalf[9],
                                                                messageRightHalf[10], messageRightHalf[11],
                                                                messageRightHalf[12],
                                                                messageRightHalf[11], messageRightHalf[12],
                                                                messageRightHalf[13],
                                                                messageRightHalf[14], messageRightHalf[15],
                                                                messageRightHalf[16],
                                                                messageRightHalf[15], messageRightHalf[16],
                                                                messageRightHalf[17],
                                                                messageRightHalf[18], messageRightHalf[19],
                                                                messageRightHalf[20],
                                                                messageRightHalf[19], messageRightHalf[20],
                                                                messageRightHalf[21],
                                                                messageRightHalf[22], messageRightHalf[23],
                                                                messageRightHalf[24],
                                                                messageRightHalf[23], messageRightHalf[24],
                                                                messageRightHalf[25],
                                                                messageRightHalf[26], messageRightHalf[27],
                                                                messageRightHalf[28],
                                                                messageRightHalf[27], messageRightHalf[28],
                                                                messageRightHalf[29],
                                                                messageRightHalf[30], messageRightHalf[31],
                                                                messageRightHalf[0] };

                                System.out.println("Step 2 (Expansion Permutation): Input: (32-bit Right-Half: "
                                                + arrayToString(messageRightHalf) + " ) Output: (48-bit expanded block)"
                                                + arrayToString(messageAfterEP));

                                // XORing 48-bit output from the expansion permutation with 48-bit Round key
                                // given from user using ^ operator. before XOR we converted Array to String
                                // then to int, after XOR we convert it back to string.
                                String XORwithKey = xor(arrayToString(messageAfterEP), roundkeys.get("key" + i));
                                System.out.println(roundkeys.get("key" + i));

                                System.out.println(
                                                "Step 3 (Round Key Addition): Input: (48-bit output from the expansion permutation: "
                                                                + arrayToString(messageAfterEP)
                                                                + " , 48-bit Round key: "
                                                                + roundkeys.get("key" + i)
                                                                + " ) Output: " + XORwithKey);

                                char[] roundKeyAdditionArray = XORwithKey.toCharArray();// Converting string to array to
                                                                                        // easily manipulate

                                // Declaring the 8 6-bit values taken from round key addition.
                                char[] toSBoxOne = { roundKeyAdditionArray[0], roundKeyAdditionArray[1],
                                                roundKeyAdditionArray[2],
                                                roundKeyAdditionArray[3], roundKeyAdditionArray[4],
                                                roundKeyAdditionArray[5] };
                                char[] toSBoxTwo = { roundKeyAdditionArray[6], roundKeyAdditionArray[7],
                                                roundKeyAdditionArray[8],
                                                roundKeyAdditionArray[9], roundKeyAdditionArray[10],
                                                roundKeyAdditionArray[11] };
                                char[] toSBoxThree = { roundKeyAdditionArray[12], roundKeyAdditionArray[13],
                                                roundKeyAdditionArray[14],
                                                roundKeyAdditionArray[15], roundKeyAdditionArray[16],
                                                roundKeyAdditionArray[17] };
                                char[] toSBoxFour = { roundKeyAdditionArray[18], roundKeyAdditionArray[19],
                                                roundKeyAdditionArray[20],
                                                roundKeyAdditionArray[21], roundKeyAdditionArray[22],
                                                roundKeyAdditionArray[23] };
                                char[] toSBoxFive = { roundKeyAdditionArray[24], roundKeyAdditionArray[25],
                                                roundKeyAdditionArray[26],
                                                roundKeyAdditionArray[27], roundKeyAdditionArray[28],
                                                roundKeyAdditionArray[29] };
                                char[] toSBoxSix = { roundKeyAdditionArray[30], roundKeyAdditionArray[31],
                                                roundKeyAdditionArray[32],
                                                roundKeyAdditionArray[33], roundKeyAdditionArray[34],
                                                roundKeyAdditionArray[35] };
                                char[] toSBoxSeven = { roundKeyAdditionArray[36], roundKeyAdditionArray[37],
                                                roundKeyAdditionArray[38],
                                                roundKeyAdditionArray[39], roundKeyAdditionArray[40],
                                                roundKeyAdditionArray[41] };
                                char[] toSBoxEight = { roundKeyAdditionArray[42], roundKeyAdditionArray[43],
                                                roundKeyAdditionArray[44],
                                                roundKeyAdditionArray[45], roundKeyAdditionArray[46],
                                                roundKeyAdditionArray[47] };

                                // In each of the 8 6-bit value, getting the column by converting bits at index
                                // 0 and 5 to int, getting the row by converting bits at index 1,2,3,4 to int.
                                // using this column and row we will get a decimal value from the s-box.
                                int decimalOne = sBoxOne[Integer.parseInt(toSBoxOne[0] + "" + toSBoxOne[5], 2)][Integer
                                                .parseInt(toSBoxOne[1] + "" + toSBoxOne[2] + "" + toSBoxOne[3] + ""
                                                                + toSBoxOne[4], 2)];
                                int decimalTwo = sBoxTwo[Integer.parseInt(toSBoxTwo[0] + "" + toSBoxTwo[5], 2)][Integer
                                                .parseInt(toSBoxTwo[1] + "" + toSBoxTwo[2] + "" + toSBoxTwo[3] + ""
                                                                + toSBoxTwo[4], 2)];
                                int decimalThree = sBoxThree[Integer
                                                .parseInt(toSBoxThree[0] + "" + toSBoxThree[5], 2)][Integer
                                                                .parseInt(toSBoxThree[1] + "" + toSBoxThree[2] + ""
                                                                                + toSBoxThree[3] + "" + toSBoxThree[4],
                                                                                2)];
                                int decimalFour = sBoxFour[Integer.parseInt(toSBoxFour[0] + "" + toSBoxFour[5],
                                                2)][Integer.parseInt(
                                                                toSBoxFour[1] + "" + toSBoxFour[2] + "" + toSBoxFour[3]
                                                                                + "" + toSBoxFour[4],
                                                                2)];
                                int decimalFive = sBoxFive[Integer.parseInt(toSBoxFive[0] + "" + toSBoxFive[5],
                                                2)][Integer.parseInt(
                                                                toSBoxFive[1] + "" + toSBoxFive[2] + "" + toSBoxFive[3]
                                                                                + "" + toSBoxFive[4],
                                                                2)];
                                int decimalSix = sBoxSix[Integer.parseInt(toSBoxSix[0] + "" + toSBoxSix[5], 2)][Integer
                                                .parseInt(toSBoxSix[1] + "" + toSBoxSix[2] + "" + toSBoxSix[3] + ""
                                                                + toSBoxSix[4], 2)];
                                int decimalSeven = sBoxSeven[Integer
                                                .parseInt(toSBoxSeven[0] + "" + toSBoxSeven[5], 2)][Integer
                                                                .parseInt(toSBoxSeven[1] + "" + toSBoxSeven[2] + ""
                                                                                + toSBoxSeven[3] + "" + toSBoxSeven[4],
                                                                                2)];
                                int decimalEight = sBoxEight[Integer
                                                .parseInt(toSBoxEight[0] + "" + toSBoxEight[5], 2)][Integer
                                                                .parseInt(toSBoxEight[1] + "" + toSBoxEight[2] + ""
                                                                                + toSBoxEight[3] + "" + toSBoxEight[4],
                                                                                2)];

                                // converting the 8 decimal into 8 binary values and putting it in one string
                                // formatting it to make it 4 digits and replacing unoccupied digits with zeros
                                // so that 2 will be 0010 not 10
                                String sBoxOutput = String.format("%4s", Integer.toBinaryString(decimalOne))
                                                .replace(" ", "0")
                                                + ""
                                                + String.format("%4s", Integer.toBinaryString(decimalTwo)).replace(" ",
                                                                "0")
                                                + ""
                                                + String.format("%4s", Integer.toBinaryString(decimalThree))
                                                                .replace(" ", "0")
                                                + ""
                                                + String.format("%4s", Integer.toBinaryString(decimalFour)).replace(" ",
                                                                "0")
                                                + ""
                                                + String.format("%4s", Integer.toBinaryString(decimalFive)).replace(" ",
                                                                "0")
                                                + ""
                                                + String.format("%4s", Integer.toBinaryString(decimalSix)).replace(" ",
                                                                "0")
                                                + ""
                                                + String.format("%4s", Integer.toBinaryString(decimalSeven))
                                                                .replace(" ", "0")
                                                + "" + String.format("%4s", Integer.toBinaryString(decimalEight))
                                                                .replace(" ", "0");

                                System.out.println(
                                                "Step 4 (S-Box): Input: (8 6-bit values from the round key addition: \n"
                                                                + arrayToString(toSBoxOne) + "\n"
                                                                + arrayToString(toSBoxTwo) + "\n"
                                                                + arrayToString(toSBoxThree) + "\n"
                                                                + arrayToString(toSBoxFour) + "\n"
                                                                + arrayToString(toSBoxFive) + "\n"
                                                                + arrayToString(toSBoxSix) + "\n"
                                                                + arrayToString(toSBoxSeven) + "\n"
                                                                + arrayToString(toSBoxEight)
                                                                + ")\nOutput: "
                                                                + decimalOne + " " + decimalTwo + " " + decimalThree
                                                                + " " + decimalFour + " "
                                                                + decimalFive + " " + decimalSix + " " + decimalSeven
                                                                + " " + decimalEight
                                                                + "\nIn Binary (32-bit): " + sBoxOutput);

                                char[] sBoxOutputArray = sBoxOutput.toCharArray();// Converting string to array to
                                                                                  // easily manipulate

                                char[] pBoxOutputArray = // Permutating based on the P-Box fixed matrix (32-bit).
                                                {
                                                                sBoxOutputArray[15], sBoxOutputArray[6],
                                                                sBoxOutputArray[19],
                                                                sBoxOutputArray[20], sBoxOutputArray[28],
                                                                sBoxOutputArray[11],
                                                                sBoxOutputArray[27], sBoxOutputArray[16],
                                                                sBoxOutputArray[0],
                                                                sBoxOutputArray[14], sBoxOutputArray[22],
                                                                sBoxOutputArray[25],
                                                                sBoxOutputArray[4], sBoxOutputArray[17],
                                                                sBoxOutputArray[30],
                                                                sBoxOutputArray[9], sBoxOutputArray[1],
                                                                sBoxOutputArray[7],
                                                                sBoxOutputArray[23], sBoxOutputArray[13],
                                                                sBoxOutputArray[31],
                                                                sBoxOutputArray[26], sBoxOutputArray[2],
                                                                sBoxOutputArray[8],
                                                                sBoxOutputArray[18], sBoxOutputArray[12],
                                                                sBoxOutputArray[29],
                                                                sBoxOutputArray[5], sBoxOutputArray[21],
                                                                sBoxOutputArray[10],
                                                                sBoxOutputArray[3], sBoxOutputArray[24] };

                                System.out.println("Step 5 (P-Box): Input: (32-bit S-Box output: " + sBoxOutput
                                                + " ) Output: (32-bit P-Box output) " + arrayToString(pBoxOutputArray));

                                // Output of step 5 XOR it with Left-Half

                                String XORwithLeftHalf = xor(arrayToString(pBoxOutputArray),
                                                arrayToString(messageLeftHalf));

                                System.out.println(
                                                "Step 6 (XOR): Input: (32-bit output from the P-Box: "
                                                                + arrayToString(pBoxOutputArray) + " ), (Left-Half: "
                                                                + arrayToString(messageLeftHalf)
                                                                + " ) Output: (Afer XORing: "
                                                                + XORwithLeftHalf);

                                // Swapping (New Left = Old Right, New Right = XOR output)
                                messageLeftHalf = messageRightHalf;
                                messageRightHalf = XORwithLeftHalf.toCharArray();

                        }

                        // Swapping Again
                        char[] temp = messageLeftHalf;
                        messageLeftHalf = messageRightHalf;
                        messageRightHalf = temp;

                        char[] finaloutput = (arrayToString(messageLeftHalf) + "" + arrayToString(messageRightHalf))
                                        .toCharArray();

                        char[] finalPermutation = // Permutating based on the final Permutation fixed matrix (64-bit).
                                        {
                                                        finaloutput[39], finaloutput[7], finaloutput[47],
                                                        finaloutput[15],
                                                        finaloutput[55], finaloutput[23], finaloutput[63],
                                                        finaloutput[31],
                                                        finaloutput[38], finaloutput[6], finaloutput[46],
                                                        finaloutput[14],
                                                        finaloutput[54], finaloutput[22], finaloutput[62],
                                                        finaloutput[30],
                                                        finaloutput[37], finaloutput[5], finaloutput[45],
                                                        finaloutput[13],
                                                        finaloutput[53], finaloutput[21], finaloutput[61],
                                                        finaloutput[29],
                                                        finaloutput[36], finaloutput[4], finaloutput[44],
                                                        finaloutput[12],
                                                        finaloutput[52], finaloutput[20], finaloutput[60],
                                                        finaloutput[28],
                                                        finaloutput[35], finaloutput[3], finaloutput[43],
                                                        finaloutput[11],
                                                        finaloutput[51], finaloutput[19], finaloutput[59],
                                                        finaloutput[27],
                                                        finaloutput[34], finaloutput[2], finaloutput[42],
                                                        finaloutput[10],
                                                        finaloutput[50], finaloutput[18], finaloutput[58],
                                                        finaloutput[26],
                                                        finaloutput[33], finaloutput[1], finaloutput[41],
                                                        finaloutput[9],
                                                        finaloutput[49], finaloutput[17], finaloutput[57],
                                                        finaloutput[25],
                                                        finaloutput[32], finaloutput[0], finaloutput[40],
                                                        finaloutput[8],
                                                        finaloutput[48], finaloutput[16], finaloutput[56],
                                                        finaloutput[24] };

                        System.out.println("Last Step (Inverse Initial Permutation): Input: (64-bit final output: "
                                        + arrayToString(finaloutput) + " ) Output: (64-bit permutated cipher block) "
                                        + arrayToString(finalPermutation));
                }

        }

        private static String arrayToString(char[] array) {// Method that converts char array to string
                String newString = "";
                for (char bit : array) {// Iterating through the char array
                        newString = newString.concat(bit + "");
                }
                return newString;

        }

        private static String xor(String x, String y) {
                String result = "";
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < x.length(); i++) {
                        sb.append(x.charAt(i) ^ y.charAt(i));
                }
                result = sb.toString();
                return result;
        }

        public static HashMap<String, String> keyExpansion(char[] keyArray) {
                HashMap<String, String> roundKeys;
                char[] permutatedKeyArray = {
                                keyArray[56], keyArray[48], keyArray[40], keyArray[32], keyArray[24], keyArray[16],
                                keyArray[8], keyArray[0], keyArray[57], keyArray[49], keyArray[41], keyArray[33],
                                keyArray[25], keyArray[17], keyArray[9], keyArray[1], keyArray[58], keyArray[50],
                                keyArray[42], keyArray[34], keyArray[26], keyArray[18], keyArray[10], keyArray[2],
                                keyArray[59], keyArray[51], keyArray[43], keyArray[35], keyArray[62], keyArray[54],
                                keyArray[46], keyArray[38], keyArray[30], keyArray[22], keyArray[14], keyArray[6],
                                keyArray[61], keyArray[53], keyArray[45], keyArray[37], keyArray[29], keyArray[21],
                                keyArray[13], keyArray[5], keyArray[60], keyArray[52], keyArray[44], keyArray[36],
                                keyArray[28], keyArray[20], keyArray[12], keyArray[4], keyArray[27], keyArray[19],
                                keyArray[11], keyArray[3] };
                roundKeys = splitAndJoin(permutatedKeyArray);// Method that will split, rotate, join, permutate and
                // store the round keys in a hashmap. This will yield the
                // final form of all round keys
                return roundKeys;

        }

        public static HashMap<String, String> splitAndJoin(char[] keyArray) {
                HashMap<String, String> roundKeys = new HashMap<>();
                char[] leftHalf = Arrays.copyOfRange(keyArray, 0, keyArray.length / 2);// The left half also known as C0
                char[] rightHalf = Arrays.copyOfRange(keyArray, keyArray.length / 2, keyArray.length);// The right half
                                                                                                      // also known as
                                                                                                      // D0
                for (int i = 0; i < 16; i++) {
                        if (i == 0 || i == 1 || i == 8 || i == 15) {// In the 1st 2nd 9th and 16th round rotate by one
                                                                    // position
                                leftHalf = rotate(leftHalf, 1);
                                rightHalf = rotate(rightHalf, 1);
                        } else {// Else rotate by two positions
                                leftHalf = rotate(leftHalf, 2);
                                rightHalf = rotate(rightHalf, 2);
                        }
                        char[] joined = (arrayToString(leftHalf) + "" + arrayToString(rightHalf)).toCharArray();
                        joined = permutate(joined);
                        roundKeys.put("key" + i, arrayToString(joined));

                }
                return roundKeys;

        }

        // Method that will left rotate by int rotation
        public static char[] rotate(char[] halfKey, int rotation) {
                for (int i = 0; i < rotation; i++) {
                        char firstBit = halfKey[0];// first bit which we will set to the last character after shifting
                        for (int j = 0; j < halfKey.length; j++) {
                                if (j == halfKey.length - 1)
                                        continue;// halfkey[28] wil raise an error
                                halfKey[j] = halfKey[j + 1];// left shifting each bit by equating it to the bit after
                        }
                        halfKey[halfKey.length - 1] = firstBit;// Equating last bit to the first one before shifting in
                        // the loop with index j
                }
                return halfKey;

        }

        public static char[] permutate(char[] keyArray) {
                char[] permutatedKeyArray = {
                                keyArray[13], keyArray[16], keyArray[10], keyArray[23], keyArray[0], keyArray[4],
                                keyArray[2], keyArray[27], keyArray[14], keyArray[5], keyArray[20], keyArray[9],
                                keyArray[22], keyArray[18], keyArray[11], keyArray[3], keyArray[25], keyArray[7],
                                keyArray[15], keyArray[6], keyArray[26], keyArray[19], keyArray[12], keyArray[1],
                                keyArray[40], keyArray[51], keyArray[30], keyArray[36], keyArray[46], keyArray[54],
                                keyArray[29], keyArray[39], keyArray[50], keyArray[44], keyArray[32], keyArray[47],
                                keyArray[43], keyArray[48], keyArray[38], keyArray[55], keyArray[33], keyArray[52],
                                keyArray[45], keyArray[41], keyArray[49], keyArray[35], keyArray[28], keyArray[31] };
                return permutatedKeyArray;

        }

}
