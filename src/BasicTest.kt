import static.CompanionObject
import static.staticMethod
import theory.*
import java.lang.StringBuilder
import kotlin.random.Random

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
    println(joinToStringFuc(listOf(1, 2, 3), ",", postfix = "postfix"))

    // static
    println(staticMethod("123"));

    // 객체 (객체 프로퍼티에 자동으로 getter, setter 생성됨)
    val z = Student(11);
    println(z.data)

    // 코틀린 상수
    println(count)

    // vararg (가변인자)
    // 코틀린에서는 가변인자의 array를 보내려면 풀어서 보내야 한다. 이때 *를 사용하면 됨
    val array = arrayOf("a", "b", "c")
    println(manyVar(*array));

    // 문자열 처리
    println(parsePath("/Users/wedul/test.txt"))

    // 싱글톤 테스트
    println(SingleToneTest.test())

    // 스태틱 클래스
    val companionObject = CompanionObject.User("wedul");
    println(companionObject._name)

    // 최상위 함수 참조
    run(::saluate)

    /**
     * 람다 테스트
    */
    // filter
    val list = listOf(1,2,3,4)
    println(list.filter { it % 2 == 0 })

    // flatmap
    val string = listOf("abc", "def")
    println(string.flatMap { it.toList() })

    // with 사용
    println(alphabet())

    // when
    println(recognize('8'))

    // apply
    println(digitWithApply());

    // nullable function
    printUpperWithNullable("abc")
    printUpperWithNullable(null)

    // property nullable
    val ceo = Employee("wedul", null)
    println(ceo.managerName(ceo))

    // 엘비스 연산자
    strLenSafe(null)
    strLenSafe("wedul")

    // 캐스팅 시 에러나면 엘비스 표현식 되는지 테스트
    println(castingNullable("abc"))
    println(castingNullable("123"))

    // ignore null
    println(ignoreNulls("wedul"))

    // 문자열 템플릿
    val name = "wedul"
    println("name : $name")

    val em = Wedul("wedul", 21);
    // getter
    println(em.name);
    println(em.age)
    // custom getter
    println(em.getAge)
    // setter (val 타입이라 setter 안됨)
//    em.name = "babo"
    // var type은 setter 가능
    em.address = "seoul kangdong"

    // enum
    println(Food.BANANA.cal)

    // when test
    println(whenExistParameterTest(1))
    println(whenNotExistParameterTest())

    // 타입 체크
    val variable = "123";
    println(variable is String)

    // smart casting 테스트
    println(smartCastTest(10))

    // let 테스트
    val nullableStr: String? = "dd";
    // null이 될 수 있는 데이터는 인자로 사용할 수 없다. (컴파일 에러)
    //    println(smartCastTest(nullableStr))

    // let 함수를 이용해서 nullable한 데이터를 안전하게 사용
    nullableStr?.let { str -> println(smartCastTest(str)) }

    // 함수에 반환형에도 let 사용 가능
    whenNotExistParameterTest()?.let { d -> println(d) }

    // null이 될 수 있는 타입에 대한 확장을 정의
    println(null.isNullOrBlank())

    println(printHashCode(123))
}

// generic null이 될 수 없는 함수
fun <T: Any> printHashCode(t: T) {
    println(t.hashCode())
}

// null이 될 수 있는 타입에 대한 확장함수
fun String?.isNullOrBlank(): Boolean  = this == null || this.isBlank()

fun smartCastTest(a : Any): Int {
    if (a is Int) {
        return 1 + a
    }
    return -1
}

fun whenNotExistParameterTest(): Int {
    val random = Random(10);

    return when {
        random.nextInt() > 5 -> 10
        random.nextInt() < 3 -> 20
        else -> 30
    }
}

fun whenExistParameterTest(a: Int) = when (a) {
    1 -> "wedul"
    2 -> "chul"
    else -> "babo"
}

enum class Food(val cal: Int) {
    BANANA(100)
}

class Wedul(val name: String, val age: Int, var address: String = "seoul") {
    val getAge: Int
        get() {
            return age + 10
        }
}

fun ignoreNulls(s: String?) {
    val sNotNull: String = s!!
    println(sNotNull.length)
}

fun castingNullable(s: Any?) = s as? Int ?: false

fun strLenSafe(s: String?): Int = s?.length ?: 0

fun printUpperWithNullable(s: String?) {
    // ?.는 if(s != null) s.toUpperCase() else null 과 동일.
    val allCaps: String? = s?.toUpperCase()
    println(allCaps)
}

class Employee(val name: String, val manager: Employee?) {
    fun managerName(employee: Employee): String? = employee.manager?.name
}

// when 사용
fun recognize(c: Char) = when (c) {
    in '0'..'9' -> "It's a digit!"
    in 'a'..'z', in 'A'..'Z' -> "It's a letter!"
    else -> "I don't know"
}

fun digitWithApply() = StringBuilder().apply {
    for (i in 100 downTo 0 step 2) {
        append(i)
    }
}.toString()

// with 사용
fun alphabet() = with(StringBuilder()) {
    for (letter in 'A'..'Z') {
        append(letter)
    }
    toString()
}

fun saluate() {
    println("Salute!")
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