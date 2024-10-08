package xcode.biz.domain.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import xcode.biz.domain.model.Company

@Repository
interface CompanyRepository : JpaRepository<Company?, String?> {

    @Query(
        value = "SELECT * FROM t_company WHERE id = :id LIMIT 1",
        nativeQuery = true,
    )
    fun getUserCompany(@Param("id") id: Int): Company?
}
