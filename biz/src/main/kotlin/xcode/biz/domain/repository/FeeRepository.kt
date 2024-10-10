package xcode.biz.domain.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import xcode.biz.domain.model.Fee

@Repository
interface FeeRepository : JpaRepository<Fee?, String?> {

    @Query(
        value = "SELECT * FROM t_fee",
        nativeQuery = true,
    )
    fun getFeeList(): List<Fee>?
}
