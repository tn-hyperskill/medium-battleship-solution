package battleship.util.matrix;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.util.Iterator;
import java.util.stream.Stream;

public class MatrixTest {

  @Test
  void constructorSetsCorrectDimensions() {
    Matrix<Integer> matrix = new Matrix<>(0, 3, 4);
    assertEquals(3, matrix.width());
    assertEquals(4, matrix.height());
  }

  @Test
  void sizeReturnsCorrectValue() {
    Matrix<Integer> matrix = new Matrix<>(0, 3, 4);
    assertEquals(12, matrix.size());
  }

  @Test
  void valAtReturnsCorrectValue() {
    Matrix<Integer> matrix = new Matrix<>(42, 3, 4);
    MatrixCoordinates coord = new MatrixCoordinates(1, 1);
    assertEquals(42, matrix.valAt(coord));
  }

  @Test
  void setValAtUpdatesValue() {
    Matrix<Integer> matrix = new Matrix<>(0, 3, 4);
    MatrixCoordinates coord = new MatrixCoordinates(2, 2);
    matrix.setValAt(coord, 69);
    assertEquals(69, matrix.valAt(coord));
  }

  @Test
  void rowWiseStreamReturnsCorrectStream() {
    Matrix<Integer> matrix = new Matrix<>(0, 2, 2);
    matrix.setValAt(new MatrixCoordinates(0, 0), 1);
    matrix.setValAt(new MatrixCoordinates(0, 1), 2);
    matrix.setValAt(new MatrixCoordinates(1, 0), 3);
    matrix.setValAt(new MatrixCoordinates(1, 1), 4);
    Stream<Integer> stream = matrix.rowWiseStream();
    Integer[] expected = {1, 2, 3, 4};
    assertArrayEquals(expected, stream.toArray());
  }

  @Test
  void rowWiseIterReturnsCorrectIterator() {
    Matrix<Integer> matrix = new Matrix<>(0, 2, 2);
    matrix.setValAt(new MatrixCoordinates(0, 0), 1);
    matrix.setValAt(new MatrixCoordinates(0, 1), 2);
    matrix.setValAt(new MatrixCoordinates(1, 0), 3);
    matrix.setValAt(new MatrixCoordinates(1, 1), 4);
    Iterator<Integer> iter = matrix.rowWiseIter();
    assertTrue(iter.hasNext());
    assertEquals(1, iter.next());
    assertEquals(2, iter.next());
    assertEquals(3, iter.next());
    assertEquals(4, iter.next());
    assertFalse(iter.hasNext());
  }
}
