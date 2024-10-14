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
import xcode.biz.enums.ProductStatus
import java.util.Date

@Data
@TableName("t_product")
@Builder
@NoArgsConstructor
@AllArgsConstructor
class Product {

    @TableId(type = IdType.AUTO)
    var id = 0

    var vehicleId: Int? = null
    var name = ""
    var price: Int? = null
    var provinceId: Int? = null
    var provinceName = ""
    var regencyId: Int? = null
    var regencyName = ""
    var districtId: Int? = null
    var districtName = ""
    var deliverable = false
    var status: ProductStatus? = null

    @TableField(fill = FieldFill.INSERT)
    var createdAt: Date? = null

    @TableField(fill = FieldFill.INSERT_UPDATE)
    var updatedAt: Date? = null

    var deletedAt: Date? = null
}
