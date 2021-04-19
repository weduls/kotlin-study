package student

// getter, setter가 생기지 않게 하려면 const를 붙인다. (static final과 동일함)
const val count = 2220;

class Student(
        // 기본 타입은 val
        input: Int
) {
    val data: Int = input;
}