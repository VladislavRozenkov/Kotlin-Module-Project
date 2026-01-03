import java.util.Scanner

class Menu<T>(
    private val name: String,
    private val list: List<T>,
    private val nameOutput: (T) -> String,
    private val create: () -> Unit,
    private val selected: (T) -> Unit,
    private val exit: () -> Unit
) {
    private val scanner = Scanner(System.`in`)

    fun show() {
        while (true) {
            println("$name")
            println("0. Создать")

            list.forEachIndexed { index, t ->
                println("${index + 1}. ${nameOutput(t)}")
            }

            println("${list.size + 1}. Выход")
            print("Выберите пункт: ")

            val input = scanner.nextLine()

            val сheck = input.toIntOrNull()

            when {
                сheck == null -> {println("Введите число.")}
                сheck == 0 -> {create()}
                сheck in 1..list.size -> {selected(list[сheck - 1])}
                сheck == list.size + 1 -> {
                    exit()
                    return
                }
                else -> {println("Ошибка. Выберите пункт.")}
            }
        }
    }

}