package be.avidoo.ddd;


import be.avidoo.ddd.common.Aggregate;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "be.avidoo.ddd")
public class AggregateArchitectureTest {

    @ArchTest
    public static ArchRule entityAndValueObjectVisibilityRule =
            classes()
                    .that().areAnnotatedWith(Aggregate.class)
                    .should().bePublic();

}
