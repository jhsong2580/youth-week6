package youth.week6.testUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;

public class SnakeCaseObjectMapper {

    public static ObjectMapper objectMapper = new ObjectMapper().setPropertyNamingStrategy(
            PropertyNamingStrategies.SNAKE_CASE)
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true)
        .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS);

    public static String 요청전문_snake_case_변경(Object 요청전문) {
        String 변환결과 = "";
        try {
            변환결과 = objectMapper.writeValueAsString(요청전문);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("TEST : 전문 변환 실패 ->" + e.getMessage());
        }
        return 변환결과;
    }
}
