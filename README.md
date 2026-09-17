@startuml
class ConversorNotacion {
    + infijaAPostfija(expresion: String): String
    - esOperador(c: char): boolean
    - obtenerPrecedencia(operador: char): int
}
@enduml
