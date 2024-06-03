CREATE TABLE snapshot(
  id TEXT NOT NULL,
  "snapshot" jsonb NOT NULL,
  "sequence" bigint NOT NULL,
  "type" TEXT NOT NULL,
  PRIMARY KEY("type", id)
);

