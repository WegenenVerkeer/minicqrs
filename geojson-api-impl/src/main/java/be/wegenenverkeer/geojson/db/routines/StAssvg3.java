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
public class StAssvg3 extends AbstractRoutine<String> {

  private static final long serialVersionUID = 1L;

  /** The parameter <code>public.st_assvg.RETURN_VALUE</code>. */
  public static final Parameter<String> RETURN_VALUE =
      Internal.createParameter("RETURN_VALUE", SQLDataType.CLOB, false, false);

  /** The parameter <code>public.st_assvg._1</code>. */
  public static final Parameter<String> _1 =
      Internal.createParameter("_1", SQLDataType.CLOB, false, true);

  /** Create a new routine call instance */
  public StAssvg3() {
    super("st_assvg", Public.PUBLIC, SQLDataType.CLOB);

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
