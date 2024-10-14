package xcode.biz.domain.model

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor
import xcode.biz.enums.FeeType

@Data
@TableName("t_fee")
@Builder
@NoArgsConstructor
@AllArgsConstructor
class Fee {

    @TableId(type = IdType.AUTO)
    var id = 0

    var percentage: Double? = null
    var price: Int? = null
    var type: FeeType? = null
}
