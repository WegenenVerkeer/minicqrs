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

/**
 * @deprecated Unknown data type. If this is a qualified, user-defined type, it may have been
 *     excluded from code generation. If this is a built-in type, you can define an explicit {@link
 *     org.jooq.Binding} to specify how this type should be handled. Deprecation can be turned off
 *     using {@literal <deprecationOnUnknownTypes/>} in your code generator configuration.
 */
@Deprecated
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class StEstimatedextent2 extends AbstractRoutine<Object> {

  private static final long serialVersionUID = 1L;

  /**
   * @deprecated Unknown data type. If this is a qualified, user-defined type, it may have been
   *     excluded from code generation. If this is a built-in type, you can define an explicit
   *     {@link org.jooq.Binding} to specify how this type should be handled. Deprecation can be
   *     turned off using {@literal <deprecationOnUnknownTypes/>} in your code generator
   *     configuration.
   */
  @Deprecated
  public static final Parameter<Object> RETURN_VALUE =
      Internal.createParameter(
          "RETURN_VALUE", DefaultDataType.getDefaultDataType("\"public\".\"box2d\""), false, false);

  /** The parameter <code>public.st_estimatedextent._1</code>. */
  public static final Parameter<String> _1 =
      Internal.createParameter("_1", SQLDataType.CLOB, false, true);

  /** The parameter <code>public.st_estimatedextent._2</code>. */
  public static final Parameter<String> _2 =
      Internal.createParameter("_2", SQLDataType.CLOB, false, true);

  /** The parameter <code>public.st_estimatedextent._3</code>. */
  public static final Parameter<String> _3 =
      Internal.createParameter("_3", SQLDataType.CLOB, false, true);

  /** Create a new routine call instance */
  public StEstimatedextent2() {
    super(
        "st_estimatedextent",
        Public.PUBLIC,
        DefaultDataType.getDefaultDataType("\"public\".\"box2d\""));

    setReturnParameter(RETURN_VALUE);
    addInParameter(_1);
    addInParameter(_2);
    addInParameter(_3);
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
  public void set__3(String value) {
    setValue(_3, value);
  }

  /**
   * Set the <code>_3</code> parameter to the function to be used with a {@link org.jooq.Select}
   * statement
   */
  public void set__3(Field<String> field) {
    setField(_3, field);
  }
}
