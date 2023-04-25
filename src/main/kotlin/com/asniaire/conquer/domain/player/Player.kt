package com.asniaire.conquer.domain.player

import com.asniaire.conquer.domain.avatar.Avatar
import com.asniaire.conquer.domain.strategy.Strategy

data class Player(val avatar: Avatar, val strategy: Strategy)