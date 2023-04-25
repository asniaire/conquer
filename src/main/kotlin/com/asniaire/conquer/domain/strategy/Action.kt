package com.asniaire.conquer.domain.strategy

import com.asniaire.conquer.domain.board.BattleBoard
import com.asniaire.conquer.domain.player.Player

interface Action {
    fun perform(player: Player, battleBoard: BattleBoard)
}
