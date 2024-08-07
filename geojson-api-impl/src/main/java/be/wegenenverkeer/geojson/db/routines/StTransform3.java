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
public class StTransform3 extends AbstractRoutine<Geometry<?>> {

  private static final long serialVersionUID = 1L;

  /** The parameter <code>public.st_transform.RETURN_VALUE</code>. */
  public static final Parameter<Geometry<?>> RETURN_VALUE =
      Internal.createParameter(
          "RETURN_VALUE", SQLDataType.OTHER, false, false, new GeolatteGeometryConverter());

  /** The parameter <code>public.st_transform.geom</code>. */
  public static final Parameter<Geometry<?>> GEOM =
      Internal.createParameter(
          "geom", SQLDataType.OTHER, false, false, new GeolatteGeometryConverter());

  /** The parameter <code>public.st_transform.from_proj</code>. */
  public static final Parameter<String> FROM_PROJ =
      Internal.createParameter("from_proj", SQLDataType.CLOB, false, false);

  /** The parameter <code>public.st_transform.to_proj</code>. */
  public static final Parameter<String> TO_PROJ =
      Internal.createParameter("to_proj", SQLDataType.CLOB, false, false);

  /** Create a new routine call instance */
  public StTransform3() {
    super("st_transform", Public.PUBLIC, SQLDataType.OTHER, new GeolatteGeometryConverter());

    setReturnParameter(RETURN_VALUE);
    addInParameter(GEOM);
    addInParameter(FROM_PROJ);
    addInParameter(TO_PROJ);
    setOverloaded(true);
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

  /** Set the <code>from_proj</code> parameter IN value to the routine */
  public void setFromProj(String value) {
    setValue(FROM_PROJ, value);
  }

  /**
   * Set the <code>from_proj</code> parameter to the function to be used with a {@link
   * org.jooq.Select} statement
   */
  public void setFromProj(Field<String> field) {
    setField(FROM_PROJ, field);
  }

  /** Set the <code>to_proj</code> parameter IN value to the routine */
  public void setToProj(String value) {
    setValue(TO_PROJ, value);
  }

  /**
   * Set the <code>to_proj</code> parameter to the function to be used with a {@link
   * org.jooq.Select} statement
   */
  public void setToProj(Field<String> field) {
    setField(TO_PROJ, field);
  }
}
