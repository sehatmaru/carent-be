package xcode.biz.domain.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Options
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update
import xcode.biz.domain.model.Company
import xcode.biz.domain.request.company.CompanyUpdateRequest
import xcode.biz.domain.response.company.CompanyResponse

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


    @Select("""SELECT * FROM t_company WHERE id = #{companyId}""")
    fun getCompany(
        @Param("companyId") companyId: Int
    ): CompanyResponse?

    @Update(
        """
        UPDATE t_company SET firm_name = #{data.firmName}, alias_name = #{data.aliasName}, address = #{data.address}, mobile = #{data.mobile}, updated_date = NOW()
        WHERE id = #{data.id}
    """,
    )
    fun updateCompany(@Param("data") data: CompanyUpdateRequest)
}
