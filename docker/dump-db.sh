#!/bin/bash

export PGPASSWORD=minicqrs
pg_dump -h db -p 5432 -U minicqrs minicqrs  | gzip > minicqrs-postgres/minicqrs.dmp.gz
``