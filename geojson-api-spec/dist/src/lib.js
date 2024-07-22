import { createTypeSpecLibrary } from "@typespec/compiler";
export const $lib = createTypeSpecLibrary({
    name: "geojson-api-spec",
    diagnostics: {},
});
// Optional but convenient, these are meant to be used locally in your library.
export const { reportDiagnostic, createDiagnostic } = $lib;
//# sourceMappingURL=lib.js.map