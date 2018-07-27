package com.github.ininmm.network.entity

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

/**
 * Created by Michael Lien
 * on 2018/7/27.
 */
@Root(name = "channel", strict = false)
class Channel (

    @field:ElementList(inline = true, name = "item", required = false)
    var weatherItems: List<WeatherItem>? = null
)