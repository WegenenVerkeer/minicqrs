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
public class StLinefromencodedpolyline extends AbstractRoutine<Geometry<?>> {

  private static final long serialVersionUID = 1L;

  /** The parameter <code>public.st_linefromencodedpolyline.RETURN_VALUE</code>. */
  public static final Parameter<Geometry<?>> RETURN_VALUE =
      Internal.createParameter(
          "RETURN_VALUE", SQLDataType.OTHER, false, false, new GeolatteGeometryConverter());

  /** The parameter <code>public.st_linefromencodedpolyline.txtin</code>. */
  public static final Parameter<String> TXTIN =
      Internal.createParameter("txtin", SQLDataType.CLOB, false, false);

  /** The parameter <code>public.st_linefromencodedpolyline.nprecision</code>. */
  public static final Parameter<Integer> NPRECISION =
      Internal.createParameter(
          "nprecision",
          SQLDataType.INTEGER.defaultValue(DSL.field(DSL.raw("5"), SQLDataType.INTEGER)),
          true,
          false);

  /** Create a new routine call instance */
  public StLinefromencodedpolyline() {
    super(
        "st_linefromencodedpolyline",
        Public.PUBLIC,
        SQLDataType.OTHER,
        new GeolatteGeometryConverter());

    setReturnParameter(RETURN_VALUE);
    addInParameter(TXTIN);
    addInParameter(NPRECISION);
  }

  /** Set the <code>txtin</code> parameter IN value to the routine */
  public void setTxtin(String value) {
    setValue(TXTIN, value);
  }

  /**
   * Set the <code>txtin</code> parameter to the function to be used with a {@link org.jooq.Select}
   * statement
   */
  public void setTxtin(Field<String> field) {
    setField(TXTIN, field);
  }

  /** Set the <code>nprecision</code> parameter IN value to the routine */
  public void setNprecision(Integer value) {
    setValue(NPRECISION, value);
  }

  /**
   * Set the <code>nprecision</code> parameter to the function to be used with a {@link
   * org.jooq.Select} statement
   */
  public void setNprecision(Field<Integer> field) {
    setField(NPRECISION, field);
  }
}
