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
public class StBoundingdiagonal extends AbstractRoutine<Geometry<?>> {

  private static final long serialVersionUID = 1L;

  /** The parameter <code>public.st_boundingdiagonal.RETURN_VALUE</code>. */
  public static final Parameter<Geometry<?>> RETURN_VALUE =
      Internal.createParameter(
          "RETURN_VALUE", SQLDataType.OTHER, false, false, new GeolatteGeometryConverter());

  /** The parameter <code>public.st_boundingdiagonal.geom</code>. */
  public static final Parameter<Geometry<?>> GEOM =
      Internal.createParameter(
          "geom", SQLDataType.OTHER, false, false, new GeolatteGeometryConverter());

  /** The parameter <code>public.st_boundingdiagonal.fits</code>. */
  public static final Parameter<Boolean> FITS =
      Internal.createParameter(
          "fits",
          SQLDataType.BOOLEAN.defaultValue(DSL.field(DSL.raw("false"), SQLDataType.BOOLEAN)),
          true,
          false);

  /** Create a new routine call instance */
  public StBoundingdiagonal() {
    super("st_boundingdiagonal", Public.PUBLIC, SQLDataType.OTHER, new GeolatteGeometryConverter());

    setReturnParameter(RETURN_VALUE);
    addInParameter(GEOM);
    addInParameter(FITS);
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

  /** Set the <code>fits</code> parameter IN value to the routine */
  public void setFits(Boolean value) {
    setValue(FITS, value);
  }

  /**
   * Set the <code>fits</code> parameter to the function to be used with a {@link org.jooq.Select}
   * statement
   */
  public void setFits(Field<Boolean> field) {
    setField(FITS, field);
  }
}
