package xcode.biz.domain.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import xcode.biz.domain.model.User

@Repository
interface UserRepository : JpaRepository<User?, String?> {
    fun findByUsernameAndDeletedAtIsNull(username: String): User?
}