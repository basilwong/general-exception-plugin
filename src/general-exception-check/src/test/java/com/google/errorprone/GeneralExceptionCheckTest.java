package com.google.errorprone;

import com.google.errorprone.CompilationTestHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class GeneralExceptionCheckTest {

  private CompilationTestHelper compilationHelper;

  @Before
  public void setup() {
    compilationHelper = CompilationTestHelper.newInstance(GeneralExceptionCheck.class, getClass());
  }

  @Test
  public void GeneralExceptionCheckPositiveCases() {
    compilationHelper.addSourceFile("GeneralExceptionCheckPositiveCases.java").doTest();
  }

  @Test
  public void GeneralExceptionCheckNegativeCases() {
    compilationHelper.addSourceFile("GeneralExceptionCheckNegativeCases.java").doTest();
  }
}
