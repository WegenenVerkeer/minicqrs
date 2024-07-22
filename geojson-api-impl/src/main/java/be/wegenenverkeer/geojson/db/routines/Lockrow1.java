/*
 * This file is generated by jOOQ.
 */
package be.wegenenverkeer.geojson.db.routines;

import be.wegenenverkeer.geojson.db.Public;
import java.time.LocalDateTime;
import org.jooq.Field;
import org.jooq.Parameter;
import org.jooq.impl.AbstractRoutine;
import org.jooq.impl.Internal;
import org.jooq.impl.SQLDataType;

/** This class is generated by jOOQ. */
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class Lockrow1 extends AbstractRoutine<Integer> {

  private static final long serialVersionUID = 1L;

  /** The parameter <code>public.lockrow.RETURN_VALUE</code>. */
  public static final Parameter<Integer> RETURN_VALUE =
      Internal.createParameter("RETURN_VALUE", SQLDataType.INTEGER, false, false);

  /** The parameter <code>public.lockrow._1</code>. */
  public static final Parameter<String> _1 =
      Internal.createParameter("_1", SQLDataType.CLOB, false, true);

  /** The parameter <code>public.lockrow._2</code>. */
  public static final Parameter<String> _2 =
      Internal.createParameter("_2", SQLDataType.CLOB, false, true);

  /** The parameter <code>public.lockrow._3</code>. */
  public static final Parameter<String> _3 =
      Internal.createParameter("_3", SQLDataType.CLOB, false, true);

  /** The parameter <code>public.lockrow._4</code>. */
  public static final Parameter<String> _4 =
      Internal.createParameter("_4", SQLDataType.CLOB, false, true);

  /** The parameter <code>public.lockrow._5</code>. */
  public static final Parameter<LocalDateTime> _5 =
      Internal.createParameter("_5", SQLDataType.LOCALDATETIME(6), false, true);

  /** Create a new routine call instance */
  public Lockrow1() {
    super("lockrow", Public.PUBLIC, SQLDataType.INTEGER);

    setReturnParameter(RETURN_VALUE);
    addInParameter(_1);
    addInParameter(_2);
    addInParameter(_3);
    addInParameter(_4);
    addInParameter(_5);
    setOverloaded(true);
  }

  /** Set the <code>_1</code> parameter IN value to the routine */
  public void set__1(String value) {
    setValue(_1, value);
  }

  /**
   * Set the <code>_1</code> parameter to the function to be used with a {@link org.jooq.Select}
   * statement
   */
  public void set__1(Field<String> field) {
    setField(_1, field);
  }

  /** Set the <code>_2</code> parameter IN value to the routine */
  public void set__2(String value) {
    setValue(_2, value);
  }

  /**
   * Set the <code>_2</code> parameter to the function to be used with a {@link org.jooq.Select}
   * statement
   */
  public void set__2(Field<String> field) {
    setField(_2, field);
  }

  /** Set the <code>_3</code> parameter IN value to the routine */
  public void set__3(String value) {
    setValue(_3, value);
  }

  /**
   * Set the <code>_3</code> parameter to the function to be used with a {@link org.jooq.Select}
   * statement
   */
  public void set__3(Field<String> field) {
    setField(_3, field);
  }

  /** Set the <code>_4</code> parameter IN value to the routine */
  public void set__4(String value) {
    setValue(_4, value);
  }

  /**
   * Set the <code>_4</code> parameter to the function to be used with a {@link org.jooq.Select}
   * statement
   */
  public void set__4(Field<String> field) {
    setField(_4, field);
  }

  /** Set the <code>_5</code> parameter IN value to the routine */
  public void set__5(LocalDateTime value) {
    setValue(_5, value);
  }

  /**
   * Set the <code>_5</code> parameter to the function to be used with a {@link org.jooq.Select}
   * statement
   */
  public void set__5(Field<LocalDateTime> field) {
    setField(_5, field);
  }
}
