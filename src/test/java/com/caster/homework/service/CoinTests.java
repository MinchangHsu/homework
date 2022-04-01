package com.caster.homework.service;

import org.json.JSONObject;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CoinTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void callCoinDeskApi() throws Exception {
        String returnString = mockMvc.perform(MockMvcRequestBuilders.get("/coin/fetching"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertEquals("callCoinDeskApi -> 成功", "success", returnString);
    }

    @Test
    @Order(2)
    public void callCoinDeskApiTransFer() throws Exception {
        String returnString = mockMvc.perform(MockMvcRequestBuilders.get("/coin/transfer"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println("callCoinDeskApiTransFer -> " + returnString);
    }

    @Test
    @Order(3)
    public void insertCoin() throws Exception {
        /**
         * {
         *     "exchangeRateTimeReq": {
         *         "updated": "2022/03/30 12:55:00",
         *         "updatedISO": "2022/03/30 12:55:00",
         *         "updateduk": "2022/03/30 13:55:00"
         *     },
         *     "disclaimer": "This data was produced from the CoinDesk Bitcoin Price Index (USD). Non-USD currency data converted using hourly conversion rate from openexchangerates.org",
         *     "chartName": "Bitcoin",
         *     "exchangeRateList": [
         *         {
         *             "code": "USD",
         *             "symbol": "&#36;",
         *             "rate": "47,317.6167",
         *             "description": "United States Dollar",
         *             "rate_float": 47317.6167
         *         },
         *         {
         *             "code": "GBP",
         *             "symbol": "&pound;",
         *             "rate": "35,957.5559",
         *             "description": "British Pound Sterling",
         *             "rate_float": 35957.5559
         *         },
         *         {
         *             "code": "EUR",
         *             "symbol": "&euro;",
         *             "rate": "42,479.0118",
         *             "description": "Euro",
         *             "rate_float": 42479.0118
         *         }
         *     ]
         * }
         */

        String jsonStr = "{\n" +
                "    \"exchangeRateTimeReq\": {\n" +
                "        \"updated\": \"2022/03/30 12:55:00\",\n" +
                "        \"updatedISO\": \"2022/03/30 12:55:00\",\n" +
                "        \"updateduk\": \"2022/03/30 13:55:00\"\n" +
                "    },\n" +
                "    \"disclaimer\": \"This data was produced from the CoinDesk Bitcoin Price Index (USD). Non-USD currency data converted using hourly conversion rate from openexchangerates.org\",\n" +
                "    \"chartName\": \"Bitcoin\",\n" +
                "    \"exchangeRateList\": [\n" +
                "        {\n" +
                "            \"code\": \"USD\",\n" +
                "            \"symbol\": \"&#36;\",\n" +
                "            \"rate\": \"47,317.6167\",\n" +
                "            \"description\": \"United States Dollar\",\n" +
                "            \"rate_float\": 47317.6167\n" +
                "        },\n" +
                "        {\n" +
                "            \"code\": \"GBP\",\n" +
                "            \"symbol\": \"&pound;\",\n" +
                "            \"rate\": \"35,957.5559\",\n" +
                "            \"description\": \"British Pound Sterling\",\n" +
                "            \"rate_float\": 35957.5559\n" +
                "        },\n" +
                "        {\n" +
                "            \"code\": \"EUR\",\n" +
                "            \"symbol\": \"&euro;\",\n" +
                "            \"rate\": \"42,479.0118\",\n" +
                "            \"description\": \"Euro\",\n" +
                "            \"rate_float\": 42479.0118\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        JSONObject postData = new JSONObject(jsonStr);
        String responseStr = mockMvc.perform(MockMvcRequestBuilders.post("/coin")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(postData)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println(responseStr);
    }

    @Test
    @Order(4)
    public void findAllCoin() throws Exception {
        String responseStr = mockMvc.perform(MockMvcRequestBuilders.get("/coin/list"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(responseStr);
    }

    @Test
    @Order(5)
    public void updateCoin() throws Exception {
        String jsonStr = "{\n" +
                "    \"exchangeRateTimeReq\": {\n" +
                "        \"id\": 2,\n" +
                "        \"updated\": \"2022/03/30 12:55:01\",\n" +
                "        \"updatedISO\": \"2022/03/30 12:55:01\",\n" +
                "        \"updateduk\": \"2022/03/30 12:55:01\"\n" +
                "    },\n" +
                "    \"disclaimer\": \"This data was produced from the CoinDesk Bitcoin Price Index (USD). Non-USD currency data converted using hourly conversion rate from openexchangerates.org\",\n" +
                "    \"chartName\": \"Bitcoin\",\n" +
                "    \"exchangeRateList\": [\n" +
                "        {\n" +
                "            \"id\": 3,\n" +
                "            \"code\": \"USD\",\n" +
                "            \"symbol\": \"&#36;\",\n" +
                "            \"rate\": \"47,317.111\",\n" +
                "            \"description\": \"United States Dollar\",\n" +
                "            \"rate_float\": 47317.111\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 4,\n" +
                "            \"code\": \"GBP\",\n" +
                "            \"symbol\": \"&pound;\",\n" +
                "            \"rate\": \"35,957.111\",\n" +
                "            \"description\": \"British Pound Sterling\",\n" +
                "            \"rate_float\": 35957.111\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\": 5,\n" +
                "            \"code\": \"EUR\",\n" +
                "            \"symbol\": \"&euro;\",\n" +
                "            \"rate\": \"42,479.111\",\n" +
                "            \"description\": \"Euro\",\n" +
                "            \"rate_float\": 42479.111\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        JSONObject putData = new JSONObject(jsonStr);

        String responseStr = mockMvc.perform(MockMvcRequestBuilders.put("/coin/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(putData)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(responseStr);
    }

    @Test
    @Order(6)
    public void deleteCoin() throws Exception {
        String responseStr = mockMvc.perform(MockMvcRequestBuilders.delete("/coin/1"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(responseStr);
    }
}
