package com.asniaire.conquer.domain.strategy.actions

import com.asniaire.conquer.domain.board.GameBoard
import com.asniaire.conquer.domain.player.Player
import com.asniaire.conquer.domain.strategy.Action
import com.asniaire.conquer.domain.strategy.Coordinates

class LinearMovementAction(private val direction: Direction) : Action {
    override fun perform(player: Player, gameBoard: GameBoard) {
        val currentCoordinates = gameBoard.getPlayerCoordinates(player) ?: TODO()
        val nextCoordinates = nextCoordinates(currentCoordinates, gameBoard)
        gameBoard.conquer(nextCoordinates, player)
    }

    private fun nextCoordinates(currentPosition: Coordinates, gameBoard: GameBoard): Coordinates {
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
        if (nextCoordinates.x < 0 || nextCoordinates.x >= gameBoard.width || nextCoordinates.y < 0 || nextCoordinates.y >= gameBoard.height) {
            throw Exception() // TODO
        }
        return nextCoordinates
    }
}

enum class Direction {
    NORTH, SOUTH, WEST, EAST, NORTH_WEST, NORTH_EAST, SOUTH_WEST, SOUTH_EAST
}