Specification summary:
	
The project aims to help students organise their study schedule; incorporating their lectures, tutorials, assignments, tests, etc. Students will be asked to provide information about their timetable, tasks they need to complete (students will rank the tasks by priority), the time they wish to study and which is their preferred study method. The main entities for the program include Student, Task and StudyMethod. These entities interact with the use cases Checklist, StudyBlock and TaskManager. The Main method is the controller that asks users for their preferences about study time and method and saves their login details. CmdLineUI is the command line interface. The objective is to help students keep track of their deadlines, tasks, revision, lectures, etc. to make university life more manageable. 

CRC summary:

We have the following classes: 
Checklist: This stores tasks based on their priority and whether they are complete or incomplete. It collaborates with Task and Task Manager.
DueDateComparator: This compares the due dates between tasks. This collaborates with Task.
Student: It stores all the information about the student, that is the student’s name, Task Manager, timetable, Study method preferences.
StudyBlock: This creates a study block in the calendar and blocks a space.It collaborates with Checklist and StudyMethod.
StudyMethod: Holds different methods of study based on study duration and priorities. It collaborates with StudyBlock.
TaskManager: It holds tasks by priority and keeps the track of dates and past due date, and missed deadlines. It collaborates with Task and Checklist.
Task: It holds due dates, length and importance of the task. It also holds whether the task is completed and its weightage. This collaborates with TaskManager and Checklist.

Scenario walk-through summary:

When a Student logs into their account, they are presented with a list of tasks they have pending. Students are asked to add any new tasks they must complete or deadlines they have. They then are prompted to rank the task based on priority and choose their preferred study method. Based on their choices a study block is created, with their chosen tasks to do during their study period (block) and the study method they wish to use. All this is then printed.

Skeleton program summary: 

The program is run in the main.java file and shows a very basic implementation of our Study Planner program. In ExampleCode, we create 4 task objects each with varying lengths, due dates and importances and store them in a checklist instance. Eventually, we will want to implement comparators in order to sort the checklist by these different priorities but we currently only have the default priority of sorting by due date implemented. The task manager class manipulates the checklist by adding, completing and sorting the tasks in the checklist. Upon running the main file, the user is presented with the option to select a default student which is written in ExampleCode.java. Eventually, we plan on implementing the option for multiple students to be saved within a network (each with individual lecture schedules, tasks and study method preferences) but for now we only have a premade student saved to the local file. The user is then prompted to select the desired study method, which lists different ways for the study block to be divided into study sessions and break sessions. Currently, the only option is the Pomodoro method which alternates between 25 minute study sessions and 5 minute breaks but we plan on having other study methods added. The main program then takes the user input for the study method and uses it to construct a study block. Finally, the user is asked to input the length of the study session. Currently, this input is irrelevant because the study block is only dividing itself up based on the length of the first task in the checklist. Eventually, we will want to take into account the study block length and the next tasks in the checklist, but for now we use the very basic example in which the first task will take 75 minutes exactly and the study block return completely ignores whatever the study block length is (i.e. the return will always be the same regardless of input). 

What we’ve been working on: 

Pooja and Paridhi have been working on the written components of phase 0 such as the specifications, walkthrough and report. 

Zoya has been working on writing up and formatting the CRC cards. 

Jeb has been working on coding primarily. UI, Main and Presenter plus small random tasks. 

Ken has been working on StudyBlock and StudyMethods. 

Eric has been working on Checklist, Task, TaskManager and DueDateComparator.

Further steps:

Eric will be continuing to implement more ways for the User to edit the priority sorting for TaskManager, as well as adding UnitTests for the program and editing the user interface

Pooja will work on writing the Student class and its related functions and methods.

Jeb plans to work on next: implementing all TODOs with Paridhi, and fleshing out additional functionality in the skeleton program classes. Beginning to explore Calendar implementations.

Zoya will be working on implementing the schedule class and its related functions.

Kenneth will be implementing more ways for StudyBlock to divide the tasks based on the StudyMethod and Priority. Add descriptions for the different types of study methods and their benefits as an option for users to see. 

Paridhi will be working on the TODOs along with Jeb. 

Questions:

A question we are struggling with is whether entities are allowed to store other entities to still comply with clean architecture. What relationship are entities permitted to have with one another?
