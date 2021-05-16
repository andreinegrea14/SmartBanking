# Smart Banking Application
A banking application written in Java & JavaFX shows the implementation of a desktop app with a very friendly user experience that uses the following technologies:
* [Java 15]
* [JavaFX] as the Graphical User Interface
* [Gradle] as the Build Tool
* [Nitrite Java] as the Database

## Setup & Run
To set up and run the project locally on your machine, please follow the next steps.

### Clone the repository
### Open the project in Intellij IDEA
### Run the project with Gradle
Run the project with gradle by using `gradlew run` command in the Intellij terminal.

## Issue Tracking
In order to obtain the full Agile Workflow we implemented, the evaluation team can find our project issue tracking flow on Jira by clicking this [link](https://andreinegrea.atlassian.net/jira/software/c/projects/SB/issues/).

## Application Description
### Registration & Login

The app lets the user to register & login via two roles:
* Client
* Bank Representative

The user also needs to enter the credentials he want to use: username and password.

<img src="docs/login.png"/>

### Forgotten Password
The user is able to change his password if he forgot his password or if he just wants to change it.

### Client
A user logged in as Client has access to the following features:
* Check his balance and add funds to his own account

<img src="docs/checkBalance.png"/>

* Transfer & receive money to and from different accounts with three types of currency and the bank will convert them into the national equivalent value

<img src="docs/transferFunds.png"/>

* See the list of the previous transactions, including their status (Sent or Received) along with the name of the sender/receiver

<img src="docs/seeTransactions.png"/>

* Ask for bank support in case they need it

<img src="docs/askForBankSupport.png"/>

* See a list of all their requests including their statuses and the reviews (messages from bank)

<img src="docs/listbankrequestsclient.png"/>

### Bank Representative

A user logged in as Bank Representative has access to the following features:
* See the list of all requests received from each client including their status (if it was already reviewed or not)

<img src="docs/listbankrequestsbank.png"/>

* Add a review along with a status (Accepted/Rejected request) for each request of each client (a client can send multiple requests to the bank)

<img src="docs/addReview.png"/>