package grandoichii.dev.todoapp;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestConfiguration(proxyBeanMethods = false)
public class TestTodoappApplication {

  @Bean
  @ServiceConnection
  PostgreSQLContainer<?> postgresContainer() {
    return new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"));
  }

  public static void main(String[] args) {
    SpringApplication.from(TodoappApplication::main).with(TestTodoappApplication.class).run(args);
  }

  @Test
  void connected() {
    assertTrue(postgresContainer().isCreated());
    assertTrue(postgresContainer().isRunning());
  }

}

