# Java JSON Parser — From Scratch, Zero Dependencies

I built a complete JSON parser and serializer from scratch in Java 21 — the library itself has zero runtime dependencies (JUnit is used only for tests). It is a recursive-descent parser over a sealed JsonValue type (objects, arrays, strings, numbers, booleans, null), with full string-escape and unicode handling, exponent numbers, round-trip stringification, and a dedicated JsonParseException for malformed input instead of leaking JDK exceptions.

## Highlights

- Json.parse / Json.stringify, JsonValue sealed hierarchy, programmatic builders.
- Java 21, Maven, JUnit 5.
- I wrote this as a Software Engineer to demonstrate the underlying mechanics, not just the APIs — everything here is implemented from first principles.

## Build and test

```bash
export JAVA_HOME=/path/to/jdk-21
mvn verify
```

## Verification

`mvn verify` — **Tests run: 9, Failures: 0, Errors: 0** (verified locally before publishing).

## License

MIT — see [LICENSE](LICENSE). Copyright (c) 2026 Prasad Dimba Anandappa.
