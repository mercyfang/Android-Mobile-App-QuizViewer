Name: Manchen Mercy Fang. Barbara Xiong.
Our app displays all quiz names and completion status upon start. When user clicks the complete/incomplete button, all history scores will be displayed if complete. When user clicks each quiz, the quiz questions will be displayed one by one. In the end the result will be shown.
Clarification: We did not write the unit test for QuizCompletionScoreDialogFragment class because it is similar to Adapter and Activity class, which contains Bundle and RecyclerView.

Extra features:
1.We show all historical results for each quiz if it is in completion status.
2.We use color to distinguish different quiz types in the main screen.
3.We added non-linear quiz which contains easy and hard attribute for each quesiton, and we select the next question to display accordingly. For example, if user gets one easy question correct, we display a hard question next. But if the user gets the hard question wrong, we display an easy question next. We also have a different way of calculating scores.
4.We managed to reuse a lot of code for better code efficiency and cleaness by having one activity to display all three types of quizzes. (QuizScreen and Adapter class)
