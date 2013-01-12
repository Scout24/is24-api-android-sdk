This project is an **Android Development Kit** for accessing the [ImmobilienScout24 REST API](http://developerwiki.immobilienscout24.de/wiki/ImmobilienScout24_API).

The purpose of this project is to enable Android developers to easily use the ImmobilienScout24 REST API **without having to care about the specifics of OAuth or how to structure requests against the API**.

The SDK is an open source project driven by ImmobilienScout24 and the community.

------------------------- 
#The SDK Library

The base of the SDK project is the SDK library itself.

The Android project is implemented as an Android Library Project (apklib). This provides the advantage of referencing the library in several projects whithin your workspace.

Unlike a normal jar package, a library project also **packages it's resources** into the application which is using it. This simplifies the usage of the attributes of the domain objects in your own application.

For more information on apklibs [search Google](https://www.google.de/search?q=apklib).

###Features:
* Two Legged OAuth implementation
* Search Service for RealEstate search requests
* Search Response translation into domain object
* Expose Service for specific RealEstate requests
* Expose Response translation into domain object

##Getting started

Before you can use any of the services the SDK provides you'll have to initialize some basic components in your code.

First you'll have to provide the `ApplicationContext` to the `LibraryContext` class so that the library is able to make resource lookups for texts and urls like this:

`LibraryContext.getInstance().initApplicationContext(getApplicationContext());`

The next thing you'll have to do is to initialize the `HttpRequestExecuter` with your **consumer-key and your consumer-secret** which you received when you [registered for using the ImmobilienScout24 REST API](http://developer.immobilienscout24.de/rest-api/rest-api-zugang). It is responsible for signing the requests which are directed to the API with your OAuth credentials. The `init2LeggedOauth` method also takes a flag specifying whether the requests should be fired *against the sandbox or the live system*.
 
`HttpRequestExecuter.getInstance().init2LeggedOauth(consumer-key, consumer-secret, isLiveUrl);`
      
After you initialized both components are ready to go.

`ExposeService.getInstance().getExpose(realEstateId);`

**The services are implemented as singletons and should always be called asynchronously.**

##The SDK Reference Implementation

To give an example of how to use the SDK, a simple reference application is provided. The reference application utilizes the SDK to submit a defined RealEstate search. The search results are displayed in a simple listview. When the user clicks on one of the results in that list an expose request is done for that specific expose. The returned result expose is displayed in another activity showing some of its attributes.

##Testing

To verify that the SDK is working and that changes didn't break the functionality a SDK test project is provided. This test project can't test the SDK library directly as no APK is generated from it. It tests SDK classes through the reference project.  

---
##The ImmobilienScout24 Android SDK is Free Software and Open Source.

The ImmobilienScout24 Android SDK is [licensed under the LGPL v2.1](http://www.gnu.org/licenses/old-licenses/lgpl-2.1.html).

This license is flexible enough to allow the use of the ImmobilienScout24 Android SDK in both open source and commercial projects.

If you modify the ImmobilienScout24 Android SDK itself and redistribute your modifications, the LGPL applies.

##Contribution

Pull requests are always welcome.

In order to create a successful pull request make sure to follow these steps:

1. Make sure not to break any tests.
2. Provide small commits.
3. Write understandable commit messages.