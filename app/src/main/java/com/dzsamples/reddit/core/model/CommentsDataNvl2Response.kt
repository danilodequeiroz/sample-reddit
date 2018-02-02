package com.dzsamples.reddit.core.model

import com.dzsamples.reddit.core.util.DefaultData

/**
 * Criado por danilodequeiroz on 18/10/17.
 */
data class CommentsDataNvl2Response(val data: CommentsDataResponse?,
                                    val kind: String = DefaultData.getString())