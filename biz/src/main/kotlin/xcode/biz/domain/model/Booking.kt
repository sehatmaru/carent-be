package xcode.biz.domain.model

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor
import xcode.biz.enums.PickupType
import java.util.Date

@Data
@TableName("t_booking")
@Builder
@NoArgsConstructor
@AllArgsConstructor
class Booking {

    @TableId(type = IdType.AUTO)
    var id = 0

    var orderId: Int? = null
    var billIid: Int? = null
    var productId: Int? = null
    var pickupType: PickupType? = null
    var deliveryAddress = ""
    var returnAddress = ""
    var customerNotes = ""
    var adminNotes = ""
    var rating: Int? = null
    var startAt: Date? = null
    var endAt: Date? = null
}
