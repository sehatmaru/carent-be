package xcode.tenant.api

import com.github.pagehelper.PageInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import xcode.biz.domain.request.vehicle.VehicleFilterRequest
import xcode.biz.domain.request.vehicle.VehicleRegisterRequest
import xcode.biz.domain.request.vehicle.VehicleUpdateRequest
import xcode.biz.domain.response.BaseResponse
import xcode.biz.domain.response.vehicle.VehicleResponse
import xcode.biz.service.tenant.VehicleService

@RestController
@RequestMapping(value = ["vehicle"])
class VehicleApi @Autowired constructor(
    private val vehicleService: VehicleService,
) {

    @PostMapping("/register")
    fun register(@RequestBody @Validated request: VehicleRegisterRequest): BaseResponse<Boolean> {
        return vehicleService.registerVehicle(request)
    }

    @PostMapping("/update/{vehicleId}")
    fun updateVehicle(
        @PathVariable("vehicleId") vehicleId: Int,
        @RequestBody @Validated request: VehicleUpdateRequest
    ): BaseResponse<Boolean> {
        return vehicleService.updateVehicle(vehicleId, request)
    }

    @PostMapping("/delete/{vehicleId}")
    fun deleteVehicle(@PathVariable("vehicleId") vehicleId: Int): BaseResponse<Boolean> {
        return vehicleService.deleteVehicle(vehicleId)
    }

    @PostMapping("/list")
    fun getVehicleList(
        @RequestBody @Validated request: VehicleFilterRequest,
        @RequestParam("pageNum") pageNumber: Int,
        @RequestParam("pageSize") pageSize: Int
    ): BaseResponse<PageInfo<VehicleResponse>> {
        return vehicleService.getVehicleList(request, pageNumber, pageSize)
    }
}
