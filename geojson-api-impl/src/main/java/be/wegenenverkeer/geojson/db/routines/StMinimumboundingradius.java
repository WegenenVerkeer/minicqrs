/*
 * This file is generated by jOOQ.
 */
package be.wegenenverkeer.geojson.db.routines;

import be.wegenenverkeer.geojson.db.Public;
import be.wegenenverkeer.geojson.util.GeolatteGeometryConverter;
import org.geolatte.geom.Geometry;
import org.jooq.Parameter;
import org.jooq.impl.AbstractRoutine;
import org.jooq.impl.Internal;
import org.jooq.impl.SQLDataType;

/** This class is generated by jOOQ. */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class StMinimumboundingradius extends AbstractRoutine<java.lang.Void> {

  private static final long serialVersionUID = 1L;

  /** The parameter <code>public.st_minimumboundingradius._1</code>. */
  public static final Parameter<Geometry<?>> _1 =
      Internal.createParameter(
          "_1", SQLDataType.OTHER, false, true, new GeolatteGeometryConverter());

  /** The parameter <code>public.st_minimumboundingradius.center</code>. */
  public static final Parameter<Geometry<?>> CENTER =
      Internal.createParameter(
          "center", SQLDataType.OTHER, false, false, new GeolatteGeometryConverter());

  /** The parameter <code>public.st_minimumboundingradius.radius</code>. */
  public static final Parameter<Double> RADIUS =
      Internal.createParameter("radius", SQLDataType.DOUBLE, false, false);

  /** Create a new routine call instance */
  public StMinimumboundingradius() {
    super("st_minimumboundingradius", Public.PUBLIC);

    addInParameter(_1);
    addOutParameter(CENTER);
    addOutParameter(RADIUS);
  }

  /** Set the <code>_1</code> parameter IN value to the routine */
  public void set__1(Geometry<?> value) {
    setValue(_1, value);
  }

  /** Get the <code>center</code> parameter OUT value from the routine */
  public Geometry<?> getCenter() {
    return get(CENTER);
  }

  /** Get the <code>radius</code> parameter OUT value from the routine */
  public Double getRadius() {
    return get(RADIUS);
  }
}
