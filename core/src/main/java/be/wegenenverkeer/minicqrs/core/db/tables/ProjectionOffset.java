/*
 * This file is generated by jOOQ.
 */
package be.wegenenverkeer.minicqrs.core.db.tables;

import be.wegenenverkeer.minicqrs.core.db.Keys;
import be.wegenenverkeer.minicqrs.core.db.Public;
import be.wegenenverkeer.minicqrs.core.db.tables.records.ProjectionOffsetRecord;
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
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

/** This class is generated by jOOQ. */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class ProjectionOffset extends TableImpl<ProjectionOffsetRecord> {

  private static final long serialVersionUID = 1L;

  /** The reference instance of <code>public.projection_offset</code> */
  public static final ProjectionOffset PROJECTION_OFFSET = new ProjectionOffset();

  /** The class holding records for this type */
  @Override
  public Class<ProjectionOffsetRecord> getRecordType() {
    return ProjectionOffsetRecord.class;
  }

  /** The column <code>public.projection_offset.projection</code>. */
  public final TableField<ProjectionOffsetRecord, String> PROJECTION =
      createField(DSL.name("projection"), SQLDataType.CLOB.nullable(false), this, "");

  /** The column <code>public.projection_offset.shard</code>. */
  public final TableField<ProjectionOffsetRecord, Long> SHARD =
      createField(DSL.name("shard"), SQLDataType.BIGINT.nullable(false), this, "");

  /** The column <code>public.projection_offset.sequence</code>. */
  public final TableField<ProjectionOffsetRecord, Long> SEQUENCE =
      createField(DSL.name("sequence"), SQLDataType.BIGINT.nullable(false), this, "");

  private ProjectionOffset(Name alias, Table<ProjectionOffsetRecord> aliased) {
    this(alias, aliased, (Field<?>[]) null, null);
  }

  private ProjectionOffset(
      Name alias, Table<ProjectionOffsetRecord> aliased, Field<?>[] parameters, Condition where) {
    super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table(), where);
  }

  /** Create an aliased <code>public.projection_offset</code> table reference */
  public ProjectionOffset(String alias) {
    this(DSL.name(alias), PROJECTION_OFFSET);
  }

  /** Create an aliased <code>public.projection_offset</code> table reference */
  public ProjectionOffset(Name alias) {
    this(alias, PROJECTION_OFFSET);
  }

  /** Create a <code>public.projection_offset</code> table reference */
  public ProjectionOffset() {
    this(DSL.name("projection_offset"), null);
  }

  @Override
  public Schema getSchema() {
    return aliased() ? null : Public.PUBLIC;
  }

  @Override
  public UniqueKey<ProjectionOffsetRecord> getPrimaryKey() {
    return Keys.PROJECTION_OFFSET_PKEY;
  }

  @Override
  public ProjectionOffset as(String alias) {
    return new ProjectionOffset(DSL.name(alias), this);
  }

  @Override
  public ProjectionOffset as(Name alias) {
    return new ProjectionOffset(alias, this);
  }

  @Override
  public ProjectionOffset as(Table<?> alias) {
    return new ProjectionOffset(alias.getQualifiedName(), this);
  }

  /** Rename this table */
  @Override
  public ProjectionOffset rename(String name) {
    return new ProjectionOffset(DSL.name(name), null);
  }

  /** Rename this table */
  @Override
  public ProjectionOffset rename(Name name) {
    return new ProjectionOffset(name, null);
  }

  /** Rename this table */
  @Override
  public ProjectionOffset rename(Table<?> name) {
    return new ProjectionOffset(name.getQualifiedName(), null);
  }

  /** Create an inline derived table from this table */
  @Override
  public ProjectionOffset where(Condition condition) {
    return new ProjectionOffset(getQualifiedName(), aliased() ? this : null, null, condition);
  }

  /** Create an inline derived table from this table */
  @Override
  public ProjectionOffset where(Collection<? extends Condition> conditions) {
    return where(DSL.and(conditions));
  }

  /** Create an inline derived table from this table */
  @Override
  public ProjectionOffset where(Condition... conditions) {
    return where(DSL.and(conditions));
  }

  /** Create an inline derived table from this table */
  @Override
  public ProjectionOffset where(Field<Boolean> condition) {
    return where(DSL.condition(condition));
  }

  /** Create an inline derived table from this table */
  @Override
  @PlainSQL
  public ProjectionOffset where(SQL condition) {
    return where(DSL.condition(condition));
  }

  /** Create an inline derived table from this table */
  @Override
  @PlainSQL
  public ProjectionOffset where(@Stringly.SQL String condition) {
    return where(DSL.condition(condition));
  }

  /** Create an inline derived table from this table */
  @Override
  @PlainSQL
  public ProjectionOffset where(@Stringly.SQL String condition, Object... binds) {
    return where(DSL.condition(condition, binds));
  }

  /** Create an inline derived table from this table */
  @Override
  @PlainSQL
  public ProjectionOffset where(@Stringly.SQL String condition, QueryPart... parts) {
    return where(DSL.condition(condition, parts));
  }

  /** Create an inline derived table from this table */
  @Override
  public ProjectionOffset whereExists(Select<?> select) {
    return where(DSL.exists(select));
  }

  /** Create an inline derived table from this table */
  @Override
  public ProjectionOffset whereNotExists(Select<?> select) {
    return where(DSL.notExists(select));
  }
}
