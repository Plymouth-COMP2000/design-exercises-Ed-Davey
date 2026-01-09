The Cracker - Restaurant management app for COMP2000 CW2 coursework

This app integrates a RESTful API for user authentication, a local SQLite database for menu items, reservations, and notifications, and role-based access controls.
The most up-to-date version of the project is located at:
tree/main/COMP2000_ActualProject/V5

The Guest role can:
log in using the API authentication, 
browse the menu, 
make table reservations,
enable or disable notifications in settings,
and receive notifications if their booking is updated or cancelled.

The Staff role can:
log in using the API authentication, 
view and add to the menu, 
view all and cancel table reservations,
enable or disable notifications in settings,
and receive notifications for bookings when created or updated.

The coursework REST API is used for user authentication and role identification for directing them to either the staff or guest sides of the app.
API communication is handled by a background thread to prevent UI blocking.

The coursework uses a local SQLite database to store the menu items, reservations, and notifications.
This allows for fast response times and gives a clear separation between authentication and application data.

Notifications are delivered by Android's NotificationManager. A notification channel is created to support devices using Android 8.0+ instead of 13.0+.
Users can choose to enable or disable notifications using the settings page and this preference is stored using SharedPreferences.

Technologies used during the project were Java, Android Studio, SQLite, REST API (HTTP and JSON), RecyclerView, SharedPreferences, and NotificationManager.

This app was created as part of university coursework.
All functionality was implemented following course materials and official Android documentation.
This app is not intended for commercial release
