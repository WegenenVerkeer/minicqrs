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
import org.jooq.impl.Internal;
import org.jooq.impl.SQLDataType;

/** This class is generated by jOOQ. */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class StTileenvelope extends AbstractRoutine<Geometry<?>> {

  private static final long serialVersionUID = 1L;

  /** The parameter <code>public.st_tileenvelope.RETURN_VALUE</code>. */
  public static final Parameter<Geometry<?>> RETURN_VALUE =
      Internal.createParameter(
          "RETURN_VALUE", SQLDataType.OTHER, false, false, new GeolatteGeometryConverter());

  /** The parameter <code>public.st_tileenvelope.zoom</code>. */
  public static final Parameter<Integer> ZOOM =
      Internal.createParameter("zoom", SQLDataType.INTEGER, false, false);

  /** The parameter <code>public.st_tileenvelope.x</code>. */
  public static final Parameter<Integer> X =
      Internal.createParameter("x", SQLDataType.INTEGER, false, false);

  /** The parameter <code>public.st_tileenvelope.y</code>. */
  public static final Parameter<Integer> Y =
      Internal.createParameter("y", SQLDataType.INTEGER, false, false);

  /** The parameter <code>public.st_tileenvelope.bounds</code>. */
  public static final Parameter<Geometry<?>> BOUNDS =
      Internal.createParameter(
          "bounds",
          SQLDataType.OTHER.defaultValue(
              DSL.field(
                  DSL.raw(
                      "'0102000020110F00000200000093107C45F81B73C193107C45F81B73C193107C45F81B734193107C45F81B7341'::geometry"),
                  SQLDataType.OTHER)),
          true,
          false,
          new GeolatteGeometryConverter());

  /** The parameter <code>public.st_tileenvelope.margin</code>. */
  public static final Parameter<Double> MARGIN =
      Internal.createParameter(
          "margin",
          SQLDataType.DOUBLE.defaultValue(DSL.field(DSL.raw("0.0"), SQLDataType.DOUBLE)),
          true,
          false);

  /** Create a new routine call instance */
  public StTileenvelope() {
    super("st_tileenvelope", Public.PUBLIC, SQLDataType.OTHER, new GeolatteGeometryConverter());

    setReturnParameter(RETURN_VALUE);
    addInParameter(ZOOM);
    addInParameter(X);
    addInParameter(Y);
    addInParameter(BOUNDS);
    addInParameter(MARGIN);
  }

  /** Set the <code>zoom</code> parameter IN value to the routine */
  public void setZoom(Integer value) {
    setValue(ZOOM, value);
  }

  /**
   * Set the <code>zoom</code> parameter to the function to be used with a {@link org.jooq.Select}
   * statement
   */
  public void setZoom(Field<Integer> field) {
    setField(ZOOM, field);
  }

  /** Set the <code>x</code> parameter IN value to the routine */
  public void setX(Integer value) {
    setValue(X, value);
  }

  /**
   * Set the <code>x</code> parameter to the function to be used with a {@link org.jooq.Select}
   * statement
   */
  public void setX(Field<Integer> field) {
    setField(X, field);
  }

  /** Set the <code>y</code> parameter IN value to the routine */
  public void setY(Integer value) {
    setValue(Y, value);
  }

  /**
   * Set the <code>y</code> parameter to the function to be used with a {@link org.jooq.Select}
   * statement
   */
  public void setY(Field<Integer> field) {
    setField(Y, field);
  }

  /** Set the <code>bounds</code> parameter IN value to the routine */
  public void setBounds(Geometry<?> value) {
    setValue(BOUNDS, value);
  }

  /**
   * Set the <code>bounds</code> parameter to the function to be used with a {@link org.jooq.Select}
   * statement
   */
  public void setBounds(Field<Geometry<?>> field) {
    setField(BOUNDS, field);
  }

  /** Set the <code>margin</code> parameter IN value to the routine */
  public void setMargin(Double value) {
    setValue(MARGIN, value);
  }

  /**
   * Set the <code>margin</code> parameter to the function to be used with a {@link org.jooq.Select}
   * statement
   */
  public void setMargin(Field<Double> field) {
    setField(MARGIN, field);
  }
}