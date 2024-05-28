FROM 162510209540.dkr.ecr.eu-west-1.amazonaws.com/rood/postgres15

ENV PGPASSWORD docker

USER root

ADD minicqrs.sql.gz /tmp/app.sql.gz

RUN /etc/init.d/postgresql start 15 &&\
    psql -U docker -d postgres -h 0.0.0.0 --command "CREATE USER minicqrs WITH SUPERUSER PASSWORD 'minicqrs';" &&\
    createdb -E UTF8 -U docker -h 0.0.0.0 -O minicqrs minicqrs --template template0  &&\
    psql -U docker -d minicqrs -h 0.0.0.0 --command "create extension hstore;" &&\
    psql -U docker -d minicqrs -h 0.0.0.0 --command "create extension postgis;" &&\
    createdb -E UTF8 -U docker -h 0.0.0.0 -O minicqrs minicqrs-test --template template0 &&\
    psql -U docker -d minicqrs-test -h 0.0.0.0 --command "create extension hstore;" &&\
    zcat /tmp/app.sql.gz | psql -U docker -h 0.0.0.0 minicqrs && \
    /etc/init.d/postgresql stop && \
    rm -f /tmp/*gz

USER postgres

# Set the default command to run when starting the container
CMD ["/usr/lib/postgresql/15/bin/postgres", "-D", "/var/lib/postgresql/15/main", "-c", "config_file=/etc/postgresql/15/main/postgresql.conf"]
