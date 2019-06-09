/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.example.android.marsrealestate.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://mars.udacity.com/"

// COMPLETED (02) Use Retrofit Builder with ScalarsConverterFactory and BASE_URL
private val retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

// COMPLETED (03) Implement the MarsApiService interface with @GET getProperties returning a String
/*
 * Defines an interface that explains how Retrofit talks to our webserver, using HTTP GET requests.
 * Retrofit will build an object implementing this interface with all of the methods that talk to
 * the server, conceptually much like Room implements our DAO interfaces to build ORM objects to
 * talk to our SQLite database.
 */
interface MarsApiService {
    @GET("realestate") // the endpoint for the JSON response this method is gonna use
    fun getProperties():
            Call<String> // The returned call object is used to start the request
}

// COMPLETED (04) Create the MarsApi object using Retrofit to implement the MarsApiService
/*
* To create a Retrofit service, we call retrofit.create(...), passing in the service interface we
* just defined.
* Since this retrofit.create(...) call is expensive, and our app just needs one retrofit service
* instance, we'll expose our Retrofit service to the rest of the application using a public object
* called `MarsApi`.
* Calling `MarsApi.retrofitService` will thus return a Retrofit object that implements MarsApiService.
*/
object MarsAPI {
    val retrofitService: MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }
}