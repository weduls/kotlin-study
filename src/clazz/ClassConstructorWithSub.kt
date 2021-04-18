package clazz

class ClassConstructorWithSub {

    val name: String
    var age: Int = 0

    constructor(_nickName: String) {
        name = _nickName
    }

    constructor(_nickName: String, _age: Int) {
        name = _nickName
        age = _age
    }

}