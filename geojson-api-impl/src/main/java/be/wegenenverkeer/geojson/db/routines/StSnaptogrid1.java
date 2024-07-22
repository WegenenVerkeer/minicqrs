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
public class StSnaptogrid1 extends AbstractRoutine<Geometry<?>> {

  private static final long serialVersionUID = 1L;

  /** The parameter <code>public.st_snaptogrid.RETURN_VALUE</code>. */
  public static final Parameter<Geometry<?>> RETURN_VALUE =
      Internal.createParameter(
          "RETURN_VALUE", SQLDataType.OTHER, false, false, new GeolatteGeometryConverter());

  /** The parameter <code>public.st_snaptogrid._1</code>. */
  public static final Parameter<Geometry<?>> _1 =
      Internal.createParameter(
          "_1", SQLDataType.OTHER, false, true, new GeolatteGeometryConverter());

  /** The parameter <code>public.st_snaptogrid._2</code>. */
  public static final Parameter<Double> _2 =
      Internal.createParameter("_2", SQLDataType.DOUBLE, false, true);

  /** The parameter <code>public.st_snaptogrid._3</code>. */
  public static final Parameter<Double> _3 =
      Internal.createParameter("_3", SQLDataType.DOUBLE, false, true);

  /** The parameter <code>public.st_snaptogrid._4</code>. */
  public static final Parameter<Double> _4 =
      Internal.createParameter("_4", SQLDataType.DOUBLE, false, true);

  /** The parameter <code>public.st_snaptogrid._5</code>. */
  public static final Parameter<Double> _5 =
      Internal.createParameter("_5", SQLDataType.DOUBLE, false, true);

  /** Create a new routine call instance */
  public StSnaptogrid1() {
    super("st_snaptogrid", Public.PUBLIC, SQLDataType.OTHER, new GeolatteGeometryConverter());

    setReturnParameter(RETURN_VALUE);
    addInParameter(_1);
    addInParameter(_2);
    addInParameter(_3);
    addInParameter(_4);
    addInParameter(_5);
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
  public void set__3(Double value) {
    setValue(_3, value);
  }

  /**
   * Set the <code>_3</code> parameter to the function to be used with a {@link org.jooq.Select}
   * statement
   */
  public void set__3(Field<Double> field) {
    setField(_3, field);
  }

  /** Set the <code>_4</code> parameter IN value to the routine */
  public void set__4(Double value) {
    setValue(_4, value);
  }

  /**
   * Set the <code>_4</code> parameter to the function to be used with a {@link org.jooq.Select}
   * statement
   */
  public void set__4(Field<Double> field) {
    setField(_4, field);
  }

  /** Set the <code>_5</code> parameter IN value to the routine */
  public void set__5(Double value) {
    setValue(_5, value);
  }

  /**
   * Set the <code>_5</code> parameter to the function to be used with a {@link org.jooq.Select}
   * statement
   */
  public void set__5(Field<Double> field) {
    setField(_5, field);
  }
}
