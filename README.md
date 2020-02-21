This application was made as an expertise test for company called Outfit7.<br/>

######<br/>
1. Download and install Eclipse (FOR ENTERPRISE JAVA DEVELOPERS!) and with it SpringBoot 4 (You can get it on Eclipse marketplace).<br/>
Database used = Firebase<br/>
2. Import the project into workspace<br/>
3. Run the main file which is located in: /demo/src/main/java/com/example/demo/Fun7         (Tests are already in main method)<br/>
######<br/>
Classes:<br/>
Fun7: main method, and Fun7App method which uses all features for obtaining data.<br/>
User: creates a User object<br/>
MultiplayerHandler: Handles multiplayer option (checks if the user is from US countrycode and if he made atleast 5 API calls)<br/>
AdsHandler: Handles ad option (sends the GET request to a site with auth and retireves ad data)<br/>
CustomerSupportHandler: Handles support availability (readsw the input date and compares it to Ljubljana time. Checks the days and hours of the week)<br/>
UserController: has methods for getting, creating, updating and deleting data on Firestore database<br/>
UnitTests: Unit and integration tests<br/>
FirebaseService: direct communication with database (get, create, update, remove)<br/>
FirebaseInitialize: Initializes the database<br/>







