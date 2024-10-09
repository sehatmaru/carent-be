package xcode.biz.domain.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import xcode.biz.domain.model.Fee

@Repository
interface FeeRepository : JpaRepository<Fee?, String?>
