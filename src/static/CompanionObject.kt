package static

class CompanionObject private constructor(val _name: String){
    companion object {
        fun User(name: String): CompanionObject = CompanionObject(name)
    }
}