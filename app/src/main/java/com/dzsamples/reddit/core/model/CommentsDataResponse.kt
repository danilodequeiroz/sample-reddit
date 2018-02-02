package com.dzsamples.reddit.core.model

import com.dzsamples.reddit.core.util.DefaultData

/**
 * Criado por danilodequeiroz on 18/10/17.
 */
data class CommentsDataResponse(val children: List<CommentsChildrenDataNvl2Response> = emptyList(),
                                val before: String = DefaultData.getString(),
                                val after: String = DefaultData.getString())