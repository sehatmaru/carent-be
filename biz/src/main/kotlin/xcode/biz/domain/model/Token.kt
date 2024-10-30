package xcode.biz.domain.model

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import lombok.Builder
import lombok.Data
import xcode.biz.enums.TokenType
import java.util.Date

@Data
@TableName(value = "t_token")
@Builder
class Token {

    @TableId(type = IdType.AUTO)
    val id = 0

    var userId = 0
    var code = ""
    var createdDate: Date? = null
    var expireDate: Date? = null
    var isActive = false
    var type: TokenType? = TokenType.NON_OTP

    fun isValid(): Boolean {
        return !expireDate!!.before(Date())
    }
}
