package xcode.biz.domain.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Options
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update
import xcode.biz.domain.model.User

@Mapper
interface UserMapper : BaseMapper<User> {

    @Insert(
        """
        INSERT INTO t_user (company_id, created_by, full_name, mobile, username, email, credential_no, credential_type, password, role, rating)
        VALUES (#{data.companyId}, #{data.createdBy}, #{data.fullName}, #{data.mobile}, #{data.username}, #{data.email}, #{data.credentialNo}, #{data.credentialType}, #{data.password}, #{data.role}, 0)
    """,
    )
    @Options(useGeneratedKeys = true, keyProperty = "data.id")
    fun insertUser(@Param("data") data: User)

    @Update(
        """
        UPDATE t_user SET verified_at = NOW() WHERE id = #{userId}
    """,
    )
    fun activateUser(@Param("userId") userId: Int)

    @Select(
        """
        SELECT * FROM t_user
        WHERE id = #{userId}
        AND deleted_at IS NULL
        AND verified_at IS NOT NULL
        AND role IN ('TENANT_MANAGER', 'TENANT_ADMIN')
        LIMIT 1
    """,
    )
    fun getActiveTenantUser(@Param("userId") userId: Int): User?

    @Select("""SELECT * FROM t_user WHERE id = #{userId} AND deleted_at IS NULL AND verified_at IS NOT NULL AND role = 'ADMIN' LIMIT 1""")
    fun getActiveAdminUser(@Param("userId") userId: Int): User?

    @Select("""SELECT * FROM t_user WHERE id = #{userId} AND deleted_at IS NULL AND verified_at IS NOT NULL AND role = 'CUSTOMER' LIMIT 1""")
    fun getActiveCustomerUser(@Param("userId") userId: Int): User?

    @Select("""SELECT * FROM t_user WHERE id = #{id} AND deleted_at IS NULL AND verified_at IS NOT NULL AND role = 'TENANT_ADMIN' LIMIT 1""")
    fun getActiveTenantAdmin(@Param("id") id: Int): User?

    @Select("""SELECT * FROM t_user WHERE id = #{id} AND deleted_at IS NULL AND verified_at IS NOT NULL AND role = 'TENANT_MANAGER' LIMIT 1""")
    fun getActiveTenantManager(@Param("id") id: Int): User?

    @Select("""SELECT * FROM t_user WHERE (username = #{username} OR email = #{email}) AND deleted_at IS NULL AND verified_at IS NOT NULL LIMIT 1""")
    fun getActiveUser(@Param("username") username: String, @Param("email") email: String): User?

    @Select(
        """
        SELECT * FROM t_user
        WHERE username = #{username}
        AND deleted_at IS NULL
        AND verified_at IS NOT NULL
        AND role IN ('TENANT_MANAGER', 'TENANT_ADMIN')
        LIMIT 1
    """,
    )
    fun getActiveTenantUserByUsername(@Param("username") username: String): User?

    @Select("""SELECT * FROM t_user WHERE username = #{username} AND deleted_at IS NULL AND verified_at IS NOT NULL AND role = 'CUSTOMER' LIMIT 1""")
    fun getActiveCustomer(@Param("username") username: String): User?

    @Select("""SELECT * FROM t_user WHERE id = #{id} AND deleted_at IS NULL AND verified_at IS NULL LIMIT 1""")
    fun getInactiveUser(@Param("id") id: Int): User?

    @Select("""SELECT * FROM t_user WHERE created_by = #{id}""")
    fun getAdminList(@Param("id") id: Int): List<User>?
}
