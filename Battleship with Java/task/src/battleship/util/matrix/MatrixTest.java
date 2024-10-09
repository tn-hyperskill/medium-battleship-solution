package battleship.util.matrix;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

class MatrixTest {

  @Test
  void constructorSetsDimensions() {
    Matrix<Integer> matrix = new Matrix<>(0, 3, 4);
    assertEquals(3, matrix.height());
    assertEquals(4, matrix.width());
  }

  @Test
  void calcCellIdValid() {
    Matrix<Integer> matrix = new Matrix<>(0, 3, 4);
    MatrixCoordinates coord =
        MatrixCoordinates.builder().row(2).column(3).build();
    assertEquals(0, matrix.valAt(coord));
  }

  @Test
  void calcCellIdInvalidRow() {
    Matrix<Integer> matrix = new Matrix<>(0, 3, 4);
    MatrixCoordinates invalidCoord =
        MatrixCoordinates.builder().row(5).column(2).build();
    assertThrows(IndexOutOfBoundsException.class,
        () -> matrix.valAt(invalidCoord));
  }

  @Test
  void calcCellIdInvalidCol() {
    Matrix<Integer> matrix = new Matrix<>(0, 3, 4);
    MatrixCoordinates invalidCoord =
        MatrixCoordinates.builder().row(2).column(5).build();
    assertThrows(IndexOutOfBoundsException.class,
        () -> matrix.valAt(invalidCoord));
  }

  @Test
  void valAt() {
    var base = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
    var matrix = new Matrix<>(base, 3, 4);
    MatrixCoordinates coord =
        MatrixCoordinates.builder().row(1).column(2).build();
    assertEquals(7, matrix.valAt(coord));
  }

  @Test
  void setValAt() {
    Matrix<Integer> matrix = new Matrix<>(0, 3, 4);
    MatrixCoordinates coord =
        MatrixCoordinates.builder().row(1).column(2).build();
    matrix.setValAt(coord, 99);
    assertEquals(99, matrix.valAt(coord));
  }

  @Test
  void rowWiseStream() {
    var base = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
    var matrix = new Matrix<>(base, 3, 4);
    var stream = matrix.rowWiseStream();
    Integer[] expected = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
    assertArrayEquals(expected, stream.toArray());
  }

  @Test
  @SuppressWarnings("PMD.JUnitTestContainsTooManyAsserts")
  void rowWiseIter() {
    var base = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
    var matrix = new Matrix<>(base, 3, 4);
    var iter = matrix.rowWiseIter();
    assertTrue(iter.hasNext());
    assertEquals(1, iter.next());
    assertEquals(2, iter.next());
    assertEquals(3, iter.next());
    for (int i = 0; i < 8; i++) {
      iter.next();
    }
    assertEquals(12, iter.next());
    assertFalse(iter.hasNext());
  }

  @Test
  void integrationSetValAtRowWiseStream() {
    Matrix<Integer> matrix = new Matrix<>(0, 3, 4);
    matrix.setValAt(MatrixCoordinates.builder()
            .row(0).column(1).build(),
        10);
    matrix.setValAt(MatrixCoordinates.builder()
            .row(1).column(1).build(),
        20);
    matrix.setValAt(MatrixCoordinates.builder()
            .row(2).column(2).build(),
        30);

    Integer[] expected = {0, 10, 0, 0, 0, 20, 0, 0, 0, 0, 30, 0};
    Stream<Integer> stream = matrix.rowWiseStream();
    assertArrayEquals(expected, stream.toArray());
  }
}
