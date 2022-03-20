package enums

import classes.Vehicle
import java.util.*

enum class InstanceCreator(private val creator : (Scanner) -> Vehicle?) : (Scanner) -> Vehicle? {
    CREATE_FROM_FILE({scanner -> commands.instanceCreate(scanner.nextLine(), scanner.nextLine(), scanner.nextLine(),
    scanner.nextLine(), scanner.nextLine(), scanner.nextLine(), 1)}),
    CREATE_WITH_INPUT({commands.instanceCreate(3)});

    override operator fun invoke(scanner: Scanner) = creator(scanner)
}