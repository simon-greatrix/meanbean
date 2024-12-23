/*-
 * ​​​
 * meanbean
 * ⁣⁣⁣
 * Copyright (C) 2010 - 2020 the original author or authors.
 * ⁣⁣⁣
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ﻿﻿﻿﻿﻿
 */

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.meanbean.test.internal;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit tests {@link org.apache.commons.lang3.builder.EqualsBuilder}.
 */
public class EqualsBuilderTest {

  //-----------------------------------------------------------------------



  public static class TestACanEqualB {

    private final int a;


    public TestACanEqualB(final int a) {
      this.a = a;
    }


    @Override
    public boolean equals(final Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof TestACanEqualB) {
        return this.a == ((TestACanEqualB) o).getA();
      }
      if (o instanceof TestBCanEqualA) {
        return this.a == ((TestBCanEqualA) o).getB();
      }
      return false;
    }


    public int getA() {
      return this.a;
    }


    @Override
    public int hashCode() {
      return a;
    }

  }



  public static class TestBCanEqualA {

    private final int b;


    public TestBCanEqualA(final int b) {
      this.b = b;
    }


    @Override
    public boolean equals(final Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof TestACanEqualB) {
        return this.b == ((TestACanEqualB) o).getA();
      }
      if (o instanceof TestBCanEqualA) {
        return this.b == ((TestBCanEqualA) o).getB();
      }
      return false;
    }


    public int getB() {
      return this.b;
    }


    @Override
    public int hashCode() {
      return b;
    }

  }



  static class TestEmptySubObject extends TestObject {

    TestEmptySubObject(final int a) {
      super(a);
    }

  }



  static class TestObject {

    private int a;


    TestObject() {
    }


    TestObject(final int a) {
      this.a = a;
    }


    @Override
    public boolean equals(final Object o) {
      if (o == null) {
        return false;
      }
      if (o == this) {
        return true;
      }
      if (o.getClass() != getClass()) {
        return false;
      }

      final TestObject rhs = (TestObject) o;
      return a == rhs.a;
    }


    public int getA() {
      return a;
    }


    @Override
    public int hashCode() {
      return a;
    }


    public void setA(final int a) {
      this.a = a;
    }

  }



  static class TestRecursiveCycleObject {

    private final int n;

    private TestRecursiveCycleObject cycle;


    TestRecursiveCycleObject(final int n) {
      this.n = n;
      this.cycle = this;
    }


    TestRecursiveCycleObject(final TestRecursiveCycleObject cycle, final int n) {
      this.n = n;
      this.cycle = cycle;
    }


    public TestRecursiveCycleObject getCycle() {
      return cycle;
    }


    public int getN() {
      return n;
    }


    public void setCycle(final TestRecursiveCycleObject cycle) {
      this.cycle = cycle;
    }

  }



  static class TestRecursiveGenericObject<T> {

    private final T a;


    TestRecursiveGenericObject(final T a) {
      this.a = a;
    }


    public T getA() {
      return a;
    }

  }



  static class TestRecursiveInnerObject {

    private final int n;


    TestRecursiveInnerObject(final int n) {
      this.n = n;
    }


    public int getN() {
      return n;
    }

  }



  static class TestRecursiveObject {

    private final TestRecursiveInnerObject a;

    private final TestRecursiveInnerObject b;

    private int z;


    TestRecursiveObject(
        final TestRecursiveInnerObject a,
        final TestRecursiveInnerObject b, final int z
    ) {
      this.a = a;
      this.b = b;
    }


    public TestRecursiveInnerObject getA() {
      return a;
    }


    public TestRecursiveInnerObject getB() {
      return b;
    }


    public int getZ() {
      return z;
    }

  }



  static class TestSubObject extends TestObject {

    private int b;


    TestSubObject() {
      super(0);
    }


    TestSubObject(final int a, final int b) {
      super(a);
      this.b = b;
    }


    @Override
    public boolean equals(final Object o) {
      if (o == null) {
        return false;
      }
      if (o == this) {
        return true;
      }
      if (o.getClass() != getClass()) {
        return false;
      }

      final TestSubObject rhs = (TestSubObject) o;
      return super.equals(o) && b == rhs.b;
    }


    public int getB() {
      return b;
    }


    @Override
    public int hashCode() {
      return b * 17 + super.hashCode();
    }


    public void setB(final int b) {
      this.b = b;
    }

  }



  static class TestTSubObject extends TestObject {

    @SuppressWarnings("unused")
    private final transient int t;


    TestTSubObject(final int a, final int t) {
      super(a);
      this.t = t;
    }

  }



  static class TestTSubObject2 extends TestObject {

    private transient int t;


    TestTSubObject2(final int a, final int t) {
      super(a);
    }


    public int getT() {
      return t;
    }


    public void setT(final int t) {
      this.t = t;
    }

  }



  static class TestTTLeafObject extends TestTTSubObject {

    @SuppressWarnings("unused")
    private final int leafValue;


    TestTTLeafObject(final int a, final int t, final int tt, final int leafValue) {
      super(a, t, tt);
      this.leafValue = leafValue;
    }

  }



  static class TestTTSubObject extends TestTSubObject {

    @SuppressWarnings("unused")
    private final transient int tt;


    TestTTSubObject(final int a, final int t, final int tt) {
      super(a, t);
      this.tt = tt;
    }

  }


  @Test
  public void testBoolean() {
    final boolean o1 = true;
    final boolean o2 = false;
    assertTrue(new $EqualsBuilder().append(o1, o1).isEquals());
    assertFalse(new $EqualsBuilder().append(o1, o2).isEquals());
  }


  @Test
  public void testBooleanArray() {
    boolean[] obj1 = new boolean[2];
    obj1[0] = true;
    obj1[1] = false;
    boolean[] obj2 = new boolean[2];
    obj2[0] = true;
    obj2[1] = false;
    assertTrue(new $EqualsBuilder().append(obj1, obj1).isEquals());
    assertTrue(new $EqualsBuilder().append(obj1, obj2).isEquals());
    obj1[1] = true;
    assertFalse(new $EqualsBuilder().append(obj1, obj2).isEquals());

    obj2 = null;
    assertFalse(new $EqualsBuilder().append(obj1, obj2).isEquals());
    obj1 = null;
    assertTrue(new $EqualsBuilder().append(obj1, obj2).isEquals());
  }


  @Test
  public void testBooleanArrayHiddenByObject() {
    final boolean[] array1 = new boolean[2];
    array1[0] = true;
    array1[1] = false;
    final boolean[] array2 = new boolean[2];
    array2[0] = true;
    array2[1] = false;
    final Object obj1 = array1;
    final Object obj2 = array2;
    assertTrue(new $EqualsBuilder().append(obj1, obj1).isEquals());
    assertTrue(new $EqualsBuilder().append(obj1, array1).isEquals());
    assertTrue(new $EqualsBuilder().append(obj1, obj2).isEquals());
    assertTrue(new $EqualsBuilder().append(obj1, array2).isEquals());
    array1[1] = true;
    assertFalse(new $EqualsBuilder().append(obj1, obj2).isEquals());
  }


  @Test
  public void testByte() {
    final byte o1 = 1;
    final byte o2 = 2;
    assertTrue(new $EqualsBuilder().append(o1, o1).isEquals());
    assertFalse(new $EqualsBuilder().append(o1, o2).isEquals());
  }


  @Test
  public void testByteArray() {
    byte[] obj1 = new byte[2];
    obj1[0] = 5;
    obj1[1] = 6;
    byte[] obj2 = new byte[2];
    obj2[0] = 5;
    obj2[1] = 6;
    assertTrue(new $EqualsBuilder().append(obj1, obj1).isEquals());
    assertTrue(new $EqualsBuilder().append(obj1, obj2).isEquals());
    obj1[1] = 7;
    assertFalse(new $EqualsBuilder().append(obj1, obj2).isEquals());

    obj2 = null;
    assertFalse(new $EqualsBuilder().append(obj1, obj2).isEquals());
    obj1 = null;
    assertTrue(new $EqualsBuilder().append(obj1, obj2).isEquals());
  }


  @Test
  public void testByteArrayHiddenByObject() {
    final byte[] array1 = new byte[2];
    array1[0] = 5;
    array1[1] = 6;
    final byte[] array2 = new byte[2];
    array2[0] = 5;
    array2[1] = 6;
    final Object obj1 = array1;
    final Object obj2 = array2;
    assertTrue(new $EqualsBuilder().append(obj1, obj1).isEquals());
    assertTrue(new $EqualsBuilder().append(obj1, array1).isEquals());
    assertTrue(new $EqualsBuilder().append(obj1, obj2).isEquals());
    assertTrue(new $EqualsBuilder().append(obj1, array2).isEquals());
    array1[1] = 7;
    assertFalse(new $EqualsBuilder().append(obj1, obj2).isEquals());
  }


  @Test
  public void testChar() {
    final char o1 = 1;
    final char o2 = 2;
    assertTrue(new $EqualsBuilder().append(o1, o1).isEquals());
    assertFalse(new $EqualsBuilder().append(o1, o2).isEquals());
  }


  @Test
  public void testCharArray() {
    char[] obj1 = new char[2];
    obj1[0] = 5;
    obj1[1] = 6;
    char[] obj2 = new char[2];
    obj2[0] = 5;
    obj2[1] = 6;
    assertTrue(new $EqualsBuilder().append(obj1, obj1).isEquals());
    assertTrue(new $EqualsBuilder().append(obj1, obj2).isEquals());
    obj1[1] = 7;
    assertFalse(new $EqualsBuilder().append(obj1, obj2).isEquals());

    obj2 = null;
    assertFalse(new $EqualsBuilder().append(obj1, obj2).isEquals());
    obj1 = null;
    assertTrue(new $EqualsBuilder().append(obj1, obj2).isEquals());
  }


  @Test
  public void testCharArrayHiddenByObject() {
    final char[] array1 = new char[2];
    array1[0] = 5;
    array1[1] = 6;
    final char[] array2 = new char[2];
    array2[0] = 5;
    array2[1] = 6;
    final Object obj1 = array1;
    final Object obj2 = array2;
    assertTrue(new $EqualsBuilder().append(obj1, obj1).isEquals());
    assertTrue(new $EqualsBuilder().append(obj1, array1).isEquals());
    assertTrue(new $EqualsBuilder().append(obj1, obj2).isEquals());
    assertTrue(new $EqualsBuilder().append(obj1, array2).isEquals());
    array1[1] = 7;
    assertFalse(new $EqualsBuilder().append(obj1, obj2).isEquals());
  }


  @Test
  public void testDouble() {
    final double o1 = 1;
    final double o2 = 2;
    assertTrue(new $EqualsBuilder().append(o1, o1).isEquals());
    assertFalse(new $EqualsBuilder().append(o1, o2).isEquals());
    assertFalse(new $EqualsBuilder().append(o1, Double.NaN).isEquals());
    assertTrue(new $EqualsBuilder().append(Double.NaN, Double.NaN).isEquals());
    assertTrue(new $EqualsBuilder().append(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY).isEquals());
  }


  @Test
  public void testDoubleArray() {
    double[] obj1 = new double[2];
    obj1[0] = 5;
    obj1[1] = 6;
    double[] obj2 = new double[2];
    obj2[0] = 5;
    obj2[1] = 6;
    assertTrue(new $EqualsBuilder().append(obj1, obj1).isEquals());
    assertTrue(new $EqualsBuilder().append(obj1, obj2).isEquals());
    obj1[1] = 7;
    assertFalse(new $EqualsBuilder().append(obj1, obj2).isEquals());

    obj2 = null;
    assertFalse(new $EqualsBuilder().append(obj1, obj2).isEquals());
    obj1 = null;
    assertTrue(new $EqualsBuilder().append(obj1, obj2).isEquals());
  }


  @Test
  public void testDoubleArrayHiddenByObject() {
    final double[] array1 = new double[2];
    array1[0] = 5;
    array1[1] = 6;
    final double[] array2 = new double[2];
    array2[0] = 5;
    array2[1] = 6;
    final Object obj1 = array1;
    final Object obj2 = array2;
    assertTrue(new $EqualsBuilder().append(obj1, obj1).isEquals());
    assertTrue(new $EqualsBuilder().append(obj1, array1).isEquals());
    assertTrue(new $EqualsBuilder().append(obj1, obj2).isEquals());
    assertTrue(new $EqualsBuilder().append(obj1, array2).isEquals());
    array1[1] = 7;
    assertFalse(new $EqualsBuilder().append(obj1, obj2).isEquals());
  }


  @Test
  public void testFloat() {
    final float o1 = 1;
    final float o2 = 2;
    assertTrue(new $EqualsBuilder().append(o1, o1).isEquals());
    assertFalse(new $EqualsBuilder().append(o1, o2).isEquals());
    assertFalse(new $EqualsBuilder().append(o1, Float.NaN).isEquals());
    assertTrue(new $EqualsBuilder().append(Float.NaN, Float.NaN).isEquals());
    assertTrue(new $EqualsBuilder().append(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY).isEquals());
  }


  @Test
  public void testFloatArray() {
    float[] obj1 = new float[2];
    obj1[0] = 5;
    obj1[1] = 6;
    float[] obj2 = new float[2];
    obj2[0] = 5;
    obj2[1] = 6;
    assertTrue(new $EqualsBuilder().append(obj1, obj1).isEquals());
    assertTrue(new $EqualsBuilder().append(obj1, obj2).isEquals());
    obj1[1] = 7;
    assertFalse(new $EqualsBuilder().append(obj1, obj2).isEquals());

    obj2 = null;
    assertFalse(new $EqualsBuilder().append(obj1, obj2).isEquals());
    obj1 = null;
    assertTrue(new $EqualsBuilder().append(obj1, obj2).isEquals());
  }


  @Test
  public void testFloatArrayHiddenByObject() {
    final float[] array1 = new float[2];
    array1[0] = 5;
    array1[1] = 6;
    final float[] array2 = new float[2];
    array2[0] = 5;
    array2[1] = 6;
    final Object obj1 = array1;
    final Object obj2 = array2;
    assertTrue(new $EqualsBuilder().append(obj1, obj1).isEquals());
    assertTrue(new $EqualsBuilder().append(obj1, array1).isEquals());
    assertTrue(new $EqualsBuilder().append(obj1, obj2).isEquals());
    assertTrue(new $EqualsBuilder().append(obj1, array2).isEquals());
    array1[1] = 7;
    assertFalse(new $EqualsBuilder().append(obj1, obj2).isEquals());
  }


  @Test
  public void testInt() {
    final int o1 = 1;
    final int o2 = 2;
    assertTrue(new $EqualsBuilder().append(o1, o1).isEquals());
    assertFalse(new $EqualsBuilder().append(o1, o2).isEquals());
  }


  @Test
  public void testIntArray() {
    int[] obj1 = new int[2];
    obj1[0] = 5;
    obj1[1] = 6;
    int[] obj2 = new int[2];
    obj2[0] = 5;
    obj2[1] = 6;
    assertTrue(new $EqualsBuilder().append(obj1, obj1).isEquals());
    assertTrue(new $EqualsBuilder().append(obj1, obj2).isEquals());
    obj1[1] = 7;
    assertFalse(new $EqualsBuilder().append(obj1, obj2).isEquals());

    obj2 = null;
    assertFalse(new $EqualsBuilder().append(obj1, obj2).isEquals());
    obj1 = null;
    assertTrue(new $EqualsBuilder().append(obj1, obj2).isEquals());
  }


  @Test
  public void testIntArrayHiddenByObject() {
    final int[] array1 = new int[2];
    array1[0] = 5;
    array1[1] = 6;
    final int[] array2 = new int[2];
    array2[0] = 5;
    array2[1] = 6;
    final Object obj1 = array1;
    final Object obj2 = array2;
    assertTrue(new $EqualsBuilder().append(obj1, obj1).isEquals());
    assertTrue(new $EqualsBuilder().append(obj1, array1).isEquals());
    assertTrue(new $EqualsBuilder().append(obj1, obj2).isEquals());
    assertTrue(new $EqualsBuilder().append(obj1, array2).isEquals());
    array1[1] = 7;
    assertFalse(new $EqualsBuilder().append(obj1, obj2).isEquals());
  }


  @Test
  public void testLong() {
    final long o1 = 1L;
    final long o2 = 2L;
    assertTrue(new $EqualsBuilder().append(o1, o1).isEquals());
    assertFalse(new $EqualsBuilder().append(o1, o2).isEquals());
  }


  @Test
  public void testLongArray() {
    long[] obj1 = new long[2];
    obj1[0] = 5L;
    obj1[1] = 6L;
    long[] obj2 = new long[2];
    obj2[0] = 5L;
    obj2[1] = 6L;
    assertTrue(new $EqualsBuilder().append(obj1, obj1).isEquals());
    assertTrue(new $EqualsBuilder().append(obj1, obj2).isEquals());
    obj1[1] = 7;
    assertFalse(new $EqualsBuilder().append(obj1, obj2).isEquals());

    obj2 = null;
    assertFalse(new $EqualsBuilder().append(obj1, obj2).isEquals());
    obj1 = null;
    assertTrue(new $EqualsBuilder().append(obj1, obj2).isEquals());
  }


  @Test
  public void testLongArrayHiddenByObject() {
    final long[] array1 = new long[2];
    array1[0] = 5L;
    array1[1] = 6L;
    final long[] array2 = new long[2];
    array2[0] = 5L;
    array2[1] = 6L;
    final Object obj1 = array1;
    final Object obj2 = array2;
    assertTrue(new $EqualsBuilder().append(obj1, obj1).isEquals());
    assertTrue(new $EqualsBuilder().append(obj1, array1).isEquals());
    assertTrue(new $EqualsBuilder().append(obj1, obj2).isEquals());
    assertTrue(new $EqualsBuilder().append(obj1, array2).isEquals());
    array1[1] = 7;
    assertFalse(new $EqualsBuilder().append(obj1, obj2).isEquals());
  }


  @Test
  public void testMixedArray() {
    final Object[] array1 = new Object[2];
    final Object[] array2 = new Object[2];
    for (int i = 0; i < array1.length; ++i) {
      array1[i] = new long[2];
      array2[i] = new long[2];
      for (int j = 0; j < 2; ++j) {
        ((long[]) array1[i])[j] = (i + 1) * (j + 1);
        ((long[]) array2[i])[j] = (i + 1) * (j + 1);
      }
    }
    assertTrue(new $EqualsBuilder().append(array1, array1).isEquals());
    assertTrue(new $EqualsBuilder().append(array1, array2).isEquals());
    ((long[]) array1[1])[1] = 0;
    assertFalse(new $EqualsBuilder().append(array1, array2).isEquals());
  }


  @Test
  public void testMultiBooleanArray() {
    final boolean[][] array1 = new boolean[2][2];
    final boolean[][] array2 = new boolean[2][2];
    for (int i = 0; i < array1.length; ++i) {
      for (int j = 0; j < array1[0].length; j++) {
        array1[i][j] = i == 1 || j == 1;
        array2[i][j] = i == 1 || j == 1;
      }
    }
    assertTrue(new $EqualsBuilder().append(array1, array1).isEquals());
    assertTrue(new $EqualsBuilder().append(array1, array2).isEquals());
    array1[1][1] = false;
    assertFalse(new $EqualsBuilder().append(array1, array2).isEquals());

    // compare 1 dim to 2.
    final boolean[] array3 = new boolean[]{true, true};
    assertFalse(new $EqualsBuilder().append(array1, array3).isEquals());
    assertFalse(new $EqualsBuilder().append(array3, array1).isEquals());
    assertFalse(new $EqualsBuilder().append(array2, array3).isEquals());
    assertFalse(new $EqualsBuilder().append(array3, array2).isEquals());
  }


  @Test
  public void testMultiByteArray() {
    final byte[][] array1 = new byte[2][2];
    final byte[][] array2 = new byte[2][2];
    for (byte i = 0; i < array1.length; ++i) {
      for (byte j = 0; j < array1[0].length; j++) {
        array1[i][j] = i;
        array2[i][j] = i;
      }
    }
    assertTrue(new $EqualsBuilder().append(array1, array1).isEquals());
    assertTrue(new $EqualsBuilder().append(array1, array2).isEquals());
    array1[1][1] = 0;
    assertFalse(new $EqualsBuilder().append(array1, array2).isEquals());
  }


  @Test
  public void testMultiCharArray() {
    final char[][] array1 = new char[2][2];
    final char[][] array2 = new char[2][2];
    for (char i = 0; i < array1.length; ++i) {
      for (char j = 0; j < array1[0].length; j++) {
        array1[i][j] = i;
        array2[i][j] = i;
      }
    }
    assertTrue(new $EqualsBuilder().append(array1, array1).isEquals());
    assertTrue(new $EqualsBuilder().append(array1, array2).isEquals());
    array1[1][1] = 0;
    assertFalse(new $EqualsBuilder().append(array1, array2).isEquals());
  }


  @Test
  public void testMultiDoubleArray() {
    final double[][] array1 = new double[2][2];
    final double[][] array2 = new double[2][2];
    for (int i = 0; i < array1.length; ++i) {
      for (int j = 0; j < array1[0].length; j++) {
        array1[i][j] = (i + 1) * (j + 1);
        array2[i][j] = (i + 1) * (j + 1);
      }
    }
    assertTrue(new $EqualsBuilder().append(array1, array1).isEquals());
    assertTrue(new $EqualsBuilder().append(array1, array2).isEquals());
    array1[1][1] = 0;
    assertFalse(new $EqualsBuilder().append(array1, array2).isEquals());
  }


  @Test
  public void testMultiFloatArray() {
    final float[][] array1 = new float[2][2];
    final float[][] array2 = new float[2][2];
    for (int i = 0; i < array1.length; ++i) {
      for (int j = 0; j < array1[0].length; j++) {
        array1[i][j] = (i + 1) * (j + 1);
        array2[i][j] = (i + 1) * (j + 1);
      }
    }
    assertTrue(new $EqualsBuilder().append(array1, array1).isEquals());
    assertTrue(new $EqualsBuilder().append(array1, array2).isEquals());
    array1[1][1] = 0;
    assertFalse(new $EqualsBuilder().append(array1, array2).isEquals());
  }


  @Test
  public void testMultiIntArray() {
    final int[][] array1 = new int[2][2];
    final int[][] array2 = new int[2][2];
    for (int i = 0; i < array1.length; ++i) {
      for (int j = 0; j < array1[0].length; j++) {
        array1[i][j] = (i + 1) * (j + 1);
        array2[i][j] = (i + 1) * (j + 1);
      }
    }
    assertTrue(new $EqualsBuilder().append(array1, array1).isEquals());
    assertTrue(new $EqualsBuilder().append(array1, array2).isEquals());
    array1[1][1] = 0;
    assertFalse(new $EqualsBuilder().append(array1, array2).isEquals());
  }


  @Test
  public void testMultiLongArray() {
    final long[][] array1 = new long[2][2];
    final long[][] array2 = new long[2][2];
    for (int i = 0; i < array1.length; ++i) {
      for (int j = 0; j < array1[0].length; j++) {
        array1[i][j] = (i + 1L) * (j + 1);
        array2[i][j] = (i + 1L) * (j + 1);
      }
    }
    assertTrue(new $EqualsBuilder().append(array1, array1).isEquals());
    assertTrue(new $EqualsBuilder().append(array1, array2).isEquals());
    array1[1][1] = 0;
    assertFalse(new $EqualsBuilder().append(array1, array2).isEquals());
  }


  @Test
  public void testMultiShortArray() {
    final short[][] array1 = new short[2][2];
    final short[][] array2 = new short[2][2];
    for (short i = 0; i < array1.length; ++i) {
      for (short j = 0; j < array1[0].length; j++) {
        array1[i][j] = i;
        array2[i][j] = i;
      }
    }
    assertTrue(new $EqualsBuilder().append(array1, array1).isEquals());
    assertTrue(new $EqualsBuilder().append(array1, array2).isEquals());
    array1[1][1] = 0;
    assertFalse(new $EqualsBuilder().append(array1, array2).isEquals());
  }


  /**
   * Test from http://issues.apache.org/bugzilla/show_bug.cgi?id=33067
   */
  @Test
  public void testNpeForNullElement() {
    final Object[] x1 = new Object[]{Integer.valueOf(1), null, Integer.valueOf(3)};
    final Object[] x2 = new Object[]{Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3)};

    // causes an NPE in 2.0 according to:
    // http://issues.apache.org/bugzilla/show_bug.cgi?id=33067
    new $EqualsBuilder().append(x1, x2);
  }


  @Test
  public void testObject() {
    final TestObject o1 = new TestObject(4);
    final TestObject o2 = new TestObject(5);
    assertTrue(new $EqualsBuilder().append(o1, o1).isEquals());
    assertFalse(new $EqualsBuilder().append(o1, o2).isEquals());
    o2.setA(4);
    assertTrue(new $EqualsBuilder().append(o1, o2).isEquals());

    assertFalse(new $EqualsBuilder().append(o1, this).isEquals());

    assertFalse(new $EqualsBuilder().append(o1, null).isEquals());
    assertFalse(new $EqualsBuilder().append(null, o2).isEquals());
    assertTrue(new $EqualsBuilder().append((Object) null, null).isEquals());
  }


  @Test
  public void testObjectArray() {
    TestObject[] obj1 = new TestObject[3];
    obj1[0] = new TestObject(4);
    obj1[1] = new TestObject(5);
    obj1[2] = null;
    TestObject[] obj2 = new TestObject[3];
    obj2[0] = new TestObject(4);
    obj2[1] = new TestObject(5);
    obj2[2] = null;

    assertTrue(new $EqualsBuilder().append(obj1, obj1).isEquals());
    assertTrue(new $EqualsBuilder().append(obj2, obj2).isEquals());
    assertTrue(new $EqualsBuilder().append(obj1, obj2).isEquals());
    obj1[1].setA(6);
    assertFalse(new $EqualsBuilder().append(obj1, obj2).isEquals());
    obj1[1].setA(5);
    assertTrue(new $EqualsBuilder().append(obj1, obj2).isEquals());
    obj1[2] = obj1[1];
    assertFalse(new $EqualsBuilder().append(obj1, obj2).isEquals());
    obj1[2] = null;
    assertTrue(new $EqualsBuilder().append(obj1, obj2).isEquals());

    obj2 = null;
    assertFalse(new $EqualsBuilder().append(obj1, obj2).isEquals());
    obj1 = null;
    assertTrue(new $EqualsBuilder().append(obj1, obj2).isEquals());
  }


  @Test
  public void testObjectArrayHiddenByObject() {
    final TestObject[] array1 = new TestObject[2];
    array1[0] = new TestObject(4);
    array1[1] = new TestObject(5);
    final TestObject[] array2 = new TestObject[2];
    array2[0] = new TestObject(4);
    array2[1] = new TestObject(5);
    final Object obj1 = array1;
    final Object obj2 = array2;
    assertTrue(new $EqualsBuilder().append(obj1, obj1).isEquals());
    assertTrue(new $EqualsBuilder().append(obj1, array1).isEquals());
    assertTrue(new $EqualsBuilder().append(obj1, obj2).isEquals());
    assertTrue(new $EqualsBuilder().append(obj1, array2).isEquals());
    array1[1].setA(6);
    assertFalse(new $EqualsBuilder().append(obj1, obj2).isEquals());
  }


  @Test
  public void testObjectBuild() {
    final TestObject o1 = new TestObject(4);
    final TestObject o2 = new TestObject(5);
    assertEquals(Boolean.TRUE, new $EqualsBuilder().append(o1, o1).build());
    assertEquals(Boolean.FALSE, new $EqualsBuilder().append(o1, o2).build());
    o2.setA(4);
    assertEquals(Boolean.TRUE, new $EqualsBuilder().append(o1, o2).build());

    assertEquals(Boolean.FALSE, new $EqualsBuilder().append(o1, this).build());

    assertEquals(Boolean.FALSE, new $EqualsBuilder().append(o1, null).build());
    assertEquals(Boolean.FALSE, new $EqualsBuilder().append(null, o2).build());
    assertEquals(Boolean.TRUE, new $EqualsBuilder().append((Object) null, null).build());
  }


  @Test
  public void testRaggedArray() {
    final long[][] array1 = new long[2][];
    final long[][] array2 = new long[2][];
    for (int i = 0; i < array1.length; ++i) {
      array1[i] = new long[2];
      array2[i] = new long[2];
      for (int j = 0; j < array1[i].length; ++j) {
        array1[i][j] = (i + 1L) * (j + 1);
        array2[i][j] = (i + 1L) * (j + 1);
      }
    }
    assertTrue(new $EqualsBuilder().append(array1, array1).isEquals());
    assertTrue(new $EqualsBuilder().append(array1, array2).isEquals());
    array1[1][1] = 0;
    assertFalse(new $EqualsBuilder().append(array1, array2).isEquals());
  }


  @Test
  public void testShort() {
    final short o1 = 1;
    final short o2 = 2;
    assertTrue(new $EqualsBuilder().append(o1, o1).isEquals());
    assertFalse(new $EqualsBuilder().append(o1, o2).isEquals());
  }


  @Test
  public void testShortArray() {
    short[] obj1 = new short[2];
    obj1[0] = 5;
    obj1[1] = 6;
    short[] obj2 = new short[2];
    obj2[0] = 5;
    obj2[1] = 6;
    assertTrue(new $EqualsBuilder().append(obj1, obj1).isEquals());
    assertTrue(new $EqualsBuilder().append(obj1, obj2).isEquals());
    obj1[1] = 7;
    assertFalse(new $EqualsBuilder().append(obj1, obj2).isEquals());

    obj2 = null;
    assertFalse(new $EqualsBuilder().append(obj1, obj2).isEquals());
    obj1 = null;
    assertTrue(new $EqualsBuilder().append(obj1, obj2).isEquals());
  }


  @Test
  public void testShortArrayHiddenByObject() {
    final short[] array1 = new short[2];
    array1[0] = 5;
    array1[1] = 6;
    final short[] array2 = new short[2];
    array2[0] = 5;
    array2[1] = 6;
    final Object obj1 = array1;
    final Object obj2 = array2;
    assertTrue(new $EqualsBuilder().append(obj1, obj1).isEquals());
    assertTrue(new $EqualsBuilder().append(obj1, array1).isEquals());
    assertTrue(new $EqualsBuilder().append(obj1, obj2).isEquals());
    assertTrue(new $EqualsBuilder().append(obj1, array2).isEquals());
    array1[1] = 7;
    assertFalse(new $EqualsBuilder().append(obj1, obj2).isEquals());
  }


  @Test
  public void testSuper() {
    final TestObject o1 = new TestObject(4);
    final TestObject o2 = new TestObject(5);
    assertTrue(new $EqualsBuilder().appendSuper(true).append(o1, o1).isEquals());
    assertFalse(new $EqualsBuilder().appendSuper(false).append(o1, o1).isEquals());
    assertFalse(new $EqualsBuilder().appendSuper(true).append(o1, o2).isEquals());
    assertFalse(new $EqualsBuilder().appendSuper(false).append(o1, o2).isEquals());
  }


  /**
   * Tests two instances of classes that can be equal and that are not "related". The two classes are not subclasses
   * of each other and do not share a parent aside from Object.
   * See http://issues.apache.org/bugzilla/show_bug.cgi?id=33069
   */
  @Test
  public void testUnrelatedClasses() {
    final Object[] x = new Object[]{new TestACanEqualB(1)};
    final Object[] y = new Object[]{new TestBCanEqualA(1)};

    // sanity checks:
    assertArrayEquals(x, x);
    assertArrayEquals(y, y);
    assertArrayEquals(x, y);
    assertArrayEquals(y, x);
    // real tests:
    assertEquals(x[0], x[0]);
    assertEquals(y[0], y[0]);
    assertEquals(x[0], y[0]);
    assertEquals(y[0], x[0]);
    assertTrue(new $EqualsBuilder().append(x, x).isEquals());
    assertTrue(new $EqualsBuilder().append(y, y).isEquals());
    assertTrue(new $EqualsBuilder().append(x, y).isEquals());
    assertTrue(new $EqualsBuilder().append(y, x).isEquals());
  }

}

