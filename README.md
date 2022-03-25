# SYSC4806_project

This is the project for SYSC 4806 made by group 8. The project topic chosen for this project is the "Customer self
server portal".

> Assuming there is already an application called AppX. The customer self-serve portal is responsible for acquiring and
> manging new customers for AppX. A new customer can register a new account and start to try AppX. A trial user can use
> the service for free for 30 days with a constraint such as a limit of 1,000 API calls per day at most. A paid user has
> constraint set up by the admin. An administrator can login to the portal and flip a trial user as a paid user and vice 
> versa. The admin can also manage the constraints. You will assume AppX as a set of REST APIs and you will mock the APIs.

Our group members are:
- Pruthvi Chivukula
- Justin Kim
- Naman Sethi
- Lazar Milojevic
- Robert Desai

## Project Devlopment Details
This project uses a kanban board that can be found in the project tab of this GitHub repo.
The issues that members are currently working on can be found in the issues tab.
Also found in the issues tab is the weekly scrum issue, where members add comments with their progress/goals for the week

This project uses some automated CI/CD features.
CircleCI is used for CI (https://app.circleci.com/pipelines/github/namansethi/SYSC4806_project?branch=main&filter=all).
Heroku is used for the CD (https://sysc4806-project-group8.herokuapp.com/)

## Project Technologies
This project is a maven project. The pom.xml file is provided for managing the dependencies, jar generation, etc.
This project uses Java Spring(for the backend) and HTML/CSS (for the frontend).

## Project Structure
Currently, The general structure of the project is as follows.

    .circleci
        config.yml - (for CircleCI integration)
    Src   
        main
            SpringJPA
                Model
                    Administrator.java - (Represents the admin user of the system) (Depricated, will be removed)
                    Constraint.java - (Represents the constraints applied to a customer by admin)
                    Customer.java - (Represents the customer for the system.) (Depricated, will be removed)
                    User.java - (The base user class which was extended by Administrator and Customer. Now it is the primary user class)
                    UserRepository.java - (The repository that stores User objects)
                CredentialService.java - (Loads user credentials from UserRepository)
                MvcConfig.java - (Adds views to the ViewControllerRegistry)
                PageController.java - (Manages the pages and templates)
                UserController.java - (Defines the REST calls for user objects)
                UserCredentials.java - (Wraps the user object to ensure safe credential access)
                WebSecurityConfig.java - (Configures security for Users)
                WebpageApplication.java - (Starts the Spring application and loads some starter users)
            resources
                TrialUser
                    upgrade.html
                    userPage.html
                noUser
                    adminView.html
                    landing.html
                    pricing.html
                    register.html
                static/css
                    landing.css
                    login.css
                    pricing.css
                templates
                    layout.html
                application.properties
        test
            java.SpringJPA
                AdminPageTest
                LandingPageTest
                PricingPageTest
                RegisterPageTest
                UserPageTest
                UserTest

    .gitignore
    Milestone 1 UML Diagram.jpg
    Milestone 2 UML Diagram.png
    Milestone 2 ER Diagram.png
    Procfile - (for heroku integration)
    README.md
    pom.xml
    system.properties -(for heroku integration)

## Deliverables
### Milestone 1

For milestone 1, the functionality as specified from the project rules is:
>For this milestone we are looking to see enough functionality to get a feel for the system and how it will work. 
>One important use case should be operational. It should collect data from the back end, do something with it and
>display the result

As such, the current functionality includes
 - Ability to log in as a preset user
 - State-Based login, which allows a user who has logged in previously to remain logged in when navigating around the website (cookies are used)
 
### Milestone 2

For milestone 2, the functionality as specified from the project rules is:
>For the alpha release your system should be somewhat usable, although not feature-complete. This means that a user 
> should be able to use several related features of the app and do something reasonably useful.

As such, the current functionality includes
- Ability to log in as a pre-set user
- Register as a new user
- Login as a newly registered user
- State-Based login, which allows a user who has logged in previously to remain logged in when navigating around the website (cookies are used)
- Make Fake API calls as a user (limited by the maximum value set for the user)
- Admins can alter the maximum number fo api calls for a user
- Admins can alter the type of user (trial vs paid)
- Logout as a logged-in user (normal user and admin)

### Milestone 3

For milestone 3, the functionality as specified from the project rules is:
>For the final sprint of your project you must decide on the final scope of the product: a set of features that can be 
>implemented within the given timeline and makes the product usable and useful. The user interface should not have any 
>dangling links to non-implemented features.

For milestone 3, we want to wrap up a few remaining tasks such as the implementation of a message when exceeding number
of trial calls, and finishing the admin and user role security. Additionally, for milestone 3 we want to add the 
constraints that can be added to the user by the admin. Additionally, we also want to add a trial duration so that 
free members can only use the trial account for a specified time limit. Finally, we want to resolve any remaining bugs 
and/or deficiencies in the functionality that may exist in our program. A list of specific issues can be found in the 
issues tab of this GitHub.



