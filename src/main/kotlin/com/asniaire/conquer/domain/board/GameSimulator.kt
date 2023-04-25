package com.asniaire.conquer.domain.board

import com.asniaire.conquer.domain.player.Player
import com.asniaire.conquer.domain.strategy.Coordinates
import kotlinx.coroutines.delay
import kotlinx.coroutines.withTimeout
import kotlin.random.Random
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

data class Movement(val player: Player, val coordinates: Coordinates)

class GameSimulator(private val gameBoard: GameBoard) {

    suspend fun simulate() {
        val players = gameBoard.players
        var currentPlayerIndex = Random.nextInt(players.size)

        withTimeout(3.minutes) {
            while (!gameBoard.isGameFinished) {
                val currentPlayer = players[currentPlayerIndex]
                val currentCoordinates = gameBoard.getPlayerCoordinates(currentPlayer) ?: TODO()
                currentPlayer.strategy.applyNextStrategy(currentPlayer, gameBoard)
                currentPlayerIndex = (currentPlayerIndex + 1) % players.size
                delay(1.seconds)
            }
        }
    }

}