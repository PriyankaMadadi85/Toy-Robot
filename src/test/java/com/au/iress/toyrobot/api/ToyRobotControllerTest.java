package com.au.iress.toyrobot.api;

import com.au.iress.toyrobot.api.api.ToyRobotController;
import com.au.iress.toyrobot.api.exception.ToyRobotException;
import com.au.iress.toyrobot.api.response.ToyRobotResponse;
import com.au.iress.toyrobot.api.service.ToyRobotService;
import com.au.iress.toyrobot.api.util.ObjectMapperUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ToyRobotController.class)
public class ToyRobotControllerTest {
    private static final Logger logger = LoggerFactory.getLogger("ToyRobotControllerTest");
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ToyRobotService robotService;

    @Autowired
    ObjectMapperUtil util;

    @Test
    public void testPlace() throws Exception {
        String jsonString = "{\"command\": \"PLACE 0,0,NORTH\"}";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/robot/simulate")
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String placeResult = result.getResponse().getContentAsString();

        ToyRobotResponse response = util.convertJSONStringToObject(placeResult);
        logger.info(response.getPosition());
        assertNotNull(response.getPosition());
        assertEquals("Robot is at position : (0, 0) facing NORTH",response.getPosition());

    }

    @Test
    public void testPlaceInvalidSquareLength() throws Exception {
        String jsonString = "{\"command\": \"PLACE 10,0,NORTH\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/robot/simulate")
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ToyRobotException))
                .andExpect(result -> assertEquals("Robot must be placed on a 5*5 square", result.getResolvedException().getMessage()));


    }

    @Test
    public void testPlaceInvalidDirection() throws Exception {
        String jsonString = "{\"command\": \"PLACE 0,0,NE\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/robot/simulate")
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ToyRobotException))
                .andExpect(result -> assertEquals("Invalid Direction in Place command", result.getResolvedException().getMessage()));

    }

    @Test
    public void testPlaceMoveAndLeft() throws Exception {

        String jsonString = "{\"command\": \"PLACE 1,2,EAST\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/robot/simulate")
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        jsonString = "{\"command\": \"MOVE\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/robot/simulate")
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        jsonString = "{\"command\": \"LEFT\"}";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/robot/simulate")
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String placeResult = result.getResponse().getContentAsString();

        ToyRobotResponse response = util.convertJSONStringToObject(placeResult);
        logger.info(response.getPosition());
        assertNotNull(response.getPosition());
        assertEquals("Robot is at position : (2, 2) facing NORTH",response.getPosition());
    }

    @Test
    public void testPlaceandMove() throws Exception {

        String jsonString = "{\"command\": \"PLACE 2,2,SOUTH\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/robot/simulate")
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        jsonString = "{\"command\": \"MOVE\"}";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/robot/simulate")
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();


        String placeResult = result.getResponse().getContentAsString();

        ToyRobotResponse response = util.convertJSONStringToObject(placeResult);
        logger.info(response.getPosition());
        assertNotNull(response.getPosition());
        assertEquals("Robot is at position : (2, 1) facing SOUTH",response.getPosition());
    }

    @Test
    public void testPlaceandRotate() throws Exception {

        String jsonString = "{\"command\": \"PLACE 1,3,EAST\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/robot/simulate")
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        jsonString = "{\"command\": \"RIGHT\"}";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/robot/simulate")
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();


        String placeResult = result.getResponse().getContentAsString();

        ToyRobotResponse response = util.convertJSONStringToObject(placeResult);
        logger.info(response.getPosition());
        assertNotNull(response.getPosition());
        assertEquals("Robot is at position : (1, 3) facing SOUTH",response.getPosition());
    }

    @Test
    public void testPlaceandMoveOutofSquare() throws Exception {

        String jsonString = "{\"command\": \"PLACE 0,0,SOUTH\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/robot/simulate")
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        jsonString = "{\"command\": \"MOVE\"}";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/robot/simulate")
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();


        String placeResult = result.getResponse().getContentAsString();

        ToyRobotResponse response = util.convertJSONStringToObject(placeResult);
        assertNotNull(response.getErrorCode());
        assertEquals("Robot cannot move outside the square",response.getErrorMessage());
    }

    @Test
    public void testIsRobotPlaced() throws Exception {


        String jsonString = "{\"command\": \"MOVE\"}";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/robot/simulate")
                .content(jsonString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();


        String placeResult = result.getResponse().getContentAsString();

        ToyRobotResponse response = util.convertJSONStringToObject(placeResult);
        assertNotNull(response.getErrorCode());
        assertEquals("Robot not placed on table",response.getErrorMessage());
    }
}
