package xcode.biz.domain.response

import com.fasterxml.jackson.annotation.JsonProperty

class GeoResponse {
    var id = ""
    var name = ""

    @JsonProperty(value = "province_id")
    var provinceId = ""

    @JsonProperty(value = "regency_id")
    var regencyId = ""
}
