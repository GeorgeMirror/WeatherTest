# WeatherTest

Проект для BDD-тестирования API прогноза погоды с использованием WireMock, Cucumber, RestAssured и Allure.

## Архитектура и структура проекта

Проект построен с доминантой **единой ответственности**. Каждый пакет отвечает строго за своё назначение, что облегчает сопровождение и масштабирование.

```
src/
└── test/
    ├── java/com/yourcompany/weather/
    │   ├── client/         # Вызов внешнего API
    │   ├── config/         # Конфигурация и контекст тестов
    │   ├── dto/            # DTO-классы для JSON-ответов
    │   ├── enums/          # Перечисления статусов, ошибок и проч.
    │   ├── logging/        # Утилиты для логирования/аллюра
    │   ├── mapper/         # Маппинг таблиц и DTO
    │   ├── mock/           # Менеджеры WireMock
    │   ├── runner/         # Запуск тестов и хуки
    │   ├── steps/          # StepDefinition-классы (разделены по типам)
    │   ├── stub/           # Генерация заглушек WireMock
    │   ├── util/           # Парсеры, утилиты
    │   └── validation/     # Валидация ответов
    └── resources/
        ├── com/yourcompany/weather/runner/
        │   └── weather_api.feature  # Gherkin-сценарии
        └── config/
            └── wiremock.properties  # Конфиг порта и ключа
```

## Запуск тестов

```bash
mvn clean test
```

## Генерация Allure-отчёта

```bash
allure serve target/allure-results
```

Для этого нужна установленная утилита Allure CLI: https://docs.qameta.io/allure/

## Логирование

Логирование выполнено через **SLF4J + Logback**.

Файл конфигурации:
```
src/main/resources/logback.xml
```

Логи пишутся в два канала:
- Консоль
- Файл `logs/app.log`

Формат файла логов:
```
2025-06-23 18:41:22 INFO  com.yourcompany.weather.validation.WeatherValidator - Температура отличается: ожидается 20.0, получено 19.7
```

## Особенности реализации

- Используется `WireMock` на динамическом порте, сервер управляется через `WireMockManager`
- Запросы и ответы строго валидируются (в позитивных и негативных сценариях)
- Все шаги разделены по ответственности: `BaseSteps`, `CommonSteps`, `WeatherPositiveSteps`, `WeatherNegativeSteps`
- Маппинг таблиц через `WeatherMapper` и `TableMapper`
- DTO покрыты `lombok`
- Поддержка `Allure` для шагов и JSON-ответов

## Зависимости

- Java 17
- Maven
- Cucumber 7
- JUnit 5
- WireMock
- RestAssured
- Allure
- SLF4J + Logback
- Jackson