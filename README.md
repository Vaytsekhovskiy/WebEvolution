# Эволюция разработки веб приложений
В ходе некоторого курса, я смотрел, каким образом разрабатываются веб приложения на языке java. 
В этом репозитории я постарался в полной мере отразить эволюцию разработки веб приложений. 
Предполагается, что каждый новый коммит будет все более высокой абстракцией по сравнению с предыдущим. 
Таким образом, я смогу продемонстрировать историю разработки, начиная с написания сервлетов, заканчивая подключением фреймворка Spring
## 1 коммит (first evol)
В первом коммите я разработал простейший Dummy сервлет, который может принимать get и post запросы по адресу /help-service/v1/support (см. web.xml).
Также здесь я написал простые тесты при помощи mockito, в которых я проверил работоспособность отправки get, post.
После собрал всё в один docker контейнер
## 2 коммит (Application context)
Дальше я добавил несколько слоёв так, как это примерно делают в современных приложениях (DummyManager + DummyServiceImpl). Также был создан Application Context при помощи рефлексии, теперь для получения нового Instance нужно обращаться туда
## 3 коммит (add Dispatcher servlet)
Добавил Dispatcher servlet. Теперь он способен управлять всеми операциями, внутри данный сервлет был реализован по принципам Solid. 
