# Currency Converter Application.

Android currency converter is a project to check the currency conversion rates for different countries.

In this project you will find:
*   The project followed clean architecture so that we can scale in future,
    we can another data source modules like local or preference dbs later. Also help to improve the 
    testability of modules. Hilt is used to implement dependency injection.
*   Kotlin coroutines for background tasks
*   Hilt for Dependency injection.
*   UI layer contains activity, fragment and  view-model.
*   View-Binding to replace findViewbyId and improve null-safety and type-safety
*   Reactive UI using Live-Data observables
*   A data layer with a repository and network source implementation.
*   Unit tests
*   Remote data source is from exchangerate-api, documentation available at https://www.exchangerate-api.com/docs/overview

Improvements:
*   Based on user's preference we can decide the currency refresh rate frequency like (live, 6HR, 12HR or 24HR),
    save the rates locally and calculate converted amount without api call.
*   Replace Live-data with kotlin state-flow for UI states.
*   Improve unit tests.
*   Add instrumented and UI tests






