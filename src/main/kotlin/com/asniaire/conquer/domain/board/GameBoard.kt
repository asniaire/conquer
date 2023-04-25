package com.asniaire.conquer.domain.board

import com.asniaire.conquer.domain.player.Player
import com.asniaire.conquer.domain.strategy.Coordinates

class GameBoard(
    val name: String,
    private val cells: Array<Array<GameCell>>,
    players: List<Player>
) {
    var occupiedCells: Int = 0
        private set

    val width = cells.size
    val height = cells[0].size

    val totalCells = width * height

    val remainingCells: Int
        get() = totalCells - occupiedCells

    val isGameFinished: Boolean = remainingCells == 0

    val players = players.toMutableList()
    private val currentCellByPlayer: Map<Player, GameCell> =
        players.associateWith { getCellByCoordinates(it.strategy.initialPosition) }

    private val failuresByPlayer = players.associateWith { 0 }.toMutableMap()
    private val cellsConqueredByPlayer =
        players.associateWith<Player, MutableList<GameCell>> { mutableListOf() }.toMutableMap()

    private fun getCellFromCoordinates(coordinates: Coordinates) =
        cells[coordinates.x][coordinates.y]


    fun occupiedCellsByUser(player: Player): Int {
        TODO()
    }

    fun getPlayerCoordinates(player: Player) =
        currentCellByPlayer[player]?.coordinates

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

    private fun getCellByCoordinates(coordinates: Coordinates) =
        cells[coordinates.x][coordinates.y]

    private fun conquerCell(player: Player, gameCell: GameCell) {
        gameCell.conquer(player)

        val conqueredCells = cellsConqueredByPlayer[player] ?: TODO()
        conqueredCells.add(gameCell)

        occupiedCells.inc()
    }

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
}
