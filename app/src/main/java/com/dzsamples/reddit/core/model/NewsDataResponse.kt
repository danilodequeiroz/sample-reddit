package com.dzsamples.reddit.core.model

import com.dzsamples.reddit.core.util.DefaultData

/**
 * Criado por danilodequeiroz on 18/10/17.
 */
data class NewsDataResponse(val data: NewsChildrenResponse?,
                            val kind: String = DefaultData.getString())