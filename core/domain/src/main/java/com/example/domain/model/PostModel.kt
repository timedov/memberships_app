package com.example.domain.model

class PostModel(
    val id: Long,
    val title: String,
    val image: String,
    val category: String,
    val postedAgo: String,
    val author: String
)

enum class Tier(val code: Int) {
    ALL_TIERS(-1),
    TIER_1(0),
    TIER_2(1),
}