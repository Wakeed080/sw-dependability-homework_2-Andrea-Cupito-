package org.magee.math;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.evosuite.runtime.EvoAssertions.*;
import org.junit.runner.RunWith;
import org.magee.math.Rational;

public class RationalTest {

  //test the add() method
  //the test is good because add() tries to create a new Rational object with 0 as denominator
  @Test
  public void test01()  throws Throwable  {
      Rational rational0 = new Rational(762L, 762L);
      // Undeclared exception!
      try { 
        rational0.add(762L);
        fail("Expecting exception: NumberFormatException");
      
      } catch(NumberFormatException e) {
         //
         // Cannot create a Rational object with zero as the denominator
         //
         verifyException("org.magee.math.Rational", e);
      }
  }

  //test longValue() method
  //it computes [(-1) / (-661)] = 0.001512
  @Test
  public void test02()  throws Throwable  {
      Rational rational0 = new Rational((-1L), (-661L));
      long long0 = rational0.longValue();
      assertEquals(0L, long0);
      assertEquals(0.0015128593040847202, rational0.doubleValue(), 0.01);
  }

  //test multiply() method
  //there is a bug with multiply()
  //it doesn't computes the multiplication between numerators but the division
  //in fact it should have computed {[(-1431) * (-1431)] / [(-1431) * (-1431)]} = 1
  @Test
  public void test03()  throws Throwable  {
      Rational rational0 = new Rational((-1431L), (-1431L));
      Rational rational1 = rational0.multiply(rational0);
      assertEquals(1L, rational0.longValue());
      assertEquals(1, rational1.floatValue(), 0);
  }

  //As i discovered in test03, multiply() method is buggy
  //so this test isn't useful at the moment
  @Test
  public void test04()  throws Throwable  {
      Rational rational0 = new Rational(1L, 1L);
      Rational rational1 = rational0.multiply(rational0);
      assertEquals(1, rational0.intValue());
      assertEquals((byte)1, rational1.byteValue());
  }

  //Test pow() method
  //It computes [(6^5) / ((-2)^5)] = 7776/ (-32)
  @Test
  public void test05()  throws Throwable  {
      Rational rational0 = new Rational(6L, (-2L));
      Rational rational1 = rational0.pow(5);
      assertEquals(7776L, rational1.numerator);
      assertEquals((byte)13, rational1.byteValue());
  }

  //it test subtract(long integer)
  //so it computes [(1 * (-1)) + ((-1) * (-1))] / ((-1) * (-1)) = 0/1
  @Test
  public void test06()  throws Throwable  {
      Rational rational0 = new Rational((-1L), (-1L));
      Rational rational1 = rational0.subtract((-1L));
      assertEquals(0.0, rational1.doubleValue(), 0.01);
      assertEquals(1L, rational1.denominator);
      assertEquals((-1L), rational0.denominator);
  }

  //test abs() method
  //abs() is buggy, it returns the correct value for numerator but not for the denominators
  //it returns (+ denominator) if (denominator < 0) but it's not correct it should return (-denominator)
  @Test
  public void test07()  throws Throwable  {
      Rational rational0 = new Rational(0L, (-1802L));
      Rational rational1 = rational0.abs();
      assertEquals(0.0, rational1.doubleValue(), 0.01);
      assertEquals((1802L), rational1.denominator);
  }

  //test negate() method
  //it computes (-887) / (887)
  @Test
  public void test08()  throws Throwable  {
      Rational rational0 = new Rational(887L, 887L);
      Rational rational1 = rational0.negate();
      assertEquals(-887, rational1.numerator);
      assertEquals((byte) (-1), rational1.byteValue());
      assertEquals(1, rational0.intValue());
  }

  //test doubleValue() method
  //it computes 0 / 1090 = 0
  @Test
  public void test09()  throws Throwable  {
      Rational rational0 = new Rational(0L, 1090L);
      double double0 = rational0.doubleValue();
      assertEquals(1090L, rational0.denominator);
      assertEquals(0.0, double0, 0.01);
  }

  //As i discovered in test07(), abs() method is buggy
  //this test doesn't fail because abd() return the correct value for positive rational
  //so the test is correct but the abs() method is still buggy
  @Test
  public void test10()  throws Throwable  {
      Rational rational0 = new Rational(1184L, 73L);
      Rational rational1 = rational0.abs();
      assertEquals(1184L, rational0.numerator);
      assertEquals(16.21917808219178, rational1.doubleValue(), 0.01);
  }

  //Simple test to execute inverse() method which is not tested in this test suite
  @Test
  public void testInverse() throws Throwable{
      Rational rational0 = new Rational(5, 3);
      Rational rational1 = rational0.inverse();
      assertEquals(3, rational1.numerator);
      assertEquals(5, rational1.denominator);
  }
}
