//package ru.spb.anohin.webrise_test_task.config.loggercfg;
//
//import jakarta.annotation.PostConstruct;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Component;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//@Component
//public class LogTableInitializer {
//
//    private static final Logger logger = LogManager.getLogger(LogTableInitializer.class);
//    private final JdbcTemplate jdbcTemplate;
//
//    public LogTableInitializer(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    @PostConstruct
//    public void initialize() {
//        String createTableSQL = """
//            CREATE TABLE IF NOT EXISTS application_logs (
//                log_id BIGSERIAL PRIMARY KEY,
//                log_date TIMESTAMP,
//                log_level VARCHAR(10),
//                logger VARCHAR(255),
//                message TEXT,
//                exception TEXT
//            )
//            """;
//
//        try {
//            jdbcTemplate.execute(createTableSQL);
//            logger.info("Log table created or already exists");
//
//            // Проверка существования таблицы (опционально)
//            boolean tableExists = jdbcTemplate.queryForObject(
//                    "SELECT EXISTS (SELECT FROM information_schema.tables WHERE table_name = 'application_logs')",
//                    Boolean.class);
//
//            logger.info("Table existence check: " + tableExists);
//        } catch (Exception e) {
//            logger.error("Failed to create log table", e);
//        }
//    }
//}
