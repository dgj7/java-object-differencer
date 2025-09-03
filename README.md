# Java Object Differencer
## Purpose
![build](https://github.com/dgj7/java-object-differencer/actions/workflows/build.yml/badge.svg)
returns a list of 'differences' between two java objects; intended to be a unit testing tool.

## Internal Testing Priorities
- [x] priority 1
  - [x] built-in primitives (int, float, etc) ([io.dgj7.jod.e2e.builtin.primitive](src/test/java/io/dgj7/jod/e2e/builtin/primitive))
  - [x] built-in types (BigInteger, BigDecimal, etc) ([io.dgj7.jod.e2e.builtin.types](src/test/java/io/dgj7/jod/e2e/builtin/types))
  - [x] object with inherited fields ([io.dgj7.jod.e2e.inherit](src/test/java/io/dgj7/jod/e2e/inherit))
- [x] priority 2
  - [x] small graph containing collection, enum ([io.dgj7.jod.e2e.custom.business](src/test/java/io/dgj7/jod/e2e/custom/business))
  - [x] self-referential graph ([io.dgj7.jod.e2e.btree](src/test/java/io/dgj7/jod/e2e/btree))
- [ ] priority 3
  - [x] collection of base abstract type, containing concrete instances ([io.dgj7.jod.e2e.mixed](src/test/java/io/dgj7/jod/e2e/mixed))
  - [ ] jaxb-generated object graph
- [ ] priority 4
  - [ ] large object graph

## History
- [x] `0.0.x`, `2025-05-11`: create initial project files
- [x] `0.1.x`, `2025-05-11`: initial implementation
- [x] `0.2.x`, `2025-05-11`: initial refactoring and refinement
- [x] `0.3.x`, `2025-05-22`: end-to-end automated tests
- [ ] `0.4.x`: final refactoring and refinement; additional unit tests, if necessary
- [ ] `1.0.0`: release

---
