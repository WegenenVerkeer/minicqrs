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
public class StNpoints extends AbstractRoutine<Integer> {

  private static final long serialVersionUID = 1L;

  /** The parameter <code>public.st_npoints.RETURN_VALUE</code>. */
  public static final Parameter<Integer> RETURN_VALUE =
      Internal.createParameter("RETURN_VALUE", SQLDataType.INTEGER, false, false);

  /** The parameter <code>public.st_npoints._1</code>. */
  public static final Parameter<Geometry<?>> _1 =
      Internal.createParameter(
          "_1", SQLDataType.OTHER, false, true, new GeolatteGeometryConverter());

  /** Create a new routine call instance */
  public StNpoints() {
    super("st_npoints", Public.PUBLIC, SQLDataType.INTEGER);

    setReturnParameter(RETURN_VALUE);
    addInParameter(_1);
  }

  /** Set the <code>_1</code> parameter IN value to the routine */
  public void set__1(Geometry<?> value) {
    setValue(_1, value);
  }

  /**
   * Set the <code>_1</code> parameter to the function to be used with a {@link org.jooq.Select}
   * statement
   */
  public void set__1(Field<Geometry<?>> field) {
    setField(_1, field);
  }
}
