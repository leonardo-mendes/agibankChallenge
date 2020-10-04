package com.agibank.challenge.processor;

import com.agibank.challenge.processor.impl.ChainProcessor;
import com.agibank.challenge.util.enums.Path;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ChainProcessorTest {

    private final String SUCCESS_FILE_TO_PROCESS = "success.dat";
    private final String TXT_FILE_TO_PROCESS = "wrong_extension.txt";
    private final String INCONSISTENT_FILE_TO_PROCESS = "wrong_salesmen.dat";
    private final String WRONG_FORMAT_FILE_TO_PROCESS = "wrong_format.dat";

    ChainProcessor chainProcessor = new ChainProcessor();

    @Test
    public void should_execute_chain_with_success() throws IOException, InterruptedException {
        moveFileToTest(SUCCESS_FILE_TO_PROCESS);
        chainProcessor.runProcess().moveForward(List.of());
    }

    @Test
    public void should_execute_chain_with_failure_wrong_extension() throws IOException, InterruptedException {
        moveFileToTest(TXT_FILE_TO_PROCESS);
        chainProcessor.runProcess().moveForward(List.of());
        //Assertions.assertFalse(checkProcessedFile(TXT_FILE_TO_PROCESS, Boolean.FALSE));
    }

    @Test
    public void should_execute_chain_with_failure_inconsistent_data() throws IOException, InterruptedException {
        moveFileToTest(INCONSISTENT_FILE_TO_PROCESS);
        chainProcessor.runProcess().moveForward(List.of());
        //Assertions.assertFalse(checkProcessedFile(INCONSISTENT_FILE_TO_PROCESS, Boolean.FALSE));
    }

    @Test
    public void should_execute_chain_with_failure_invalid_format() throws IOException, InterruptedException {
        moveFileToTest(WRONG_FORMAT_FILE_TO_PROCESS);
        chainProcessor.runProcess().moveForward(List.of());
        //Assertions.assertFalse(checkProcessedFile(WRONG_FORMAT_FILE_TO_PROCESS, Boolean.FALSE));
    }

    private void moveFileToTest(String fileName) throws IOException, InterruptedException {
        File original = new File(
                "src/test/resources/".concat(fileName));
        File copied = new File(Path.INPUT.path.concat(fileName));
        FileUtils.copyFile(original, copied);
        Thread.sleep(500);
    }

    private Boolean checkProcessedFile(String fileName, Boolean expectedResult) throws InterruptedException {
        Thread.sleep(500);
        String outOutPath;
        if(expectedResult){
            outOutPath = Path.OUTPUT.path.concat(fileName).replace(".dat", ".done.dat");
        } else {
            outOutPath = Path.FAILURE_BACKUP.path.concat(fileName);
        }
        File file = new File(outOutPath);
        boolean result = file.canRead();
/*        file.delete();
        deleteFile(fileName);*/
        return result;
    }

    private void deleteFile(String fileName) throws InterruptedException {
        Thread.sleep(500);
        new File(Path.OUTPUT.path.concat(fileName).replace(".dat", ".done.dat"));
        new File(Path.BACKUP.path.concat(fileName)).delete();
        new File(Path.FAILURE_BACKUP.path.concat(fileName)).delete();
    }

}