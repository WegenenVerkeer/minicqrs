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
public class _PostgisIndexExtent extends AbstractRoutine<Object> {

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

  /**
   * @deprecated Unknown data type. If this is a qualified, user-defined type, it may have been
   *     excluded from code generation. If this is a built-in type, you can define an explicit
   *     {@link org.jooq.Binding} to specify how this type should be handled. Deprecation can be
   *     turned off using {@literal <deprecationOnUnknownTypes/>} in your code generator
   *     configuration.
   */
  @Deprecated
  public static final Parameter<Object> TBL =
      Internal.createParameter(
          "tbl", DefaultDataType.getDefaultDataType("\"pg_catalog\".\"regclass\""), false, false);

  /** The parameter <code>public._postgis_index_extent.col</code>. */
  public static final Parameter<String> COL =
      Internal.createParameter("col", SQLDataType.CLOB, false, false);

  /** Create a new routine call instance */
  public _PostgisIndexExtent() {
    super(
        "_postgis_index_extent",
        Public.PUBLIC,
        DefaultDataType.getDefaultDataType("\"public\".\"box2d\""));

    setReturnParameter(RETURN_VALUE);
    addInParameter(TBL);
    addInParameter(COL);
  }

  /** Set the <code>tbl</code> parameter IN value to the routine */
  public void setTbl(Object value) {
    setValue(TBL, value);
  }

  /**
   * Set the <code>tbl</code> parameter to the function to be used with a {@link org.jooq.Select}
   * statement
   */
  public void setTbl(Field<Object> field) {
    setField(TBL, field);
  }

  /** Set the <code>col</code> parameter IN value to the routine */
  public void setCol(String value) {
    setValue(COL, value);
  }

  /**
   * Set the <code>col</code> parameter to the function to be used with a {@link org.jooq.Select}
   * statement
   */
  public void setCol(Field<String> field) {
    setField(COL, field);
  }
}
