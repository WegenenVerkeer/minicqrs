/*
 * This file is generated by jOOQ.
 */
package be.wegenenverkeer.geojson.db.routines;

import be.wegenenverkeer.geojson.db.Public;
import org.jooq.Parameter;
import org.jooq.impl.AbstractRoutine;
import org.jooq.impl.Internal;
import org.jooq.impl.SQLDataType;

/** This class is generated by jOOQ. */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class PostgisExtensionsUpgrade extends AbstractRoutine<String> {

  private static final long serialVersionUID = 1L;

  /** The parameter <code>public.postgis_extensions_upgrade.RETURN_VALUE</code>. */
  public static final Parameter<String> RETURN_VALUE =
      Internal.createParameter("RETURN_VALUE", SQLDataType.CLOB, false, false);

  /** Create a new routine call instance */
  public PostgisExtensionsUpgrade() {
    super("postgis_extensions_upgrade", Public.PUBLIC, SQLDataType.CLOB);

    setReturnParameter(RETURN_VALUE);
  }
}
