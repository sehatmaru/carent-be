package xcode.biz.domain.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper
import xcode.biz.domain.model.Booking

@Mapper
interface BookingMapper : BaseMapper<Booking>
