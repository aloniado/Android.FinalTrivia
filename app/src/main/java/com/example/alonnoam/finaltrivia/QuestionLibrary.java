package com.example.alonnoam.finaltrivia;

public class QuestionLibrary {

    private int totalQuestionNumber=24;

    private String Questions[] = {
            "Which one is alive?",
            "What is the largest country on Earth?",
            "Where did Napolene die?",
            "Which of the following is not an island?",
            "How many Tennis Grandslams there are?",
            "What is the main color of the Swiss flag?",
            "How many players participate in a basketball game?",
            "What is Israel's year of independence?",
            "How many oscars were won by the movie Titanic?",
            "Complete the sentence: Hit me baby one more...?",
            "What was the name of the first manned mission to land on the moon?",
            "What is the only mammal that can truly fly?",
            "In the game of chess, how many pawns does each player start with?",
            "what lake is the deepest and largest freshwater lake in the world?",
            "Where would you find the Sea of Tranquility?",
            "What kind of weapon is a falchion?",
            "What is the diameter of Earth?",
            "What color is a Welsh poppy?",
            "What color is a Himalayan poppy?",
            "How many valves does a trumpet have?",
            "What is the name for the offspring of a male lion and a female tiger?",
            "What is the largest rodent found in North America?",
            "What school does Harry Potter attend?",
            "What is the closest star to our own sun?"
    };

    private String Choices[][] = {
            {"Rock", "Dog", "Flower"},
            {"Russia", "Canada", "China"},
            {"Paris","Rome","Corsica"},
            {"Spain","Australia","Cyprus"},
            {"3","4","5"},
            {"Red","Blue","Black"},
            {"10","22","16"},
            {"2000","1979","1948"},
            {"2","11","6"},
            {"Dog","Time","day"},
            {"Apollo 11", "Apollo 9", "Event Horizon"},
            {"a Bat", "a Flying squirrel", "a Pigeon"},
            {"10", "8", "12"},
            {"Lake Baikal", "Lake Victoria", "Lake Huron"},
            {"The Moon","Mars","The Sun"},
            {"A Sword","A Gun","A Cannon"},
            {"8,000 miles","80,000 miles","800,000 miles"},
            {"Yellow","Red","Blue"},
            {"Yellow","Red","Blue"},
            {"1","3","8"},
            {"Liger","Tigeon","Stripped Lion"},
            {"Guinea pig","Beaver","Rock cavy"},
            {"Hogwarts","Durmstrang","Beauxbatons"},
            {"Proxima Centauri","Mercury","Alpha Centauri"}

    };

    private String Answers[] = {
            "Dog",
            "Russia",
            "Corsica",
            "Spain",
            "4",
            "Red",
            "10",
            "1948",
            "11",
            "Time",
            "Apollo 11",
            "a Bat",
            "8",
            "Lake Baikal",
            "The Moon",
            "A Sword",
            "8,000 miles",
            "Yellow",
            "Blue",
            "3",
            "Liger",
            "Beaver",
            "Hogwarts",
            "Proxima Centauri"
    };


    //------------------------------------------methods:

    public String getQuestion(int n) {      //returns question number n
        String question = Questions[n];
        return question;
    }

    public String getAnswer(int n) {        //returns correct answer for question number n
        String answer = Answers[n];
        return answer;
    }

    public String getChoice(int question, int choiceNum) {  //returns choice number  choiceNum, for question number question
        String choice = Choices[question][choiceNum];
        return choice;
    }

    public int getTotalQuestionNumber(){
        return totalQuestionNumber;
    }
    
}
