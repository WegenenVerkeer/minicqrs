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
public class StSharedpaths extends AbstractRoutine<Geometry<?>> {

  private static final long serialVersionUID = 1L;

  /** The parameter <code>public.st_sharedpaths.RETURN_VALUE</code>. */
  public static final Parameter<Geometry<?>> RETURN_VALUE =
      Internal.createParameter(
          "RETURN_VALUE", SQLDataType.OTHER, false, false, new GeolatteGeometryConverter());

  /** The parameter <code>public.st_sharedpaths.geom1</code>. */
  public static final Parameter<Geometry<?>> GEOM1 =
      Internal.createParameter(
          "geom1", SQLDataType.OTHER, false, false, new GeolatteGeometryConverter());

  /** The parameter <code>public.st_sharedpaths.geom2</code>. */
  public static final Parameter<Geometry<?>> GEOM2 =
      Internal.createParameter(
          "geom2", SQLDataType.OTHER, false, false, new GeolatteGeometryConverter());

  /** Create a new routine call instance */
  public StSharedpaths() {
    super("st_sharedpaths", Public.PUBLIC, SQLDataType.OTHER, new GeolatteGeometryConverter());

    setReturnParameter(RETURN_VALUE);
    addInParameter(GEOM1);
    addInParameter(GEOM2);
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
}
