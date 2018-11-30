package com.google.errorprone.sample;

// Positive Case 1
public class SampleProgram {
   public static void main(String[] args) {

      int num1 = 10;
      int num2 = 0;

       try {
         int num3 = num1 / num2;
       } catch (NullPointerException A) {
           System.out.println("no$$");
           try {
              int num3 = num1 / num2;
           } catch (Exception B) {
             System.out.println("Did not work after trying again.");
           }

       } catch (Exception C) {
           System.out.println("Variable is not correct."); // Obviously not clear enough.
           try {
              int num3 = num1 / num2;
           } catch (Exception D) {
             System.out.println("Did not work after trying again.");
           }

       }

       try {
          int num3 = num2 + 1;

       } catch (Exception E) {
           System.out.println("Variable is not correct."); // Obviously not clear enough.
       }

   }

}
