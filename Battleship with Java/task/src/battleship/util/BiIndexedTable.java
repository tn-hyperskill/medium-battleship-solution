package battleship.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * This is a special case of bidirectional map.
 * <br><br>
 * This bi-map is less abstract,
 * because one of the 2 generic types is known to be {@link Integer}.
 * Thus, type {@link BiIndexedTable}&lt;T&gt; ≈ {@code Bimap<Integer, T>}.
 * @param <T>
 */
public final class BiIndexedTable<T> {

  // Instance fields
  private final List<T> table;
  private final Map<T, Integer> secondIndexer;

  // CRUD-C: Factory methods

  public static <T> BiIndexedTable<T> fromSeq(final Stream<T> base) {
    return new BiIndexedTable(base.toList());
  }

  public static <T> BiIndexedTable<T> fromSeq(final List<T> base) {
    return new BiIndexedTable(List.copyOf(base));
  }

  public static <T> BiIndexedTable<T> fromSeq(final T[] base) {
    return new BiIndexedTable(List.of(base));
  }

  // CRUD-C: Priv. constructors

  private BiIndexedTable(final List<T> immutBaseTable) {
    // Declaring pre-field values
    var $secondIndexer = new HashMap<T, Integer>();
    // Initializing pre-field values
    IntStream.range(0, immutBaseTable.size())
        .forEach(curIdx -> {
          var currEl = immutBaseTable.get(curIdx);
          Integer oldIdx = $secondIndexer.put(currEl, curIdx);
          if (oldIdx != null) {
            throw new IllegalArgumentException(String.format(
                """
                    the provided array contains 2+ `Object.equal` values:
                    {%s, %s, ...} 
                    """,
                curIdx, oldIdx
            ));
          }
        });
    // Initializing fields.
    this.table = immutBaseTable;
    this.secondIndexer = Collections.unmodifiableMap($secondIndexer);
  }

  // CRUD-R: Indexers

  /**
   * @return value at the provided index
   */
  public T value(final int index) {
    return this.table.get(index);
  }

  /**
   * @return index of the provided value
   */
  public Integer index(final T value) {
    return this.secondIndexer.get(value);
  }

  // CRUD-R: Getters

  public int size() {
    return this.table.size();
  }

  // CRUD-R: Streams

  public Stream<T> valStream() {
    return this.table.stream();
  }

  public Iterator<T> valIter() {
    return this.valStream().iterator();
  }
}
