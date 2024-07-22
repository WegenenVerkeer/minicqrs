/*
 * This file is generated by jOOQ.
 */
package be.wegenenverkeer.geojson.db.routines;

import be.wegenenverkeer.geojson.db.Public;
import be.wegenenverkeer.geojson.util.GeolatteGeometryConverter;
import org.geolatte.geom.Geometry;
import org.jooq.Field;
import org.jooq.Parameter;
import org.jooq.impl.AbstractRoutine;
import org.jooq.impl.Internal;
import org.jooq.impl.SQLDataType;

/** This class is generated by jOOQ. */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class _StLinecrossingdirection extends AbstractRoutine<Integer> {

  private static final long serialVersionUID = 1L;

  /** The parameter <code>public._st_linecrossingdirection.RETURN_VALUE</code>. */
  public static final Parameter<Integer> RETURN_VALUE =
      Internal.createParameter("RETURN_VALUE", SQLDataType.INTEGER, false, false);

  /** The parameter <code>public._st_linecrossingdirection.line1</code>. */
  public static final Parameter<Geometry<?>> LINE1 =
      Internal.createParameter(
          "line1", SQLDataType.OTHER, false, false, new GeolatteGeometryConverter());

  /** The parameter <code>public._st_linecrossingdirection.line2</code>. */
  public static final Parameter<Geometry<?>> LINE2 =
      Internal.createParameter(
          "line2", SQLDataType.OTHER, false, false, new GeolatteGeometryConverter());

  /** Create a new routine call instance */
  public _StLinecrossingdirection() {
    super("_st_linecrossingdirection", Public.PUBLIC, SQLDataType.INTEGER);

    setReturnParameter(RETURN_VALUE);
    addInParameter(LINE1);
    addInParameter(LINE2);
  }

  /** Set the <code>line1</code> parameter IN value to the routine */
  public void setLine1(Geometry<?> value) {
    setValue(LINE1, value);
  }

  /**
   * Set the <code>line1</code> parameter to the function to be used with a {@link org.jooq.Select}
   * statement
   */
  public void setLine1(Field<Geometry<?>> field) {
    setField(LINE1, field);
  }

  /** Set the <code>line2</code> parameter IN value to the routine */
  public void setLine2(Geometry<?> value) {
    setValue(LINE2, value);
  }

  /**
   * Set the <code>line2</code> parameter to the function to be used with a {@link org.jooq.Select}
   * statement
   */
  public void setLine2(Field<Geometry<?>> field) {
    setField(LINE2, field);
  }
}
