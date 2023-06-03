package youth.week6.testUtils;

import io.restassured.RestAssured;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import youth.week6.member.participant.entity.Allergens;
import youth.week6.member.participant.repository.AllergensRepository;
import youth.week6.testFixture.AllergensFixture;

@ActiveProfiles(profiles = "test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class SpringBootTestHelper {

    @LocalServerPort
    int port;
    @Autowired
    private DatabaseCleanup databaseCleanup;

    @Autowired
    private AllergensRepository allergensRepository;

    protected void init() {
        RestAssured.port = port;
        databaseCleanup.execute();
    }

    protected List<Allergens> savedAllergens() {
        List<Allergens> allergens = Arrays.stream(AllergensFixture.values())
            .map((allergensFixture -> new Allergens(allergensFixture.name())))
            .collect(Collectors.toList());
        return allergensRepository.saveAll(allergens);
    }

    protected Map<AllergensFixture, Allergens> findAllAllergens() {
        return allergensRepository.findAll()
            .stream().collect(
                Collectors.toMap(
                    allergens -> AllergensFixture.valueOf(allergens.getName()),
                    allergens -> allergens
                )
            );
    }
}
