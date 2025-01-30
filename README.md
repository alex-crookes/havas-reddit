# Havas Reddit Take-home Assignment

Deliverable details can be found in the AndroidDeveloperAssignment.pdf at the root of the project



# Deliverable Notes

This is not a complete project - There are notes on the Pseudo-Kanban below that outline where things are either skipped or should be improved.

The app only has a single API call - To get the 25 items on the Home Page feed. It uses the **MVI** pattern to manage data in the correct way. This is handled in the RedditApi method. Unit testing is present to ensure the API Model correctly maps to the models we need for the UI.

The next layer is the RedditRepository. This mostly follows the StaleWhileRevalidate (SWR) pattern. In short, the last loaded data are cached and only reloaded *if* the data is older than 60seconds (note: this is actually too fast for reddit itself). It could be turned into a true SWR implementation if session caching were implemented (note: this would also be trivial by implementing the FileIO method(s))

Next then is the ViewModel  - This plays the role of the Domain as there's no real business logic here. It expresses the HomePage view via the HomePageViewState and updates it by sending HomePageIntents. 

Finally, UI is rendered in functional components. The requirement to handle a RecyclerView is managed by wrapping it into a Compose component which uses XML Layouts and ViewBinding internally. The ViewHolder is also created in XML and ViewBinding, however, it also then contains the "MetaBar" as a compose component. This demonstrates the power of XML/Compose interop. The UI is also intended to more closely match the "new" Reddit homepage, which means it does not look like the Image. However, this would be a minor refactoring, which is also limited to the items in the UI folder

As mentioned above, I've deliberately skipped steps and these are added to the PseudoKanban as "WONTFIX" items purely for interest.

Project taken about 5 total hours so far, mainly I have spent the past two days multi-tasking in one hour chunks.

I also want to stress there is also NO usage of Gemini or other AI assisted tools - I find their autocompletes distracting and they slow me down. I also am not a huge fan of the code they create. 

Any questions, please let me know


# Preparatory Notes


Ordinarily, the project would be split into multiple modules, each of which performs a specific task

- UI: This layer is inherently dumb and exists to print data only. Highly Android code-dependent
- Domain: Business logic. In the case of this app, there really isn't any business logic
- Repository: This is the abstraction of the Providers (data sources).
  - Ordinarily, it would decide the source of truth (i.e. can manage prefs, local DBs, caching, load strategies, etc), and it would return an *internal* Entity or Domain model - This abstracts the Domain operations from any other data structures, though it does add some overhead in conversions
  - We only have a single Provider, so there's no real Repo layer either
- Provider: Direct access to data sources, such as API, SQL. Manage converting the API model into a DTO (i.e. Handle serialization) and all access methods

In all the above cases, we strive for composition over inheritance. This is **normally** achieved by creating interfaces for each layer and using (say) Hilt to inject (ideally at the constructor)

This approach ensures that almost *every* line of code (except UI) can have a Junit test


## Unit Tests

Most of the time I would add Unit Tests to build confidence. As this is an Minimum Viable Product, Only the API layer will be testable


## Project Structure

As covered above, this would *normally* include multiple modules, which means more top level folders. This normally make it easier to move the modules to their own repos later on if needed. To identify the layers, folders will be used.

However we don't have that. What we do have, however is another folder called **Bruno** - This is an open source version of Postman and used to explore/test the API at low effort. In practice, this means you can test the API methods and copy the output to files, which can then be used as the source of Unit tests. While there is only a single API call, it's still good practice to include the Bruno folder as a function of testing.

The architecture will essentially be MVVM/MVI


# Pseudo-Kanban

In the absence of a project board, the following is the list of tasks that need to be completed as a pseudo-Kanban

- [X] Set up Git repo
- [X] Create base project based using Version Catalog. Base dependencies include
	- Ktor (Networking) - Retrofit likely expected, but KTOR is now part of Kotlin and supports KMP. Also, does not require Context, so much easier to test
	- Glide Compose (Image Handling)
	- KotlinXSerialization (Serialization) - GSON is likely expected. Kx is both faster and now the standard
	- Note: Based off of another project I was working on - will need to be cleaned
- [X] Implement the API Provider - ie, get the data and deserialize it to a DTO
- [X] Create the Entity / Domain model
- [X] Created a Repo layer to manage accessing the data
- [X] Create the View Model to expose the List Data
- [X] Implement the Home Screen (Old school Recycler/View Adapter) will likely be injected into a Compose function
- [X] Implement the Details screen using Compose
- [X] Check and clean Dependencies
- [X] Add Back button support


BUGS

- [X] Reddit Images not loading
	Ughhh... They use some encoding - [this](https://old.reddit.com/r/redditdev/comments/9ncg2r/deleted_by_user/) fixes it 
- [X] Image in Cards is small (am using the smallest thumbnail)

WONTFIX
- [ ] There are @TODO Notes in the code...
- [ ] Glide Module Annotation not found (would be fixed by using Compose only)
- [ ] Fix layouts to be a little more "standard"
- [ ] Implement a *proper* theme - While there is a Material theme, it's only really used in Compose - Would need a little more time to research Compose theming in XML to proceed
- [ ] Placeholders for image loading
- [ ] Add other decorators, such as DATE
- [ ] Add other data from API
- [ ] FileIO is only really used for Testing - Could move to testing, but this is useful for caching so leaving here.
- [ ] Would add more tests, especially value based inspections (i.e. ensure values are correctly being set)
- [ ] Add a TopBar with Back button via the Scaffold
- [ ] Implement Navigation library - Would make the UI code even less coupled


