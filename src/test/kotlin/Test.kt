import ru.DmN.phtx.docs.utils.format.format

object Test {
    @JvmStatic
    fun main(args: Array<String>) {
        println(format("Код @b{работает} на @b{всех} @r(https://google.com){платформах}!"))
    }
}