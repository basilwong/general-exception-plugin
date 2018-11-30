package com.google.errorprone.testdata;


public class GeneralExceptionCheckPositiveCases {

   // Positive Version of ArithmeticException, example of primary catch.
   public void pos1() {
     int num1, num2;
     try {
       num1 = 0;
       num2 = 62 / num1;
       System.out.println(num2);
       System.out.println("Hey I'm at the end of try block");
      // BUG: Diagnostic contains: Catch general exception not specific enough.
     } catch (Exception e) {
       System.out.println("You should not divide a number by zero");
     }
     System.out.println("I'm out of try-catch block in Java.");
    }

   // Positive vesion of NullPointerException, example of secondary catch.
   public void pos2() {
     try {
       Object obj = null;
       obj.hashCode();
       System.out.println("Hey I'm at the end of try block");
     } catch (NullPointerException e) {
       System.out.println("Don't try to get the hashcode of a null object.");
      // BUG: Diagnostic contains: Catch general exception not specific enough.
     } catch (Exception e) {
         System.out.println("Exception occurred");
      }
      System.out.println("I'm out of try-catch block in Java.");
   }

   // Positive Version of Test of Nested Catch Try with Exception being nested.
   public void pos3() {
     int num1, num2;
     try {
       Object obj = null;
       obj.hashCode();
       System.out.println("Hey I'm at the end of try block");
      // BUG: Diagnostic contains: Catch general exception not specific enough.
     } catch (Exception e1) {
       System.out.println("Don't try to get the hashcode of a null object.");
       try {
         num1 = 0;
         num2 = 62 / num1;
         System.out.println(num2);
         System.out.println("Hey I'm at the end of try block");
      // BUG: Diagnostic contains: Catch general exception not specific enough.
       } catch (Exception e2) {
         System.out.println("You should not divide a number by zero");
       }
     }
     System.out.println("I'm out of try-catch block in Java.");
   }

}
