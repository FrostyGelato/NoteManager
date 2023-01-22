# NoteManager
NoteManager is a notes management application. It will keep track of notes scattered across a filesystem and provide easy access to notes related to a certain topic. The application will link to the note files but will not store the actual files. The notes will be grouped by topics, which in turn are grouped by subject. A note file can be present under more than one topic, and a topic can contain multiple note files.

## Features
- [x] Be able to add a subject to a list of subjects
- [x] Be able to view a list of my subjects
- [x] Be able to remove a subject
- [x] Be able to add a topic to a subject
- [x] Be able to view a list of topics in a subject
- [x] Be able to remove a topic from a subject
- [x] When the user select the quit option from the application menu, he/she will be reminded to save his/her lists of subjects, topics, and notes to file and have the option to do so or not.
- [x] When the user start the application, he/she will be given the option to load my lists of subjects, topics, and notes from file.

## Instructions
- To create a new subject, enter the name of a subject in the bottom text field of the Subject Menu window and clicking the Add button
- To remove a subject, select an element in the list in the Subject Menu window and clicking the Remove button
- Save the state of the application by clicking the x button at the top of the Subject Menu window and clicking Yes
- Reload the state of the application by clicking Yes in the Restore Data dialog on launch

## Sample Log
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

## Potential Future Improvements
- Change the LinkedHashSet in ListOfSubjects, Subject, and Topic to LinkedHashMap. The key would be the name of the element as a String and the value would be the element. This way, retrieving or removing a specific element would be much easier.
- Use generics to implement the Subject and Topic classes since they are basically identical in functionality.
- Create an abstract class that MenuGui and NoteMenuGui would extend since they share some behaviour, especially in the constructors.
- Refractor some methods in the gui classes to use helper methods. For instance, initializeToolBar() in MenuGui handles a bunch of tasks that can be extracted into their own methods.