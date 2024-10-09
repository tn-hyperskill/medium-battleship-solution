package battleship.util;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

class ImmutBiIdxTableTest {

  @Test
  void fromSeqCreatesTableFromList() {
    List<String> data = List.of("A", "B", "C");
    ImmutBiIdxTable<String> table = ImmutBiIdxTable.fromSeq(data);
    assertEquals(3, table.size());
  }

  @Test
  void fromSeqCreatesTableFromArray() {
    String[] data = {"X", "Y", "Z"};
    ImmutBiIdxTable<String> table = ImmutBiIdxTable.fromSeq(data);
    assertEquals(3, table.size());
  }

  @Test
  void hasIdxReturnsTrueForValidIndex() {
    ImmutBiIdxTable<Integer> table =
        ImmutBiIdxTable.fromSeq(List.of(10, 20, 30));
    assertTrue(table.hasIdx(1));
  }

  @Test
  void hasIdxReturnsFalseForInvalidIndex() {
    ImmutBiIdxTable<Integer> table =
        ImmutBiIdxTable.fromSeq(List.of(10, 20, 30));
    assertFalse(table.hasIdx(5));
  }

  @Test
  void hasValReturnsTrueForExistingValue() {
    ImmutBiIdxTable<String> table =
        ImmutBiIdxTable.fromSeq(List.of("A", "B", "C"));
    assertTrue(table.hasVal("B"));
  }

  @Test
  void hasValReturnsFalseForNonExistingValue() {
    ImmutBiIdxTable<String> table =
        ImmutBiIdxTable.fromSeq(List.of("A", "B", "C"));
    assertFalse(table.hasVal("X"));
  }

  @Test
  void getValReturnsCorrectValue() {
    ImmutBiIdxTable<Integer> table =
        ImmutBiIdxTable.fromSeq(List.of(10, 20, 30));
    assertEquals(20, table.getVal(1));
  }

  @Test
  void getValThrowsNoSuchElementExceptionForInvalidIndex() {
    ImmutBiIdxTable<Integer> table =
        ImmutBiIdxTable.fromSeq(List.of(10, 20, 30));
    assertThrows(NoSuchElementException.class, () -> table.getVal(5));
  }

  @Test
  void getIdxReturnsCorrectIndex() {
    ImmutBiIdxTable<String> table =
        ImmutBiIdxTable.fromSeq(List.of("A", "B", "C"));
    assertEquals(1, table.getIdx("B"));
  }

  @Test
  void getIdxThrowsForNonExistingValue() {
    ImmutBiIdxTable<String> table =
        ImmutBiIdxTable.fromSeq(List.of("A", "B", "C"));
    assertThrows(NoSuchElementException.class, () -> table.getIdx("X"));
  }

  @Test
  void tryGetValReturnsOptionalWithValue() {
    ImmutBiIdxTable<Integer> table =
        ImmutBiIdxTable.fromSeq(List.of(10, 20, 30));
    Optional<Integer> result = table.tryGetVal(1);
    assertTrue(result.isPresent());
    assertEquals(20, result.get());
  }

  @Test
  void tryGetValReturnsEmptyOptionalForInvalidIndex() {
    ImmutBiIdxTable<Integer> table =
        ImmutBiIdxTable.fromSeq(List.of(10, 20, 30));
    Optional<Integer> result = table.tryGetVal(5);
    assertFalse(result.isPresent());
  }

  @Test
  void tryGetIdxReturnsOptionalWithIndex() {
    ImmutBiIdxTable<String> table =
        ImmutBiIdxTable.fromSeq(List.of("A", "B", "C"));
    OptionalInt result = table.tryGetIdx("B");
    assertTrue(result.isPresent());
    assertEquals(1, result.getAsInt());
  }

  @Test
  void tryGetIdxReturnsEmptyOptionalForNonExistingValue() {
    ImmutBiIdxTable<String> table =
        ImmutBiIdxTable.fromSeq(List.of("A", "B", "C"));
    OptionalInt result = table.tryGetIdx("X");
    assertFalse(result.isPresent());
  }

  @Test
  void valStreamReturnsCorrectStream() {
    ImmutBiIdxTable<Integer> table =
        ImmutBiIdxTable.fromSeq(List.of(10, 20, 30));
    Stream<Integer> stream = table.valStream();
    Integer[] expected = {10, 20, 30};
    assertArrayEquals(expected, stream.toArray());
  }

  @Test
  @SuppressWarnings("PMD.JUnitTestContainsTooManyAsserts")
  void valIterReturnsCorrectIterator() {
    ImmutBiIdxTable<Integer> table =
        ImmutBiIdxTable.fromSeq(List.of(10, 20, 30));
    Iterator<Integer> iter = table.valIter();
    assertTrue(iter.hasNext());
    assertEquals(10, iter.next());
    assertEquals(20, iter.next());
    assertEquals(30, iter.next());
    assertFalse(iter.hasNext());
  }
}
