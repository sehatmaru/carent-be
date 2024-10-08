package xcode.tenant.api

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import xcode.biz.domain.request.vehicle.VehicleRegisterRequest
import xcode.biz.domain.response.BaseResponse
import xcode.biz.domain.response.auth.LoginResponse
import xcode.biz.domain.response.vehicle.VehicleResponse
import xcode.biz.service.tenant.VehicleService

@RestController
@RequestMapping(value = ["vehicle"])
class VehicleApi @Autowired constructor(
    private val vehicleService: VehicleService,
) {

    @PostMapping("/register")
    fun register(@RequestBody @Validated request: VehicleRegisterRequest): BaseResponse<LoginResponse> {
        return vehicleService.registerVehicle(request)
    }

    @GetMapping("/list")
    fun getVehicleList(): BaseResponse<List<VehicleResponse>> {
        return vehicleService.getVehicleList()
    }
}
