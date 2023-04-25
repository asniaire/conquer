package com.asniaire.conquer.application

import com.asniaire.conquer.domain.avatar.*

class CreateAvatar {
    suspend operator fun invoke(id: String, name: String, avatarModelUUID: String): String {
        return "Hello world!"
    }
}