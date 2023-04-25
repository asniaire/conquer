package com.asniaire.conquer.domain.strategy.actions

import com.asniaire.conquer.domain.board.BattleBoard
import com.asniaire.conquer.domain.player.Player
import com.asniaire.conquer.domain.strategy.Action
import com.asniaire.conquer.domain.strategy.Coordinates

class LinearMovementAction(private val direction: Direction) : Action {
    override fun perform(player: Player, battleBoard: BattleBoard) {
        val nextCoordinates = nextCoordinates(player.currentCoordinates, battleBoard)
        battleBoard.conquer(nextCoordinates, player)
    }

    private fun nextCoordinates(currentPosition: Coordinates, battleBoard: BattleBoard): Coordinates {
        val x = currentPosition.x
        val y = currentPosition.y
        val nextCoordinates = when (direction) {
            Direction.NORTH -> currentPosition.copy(y = y - 1)
            Direction.SOUTH -> currentPosition.copy(y = y + 1)
            Direction.WEST -> currentPosition.copy(x = x - 1)
            Direction.EAST -> currentPosition.copy(x = x + 1)
            Direction.NORTH_WEST -> currentPosition.copy(x = x - 1, y = y - 1)
            Direction.NORTH_EAST -> currentPosition.copy(x = x + 1, y = y - 1)
            Direction.SOUTH_WEST -> currentPosition.copy(x = x - 1, y = y + 1)
            Direction.SOUTH_EAST -> currentPosition.copy(x = x + 1, y = y + 1)
        }
        if (nextCoordinates.x < 1 || nextCoordinates.x > battleBoard.width || nextCoordinates.y < 1 || nextCoordinates.y > battleBoard.height) {
            TODO("Error")
        }
        return nextCoordinates
    }
}

enum class Direction {
    NORTH, SOUTH, WEST, EAST, NORTH_WEST, NORTH_EAST, SOUTH_WEST, SOUTH_EAST
}