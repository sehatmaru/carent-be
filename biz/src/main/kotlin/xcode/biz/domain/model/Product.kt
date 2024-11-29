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
import xcode.biz.enums.EngineType
import xcode.biz.enums.ProductStatus
import xcode.biz.enums.Transmission
import xcode.biz.enums.VehicleBrand

@Data
@TableName("t_product")
@Builder
@NoArgsConstructor
@AllArgsConstructor
class Product {

    @TableId(type = IdType.AUTO)
    var id = 0

    var companyId: Int? = null
    var name = ""
    var price: Int? = null
    var quantity: Int? = null
    var available: Int? = null
    var provinceId: Int? = null
    var provinceName = ""
    var regencyId: Int? = null
    var regencyName = ""
    var districtId: Int? = null
    var districtName = ""
    var deliverable = false
    var transmission: Transmission? = null
    var seat: Int? = null
    var rating: Double? = null
    var engineType: EngineType? = null
    var brand: VehicleBrand? = null
    var status: ProductStatus? = null

    @TableField(fill = FieldFill.INSERT)
    var createdDate: Date? = null

    @TableField(fill = FieldFill.INSERT_UPDATE)
    var updatedDate: Date? = null

    var deletedDate: Date? = null
}
