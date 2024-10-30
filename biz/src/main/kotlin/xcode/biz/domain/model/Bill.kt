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
import xcode.biz.enums.PaymentStatus
import xcode.biz.enums.PaymentType
import java.util.Date

@Data
@TableName("t_bill")
@Builder
@NoArgsConstructor
@AllArgsConstructor
class Bill {

    @TableId(type = IdType.AUTO)
    var id = 0

    var customerId: Int? = null
    var orderId: Int? = null
    var totalPayment: Int? = null
    var totalPaid: Int? = null
    var applicationFee: Double? = null
    var paymentType: PaymentType? = null
    var paymentStatus: PaymentStatus? = null

    @TableField(fill = FieldFill.INSERT)
    var createdDate: Date? = null

    @TableField(fill = FieldFill.INSERT_UPDATE)
    var updatedDate: Date? = null

    var verifiedDate: Date? = null
    var deletedDate: Date? = null
}
