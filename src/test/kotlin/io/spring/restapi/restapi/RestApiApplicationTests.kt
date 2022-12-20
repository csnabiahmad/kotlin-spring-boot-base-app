package io.spring.restapi.restapi

import com.fasterxml.jackson.databind.ObjectMapper
import io.spring.restapi.restapi.model.NFT
import io.spring.restapi.restapi.utils.Routes
import org.junit.jupiter.api.Test
import org.mockito.internal.matchers.GreaterThan
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
@AutoConfigureMockMvc
@SpringBootTest
class RestApiApplicationTests(
    @Autowired val mockMvc: MockMvc,
    @Autowired val objectMapper: ObjectMapper
) {

//    @Test
//    fun contextLoads() {
//    }

    @Test
    fun `Assert NFTs has CryptoPunks as the first item`() {
        mockMvc.get("/nfts/get_all_nft") // 1
            .andExpect { // 2
                status { isOk() } // 3
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$[0].id") { value(1) } // 4
                jsonPath("$[0].name") { value("CryptoPunks") }
                jsonPath("$[0].floor_price") { value(100) }
                jsonPath("$.length()") { GreaterThan(1) }
            }
    }

    @Test
    fun `Assert that we can create an NFT`() {
        mockMvc.get("${Routes.GET_NFT_BY_ID}/6")
            .andExpect {
                status { isNotFound() }
            }
        val newNFT = NFT(0, "Loot", 45.3)
        mockMvc.post(Routes.BASE) {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(newNFT)
        }
            .andExpect {
                status { isCreated() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$.name") { value("Loot") }
                jsonPath("$.floor_price") { value(45.3) }
                jsonPath("$.id") { value(6) }
            }
        mockMvc.get("${Routes.GET_NFT_BY_ID}/6")
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$.name") { value("Loot") }
                jsonPath("$.floor_price") { value(45.3) }
                jsonPath("$.id") { value(6) }
            }
    }
}
