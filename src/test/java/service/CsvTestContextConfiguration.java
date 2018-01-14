package service;

import org.powermock.core.classloader.annotations.PrepareForTest;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import starter.Service.CsvExtractService;
import starter.Service.CsvExtractServiceImpl;
import starter.Service.FileListServiceImpl;
import starter.Service.LoadFileServiceImpl;
import starter.dataaccess.CsvCurrencyExchangeCountRepository;
import starter.dataaccess.CsvInvalidDataRepository;
import starter.dataaccess.CsvValidDataRepository;

import static org.mockito.Mockito.mock;


@Configuration
public class CsvTestContextConfiguration {
    @Bean
    public  starter.dataaccess.CsvInvalidDataRepository csvInvalidDataRepository() {
        return mock(CsvInvalidDataRepository.class);
    }

    @Bean
    public starter.dataaccess.CsvValidDataRepository csvValidDataRepository() {
        return mock(CsvValidDataRepository.class);
    }

    @Bean
    public CsvCurrencyExchangeCountRepository csvCurrencyExchangeCountRepository() {
        return mock(CsvCurrencyExchangeCountRepository.class);
    }

    @Bean
    public CsvExtractServiceImpl csvExtractServiceImpl() {
        return mock(CsvExtractServiceImpl.class);
    }

    @Bean
    public FileListServiceImpl fileListServiceImpl() { return mock(FileListServiceImpl.class); }

    @Bean
    public LoadFileServiceImpl loadFileServiceImpl() { return mock(LoadFileServiceImpl.class); }

    @Bean
    public LoggerFactory loggerFactory(){ return mock(LoggerFactory.class);}
}
