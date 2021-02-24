package com.coveros.codeveros.userservice

import org.springframework.stereotype.Repository
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Mono

@Repository
interface UserRepository: ReactiveMongoRepository<User, String> {

    fun findByUsername(username: String): Mono<User>

}