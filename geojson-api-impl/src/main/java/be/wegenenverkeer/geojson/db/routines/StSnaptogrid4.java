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
public class StSnaptogrid4 extends AbstractRoutine<Geometry<?>> {

  private static final long serialVersionUID = 1L;

  /** The parameter <code>public.st_snaptogrid.RETURN_VALUE</code>. */
  public static final Parameter<Geometry<?>> RETURN_VALUE =
      Internal.createParameter(
          "RETURN_VALUE", SQLDataType.OTHER, false, false, new GeolatteGeometryConverter());

  /** The parameter <code>public.st_snaptogrid.geom1</code>. */
  public static final Parameter<Geometry<?>> GEOM1 =
      Internal.createParameter(
          "geom1", SQLDataType.OTHER, false, false, new GeolatteGeometryConverter());

  /** The parameter <code>public.st_snaptogrid.geom2</code>. */
  public static final Parameter<Geometry<?>> GEOM2 =
      Internal.createParameter(
          "geom2", SQLDataType.OTHER, false, false, new GeolatteGeometryConverter());

  /** The parameter <code>public.st_snaptogrid._3</code>. */
  public static final Parameter<Double> _3 =
      Internal.createParameter("_3", SQLDataType.DOUBLE, false, true);

  /** The parameter <code>public.st_snaptogrid._4</code>. */
  public static final Parameter<Double> _4 =
      Internal.createParameter("_4", SQLDataType.DOUBLE, false, true);

  /** The parameter <code>public.st_snaptogrid._5</code>. */
  public static final Parameter<Double> _5 =
      Internal.createParameter("_5", SQLDataType.DOUBLE, false, true);

  /** The parameter <code>public.st_snaptogrid._6</code>. */
  public static final Parameter<Double> _6 =
      Internal.createParameter("_6", SQLDataType.DOUBLE, false, true);

  /** Create a new routine call instance */
  public StSnaptogrid4() {
    super("st_snaptogrid", Public.PUBLIC, SQLDataType.OTHER, new GeolatteGeometryConverter());

    setReturnParameter(RETURN_VALUE);
    addInParameter(GEOM1);
    addInParameter(GEOM2);
    addInParameter(_3);
    addInParameter(_4);
    addInParameter(_5);
    addInParameter(_6);
    setOverloaded(true);
  }

  /** Set the <code>geom1</code> parameter IN value to the routine */
  public void setGeom1(Geometry<?> value) {
    setValue(GEOM1, value);
  }

  /**
   * Set the <code>geom1</code> parameter to the function to be used with a {@link org.jooq.Select}
   * statement
   */
  public void setGeom1(Field<Geometry<?>> field) {
    setField(GEOM1, field);
  }

  /** Set the <code>geom2</code> parameter IN value to the routine */
  public void setGeom2(Geometry<?> value) {
    setValue(GEOM2, value);
  }

  /**
   * Set the <code>geom2</code> parameter to the function to be used with a {@link org.jooq.Select}
   * statement
   */
  public void setGeom2(Field<Geometry<?>> field) {
    setField(GEOM2, field);
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

  /** Set the <code>_6</code> parameter IN value to the routine */
  public void set__6(Double value) {
    setValue(_6, value);
  }

  /**
   * Set the <code>_6</code> parameter to the function to be used with a {@link org.jooq.Select}
   * statement
   */
  public void set__6(Field<Double> field) {
    setField(_6, field);
  }
}
