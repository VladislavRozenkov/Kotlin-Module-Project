import java.util.Scanner

class NotesApp {
    private val archives = mutableListOf<ArchiveNote>()
    private val scanner = Scanner(System.`in`)

    private fun entering(heading: String): String {
        while (true) {
            print(heading)
            val input = scanner.nextLine().trim()
            if (input.isNotEmpty()) {
                return input
            }
            println("Поле не может быть пустым.")
        }
    }

    private fun createArchive() {
        val name = entering(" Название архива: ")
        archives.add(ArchiveNote(name))
    }

    private fun showArchive(archive: ArchiveNote) {
        val menu = Menu(
            name = "${archive.name}",
            list = archive.notes,
            nameOutput = {it.name},
            create = {createNote(archive)},
            selected =  {note -> showNote(note)},
            exit = {}
        )
        menu.show()
    }

    private  fun createNote(archive: ArchiveNote) {
        val name = entering("Название заметки: ")
        val content = entering("Введите содержание: ")
        archive.notes.add(Note(name, content))
    }

    private fun showNote(note: Note) {
        println("Заметка: ${note.name}")
        println(note.content)
        println("Нажмите Enter для возврата")
        scanner.nextLine()
    }

    fun start() {
        val menu = Menu(
            name = "Список архивов: ",
            list = archives,
            nameOutput = {it.name},
            create = {createArchive()},
            selected = {archive -> showArchive(archive)},
            exit = {println("Выход")}
        )
        menu.show()
    }
}