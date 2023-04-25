package com.asniaire.conquer.domain.strategy.actions

import com.asniaire.conquer.domain.board.GameBoard
import com.asniaire.conquer.domain.player.Player
import com.asniaire.conquer.domain.strategy.Action
import com.asniaire.conquer.domain.strategy.Coordinates

class ConquerCellAction(private val coordinates: Coordinates) : Action {
    override fun perform(player: Player, gameBoard: GameBoard) {
        gameBoard.conquer(coordinates, player)
    }
}