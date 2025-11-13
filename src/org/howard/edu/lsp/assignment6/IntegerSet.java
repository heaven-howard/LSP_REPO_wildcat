package org.howard.edu.lsp.assignment6;

import java.util.ArrayList;
import java.util.List;

/**
 * IntegerSet represents a mathematical set of unique integers.
 * This class provides standard set operations including union, intersection,
 * difference, and complement. The underlying storage is an ArrayList that
 * maintains uniqueness of elements (no duplicates allowed).
 */
public class IntegerSet {
  private List<Integer> set = new ArrayList<Integer>();

  /**
   * Clears all elements from the set, making it empty.
   */
  public void clear() {
    set.clear();
  }

  /**
   * Returns the number of elements in the set.
   *
   * @return the size of the set
   */
  public int length() {
    return set.size();
  }

  /**
   * Compares this set with another object for equality.
   * Two sets are equal if they contain the same elements, regardless of order.
   *
   * @param o the object to compare with
   * @return true if o is an IntegerSet with the same elements, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    IntegerSet that = (IntegerSet) o;
    if (this.set.size() != that.set.size()) return false;
    return this.set.containsAll(that.set) && that.set.containsAll(this.set);
  }

  /**
   * Checks whether the set contains a specific value.
   *
   * @param value the integer to search for
   * @return true if the set contains the value, false otherwise
   */
  public boolean contains(int value) {
    return set.contains(value);
  }

  /**
   * Returns the largest element in the set.
   *
   * @return the maximum value in the set
   * @throws IllegalStateException if the set is empty
   */
  public int largest() {
    if (set.isEmpty()) {
      throw new IllegalStateException("Set is empty");
    }
    return set.stream().mapToInt(Integer::intValue).max().orElse(Integer.MIN_VALUE);
  }

  /**
   * Returns the smallest element in the set.
   *
   * @return the minimum value in the set
   * @throws IllegalStateException if the set is empty
   */
  public int smallest() {
    if (set.isEmpty()) {
      throw new IllegalStateException("Set is empty");
    }
    return set.stream().mapToInt(Integer::intValue).min().orElse(Integer.MAX_VALUE);
  }

  /**
   * Adds an integer to the set if it is not already present.
   * Maintains the uniqueness constraint of a set.
   *
   * @param item the integer to add
   */
  public void add(int item) {
    if (!set.contains(item)) {
      set.add(item);
    }
  }

  /**
   * Removes an integer from the set if it is present.
   * Does nothing if the element is not in the set.
   *
   * @param item the integer to remove
   */
  public void remove(int item) {
    set.remove(Integer.valueOf(item));
  }

  /**
   * Performs a union operation with another set.
   * Modifies the current set to contain all elements from both this set
   * and the other set.
   *
   * @param other the other IntegerSet to union with (must not be null)
   */
  public void union(IntegerSet other) {
    if (other != null) {
      for (Integer item : other.set) {
        if (!this.set.contains(item)) {
          this.set.add(item);
        }
      }
    }
  }

  /**
   * Performs an intersection operation with another set.
   * Modifies the current set to contain only elements that are present
   * in both this set and the other set.
   *
   * @param other the other IntegerSet to intersect with (must not be null)
   */
  public void intersect(IntegerSet other) {
    if (other == null) {
      this.set.clear();
      return;
    }
    List<Integer> toRemove = new ArrayList<>();
    for (Integer item : this.set) {
      if (!other.set.contains(item)) {
        toRemove.add(item);
      }
    }
    this.set.removeAll(toRemove);
  }

  /**
   * Performs a difference operation with another set.
   * Modifies the current set to contain only elements that are in this set
   * but not in the other set.
   *
   * @param other the other IntegerSet to difference with (must not be null)
   */
  public void diff(IntegerSet other) {
    if (other != null) {
      for (Integer item : other.set) {
        this.set.remove(Integer.valueOf(item));
      }
    }
  }

  /**
   * Performs a complement operation with respect to another set.
   * Modifies the current set to contain elements from the other set
   * that are not in this set.
   *
   * @param other the other IntegerSet to use as the universal set
   *              (must not be null)
   */
  public void complement(IntegerSet other) {
    if (other == null) {
      return;
    }
    List<Integer> complementSet = new ArrayList<>();
    for (Integer item : other.set) {
      if (!this.set.contains(item)) {
        complementSet.add(item);
      }
    }
    this.set = complementSet;
  }

  /**
   * Checks whether the set is empty.
   *
   * @return true if the set contains no elements, false otherwise
   */
  public boolean isEmpty() {
    return set.isEmpty();
  }

  /**
   * Returns a string representation of the set.
   * Format: [1, 2, 3]
   *
   * @return a string representation of the set
   */
  @Override
  public String toString() {
    if (set.isEmpty()) {
      return "[]";
    }
    StringBuilder sb = new StringBuilder("[");
    for (int i = 0; i < set.size(); i++) {
      sb.append(set.get(i));
      if (i < set.size() - 1) {
        sb.append(", ");
      }
    }
    sb.append("]");
    return sb.toString();
  }
}
