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
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;
import org.jooq.impl.SQLDataType;

/** This class is generated by jOOQ. */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class StCurvetoline extends AbstractRoutine<Geometry<?>> {

  private static final long serialVersionUID = 1L;

  /** The parameter <code>public.st_curvetoline.RETURN_VALUE</code>. */
  public static final Parameter<Geometry<?>> RETURN_VALUE =
      Internal.createParameter(
          "RETURN_VALUE", SQLDataType.OTHER, false, false, new GeolatteGeometryConverter());

  /** The parameter <code>public.st_curvetoline.geom</code>. */
  public static final Parameter<Geometry<?>> GEOM =
      Internal.createParameter(
          "geom", SQLDataType.OTHER, false, false, new GeolatteGeometryConverter());

  /** The parameter <code>public.st_curvetoline.tol</code>. */
  public static final Parameter<Double> TOL =
      Internal.createParameter(
          "tol",
          SQLDataType.DOUBLE.defaultValue(DSL.field(DSL.raw("32"), SQLDataType.DOUBLE)),
          true,
          false);

  /** The parameter <code>public.st_curvetoline.toltype</code>. */
  public static final Parameter<Integer> TOLTYPE =
      Internal.createParameter(
          "toltype",
          SQLDataType.INTEGER.defaultValue(DSL.field(DSL.raw("0"), SQLDataType.INTEGER)),
          true,
          false);

  /** The parameter <code>public.st_curvetoline.flags</code>. */
  public static final Parameter<Integer> FLAGS =
      Internal.createParameter(
          "flags",
          SQLDataType.INTEGER.defaultValue(DSL.field(DSL.raw("0"), SQLDataType.INTEGER)),
          true,
          false);

  /** Create a new routine call instance */
  public StCurvetoline() {
    super("st_curvetoline", Public.PUBLIC, SQLDataType.OTHER, new GeolatteGeometryConverter());

    setReturnParameter(RETURN_VALUE);
    addInParameter(GEOM);
    addInParameter(TOL);
    addInParameter(TOLTYPE);
    addInParameter(FLAGS);
  }

  /** Set the <code>geom</code> parameter IN value to the routine */
  public void setGeom(Geometry<?> value) {
    setValue(GEOM, value);
  }

  /**
   * Set the <code>geom</code> parameter to the function to be used with a {@link org.jooq.Select}
   * statement
   */
  public void setGeom(Field<Geometry<?>> field) {
    setField(GEOM, field);
  }

  /** Set the <code>tol</code> parameter IN value to the routine */
  public void setTol(Double value) {
    setValue(TOL, value);
  }

  /**
   * Set the <code>tol</code> parameter to the function to be used with a {@link org.jooq.Select}
   * statement
   */
  public void setTol(Field<Double> field) {
    setField(TOL, field);
  }

  /** Set the <code>toltype</code> parameter IN value to the routine */
  public void setToltype(Integer value) {
    setValue(TOLTYPE, value);
  }

  /**
   * Set the <code>toltype</code> parameter to the function to be used with a {@link
   * org.jooq.Select} statement
   */
  public void setToltype(Field<Integer> field) {
    setField(TOLTYPE, field);
  }

  /** Set the <code>flags</code> parameter IN value to the routine */
  public void setFlags(Integer value) {
    setValue(FLAGS, value);
  }

  /**
   * Set the <code>flags</code> parameter to the function to be used with a {@link org.jooq.Select}
   * statement
   */
  public void setFlags(Field<Integer> field) {
    setField(FLAGS, field);
  }
}
