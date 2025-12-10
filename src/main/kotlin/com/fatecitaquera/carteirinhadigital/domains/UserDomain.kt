package com.fatecitaquera.carteirinhadigital.domains

import com.fatecitaquera.carteirinhadigital.domains.enums.UserRoleEnum
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

open class UserDomain(
    var id: String?,
    var name: String,
    var email: String,
    var passwd: String,
    var role: UserRoleEnum,
): UserDetails {

    override fun getAuthorities(): Collection<GrantedAuthority> =
        if (role == UserRoleEnum.SECRETARY)
            listOf(SimpleGrantedAuthority("ROLE_SECRETARY"), SimpleGrantedAuthority("ROLE_STUDENT"))
        else
            listOf(SimpleGrantedAuthority("ROLE_STUDENT"))

    override fun getPassword(): String = passwd

    override fun getUsername(): String = email
}