package com.asniaire.conquer.domain.board

import com.asniaire.conquer.domain.player.Player
import com.asniaire.conquer.domain.strategy.Coordinates
import kotlinx.coroutines.delay
import kotlinx.coroutines.withTimeout
import kotlin.random.Random
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds
const val TIME_BETWEEN_MOVES_SECONDS = 1


class GameBoard(
    val name: String,
    private val cells: Array<Array<GameCell>>,
    players: List<Player>
) {
    var conqueredCells: Int = 0
        private set

    val width = cells.size
    val height = cells[0].size

    val totalCells = width * height

    val remainingCells: Int
        get() = totalCells - conqueredCells

    val isGameFinished: Boolean = remainingCells == 0

    val players = players.toMutableList()

    private val failuresByPlayer = players.associateWith { 0 }.toMutableMap()
    private val cellsConqueredByPlayer =
        players.associateWith<Player, MutableList<GameCell>> { mutableListOf() }.toMutableMap()

    fun conqueredCellsByUser(player: Player) =
        cellsConqueredByPlayer[player]?.size ?: TODO("Not found")

    fun conquer(coordinates: Coordinates, player: Player) {
        if (!playerInBoard(player)) {
            TODO("Manage errors")
        }

        if (!validPositionInBoard(coordinates)) {
            TODO("Manage errors")
        }

        val gameCell = getCellByCoordinates(coordinates)
        checkIfAlreadyConquered(gameCell, player)

        conquerCell(player, gameCell)
    }

    private fun checkIfAlreadyConquered(gameCell: GameCell, player: Player) {
        if (gameCell.isConquered) {
            incrementFailures(player)
            TODO("Manage errors")
        }
    }

    private fun conquerCell(player: Player, gameCell: GameCell) {
        gameCell.conquer(player)

        val conqueredCellsByPlayer = cellsConqueredByPlayer[player] ?: TODO()
        conqueredCellsByPlayer.add(gameCell)

        player.currentCoordinates = gameCell.coordinates

        conqueredCells.inc()
    }

    private fun getCellByCoordinates(coordinates: Coordinates) =
        cells[coordinates.x][coordinates.y]

    private fun incrementFailures(player: Player) {
        val failures = failuresByPlayer[player] ?: TODO()
        failuresByPlayer[player] = failures.inc()
    }

    private fun validPositionInBoard(coordinates: Coordinates) =
        (cells.getOrNull(coordinates.x)?.getOrNull(coordinates.y) != null) ?: false


    private fun playerInBoard(player: Player) =
        players.contains(player)


    fun getRandomFreeCell(): GameCell {
        return cells
            .flatMap { it.asList() }
            .filter { !it.isConquered }
            .random()
    }

    suspend fun simulate() {
        var currentPlayerIndex = Random.nextInt(players.size)

        withTimeout(3.minutes) {
            while (!isGameFinished) {
                val currentPlayer = players[currentPlayerIndex]
                currentPlayer.runNextMovement(this@GameBoard)
                currentPlayerIndex = (currentPlayerIndex + 1) % players.size
                delay(TIME_BETWEEN_MOVES_SECONDS.seconds)
            }
        }
    }
}
