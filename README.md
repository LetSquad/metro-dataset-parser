# metro-dataset-parser

Модуль metro-dataset-parser предназначен для заполнения базы данных модуля metro-back информацией о станциях и заявках метро на основе предоставленных датасетов.

### Инструкция по сборке и запуску

#### Сборка приложения

Команда для сборки:

`mvn clean install`

В случае успешной сборки приложения в рабочей директории будет создана поддиректория target с приложением, а в лог будет выведено сообщение:

`BUILD SUCCESS`

#### Запуск приложения для заполнения базы данных из датасетов

Для настройки подключения к базе данных в рабочей директории необходимо создать файл application.yml со следующим содержимым:

```yaml
spring:
  datasource:
    url: 'jdbc:postgresql://<DATABASE_HOST>/<DATABASE_NAME>'
    username: <DATABASE_USERNAME>
    password: <DATABASE_PASSWORD>
```

В базе данных должна быть создана структура таблиц в соответствии с моделью данных модуля metro-back

Команда для запуска модуля metro-dataset-parser (из директории target):

`java -jar metro-dataset-parser.jar`

В случае успешного завершения работы парсера в лог будет выведено сообщение:

`Dataset parsing finished successfully`