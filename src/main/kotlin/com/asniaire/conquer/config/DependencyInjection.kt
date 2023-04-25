package com.asniaire.conquer.config

import com.asniaire.conquer.application.CreateAvatar
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DependencyInjection {

    @Bean
    fun createAvatar() = CreateAvatar()

}