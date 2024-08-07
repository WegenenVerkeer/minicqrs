/*
 * This file is generated by jOOQ.
 */
package be.wegenenverkeer.geojson.db.routines;

import be.wegenenverkeer.geojson.db.Public;
import org.jooq.Field;
import org.jooq.Parameter;
import org.jooq.impl.AbstractRoutine;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultDataType;
import org.jooq.impl.Internal;
import org.jooq.impl.SQLDataType;

/** This class is generated by jOOQ. */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class StAsgeojson3 extends AbstractRoutine<String> {

  private static final long serialVersionUID = 1L;

  /** The parameter <code>public.st_asgeojson.RETURN_VALUE</code>. */
  public static final Parameter<String> RETURN_VALUE =
      Internal.createParameter("RETURN_VALUE", SQLDataType.CLOB, false, false);

  /**
   * @deprecated Unknown data type. If this is a qualified, user-defined type, it may have been
   *     excluded from code generation. If this is a built-in type, you can define an explicit
   *     {@link org.jooq.Binding} to specify how this type should be handled. Deprecation can be
   *     turned off using {@literal <deprecationOnUnknownTypes/>} in your code generator
   *     configuration.
   */
  @Deprecated
  public static final Parameter<Object> GEOG =
      Internal.createParameter(
          "geog", DefaultDataType.getDefaultDataType("\"public\".\"geography\""), false, false);

  /** The parameter <code>public.st_asgeojson.maxdecimaldigits</code>. */
  public static final Parameter<Integer> MAXDECIMALDIGITS =
      Internal.createParameter(
          "maxdecimaldigits",
          SQLDataType.INTEGER.defaultValue(DSL.field(DSL.raw("9"), SQLDataType.INTEGER)),
          true,
          false);

  /** The parameter <code>public.st_asgeojson.options</code>. */
  public static final Parameter<Integer> OPTIONS =
      Internal.createParameter(
          "options",
          SQLDataType.INTEGER.defaultValue(DSL.field(DSL.raw("0"), SQLDataType.INTEGER)),
          true,
          false);

  /** Create a new routine call instance */
  public StAsgeojson3() {
    super("st_asgeojson", Public.PUBLIC, SQLDataType.CLOB);

    setReturnParameter(RETURN_VALUE);
    addInParameter(GEOG);
    addInParameter(MAXDECIMALDIGITS);
    addInParameter(OPTIONS);
    setOverloaded(true);
  }

  /** Set the <code>geog</code> parameter IN value to the routine */
  public void setGeog(Object value) {
    setValue(GEOG, value);
  }

  /**
   * Set the <code>geog</code> parameter to the function to be used with a {@link org.jooq.Select}
   * statement
   */
  public void setGeog(Field<Object> field) {
    setField(GEOG, field);
  }

  /** Set the <code>maxdecimaldigits</code> parameter IN value to the routine */
  public void setMaxdecimaldigits(Integer value) {
    setValue(MAXDECIMALDIGITS, value);
  }

  /**
   * Set the <code>maxdecimaldigits</code> parameter to the function to be used with a {@link
   * org.jooq.Select} statement
   */
  public void setMaxdecimaldigits(Field<Integer> field) {
    setField(MAXDECIMALDIGITS, field);
  }

  /** Set the <code>options</code> parameter IN value to the routine */
  public void setOptions(Integer value) {
    setValue(OPTIONS, value);
  }

  /**
   * Set the <code>options</code> parameter to the function to be used with a {@link
   * org.jooq.Select} statement
   */
  public void setOptions(Field<Integer> field) {
    setField(OPTIONS, field);
  }
}
