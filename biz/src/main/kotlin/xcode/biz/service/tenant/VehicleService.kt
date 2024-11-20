package xcode.biz.service.tenant

import com.github.pagehelper.PageInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import xcode.biz.domain.dto.CurrentUser
import xcode.biz.domain.mapper.ProductMapper
import xcode.biz.domain.mapper.VehicleMapper
import xcode.biz.domain.request.vehicle.VehicleFilterRequest
import xcode.biz.domain.request.vehicle.VehicleRegisterRequest
import xcode.biz.domain.request.vehicle.VehicleUpdateRequest
import xcode.biz.domain.response.BaseResponse
import xcode.biz.domain.response.vehicle.VehicleResponse
import xcode.biz.exception.AppException
import xcode.biz.shared.ResponseCode.NOT_FOUND
import xcode.biz.shared.ResponseCode.UNAUTHORIZED

@Service
class VehicleService @Autowired constructor(
    private val vehicleMapper: VehicleMapper,
    private val productMapper: ProductMapper
) {

    fun registerVehicle(request: VehicleRegisterRequest): BaseResponse<Boolean> {
        checkPermission()
        request.validate()

        productMapper.getProductById(request.productId) ?: throw AppException(NOT_FOUND)

        vehicleMapper.insertVehicle(request)
        productMapper.increaseProductQuantity(request.productId)

        return BaseResponse()
    }

    fun updateVehicle(vehicleId: Int, request: VehicleUpdateRequest): BaseResponse<Boolean> {
        checkPermission()

        vehicleMapper.updateVehicle(vehicleId, request)

        return BaseResponse()
    }

    fun deleteVehicle(vehicleId: Int): BaseResponse<Boolean> {
        checkPermission()

        val vehicle = vehicleMapper.getVehicle(vehicleId) ?: throw AppException(NOT_FOUND)

        productMapper.getProductById(vehicle.productId!!) ?: throw AppException(NOT_FOUND)
        vehicleMapper.deleteVehicle(vehicleId)
        productMapper.decreaseProductQuantity(vehicle.productId!!)

        return BaseResponse()
    }

    fun getVehicleList(
        request: VehicleFilterRequest,
        pageNum: Int,
        pageSize: Int
    ): BaseResponse<PageInfo<VehicleResponse>> {
        val result = BaseResponse<PageInfo<VehicleResponse>>()

        checkPermission()

        result.setSuccess(PageInfo(vehicleMapper.getVehicleList(CurrentUser.get().companyId!!, request)))

        return result
    }

    fun checkPermission() {
        if (!CurrentUser.get().isManager()) {
            throw AppException(UNAUTHORIZED)
        }
    }
}
