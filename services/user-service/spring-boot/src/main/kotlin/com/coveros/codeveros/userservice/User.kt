package com.coveros.codeveros.userservice

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import javax.validation.constraints.Email

@Document
@JsonIgnoreProperties(ignoreUnknown=true)
data class User(
    @Id
    var id: String?,
    @Indexed(unique=true)
    var username: String,
    var firstName: String,
    var lastName: String,
    @field:Email(message = "invalid email")
    var email: String,
    @field:JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    var password: String?
) {


}
