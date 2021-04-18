package clazz

interface Clickable {
    fun click()

    // default 메소드는 자바와 달리 별도 default 키워드가 필요 없다.
    fun showOff() = println("I'm clickable")

}