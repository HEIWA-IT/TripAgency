package com.heiwait.tripagency.domain;


import com.heiwait.tripagency.domain.cucumber.BDDRunner;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({BDDRunner.class})
public class RunAllFeatureAndGenerateReportTest {
}
