import com.demo.DemoApplication
import com.demo.dto.TransferRequestDto
import com.demo.entity.Account
import com.demo.entity.User
import com.demo.repository.AccountRepository
import com.google.gson.Gson
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.ext.ScriptUtils
import org.testcontainers.jdbc.JdbcDatabaseDelegate
import org.testcontainers.spock.Testcontainers
import spock.lang.Shared
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

@Testcontainers
@SpringBootTest(classes = DemoApplication.class)
@AutoConfigureMockMvc
class HomeControllerSpec extends Specification {

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

    def "New account was added"() {
        given:
        def user = new User(UUID.fromString("baffb5b0-f258-48f8-9d8e-7dbae34288fb"),
                "Aleksandr", "Matveev", "admin", "admin", "admin", "test@mail.com", new ArrayList<Account>())
        def account = new Account("d8f0eb1b-6c87-414f-9428-2aa59ed42e1c", BigDecimal.TEN, false, user)

        when:
        def response = mockMvc.perform(
                post("/api/v1/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(account))
        )
                .andReturn().getResponse()

        then:
        Objects.nonNull(accountRepository.findById("d8f0eb1b-6c87-414f-9428-2aa59ed42e1c"))
        response.getStatus() == HttpStatus.CREATED.value()
    }

    def "deposit account"() {
        when:
        def response = mockMvc.perform(
                post("/api/v1/accounts/346598060226129")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(BigDecimal.TEN))
        )
                .andReturn().getResponse()

        then:
        accountRepository.findById("346598060226129").get().balance == BigDecimal.valueOf(110L)
        response.getStatus() == HttpStatus.OK.value()
    }

    def "block account"() {
        when:
        def response = mockMvc.perform(
                patch("/api/v1/accounts/346598060226129")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(Boolean.TRUE))
        )
                .andReturn().getResponse()

        then:
        accountRepository.findById("346598060226129").get().isBlocked()
        response.getStatus() == HttpStatus.OK.value()
    }

    def "transfer money"() {
        given:
        def dto = new TransferRequestDto("5285787197053207", "5456974023407780", BigDecimal.TEN)

        when:
        def response = mockMvc.perform(
                post("/api/v1/accounts/transfer")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(dto))
        )
                .andReturn().getResponse()

        then:
        response.getStatus() == HttpStatus.OK.value()
        accountRepository.findById("5285787197053207").get().balance == BigDecimal.ZERO
    }
}
