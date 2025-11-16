package dev.yamh.io.presentation.core.localisation.source.locale

import dev.yamh.io.presentation.core.localisation.source.Localisation
import dev.yamh.io.presentation.core.localisation.source.translation.AuthorizationTranslation
import dev.yamh.io.presentation.core.localisation.source.translation.GeneralTranslation
import dev.yamh.io.presentation.core.localisation.source.translation.OnboardingTranslation
import dev.yamh.io.presentation.core.localisation.source.translation.SettingsTranslation

internal val localeUk: Localisation = Localisation(
    onboarding = OnboardingTranslation(
        welcome = "Керуйте своїм будинком. З будь-якого місця.",
        page1Title = "Підключення пристроїв",
        page1Description = "Під'єднуйте розумні лампочки, датчики й розетки за секунди.",
        page2Title = "Мультиплатформна підтримка",
        page2Description = "Використовуйте на телефоні, планшеті або настільному комп'ютері. На ваш вибір.",
        page3Title = "Керування без зусиль",
        page3Description = "Увімкніть світло, встановіть температуру або закрийте жалюзі — все з одного екрана.",
        page4Title = "Усе в одному місці",
        page4Description = "Керуйте всім. Один додаток, всі кімнати, кожен пристрій. Просто."
    ),
    authorization = AuthorizationTranslation(
        title = "Потрібна авторизація",
        description = "Будь ласка, авторизуйте додаток для доступу до ваших пристроїв Google Home."
    ),
    settings = SettingsTranslation(
        biometry = "Біометрія",
        enabled = "Увімкнено",
        language = "Мова",
        theme = "Тема",
        about = "Про додаток",
        aboutDescription = "Yet Another Multiplatform Home",
        information = "Інформація",
        type = "Тип",
        customize = "Налаштування",
        selected = "Вибрано"
    ),
    general = GeneralTranslation(
        empty = "Жоден елемент не вибрано.",
        error = "Помилка",
        unknownError = "Сталася невідома помилка.",
        networkError = "Будь ласка, перевірте ваше з'єднання.",
        releaseToRefresh = "Відпустіть, щоб оновити",
        refreshing = "Оновлення...",
        pullToRefresh = "Потягніть, щоб оновити"
    )
)