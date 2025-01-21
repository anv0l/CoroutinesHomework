# Задание Coroutines

В этом репозитории находится задание по теме Coroutines.
Каждое из заданий выполняется в отдельном фрагменте главного экрана.

Задание нужно сдавать в виде нескольких атомарных коммитов по каждой задаче.
Например:

1. Задание 1.1 - Таймер с использованием coroutines
2. Задание 1.2 - Перевод таймера на Kotlin Flow
3. Задание 2.1 - Login с использованием coroutines
4. Задание 2.2 - Login с использованием Kotlin Flow
5. Задание 3 - Speed-test

## Задание 1.1 - Таймер

![Таймер](/readme/timer.png)

В этом задании вам нужно сделать таймер при помощи `coroutines`.
В коде [TimerFragment.kt](app/src/main/kotlin/ru/otus/coroutineshomework/ui/timer/TimerFragment.kt) есть функции `startTimer` и `stopTimer` для 
запуска и останова таймера.
Напишите код корутины, которая будет увеличивать счетчик раз в несколько миллисекунд (по вашему выбору) и обновлять значение переменной `time`.
Используйте scope, привязанный ко view фрагмента.

## Задание 1.2 - Перевод таймера на Kotlin Flow

В этом задании вам нужно переписать пример с использованием Kotlin Flow.

- Измените переменную `time` на `timeFlow` типа `MutableStateFlow<Duration>`.
- Создавайте `timeFlow` в `onViewCreated` с учетом изначального значения в `bundle`.
- Подпишитесь на `timeFlow` в `onViewCreated` и обновляйте значение текстового поля `time`.
- Используйте `repeatOnLifecycle` для отслеживания таймера.
- В корутине таймера используйте `emit` для обновления значения `timeFlow`.

## Задание 2.1 - Login

![Login](/readme/login.png)

В этом задании вам нужно сделать форму логина при помощи `coroutines`.
Состояние [LoginFragment.kt](app/src/main/kotlin/ru/otus/coroutineshomework/ui/login/LoginFragment.kt) определяется в [LoginViewModel.kt](app/src/main/kotlin/ru/otus/coroutineshomework/ui/login/LoginViewModel.kt) 
при помощи свойства `state` и может принимать одно из следующих значений [LoginViewState](app/src/main/kotlin/ru/otus/coroutineshomework/ui/login/LoginViewState.kt):

- `LoginViewState.Login` - форма входа. Опционально содержит ошибку входа
- `LoginViewState.LoggingIn` - процесс входа
- `LoginViewState.Content` - контент после входа
- `LoginViewState.LoggingOut` - процесс выхода

Реализуйте функции `login` и `logout` в коде [LoginViewModel.kt](app/src/main/kotlin/ru/otus/coroutineshomework/ui/login/LoginViewModel.kt).
Требования:

- Используйте экземпляр [LoginApi](app/src/main/kotlin/ru/otus/coroutineshomework/ui/login/LoginApi.kt) для запуска операций входа и выхода.
- Обратите внимание, что если запустить сетевую операцию на основном потоке, то приложение выдаст ошибку.
- Используйте scope, привязанный к viewModel.
- При входе и выходе показывайте индикатор загрузки, переключая состояние на `LoggingIn` и `LoggingOut` соответственно.
- При успешном входе переключайте состояние на `Content`.
- При ошибке входа переключайте состояние на `Login` и передавайте ошибку в состоянии.

## Задание 2.2 - Login с использованием Kotlin Flow

В этом задании вам нужно переписать пример с использованием Kotlin Flow.

- Измените тип свойства `state` на `StateFlow`.
- Создайте внутреннее поле `stateFlow` типа `MutableStateFlow<LoginViewState>` и экспортируйте его как `state`.
- Напишите внутреннюю функцию `loginFlow`, которая будет запускать сетевую операцию и возвращать `Flow<LoginViewState>`.
- Используйте билдер `flow` для создания холодного потока изменения состояния.
- По мере выполнения операции, посылайте новое состояние при помощи функции `emit`.
- Подпишитесь на `loginFlow` в функции `login` и обновляйте значение `stateFlow` внутри `collect`.

## Задание 3. Speed-test

![Speed-test](/readme/speedtest.png)

В этом задании вам нужно сделать эмулятор speed-test при помощи `coroutines`.
Мы будем тренироваться запускать несколько корутин одновременно и усреднять их время выполнения.
В коде [NetworkViewModel.kt](app/src/main/kotlin/ru/otus/coroutineshomework/ui/network/NetworkViewModel.kt) определена функция `emulateBlockingNetworkRequest`, 
которая эмулирует сетевой запрос. Функция возвращает время выполнения запроса в миллисекундах или ошибку.
Вам нужно реализовать метод модели `startTest`, который запускает несколько запросов одновременно и усредняет время выполнения.

- Используйте scope, привязанный к viewModel.
- Запускайте несколько параллельных корутин.
- Отфильтруйте удачные результаты.
- Усредните время выполнения запросов.
- Результат поместите в переменную `_result`.
- На время выполнения операции, показывайте индикатор загрузки, устанавливая значение `_running` в `true`.