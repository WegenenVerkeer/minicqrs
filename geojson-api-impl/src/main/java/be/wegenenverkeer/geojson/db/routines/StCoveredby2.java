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
public class StCoveredby2 extends AbstractRoutine<Boolean> {

  private static final long serialVersionUID = 1L;

  /** The parameter <code>public.st_coveredby.RETURN_VALUE</code>. */
  public static final Parameter<Boolean> RETURN_VALUE =
      Internal.createParameter("RETURN_VALUE", SQLDataType.BOOLEAN, false, false);

  /**
   * @deprecated Unknown data type. If this is a qualified, user-defined type, it may have been
   *     excluded from code generation. If this is a built-in type, you can define an explicit
   *     {@link org.jooq.Binding} to specify how this type should be handled. Deprecation can be
   *     turned off using {@literal <deprecationOnUnknownTypes/>} in your code generator
   *     configuration.
   */
  @Deprecated
  public static final Parameter<Object> GEOG1 =
      Internal.createParameter(
          "geog1", DefaultDataType.getDefaultDataType("\"public\".\"geography\""), false, false);

  /**
   * @deprecated Unknown data type. If this is a qualified, user-defined type, it may have been
   *     excluded from code generation. If this is a built-in type, you can define an explicit
   *     {@link org.jooq.Binding} to specify how this type should be handled. Deprecation can be
   *     turned off using {@literal <deprecationOnUnknownTypes/>} in your code generator
   *     configuration.
   */
  @Deprecated
  public static final Parameter<Object> GEOG2 =
      Internal.createParameter(
          "geog2", DefaultDataType.getDefaultDataType("\"public\".\"geography\""), false, false);

  /** Create a new routine call instance */
  public StCoveredby2() {
    super("st_coveredby", Public.PUBLIC, SQLDataType.BOOLEAN);

    setReturnParameter(RETURN_VALUE);
    addInParameter(GEOG1);
    addInParameter(GEOG2);
    setOverloaded(true);
  }

  /** Set the <code>geog1</code> parameter IN value to the routine */
  public void setGeog1(Object value) {
    setValue(GEOG1, value);
  }

  /**
   * Set the <code>geog1</code> parameter to the function to be used with a {@link org.jooq.Select}
   * statement
   */
  public void setGeog1(Field<Object> field) {
    setField(GEOG1, field);
  }

  /** Set the <code>geog2</code> parameter IN value to the routine */
  public void setGeog2(Object value) {
    setValue(GEOG2, value);
  }

  /**
   * Set the <code>geog2</code> parameter to the function to be used with a {@link org.jooq.Select}
   * statement
   */
  public void setGeog2(Field<Object> field) {
    setField(GEOG2, field);
  }
}
