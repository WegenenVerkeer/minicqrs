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
public class StAsewkb2 extends AbstractRoutine<byte[]> {

  private static final long serialVersionUID = 1L;

  /** The parameter <code>public.st_asewkb.RETURN_VALUE</code>. */
  public static final Parameter<byte[]> RETURN_VALUE =
      Internal.createParameter("RETURN_VALUE", SQLDataType.BLOB, false, false);

  /** The parameter <code>public.st_asewkb._1</code>. */
  public static final Parameter<Geometry<?>> _1 =
      Internal.createParameter(
          "_1", SQLDataType.OTHER, false, true, new GeolatteGeometryConverter());

  /** The parameter <code>public.st_asewkb._2</code>. */
  public static final Parameter<String> _2 =
      Internal.createParameter("_2", SQLDataType.CLOB, false, true);

  /** Create a new routine call instance */
  public StAsewkb2() {
    super("st_asewkb", Public.PUBLIC, SQLDataType.BLOB);

    setReturnParameter(RETURN_VALUE);
    addInParameter(_1);
    addInParameter(_2);
    setOverloaded(true);
  }

  /** Set the <code>_1</code> parameter IN value to the routine */
  public void set__1(Geometry<?> value) {
    setValue(_1, value);
  }

  /**
   * Set the <code>_1</code> parameter to the function to be used with a {@link org.jooq.Select}
   * statement
   */
  public void set__1(Field<Geometry<?>> field) {
    setField(_1, field);
  }

  /** Set the <code>_2</code> parameter IN value to the routine */
  public void set__2(String value) {
    setValue(_2, value);
  }

  /**
   * Set the <code>_2</code> parameter to the function to be used with a {@link org.jooq.Select}
   * statement
   */
  public void set__2(Field<String> field) {
    setField(_2, field);
  }
}
