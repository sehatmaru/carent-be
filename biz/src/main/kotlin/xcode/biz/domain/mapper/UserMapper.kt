package xcode.biz.domain.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Options
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update
import xcode.biz.domain.model.User
import xcode.biz.domain.request.admin.AdminUpdateRequest
import xcode.biz.domain.request.customer.CustomerFilterRequest
import xcode.biz.domain.response.admin.AdminResponse

@Mapper
interface UserMapper : BaseMapper<User> {

    @Insert(
        """
        INSERT INTO t_user (company_id, created_by, full_name, mobile, username, email, credential_no, credential_type, password, role, rating, verified_date)
        VALUES (#{data.companyId}, #{data.createdBy}, #{data.fullName}, #{data.mobile}, #{data.username}, #{data.email}, #{data.credentialNo}, #{data.credentialType}::credential_type, #{data.password}, #{data.role}::user_role, 0, #{data.verifiedDate})
    """,
    )
    @Options(useGeneratedKeys = true, keyProperty = "data.id")
    fun insertUser(@Param("data") data: User)

    @Update(
        """
        UPDATE t_user SET verified_date = NOW() WHERE id = #{userId}
    """,
    )
    fun activateUser(@Param("userId") userId: Int)

    @Select(
        """
        SELECT * FROM t_user
        WHERE id = #{userId}
        AND deleted_date IS NULL
        AND verified_date IS NOT NULL
        AND role IN ('TENANT_MANAGER', 'TENANT_ADMIN')
        LIMIT 1
    """,
    )
    fun getActiveTenantUser(@Param("userId") userId: Int): User?

    @Select("""SELECT * FROM t_user WHERE id = #{userId} AND deleted_date IS NULL AND verified_date IS NOT NULL AND role = 'ADMIN' LIMIT 1""")
    fun getActiveAdminUser(@Param("userId") userId: Int): User?

    @Select("""SELECT * FROM t_user WHERE id = #{userId} AND deleted_date IS NULL AND verified_date IS NOT NULL AND role = 'CUSTOMER' LIMIT 1""")
    fun getActiveCustomerUser(@Param("userId") userId: Int): User?

    @Select("""SELECT * FROM t_user WHERE id = #{id} AND deleted_date IS NULL AND verified_date IS NOT NULL AND role = 'TENANT_ADMIN' LIMIT 1""")
    fun getActiveTenantAdmin(@Param("id") id: Int): User?

    @Select("""SELECT * FROM t_user WHERE id = #{id} AND deleted_date IS NULL AND verified_date IS NOT NULL AND role = 'TENANT_MANAGER' LIMIT 1""")
    fun getActiveTenantManager(@Param("id") id: Int): User?

    @Select("""SELECT * FROM t_user WHERE (username = #{username} OR email = #{email}) AND deleted_date IS NULL AND verified_date IS NOT NULL LIMIT 1""")
    fun getActiveUser(@Param("username") username: String, @Param("email") email: String): User?

    @Select(
        """
        SELECT * FROM t_user
        WHERE username = #{username}
        AND deleted_date IS NULL
        AND verified_date IS NOT NULL
        AND role IN ('TENANT_MANAGER', 'TENANT_ADMIN')
        LIMIT 1
    """,
    )
    fun getActiveTenantByUsername(@Param("username") username: String): User?

    @Select(
        """
        SELECT * FROM t_user
        WHERE username = #{username}
        AND deleted_date IS NULL
        AND verified_date IS NOT NULL
        AND role = 'ADMIN'
        LIMIT 1
    """,
    )
    fun getActiveAdminByUsername(@Param("username") username: String): User?

    @Select("""SELECT * FROM t_user WHERE username = #{username} AND deleted_date IS NULL AND verified_date IS NOT NULL AND role = 'CUSTOMER' LIMIT 1""")
    fun getActiveCustomer(@Param("username") username: String): User?

    @Select("""SELECT * FROM t_user WHERE id = #{id} AND deleted_date IS NULL AND verified_date IS NULL LIMIT 1""")
    fun getInactiveUser(@Param("id") id: Int): User?

    @Select(
        """
        <script>
            SELECT u.id, u.full_name as "name", u.username, u.mobile, u.email, u.verified_date as "join_date"
            FROM t_user u
            WHERE u.role = 'TENANT_ADMIN' AND u.verified_date IS NOT NULL
            AND u.deleted_date IS NULL
            <if test="request.name != null">
                AND u.full_name ILIKE CONCAT('%', #{request.name}, '%')
            </if>
            <if test="request.mobile != null">
                AND u.mobile ILIKE CONCAT('%', #{request.mobile}, '%')
            </if>
            <if test="request.username != null">
                AND u.username ILIKE CONCAT('%', #{request.username}, '%')
            </if>
            ORDER BY u.updated_date DESC
        </script>
    """,
    )
    fun getAdminList(
        @Param("companyId") companyId: Int,
        @Param("request") request: CustomerFilterRequest,
    ): List<AdminResponse>

    @Update(
        """
        UPDATE t_user SET full_name = #{data.fullName}, username = #{data.username}, email = #{data.email}, mobile = #{data.mobile}, updated_date = NOW()
        WHERE id = #{id}
    """,
    )
    fun updateAdmin(@Param("id") productId: Int, @Param("data") data: AdminUpdateRequest)

    @Update(
        """
        UPDATE t_user SET deleted_date = NOW() WHERE id = #{id}
    """,
    )
    fun deleteAdmin(@Param("id") productId: Int)
}
