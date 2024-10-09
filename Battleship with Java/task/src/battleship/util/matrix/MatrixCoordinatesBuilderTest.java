package battleship.util.matrix;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class MatrixCoordinatesBuilderTest {

  @Test
  void buildCreatesCorrectMatrixCoordinates() {
    MatrixCoordinates coord = MatrixCoordinates.builder()
        .row(1)
        .column(2)
        .build();
    assertEquals(1, coord.row);
    assertEquals(2, coord.col);
  }

  @Test
  void buildThrowsIfBothFieldsNotSet() {
    MatrixCoordinatesBuilder builder = MatrixCoordinates.builder();
    var exception = assertThrows(RuntimeException.class, builder::build);
    assertEquals("not all fields have been initialized",
        exception.getMessage());
  }

  @Test
  void onlyRowIsNotEnough() {
    MatrixCoordinatesBuilder builder = MatrixCoordinates.builder();
    builder.row(3);
    var exception = assertThrows(RuntimeException.class, builder::build);
    assertEquals("not all fields have been initialized",
        exception.getMessage());
  }

  @Test
  void onlyColIsNotEnough() {
    MatrixCoordinatesBuilder builder = MatrixCoordinates.builder();
    builder.column(4);
    var exception = assertThrows(RuntimeException.class, builder::build);
    assertEquals("not all fields have been initialized",
        exception.getMessage());
  }
}
