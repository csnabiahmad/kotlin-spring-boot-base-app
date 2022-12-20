package io.spring.restapi.restapi.controller

import io.spring.restapi.restapi.exception.NFTNotFoundException
import io.spring.restapi.restapi.model.NFT
import io.spring.restapi.restapi.utils.Routes
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RequestMapping(Routes.BASE)
@RestController
class NFTController {

    @Value("\${company_name}")
    private lateinit var name: String

    private var NFTs = mutableListOf(
        NFT(1, "CryptoPunks", 100.0),
        NFT(2, "Sneaky Vampire Syndicate", 36.9),
        NFT(3, "The Sevens (Official)", 0.6),
        NFT(4, "Art Blocks Curated", 1.1),
        NFT(5, "Pudgy Penguins", 2.5),
    )


    @GetMapping(Routes.GET_ALL_NFT)
    fun getNFTs() = NFTs


    @GetMapping(Routes.GET_NFT_BY_ID)
    fun getNFTById(@PathVariable id: Int) : NFT? {
        return NFTs.firstOrNull { it.id == id } ?: throw NFTNotFoundException()
    }


    @PostMapping(Routes.SAVE_NFT)
    @ResponseStatus(HttpStatus.CREATED)
    fun postNFT(@RequestBody nft: NFT): List<NFT> {
        val maxId = NFTs.map { it.id }.maxOrNull() ?: 0
        val nextId = maxId + 1
        val newNft = NFT(id = nextId, name = nft.name, floor_price = nft.floor_price)
        NFTs.add(newNft)
        return NFTs
    }
}
