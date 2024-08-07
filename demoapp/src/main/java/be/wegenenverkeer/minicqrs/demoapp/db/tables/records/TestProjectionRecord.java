/*
 * This file is generated by jOOQ.
 */
package be.wegenenverkeer.minicqrs.demoapp.db.tables.records;


import be.wegenenverkeer.minicqrs.demoapp.db.tables.TestProjection;

import java.util.UUID;

import org.jooq.Record1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class TestProjectionRecord extends UpdatableRecordImpl<TestProjectionRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.test_projection.id</code>.
     */
    public void setId(UUID value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.test_projection.id</code>.
     */
    public UUID getId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>public.test_projection.counter</code>.
     */
    public void setCounter(Long value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.test_projection.counter</code>.
     */
    public Long getCounter() {
        return (Long) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<UUID> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached TestProjectionRecord
     */
    public TestProjectionRecord() {
        super(TestProjection.TEST_PROJECTION);
    }

    /**
     * Create a detached, initialised TestProjectionRecord
     */
    public TestProjectionRecord(UUID id, Long counter) {
        super(TestProjection.TEST_PROJECTION);

        setId(id);
        setCounter(counter);
        resetChangedOnNotNull();
    }
}
