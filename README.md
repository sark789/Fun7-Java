This application was made as an expertise test for company called Outfit7.

######
1. Download and install Eclipse (FOR ENTERPRISE JAVA DEVELOPERS!) and with it SpringBoot 4 (You can get it on Eclipse marketplace).
Database used = Firebase
2. Import the project into workspace
3. Run the main file which is located in: /demo/src/main/java/com/example/demo/Fun7         (Tests are already in main method)
######
Classes:
Fun7: main method, and Fun7App method which uses all features for obtaining data.
User: creates a User object
MultiplayerHandler: Handles multiplayer option (checks if the user is from US countrycode and if he made atleast 5 API calls)
AdsHandler: Handles ad option (sends the GET request to a site with auth and retireves ad data)
CustomerSupportHandler: Handles support availability (readsw the input date and compares it to Ljubljana time. Checks the days and hours of the week)
UserController: has methods for getting, creating, updating and deleting data on Firestore database
UnitTests: Unit and integration tests
FirebaseService: direct communication with database (get, create, update, remove)
FirebaseInitialize: Initializes the database







