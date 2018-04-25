package com.heiwait.tripagency.infrastructure.repository.liquibase;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import liquibase.Liquibase;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.ChangeLogParseException;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

/**
 * Helper pour le chargement de données via liquibase.
 * <p>
 * Une utilisation type de cette classe pour les tests : <BR/>
 * Liquibase liquibase = LiquibaseHelper.loadData(dataSource, dataChangelogFile);
 * try{
 * .. code de test
 * } finally {
 * LiquibaseHelper.rollbackAndClose(liquibase);
 * }
 *
 * @author Joan DAVID
 * @version $Revision$ $Date$
 * @date 20 mars 2017
 */
public class LiquibaseHelper {
    /**
     * Logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(LiquibaseHelper.class);

    /**
     * Import des données via un fichier liquibase.
     *
     * @param dataSource
     * @param dataChangelogFile Le fichier de chargement
     * @return Une connection Liquibase optionnelle, si le fichier a été trouvé
     * @throws RuntimeException
     */
    public static Optional<Liquibase> loadData(final DataSource dataSource,
                                               final String dataChangelogFile) {
        try {
            final Connection connection = dataSource.getConnection();
            final Liquibase liquibase = new Liquibase(dataChangelogFile,
                    new ClassLoaderResourceAccessor(), new JdbcConnection(connection));
            liquibase.update("");
            return Optional.of(liquibase);
        } catch (final ChangeLogParseException e) {
            LOG.debug(e.getMessage(), e);
            return Optional.empty();
        } catch (LiquibaseException | SQLException e) {
            throw new RuntimeException(e.getMessage(), e);

        }
    }

    /**
     * Rollback les données liquibase et ferme la connection.
     *
     * @param optLiquibase
     */
    public static void rollbackAndClose(final Optional<Liquibase> optLiquibase) {
        if (optLiquibase.isPresent()) {
            final Liquibase liquibase = optLiquibase.get();
            try {
                liquibase.dropAll();
                liquibase.getDatabase().close();
            } catch (final LiquibaseException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
    }

}
