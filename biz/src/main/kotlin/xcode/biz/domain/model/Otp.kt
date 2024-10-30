package xcode.biz.domain.model

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor
import java.util.Date

@Data
@TableName("t_otp")
@Builder
@NoArgsConstructor
@AllArgsConstructor
class Otp {

    @TableId(type = IdType.AUTO)
    var id = 0

    var code = ""
    var userId: Int? = null
    var verifiedDate: Date? = null
    var deletedDate: Date? = null
}
