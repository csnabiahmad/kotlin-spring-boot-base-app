package io.spring.restapi.restapi.utils

object Routes {
    const val BASE  : String = "/nfts"
    const val GET_ALL_NFT  : String = "$BASE/get_all_nft"
    const val GET_NFT_BY_ID  : String = "$BASE/get_nft/{id}"
    const val SAVE_NFT  : String = "$BASE/save_nft"
}