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
import org.springframework.web.multipart.MultipartFile;
import starter.Service.CsvExtractServiceImpl;
import starter.dataaccess.CsvCurrencyExchangeCountRepository;
import starter.dvo.Response;

import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyString;
import static org.powermock.api.mockito.PowerMockito.doCallRealMethod;
import static org.powermock.api.mockito.PowerMockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.mock;

import static starter.common.CsvServiceContstants.SUCCESS_DESC;
import static starter.common.CsvServiceContstants.EXISTS_DESC;

@PowerMockIgnore({"javax.management.*"})
@ContextConfiguration(classes = {CsvTestContextConfiguration.class})
@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
@PrepareForTest({LoggerFactory.class, Log4jContextFactory.class})
@ActiveProfiles("CsvExtractServiceImplTest")
public class CsvExtractServiceImplTest {

    @Autowired
    private starter.dataaccess.CsvInvalidDataRepository csvInvalidDataRepository;

    @Autowired
    private starter.dataaccess.CsvValidDataRepository csvValidDataRepository;

    @Autowired
    private CsvCurrencyExchangeCountRepository csvCurrencyExchangeCountRepository;

    @Autowired
    private CsvExtractServiceImpl csvExtractServiceImpl;

   @Test
    public void executeTestAlreadyExitingFile() throws  Exception{
        MultipartFile file = mock(MultipartFile.class);
        doNothing().when(csvExtractServiceImpl).fileUpload(file);
        doReturn(MockData.getExistingDataMock()).when(csvValidDataRepository).findByCsvOrderFilename(anyString());
        doCallRealMethod().when(csvExtractServiceImpl).execute(file);
        String filename = mock(String.class);
        doReturn("SampleCSVFile_.csv").when(file).getOriginalFilename();
        Object response = csvExtractServiceImpl.execute(file);
        Assert.assertTrue(response instanceof Response);
        Response res = (Response) response;
        Assert.assertEquals(res.getDescription(), EXISTS_DESC);

    }

    @Test
    public void executeTestNewFile() throws  Exception{
        MultipartFile file = mock(MultipartFile.class);
        String filename = mock(String.class);
        doNothing().when(csvExtractServiceImpl).fileUpload(file);
        doReturn(MockData.getNewDataMock()).when(csvValidDataRepository).findByCsvOrderFilename(anyString());
        doCallRealMethod().when(csvExtractServiceImpl).execute(file);
        doReturn("SampleCSVFile_.csv").when(file).getOriginalFilename();
        Object response = csvExtractServiceImpl.execute(file);
        Assert.assertTrue(response instanceof Response);
        Response res = (Response) response;
        Assert.assertEquals(res.getDescription(), SUCCESS_DESC);
    }
}
