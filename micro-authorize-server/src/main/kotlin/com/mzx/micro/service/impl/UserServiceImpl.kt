package com.mzx.micro.service.impl

import com.mzx.micro.entity.User
import com.mzx.micro.service.UserService
import io.ebean.DB
import org.springframework.stereotype.Service

@Service
class UserServiceImpl : UserService {
    override fun getByUsername(username: String): User? {
        return DB.find(User::class.java).where().eq("username", username).findOne()
    }
}