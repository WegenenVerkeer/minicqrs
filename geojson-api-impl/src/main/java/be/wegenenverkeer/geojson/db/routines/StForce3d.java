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
public class StForce3d extends AbstractRoutine<Geometry<?>> {

  private static final long serialVersionUID = 1L;

  /** The parameter <code>public.st_force3d.RETURN_VALUE</code>. */
  public static final Parameter<Geometry<?>> RETURN_VALUE =
      Internal.createParameter(
          "RETURN_VALUE", SQLDataType.OTHER, false, false, new GeolatteGeometryConverter());

  /** The parameter <code>public.st_force3d.geom</code>. */
  public static final Parameter<Geometry<?>> GEOM =
      Internal.createParameter(
          "geom", SQLDataType.OTHER, false, false, new GeolatteGeometryConverter());

  /** The parameter <code>public.st_force3d.zvalue</code>. */
  public static final Parameter<Double> ZVALUE =
      Internal.createParameter(
          "zvalue",
          SQLDataType.DOUBLE.defaultValue(DSL.field(DSL.raw("0.0"), SQLDataType.DOUBLE)),
          true,
          false);

  /** Create a new routine call instance */
  public StForce3d() {
    super("st_force3d", Public.PUBLIC, SQLDataType.OTHER, new GeolatteGeometryConverter());

    setReturnParameter(RETURN_VALUE);
    addInParameter(GEOM);
    addInParameter(ZVALUE);
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

  /** Set the <code>zvalue</code> parameter IN value to the routine */
  public void setZvalue(Double value) {
    setValue(ZVALUE, value);
  }

  /**
   * Set the <code>zvalue</code> parameter to the function to be used with a {@link org.jooq.Select}
   * statement
   */
  public void setZvalue(Field<Double> field) {
    setField(ZVALUE, field);
  }
}
