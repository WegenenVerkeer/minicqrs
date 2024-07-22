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
public class Dropgeometrytable1 extends AbstractRoutine<String> {

  private static final long serialVersionUID = 1L;

  /** The parameter <code>public.dropgeometrytable.RETURN_VALUE</code>. */
  public static final Parameter<String> RETURN_VALUE =
      Internal.createParameter("RETURN_VALUE", SQLDataType.CLOB, false, false);

  /** The parameter <code>public.dropgeometrytable.catalog_name</code>. */
  public static final Parameter<String> CATALOG_NAME =
      Internal.createParameter("catalog_name", SQLDataType.VARCHAR, false, false);

  /** The parameter <code>public.dropgeometrytable.schema_name</code>. */
  public static final Parameter<String> SCHEMA_NAME =
      Internal.createParameter("schema_name", SQLDataType.VARCHAR, false, false);

  /** The parameter <code>public.dropgeometrytable.table_name</code>. */
  public static final Parameter<String> TABLE_NAME =
      Internal.createParameter("table_name", SQLDataType.VARCHAR, false, false);

  /** Create a new routine call instance */
  public Dropgeometrytable1() {
    super("dropgeometrytable", Public.PUBLIC, SQLDataType.CLOB);

    setReturnParameter(RETURN_VALUE);
    addInParameter(CATALOG_NAME);
    addInParameter(SCHEMA_NAME);
    addInParameter(TABLE_NAME);
    setOverloaded(true);
  }

  /** Set the <code>catalog_name</code> parameter IN value to the routine */
  public void setCatalogName(String value) {
    setValue(CATALOG_NAME, value);
  }

  /**
   * Set the <code>catalog_name</code> parameter to the function to be used with a {@link
   * org.jooq.Select} statement
   */
  public void setCatalogName(Field<String> field) {
    setField(CATALOG_NAME, field);
  }

  /** Set the <code>schema_name</code> parameter IN value to the routine */
  public void setSchemaName(String value) {
    setValue(SCHEMA_NAME, value);
  }

  /**
   * Set the <code>schema_name</code> parameter to the function to be used with a {@link
   * org.jooq.Select} statement
   */
  public void setSchemaName(Field<String> field) {
    setField(SCHEMA_NAME, field);
  }

  /** Set the <code>table_name</code> parameter IN value to the routine */
  public void setTableName(String value) {
    setValue(TABLE_NAME, value);
  }

  /**
   * Set the <code>table_name</code> parameter to the function to be used with a {@link
   * org.jooq.Select} statement
   */
  public void setTableName(Field<String> field) {
    setField(TABLE_NAME, field);
  }
}
