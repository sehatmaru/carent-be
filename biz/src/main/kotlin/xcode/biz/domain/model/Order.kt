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
import xcode.biz.enums.OrderStatus
import java.util.Date

@Data
@TableName("t_order")
@Builder
@NoArgsConstructor
@AllArgsConstructor
class Order {

    @TableId(type = IdType.AUTO)
    var id = 0

    var customerId: Int? = null
    var productId: Int? = null
    var vehicleId: Int? = null
    var status: OrderStatus? = null
    var rating: Int? = null

    @TableField(fill = FieldFill.INSERT)
    var createdDate: Date? = null

    @TableField(fill = FieldFill.INSERT_UPDATE)
    var updatedDate: Date? = null

    var deletedDate: Date? = null
}
