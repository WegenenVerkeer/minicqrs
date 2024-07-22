/*
 * This file is generated by jOOQ.
 */
package be.wegenenverkeer.geojson.db.routines;

import be.wegenenverkeer.geojson.db.Public;
import org.jooq.Field;
import org.jooq.Parameter;
import org.jooq.impl.AbstractRoutine;
import org.jooq.impl.DefaultDataType;
import org.jooq.impl.Internal;
import org.jooq.impl.SQLDataType;

/** This class is generated by jOOQ. */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class GeographySend extends AbstractRoutine<byte[]> {

  private static final long serialVersionUID = 1L;

  /** The parameter <code>public.geography_send.RETURN_VALUE</code>. */
  public static final Parameter<byte[]> RETURN_VALUE =
      Internal.createParameter("RETURN_VALUE", SQLDataType.BLOB, false, false);

  /**
   * @deprecated Unknown data type. If this is a qualified, user-defined type, it may have been
   *     excluded from code generation. If this is a built-in type, you can define an explicit
   *     {@link org.jooq.Binding} to specify how this type should be handled. Deprecation can be
   *     turned off using {@literal <deprecationOnUnknownTypes/>} in your code generator
   *     configuration.
   */
  @Deprecated
  public static final Parameter<Object> _1 =
      Internal.createParameter(
          "_1", DefaultDataType.getDefaultDataType("\"public\".\"geography\""), false, true);

  /** Create a new routine call instance */
  public GeographySend() {
    super("geography_send", Public.PUBLIC, SQLDataType.BLOB);

    setReturnParameter(RETURN_VALUE);
    addInParameter(_1);
  }

  /** Set the <code>_1</code> parameter IN value to the routine */
  public void set__1(Object value) {
    setValue(_1, value);
  }

  /**
   * Set the <code>_1</code> parameter to the function to be used with a {@link org.jooq.Select}
   * statement
   */
  public void set__1(Field<Object> field) {
    setField(_1, field);
  }
}
