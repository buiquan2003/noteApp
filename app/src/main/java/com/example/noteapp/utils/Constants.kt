package com.example.noteapp.utils

object Constants {
    const val BASE_URL = "https://run.mocky.io"
    const val HOTELS_URL = "https://run.mocky.io/v3/2788d71c-c883-48e6-bf45-4cc977f89d3d"
    const val FLIGHTS_URL = "https://run.mocky.io/v3/e12d31ef-8fde-48e0-b63a-c92934c2aba6"
    const val CITY_VN_URL = "https://aa80ebf3-54ba-47f4-b7f3-26af4bc17206.mock.pstmn.io/getCityVietNam"
    const val SEARCH_HOTEL_URL = "https://aaaa5f39-9088-4f90-ae73-d2053d6fff4a.mock.pstmn.io/hotels"

    object NotificationId {
        const val BASE_NOTIFICATION_ID = 0
        const val CHANNEL_NAME = "Travel channel"
        const val CHANNEL_ID = "channel_id"
        const val NOTIFICATION_REQUEST_CODE = 1
        const val FCM_NOTIFICATION_ID = 1
        //
        const val TRIP_PLAN_NOTIFICATION = "trip_plan_notification"
    }

    object NotificationRepeatTime {
        const val TRIP_PLAN_REPEAT_TIME = 1L
    }
}