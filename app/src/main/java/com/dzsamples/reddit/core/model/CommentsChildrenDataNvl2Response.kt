package com.dzsamples.reddit.core.model

import com.dzsamples.reddit.core.util.DefaultData

/**
 * Criado por danilodequeiroz on 18/10/17.
 */
data class CommentsChildrenDataNvl2Response(val data: CommentData?,
                                            val kind: String = DefaultData.getString())