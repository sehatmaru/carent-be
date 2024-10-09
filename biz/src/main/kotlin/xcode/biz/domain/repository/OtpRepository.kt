package xcode.biz.domain.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import xcode.biz.domain.model.Otp

@Repository
interface OtpRepository : JpaRepository<Otp?, String?> {

    @Query(
        value = "SELECT * FROM t_otp WHERE code = :code AND verified_at IS NULL LIMIT 1",
        nativeQuery = true,
    )
    fun getUnverifiedOtp(@Param("code") code: String): Otp?
}
