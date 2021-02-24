package com.coveros.codeveros.userservice

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import reactor.core.publisher.Mono
import javax.validation.Valid

@RestController
@RequestMapping("/user")
class UserController(
    val userRepository: UserRepository,
    val passwordEncoder: BCryptPasswordEncoder
) {
    val logger: Logger = LoggerFactory.getLogger(UserController::class.java)

    @GetMapping
    @ResponseBody
    fun getUsers() = userRepository.findAll()

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: String) = userRepository.findById(id)

    @PostMapping
    fun addUser(@Valid @RequestBody user: User): Mono<User> {
        logger.info(user.password)
        user.password = passwordEncoder.encode(user.password)
        return userRepository.save(user)
    }

    @PutMapping("/{id}")
    fun updateUserById(@RequestBody user: User, @PathVariable id: String): Mono<User> {
        return userRepository.findById(id) // placeholder
    }

    @DeleteMapping("/{id}")
    fun deleteUserById(@PathVariable id: String) = userRepository.deleteById(id)

    @PostMapping("/login")
    fun login(@RequestBody credential: Credential): Mono<ResponseEntity<Any>> {
        return userRepository.findByUsername(credential.username)
            .map { user: User ->
                if (passwordEncoder.matches(credential.password, user.password)) {
                    ResponseEntity.ok().body(user)
                } else {
                    ResponseEntity.badRequest().body("NO")
                }
            }
    }

    @PostMapping("/login-exception")
    fun loginWithException(@RequestBody credential: Credential): Mono<Any> {
        return userRepository.findByUsername(credential.username)
            .flatMap { user: User ->
                if (passwordEncoder.matches(credential.password, user.password)) {
                    Mono.just(user)
                } else {
                    Mono.error(ResponseStatusException(HttpStatus.BAD_REQUEST, "bad password"))
                }
            }
    }

    @PostMapping("/logout")
    fun logout() = "logout post"
}