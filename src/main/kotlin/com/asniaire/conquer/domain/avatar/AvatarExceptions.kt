package com.asniaire.conquer.domain.avatar

sealed class InvalidArgumentAvatarException(override val message: String, override val cause: Throwable? = null) : IllegalArgumentException(message, cause)

data class InvalidAvatarIdException(val id: String, override val cause: Throwable?) : InvalidArgumentAvatarException("The id '$id' is not valid", cause)

data class InvalidAvatarNameException(val name: String) : InvalidArgumentAvatarException("The name '$name' is not valid")

data class InvalidAvatarImageException(val imageUrl: String) : InvalidArgumentAvatarException("The image '$imageUrl' is not valid")