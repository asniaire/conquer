package com.asniaire.conquer.domain.strategy.actions

import com.asniaire.conquer.domain.board.GameBoard
import com.asniaire.conquer.domain.player.Player
import com.asniaire.conquer.domain.strategy.Action
import com.asniaire.conquer.domain.strategy.Coordinates

class ExpandNearestCellAction() : Action {
    override fun perform(player: Player, gameBoard: GameBoard) {
        val currentCoordinates = gameBoard.getPlayerCoordinates(player) ?: TODO()
        val neighborsCoordinates = neighbors(currentCoordinates, gameBoard)
        val randomCoordinates = neighborsCoordinates.random()
        gameBoard.conquer(randomCoordinates, player)
    }

    private fun neighbors(coordinates: Coordinates, gameBoard: GameBoard): List<Coordinates> {
        val x = coordinates.x
        val y = coordinates.y
        val neighbors = mutableListOf<Coordinates>()

        if (x > 0) neighbors.add(coordinates.copy(x = x - 1))
        if (x < gameBoard.width - 1) neighbors.add(coordinates.copy(x = x + 1))
        if (y > 0) neighbors.add(coordinates.copy(y = y - 1))
        if (y < gameBoard.height - 1) neighbors.add(coordinates.copy(y = y + 1))

        if (x > 0 && y > 0) neighbors.add(coordinates.copy(x = x - 1, y = y - 1))
        if (x > 0 && y < gameBoard.height - 1) neighbors.add(coordinates.copy(x = x - 1, y = y + 1))
        if (x < gameBoard.width - 1 && y > 0) neighbors.add(coordinates.copy(x = x + 1, y = y - 1))
        if (x < gameBoard.width - 1 && y < gameBoard.height - 1) neighbors.add(coordinates.copy(x = x + 1, y = y + 1))

        return neighbors
    }
}
