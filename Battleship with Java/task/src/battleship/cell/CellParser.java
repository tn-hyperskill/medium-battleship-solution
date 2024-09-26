package battleship.cell;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

abstract class CellParser {

  private static Map<Character, Cell> variantDict = null;
  private static final Object lock = new Object();

  // CRUD-C

  protected CellParser() {
  }

  // CRUD-R

  public static Cell parse(final char c) {
    var ret = variantDict().get(c);
    if (ret == null) {
      throw new NoSuchElementException();
    } else {
      return ret;
    }
  }

  public static Map<Character, Cell> variantDict() {
    synchronized (lock) {
      if (variantDict == null) {
        variantDict =
            Collections.unmodifiableMap(constructModifiableVariantDict());
      }
    }
    return variantDict;
  }

  private static Map<Character, Cell> constructModifiableVariantDict() {
    var dict = new HashMap<Character, Cell>();
    for (var variant : ShipCell.variants()) {
      dict.put(variant.symbol, variant);
    }
    return dict;
  }
}
