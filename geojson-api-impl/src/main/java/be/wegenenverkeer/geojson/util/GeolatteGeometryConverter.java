package be.wegenenverkeer.geojson.util;

import org.geolatte.geom.ByteBuffer;
import org.geolatte.geom.Geometry;
import org.geolatte.geom.codec.Wkb;
import org.geolatte.geom.codec.WkbDecoder;
import org.geolatte.geom.codec.Wkt;
import org.geolatte.geom.codec.WktDecoder;
import org.geolatte.geom.codec.WktEncoder;
import org.jetbrains.annotations.NotNull;
import org.jooq.Converter;

public class GeolatteGeometryConverter implements Converter<Object, Geometry<?>> {
  private ThreadLocal<WktEncoder> wktWriterHolder =
      ThreadLocal.withInitial(() -> Wkt.newEncoder(Wkt.Dialect.POSTGIS_EWKT_1));
  private ThreadLocal<WktDecoder> wktReaderHolder =
      ThreadLocal.withInitial(() -> Wkt.newDecoder(Wkt.Dialect.POSTGIS_EWKT_1));
  private ThreadLocal<WkbDecoder> wkbReaderHolder =
      ThreadLocal.withInitial(() -> Wkb.newDecoder(Wkb.Dialect.POSTGIS_EWKB_1));

  @Override
  public Geometry<?> from(Object databaseObject) {
    return fromLiteral(databaseObject);
  }

  @Override
  public String to(Geometry<?> userObject) {
    return toLiteral(userObject);
  }

  @Override
  public @NotNull Class<Object> fromType() {
    return Object.class;
  }

  @SuppressWarnings("unchecked")
  @Override
  public @NotNull Class<Geometry<?>> toType() {
    return (Class<Geometry<?>>) (Object) Geometry.class;
  }

  private String toLiteral(Geometry<?> geom) {
    return wktWriterHolder.get().encode(geom);
  }

  private Geometry<?> fromLiteral(Object value) {
    if (value == null) {
      return null;
    } else if (value.toString().startsWith("00") || value.toString().startsWith("01")) {
      return fromBytes(value.toString().getBytes());
    } else {
      return wktReaderHolder.get().decode(value.toString());
    }
  }

  private Geometry<?> fromBytes(byte[] bytes) {
    return wkbReaderHolder.get().decode(ByteBuffer.from(bytes));
  }
}
