package be.wegenenverkeer.geojson.util;

import java.sql.SQLException;
import org.geolatte.geom.Geometry;
import org.jetbrains.annotations.NotNull;
import org.jooq.Binding;
import org.jooq.BindingGetResultSetContext;
import org.jooq.BindingGetSQLInputContext;
import org.jooq.BindingGetStatementContext;
import org.jooq.BindingRegisterContext;
import org.jooq.BindingSQLContext;
import org.jooq.BindingSetSQLOutputContext;
import org.jooq.BindingSetStatementContext;
import org.jooq.Converter;
import org.jooq.impl.DSL;

public class GeolatteGeometryBinding implements Binding<Object, Geometry<?>> {
  GeolatteGeometryConverter converter = new GeolatteGeometryConverter();

  @Override
  public @NotNull Converter<Object, Geometry<?>> converter() {
    return converter;
  }

  @Override
  public void sql(BindingSQLContext<Geometry<?>> ctx) throws SQLException {
    ctx.render().visit(DSL.sql("?::geometry"));
  }

  @Override
  public void register(BindingRegisterContext<Geometry<?>> ctx) throws SQLException {
    throw new UnsupportedOperationException("Unimplemented method 'register'");
  }

  @Override
  public void set(BindingSetStatementContext<Geometry<?>> ctx) throws SQLException {
    ctx.statement().setObject(ctx.index(), ctx.convert(converter()).value());
  }

  @Override
  public void set(BindingSetSQLOutputContext<Geometry<?>> ctx) throws SQLException {
    throw new UnsupportedOperationException("Unimplemented method 'set'");
  }

  @Override
  public void get(BindingGetResultSetContext<Geometry<?>> ctx) throws SQLException {
    ctx.convert(converter()).value(ctx.resultSet().getString(ctx.index()));
  }

  @Override
  public void get(BindingGetStatementContext<Geometry<?>> ctx) throws SQLException {
    ctx.convert(converter()).value(ctx.statement().getString(ctx.index()));
  }

  @Override
  public void get(BindingGetSQLInputContext<Geometry<?>> ctx) throws SQLException {
    throw new UnsupportedOperationException("Unimplemented method 'get'");
  }
}
