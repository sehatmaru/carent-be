package xcode.biz.domain.response.finance

class BalanceReportResponse {
    var currentOrder = 0
    var orderHistory = mutableListOf<BalanceReportHistory>()
    var currentIncome = 0.0
    var incomeHistory = mutableListOf<BalanceReportHistory>()
    var currentRevenue = 0.0
    var revenueHistory = mutableListOf<BalanceReportHistory>()
    var currentCustomer = 0
    var customerHistory = mutableListOf<BalanceReportHistory>()
}

class BalanceReportHistory(var month: String, var value: Double)
