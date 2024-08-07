/*
 * This file is generated by jOOQ.
 */
package be.wegenenverkeer.geojson.db.tables.records;

import be.wegenenverkeer.geojson.db.tables.StSubdivide;
import org.geolatte.geom.Geometry;
import org.jooq.impl.TableRecordImpl;

/** This class is generated by jOOQ. */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class StSubdivideRecord extends TableRecordImpl<StSubdivideRecord> {

  private static final long serialVersionUID = 1L;

  /** Setter for <code>public.st_subdivide.st_subdivide</code>. */
  public void setStSubdivide(Geometry<?> value) {
    set(0, value);
  }

  /** Getter for <code>public.st_subdivide.st_subdivide</code>. */
  public Geometry<?> getStSubdivide() {
    return (Geometry<?>) get(0);
  }

  // -------------------------------------------------------------------------
  // Constructors
  // -------------------------------------------------------------------------

  /** Create a detached StSubdivideRecord */
  public StSubdivideRecord() {
    super(StSubdivide.ST_SUBDIVIDE);
  }

  /** Create a detached, initialised StSubdivideRecord */
  public StSubdivideRecord(Geometry<?> stSubdivide) {
    super(StSubdivide.ST_SUBDIVIDE);

    setStSubdivide(stSubdivide);
    resetChangedOnNotNull();
  }
}
