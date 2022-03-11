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
    SRC   
        files
    .gitignore
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




