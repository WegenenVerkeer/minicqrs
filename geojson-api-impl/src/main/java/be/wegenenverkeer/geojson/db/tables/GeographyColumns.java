/*
 * This file is generated by jOOQ.
 */
package be.wegenenverkeer.geojson.db.tables;

import be.wegenenverkeer.geojson.db.Public;
import be.wegenenverkeer.geojson.db.tables.records.GeographyColumnsRecord;
import java.util.Collection;
import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.Name;
import org.jooq.PlainSQL;
import org.jooq.QueryPart;
import org.jooq.SQL;
import org.jooq.Schema;
import org.jooq.Select;
import org.jooq.Stringly;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

/** This class is generated by jOOQ. */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class GeographyColumns extends TableImpl<GeographyColumnsRecord> {

  private static final long serialVersionUID = 1L;

  /** The reference instance of <code>public.geography_columns</code> */
  public static final GeographyColumns GEOGRAPHY_COLUMNS = new GeographyColumns();

  /** The class holding records for this type */
  @Override
  public Class<GeographyColumnsRecord> getRecordType() {
    return GeographyColumnsRecord.class;
  }

  /** The column <code>public.geography_columns.f_table_catalog</code>. */
  public final TableField<GeographyColumnsRecord, String> F_TABLE_CATALOG =
      createField(DSL.name("f_table_catalog"), SQLDataType.VARCHAR, this, "");

  /** The column <code>public.geography_columns.f_table_schema</code>. */
  public final TableField<GeographyColumnsRecord, String> F_TABLE_SCHEMA =
      createField(DSL.name("f_table_schema"), SQLDataType.VARCHAR, this, "");

  /** The column <code>public.geography_columns.f_table_name</code>. */
  public final TableField<GeographyColumnsRecord, String> F_TABLE_NAME =
      createField(DSL.name("f_table_name"), SQLDataType.VARCHAR, this, "");

  /** The column <code>public.geography_columns.f_geography_column</code>. */
  public final TableField<GeographyColumnsRecord, String> F_GEOGRAPHY_COLUMN =
      createField(DSL.name("f_geography_column"), SQLDataType.VARCHAR, this, "");

  /** The column <code>public.geography_columns.coord_dimension</code>. */
  public final TableField<GeographyColumnsRecord, Integer> COORD_DIMENSION =
      createField(DSL.name("coord_dimension"), SQLDataType.INTEGER, this, "");

  /** The column <code>public.geography_columns.srid</code>. */
  public final TableField<GeographyColumnsRecord, Integer> SRID =
      createField(DSL.name("srid"), SQLDataType.INTEGER, this, "");

  /** The column <code>public.geography_columns.type</code>. */
  public final TableField<GeographyColumnsRecord, String> TYPE =
      createField(DSL.name("type"), SQLDataType.CLOB, this, "");

  private GeographyColumns(Name alias, Table<GeographyColumnsRecord> aliased) {
    this(alias, aliased, (Field<?>[]) null, null);
  }

  private GeographyColumns(
      Name alias, Table<GeographyColumnsRecord> aliased, Field<?>[] parameters, Condition where) {
    super(
        alias,
        null,
        aliased,
        parameters,
        DSL.comment(""),
        TableOptions.view(
            """
        create view "geography_columns" as  SELECT current_database() AS f_table_catalog,
          n.nspname AS f_table_schema,
          c.relname AS f_table_name,
          a.attname AS f_geography_column,
          postgis_typmod_dims(a.atttypmod) AS coord_dimension,
          postgis_typmod_srid(a.atttypmod) AS srid,
          postgis_typmod_type(a.atttypmod) AS type
         FROM pg_class c,
          pg_attribute a,
          pg_type t,
          pg_namespace n
        WHERE ((t.typname = 'geography'::name) AND (a.attisdropped = false) AND (a.atttypid = t.oid) AND (a.attrelid = c.oid) AND (c.relnamespace = n.oid) AND (c.relkind = ANY (ARRAY['r'::"char", 'v'::"char", 'm'::"char", 'f'::"char", 'p'::"char"])) AND (NOT pg_is_other_temp_schema(c.relnamespace)) AND has_table_privilege(c.oid, 'SELECT'::text));
        """),
        where);
  }

  /** Create an aliased <code>public.geography_columns</code> table reference */
  public GeographyColumns(String alias) {
    this(DSL.name(alias), GEOGRAPHY_COLUMNS);
  }

  /** Create an aliased <code>public.geography_columns</code> table reference */
  public GeographyColumns(Name alias) {
    this(alias, GEOGRAPHY_COLUMNS);
  }

  /** Create a <code>public.geography_columns</code> table reference */
  public GeographyColumns() {
    this(DSL.name("geography_columns"), null);
  }

  @Override
  public Schema getSchema() {
    return aliased() ? null : Public.PUBLIC;
  }

  @Override
  public GeographyColumns as(String alias) {
    return new GeographyColumns(DSL.name(alias), this);
  }

  @Override
  public GeographyColumns as(Name alias) {
    return new GeographyColumns(alias, this);
  }

  @Override
  public GeographyColumns as(Table<?> alias) {
    return new GeographyColumns(alias.getQualifiedName(), this);
  }

  /** Rename this table */
  @Override
  public GeographyColumns rename(String name) {
    return new GeographyColumns(DSL.name(name), null);
  }

  /** Rename this table */
  @Override
  public GeographyColumns rename(Name name) {
    return new GeographyColumns(name, null);
  }

  /** Rename this table */
  @Override
  public GeographyColumns rename(Table<?> name) {
    return new GeographyColumns(name.getQualifiedName(), null);
  }

  /** Create an inline derived table from this table */
  @Override
  public GeographyColumns where(Condition condition) {
    return new GeographyColumns(getQualifiedName(), aliased() ? this : null, null, condition);
  }

  /** Create an inline derived table from this table */
  @Override
  public GeographyColumns where(Collection<? extends Condition> conditions) {
    return where(DSL.and(conditions));
  }

  /** Create an inline derived table from this table */
  @Override
  public GeographyColumns where(Condition... conditions) {
    return where(DSL.and(conditions));
  }

  /** Create an inline derived table from this table */
  @Override
  public GeographyColumns where(Field<Boolean> condition) {
    return where(DSL.condition(condition));
  }

  /** Create an inline derived table from this table */
  @Override
  @PlainSQL
  public GeographyColumns where(SQL condition) {
    return where(DSL.condition(condition));
  }

  /** Create an inline derived table from this table */
  @Override
  @PlainSQL
  public GeographyColumns where(@Stringly.SQL String condition) {
    return where(DSL.condition(condition));
  }

  /** Create an inline derived table from this table */
  @Override
  @PlainSQL
  public GeographyColumns where(@Stringly.SQL String condition, Object... binds) {
    return where(DSL.condition(condition, binds));
  }

  /** Create an inline derived table from this table */
  @Override
  @PlainSQL
  public GeographyColumns where(@Stringly.SQL String condition, QueryPart... parts) {
    return where(DSL.condition(condition, parts));
  }

  /** Create an inline derived table from this table */
  @Override
  public GeographyColumns whereExists(Select<?> select) {
    return where(DSL.exists(select));
  }

  /** Create an inline derived table from this table */
  @Override
  public GeographyColumns whereNotExists(Select<?> select) {
    return where(DSL.notExists(select));
  }
}
