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
public class _PostgisDeprecate extends AbstractRoutine<java.lang.Void> {

  private static final long serialVersionUID = 1L;

  /** The parameter <code>public._postgis_deprecate.oldname</code>. */
  public static final Parameter<String> OLDNAME =
      Internal.createParameter("oldname", SQLDataType.CLOB, false, false);

  /** The parameter <code>public._postgis_deprecate.newname</code>. */
  public static final Parameter<String> NEWNAME =
      Internal.createParameter("newname", SQLDataType.CLOB, false, false);

  /** The parameter <code>public._postgis_deprecate.version</code>. */
  public static final Parameter<String> VERSION =
      Internal.createParameter("version", SQLDataType.CLOB, false, false);

  /** Create a new routine call instance */
  public _PostgisDeprecate() {
    super("_postgis_deprecate", Public.PUBLIC);

    addInParameter(OLDNAME);
    addInParameter(NEWNAME);
    addInParameter(VERSION);
  }

  /** Set the <code>oldname</code> parameter IN value to the routine */
  public void setOldname(String value) {
    setValue(OLDNAME, value);
  }

  /** Set the <code>newname</code> parameter IN value to the routine */
  public void setNewname(String value) {
    setValue(NEWNAME, value);
  }

  /** Set the <code>version</code> parameter IN value to the routine */
  public void setVersion(String value) {
    setValue(VERSION, value);
  }
}