module by.egor.inmemorybroker {
    requires static lombok;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    opens by.Egor.WebEvol to spring.core;
    exports by.egor.inmemorybroker;
}