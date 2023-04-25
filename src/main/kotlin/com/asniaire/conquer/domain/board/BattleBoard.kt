package com.asniaire.conquer.domain.board

import com.asniaire.conquer.domain.player.Player
import com.asniaire.conquer.domain.strategy.Coordinates
import kotlinx.coroutines.delay
import kotlinx.coroutines.withTimeout
import kotlin.random.Random
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds
const val TIME_BETWEEN_MOVES_SECONDS = 1
const val HIT = true
const val MISS = false

data class Movement(val isHit: Boolean, val coordinates: Coordinates)

class BattleBoard(
    val name: String,
    private val cells: Array<Array<BoardCell>>,
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

    private val movementsByPlayer =
        players.associateWith<Player, MutableList<Movement>> { mutableListOf() }.toMutableMap()
    private val failuresByPlayer = players.associateWith { 0 }.toMutableMap()
    private val cellsConqueredByPlayer =
        players.associateWith<Player, MutableList<BoardCell>> { mutableListOf() }.toMutableMap()

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
        checkIfCellsAlreadyConquered(gameCell, player)

        conquerCell(player, gameCell)
    }

    private fun checkIfCellsAlreadyConquered(boardCell: BoardCell, player: Player) {
        if (boardCell.isConquered) {
            incrementFailures(player)

            addMovementToPlayer(player, boardCell, MISS)

            TODO("Manage errors")
        }
    }

    private fun conquerCell(player: Player, boardCell: BoardCell) {
        boardCell.conquer(player)

        val conqueredCellsByPlayer = cellsConqueredByPlayer[player] ?: TODO()
        conqueredCellsByPlayer.add(boardCell)

        addMovementToPlayer(player, boardCell, HIT)

        player.currentCoordinates = boardCell.coordinates

        conqueredCells.inc()
    }

    private fun addMovementToPlayer(
        player: Player,
        boardCell: BoardCell,
        hit: Boolean
    ) {
        val movementsDoneByPlayer = movementsByPlayer[player] ?: TODO()
        movementsDoneByPlayer.add(Movement(hit, boardCell.coordinates))
    }

    private fun getCellByCoordinates(coordinates: Coordinates) =
        cells[coordinates.x - 1][coordinates.y - 1]

    private fun incrementFailures(player: Player) {
        val failures = failuresByPlayer[player] ?: TODO()
        failuresByPlayer[player] = failures.inc()
    }

    private fun validPositionInBoard(coordinates: Coordinates) =
        cells.getOrNull(coordinates.x - 1)?.getOrNull(coordinates.y - 1) != null


    private fun playerInBoard(player: Player) =
        players.contains(player)


    fun getRandomFreeCell(): BoardCell {
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
                currentPlayer.runNextMovement(this@BattleBoard)
                currentPlayerIndex = (currentPlayerIndex + 1) % players.size
                delay(TIME_BETWEEN_MOVES_SECONDS.seconds)
            }
        }
    }
}
