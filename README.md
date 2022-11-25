# My CPSC 210 Project

## Proposal
My project will be a notes management application. **It will keep track of notes scattered across a filesystem and provide easy access to notes related to a certain topic.** The application will link to the note files but will not store the actual files. The notes will be grouped by topics, which in turn are grouped by subject. A note file can be present under more than one topic, and a topic can contain multiple note files.

**It is mainly intended for students that take notes by computer.** As a student that mainly takes notes on computers and never deletes them, I sometimes find that I already have prior notes on a subject I am learning about. Instead of writing entirely new notes, I would like to build upon my previous notes. However, **my notes are usually scattered across multiple folders, and I have trouble recalling their location**. There are several other reasons why I want to build this application:
- In class, I don’t have the time to manually search for certain notes.
- Some of my notes are in third-party file formats that the system search is unable to index.
- System search brings up too many irrelevant results.

## User Stories
- As a user, I want to be able to add a subject to a list of subjects
- As a user, I want to be able to view a list of my subjects
- As a user, I want to be able to remove a subject
- As a user, I want to be able to add a topic to a subject
- As a user, I want to be able to view a list of topics in a subject
- As a user, I want to be able to remove a topic from a subject
- As a user, when I select the quit option from the application menu, I want to be reminded to save my lists of subjects, topics, and notes to file and have the option to do so or not.
- As a user, when I start the application, I want to be given the option to load my lists of subjects, topics, and notes from file.

# Instructions for Grader
- You can generate the first required event related to adding Xs to a Y by entering the name of a subject in the bottom text field of the Subject Menu window and clicking the Add button
- You can generate the second required event related to adding Xs to a Y by selecting an element in the list in the Subject Menu window and clicking the Remove button
- You can locate my visual component by looking at the Restore Data dialog that pops up on launch
- You can save the state of my application by clicking the x button at the top of the Subject Menu window and clicking Yes
- You can reload the state of my application by clicking Yes in the Restore Data dialog on launch

# Phase 4: Task 2
Wed Nov 23 09:30:38 PST 2022  
Subject Biology added to list of subjects.

Wed Nov 23 09:30:48 PST 2022  
Subject History added to list of subjects.

Wed Nov 23 09:31:10 PST 2022  
Subject Geography added to list of subjects.

Wed Nov 23 09:31:15 PST 2022  
Subject Biology has been removed from list of subjects.

Wed Nov 23 09:31:28 PST 2022  
Topic Causes of WWIII added to subject History

Wed Nov 23 09:31:50 PST 2022  
Topic Apocalypse added to subject History

Wed Nov 23 09:31:52 PST 2022  
Topic Apocalypse has been removed from subject History

# Phase 4: Task 3
- I would change the LinkedHashSet in ListOfSubjects, Subject, and Topic to LinkedHashMap. The key would be the name of the element as a String and the value would be the element. This way, retrieving or removing a specific element would be much easier.
- I might use generics to implement the Subject and Topic classes since they are basically identical in functionality.
- I would create an abstract class that MenuGui and NoteMenuGui would extend since they share some behaviour, especially in the constructors.
- I would refractor some methods in the gui classes to use helper methods. For instance, initializeToolBar() in MenuGui handles a bunch of tasks that can be extracted into their own methods.