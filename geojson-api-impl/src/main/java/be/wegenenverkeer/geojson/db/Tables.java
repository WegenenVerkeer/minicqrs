/*
 * This file is generated by jOOQ.
 */
package be.wegenenverkeer.geojson.db;

import be.wegenenverkeer.geojson.db.tables.GeographyColumns;
import be.wegenenverkeer.geojson.db.tables.GeometryColumns;
import be.wegenenverkeer.geojson.db.tables.SpatialRefSys;
import be.wegenenverkeer.geojson.db.tables.StDump;
import be.wegenenverkeer.geojson.db.tables.StDumppoints;
import be.wegenenverkeer.geojson.db.tables.StDumprings;
import be.wegenenverkeer.geojson.db.tables.StHexagongrid;
import be.wegenenverkeer.geojson.db.tables.StSquaregrid;
import be.wegenenverkeer.geojson.db.tables.StSubdivide;
import be.wegenenverkeer.geojson.db.tables.records.StDumpRecord;
import be.wegenenverkeer.geojson.db.tables.records.StDumppointsRecord;
import be.wegenenverkeer.geojson.db.tables.records.StDumpringsRecord;
import be.wegenenverkeer.geojson.db.tables.records.StHexagongridRecord;
import be.wegenenverkeer.geojson.db.tables.records.StSquaregridRecord;
import be.wegenenverkeer.geojson.db.tables.records.StSubdivideRecord;
import org.geolatte.geom.Geometry;
import org.jooq.Configuration;
import org.jooq.Field;
import org.jooq.Result;

/** Convenience access to all tables in public. */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class Tables {

  /** The table <code>public.geography_columns</code>. */
  public static final GeographyColumns GEOGRAPHY_COLUMNS = GeographyColumns.GEOGRAPHY_COLUMNS;

  /** The table <code>public.geometry_columns</code>. */
  public static final GeometryColumns GEOMETRY_COLUMNS = GeometryColumns.GEOMETRY_COLUMNS;

  /** The table <code>public.spatial_ref_sys</code>. */
  public static final SpatialRefSys SPATIAL_REF_SYS = SpatialRefSys.SPATIAL_REF_SYS;

  /** The table <code>public.st_dump</code>. */
  public static final StDump ST_DUMP = StDump.ST_DUMP;

  /** Call <code>public.st_dump</code>. */
  public static Result<StDumpRecord> ST_DUMP(Configuration configuration, Geometry<?> __1) {
    return configuration
        .dsl()
        .selectFrom(be.wegenenverkeer.geojson.db.tables.StDump.ST_DUMP.call(__1))
        .fetch();
  }

  /** Get <code>public.st_dump</code> as a table. */
  public static StDump ST_DUMP(Geometry<?> __1) {
    return be.wegenenverkeer.geojson.db.tables.StDump.ST_DUMP.call(__1);
  }

  /** Get <code>public.st_dump</code> as a table. */
  public static StDump ST_DUMP(Field<Geometry<?>> __1) {
    return be.wegenenverkeer.geojson.db.tables.StDump.ST_DUMP.call(__1);
  }

  /** The table <code>public.st_dumppoints</code>. */
  public static final StDumppoints ST_DUMPPOINTS = StDumppoints.ST_DUMPPOINTS;

  /** Call <code>public.st_dumppoints</code>. */
  public static Result<StDumppointsRecord> ST_DUMPPOINTS(
      Configuration configuration, Geometry<?> __1) {
    return configuration
        .dsl()
        .selectFrom(be.wegenenverkeer.geojson.db.tables.StDumppoints.ST_DUMPPOINTS.call(__1))
        .fetch();
  }

  /** Get <code>public.st_dumppoints</code> as a table. */
  public static StDumppoints ST_DUMPPOINTS(Geometry<?> __1) {
    return be.wegenenverkeer.geojson.db.tables.StDumppoints.ST_DUMPPOINTS.call(__1);
  }

  /** Get <code>public.st_dumppoints</code> as a table. */
  public static StDumppoints ST_DUMPPOINTS(Field<Geometry<?>> __1) {
    return be.wegenenverkeer.geojson.db.tables.StDumppoints.ST_DUMPPOINTS.call(__1);
  }

  /** The table <code>public.st_dumprings</code>. */
  public static final StDumprings ST_DUMPRINGS = StDumprings.ST_DUMPRINGS;

  /** Call <code>public.st_dumprings</code>. */
  public static Result<StDumpringsRecord> ST_DUMPRINGS(
      Configuration configuration, Geometry<?> __1) {
    return configuration
        .dsl()
        .selectFrom(be.wegenenverkeer.geojson.db.tables.StDumprings.ST_DUMPRINGS.call(__1))
        .fetch();
  }

  /** Get <code>public.st_dumprings</code> as a table. */
  public static StDumprings ST_DUMPRINGS(Geometry<?> __1) {
    return be.wegenenverkeer.geojson.db.tables.StDumprings.ST_DUMPRINGS.call(__1);
  }

  /** Get <code>public.st_dumprings</code> as a table. */
  public static StDumprings ST_DUMPRINGS(Field<Geometry<?>> __1) {
    return be.wegenenverkeer.geojson.db.tables.StDumprings.ST_DUMPRINGS.call(__1);
  }

  /** The table <code>public.st_hexagongrid</code>. */
  public static final StHexagongrid ST_HEXAGONGRID = StHexagongrid.ST_HEXAGONGRID;

  /** Call <code>public.st_hexagongrid</code>. */
  public static Result<StHexagongridRecord> ST_HEXAGONGRID(
      Configuration configuration, Double size, Geometry<?> bounds) {
    return configuration
        .dsl()
        .selectFrom(
            be.wegenenverkeer.geojson.db.tables.StHexagongrid.ST_HEXAGONGRID.call(size, bounds))
        .fetch();
  }

  /** Get <code>public.st_hexagongrid</code> as a table. */
  public static StHexagongrid ST_HEXAGONGRID(Double size, Geometry<?> bounds) {
    return be.wegenenverkeer.geojson.db.tables.StHexagongrid.ST_HEXAGONGRID.call(size, bounds);
  }

  /** Get <code>public.st_hexagongrid</code> as a table. */
  public static StHexagongrid ST_HEXAGONGRID(Field<Double> size, Field<Geometry<?>> bounds) {
    return be.wegenenverkeer.geojson.db.tables.StHexagongrid.ST_HEXAGONGRID.call(size, bounds);
  }

  /** The table <code>public.st_squaregrid</code>. */
  public static final StSquaregrid ST_SQUAREGRID = StSquaregrid.ST_SQUAREGRID;

  /** Call <code>public.st_squaregrid</code>. */
  public static Result<StSquaregridRecord> ST_SQUAREGRID(
      Configuration configuration, Double size, Geometry<?> bounds) {
    return configuration
        .dsl()
        .selectFrom(
            be.wegenenverkeer.geojson.db.tables.StSquaregrid.ST_SQUAREGRID.call(size, bounds))
        .fetch();
  }

  /** Get <code>public.st_squaregrid</code> as a table. */
  public static StSquaregrid ST_SQUAREGRID(Double size, Geometry<?> bounds) {
    return be.wegenenverkeer.geojson.db.tables.StSquaregrid.ST_SQUAREGRID.call(size, bounds);
  }

  /** Get <code>public.st_squaregrid</code> as a table. */
  public static StSquaregrid ST_SQUAREGRID(Field<Double> size, Field<Geometry<?>> bounds) {
    return be.wegenenverkeer.geojson.db.tables.StSquaregrid.ST_SQUAREGRID.call(size, bounds);
  }

  /** The table <code>public.st_subdivide</code>. */
  public static final StSubdivide ST_SUBDIVIDE = StSubdivide.ST_SUBDIVIDE;

  /** Call <code>public.st_subdivide</code>. */
  public static Result<StSubdivideRecord> ST_SUBDIVIDE(
      Configuration configuration, Geometry<?> geom, Integer maxvertices, Double gridsize) {
    return configuration
        .dsl()
        .selectFrom(
            be.wegenenverkeer.geojson.db.tables.StSubdivide.ST_SUBDIVIDE.call(
                geom, maxvertices, gridsize))
        .fetch();
  }

  /** Get <code>public.st_subdivide</code> as a table. */
  public static StSubdivide ST_SUBDIVIDE(Geometry<?> geom, Integer maxvertices, Double gridsize) {
    return be.wegenenverkeer.geojson.db.tables.StSubdivide.ST_SUBDIVIDE.call(
        geom, maxvertices, gridsize);
  }

  /** Get <code>public.st_subdivide</code> as a table. */
  public static StSubdivide ST_SUBDIVIDE(
      Field<Geometry<?>> geom, Field<Integer> maxvertices, Field<Double> gridsize) {
    return be.wegenenverkeer.geojson.db.tables.StSubdivide.ST_SUBDIVIDE.call(
        geom, maxvertices, gridsize);
  }
}