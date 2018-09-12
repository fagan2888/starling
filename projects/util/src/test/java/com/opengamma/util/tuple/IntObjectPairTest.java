/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.util.tuple;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;

import org.testng.annotations.Test;

import com.opengamma.util.test.TestGroup;

/**
 * Test {@link IntObjectPair}.
 */
@Test(groups = TestGroup.UNIT)
public class IntObjectPairTest {
  private static final double EPS = 1e-15;

  /**
   * Tests construction with an int and String.
   */
  @Test
  public void testIntObjectPairOf() {
    final IntObjectPair<String> test = IntObjectPair.of(2, "A");
    assertEquals(Integer.valueOf(2), test.getFirst());
    assertEquals("A", test.getSecond());
    assertEquals(2, test.getFirstInt());
    assertEquals(Integer.valueOf(2), test.getKey());
    assertEquals(2, test.getIntKey());
    assertEquals("[2, A]", test.toString());
  }

  //-------------------------------------------------------------------------
  /**
   * Checks that the set method cannot be used.
   */
  @Test(expectedExceptions = UnsupportedOperationException.class)
  public void testSetValue() {
    final IntObjectPair<String> pair = IntObjectPair.of(2, "B");
    pair.setValue("W");
  }

  /**
   * Checks that the set method cannot be used.
   */
  @Test(expectedExceptions = UnsupportedOperationException.class)
  public void testSetValueNull() {
    final IntObjectPair<String> pair = IntObjectPair.of(2, "P");
    pair.setValue(null);
  }

  //-------------------------------------------------------------------------
  /**
   * Tests the compareTo() method.
   */
  @Test
  public void testCompareToIntObjectPair() {
    final IntObjectPair<String> p12 = IntObjectPair.of(1, "2");
    final IntObjectPair<String> p13 = IntObjectPair.of(1, "3");
    final IntObjectPair<String> p21 = IntObjectPair.of(2, "1");

    assertTrue(p12.compareTo(p12) == 0);
    assertTrue(p12.compareTo(p13) < 0);
    assertTrue(p12.compareTo(p21) < 0);

    assertTrue(p13.compareTo(p12) > 0);
    assertTrue(p13.compareTo(p13) == 0);
    assertTrue(p13.compareTo(p21) < 0);

    assertTrue(p21.compareTo(p12) > 0);
    assertTrue(p21.compareTo(p13) > 0);
    assertTrue(p21.compareTo(p21) == 0);
  }

  /**
   * Tests the compareTo() method.
   */
  @Test
  public void testCompareToIntObjectPairAsPair() {
    final Pair<Integer, String> p12 = IntObjectPair.of(1, "2");
    final Pair<Integer, String> p13 = IntObjectPair.of(1, "3");
    final Pair<Integer, String> p21 = IntObjectPair.of(2, "1");

    assertTrue(p12.compareTo(p12) == 0);
    assertTrue(p12.compareTo(p13) < 0);
    assertTrue(p12.compareTo(p21) < 0);

    assertTrue(p13.compareTo(p12) > 0);
    assertTrue(p13.compareTo(p13) == 0);
    assertTrue(p13.compareTo(p21) < 0);

    assertTrue(p21.compareTo(p12) > 0);
    assertTrue(p21.compareTo(p13) > 0);
    assertTrue(p21.compareTo(p21) == 0);
  }

  /**
   * Tests the comparator.
   */
  @Test
  public void testCompareToComparatorFirstThenSecond() {
    final IntObjectPair<String> ab = IntObjectPair.of(1, "J");
    final IntObjectPair<String> ac = IntObjectPair.of(1, "K");
    final IntObjectPair<String> ba = IntObjectPair.of(2, "L");

    final FirstThenSecondPairComparator<Integer, String> comparator = new FirstThenSecondPairComparator<>();
    assertTrue(comparator.compare(ab, ab) == 0);
    assertTrue(comparator.compare(ac, ab) > 0);
    assertTrue(comparator.compare(ba, ab) > 0);

    assertTrue(comparator.compare(ab, ac) < 0);
    assertTrue(comparator.compare(ac, ac) == 0);
    assertTrue(comparator.compare(ba, ac) > 0);

    assertTrue(comparator.compare(ab, ba) < 0);
    assertTrue(comparator.compare(ac, ba) < 0);
    assertTrue(comparator.compare(ba, ba) == 0);
  }

  //-------------------------------------------------------------------------
  /**
   * Tests the equals() method.
   */
  @Test
  public void testEquals() {
    final IntObjectPair<String> a = IntObjectPair.of(1, "2.0");
    final IntObjectPair<String> b = IntObjectPair.of(1, "3.0");
    final IntObjectPair<String> c = IntObjectPair.of(2, "2.0");
    final IntObjectPair<String> d = IntObjectPair.of(2, "3.0");
    final IntObjectPair<String> e = IntObjectPair.of(1, "2.0");

    assertEquals(true, a.equals(a));
    assertEquals(false, a.equals(b));
    assertEquals(false, a.equals(c));
    assertEquals(false, a.equals(d));
    assertEquals(true, a.equals(e));

    assertEquals(false, b.equals(a));
    assertEquals(true, b.equals(b));
    assertEquals(false, b.equals(c));
    assertEquals(false, b.equals(d));
    assertEquals(false, b.equals(e));

    assertEquals(false, c.equals(a));
    assertEquals(false, c.equals(b));
    assertEquals(true, c.equals(c));
    assertEquals(false, c.equals(d));
    assertEquals(false, c.equals(e));

    assertEquals(false, d.equals(a));
    assertEquals(false, d.equals(b));
    assertEquals(false, d.equals(c));
    assertEquals(true, d.equals(d));
    assertEquals(false, d.equals(e));
  }

  /**
   * Tests the equals() method.
   */
  @Test
  public void testEqualsToObjectVersion() {
    final IntObjectPair<String> a = IntObjectPair.of(1, "R");
    final Pair<Integer, String> b = ObjectsPair.of(Integer.valueOf(1), "R");
    assertEquals(true, a.equals(b));
    assertEquals(true, b.equals(a));
  }

  /**
   * Tests the equals() method.
   */
  @Test
  public void testEqualsToObjectVersionNull() {
    final IntObjectPair<String> b = IntObjectPair.of(1, "F");
    final Pair<Integer, Double> a = ObjectsPair.of(null, Double.valueOf(1.9d));
    assertEquals(true, a.equals(a));
    assertEquals(false, a.equals(b));
    assertEquals(false, b.equals(a));
    assertEquals(true, b.equals(b));
  }

  /**
   * Tests the hashCode() method.
   */
  @Test
  public void testHashCode() {
    final IntObjectPair<String> a = IntObjectPair.of(1, "N");
    final Pair<Integer, String> b = ObjectsPair.of(Integer.valueOf(1), "N");
    assertEquals(a.hashCode(), b.hashCode());
  }

  /**
   * Tests the hashCode() method.
   */
  @Test
  public void testHashCodeValue() {
    final IntObjectPair<String> a = IntObjectPair.of(1, "$");
    assertEquals(a.hashCode(), a.hashCode());
    assertEquals(Integer.valueOf(1) ^ "$".hashCode(), a.hashCode());
    // can't test for different hash codes as they might not be different
  }

  /**
   * Tests the bean.
   */
  @Test
  public void testBean() {
    final IntObjectPair<String> pair = IntObjectPair.of(2, "U");
    assertNotNull(pair.metaBean());
    assertNotNull(pair.metaBean().first());
    assertNotNull(pair.metaBean().second());
    assertEquals(pair.metaBean().first().get(pair), 2, EPS);
    assertEquals(pair.metaBean().second().get(pair), "U");
    assertEquals(pair.property("first").get(), 2);
    assertEquals(pair.property("second").get(), "U");
  }

}
