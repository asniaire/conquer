package com.asniaire.conquer.domain.board

import kotlinx.coroutines.delay
import kotlinx.coroutines.withTimeout
import kotlin.random.Random
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

class GameSimulator(private val battleBoard: BattleBoard) {

    suspend fun simulate() {
        val players = battleBoard.players
        var currentPlayerIndex = Random.nextInt(players.size)

        withTimeout(3.minutes) {
            while (!battleBoard.isGameFinished) {
                val currentPlayer = players[currentPlayerIndex]
                currentPlayer.runNextMovement(battleBoard)
                currentPlayerIndex = (currentPlayerIndex + 1) % players.size
                delay(1.seconds)
            }
        }
    }

}