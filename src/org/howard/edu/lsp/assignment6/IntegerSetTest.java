package org.howard.edu.lsp.assignment6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit 5 test suite for the IntegerSet class.
 * Tests cover all public methods including normal cases, edge cases,
 * and exception scenarios.
 */
@DisplayName("IntegerSet Test Suite")
class IntegerSetTest {

  private IntegerSet set1;
  private IntegerSet set2;
  private IntegerSet emptySet;

  @BeforeEach
  void setUp() {
    set1 = new IntegerSet();
    set2 = new IntegerSet();
    emptySet = new IntegerSet();
  }

  // ========== clear() Tests ==========

  @Test
  @DisplayName("clear() removes all elements from a non-empty set")
  void testClearNonEmptySet() {
    set1.add(1);
    set1.add(2);
    set1.add(3);
    assertEquals(3, set1.length());
    set1.clear();
    assertEquals(0, set1.length());
    assertTrue(set1.isEmpty());
  }

  @Test
  @DisplayName("clear() on an already empty set does nothing")
  void testClearEmptySet() {
    assertTrue(emptySet.isEmpty());
    emptySet.clear();
    assertTrue(emptySet.isEmpty());
    assertEquals(0, emptySet.length());
  }

  // ========== length() Tests ==========

  @Test
  @DisplayName("length() returns 0 for empty set")
  void testLengthEmptySet() {
    assertEquals(0, emptySet.length());
  }

  @Test
  @DisplayName("length() returns correct count after additions")
  void testLengthAfterAdditions() {
    set1.add(1);
    assertEquals(1, set1.length());
    set1.add(2);
    assertEquals(2, set1.length());
    set1.add(3);
    assertEquals(3, set1.length());
  }

  @Test
  @DisplayName("length() does not increase when adding duplicates")
  void testLengthWithDuplicates() {
    set1.add(5);
    set1.add(5);
    set1.add(5);
    assertEquals(1, set1.length());
  }

  // ========== equals() Tests ==========

  @Test
  @DisplayName("equals() returns true for sets with same elements in same order")
  void testEqualsIdenticalOrder() {
    set1.add(1);
    set1.add(2);
    set1.add(3);
    set2.add(1);
    set2.add(2);
    set2.add(3);
    assertTrue(set1.equals(set2));
  }

  @Test
  @DisplayName("equals() returns true for sets with same elements in different order")
  void testEqualsDifferentOrder() {
    set1.add(3);
    set1.add(1);
    set1.add(2);
    set2.add(1);
    set2.add(2);
    set2.add(3);
    assertTrue(set1.equals(set2));
  }

  @Test
  @DisplayName("equals() returns false for sets with different elements")
  void testEqualsDifferentElements() {
    set1.add(1);
    set1.add(2);
    set2.add(1);
    set2.add(3);
    assertFalse(set1.equals(set2));
  }

  @Test
  @DisplayName("equals() returns true for two empty sets")
  void testEqualsEmptySets() {
    IntegerSet empty1 = new IntegerSet();
    IntegerSet empty2 = new IntegerSet();
    assertTrue(empty1.equals(empty2));
  }

  @Test
  @DisplayName("equals() returns false when comparing with non-IntegerSet object")
  void testEqualsNonIntegerSet() {
    set1.add(1);
    assertFalse(set1.equals("not a set"));
    assertFalse(set1.equals(123));
  }

  @Test
  @DisplayName("equals() returns false when comparing with null")
  void testEqualsNull() {
    set1.add(1);
    assertFalse(set1.equals(null));
  }

  @Test
  @DisplayName("equals() returns true for reflexive property (a.equals(a))")
  void testEqualsReflexive() {
    set1.add(1);
    set1.add(2);
    assertTrue(set1.equals(set1));
  }

  @Test
  @DisplayName("equals() returns false for sets with different sizes")
  void testEqualsDifferentSizes() {
    set1.add(1);
    set1.add(2);
    set1.add(3);
    set2.add(1);
    set2.add(2);
    assertFalse(set1.equals(set2));
  }

  // ========== contains() Tests ==========

  @Test
  @DisplayName("contains() returns true for element in set")
  void testContainsExistingElement() {
    set1.add(5);
    set1.add(10);
    assertTrue(set1.contains(5));
    assertTrue(set1.contains(10));
  }

  @Test
  @DisplayName("contains() returns false for element not in set")
  void testContainsNonExistentElement() {
    set1.add(5);
    assertFalse(set1.contains(10));
  }

  @Test
  @DisplayName("contains() returns false for empty set")
  void testContainsEmptySet() {
    assertFalse(emptySet.contains(1));
  }

  @Test
  @DisplayName("contains() works with negative integers")
  void testContainsNegativeIntegers() {
    set1.add(-5);
    set1.add(-10);
    assertTrue(set1.contains(-5));
    assertTrue(set1.contains(-10));
    assertFalse(set1.contains(5));
  }

  // ========== largest() Tests ==========

  @Test
  @DisplayName("largest() returns the maximum element")
  void testLargestBasic() {
    set1.add(5);
    set1.add(10);
    set1.add(3);
    assertEquals(10, set1.largest());
  }

  @Test
  @DisplayName("largest() throws exception on empty set")
  void testLargestEmptySet() {
    assertThrows(IllegalStateException.class, () -> emptySet.largest());
  }

  @Test
  @DisplayName("largest() returns single element when set has only one element")
  void testLargestSingleElement() {
    set1.add(42);
    assertEquals(42, set1.largest());
  }

  @Test
  @DisplayName("largest() handles negative integers correctly")
  void testLargestNegativeIntegers() {
    set1.add(-1);
    set1.add(-100);
    set1.add(-50);
    assertEquals(-1, set1.largest());
  }

  @Test
  @DisplayName("largest() handles mixed positive and negative integers")
  void testLargestMixedIntegers() {
    set1.add(-10);
    set1.add(5);
    set1.add(0);
    set1.add(20);
    assertEquals(20, set1.largest());
  }

  // ========== smallest() Tests ==========

  @Test
  @DisplayName("smallest() returns the minimum element")
  void testSmallestBasic() {
    set1.add(5);
    set1.add(10);
    set1.add(3);
    assertEquals(3, set1.smallest());
  }

  @Test
  @DisplayName("smallest() throws exception on empty set")
  void testSmallestEmptySet() {
    assertThrows(IllegalStateException.class, () -> emptySet.smallest());
  }

  @Test
  @DisplayName("smallest() returns single element when set has only one element")
  void testSmallestSingleElement() {
    set1.add(42);
    assertEquals(42, set1.smallest());
  }

  @Test
  @DisplayName("smallest() handles negative integers correctly")
  void testSmallestNegativeIntegers() {
    set1.add(-1);
    set1.add(-100);
    set1.add(-50);
    assertEquals(-100, set1.smallest());
  }

  @Test
  @DisplayName("smallest() handles mixed positive and negative integers")
  void testSmallestMixedIntegers() {
    set1.add(-10);
    set1.add(5);
    set1.add(0);
    set1.add(20);
    assertEquals(-10, set1.smallest());
  }

  // ========== add() Tests ==========

  @Test
  @DisplayName("add() adds element to empty set")
  void testAddToEmptySet() {
    emptySet.add(5);
    assertEquals(1, emptySet.length());
    assertTrue(emptySet.contains(5));
  }

  @Test
  @DisplayName("add() adds multiple distinct elements")
  void testAddMultipleElements() {
    set1.add(1);
    set1.add(2);
    set1.add(3);
    assertEquals(3, set1.length());
    assertTrue(set1.contains(1));
    assertTrue(set1.contains(2));
    assertTrue(set1.contains(3));
  }

  @Test
  @DisplayName("add() does not add duplicate elements")
  void testAddDuplicates() {
    set1.add(5);
    set1.add(5);
    set1.add(5);
    assertEquals(1, set1.length());
  }

  @Test
  @DisplayName("add() works with negative integers")
  void testAddNegativeIntegers() {
    set1.add(-5);
    set1.add(-10);
    assertTrue(set1.contains(-5));
    assertTrue(set1.contains(-10));
  }

  @Test
  @DisplayName("add() works with zero")
  void testAddZero() {
    set1.add(0);
    assertTrue(set1.contains(0));
    assertEquals(1, set1.length());
  }

  // ========== remove() Tests ==========

  @Test
  @DisplayName("remove() removes element from set")
  void testRemoveExistingElement() {
    set1.add(5);
    set1.add(10);
    set1.remove(5);
    assertFalse(set1.contains(5));
    assertTrue(set1.contains(10));
    assertEquals(1, set1.length());
  }

  @Test
  @DisplayName("remove() does nothing if element not in set")
  void testRemoveNonExistentElement() {
    set1.add(5);
    set1.remove(10);
    assertEquals(1, set1.length());
    assertTrue(set1.contains(5));
  }

  @Test
  @DisplayName("remove() from empty set does nothing")
  void testRemoveFromEmptySet() {
    emptySet.remove(5);
    assertEquals(0, emptySet.length());
  }

  @Test
  @DisplayName("remove() all elements from set")
  void testRemoveAllElements() {
    set1.add(1);
    set1.add(2);
    set1.add(3);
    set1.remove(1);
    set1.remove(2);
    set1.remove(3);
    assertTrue(set1.isEmpty());
    assertEquals(0, set1.length());
  }

  // ========== union() Tests ==========

  @Test
  @DisplayName("union() combines two disjoint sets")
  void testUnionDisjointSets() {
    set1.add(1);
    set1.add(2);
    set2.add(3);
    set2.add(4);
    set1.union(set2);
    assertEquals(4, set1.length());
    assertTrue(set1.contains(1));
    assertTrue(set1.contains(2));
    assertTrue(set1.contains(3));
    assertTrue(set1.contains(4));
  }

  @Test
  @DisplayName("union() combines sets with overlapping elements")
  void testUnionOverlappingSets() {
    set1.add(1);
    set1.add(2);
    set1.add(3);
    set2.add(2);
    set2.add(3);
    set2.add(4);
    set1.union(set2);
    assertEquals(4, set1.length());
    assertTrue(set1.contains(1));
    assertTrue(set1.contains(2));
    assertTrue(set1.contains(3));
    assertTrue(set1.contains(4));
  }

  @Test
  @DisplayName("union() with empty set leaves original set unchanged")
  void testUnionWithEmptySet() {
    set1.add(1);
    set1.add(2);
    IntegerSet empty = new IntegerSet();
    set1.union(empty);
    assertEquals(2, set1.length());
    assertTrue(set1.contains(1));
    assertTrue(set1.contains(2));
  }

  @Test
  @DisplayName("union() of empty set with non-empty set adds all elements")
  void testUnionEmptySetWithNonEmpty() {
    set1.add(1);
    set1.add(2);
    emptySet.union(set1);
    assertEquals(2, emptySet.length());
    assertTrue(emptySet.contains(1));
    assertTrue(emptySet.contains(2));
  }

  @Test
  @DisplayName("union() with null does nothing")
  void testUnionWithNull() {
    set1.add(1);
    set1.add(2);
    set1.union(null);
    assertEquals(2, set1.length());
  }

  @Test
  @DisplayName("union() of identical sets")
  void testUnionIdenticalSets() {
    set1.add(1);
    set1.add(2);
    set2.add(1);
    set2.add(2);
    set1.union(set2);
    assertEquals(2, set1.length());
    assertTrue(set1.contains(1));
    assertTrue(set1.contains(2));
  }

  // ========== intersect() Tests ==========

  @Test
  @DisplayName("intersect() finds common elements in two sets")
  void testIntersectOverlappingSets() {
    set1.add(1);
    set1.add(2);
    set1.add(3);
    set2.add(2);
    set2.add(3);
    set2.add(4);
    set1.intersect(set2);
    assertEquals(2, set1.length());
    assertTrue(set1.contains(2));
    assertTrue(set1.contains(3));
    assertFalse(set1.contains(1));
    assertFalse(set1.contains(4));
  }

  @Test
  @DisplayName("intersect() with disjoint sets results in empty set")
  void testIntersectDisjointSets() {
    set1.add(1);
    set1.add(2);
    set2.add(3);
    set2.add(4);
    set1.intersect(set2);
    assertTrue(set1.isEmpty());
    assertEquals(0, set1.length());
  }

  @Test
  @DisplayName("intersect() with empty set results in empty set")
  void testIntersectWithEmptySet() {
    set1.add(1);
    set1.add(2);
    IntegerSet empty = new IntegerSet();
    set1.intersect(empty);
    assertTrue(set1.isEmpty());
    assertEquals(0, set1.length());
  }

  @Test
  @DisplayName("intersect() of empty set with non-empty set results in empty set")
  void testIntersectEmptySetWithNonEmpty() {
    set1.add(1);
    set1.add(2);
    emptySet.intersect(set1);
    assertTrue(emptySet.isEmpty());
  }

  @Test
  @DisplayName("intersect() with null clears the set")
  void testIntersectWithNull() {
    set1.add(1);
    set1.add(2);
    set1.intersect(null);
    assertTrue(set1.isEmpty());
  }

  @Test
  @DisplayName("intersect() with identical sets")
  void testIntersectIdenticalSets() {
    set1.add(1);
    set1.add(2);
    set1.add(3);
    set2.add(1);
    set2.add(2);
    set2.add(3);
    set1.intersect(set2);
    assertEquals(3, set1.length());
    assertTrue(set1.contains(1));
    assertTrue(set1.contains(2));
    assertTrue(set1.contains(3));
  }

  // ========== diff() Tests ==========

  @Test
  @DisplayName("diff() removes elements that exist in other set")
  void testDiffBasic() {
    set1.add(1);
    set1.add(2);
    set1.add(3);
    set2.add(2);
    set2.add(4);
    set1.diff(set2);
    assertEquals(2, set1.length());
    assertTrue(set1.contains(1));
    assertTrue(set1.contains(3));
    assertFalse(set1.contains(2));
  }

  @Test
  @DisplayName("diff() with disjoint sets leaves set unchanged")
  void testDiffDisjointSets() {
    set1.add(1);
    set1.add(2);
    set2.add(3);
    set2.add(4);
    set1.diff(set2);
    assertEquals(2, set1.length());
    assertTrue(set1.contains(1));
    assertTrue(set1.contains(2));
  }

  @Test
  @DisplayName("diff() with identical sets results in empty set")
  void testDiffIdenticalSets() {
    set1.add(1);
    set1.add(2);
    set2.add(1);
    set2.add(2);
    set1.diff(set2);
    assertTrue(set1.isEmpty());
  }

  @Test
  @DisplayName("diff() with empty set leaves set unchanged")
  void testDiffWithEmptySet() {
    set1.add(1);
    set1.add(2);
    IntegerSet empty = new IntegerSet();
    set1.diff(empty);
    assertEquals(2, set1.length());
    assertTrue(set1.contains(1));
    assertTrue(set1.contains(2));
  }

  @Test
  @DisplayName("diff() on empty set with non-empty set remains empty")
  void testDiffEmptySetWithNonEmpty() {
    set1.add(1);
    set1.add(2);
    emptySet.diff(set1);
    assertTrue(emptySet.isEmpty());
  }

  @Test
  @DisplayName("diff() with null does nothing")
  void testDiffWithNull() {
    set1.add(1);
    set1.add(2);
    set1.diff(null);
    assertEquals(2, set1.length());
  }

  // ========== complement() Tests ==========

  @Test
  @DisplayName("complement() returns elements in other but not in this")
  void testComplementBasic() {
    set1.add(1);
    set1.add(2);
    set2.add(2);
    set2.add(3);
    set2.add(4);
    set1.complement(set2);
    assertEquals(2, set1.length());
    assertTrue(set1.contains(3));
    assertTrue(set1.contains(4));
    assertFalse(set1.contains(1));
    assertFalse(set1.contains(2));
  }

  @Test
  @DisplayName("complement() with disjoint sets results in all elements of other")
  void testComplementDisjointSets() {
    set1.add(1);
    set1.add(2);
    set2.add(3);
    set2.add(4);
    set1.complement(set2);
    assertEquals(2, set1.length());
    assertTrue(set1.contains(3));
    assertTrue(set1.contains(4));
  }

  @Test
  @DisplayName("complement() with identical sets results in empty set")
  void testComplementIdenticalSets() {
    set1.add(1);
    set1.add(2);
    set2.add(1);
    set2.add(2);
    set1.complement(set2);
    assertTrue(set1.isEmpty());
  }

  @Test
  @DisplayName("complement() with empty set results in empty set")
  void testComplementWithEmptySet() {
    set1.add(1);
    set1.add(2);
    IntegerSet empty = new IntegerSet();
    set1.complement(empty);
    assertTrue(set1.isEmpty());
  }

  @Test
  @DisplayName("complement() of empty set with non-empty set")
  void testComplementEmptySetWithNonEmpty() {
    set1.add(1);
    set1.add(2);
    emptySet.complement(set1);
    assertEquals(2, emptySet.length());
    assertTrue(emptySet.contains(1));
    assertTrue(emptySet.contains(2));
  }

  @Test
  @DisplayName("complement() with null does nothing")
  void testComplementWithNull() {
    set1.add(1);
    set1.add(2);
    set1.complement(null);
    assertEquals(2, set1.length());
  }

  // ========== isEmpty() Tests ==========

  @Test
  @DisplayName("isEmpty() returns true for empty set")
  void testIsEmptyTrue() {
    assertTrue(emptySet.isEmpty());
  }

  @Test
  @DisplayName("isEmpty() returns false for non-empty set")
  void testIsEmptyFalse() {
    set1.add(1);
    assertFalse(set1.isEmpty());
  }

  @Test
  @DisplayName("isEmpty() returns true after clearing a set")
  void testIsEmptyAfterClear() {
    set1.add(1);
    set1.add(2);
    assertFalse(set1.isEmpty());
    set1.clear();
    assertTrue(set1.isEmpty());
  }

  @Test
  @DisplayName("isEmpty() returns true after removing all elements")
  void testIsEmptyAfterRemoveAll() {
    set1.add(1);
    set1.remove(1);
    assertTrue(set1.isEmpty());
  }

  // ========== toString() Tests ==========

  @Test
  @DisplayName("toString() returns empty brackets for empty set")
  void testToStringEmpty() {
    assertEquals("[]", emptySet.toString());
  }

  @Test
  @DisplayName("toString() returns correct format for single element")
  void testToStringSingleElement() {
    set1.add(5);
    assertEquals("[5]", set1.toString());
  }

  @Test
  @DisplayName("toString() returns correct format for multiple elements")
  void testToStringMultipleElements() {
    set1.add(1);
    set1.add(2);
    set1.add(3);
    String result = set1.toString();
    assertTrue(result.startsWith("["));
    assertTrue(result.endsWith("]"));
    assertTrue(result.contains("1"));
    assertTrue(result.contains("2"));
    assertTrue(result.contains("3"));
  }

  @Test
  @DisplayName("toString() format matches [1, 2, 3] pattern")
  void testToStringFormat() {
    set1.add(1);
    set1.add(2);
    set1.add(3);
    String result = set1.toString();
    assertTrue(result.matches("\\[\\d+(, \\d+)*\\]"));
  }

  @Test
  @DisplayName("toString() works with negative integers")
  void testToStringNegative() {
    set1.add(-1);
    set1.add(-2);
    String result = set1.toString();
    assertTrue(result.startsWith("["));
    assertTrue(result.endsWith("]"));
  }

  // ========== Integration Tests ==========

  @Test
  @DisplayName("Integration: Complex sequence of operations")
  void testComplexOperations() {
    set1.add(1);
    set1.add(2);
    set1.add(3);
    set2.add(2);
    set2.add(3);
    set2.add(4);
    set2.add(5);

    IntegerSet backup = new IntegerSet();
    backup.union(set1);
    assertEquals(3, backup.length());

    set1.union(set2);
    assertEquals(5, set1.length());

    IntegerSet intersection = new IntegerSet();
    intersection.union(set1);
    intersection.intersect(set2);
    assertEquals(4, intersection.length());
  }

  @Test
  @DisplayName("Integration: Multiple set operations maintain integrity")
  void testMultipleOperations() {
    set1.add(1);
    set1.add(2);
    set1.add(3);
    set2.add(3);
    set2.add(4);
    set2.add(5);

    set1.union(set2);
    assertTrue(set1.contains(1));
    assertTrue(set1.contains(2));
    assertTrue(set1.contains(3));
    assertTrue(set1.contains(4));
    assertTrue(set1.contains(5));

    set1.remove(1);
    assertFalse(set1.contains(1));
    assertEquals(4, set1.length());

    set1.diff(set2);
    assertEquals(1, set1.length());
    assertTrue(set1.contains(2));
  }

  @Test
  @DisplayName("Integration: Set operations with edge cases")
  void testEdgeCaseOperations() {
    set1.add(Integer.MAX_VALUE);
    set1.add(Integer.MIN_VALUE);
    set1.add(0);

    assertEquals(3, set1.length());
    assertEquals(Integer.MAX_VALUE, set1.largest());
    assertEquals(Integer.MIN_VALUE, set1.smallest());

    set2.add(Integer.MAX_VALUE);
    set1.intersect(set2);
    assertEquals(1, set1.length());
    assertTrue(set1.contains(Integer.MAX_VALUE));
  }
}
