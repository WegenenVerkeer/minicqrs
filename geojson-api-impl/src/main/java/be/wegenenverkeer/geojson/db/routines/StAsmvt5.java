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
public class StAsmvt5 extends AbstractRoutine<byte[]> {

  private static final long serialVersionUID = 1L;

  /** The parameter <code>public.st_asmvt.RETURN_VALUE</code>. */
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
          "_1", DefaultDataType.getDefaultDataType("\"pg_catalog\".\"anyelement\""), false, true);

  /** The parameter <code>public.st_asmvt._2</code>. */
  public static final Parameter<String> _2 =
      Internal.createParameter("_2", SQLDataType.CLOB, false, true);

  /** The parameter <code>public.st_asmvt._3</code>. */
  public static final Parameter<Integer> _3 =
      Internal.createParameter("_3", SQLDataType.INTEGER, false, true);

  /** The parameter <code>public.st_asmvt._4</code>. */
  public static final Parameter<String> _4 =
      Internal.createParameter("_4", SQLDataType.CLOB, false, true);

  /** The parameter <code>public.st_asmvt._5</code>. */
  public static final Parameter<String> _5 =
      Internal.createParameter("_5", SQLDataType.CLOB, false, true);

  /** Create a new routine call instance */
  public StAsmvt5() {
    super("st_asmvt", Public.PUBLIC, SQLDataType.BLOB);

    setReturnParameter(RETURN_VALUE);
    addInParameter(_1);
    addInParameter(_2);
    addInParameter(_3);
    addInParameter(_4);
    addInParameter(_5);
    setOverloaded(true);
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

  /** Set the <code>_3</code> parameter IN value to the routine */
  public void set__3(Integer value) {
    setValue(_3, value);
  }

  /**
   * Set the <code>_3</code> parameter to the function to be used with a {@link org.jooq.Select}
   * statement
   */
  public void set__3(Field<Integer> field) {
    setField(_3, field);
  }

  /** Set the <code>_4</code> parameter IN value to the routine */
  public void set__4(String value) {
    setValue(_4, value);
  }

  /**
   * Set the <code>_4</code> parameter to the function to be used with a {@link org.jooq.Select}
   * statement
   */
  public void set__4(Field<String> field) {
    setField(_4, field);
  }

  /** Set the <code>_5</code> parameter IN value to the routine */
  public void set__5(String value) {
    setValue(_5, value);
  }

  /**
   * Set the <code>_5</code> parameter to the function to be used with a {@link org.jooq.Select}
   * statement
   */
  public void set__5(Field<String> field) {
    setField(_5, field);
  }
}
