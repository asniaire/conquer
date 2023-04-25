package com.asniaire.conquer.domain.strategy.actions

import com.asniaire.conquer.domain.board.BattleBoard
import com.asniaire.conquer.domain.player.Player
import com.asniaire.conquer.domain.strategy.Action
import com.asniaire.conquer.domain.strategy.Coordinates

class ExpandNearestCellAction() : Action {
    override fun perform(player: Player, battleBoard: BattleBoard) {
        val neighborsCoordinates = neighbors(player.currentCoordinates, battleBoard)
        val randomCoordinates = neighborsCoordinates.random()
        battleBoard.conquer(randomCoordinates, player)
    }

    private fun neighbors(coordinates: Coordinates, battleBoard: BattleBoard): List<Coordinates> {
        val x = coordinates.x
        val y = coordinates.y
        val neighbors = mutableListOf<Coordinates>()

        if (x > 0) neighbors.add(coordinates.copy(x = x - 1))
        if (x < battleBoard.width - 1) neighbors.add(coordinates.copy(x = x + 1))
        if (y > 0) neighbors.add(coordinates.copy(y = y - 1))
        if (y < battleBoard.height - 1) neighbors.add(coordinates.copy(y = y + 1))

        if (x > 0 && y > 0) neighbors.add(coordinates.copy(x = x - 1, y = y - 1))
        if (x > 0 && y < battleBoard.height - 1) neighbors.add(coordinates.copy(x = x - 1, y = y + 1))
        if (x < battleBoard.width - 1 && y > 0) neighbors.add(coordinates.copy(x = x + 1, y = y - 1))
        if (x < battleBoard.width - 1 && y < battleBoard.height - 1) neighbors.add(coordinates.copy(x = x + 1, y = y + 1))

        return neighbors
    }
}
