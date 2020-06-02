package org.apache.commons.cli;


import org.junit.Test;
import static org.evosuite.runtime.EvoAssertions.*;
import static org.junit.Assert.*;

import org.apache.commons.cli.Option;
import org.junit.runner.RunWith;

public class OptionTest {

  //test if setArgName() method works properly
  @Test
  public void test01()  throws Throwable  {
      Option option0 = new Option("", "");
      assertEquals("arg", option0.getArgName());
      
      option0.setArgName("");

      //method hasArgName return true if arg length is >= 0 and arg != null
      //so this is always true except for null that is correctly checked by test
      //so i want to check the case in which the length of the string is 0 but not null
      boolean boolean0 = option0.hasArgName();

      //i expect that for a "" String, the method hasArgName() returns false
      assertFalse(boolean0);
  }

  //test getKey() method
  @Test
  public void test02()  throws Throwable  {
      Option option0 = new Option("", "", true, "");

      //they check only the null value, but not if the string length is 0 but not null
      //option0.getKey(), old statement
      assertTrue("opt is not specified", option0.getKey().length() > 0);

      assertEquals("arg", option0.getArgName());
      assertEquals(1, option0.getArgs());
  }

  //test equals() method
  @Test
  public void test03()  throws Throwable  {
      Option option0 = new Option("R", "R", true, "R");
      Option option1 = new Option("R", "R", true, "R");

      //the equals seems to test only the equality of opt attribute
      boolean boolean0 = option1.equals(option0);

      //the equal method returns true even if the two longOpt are not the same
      //in this way i check if they are equals or not
      if(!option1.getLongOpt().equals(option0.getLongOpt())){
          boolean0 = false;
      }

      assertEquals("arg", option1.getArgName());
      assertTrue(option1.hasArg());
      assertTrue(boolean0);
  }

  //Test of getId() method
  //seems to be buggy because it returns the int value of the second opt character instead of the first
  //you can see that getId() calls charAt(1)
  @Test
  public void test04()  throws Throwable  {
      Option option0 = new Option("VC", "VC");
      int int0 = option0.getId();
      assertFalse(option0.hasLongOpt());
      assertFalse(option0.hasArgs());
      //assertEquals(67, int0); old statement
      assertEquals("The first letter is V", 'V', int0);
      assertEquals("arg", option0.getArgName());
  }

  //test hasArgs() method
  //seems correct because in the Option constructor if hasArg is true, it put numberOfArgs var = 1
  //in this way the method hasArgs returns true
  @Test
  public void test05()  throws Throwable  {
      Option option0 = new Option("P", "P", true, "P");
      boolean boolean0 = option0.hasArgs();
      assertEquals("arg", option0.getArgName());
      assertTrue(boolean0);
      assertEquals(1, option0.getArgs());
  }

  //test getValue() method
  //i test if getValue() method throws correctly IndexOutOfBoundException
  @Test (expected = IndexOutOfBoundsException.class)
  public void test06()  throws Throwable  {
      Option option0 = new Option("P", true, "P");
      option0.addValueForProcessing("P");

      //i expect an IndexOutOfBoundsException
      //because values contains just 1 element and i'm checking for the 295th
      option0.getValue(295);
  }

  //test equals() method
  //with two different Option created with two different constructors
  //equals method just value the opt String, so if opt is equals the method return true
  @Test
  public void test07()  throws Throwable  {
      Option option0 = new Option("", "", true, "");
      Option option1 = new Option("", "");
      boolean boolean0 = option0.equals(option1);
      assertEquals((-1), option1.getArgs());
      assertFalse(option1.hasLongOpt());
      assertEquals("arg", option1.getArgName());
      assertEquals(1, option0.getArgs());
      assertTrue(boolean0);
  }

  //test getValue() method
  //the method return null if the Option doesn't add something to List values
  @Test
  public void test08()  throws Throwable  {
      Option option0 = new Option("", "", true, "");

      //I want to test if getValue() is actually correct
      assertNull(option0.getValue());

      assertEquals("arg", option0.getArgName());
      assertTrue(option0.hasArg());
  }

  //another test for getValue()
  //i want to test if it returns the correct value when values List contains something
  @Test
  public void testGetValueWithParameters() throws Throwable{
      Option option0 = new Option("", "", true, "");

      option0.addValueForProcessing("P");
      assertEquals("P", option0.getValue());

      assertEquals("arg", option0.getArgName());
      assertTrue(option0.hasArg());
  }

  //test of setArgName() method
  @Test
  public void test09()  throws Throwable  {
      Option option0 = new Option((String) null, (String) null, true, (String) null);
      assertEquals("arg", option0.getArgName());
      
      option0.setArgName((String) null);
      //I prefer to assess the nullability of the arg that has been set previously
      assertNull(option0.getArgName());

      //if hasArg = true, numberOfArgs = 1 (REMINDER)
      assertEquals(1, option0.getArgs());
  }

  //test equals() method
  //as i said previously equals method just check the opt values
  @Test
  public void test10()  throws Throwable  {
      Option option0 = new Option("SX", "SX");
      Option option1 = new Option("SX", "SX", false, "SX");
      boolean boolean0 = option0.equals(option1);
      assertTrue(boolean0);
      assertEquals((-1), option1.getArgs());
      assertFalse(option0.hasLongOpt());
      assertEquals("arg", option1.getArgName());
  }
}
