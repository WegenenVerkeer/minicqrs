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
public class StInterpolatepoint extends AbstractRoutine<Double> {

  private static final long serialVersionUID = 1L;

  /** The parameter <code>public.st_interpolatepoint.RETURN_VALUE</code>. */
  public static final Parameter<Double> RETURN_VALUE =
      Internal.createParameter("RETURN_VALUE", SQLDataType.DOUBLE, false, false);

  /** The parameter <code>public.st_interpolatepoint.line</code>. */
  public static final Parameter<Geometry<?>> LINE =
      Internal.createParameter(
          "line", SQLDataType.OTHER, false, false, new GeolatteGeometryConverter());

  /** The parameter <code>public.st_interpolatepoint.point</code>. */
  public static final Parameter<Geometry<?>> POINT =
      Internal.createParameter(
          "point", SQLDataType.OTHER, false, false, new GeolatteGeometryConverter());

  /** Create a new routine call instance */
  public StInterpolatepoint() {
    super("st_interpolatepoint", Public.PUBLIC, SQLDataType.DOUBLE);

    setReturnParameter(RETURN_VALUE);
    addInParameter(LINE);
    addInParameter(POINT);
  }

  /** Set the <code>line</code> parameter IN value to the routine */
  public void setLine(Geometry<?> value) {
    setValue(LINE, value);
  }

  /**
   * Set the <code>line</code> parameter to the function to be used with a {@link org.jooq.Select}
   * statement
   */
  public void setLine(Field<Geometry<?>> field) {
    setField(LINE, field);
  }

  /** Set the <code>point</code> parameter IN value to the routine */
  public void setPoint(Geometry<?> value) {
    setValue(POINT, value);
  }

  /**
   * Set the <code>point</code> parameter to the function to be used with a {@link org.jooq.Select}
   * statement
   */
  public void setPoint(Field<Geometry<?>> field) {
    setField(POINT, field);
  }
}
