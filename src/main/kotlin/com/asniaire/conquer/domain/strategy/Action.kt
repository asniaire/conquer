package com.asniaire.conquer.domain.strategy

import com.asniaire.conquer.domain.board.GameBoard
import com.asniaire.conquer.domain.player.Player

interface Action {
    fun perform(player: Player, gameBoard: GameBoard)
}
