package com.heiwait.tripagency.domain;

import com.github.cukedoctor.Cukedoctor;
import com.github.cukedoctor.api.CukedoctorConverter;
import com.github.cukedoctor.api.DocumentAttributes;
import com.github.cukedoctor.api.model.Feature;
import com.github.cukedoctor.config.GlobalConfig;
import com.github.cukedoctor.parser.FeatureParser;
import com.github.cukedoctor.util.FileUtil;
import com.heiwait.tripagency.domain.cucumber.BDDRunner;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Suite.class)
@Suite.SuiteClasses({BDDRunner.class})
public class RunAllFeatureAndGenerateReportTest {

    //@AfterClass
    public static void generateExecutionReport() throws Exception {
        List<String> pathToCucumberJsonFiles = FileUtil.findJsonFiles("target/cucumber/");
        List<Feature> features = FeatureParser.parse(pathToCucumberJsonFiles);
        DocumentAttributes attrs = GlobalConfig.getInstance().getDocumentAttributes();
        attrs.toc("left")
                .backend("html5")
                .docType("pdf")
                .icons("font").numbered(false)
                .sourceHighlighter("coderay")
                .docTitle("Documentation Title")
                .sectAnchors(true)
                .sectLink(true);

        CukedoctorConverter converter = Cukedoctor.instance(features, attrs);
        converter.setFilename("target/living_documentation/TripFees.adoc");

        converter.saveDocumentation();
        assertThat(FileUtil.loadFile("target/living_documentation/TripFees.adoc")).exists();

        //FileUtil.saveFile("target/living_documentation/TripFees.pdf", "target/cucumber/TripFees.json");
    }
}
