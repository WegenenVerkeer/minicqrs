CREATE TABLE projection_offset(
  projection TEXT NOT NULL,
  shard bigint NOT NULL,
  "sequence" bigint NOT NULL,
  PRIMARY KEY(projection, shard)
);

