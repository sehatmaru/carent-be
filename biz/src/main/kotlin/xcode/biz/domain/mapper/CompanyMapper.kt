package xcode.biz.domain.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Options
import org.apache.ibatis.annotations.Param
import xcode.biz.domain.model.Company

@Mapper
interface CompanyMapper : BaseMapper<Company> {

    @Insert(
        """
        INSERT INTO t_company (address, alias_name, firm_name, founding_date, mobile, rating)
        VALUES (#{company.address}, #{company.aliasName}, #{company.firmName}, #{company.foundingDate}, #{company.mobile}, 0)
    """,
    )
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    fun insertCompany(@Param("company") company: Company)
}
