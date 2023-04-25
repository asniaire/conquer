package com.asniaire.conquer.domain.avatar

import java.util.*

data class AvatarId(val value: UUID) {
    companion object {
        fun fromString(id: String) = try {
            AvatarId(UUID.fromString(id))
        } catch (exception: Exception) {
            throw InvalidAvatarIdException(id, exception)
        }
    }
}

data class AvatarName(val value: String) {
    init {
        validate()
    }

    private fun validate() {
        if (value.isEmpty() || value.isBlank()) {
            throw InvalidAvatarNameException(value)
        }
    }
}

data class Avatar(val id: AvatarId, val name: AvatarName)