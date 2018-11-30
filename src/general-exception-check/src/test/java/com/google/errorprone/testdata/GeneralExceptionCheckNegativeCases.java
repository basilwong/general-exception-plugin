package com.google.errorprone.testdata;


public class GeneralExceptionCheckNegativeCases {

  // Negative Version of ArithmeticException.
  public void neg1() {
    int num1, num2;
    try {
      num1 = 0;
      num2 = 62 / num1;
      System.out.println(num2);
      System.out.println("Hey I'm at the end of try block");
    } catch (ArithmeticException e) {
      System.out.println("You should not divide a number by zero");
    }
    System.out.println("I'm out of try-catch block in Java.");
   }

  // Negative vesion of NullPointerException.
  public void neg2() {
    try {
      Object obj = null;
      obj.hashCode();
      System.out.println("Hey I'm at the end of try block");
    } catch (NullPointerException e) {
      System.out.println("Don't try to get the hashcode of a null object.");
    }
    System.out.println("I'm out of try-catch block in Java.");
  }

  // Negative Version of Test of Nested Catch Try.
  public void neg3() {
    int num1, num2;
    try {
      Object obj = null;
      obj.hashCode();
      System.out.println("Hey I'm at the end of try block");
    } catch (NullPointerException e1) {
      System.out.println("Don't try to get the hashcode of a null object.");
      try {
        num1 = 0;
        num2 = 62 / num1;
        System.out.println(num2);
        System.out.println("Hey I'm at the end of try block");
      } catch (ArithmeticException e2) {
        System.out.println("You should not divide a number by zero");
      }
    }
    System.out.println("I'm out of try-catch block in Java.");
  }

}
