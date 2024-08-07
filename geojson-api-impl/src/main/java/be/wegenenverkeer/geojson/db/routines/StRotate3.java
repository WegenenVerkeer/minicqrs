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
public class StRotate3 extends AbstractRoutine<Geometry<?>> {

  private static final long serialVersionUID = 1L;

  /** The parameter <code>public.st_rotate.RETURN_VALUE</code>. */
  public static final Parameter<Geometry<?>> RETURN_VALUE =
      Internal.createParameter(
          "RETURN_VALUE", SQLDataType.OTHER, false, false, new GeolatteGeometryConverter());

  /** The parameter <code>public.st_rotate._1</code>. */
  public static final Parameter<Geometry<?>> _1 =
      Internal.createParameter(
          "_1", SQLDataType.OTHER, false, true, new GeolatteGeometryConverter());

  /** The parameter <code>public.st_rotate._2</code>. */
  public static final Parameter<Double> _2 =
      Internal.createParameter("_2", SQLDataType.DOUBLE, false, true);

  /** The parameter <code>public.st_rotate._3</code>. */
  public static final Parameter<Geometry<?>> _3 =
      Internal.createParameter(
          "_3", SQLDataType.OTHER, false, true, new GeolatteGeometryConverter());

  /** Create a new routine call instance */
  public StRotate3() {
    super("st_rotate", Public.PUBLIC, SQLDataType.OTHER, new GeolatteGeometryConverter());

    setReturnParameter(RETURN_VALUE);
    addInParameter(_1);
    addInParameter(_2);
    addInParameter(_3);
    setOverloaded(true);
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

  /** Set the <code>_2</code> parameter IN value to the routine */
  public void set__2(Double value) {
    setValue(_2, value);
  }

  /**
   * Set the <code>_2</code> parameter to the function to be used with a {@link org.jooq.Select}
   * statement
   */
  public void set__2(Field<Double> field) {
    setField(_2, field);
  }

  /** Set the <code>_3</code> parameter IN value to the routine */
  public void set__3(Geometry<?> value) {
    setValue(_3, value);
  }

  /**
   * Set the <code>_3</code> parameter to the function to be used with a {@link org.jooq.Select}
   * statement
   */
  public void set__3(Field<Geometry<?>> field) {
    setField(_3, field);
  }
}
