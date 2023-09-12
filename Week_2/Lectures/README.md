# Basics of Java

## DataTypes in Java:
### Primitive:
The objects that are predefined: boolean, char, byte, short, int, long.
### Non-Primitive:
Predefined datatypes like: arrays.
### Size of DataTypes:
1. byte -> 8 bits -> [-128 to 127]
2. short -> 16 bits -> [-32768 to 32767]
3. int -> 32 bits -> [-(2^31) to (2^31) - 1]
4. long -> 64 bits
5. float -> 32 bits
6. double -> 64 bits
7. char -> 16 bits
8. boolean -> Only true or false.

*When ever we try to go out of bounds we get an error while compiling.
There are no direct ways to check the size in Java. The data size is completely abstracted out.*

### User-defined data types:
Here we have classes that are defined by the programmer when he/she creates a program.

### Concept of Literals:
Here we use basics of representing data.
- int: General Numbers
- float: General Numbers with decimal points
- Char: Anything in between single quotes.
- Strings: Anything with double quotes.

### Type Casting: Converitng between datatypes.
Two types of type casting:
#### Implicit:
- Also called as Widening
- Generally not adviced as it can lead to Run-time errors.
- Smaller to Larger data Types.
- No Loss of data.
- ```int -> float```

#### Explicit:
- Possible loss of data.
- ```float -> int```
- Narrowing.
- Also can lead to Run-Time-Errors.

#### Advatages and Dis-advantages of TypeCasting.
