package battleship.ship.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class ShipSizeTest {

  @Test
  void fromVolumeReturnsCorrectShipSize() {
    assertEquals(ShipSize.TINY, ShipSize.fromVolume(2));
    assertEquals(ShipSize.MEDIUM, ShipSize.fromVolume(3));
    assertEquals(ShipSize.BIG, ShipSize.fromVolume(4));
    assertEquals(ShipSize.LARGE, ShipSize.fromVolume(5));
  }

  @Test
  void fromVolumeThrowsExceptionForInvalidVolume() {
    assertThrows(IllegalArgumentException.class, () -> ShipSize.fromVolume(1));
  }

  @Test
  void constructorThrowsExceptionForZeroVolume() {
    assertThrows(IllegalArgumentException.class, () -> ShipSize.fromVolume(0));
  }

  @Test
  void toStringReturnsCorrectStringFormat() {
    assertEquals("Tiny(2)", ShipSize.TINY.toString());
    assertEquals("Medium(3)", ShipSize.MEDIUM.toString());
    assertEquals("Big(4)", ShipSize.BIG.toString());
    assertEquals("Large(5)", ShipSize.LARGE.toString());
  }
}
