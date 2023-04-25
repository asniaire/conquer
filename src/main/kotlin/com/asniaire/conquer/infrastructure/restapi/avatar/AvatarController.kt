package com.asniaire.conquer.infrastructure.restapi.avatar

import com.asniaire.conquer.application.CreateAvatar
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class AvatarController(private val createAvatar: CreateAvatar) {

    @PostMapping("/avatar")
    suspend fun createAvatar(): String {
        return createAvatar(UUID.randomUUID().toString(), "Test", "asdf")
    }
}
