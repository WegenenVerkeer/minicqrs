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
public class StSetsrid2 extends AbstractRoutine<Object> {

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
          "RETURN_VALUE",
          DefaultDataType.getDefaultDataType("\"public\".\"geography\""),
          false,
          false);

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

  /** The parameter <code>public.st_setsrid.srid</code>. */
  public static final Parameter<Integer> SRID =
      Internal.createParameter("srid", SQLDataType.INTEGER, false, false);

  /** Create a new routine call instance */
  public StSetsrid2() {
    super(
        "st_setsrid",
        Public.PUBLIC,
        DefaultDataType.getDefaultDataType("\"public\".\"geography\""));

    setReturnParameter(RETURN_VALUE);
    addInParameter(GEOG);
    addInParameter(SRID);
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

  /** Set the <code>srid</code> parameter IN value to the routine */
  public void setSrid(Integer value) {
    setValue(SRID, value);
  }

  /**
   * Set the <code>srid</code> parameter to the function to be used with a {@link org.jooq.Select}
   * statement
   */
  public void setSrid(Field<Integer> field) {
    setField(SRID, field);
  }
}
