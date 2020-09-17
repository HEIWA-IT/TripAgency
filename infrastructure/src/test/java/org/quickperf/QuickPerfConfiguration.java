package org.quickperf;

import org.quickperf.config.SpecifiableGlobalAnnotations;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collection;

import static org.quickperf.sql.annotation.SqlAnnotationBuilder.*;

/**
 * This class configures QuickPerf global annotations that is to say annotations applying on
 * each test.
 */
public class QuickPerfConfiguration implements SpecifiableGlobalAnnotations {

    public Collection<Annotation> specifyAnnotationsAppliedOnEachTest() {

        return Arrays.asList(

                // Able to reveal N+1 selects
                // https://blog.jooq.org/2017/12/18/the-cost-of-jdbc-server-roundtrips/
                disableSameSelectTypesWithDifferentParams()

                , // https://use-the-index-luke.com/sql/where-clause/searching-for-ranges/like-performance-tuning
                disableLikeWithLeadingWildcard()

                , disableExactlySameSelects()

                // Preview feature
                //, disableQueriesWithoutBindParameters

        );

    }

}