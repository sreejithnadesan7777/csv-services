package service;

import org.apache.logging.log4j.core.impl.Log4jContextFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import starter.Service.FileListServiceImpl;
import starter.dto.CsvValidData;

import java.util.List;

import static org.powermock.api.mockito.PowerMockito.doCallRealMethod;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.mock;

@PowerMockIgnore({"javax.management.*"})
@ContextConfiguration(classes = {CsvTestContextConfiguration.class})
@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
@PrepareForTest({LoggerFactory.class, Log4jContextFactory.class})
@ActiveProfiles("FileListServiceImplTest")

public class FileListServiceImplTest {

    @Autowired
    private FileListServiceImpl fileListServiceImpl;

    @Test
    public void executeTest(){
        String filename = mock(String.class);
        doCallRealMethod().when(fileListServiceImpl).execute(filename);
        doReturn(MockData.getExistingDataMock()).when(fileListServiceImpl).execute(filename);
        List<CsvValidData> dataList = fileListServiceImpl.execute(filename);
        Assert.assertTrue(dataList.size()>0);
    }

    @Test
    public void executeNonExistingFileTest(){
        String filename = mock(String.class);
        doCallRealMethod().when(fileListServiceImpl).execute(filename);
        doReturn(MockData.getNewDataMock()).when(fileListServiceImpl).execute(filename);
        List<CsvValidData> dataList = fileListServiceImpl.execute(filename);
        Assert.assertTrue(dataList.size()==0);
    }
}
