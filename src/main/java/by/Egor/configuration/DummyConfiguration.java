package by.Egor.configuration;

import by.Egor.DummyManager;
import by.Egor.DummyService;
import by.Egor.DummyServiceImpl;

@Configuration
public class DummyConfiguration {
     @Instance
     public DummyManager dummyManager(){
          return new DummyManager(dummyService());
     }
     @Instance
     public DummyService dummyService(){
          return new DummyServiceImpl();
     }
}
