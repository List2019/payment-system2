package com.auth.refresh.token.repository;

import com.auth.refresh.token.entity.RefreshToken;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.repository.CrudRepository;

import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.UUID;

import static io.micronaut.data.model.query.builder.sql.Dialect.H2;

@JdbcRepository(dialect = H2)
public interface RefreshTokenRepository extends CrudRepository<RefreshToken, UUID> {

    @Transactional
    RefreshToken save(@NonNull @NotNull UUID id,
                      @NonNull @NotBlank String username,
                      @NonNull @NotBlank String refreshToken,
                      @NonNull @NotNull Boolean revoked);

    Optional<RefreshToken> findByRefreshToken(@NonNull @NotBlank String refreshToken);

    long updateByUsername(@NonNull @NotBlank String username,
                          boolean revoked);
}