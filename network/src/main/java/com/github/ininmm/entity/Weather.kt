package com.github.ininmm.entity

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

/**
 * Created by Michael Lien
 * on 2018/7/26.
 */
@Root(name = "rss", strict = false)
data class Weather (
    @field:Element(name = "channel")
    var channel: Channel? = null
)


