package com.asniaire.conquer.domain.avatar

sealed class InvalidArgumentAvatarModelException(override val message: String, override val cause: Throwable? = null) : IllegalArgumentException(message, cause)

data class InvalidAvatarModelIdException(val id: String, override val cause: Throwable?) : InvalidArgumentAvatarException("The id '$id' is not valid", cause)

data class InvalidAvatarModelNameException(val name: String) : InvalidArgumentAvatarException("The name '$name' is not valid")

data class InvalidAvatarModelImageException(val imageUrl: String) : InvalidArgumentAvatarException("The image '$imageUrl' is not valid")