package battleship.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * <h6>Immutable Bidirectionally Indexed Table</h6>
 * This is a special case of bidirectional map.
 * <br><br>
 * This bi-map is less abstract,
 * because one of the 2 generic types is known to be {@link Integer}.
 * Thus, type {@link ImmutBiIdxTable}&lt;T&gt; ≈ {@code Bimap<Integer, T>}.
 * @param <T>
 */
public final class ImmutBiIdxTable<T> {

  // Instance fields
  private final List<T> table;
  private final Map<T, Integer> secondIndexer;

  // CRUD-C: Factory methods

  public static <T> ImmutBiIdxTable<T> fromSeq(final Stream<T> base) {
    return new ImmutBiIdxTable(base.toList());
  }

  public static <T> ImmutBiIdxTable<T> fromSeq(final List<T> base) {
    return new ImmutBiIdxTable(List.copyOf(base));
  }

  public static <T> ImmutBiIdxTable<T> fromSeq(final T[] base) {
    return new ImmutBiIdxTable(List.of(base));
  }

  // CRUD-C: Priv. constructors

  private ImmutBiIdxTable(final List<T> immutBaseTable) {
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

  // CRUD-R: Properties
  public boolean hasIdx(final int index){
    return 0 <= index && index < this.table.size();
  }
  public boolean hasVal(final T value){
    return this.secondIndexer.containsKey(value);
  }

  // CRUD-R: Indexers

  public T findVal(final int index) throws NoSuchElementException{
    return this.tryFindVal(index).orElseThrow(NoSuchElementException::new);
  }
  public int findIdx(final T value) throws NoSuchElementException{
    return this.tryFindIdx(value).orElseThrow(NoSuchElementException::new);
  }

  /**
   * @return value at the provided index
   */
  public Optional<T> tryFindVal(final int index) {
    try {
      return Optional.of(this.table.get(index));
    }catch (IndexOutOfBoundsException e){
      return Optional.empty();
    }
  }

  /**
   * @return index of the provided value
   */
  public OptionalInt tryFindIdx(final T value) {
    Integer index = this.secondIndexer.get(value);
    return (index == null)? OptionalInt.empty() : OptionalInt.of(index);
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
