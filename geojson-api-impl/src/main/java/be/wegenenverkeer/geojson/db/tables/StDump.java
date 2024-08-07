/*
 * This file is generated by jOOQ.
 */
package be.wegenenverkeer.geojson.db.tables;

import be.wegenenverkeer.geojson.db.Public;
import be.wegenenverkeer.geojson.db.tables.records.StDumpRecord;
import be.wegenenverkeer.geojson.util.GeolatteGeometryConverter;
import org.geolatte.geom.Geometry;
import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.Name;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultDataType;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

/** This class is generated by jOOQ. */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class StDump extends TableImpl<StDumpRecord> {

  private static final long serialVersionUID = 1L;

  /** The reference instance of <code>public.st_dump</code> */
  public static final StDump ST_DUMP = new StDump();

  /** The class holding records for this type */
  @Override
  public Class<StDumpRecord> getRecordType() {
    return StDumpRecord.class;
  }

  /**
   * @deprecated Unknown data type. If this is a qualified, user-defined type, it may have been
   *     excluded from code generation. If this is a built-in type, you can define an explicit
   *     {@link org.jooq.Binding} to specify how this type should be handled. Deprecation can be
   *     turned off using {@literal <deprecationOnUnknownTypes/>} in your code generator
   *     configuration.
   */
  @Deprecated
  public final TableField<StDumpRecord, Object> ST_DUMP_ =
      createField(
          DSL.name("st_dump"), DefaultDataType.getDefaultDataType("\"geometry_dump\""), this, "");

  private StDump(Name alias, Table<StDumpRecord> aliased) {
    this(
        alias,
        aliased,
        new Field[] {
          DSL.val(null, SQLDataType.OTHER.asConvertedDataType(new GeolatteGeometryConverter()))
        });
  }

  private StDump(Name alias, Table<StDumpRecord> aliased, Field<?>[] parameters) {
    this(alias, aliased, parameters, null);
  }

  private StDump(Name alias, Table<StDumpRecord> aliased, Field<?>[] parameters, Condition where) {
    super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.function(), where);
  }

  /** Create an aliased <code>public.st_dump</code> table reference */
  public StDump(String alias) {
    this(DSL.name(alias), ST_DUMP);
  }

  /** Create an aliased <code>public.st_dump</code> table reference */
  public StDump(Name alias) {
    this(alias, ST_DUMP);
  }

  /** Create a <code>public.st_dump</code> table reference */
  public StDump() {
    this(DSL.name("st_dump"), null);
  }

  @Override
  public Schema getSchema() {
    return aliased() ? null : Public.PUBLIC;
  }

  @Override
  public StDump as(String alias) {
    return new StDump(DSL.name(alias), this, parameters);
  }

  @Override
  public StDump as(Name alias) {
    return new StDump(alias, this, parameters);
  }

  @Override
  public StDump as(Table<?> alias) {
    return new StDump(alias.getQualifiedName(), this, parameters);
  }

  /** Rename this table */
  @Override
  public StDump rename(String name) {
    return new StDump(DSL.name(name), null, parameters);
  }

  /** Rename this table */
  @Override
  public StDump rename(Name name) {
    return new StDump(name, null, parameters);
  }

  /** Rename this table */
  @Override
  public StDump rename(Table<?> name) {
    return new StDump(name.getQualifiedName(), null, parameters);
  }

  /** Call this table-valued function */
  public StDump call(Geometry<?> __1) {
    StDump result =
        new StDump(
            DSL.name("st_dump"),
            null,
            new Field[] {
              DSL.val(__1, SQLDataType.OTHER.asConvertedDataType(new GeolatteGeometryConverter()))
            });

    return aliased() ? result.as(getUnqualifiedName()) : result;
  }

  /** Call this table-valued function */
  public StDump call(Field<Geometry<?>> __1) {
    StDump result = new StDump(DSL.name("st_dump"), null, new Field[] {__1});

    return aliased() ? result.as(getUnqualifiedName()) : result;
  }
}
