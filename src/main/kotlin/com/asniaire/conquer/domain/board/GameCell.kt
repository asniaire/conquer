package com.asniaire.conquer.domain.board

import com.asniaire.conquer.domain.player.Player
import com.asniaire.conquer.domain.strategy.Coordinates

class GameCell(coordinates: Coordinates, boost: Boost? = null) : Cell(coordinates, boost) {
    var isConquered: Boolean = false
        private set

    var player: Player? = null
        private set

    fun conquer(player: Player) {
        this.player = player
        isConquered = true
    }
}