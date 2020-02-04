Typechecked markdown documentation for Scala
============================================

This project has the [Mdoc](https://scalameta.org/mdoc/).

You could use it for documenting your project

```scala mdoc
val x = 40
val y = 2
x+y
```

```scala mdoc:silent
case class Person(id: Int, name: String)

def changeName(p: Person, newName: String): Person =
  p.copy(name = newName)
```

```scala mdoc
val p = Person(1, "jjOOOOOhnnnnn")
changeName(p, "john")
```

All documentations sources located inside '/docs/mdoc' folder.

To generate documentation run 'sbt mdoc' and see results see '/docs' folder.
