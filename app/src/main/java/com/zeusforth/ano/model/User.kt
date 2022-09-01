package com.zeusforth.ano.model

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter



class User {
    private var id: Long = 0

    private var userName: String? = null

    private var name: String? = null

    private var email: String? = null

    private var password: String? = null

    private var phone: Long = 0

    private var organizationEmail: String? = null

    private var createdAt: String? = null

    private var updatedAt: String? = null

    private var profileUrl: String? = null

    private var authenticated: Boolean? = null

    private var superUser: Boolean? = null

    fun User(){

    }


    fun User(
        userName: String?,
        name: String?,
        email: String?,
        password: String?,
        phone: Long,
        organizationEmail: String?,
        createdAt: String?,
        updatedAt: String?,
        profileUrl: String?,
        authenticated: Boolean?,
        superUser: Boolean?
    ) {
        this.userName = userName
        this.name = name
        this.email = email
        this.password = password
        this.phone = phone
        this.organizationEmail = organizationEmail
        this.createdAt = createdAt
        this.updatedAt = updatedAt
        this.profileUrl = profileUrl
        this.authenticated = authenticated
        this.superUser = superUser
    }


    fun getId(): Long {
        return id
    }


    fun setId(id: Long) {
        this.id = id
    }

    fun getUserName(): String? {
        return userName
    }

    fun setUserName(userName: String?) {
        this.userName = userName
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getEmail(): String? {
        return email
    }

    fun setEmail(email: String?) {
        this.email = email
    }

    fun getPassword(): String? {
        return password
    }

    fun setPassword(password: String?) {
        this.password = password
    }

    fun getPhone(): Long {
        return phone
    }

    fun setPhone(phone: Long) {
        this.phone = phone
    }

    fun getOrganizationEmail(): String? {
        return organizationEmail
    }

    fun setOrganizationEmail(organizationEmail: String?) {
        this.organizationEmail = organizationEmail
    }

    fun getCreatedAt(): String? {
        return createdAt
    }

    fun setCreatedAt(createdAt: String?) {
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
//        LocalDateTime now = LocalDateTime.now();
//        String newdate = dtf.format(now);
        this.createdAt = createdAt
    }

    fun getUpdatedAt(): String? {
        return updatedAt
    }

    fun setUpdatedAt() {
        val dtf: DateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
        val zoneid1: ZoneId = ZoneId.of("Asia/Kolkata")
        val now: LocalDateTime = LocalDateTime.now(zoneid1)
        val newdate: String = dtf.format(now)
        updatedAt = newdate
    }

    fun getProfileUrl(): String? {
        return profileUrl
    }

    fun setProfileUrl(profileUrl: String?) {
        this.profileUrl = profileUrl
    }

    fun getAuthenticated(): Boolean? {
        return authenticated
    }

    fun setAuthenticated(authenticated: Boolean?) {
        this.authenticated = authenticated
    }

    fun setUpdatedAt(updatedAt: String?) {
        this.updatedAt = updatedAt
    }

    fun getSuperUser(): Boolean? {
        return superUser
    }

    fun setSuperUser(superUser: Boolean?) {
        this.superUser = superUser
    }
}