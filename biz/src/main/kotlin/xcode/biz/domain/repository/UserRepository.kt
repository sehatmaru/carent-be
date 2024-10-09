package xcode.biz.domain.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import xcode.biz.domain.model.User

@Repository
interface UserRepository : JpaRepository<User?, String?> {

    @Query(
        value = "SELECT * FROM t_user WHERE (username = :username OR email = :email) AND deleted_at IS NULL AND verified_at IS NOT NULL AND role IN ('TENANT_MANAGER', 'TENANT_ADMIN') LIMIT 1",
        nativeQuery = true,
    )
    fun getActiveTenantUser(@Param("username") username: String, @Param("email") email: String): User?

    @Query(
        value = "SELECT * FROM t_user WHERE id = :id AND deleted_at IS NULL AND verified_at IS NOT NULL AND role = 'TENANT_ADMIN' LIMIT 1",
        nativeQuery = true,
    )
    fun getActiveTenantAdmin(@Param("id") id: Int): User?

    @Query(
        value = "SELECT * FROM t_user WHERE (username = :username OR email = :email) AND deleted_at IS NULL AND verified_at IS NOT NULL LIMIT 1",
        nativeQuery = true,
    )
    fun getActiveUser(@Param("username") username: String, @Param("email") email: String): User?

    @Query(
        value = "SELECT * FROM t_user WHERE username = :username AND deleted_at IS NULL AND verified_at IS NOT NULL AND role IN ('TENANT_MANAGER', 'TENANT_ADMIN') LIMIT 1",
        nativeQuery = true,
    )
    fun getActiveTenantUserByUsername(@Param("username") username: String): User?

    @Query(
        value = "SELECT * FROM t_user WHERE username = :username AND deleted_at IS NULL AND verified_at IS NOT NULL AND role = 'CUSTOMER' LIMIT 1",
        nativeQuery = true,
    )
    fun getActiveCustomer(@Param("username") username: String): User?

    @Query(
        value = "SELECT * FROM t_user WHERE id = :id AND deleted_at IS NULL AND verified_at IS NULL LIMIT 1",
        nativeQuery = true,
    )
    fun getInactiveUser(@Param("id") id: Int): User?
}
