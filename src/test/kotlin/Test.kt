import ru.DmN.pht.module.utils.asFilesSequence

object Test {
    @JvmStatic
    fun main(args: Array<String>) {
        val iter = listOf("src/*").asFilesSequence("test").iterator()
        println(iter.hasNext())
        println(iter.next())
        println(iter.hasNext())
    }
}