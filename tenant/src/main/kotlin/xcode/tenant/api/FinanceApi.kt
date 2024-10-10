package xcode.tenant.api

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import xcode.biz.domain.response.BaseResponse
import xcode.biz.domain.response.finance.BalanceResponse
import xcode.biz.service.tenant.FinanceService

@RestController
@RequestMapping(value = ["finance"])
class FinanceApi @Autowired constructor(
    private val financeService: FinanceService,
) {

    @GetMapping("/balance")
    fun getBalance(): BaseResponse<BalanceResponse> {
        return financeService.getBalance()
    }
}
