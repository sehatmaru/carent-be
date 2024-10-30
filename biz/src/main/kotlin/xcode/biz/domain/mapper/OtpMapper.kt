package xcode.biz.domain.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Options
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update
import xcode.biz.domain.model.Otp

@Mapper
interface OtpMapper : BaseMapper<Otp> {

    @Insert(
        """
        INSERT INTO t_otp (code, user_id)
        VALUES (#{otp.code}, #{otp.userId})
    """,
    )
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    fun insertOtp(@Param("otp") otp: Otp)

    @Update(
        """
        UPDATE t_otp SET verified_date = NOW() WHERE id = #{id}
    """,
    )
    fun deactivateOtp(@Param("id") id: Int)

    @Select("""SELECT * FROM t_otp WHERE code = #{code} AND verified_date IS NULL LIMIT 1""")
    fun getUnverifiedOtp(@Param("code") code: String): Otp?
}
