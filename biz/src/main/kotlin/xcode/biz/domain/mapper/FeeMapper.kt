package xcode.biz.domain.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select
import xcode.biz.domain.model.Fee

@Mapper
interface FeeMapper : BaseMapper<Fee> {

    @Select("""SELECT * FROM t_fee""")
    fun getFeeList(): List<Fee>?
}
