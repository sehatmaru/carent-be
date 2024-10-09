package xcode.biz.domain.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import xcode.biz.domain.model.Product

@Repository
interface ProductRepository : JpaRepository<Product?, String?>
