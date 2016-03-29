#Coding Guide
=============
##Android Studio Settings
Let's prepare our Android Studio first.<br>
###Just editing
To make the team work easier, we make a few standards for editing.<br>
####Copyrights
Follow instructions listed below:<br>
        File->Settings...->Editor->Copyright->Copyright Profiles<br>
You can also use `Ctrl +　Alt + S` or press setting button on the toolbar,Your copyright may look like this:<br>
```Java
Copyright (c) $today.year. Team Yohe from Southeast University
All rights reserved.
```
After that, click back to Copyright and set a scope for your copyright, `'All'` is preferred.<br>
####File Header
Follow instructions listed below:<br>
       File->Settings...->Editor->File and Code Templates->File Header<br>
Here's the file header template:<br>
```Java
/**
 * Author: your_name on ${DATE} ${HOUR}:${MINUTE}
 * E-mail: your_email_address
 */
```
####Comments
* Follow javadoc standards.<br>
* Comment your incomplete code with
```Java
//TODO：describe briefly about the work in the future.<br>
```
After that, you will be able to navigate to your code in Android Studio.<br>
####More about coding
Here's still some other basic rules we might want to obey, we'll discuss that later.<br>

------------------------------------------------------------------------------
##Clean Android Architecture
See article [Architecting Android…The clean way](http://fernandocejas.com/2014/09/03/architecting-android-the-clean-way/) for details.<br>
The objective is the separation of concerns by keeping the business rules not knowing anything at all about the outside world,
thus, they can can be tested without any dependency to any external element.<br>
It is worth mentioning that each layer uses its own data model so this independence can be reached
(you will see in code that a data mapper is needed in order to accomplish data transformation,
a price to be paid if you do not want to cross the use of your models over the entire application).<br>
Here is an schema so you can see how it looks like:<br>
![3layer schema](http://fernandocejas.com/wp-content/uploads/2014/09/clean_architecture_android.png)
###Presentaton Layer
Is here, where the logic related with views and animations happens.
Here fragments and activities are only views, there is no logic inside them other than UI logic,
and this is where all the rendering stuff takes place.<br>
Presenters in this layer are composed with interactors (use cases) that perform the job in a new thread
outside the android UI thread, and come back using a callback with the data that will be rendered in the view.<br>
Then we can discuss how to code and where to put them according to packages.
Root name of the package in this layer may look like this: `app_package.presentation`<br>
####exception package
Typically, we have a class named ErrorMessageFactory.
It turns an Exception into a String message, which is easier to display<br>
####internal.di package
The place we use [dagger2](http://google.github.io/dagger/) to do Dependency Inject stuff.<br>
Module is a wrapper for all @Provides-annotated methods.
Component is an interface for using @Inject to get an instance.<br>
####model package
Classes in mapper package simply map <Some>Data into <Some>ModelData.
<Some>Data is passed by the domain layer.<Some>ModelData is ui related data.<br>
Define all the ui related data here with a suf-fix like 'ModelData'.<br>
####navigation package
Define a Navigator Class, then we can navigate through the application.
Add a pre-fix like 'navigateTo' before every method.
Also remember to add @Inject-annotate to the construct method.<br>
####presenter package
Presenter is an interface for all presenters.
This is the logic layer for the ui, which mean we can only do some ui related operations,
like check the user id or the password.Business logic will be passed to domain layer.
We should avoid running to much code in the ui thread.<br>
A presenter should have a view reference to interact with the View.
A presenter should also register all use cases it might need,
along with the corresponding <Some>ModelDataWrapper.<br>
In the destroy method, remember to unsubscribe all use cases.
Also pay attention to the code listed below:
```Java
@Inject
  public UserListPresenter(@Named("userList") UseCase getUserListUserCase,
      UserModelDataMapper userModelDataMapper) {
    this.getUserListUseCase = getUserListUserCase;
    this.userModelDataMapper = userModelDataMapper;
  }
```
Use @Inject to annotate the constructor that Dagger should use to create instances of a class.
When a new instance is requested, Dagger will obtain the required parameters values and invoke this constructor.
The @Named("userList") is a qualifier for UseCase, because UseCase is the super class of GetUserList.
We might need some qualifier to distinguish between different UseCases.<br>
We interact with the domain layer with interactors.
Hereby, we trigger execute method of the UseCase, and then specify a subscriber.
More to be discussed about the lifecycle and Presenter interface.<br>
####view package
We put all the activities in the activity package, all the fragments in the fragment package
and all the adapter in the adapter package.Components are those view components defined by our selves.<br>
View interface is critical for presenters to work functionally.
It should provide all view related operations, like showLoading(), showError() etc.<br>
More to mention about activity is the IckPick tool.Add @State-annotate to the state you want to store.<br>
###Domain Layer
We provide interactors for presentation layer and fetch data using repository interface from data layer.
All the business logic will be handled here.<br>
Then we can discuss how to code and where to put them according to packages.<br>
####exception package
Here we have a ErrorBundle interface and a default implementation named DefaultErrorBundle.
It bundles all the exceptions from data layer.<br>
####executor package
`Wait to be understood.No idea about this.<br>`
####interactor package
UseCase is an abstract class with an abstract method called buildUseCaseObservable().
We pass request-related data to the construct method of the UseCase,
then UseCase build an Observable object to start a asynchronous task.
The unsubscribe method will abandon the subscription, then
Observable instance will stop emitting and Subscriber instance will stop receiving.<br>
####repository package
This is an interface for data layer.We only discuss data repository at the aspect of domain layer,
which means we won't know anything about how the data is fetched afterwards.
This is just an agreement to the data layer, nothing more.<br>
#####Some Confusions
Let's compare repository with interactor.Domain layer implement interactor and define the interface for presentation layer.
Domain also define an interface for data layer, but data layer have to implement it.
It is easy to understand where we can implement the interfaces,
but why do domain layer define both interfaces for presentation layer and data layer?<br>
Another problem is we call ui related data <Some>ModelData and put them in the model package,
we also call pure data in the data layer <Some>Entity and put them in the entity package,
then what about the data in the domain layer?
In this example, they just put them in the domain package.<br>
###Data Layer
We can fetch data from ram, files or cloud here and provide an interface for the domain layer
when it request some kind of data.`That's for you to find out...`<br>

-----------------------------------------------------------------------
##Open Source tools
See [android open project](https://github.com/Trinea/android-open-project), dig anything you want.<br>
Article and user guides like [dagger2](http://google.github.io/dagger/users-guide.html),
[Grokking RxJava, Part 1: The Basics](http://blog.danlew.net/2014/09/15/grokking-rxjava-part-1/),
[NotRxJava guide for lazy folks](http://yarikx.github.io/NotRxJava/),
[Retrofit 2.0 Official](http://square.github.io/retrofit/),
[Retrofit 2.0：有史以来最大的改进](http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0915/3460.html).<br>
More's coming.<br>