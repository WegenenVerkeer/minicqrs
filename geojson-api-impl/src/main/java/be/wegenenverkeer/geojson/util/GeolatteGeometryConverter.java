package be.wegenenverkeer.geojson.util;

import org.geolatte.geom.ByteBuffer;
import org.geolatte.geom.ByteOrder;
import org.geolatte.geom.Geometry;
import org.geolatte.geom.codec.Wkb;
import org.geolatte.geom.codec.WkbDecoder;
import org.geolatte.geom.codec.WkbEncoder;
import org.geolatte.geom.codec.Wkt;
import org.geolatte.geom.codec.WktDecoder;
import org.jetbrains.annotations.NotNull;
import org.jooq.Converter;

public class GeolatteGeometryConverter implements Converter<Object, Geometry<?>> {
  private ThreadLocal<WkbEncoder> wkbWriterHolder =
      ThreadLocal.withInitial(
          () -> {
            return Wkb.newEncoder(org.geolatte.geom.codec.Wkb.Dialect.POSTGIS_EWKB_1);
          });
  private ThreadLocal<WktDecoder> wktReaderHolder =
      ThreadLocal.withInitial(() -> Wkt.newDecoder(Wkt.Dialect.POSTGIS_EWKT_1));
  private ThreadLocal<WkbDecoder> wkbReaderHolder =
      ThreadLocal.withInitial(() -> Wkb.newDecoder(Wkb.Dialect.POSTGIS_EWKB_1));

  @Override
  public Geometry<?> from(Object obj) {
    return obj == null ? null : fromLiteral(obj.toString());
  }

  @Override
  public Object to(Geometry<?> geom) {
    return geom == null ? null : toBytes(geom);
  }

  @Override
  public @NotNull Class<Object> fromType() {
    return Object.class;
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  @Override
  public @NotNull Class<Geometry<?>> toType() {
    return (Class) Geometry.class;
  }

  private byte[] toBytes(Geometry<?> geom) {
    return ((WkbEncoder) this.wkbWriterHolder.get()).encode(geom, ByteOrder.NDR).toByteArray();
  }

  private Geometry<?> fromLiteral(String value) {
    return !value.startsWith("00") && !value.startsWith("01")
        ? ((WktDecoder) this.wktReaderHolder.get()).decode(value)
        : this.fromBytes(ByteBuffer.from(value));
  }

  private Geometry<?> fromBytes(ByteBuffer bytes) {
    return wkbReaderHolder.get().decode(bytes);
  }
}
