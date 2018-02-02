package com.dzsamples.reddit.core.model

import com.dzsamples.reddit.core.util.DefaultData

/**
 * Criado por danilodequeiroz on 18/10/17.
 */
data class NewsChildrenDataNvl2Response(val data: NewsData?,
                                        val kind: String = DefaultData.getString())