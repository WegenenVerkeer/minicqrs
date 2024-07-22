/*
 * This file is generated by jOOQ.
 */
package be.wegenenverkeer.geojson.db.routines;

import be.wegenenverkeer.geojson.db.Public;
import org.jooq.Field;
import org.jooq.Parameter;
import org.jooq.impl.AbstractRoutine;
import org.jooq.impl.Internal;
import org.jooq.impl.SQLDataType;

/** This class is generated by jOOQ. */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class PostgisTypmodDims extends AbstractRoutine<Integer> {

  private static final long serialVersionUID = 1L;

  /** The parameter <code>public.postgis_typmod_dims.RETURN_VALUE</code>. */
  public static final Parameter<Integer> RETURN_VALUE =
      Internal.createParameter("RETURN_VALUE", SQLDataType.INTEGER, false, false);

  /** The parameter <code>public.postgis_typmod_dims._1</code>. */
  public static final Parameter<Integer> _1 =
      Internal.createParameter("_1", SQLDataType.INTEGER, false, true);

  /** Create a new routine call instance */
  public PostgisTypmodDims() {
    super("postgis_typmod_dims", Public.PUBLIC, SQLDataType.INTEGER);

    setReturnParameter(RETURN_VALUE);
    addInParameter(_1);
  }

  /** Set the <code>_1</code> parameter IN value to the routine */
  public void set__1(Integer value) {
    setValue(_1, value);
  }

  /**
   * Set the <code>_1</code> parameter to the function to be used with a {@link org.jooq.Select}
   * statement
   */
  public void set__1(Field<Integer> field) {
    setField(_1, field);
  }
}
