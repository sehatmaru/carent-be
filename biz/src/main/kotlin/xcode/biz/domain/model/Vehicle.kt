package xcode.biz.domain.model

import com.baomidou.mybatisplus.annotation.FieldFill
import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor
import java.util.Date

@Data
@TableName("t_vehicle")
@Builder
@NoArgsConstructor
@AllArgsConstructor
class Vehicle {

    @TableId(type = IdType.AUTO)
    var id = 0

    var productId: Int? = null
    var name = ""
    var year = ""
    var licenseNumber = ""
    var rating: Int? = null
    var createdBy: Int? = null

    @TableField(fill = FieldFill.INSERT)
    var createdDate: Date? = null

    @TableField(fill = FieldFill.INSERT_UPDATE)
    var updatedDate: Date? = null

    var deletedDate: Date? = null
}
