# Weather Station

Android-приложение для просмотра измерений локальной погодной станции. Приложение подключается к серверу погодной станции по указанным host/port, получает список устройств и отображает текущие значения сенсоров вместе с дневными расчетами.

## Возможности

- настройка адреса сервера погодной станции внутри приложения;
- сохранение host и port через Android DataStore;
- загрузка списка устройств с сервера;
- группировка устройств по location;
- отображение измерений в Jetpack Compose UI;
- карточки измерений с текущим значением, min/average/max за день и временем min/max;
- pull-to-refresh для ручного обновления данных;
- горизонтальный pager для переключения между группами измерений;
- логирование через Timber.

## Стек

- Kotlin 2.2
- Android Gradle Plugin 8.13
- Jetpack Compose
- Material 3
- Hilt
- AndroidX DataStore Preferences
- Kotlin Coroutines / Flow
- Timber
- Joda-Time Android
- `weather-station-client` через JitPack

## Требования

- Android Studio с поддержкой AGP 8.13+
- JDK 21
- Android SDK:
  - compileSdk 36
  - minSdk 26
  - targetSdk 36

## Быстрый Старт

Откройте проект в Android Studio:

```text
/home/artem/Projects/AndroidStudioProjects/WeatherStation
```

Дождитесь Gradle Sync и запустите конфигурацию `app` на эмуляторе или устройстве.

Сборка debug APK из терминала:

```bash
./gradlew assembleDebug
```

Установка debug APK на подключенное устройство:

```bash
./gradlew installDebug
```

## Настройка Сервера

1. Запустите backend погодной станции.
2. Откройте приложение.
3. Нажмите кнопку настроек в верхней панели.
4. Укажите:
   - `server host` - hostname или IP адрес сервера;
   - `server port` - порт сервера.
5. Закройте панель настроек или свайпните ее влево.

Приложение формирует base URL в виде:

```text
http://<host>:<port>
```

Если port пустой, используется:

```text
http://<host>
```

Для локального backend с Android Emulator обычно нужен адрес хоста:

```text
10.0.2.2
```

## Архитектура

Проект состоит из одного Android-модуля:

```text
app/
```

Основные слои:

- `ui` - Compose экраны, drawer настроек и карточки измерений;
- `MainViewModel` - состояние экрана, загрузка устройств и измерений;
- `domain` - use case для группировки устройств, загрузки измерений и форматирования времени;
- `data` - сохранение пользовательских настроек через DataStore;
- `di` - Hilt-модули для репозиториев, storage, dispatcher, factory и UI-конвертеров;
- `storage` - in-memory состояние текущей сессии;
- `ui/converters` - преобразование domain-моделей в view state.

## Основной Поток Данных

1. Пользователь задает `host` и `port` в панели настроек.
2. Значения сохраняются в DataStore с debounce.
3. `MainViewModel` наблюдает изменения host/port и создает `WeatherStationRepository`.
4. Репозиторий загружает список устройств.
5. Устройства группируются по location.
6. Для выбранной группы загружаются измерения.
7. Измерения конвертируются в `MeasureViewState` и отображаются в Compose UI.

## Структура

```text
app/src/main/kotlin/com/artembotnev/weatherstation/
├── App.kt
├── MainViewModel.kt
├── MainScreenState.kt
├── MainScreenEvent.kt
├── data/
├── di/
├── domain/
├── storage/
└── ui/
```

## Полезные Команды

Синхронизация и проверка сборки:

```bash
./gradlew build
```

Debug сборка:

```bash
./gradlew assembleDebug
```

Release сборка:

```bash
./gradlew assembleRelease
```

Очистка build artifacts:

```bash
./gradlew clean
```

## Зависимость От API

Приложение использует библиотеку:

```text
com.github.ArtemBotnev:weather-station-client:1.0.0
```

Клиент создается через `WeatherStationRepositoryFactory` и ожидает HTTP endpoint погодной станции. Формат API и domain-модели приходят из этой библиотеки.

## Заметки По Разработке

- `INTERNET` permission уже добавлен в `AndroidManifest.xml`.
- Для HTTP-подключений используется `network_security_config`.
- Сейчас выбирается первая группа устройств из map locations. В `MainViewModel` это отмечено TODO и может быть расширено выбором location в UI.
- UI построен на Material 3 и Compose.

