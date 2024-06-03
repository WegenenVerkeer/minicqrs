CREATE SEQUENCE journal_sequence;

CREATE TABLE journal(
  id TEXT NOT NULL,
  "event" jsonb NOT NULL,
  "sequence" bigint NOT NULL,
  global_sequence bigint NOT NULL DEFAULT nextval('journal_sequence'),
  shard bigint NOT NULL,
  occured timestamp NOT NULL,
  "type" TEXT NOT NULL,
  PRIMARY KEY("type", id, "sequence")
);

