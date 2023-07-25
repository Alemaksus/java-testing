package com.epmrdpt.regression.training_management;

import com.epmrdpt.screens.ReactAutoreplyTabScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.nio.file.FileSystems;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static com.epmrdpt.framework.ui.AbstractScreen.DEFAULT_TIMEOUT_FOR_PAGE_LOAD;
import static com.epmrdpt.utils.FileUtils.*;
import static org.testng.Assert.assertTrue;

@Test(description = "EPMRDPT_106896_VerifyThatAutoreplyTextCanBeDownloadedByClickingExportToWordButton",
        groups = {"manager", "full", "regression"})

public class EPMRDPT_106896_VerifyThatАutoreplyTextCanBeDownloadedByClickingExportToWordButton {
    private final String DOWNLOADS_PATH = getDownloadedFilePath();
    private final String FILENAME = "document.doc";
    private final String TRAINING_NAME = "Autotest 106896";
    private final String autoreplyFile =
            DOWNLOADS_PATH + FileSystems.getDefault().getSeparator() + FILENAME;
    private final int timeToWaitForDownload = DEFAULT_TIMEOUT_FOR_PAGE_LOAD * 2;

    @BeforeClass(inheritGroups = false, alwaysRun = true)
    public void setup() {
        deleteFile(autoreplyFile);
        new LoginService().loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
                        asTrainingManager()
                )
                .clickReactTrainingManagementLink();

        new ReactTrainingManagementService().searchTrainingByNameAndRedirectOnIt(TRAINING_NAME)
                .clickAutoreplyTabFromTrainingScreen();
    }

    @Test
    public void checkThatTrainingManagerCanDownloadAutoreplyFile() {
        new ReactAutoreplyTabScreen().clickExportToWordButton();
        waitForFileExistence(autoreplyFile, timeToWaitForDownload);
        assertTrue(isFileExists(autoreplyFile), "Download of autoreply has failed");
    }

    @AfterMethod(inheritGroups = false, alwaysRun = true)
    private void deleteDownloadedFiles() {
        deleteFile(autoreplyFile);
    }
}
