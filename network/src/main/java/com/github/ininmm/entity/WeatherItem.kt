package com.github.ininmm.entity

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

/**
 * Created by Michael Lien
 * on 2018/7/27.
 */
@Root(name = "item", strict = false)
class WeatherItem (
    @field:Element(name = "pubDate")
    var pubDate: String? = null,

    @field:Element(name = "title", data = true)
    var title: String? = null,

    @field:Element(name = "link")
    var link: String? = null,

    @field:Element(name = "description", data = true)
    var description: String? = null
)