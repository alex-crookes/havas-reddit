# Havas Reddit Take-home Assignment

Deliverable details can be found in the AndroidDeveloperAssignment.pdf at the root of the project

Project taken about 3 hours so far (2025-01-29) due to multitasking.

There is also NO usage of Gemini or other AI assisted tools - they slow me down...

# Notes

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
- [ ] Implement the Home Screen (Old school Recycler/View Adapter) will likely be injected into a Compose function
- [X] Implement the Details screen using Compose
- [ ] Check and clean Dependencies



