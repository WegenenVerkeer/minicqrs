CREATE TABLE snapshot(
  id UUID NOT NULL,
  "snapshot" jsonb NOT NULL,
  "sequence" bigint NOT NULL,
  "type" TEXT NOT NULL,
  PRIMARY KEY("type", id)
);

