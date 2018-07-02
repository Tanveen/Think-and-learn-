#include <iostream>
#include<unistd.h>
#include<sys/ipc.h>
#include<sys/shm.h>
#include <sys/wait.h> // declartion for waiting
#include <stdlib.h> //to include dynamic memory management
using namespace std; 
const int nofQuestions = 3; //declaring a constant variable for number of question set to 3.

class Quiz 
{
public:
    int ans[nofQuestions]; 
    string studentname;

public:
    string copystring(string); 
    int Getstudentpoints(); 
    void EnterName(); 
    string FetchName(); 
    void EnterAnswers(); // function for accepting the answers from the players
    void showAnswers(); //function to display the answers entered by the players
};


string Quiz::copystring(string str)
{
	studentname=str;
	return studentname;
}

//following function takes the name of the player
void Quiz::EnterName() //defining member function of class quiz
{
     string Stu_name; //declaring Stu_name as data type string
     cout << endl << "Please enter the name of the player:"; //displays asking the player to enter the name
     cin>>Stu_name; // player entered value is stored in Stu_name
     copystring(Stu_name); // copies the string stored in Stu_name
     
}

//following function is for returning the name of the player

string Quiz::FetchName() //defining member function string FetchName() of class quiz
{
    return studentname; //returns the player name
}

//following function is for displaying the answers given by the player
void Quiz::showAnswers() // defining member function of class Quiz
{
    for (int l = 0; l < nofQuestions; l++) 
{
        cout << endl << "Answer" <<l+1<<": "<< ans[l];
    }
}

//following func() is for providing the questions to the player, called after the player provides his name

string getQuestion(int t) 
{
   if (t==1)
 {
   cout<<" 2*1= "; // print's first question
}
else if(t==2)
{
   cout<<" 4/2= "; // print's the second question
}
else if(t==3)
{
   cout<<" 3-1= "; // print's the third question
}
return "";
}

//following func() for taking the answers from the player
 void Quiz::EnterAnswers() // defining member function of class Quiz

{
  for (int m = 0; m < nofQuestions; m++) //enters into the loop till all the three questions are answered*/ 
 {
   int answer; 
   cout<<"\nPlease enter the answers: \t"; //print's "Please enter the answers"
   int l=m+1; // incrementing m by 1 and storing the value in l,this moves to the next question till all the three questions are answered */
  getQuestion(l); //to access the questions in the getQuestion function */
   cin>>answer; //geting values (answers) given by the player */
   ans[m]=answer; // assigning the values to the array for the all the answers */
 }
}

//this func() is for validating the player answers and get the score
int Quiz::Getstudentpoints() //defining the member function of class Quiz
{
  int points = 0; // initialization of points to 0
  for (int m = 0; m < nofQuestions; m++) // enters the loop till all the answers are validated
 {
  if (ans[m] == 2) // checks condition if the player answers are equal to 2 then increments the points. 
  {
   points++; 
  }
 } 
  return points;
 } 
 void Position(int finalpoints[], pid_t Shared_Memory_Pointer_Formal[], int QuizCount) 

{
  for (int e = 0; e < QuizCount; e++) 
 {
  for (int f = 0; f < QuizCount-1; f++) 
  {
   if (finalpoints[f]<finalpoints[f+1]) //this condition compares the first index with next index and swaps based on the higher point 
    {
     int tmp = finalpoints[f];
     finalpoints[f] = finalpoints[f + 1];
     finalpoints[f + 1] = tmp;
     pid_t TemporaryID = Shared_Memory_Pointer_Formal[f]; /* this is to swap the process ID in the array as per the previous swap of higher to lower points */
     Shared_Memory_Pointer_Formal[f] = Shared_Memory_Pointer_Formal[f + 1]; 
     Shared_Memory_Pointer_Formal[f + 1] = TemporaryID;
    }
   } 
 }
}

int main(int argc, char** argv) 
{
    int QuizCount; /* initializing QuizCount to store the number of participants */
    cout << endl << "This is Main process " << getpid() << "\nEnter the count of the participants: ";
[4:34 PM, 2/15/2018] Dhanush.: Quiz dynamically using new operator
    Quiz* q = new Quiz[QuizCount];

//Creating shared memory segments for the scores, process_ids, and qualifer(aka. counter) 
    int POINTS_SEG_ID =  shmget(IPC_PRIVATE,  QuizCount * sizeof (int), IPC_CREAT | 0666);
    int QUALIFIER_SEG_ID = shmget(IPC_PRIVATE,  QuizCount * sizeof (int), IPC_CREAT | 0666);
    int PROCESS_SEG_ID = shmget(IPC_PRIVATE,  QuizCount * sizeof (int), IPC_CREAT | 0666);
//attaching the created shared memory segment to respective pointers
    int *points = (int *) shmat(POINTS_SEG_ID, NULL, 0);
    int *addr = (int *) shmat(QUALIFIER_SEG_ID, NULL, 0);
    int *PROCESS_SEG_IDs = (int *) shmat(PROCESS_SEG_ID, NULL, 0);

//creating child_pid, Parent_Pid of pid types to use it for display and if conditions    
    pid_t child_pid;
    pid_t Parent_Pid = getpid();


 int qualifier = 0; //setting the qualifier to 0
//iterate the loop for the no.of Players playing
  for (int x = 0; x < QuizCount; x++)
 {
  if (Parent_Pid == getpid()) //if this is the parent and enter the loop 
  {
   child_pid = fork(); //create the child for the parent
   if (child_pid == 0) //if child is created then enter
    { 
      pid_t p=getpid(); //get the pid of the child process int p
      PROCESS_SEG_IDs[addr[0]] = p; //assign the child process id p into the shared segment array[0]
      q[qualifier].EnterName(); //enter the name of the student
	  cout<<"\n";
      cout <<q[qualifier].FetchName() << ": " << p << endl; //print student information
      q[qualifier].EnterAnswers(); //enter the answers by calling the EnterAnswers() function call
      points[addr[0]] = q[qualifier].Getstudentpoints();
      addr[0]++; //increment the counter in shared segment
      q[qualifier].showAnswers(); //display the answers of the student
      qualifier++; //increment the counter for child
     } 
   else 
  {

    cout<<"\n\t\t\t ---------------- This Quiz will have 3 Questions and to be answered in (15 Seconds) ---------------- \n";
    cout <<"\nTimer starts for Player: "<<child_pid<<endl;
    system("sleep 15"); /* adding a timer of 15 seconds to answer each question within it */
  }  
   }  
}
 if (Parent_Pid == getpid()) //for parent do the following
 {  
   Position(points, PROCESS_SEG_IDs, QuizCount);
   cout<<endl;
   int l;
   for (int k = 0; k < QuizCount; k++) 
   {
     l=k+1;
      cout <<l<<" Rank goes to the player with "<<points[k] <<" Correct answers \n"; // print the score for each student 
   }
     for (int m = 0; m < QuizCount; m++) 
   {  
     cout << "Terminating: " << PROCESS_SEG_IDs[m] << endl;
     kill(PROCESS_SEG_IDs[m], SIGTERM); /* its a signal to kill the process IDs */
	 }

	 //removing the shared memory segments created in this program.
 shmctl(POINTS_SEG_ID, IPC_RMID, (struct shmid_ds *) NULL);
 shmctl(QUALIFIER_SEG_ID, IPC_RMID, (struct shmid_ds *) NULL);
 shmctl(PROCESS_SEG_ID, IPC_RMID, (struct shmid_ds *) NULL);
 }