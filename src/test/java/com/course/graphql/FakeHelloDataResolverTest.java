package com.course.graphql;

import com.netflix.graphql.dgs.DgsQueryExecutor;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class FakeHelloDataResolverTest {

    @Autowired
    DgsQueryExecutor dgsQueryExecutor;

    @Test
    void testOneHello(){
        var graphqlQuery = """
                {
                 oneHello {
                    text
                    randomNuber
                  }
                }
                """;
        String text = dgsQueryExecutor.executeAndExtractJsonPath(
                graphqlQuery, "data.oneHello.text");
        Integer randomNumber = dgsQueryExecutor.executeAndExtractJsonPath(
                graphqlQuery, "data.oneHello.randomNuber");
        assertFalse(StringUtils.isBlank(text));
        assertNotNull(randomNumber);
    }


    @Test
    void testAllHellos(){
        var graphqlQuery = """
                {
                 allHellos {
                    text
                    randomNuber
                  }
                }
                """;
        List<String> texts = dgsQueryExecutor.executeAndExtractJsonPath(graphqlQuery, "data.allHellos[*].text");
        List<Integer> randomNumbers  = dgsQueryExecutor.executeAndExtractJsonPath(graphqlQuery, "data.allHellos[*].randomNuber");
        assertNotNull(texts);
        assertFalse(texts.isEmpty());
        assertNotNull(randomNumbers);
        assertFalse(randomNumbers.isEmpty());
        assertEquals(texts.size(), randomNumbers.size());
    }
}
