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
import xcode.biz.enums.EngineType
import xcode.biz.enums.Transmission
import xcode.biz.enums.VehicleBrand
import xcode.biz.enums.VehicleType
import java.util.Date

@Data
@TableName("t_vehicle")
@Builder
@NoArgsConstructor
@AllArgsConstructor
class Vehicle {

    @TableId(type = IdType.AUTO)
    var id = 0

    var companyId: Int? = null
    var name = ""
    var vehicleType: VehicleType? = null
    var transmission: Transmission? = null
    var year = ""
    var licenseNumber = ""
    var seat: Int? = null
    var rating: Int? = null
    var engineType: EngineType? = null
    var brand: VehicleBrand? = null
    var createdBy: Int? = null

    @TableField(fill = FieldFill.INSERT)
    var createdAt: Date? = null

    @TableField(fill = FieldFill.INSERT_UPDATE)
    var updatedAt: Date? = null

    var deletedAt: Date? = null
}
