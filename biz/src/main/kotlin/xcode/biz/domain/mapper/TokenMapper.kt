package xcode.biz.domain.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Options
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update
import xcode.biz.domain.model.Token

@Mapper
interface TokenMapper : BaseMapper<Token> {

    @Insert(
        """
        INSERT INTO t_token (user_id, code, type, is_active, expire_at)
        VALUES (#{data.userId}, #{data.code}, #{data.type}, true, #{data.expireAt})
    """,
    )
    @Options(useGeneratedKeys = true, keyProperty = "data.id")
    fun insertToken(@Param("data") data: Token)

    @Update(
        """
        UPDATE t_token SET is_active = false WHERE id = #{id}
    """,
    )
    fun deactivateToken(@Param("id") id: Int)

    @Select("""SELECT * FROM t_token WHERE code = #{code} LIMIT 1""")
    fun getToken(code: String): Token?

    @Select("""SELECT * FROM t_token WHERE code = #{code} AND is_active IS TRUE AND type = 'OTP' LIMIT 1""")
    fun getOtpToken(@Param("code") code: String): Token?
}
