/*
 * This file is generated by jOOQ.
 */
package be.wegenenverkeer.geojson.db.routines;

import be.wegenenverkeer.geojson.db.Public;
import org.jooq.Parameter;
import org.jooq.impl.AbstractRoutine;
import org.jooq.impl.DefaultDataType;
import org.jooq.impl.Internal;

/** This class is generated by jOOQ. */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class GeometrySpgistChoose_2d extends AbstractRoutine<java.lang.Void> {

  private static final long serialVersionUID = 1L;

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
          "_1", DefaultDataType.getDefaultDataType("\"pg_catalog\".\"internal\""), false, true);

  /**
   * @deprecated Unknown data type. If this is a qualified, user-defined type, it may have been
   *     excluded from code generation. If this is a built-in type, you can define an explicit
   *     {@link org.jooq.Binding} to specify how this type should be handled. Deprecation can be
   *     turned off using {@literal <deprecationOnUnknownTypes/>} in your code generator
   *     configuration.
   */
  @Deprecated
  public static final Parameter<Object> _2 =
      Internal.createParameter(
          "_2", DefaultDataType.getDefaultDataType("\"pg_catalog\".\"internal\""), false, true);

  /** Create a new routine call instance */
  public GeometrySpgistChoose_2d() {
    super("geometry_spgist_choose_2d", Public.PUBLIC);

    addInParameter(_1);
    addInParameter(_2);
  }

  /** Set the <code>_1</code> parameter IN value to the routine */
  public void set__1(Object value) {
    setValue(_1, value);
  }

  /** Set the <code>_2</code> parameter IN value to the routine */
  public void set__2(Object value) {
    setValue(_2, value);
  }
}
