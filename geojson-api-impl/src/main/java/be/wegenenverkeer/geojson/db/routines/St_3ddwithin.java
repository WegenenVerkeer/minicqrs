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
public class St_3ddwithin extends AbstractRoutine<Boolean> {

  private static final long serialVersionUID = 1L;

  /** The parameter <code>public.st_3ddwithin.RETURN_VALUE</code>. */
  public static final Parameter<Boolean> RETURN_VALUE =
      Internal.createParameter("RETURN_VALUE", SQLDataType.BOOLEAN, false, false);

  /** The parameter <code>public.st_3ddwithin.geom1</code>. */
  public static final Parameter<Geometry<?>> GEOM1 =
      Internal.createParameter(
          "geom1", SQLDataType.OTHER, false, false, new GeolatteGeometryConverter());

  /** The parameter <code>public.st_3ddwithin.geom2</code>. */
  public static final Parameter<Geometry<?>> GEOM2 =
      Internal.createParameter(
          "geom2", SQLDataType.OTHER, false, false, new GeolatteGeometryConverter());

  /** The parameter <code>public.st_3ddwithin._3</code>. */
  public static final Parameter<Double> _3 =
      Internal.createParameter("_3", SQLDataType.DOUBLE, false, true);

  /** Create a new routine call instance */
  public St_3ddwithin() {
    super("st_3ddwithin", Public.PUBLIC, SQLDataType.BOOLEAN);

    setReturnParameter(RETURN_VALUE);
    addInParameter(GEOM1);
    addInParameter(GEOM2);
    addInParameter(_3);
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
}
