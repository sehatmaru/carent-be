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
@TableName("t_company")
@Builder
@NoArgsConstructor
@AllArgsConstructor
class Company {

    @TableId(type = IdType.AUTO)
    var id = 0

    var firmName = ""
    var aliasName = ""
    var address = ""
    var mobile = ""
    var rating: Int? = null
    var foundingDate: Date? = null
}
