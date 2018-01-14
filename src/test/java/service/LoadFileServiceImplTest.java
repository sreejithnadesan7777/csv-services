package service;

import org.apache.logging.log4j.core.impl.Log4jContextFactory;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.multipart.MultipartFile;
import starter.Service.CsvExtractServiceImpl;
import starter.Service.LoadFileServiceImpl;
import starter.dataaccess.CsvCurrencyExchangeCountRepository;
import starter.dto.CsvValidData;
import starter.dvo.Response;

import java.util.List;

import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyString;
import static org.powermock.api.mockito.PowerMockito.doCallRealMethod;
import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.mock;

@PowerMockIgnore({"javax.management.*"})
@ContextConfiguration(classes = {CsvTestContextConfiguration.class})
@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
@PrepareForTest({LoggerFactory.class, Log4jContextFactory.class})
@ActiveProfiles("LoadFileServiceImplTest")

public class LoadFileServiceImplTest {

    @Autowired
    private LoadFileServiceImpl loadFileServiceImpl;

    @Test
    public void loadFileExecuteTest(){
        doCallRealMethod().when(loadFileServiceImpl).execute();
        doReturn(MockData.getfileNames()).when(loadFileServiceImpl).execute();
        List<String> fileList = loadFileServiceImpl.execute();
        Assert.assertTrue(fileList.size()>0);
    }

    @Test
    public void loadEmptyExecuteTest(){
        doCallRealMethod().when(loadFileServiceImpl).execute();
        doReturn(MockData.getEmptyfileNames()).when(loadFileServiceImpl).execute();
        List<String> fileList = loadFileServiceImpl.execute();
        Assert.assertTrue(fileList.size()==0);
    }

}
