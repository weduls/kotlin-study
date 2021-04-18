import static.staticMethod
import student.*
import java.lang.StringBuilder

// age를 별도로 지정하지 않을 경우 age는 null
data class Person(val name: String, val age: Int? = null)

fun main(args: Array<String>) {
    val persons = listOf(Person("영희"), Person("철수", age = 29))

    // it를 사용하면 이터레이터로 별도의 파라미터이름을 정의하지 않아도 된다.
    // ?: 엘비스 연산자로써 age가 null이면 0값이 된다.
    val order = persons.maxBy { it.age ?: 0 }
    println(order)

    // 함수도 변수에 선언이 가능
    val callData = data(123);
    println(callData);

    // 함수 호출
    println(joinToStringFuc(listOf(1,2,3), ",", postfix = "postfix"))

    // static
    println(staticMethod("123"));

    // 객체 (객체 프로퍼티에 자동으로 getter, setter 생성됨)
    val z = Student(11);
    println(z.data)

    // 코틀린 상수
    println(count)

    // vararg (가변인자)
    // 코틀린에서는 가변인자의 array를 보내려면 풀어서 보내야 한다. 이때 *를 사용하면 됨
    val array = arrayOf("a","b", "c")
    println(manyVar(*array));

    // 문자열 처리
    println(parsePath("/Users/wedul/test.txt"))

    // 싱글톤 테스트
    println(SingleToneTest.test())
}

// directory pull path parsing 테스트 코드
fun parsePath(path: String): String {
    val directory = path.substringBeforeLast("/")
    val fullName = path.substringAfterLast("/")
    val fileName = fullName.substringBeforeLast(".")
    val extension = fullName.substringAfterLast(".")

    return "Dir: $directory, name : $fileName, ext: $extension"
}

// 가변인자
fun manyVar(vararg values: String): String {
    return values.joinToString();
}

// 코틀린 함수의 기본 인자는 val 타입.
fun data(data: Int): Int {
    return data;
}

fun <T> joinToStringFuc(
        collection: Collection<T>,
        separator: String,
        prefix: String = "prefix",
        postfix: String = "postfix"
): String {
    val result = StringBuilder(prefix);
    for ((index, element) in collection.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }

    result.append(postfix)
    return result.toString()
}