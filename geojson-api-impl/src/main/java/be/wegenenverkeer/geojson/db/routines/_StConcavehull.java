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
public class _StConcavehull extends AbstractRoutine<Geometry<?>> {

  private static final long serialVersionUID = 1L;

  /** The parameter <code>public._st_concavehull.RETURN_VALUE</code>. */
  public static final Parameter<Geometry<?>> RETURN_VALUE =
      Internal.createParameter(
          "RETURN_VALUE", SQLDataType.OTHER, false, false, new GeolatteGeometryConverter());

  /** The parameter <code>public._st_concavehull.param_inputgeom</code>. */
  public static final Parameter<Geometry<?>> PARAM_INPUTGEOM =
      Internal.createParameter(
          "param_inputgeom", SQLDataType.OTHER, false, false, new GeolatteGeometryConverter());

  /** Create a new routine call instance */
  public _StConcavehull() {
    super("_st_concavehull", Public.PUBLIC, SQLDataType.OTHER, new GeolatteGeometryConverter());

    setReturnParameter(RETURN_VALUE);
    addInParameter(PARAM_INPUTGEOM);
  }

  /** Set the <code>param_inputgeom</code> parameter IN value to the routine */
  public void setParamInputgeom(Geometry<?> value) {
    setValue(PARAM_INPUTGEOM, value);
  }

  /**
   * Set the <code>param_inputgeom</code> parameter to the function to be used with a {@link
   * org.jooq.Select} statement
   */
  public void setParamInputgeom(Field<Geometry<?>> field) {
    setField(PARAM_INPUTGEOM, field);
  }
}