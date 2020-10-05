package com.agibank.challenge.processor;

import com.agibank.challenge.configuration.StartProcess;
import com.agibank.challenge.processor.impl.ChainProcessor;
import com.agibank.challenge.util.enums.Path;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.agibank.challenge.util.enums.Path.INPUT;

@ExtendWith(MockitoExtension.class)
public class ChainProcessorTest {

    private final String SUCCESS_FILE_TO_PROCESS = "success.dat";
    private final String TXT_FILE_TO_PROCESS = "wrong_extension.txt";
    private final String INCONSISTENT_FILE_TO_PROCESS = "wrong_salesmen.dat";
    private final String WRONG_FORMAT_FILE_TO_PROCESS = "wrong_format.dat";

    ChainProcessor chainProcessor = new ChainProcessor();

    @BeforeEach
    public void createResources(){
        StartProcess.createResources();
    }

    @AfterEach
    public void cleanResources(){
        Arrays.stream(Path.values()).forEach(source -> {
                    Arrays.stream(Objects.requireNonNull(new File(source.path).listFiles()))
                            .filter(file -> !file.isDirectory())
                            .forEach(File::delete);
                }
        );
    }

    @Test
    public void should_execute_chain_with_success() throws IOException {
        moveFileToTest(SUCCESS_FILE_TO_PROCESS);
        chainProcessor.runProcess().moveForward(List.of());
        Assertions.assertFalse(checkProcessedFile(SUCCESS_FILE_TO_PROCESS, Boolean.FALSE));
    }

    @Test
    public void should_execute_chain_with_failure_inconsistent_data() throws IOException, InterruptedException {
        moveFileToTest(INCONSISTENT_FILE_TO_PROCESS);
        chainProcessor.runProcess().moveForward(List.of());
        Assertions.assertTrue(checkProcessedFile(INCONSISTENT_FILE_TO_PROCESS, Boolean.FALSE));
    }

    @Test
    public void should_execute_chain_with_failure_invalid_format() throws IOException{
        moveFileToTest(WRONG_FORMAT_FILE_TO_PROCESS);
        chainProcessor.runProcess().moveForward(List.of());
        Assertions.assertTrue(checkProcessedFile(WRONG_FORMAT_FILE_TO_PROCESS, Boolean.FALSE));
    }

    private void moveFileToTest(String fileName) throws IOException{
        File original = new File(
                "src/test/resources/".concat(fileName));
        File copied = new File(Path.INPUT.path.concat(fileName));
        FileUtils.copyFile(original, copied);
    }

    private Boolean checkProcessedFile(String fileName, Boolean expectedResult)  {
        String outOutPath;
        if(expectedResult){
            outOutPath = Path.OUTPUT.path.concat(fileName).replace(".dat", ".done.dat");
        } else {
            outOutPath = Path.FAILURE_BACKUP.path.concat(fileName);
        }
        File file = new File(outOutPath);
        boolean result = file.canRead();
        return result;
    }

}
