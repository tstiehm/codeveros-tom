package com.coveros.codeveros.userservice.config

import com.coveros.codeveros.userservice.User
import com.coveros.codeveros.userservice.UserRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.HandlerFunction
import org.springframework.web.reactive.function.server.RequestPredicates.GET
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions.route
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
class UserFunctionalConfig(val userRepository: UserRepository) {

    @Bean
    fun routes(): RouterFunction<ServerResponse> =
        route(GET("/func/{id}"), getUser)

    val getUser = { req: ServerRequest -> ServerResponse.ok().body(userRepository.findById(req.pathVariable("id")), User::class.java) }
}