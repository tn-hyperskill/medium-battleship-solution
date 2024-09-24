package battleship.util.matrix;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class MatrixCoordinatesTest {

  @Test
  void equalsWorks() {
    MatrixCoordinates coord1 = new MatrixCoordinates(1, 2);
    MatrixCoordinates coord2 = new MatrixCoordinates(1, 2);
    MatrixCoordinates coord3 = new MatrixCoordinates(2, 3);
    assertEquals(coord1, coord2);
    assertNotEquals(coord1, coord3);
  }

  @Test
  void hashCodeWorks() {
    MatrixCoordinates coord = new MatrixCoordinates(1, 2);
    int expectedHash = (1 & 0b10101010101010101010101010101010) |
        (2 & 0b01010101010101010101010101010101);
    assertEquals(expectedHash, coord.hashCode());
  }

  @Test
  void cloneWithRowChangesRow() {
    MatrixCoordinates coord = new MatrixCoordinates(1, 2);
    MatrixCoordinates clonedCoord = coord.cloneWithRow(3);
    assertEquals(3, clonedCoord.row);
    assertEquals(2, clonedCoord.col);
  }

  @Test
  void cloneWithColChangesCol() {
    MatrixCoordinates coord = new MatrixCoordinates(1, 2);
    MatrixCoordinates clonedCoord = coord.cloneWithCol(3);
    assertEquals(1, clonedCoord.row);
    assertEquals(3, clonedCoord.col);
  }

  @Test
  void mergeToMinimizeCordsWorks() {
    MatrixCoordinates coord1 = new MatrixCoordinates(5, 6);
    MatrixCoordinates coord2 = new MatrixCoordinates(3, 4);
    MatrixCoordinates min = coord1.mergeToMinimizeCords(coord2);
    assertEquals(3, min.row);
    assertEquals(4, min.col);
  }

  @Test
  void mergeToMaximizeCordsWorks() {
    MatrixCoordinates coord1 = new MatrixCoordinates(5, 6);
    MatrixCoordinates coord2 = new MatrixCoordinates(3, 4);
    MatrixCoordinates result = coord1.mergeToMaximizeCords(coord2);
    assertEquals(5, result.row);
    assertEquals(6, result.col);
  }

  @Test
  void mergeInheritsMixedFields() {
    MatrixCoordinates parent1 = new MatrixCoordinates(10, 1);
    MatrixCoordinates parent2 = new MatrixCoordinates(2, 20);
    MatrixCoordinates childMin = parent1.mergeToMinimizeCords(parent2);
    MatrixCoordinates childMax = parent1.mergeToMaximizeCords(parent2);

    // ChildMin should inherit min row from parent2 and min col from parent1
    assertEquals(2, childMin.row);
    assertEquals(1, childMin.col);

    // ChildMax should inherit max row from parent1 and max col from parent2
    assertEquals(10, childMax.row);
    assertEquals(20, childMax.col);
  }
}
