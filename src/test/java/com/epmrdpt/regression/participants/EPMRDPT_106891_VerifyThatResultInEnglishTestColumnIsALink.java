package com.epmrdpt.regression.participants;

import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.ProfileScreen;
import com.epmrdpt.screens.ReactParticipantsTrainingScreen;
import com.epmrdpt.services.ExaminatorService;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactParticipantsService;
import com.epmrdpt.services.ReactTrainingManagementService;
import com.epmrdpt.utils.ExcelUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.epmrdpt.bo.user.UserFactory.*;
import static com.epmrdpt.utils.FileUtils.*;
import static java.lang.String.format;
import static org.testng.Assert.assertTrue;

@Test(description = "EPMRDPT_106891_VerifyThatResultInEnglishTestColumnIsALink",
        groups = {"full", "regression"})
public class EPMRDPT_106891_VerifyThatResultInEnglishTestColumnIsALink {

    private static String DOWNLOADS_PATH = getDownloadedFilePath();
    private final String TRAINING_NAME = "AutoTest_WithPaidPricing +1";
    public static final String URL_PATTERN_STRING = "https://examinator-stage.lab.epam.com/Main/Trainer/AssignmentContainers/";
    private String fileName;
    private int row = 1;
    private int column = 5;
    private final int DEFAULT_SHEET_NUMBER = 0;
    private final String FILE_NAME_PATTERN = "StudentsInTraining - %s.xlsx";

    @BeforeClass(inheritGroups = false, alwaysRun = true)
    private void setup() {
        LoginService loginService = new LoginService();
        loginService
                .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(withSimplePermissionAndWithTraining())
                .clickProfileNavigationLink();
        new ExaminatorService()
                .failEnglishTestInProfile();
        new ProfileScreen()
                .clickBackButton();
        new HeaderScreen()
                .waitProfileMenuDisplayed()
                .signOut();
        loginService
                .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asRecruiter())
                .clickReactTrainingManagementLink();
        ReactParticipantsTrainingScreen reactParticipantsTrainingScreen = new ReactTrainingManagementService()
                .searchTrainingByNameAndRedirectOnIt(TRAINING_NAME)
                .clickReactParticipantsTab()
                .waitScreenLoading();
        ReactParticipantsService reactParticipantsService = new ReactParticipantsService();
        reactParticipantsService
                .waiteOfEnglishTestToBeLinked();
        fileName = format(FILE_NAME_PATTERN,
                reactParticipantsTrainingScreen
                        .getPlanId());
        reactParticipantsService
                .downloadSelectedParticipantsFile();
        waitForFileExistence(DOWNLOADS_PATH, fileName);
    }

    @Test
    public void checkExcelFileIsDownloaded() {
        assertTrue(isFileExists(DOWNLOADS_PATH, fileName),
                format("File [%s] isn't downloaded!", fileName));
    }

    @Test(dependsOnMethods = {"checkExcelFileIsDownloaded"})
    public void checkDownloadedExcelFileHasURLLink() {
        String excelString = new ExcelUtils(DOWNLOADS_PATH, fileName, DEFAULT_SHEET_NUMBER)
                .getCellURL(row, column);
        assertTrue(excelString.contains(URL_PATTERN_STRING));
    }

    @AfterClass(inheritGroups = false, alwaysRun = true)
    public void deleteDownloadedFile() {
        deleteExistingFile();
    }

    private void deleteExistingFile() {
        deleteFile(DOWNLOADS_PATH, fileName);
    }
}
