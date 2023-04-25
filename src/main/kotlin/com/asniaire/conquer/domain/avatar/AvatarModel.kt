package com.asniaire.conquer.domain.avatar

import java.util.UUID

data class AvatarModelId(val value: UUID) {
    companion object {
        fun fromString(id: String) = try {
            AvatarModelId(UUID.fromString(id))
        } catch (exception: Exception) {
            throw InvalidAvatarModelIdException(id, exception)
        }
    }
}

data class AvatarModelName(val value: String) {
    init {
        validate()
    }

    private fun validate() {
        if (value.isEmpty() || value.isBlank()) {
            throw InvalidAvatarModelNameException(value)
        }
    }
}

data class AvatarModelImageUrl(val value: String) {
    init {
        validate()
    }

    private fun validate() {
        if (value.isEmpty()) {
            throw InvalidAvatarModelImageException(value)
        }

    }
}

data class AvatarModel(val id: AvatarModelId, val name: AvatarModelName, val imageUrl: AvatarModelImageUrl)
