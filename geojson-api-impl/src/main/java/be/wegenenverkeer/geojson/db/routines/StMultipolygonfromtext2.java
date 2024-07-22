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
public class StMultipolygonfromtext2 extends AbstractRoutine<Geometry<?>> {

  private static final long serialVersionUID = 1L;

  /** The parameter <code>public.st_multipolygonfromtext.RETURN_VALUE</code>. */
  public static final Parameter<Geometry<?>> RETURN_VALUE =
      Internal.createParameter(
          "RETURN_VALUE", SQLDataType.OTHER, false, false, new GeolatteGeometryConverter());

  /** The parameter <code>public.st_multipolygonfromtext._1</code>. */
  public static final Parameter<String> _1 =
      Internal.createParameter("_1", SQLDataType.CLOB, false, true);

  /** Create a new routine call instance */
  public StMultipolygonfromtext2() {
    super(
        "st_multipolygonfromtext",
        Public.PUBLIC,
        SQLDataType.OTHER,
        new GeolatteGeometryConverter());

    setReturnParameter(RETURN_VALUE);
    addInParameter(_1);
    setOverloaded(true);
  }

  /** Set the <code>_1</code> parameter IN value to the routine */
  public void set__1(String value) {
    setValue(_1, value);
  }

  /**
   * Set the <code>_1</code> parameter to the function to be used with a {@link org.jooq.Select}
   * statement
   */
  public void set__1(Field<String> field) {
    setField(_1, field);
  }
}
