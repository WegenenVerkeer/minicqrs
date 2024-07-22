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
public class StCoorddim extends AbstractRoutine<Short> {

  private static final long serialVersionUID = 1L;

  /** The parameter <code>public.st_coorddim.RETURN_VALUE</code>. */
  public static final Parameter<Short> RETURN_VALUE =
      Internal.createParameter("RETURN_VALUE", SQLDataType.SMALLINT, false, false);

  /** The parameter <code>public.st_coorddim.geometry</code>. */
  public static final Parameter<Geometry<?>> GEOMETRY =
      Internal.createParameter(
          "geometry", SQLDataType.OTHER, false, false, new GeolatteGeometryConverter());

  /** Create a new routine call instance */
  public StCoorddim() {
    super("st_coorddim", Public.PUBLIC, SQLDataType.SMALLINT);

    setReturnParameter(RETURN_VALUE);
    addInParameter(GEOMETRY);
  }

  /** Set the <code>geometry</code> parameter IN value to the routine */
  public void setGeometry(Geometry<?> value) {
    setValue(GEOMETRY, value);
  }

  /**
   * Set the <code>geometry</code> parameter to the function to be used with a {@link
   * org.jooq.Select} statement
   */
  public void setGeometry(Field<Geometry<?>> field) {
    setField(GEOMETRY, field);
  }
}