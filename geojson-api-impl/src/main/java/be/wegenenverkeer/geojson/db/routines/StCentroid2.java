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

/**
 * @deprecated Unknown data type. If this is a qualified, user-defined type, it may have been
 *     excluded from code generation. If this is a built-in type, you can define an explicit {@link
 *     org.jooq.Binding} to specify how this type should be handled. Deprecation can be turned off
 *     using {@literal <deprecationOnUnknownTypes/>} in your code generator configuration.
 */
@Deprecated
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class StCentroid2 extends AbstractRoutine<Object> {

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
  public static final Parameter<Object> _1 =
      Internal.createParameter(
          "_1", DefaultDataType.getDefaultDataType("\"public\".\"geography\""), false, true);

  /** The parameter <code>public.st_centroid.use_spheroid</code>. */
  public static final Parameter<Boolean> USE_SPHEROID =
      Internal.createParameter(
          "use_spheroid",
          SQLDataType.BOOLEAN.defaultValue(DSL.field(DSL.raw("true"), SQLDataType.BOOLEAN)),
          true,
          false);

  /** Create a new routine call instance */
  public StCentroid2() {
    super(
        "st_centroid",
        Public.PUBLIC,
        DefaultDataType.getDefaultDataType("\"public\".\"geography\""));

    setReturnParameter(RETURN_VALUE);
    addInParameter(_1);
    addInParameter(USE_SPHEROID);
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

  /** Set the <code>use_spheroid</code> parameter IN value to the routine */
  public void setUseSpheroid(Boolean value) {
    setValue(USE_SPHEROID, value);
  }

  /**
   * Set the <code>use_spheroid</code> parameter to the function to be used with a {@link
   * org.jooq.Select} statement
   */
  public void setUseSpheroid(Field<Boolean> field) {
    setField(USE_SPHEROID, field);
  }
}