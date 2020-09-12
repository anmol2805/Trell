# Trell
    
* Developed over MVVM + Clean architecture for better abstraction of data layer
* Followed modular structure
* Base module for all interfaces, classes, utilities, etc which are common to multiple features of the app
* Home module for video snapping feature
* App module for app entry point and initializing Dagger graph
* Each module has three layers of code i.e. data, domain and ui layer
* Used dagger2 for dependency injection of fragments, viewModels, and repositories
* Used viewState with databinding to update changes in UI which is updated from viewModel
* Used Kotlin Coroutines for accessing data on light weight threads from data layer
