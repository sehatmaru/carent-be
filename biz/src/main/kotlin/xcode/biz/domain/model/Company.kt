package xcode.biz.domain.model

import com.baomidou.mybatisplus.annotation.FieldFill
import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import java.util.Date
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor

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

    @TableField(fill = FieldFill.INSERT_UPDATE)
    var updatedDate: Date? = null
}
