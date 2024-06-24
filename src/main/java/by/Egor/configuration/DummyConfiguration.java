package by.Egor.configuration;

import by.Egor.DummyController;
import by.Egor.DummyManager;
import by.Egor.DummyService;
import by.Egor.DummyServiceImpl;
import by.Egor.generators.GetResponseGenerator;
import by.Egor.generators.PostResponseGenerator;

@Configuration
public class DummyConfiguration {
     @Instance(1)
     public DummyManager dummyManager(DummyService dummyService){
          return new DummyManager(dummyService);
     }
     @Instance(0)
     public DummyService dummyService(){
          return new DummyServiceImpl();
     }
     @Instance(2)
     public DummyController dummyController(DummyManager dummyManager){
          return new DummyController(dummyManager);
     }
     @Instance(3)
     public GetResponseGenerator getResponseGenerator(DummyController dummyController){
          return new GetResponseGenerator(dummyController);
     }
     @Instance(4)
     public PostResponseGenerator PostResponseGenerator(DummyController dummyController){
          return new PostResponseGenerator(dummyController);
     }
}
