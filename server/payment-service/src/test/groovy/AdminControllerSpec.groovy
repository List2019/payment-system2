import com.payment_system.DemoApplication
import com.payment_system.model.entity.Account
import com.payment_system.model.entity.User
import com.payment_system.repository.AccountRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.ext.ScriptUtils
import org.testcontainers.jdbc.JdbcDatabaseDelegate
import org.testcontainers.spock.Testcontainers
import spock.lang.Shared
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

@Testcontainers
@SpringBootTest(classes = DemoApplication.class)
@AutoConfigureMockMvc
class AdminControllerSpec extends Specification {

    @Autowired
    private MockMvc mockMvc

    @Autowired
    private AccountRepository accountRepository

    @Shared
    static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:11.1")
            .withDatabaseName("foo")
            .withUsername("foo")
            .withPassword("secret")

    @DynamicPropertySource
    static void addConnectionProperties(DynamicPropertyRegistry registry) {
        postgreSQLContainer
                .withInitScript("schema.sql")
                .start()

        def delegate = new JdbcDatabaseDelegate(postgreSQLContainer, "")
        ScriptUtils.runInitScript(delegate, "data.sql")

        registry.add("spring.datasource.url", () -> postgreSQLContainer.getJdbcUrl())
        registry.add("spring.datasource.username", () -> postgreSQLContainer.getUsername())
        registry.add("spring.datasource.password", () -> postgreSQLContainer.getPassword())
    }

    def "get all accounts"() {
        when:
        def response = mockMvc.perform(get("/api/v1/admin/accounts"))
                .andReturn()
                .getResponse()

        then:
        response.getStatus() == HttpStatus.OK.value()
        def type = new TypeToken<ArrayList<Account>>() {}.getType()
        List<Account> accounts = new Gson().fromJson(response.getContentAsString(), type)
        accounts.size() == 4
    }

    def "find user by account number"() {
        when:
        def response = mockMvc.perform(get("/api/v1/admin/user/5285787197053207"))
                .andReturn()
                .getResponse()

        then:
        response.getStatus() == HttpStatus.OK.value()
        def user = new Gson().fromJson(response.getContentAsString(), User)
        Objects.nonNull(user)
        user.getId() == UUID.fromString("18cba0ef-7d6e-4441-8796-26321cd5dfb5")
    }

    def "delete account by account number"() {
        when:
        def response = mockMvc.perform(delete("/api/v1/admin/account/5456974023407780"))
                .andReturn()
                .getResponse()

        then:
        response.getStatus() == HttpStatus.OK.value()
        accountRepository.findById("5456974023407780").isEmpty()
    }
}
