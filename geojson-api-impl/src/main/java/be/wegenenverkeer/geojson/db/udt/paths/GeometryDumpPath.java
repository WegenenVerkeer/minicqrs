/*
 * This file is generated by jOOQ.
 */
package be.wegenenverkeer.geojson.db.udt.paths;

import be.wegenenverkeer.geojson.db.udt.GeometryDump;
import be.wegenenverkeer.geojson.db.udt.records.GeometryDumpRecord;
import be.wegenenverkeer.geojson.util.GeolatteGeometryConverter;
import org.geolatte.geom.Geometry;
import org.jooq.Binding;
import org.jooq.Comment;
import org.jooq.DataType;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.RecordQualifier;
import org.jooq.UDTField;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.UDTPathTableFieldImpl;

/** This class is generated by jOOQ. */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class GeometryDumpPath<R extends Record, T>
    extends UDTPathTableFieldImpl<R, GeometryDumpRecord, T> {

  private static final long serialVersionUID = 1L;

  /** The attribute <code>public.geometry_dump.path</code>. */
  public final UDTField<GeometryDumpRecord, Integer[]> PATH =
      Internal.createUDTPathField(
          DSL.name("path"), SQLDataType.INTEGER.array(), this, "", UDTField.class);

  /** The attribute <code>public.geometry_dump.geom</code>. */
  public final UDTField<GeometryDumpRecord, Geometry<?>> GEOM =
      Internal.createUDTPathField(
          DSL.name("geom"),
          SQLDataType.OTHER,
          this,
          "",
          UDTField.class,
          new GeolatteGeometryConverter());

  public GeometryDumpPath(
      Name name,
      DataType<T> type,
      RecordQualifier<R> qualifier,
      Comment comment,
      Binding<?, T> binding) {
    super(name, type, qualifier, GeometryDump.GEOMETRY_DUMP, comment, binding);
  }
}
