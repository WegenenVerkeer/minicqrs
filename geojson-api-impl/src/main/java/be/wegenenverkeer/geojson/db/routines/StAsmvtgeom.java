/*
 * This file is generated by jOOQ.
 */
package be.wegenenverkeer.geojson.db.routines;

import be.wegenenverkeer.geojson.db.Public;
import be.wegenenverkeer.geojson.util.GeolatteGeometryConverter;
import org.geolatte.geom.Geometry;
import org.jooq.Field;
import org.jooq.Parameter;
import org.jooq.impl.AbstractRoutine;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultDataType;
import org.jooq.impl.Internal;
import org.jooq.impl.SQLDataType;

/** This class is generated by jOOQ. */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class StAsmvtgeom extends AbstractRoutine<Geometry<?>> {

  private static final long serialVersionUID = 1L;

  /** The parameter <code>public.st_asmvtgeom.RETURN_VALUE</code>. */
  public static final Parameter<Geometry<?>> RETURN_VALUE =
      Internal.createParameter(
          "RETURN_VALUE", SQLDataType.OTHER, false, false, new GeolatteGeometryConverter());

  /** The parameter <code>public.st_asmvtgeom.geom</code>. */
  public static final Parameter<Geometry<?>> GEOM =
      Internal.createParameter(
          "geom", SQLDataType.OTHER, false, false, new GeolatteGeometryConverter());

  /**
   * @deprecated Unknown data type. If this is a qualified, user-defined type, it may have been
   *     excluded from code generation. If this is a built-in type, you can define an explicit
   *     {@link org.jooq.Binding} to specify how this type should be handled. Deprecation can be
   *     turned off using {@literal <deprecationOnUnknownTypes/>} in your code generator
   *     configuration.
   */
  @Deprecated
  public static final Parameter<Object> BOUNDS =
      Internal.createParameter(
          "bounds", DefaultDataType.getDefaultDataType("\"public\".\"box2d\""), false, false);

  /** The parameter <code>public.st_asmvtgeom.extent</code>. */
  public static final Parameter<Integer> EXTENT =
      Internal.createParameter(
          "extent",
          SQLDataType.INTEGER.defaultValue(DSL.field(DSL.raw("4096"), SQLDataType.INTEGER)),
          true,
          false);

  /** The parameter <code>public.st_asmvtgeom.buffer</code>. */
  public static final Parameter<Integer> BUFFER =
      Internal.createParameter(
          "buffer",
          SQLDataType.INTEGER.defaultValue(DSL.field(DSL.raw("256"), SQLDataType.INTEGER)),
          true,
          false);

  /** The parameter <code>public.st_asmvtgeom.clip_geom</code>. */
  public static final Parameter<Boolean> CLIP_GEOM =
      Internal.createParameter(
          "clip_geom",
          SQLDataType.BOOLEAN.defaultValue(DSL.field(DSL.raw("true"), SQLDataType.BOOLEAN)),
          true,
          false);

  /** Create a new routine call instance */
  public StAsmvtgeom() {
    super("st_asmvtgeom", Public.PUBLIC, SQLDataType.OTHER, new GeolatteGeometryConverter());

    setReturnParameter(RETURN_VALUE);
    addInParameter(GEOM);
    addInParameter(BOUNDS);
    addInParameter(EXTENT);
    addInParameter(BUFFER);
    addInParameter(CLIP_GEOM);
  }

  /** Set the <code>geom</code> parameter IN value to the routine */
  public void setGeom(Geometry<?> value) {
    setValue(GEOM, value);
  }

  /**
   * Set the <code>geom</code> parameter to the function to be used with a {@link org.jooq.Select}
   * statement
   */
  public void setGeom(Field<Geometry<?>> field) {
    setField(GEOM, field);
  }

  /** Set the <code>bounds</code> parameter IN value to the routine */
  public void setBounds(Object value) {
    setValue(BOUNDS, value);
  }

  /**
   * Set the <code>bounds</code> parameter to the function to be used with a {@link org.jooq.Select}
   * statement
   */
  public void setBounds(Field<Object> field) {
    setField(BOUNDS, field);
  }

  /** Set the <code>extent</code> parameter IN value to the routine */
  public void setExtent(Integer value) {
    setValue(EXTENT, value);
  }

  /**
   * Set the <code>extent</code> parameter to the function to be used with a {@link org.jooq.Select}
   * statement
   */
  public void setExtent(Field<Integer> field) {
    setField(EXTENT, field);
  }

  /** Set the <code>buffer</code> parameter IN value to the routine */
  public void setBuffer(Integer value) {
    setValue(BUFFER, value);
  }

  /**
   * Set the <code>buffer</code> parameter to the function to be used with a {@link org.jooq.Select}
   * statement
   */
  public void setBuffer(Field<Integer> field) {
    setField(BUFFER, field);
  }

  /** Set the <code>clip_geom</code> parameter IN value to the routine */
  public void setClipGeom(Boolean value) {
    setValue(CLIP_GEOM, value);
  }

  /**
   * Set the <code>clip_geom</code> parameter to the function to be used with a {@link
   * org.jooq.Select} statement
   */
  public void setClipGeom(Field<Boolean> field) {
    setField(CLIP_GEOM, field);
  }
}
