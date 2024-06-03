/*
 * This file is generated by jOOQ.
 */
package be.wegenenverkeer.minicqrs.demoapp.db;


import be.wegenenverkeer.minicqrs.demoapp.db.tables.TestProjection;
import be.wegenenverkeer.minicqrs.demoapp.db.tables.records.TestProjectionRecord;

import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables in
 * public.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<TestProjectionRecord> TEST_PROJECTION_PKEY = Internal.createUniqueKey(TestProjection.TEST_PROJECTION, DSL.name("test_projection_pkey"), new TableField[] { TestProjection.TEST_PROJECTION.ID }, true);
}
