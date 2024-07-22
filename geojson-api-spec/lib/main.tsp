import "@typespec/openapi3";
namespace geojson;

using OpenAPI;

model NamedCrsProperty {
  name: string;
}

model BaseGeometry {
  bbox?: float64[]
}

model Point extends BaseGeometry {
  type: "Point",
  coordinates: float64[];
}

model MultiPoint extends BaseGeometry {
  type: "MultiPoint",
  coordinates: float64[][];
}

model LineString extends BaseGeometry {
  type: "LineString",
  coordinates: float64[][];
}

model MultiLineString extends BaseGeometry {
  type: "MultiLineString",
  coordinates: float64[][][];
}

model Polygon extends BaseGeometry {
  type: "Polygon",
  coordinates: float64[][][];
}

model MultiPolygon extends BaseGeometry {
  type: "MultiPolygon",
  coordinates: float64[][][][];
}

@oneOf
union Geometry {
  Point;
  MultiPoint;
  LineString;
  MultiLineString;
  Polygon;
  MultiPolygon;
}

model GeoJson<T,I> {
  type: "Feature";
  geometry: Geometry;
  properties: T;
  id: I;
}

