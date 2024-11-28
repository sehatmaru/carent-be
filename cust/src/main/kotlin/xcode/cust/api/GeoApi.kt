package xcode.cust.api

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import xcode.biz.domain.response.BaseResponse
import xcode.biz.domain.response.GeoResponse
import xcode.biz.service.GeoService

@RestController
@RequestMapping(value = ["geo"])
class GeoApi @Autowired constructor(
    private val geoService: GeoService,
) {

    @GetMapping("/provinces")
    fun getProvinces(@RequestParam("name", required = false) name: String): BaseResponse<List<GeoResponse>> {
        return geoService.getProvinces(name)
    }

    @GetMapping("/regencies/{provinceId}")
    fun getRegencies(
        @PathVariable("provinceId", required = false) @Validated provinceId: Int,
        @RequestParam("name", required = false) name: String,
    ): BaseResponse<List<GeoResponse>> {
        return geoService.getRegencies(provinceId, name)
    }

    @GetMapping("/districts/{regencyId}")
    fun getDistricts(
        @PathVariable("regencyId", required = false) @Validated regencyId: Int,
        @RequestParam("name", required = false) name: String,
    ): BaseResponse<List<GeoResponse>> {
        return geoService.getDistricts(regencyId, name)
    }
}
