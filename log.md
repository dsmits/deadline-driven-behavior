DISCLAIMER: The things that I write here are mainly to arrange my own thoughts and might not always be understandable for other people.

[Latest version of thesis](https://deadline-driven-behavior.googlecode.com/svn/trunk/report/simobs_thesis_final.pdf)


## General TODO: ##
  * For final demonstration:
    * Fix coordinates of situations
    * Make sure that pedestrians stay within simulation area
  * Read that one article about Petri nets
  * Further read the book/article that I refer to about finite state machines.

# 23-12-2014 #
I'm quite proud of the cover picture I made:

![https://deadline-driven-behavior.googlecode.com/svn/trunk/report/kaft_bewerkt.png](https://deadline-driven-behavior.googlecode.com/svn/trunk/report/kaft_bewerkt.png)

# 13-12-2014 #
I'm reading an article that describes basic pPetri niets in detail and some interesting properties are defined. Maybe can put some of the restrictions I put on Petri nets for my system to work in these terms.

# 12-12-2014 #
I'm working on completing the related works chapter. I've gotten to the part about finite state machines and notice it is very short in comparison to the Petri-nets part. Petri-nets are more important for my thesis but still. I'll have to decide what to do about that but I think it won't be a disaster if it stays that short. One thing that I also won't be finishing today in this section is the todo about adding the article about petri nets by Jörg Desel et al. I'll read the article tomorrow and decide what to add then.

I think I also need to discuss with my supervisor what to do about the plans in the thesis about using stochastic Petri nets and then not doing it because the software doesn't work as expected.

Shiit, I notice that my literature says absolutely nothing about time planning! I hope I finish the rest of my thesis on monday (the method experiments and results), which should be doable because I think they are pretty complete already, and then on tuesday or wednesday fix the issue about the time planning literature.

# 10-12-2014 #
Hoped to finish the complete related works section but it takes a lot of time. I did improve it a lot though, and I'm quite satisfied with the way my thesis looks now up to what I've worked on today, but I've still only gotten through half of the chapter. I did manage to resolve all to-do's on my path. Hopefully I can finish this later this week and also mostly finish the method section. I really want to finish my thesis before Christmas.

# 8-12-2014 #
Today I started on improving the introduction. It's a lot better now, and I also broke up the walls of text some more with another picture.

# 5-12-2014 #
Today I'm going run through the thesis and check what needs to be done. I also made the margins smaller so there is more space for text and hopefully the pictures will be more in between pieces of text instead of all at the end of the chapter. It worked for some pictures but not all so I still have to play around a bit with the pictures.
I'm making a list of stuff in decreasing priority of what still needs to be done about the thesis. I hope I'll be able to add a few pages because with the smaller margins my thesis is only 50 pages long.
What needs to be done:
  1. rearrange/rewrite related work so it's more consistent
  1. rewrite second part of introduction
  1. Put finishing touch on research questions

# 1-12-2014 #
I added the rest of the pictures of the qualitative experiment.

# 28-11-2014 #
I just noticed the pillar situation is totally in the wrong place, that is probably why it barely showed up in the graphs.

# 26-11-2014 #
Today I'm working on the quantitative experiment (and hopefully also the quantitative result). I think I'm going to make the simulation so that only the desired behavior is done, and then make a picture of that.

Here are the pictures:

![https://deadline-driven-behavior.googlecode.com/svn/trunk/report/qualitative%20experiment%20pictures/zoomed_in_leanagainstpillar_reallife.png](https://deadline-driven-behavior.googlecode.com/svn/trunk/report/qualitative%20experiment%20pictures/zoomed_in_leanagainstpillar_reallife.png)

![https://deadline-driven-behavior.googlecode.com/svn/trunk/report/qualitative%20experiment%20pictures/zoomed_in_standstill_reallife.png](https://deadline-driven-behavior.googlecode.com/svn/trunk/report/qualitative%20experiment%20pictures/zoomed_in_standstill_reallife.png)

![https://deadline-driven-behavior.googlecode.com/svn/trunk/report/qualitative%20experiment%20pictures/zoomed_in_toiletsituation_reallife.png](https://deadline-driven-behavior.googlecode.com/svn/trunk/report/qualitative%20experiment%20pictures/zoomed_in_toiletsituation_reallife.png)

Only I just realized I forgot to do the wander behavior. I think I'm going to do these behaviors first and insert wander later, otherwise I have to switch to windows again and back to use photoshop.

I want to give an indication of how often the behaviors I describe are happening. I could use the tracked data for that but I have no idea of what kind of time interval that data is. I'm suspecting 10 minutes but I'm not sure of it. I also made a slight modification to the toiletsituation Petri net so that the two "friends" have a fixed place where one person waits and the other comes back to. I will probably have to modify the picture in the thesis.


# 24-11-2014 #
I finished the example in the method section today.

# 21-11-2014 #
Last tuesday my supervisor suggested I add some tables in which we judge the different combinations of utility functions in the results section of the quantitative experiments. I added the tables and descriptions today.

I think the most important things that I can do next is modifying the method section, where an step-by-step example should be added, and modifying the first qualitative experiment. I don't feel like doing the latter so I'll start with the method section.

Among other things I added these nice pictures:

![https://deadline-driven-behavior.googlecode.com/svn-history/r253/trunk/report/room_with_agent.png](https://deadline-driven-behavior.googlecode.com/svn-history/r253/trunk/report/room_with_agent.png)

![https://deadline-driven-behavior.googlecode.com/svn-history/r253/trunk/report/room_with_agent_and_situations.png](https://deadline-driven-behavior.googlecode.com/svn-history/r253/trunk/report/room_with_agent_and_situations.png)

# 19-11-2014 #
I had a meeting with my supervisor yesterday. We discussed a lot of things that will be too much to name here. One important point that we decided on is that the qualitative experiment should be different. The design of the Petri nets can go back to the method, and then the qualitative experiment can be studying the tracked data, plotting trajectories of the pedestrians doing the selected behavior and compare those to the trajectories of the designed behavior.

# 17-11-2014 #
Added stuff about Petri nets in the methods chapter. Trying to make something consistent out of my method remains a challenge. Wednesday I will make a step-by-step description of the system using a example scene.

# 12-11-2014 #
I have included a reason for the utility functions. Today I'm scanning through the thesis, checking which "to-dos" are most important and fixing those. Next time I should really start on finishing up my conclusion and discussion. Also, I feel that the method section is still quite short. Have I said everything or are there still parts missing? I've also printed another article on Petri nets, maybe it will enable me to give some more in-depth information.

# 10-11-2014 #
Among other things I'm fixing the captions for the graphs in quantitative results. I was also thinking I have to add a good reason why I chose the utility functions that I have. I will probably not get to this today anymore, so that's for wednesday.

# 7-11-2014 #
Today I'm working on the "qualitative experiment" section in the experiments chapter. I do have some trouble though with thinking of the right criteria for deciding whether the experiment is a success.

# 5-11-2014 #
I think I finished the [results section](https://deadline-driven-behavior.googlecode.com/svn-history/r232/trunk/report/simobs_results.pdf).

[Complete thesis](https://deadline-driven-behavior.googlecode.com/svn-history/r232/trunk/report/simobs_thesis_final.pdf)

I think I will work on the qualitative experiment in the "Experiments" section next time. I will also read through the results section to see if there's something missing.


# 3-11-2014 #
At the moment I'm describing the results of the second quantitative experiment, but I'm having a little trouble over deciding which graphs are important to show and which aren't. What am I trying to prove with these graphs?

[Results section](https://deadline-driven-behavior.googlecode.com/svn-history/r222/trunk/report/simobs_results.pdf)

# 1-11-2014 #

Today I'm going to try to finish the results section. First I will make sure the graphs are in the right position. I'm going to make subfigures and group the graphs like I did in the to-do list last week. Then I will show the two graphs with the constant goal utility function.

Update: Just decided that I can't put the graphs three in a row, they would become unreadable.

Some things to discuss with my supervisor:
  * I call the x axis in my graphs t, but this is probably confusing, should I call it something else? It represents the time that is left, but that is the exact opposite of the time elapsed.
  * I try to finish the results section today but I think I won't be able to do anything about the "qualitative" experiment. I'm not sure yet what I should do about that experiment. Maybe I can form a plan tomorrow.


# 29-10-2014 #
Just generated the last 3 graphs:

![https://deadline-driven-behavior.googlecode.com/svn-history/r211/trunk/hulpscriptjes/goodresults/gaussian200_50_1_goalgaussian0_50_1.png](https://deadline-driven-behavior.googlecode.com/svn-history/r211/trunk/hulpscriptjes/goodresults/gaussian200_50_1_goalgaussian0_50_1.png)

![https://deadline-driven-behavior.googlecode.com/svn-history/r211/trunk/hulpscriptjes/goodresults/linear0_1_200_goalgaussian_0_50_1.png](https://deadline-driven-behavior.googlecode.com/svn-history/r211/trunk/hulpscriptjes/goodresults/linear0_1_200_goalgaussian_0_50_1.png)

![https://deadline-driven-behavior.googlecode.com/svn-history/r211/trunk/hulpscriptjes/goodresults/sigmoid100_0_10_1_goalgaussian0_50_1.png](https://deadline-driven-behavior.googlecode.com/svn-history/r211/trunk/hulpscriptjes/goodresults/sigmoid100_0_10_1_goalgaussian0_50_1.png)

Now I will have to work on finishing the results section. To do this, I am going through the results images of the experiments where the goal action is included and came across a graph that needs to be double checked:

![https://deadline-driven-behavior.googlecode.com/svn-history/r206/trunk/hulpscriptjes/goodresults/linear0_1_200_goalsigmoid_100_0_-10_1.png](https://deadline-driven-behavior.googlecode.com/svn-history/r206/trunk/hulpscriptjes/goodresults/linear0_1_200_goalsigmoid_100_0_-10_1.png)

This graph looks exactly the same as:

![https://deadline-driven-behavior.googlecode.com/svn-history/r206/trunk/hulpscriptjes/goodresults/gaussian200_50_1_goalsigmoid_100_0_-1_1.png](https://deadline-driven-behavior.googlecode.com/svn-history/r206/trunk/hulpscriptjes/goodresults/gaussian200_50_1_goalsigmoid_100_0_-1_1.png)

Only the legend is different. What's going on here? I'll find out friday and finish off today with some text in the results section about the results in general.


# 27-10-2014 #
Just plotted with init(clock, goalTime, new Gaussian(200, 50, 1), new Sigmoid(100,0,10,-1));

![https://deadline-driven-behavior.googlecode.com/svn/trunk/hulpscriptjes/goodresults/gaussian_200_50_1_goalsigmoid_100_0_10_-1.png](https://deadline-driven-behavior.googlecode.com/svn/trunk/hulpscriptjes/goodresults/gaussian_200_50_1_goalsigmoid_100_0_10_-1.png)

I just realize that I shouldn't have used these parameters for a Gaussian goal probability function. The mean of the Gaussian should be 0. I see that I misread the todo list.

I also just realize that the parameters for the sigmoid goal utility function (100,0,10,-1) are wrong. In this way all values are below zero. This also means I have to redo two other plots. The parameters should have been (100,0,-10,1)

The proper graphs I did today:

![https://deadline-driven-behavior.googlecode.com/svn-history/r206/trunk/hulpscriptjes/goodresults/gaussian200_50_1_goalsigmoid_100_0_-1_1.png](https://deadline-driven-behavior.googlecode.com/svn-history/r206/trunk/hulpscriptjes/goodresults/gaussian200_50_1_goalsigmoid_100_0_-1_1.png)

![https://deadline-driven-behavior.googlecode.com/svn-history/r206/trunk/hulpscriptjes/goodresults/linear0_1_200_goalsigmoid_100_0_-10_1.png](https://deadline-driven-behavior.googlecode.com/svn-history/r206/trunk/hulpscriptjes/goodresults/linear0_1_200_goalsigmoid_100_0_-10_1.png)

![https://deadline-driven-behavior.googlecode.com/svn-history/r206/trunk/hulpscriptjes/goodresults/sigmoid100_0_10_1_goalsigmoid100_0_-10_1.png](https://deadline-driven-behavior.googlecode.com/svn-history/r206/trunk/hulpscriptjes/goodresults/sigmoid100_0_10_1_goalsigmoid100_0_-10_1.png)

# 22-10-2014 #
The graphs of today:

![https://deadline-driven-behavior.googlecode.com/svn/trunk/hulpscriptjes/goodresults/linear0to1_goalsigmoid_100_0_10_01.png](https://deadline-driven-behavior.googlecode.com/svn/trunk/hulpscriptjes/goodresults/linear0to1_goalsigmoid_100_0_10_01.png)

![https://deadline-driven-behavior.googlecode.com/svn/trunk/hulpscriptjes/goodresults/sigmoid100_0_10_1_goalsigmoid100_0_10_-1.png](https://deadline-driven-behavior.googlecode.com/svn/trunk/hulpscriptjes/goodresults/sigmoid100_0_10_1_goalsigmoid100_0_10_-1.png)

![https://deadline-driven-behavior.googlecode.com/svn/trunk/hulpscriptjes/goodresults/sigmoid_100_0_10_1_goal_100_0_10_01.png](https://deadline-driven-behavior.googlecode.com/svn/trunk/hulpscriptjes/goodresults/sigmoid_100_0_10_1_goal_100_0_10_01.png)

![https://deadline-driven-behavior.googlecode.com/svn/trunk/hulpscriptjes/goodresults/sigmoid_100_0_10_1goal01to0.png](https://deadline-driven-behavior.googlecode.com/svn/trunk/hulpscriptjes/goodresults/sigmoid_100_0_10_1goal01to0.png)

# 20-10-2014 #
I have to run a few more simulations. I think I get enough variety when I do the following (I've checked the ones I have done):
  * Goal action linear 1 to 0
    * ~~With non-goal actions linear 0 to 1~~
    * ~~Non goal-sigmoid with parameters (100,0,10,1)~~
    * ~~With Gaussian(200, 50, 1)~~
  * Goal action linear 0.1 to 0
    * ~~With non-goal actions linear 0 to 1~~
    * ~~Non goal-sigmoid with parameters (100,0,10,1)~~
    * ~~With Gaussian(200, 50, 1)~~
  * Goal action Sigmoid at nice parameter (100,0,-10,1)
    * ~~With non-goal actions linear 0 to 1~~
    * ~~Non goal-sigmoid with parameters (100,0,10,1)~~
    * ~~With Gaussian(200, 50, 1)~~
  * Goal action as Gaussian with nice parameters (0,50,1)
    * ~~With non-goal actions linear 0 to 1~~
    * ~~Non goal-sigmoid (100,0,10,1)~~
    * ~~With Gaussian(200, 50, 1)~~


I think I won't put every graph in the main thesis part, because it is so much, so a part will be put in the appendix.

I will also have to clarify why I chose these parameters.
Also, I still have to run another simulation with goal action at 0 and sigmoid with some nicer parameters.

When I have these graphs, I should start finishing up the result section. After that, add to the conclusion section and, most importantly, **DON'T LOSE SIGHT OF THE RESEARCH QUESTIONS!**

The graphs:

![https://deadline-driven-behavior.googlecode.com/svn-history/r197/trunk/hulpscriptjes/goodresults/gaussian200_50_1_goal01to0.png](https://deadline-driven-behavior.googlecode.com/svn-history/r197/trunk/hulpscriptjes/goodresults/gaussian200_50_1_goal01to0.png)

![https://deadline-driven-behavior.googlecode.com/svn-history/r197/trunk/hulpscriptjes/goodresults/gaussian200_50_1_goal1to0.png](https://deadline-driven-behavior.googlecode.com/svn-history/r197/trunk/hulpscriptjes/goodresults/gaussian200_50_1_goal1to0.png)

![https://deadline-driven-behavior.googlecode.com/svn-history/r197/trunk/hulpscriptjes/goodresults/linear0to1_goal01to0.png](https://deadline-driven-behavior.googlecode.com/svn-history/r197/trunk/hulpscriptjes/goodresults/linear0to1_goal01to0.png)

![https://deadline-driven-behavior.googlecode.com/svn-history/r197/trunk/hulpscriptjes/goodresults/linear0to1goal1to0.png](https://deadline-driven-behavior.googlecode.com/svn-history/r197/trunk/hulpscriptjes/goodresults/linear0to1goal1to0.png)

![https://deadline-driven-behavior.googlecode.com/svn-history/r197/trunk/hulpscriptjes/goodresults/sigmoid100_0_10_1_goal1to0.png](https://deadline-driven-behavior.googlecode.com/svn-history/r197/trunk/hulpscriptjes/goodresults/sigmoid100_0_10_1_goal1to0.png)


# 17-10-2014 #
I just started running the simulation with only two big areas for fastwander and slowwander and the pedestrians keep moving and don't go idle after a few steps. Now the simulation is loaded in about 2 seconds instead of a couple of minutes, so I'm definitely keeping it this way. Today I am going to run the simulation with the goal action included again.

I realize that when I put the goal action in again, there is another variable to vary. I'm gonna start with the linear function for non-goal actions and a constant for goal actions at 1 and then at 0.1.

I notice that it takes a long time for some pedestrians to reach the goal, because they are so far away from it. Maybe I should modify the estimation of how long it takes to reach the goal? I should find out where I can find that. Another option is to make the area smaller where the pedestrians walk around.

These are the new graphs I made today:

![https://deadline-driven-behavior.googlecode.com/svn/trunk/hulpscriptjes/goodresults/linear0to1goal1.png](https://deadline-driven-behavior.googlecode.com/svn/trunk/hulpscriptjes/goodresults/linear0to1goal1.png)

![https://deadline-driven-behavior.googlecode.com/svn/trunk/hulpscriptjes/goodresults/linear0to1goal01.png](https://deadline-driven-behavior.googlecode.com/svn/trunk/hulpscriptjes/goodresults/linear0to1goal01.png)

Aren't they beautiful?


# 13-10-2014 #
Today I'm writing about my new experiments and results in my [thesis](https://deadline-driven-behavior.googlecode.com/svn/trunk/report/simobs_thesis_final.pdf). This thursday I'm going to talk with my supervisor again. Maybe I can then discuss what I should do with my old experiments. My old experiments are run on old code so the system behaves a little differently now. There is valuable information in there though, because without the old experiments I would never have observed how much influence the goal probability/behavior has on the system, so I would never have decided to do the experiments without it. What's also an option is that I get rid of the old graphs and do the experiments again in the new way but with the goal behavior added again. Maybe that is the best solution.


# 8-10-2014 #
More graphs:

![https://deadline-driven-behavior.googlecode.com/svn/trunk/hulpscriptjes/goodresults/sigmoid%20xtimes10.png](https://deadline-driven-behavior.googlecode.com/svn/trunk/hulpscriptjes/goodresults/sigmoid%20xtimes10.png)

![https://deadline-driven-behavior.googlecode.com/svn/trunk/hulpscriptjes/goodresults/sigmoid%20xtimes20.png](https://deadline-driven-behavior.googlecode.com/svn/trunk/hulpscriptjes/goodresults/sigmoid%20xtimes20.png)

![https://deadline-driven-behavior.googlecode.com/svn/trunk/hulpscriptjes/goodresults/constant1.png](https://deadline-driven-behavior.googlecode.com/svn/trunk/hulpscriptjes/goodresults/constant1.png)

![https://deadline-driven-behavior.googlecode.com/svn/trunk/hulpscriptjes/goodresults/gaussian_mean200_sigma50_100_max1.png](https://deadline-driven-behavior.googlecode.com/svn/trunk/hulpscriptjes/goodresults/gaussian_mean200_sigma50_100_max1.png)


# 6-10-2014 #
Today I first plotted a plot for the sigmoid probability function with x multiplied by 100. If I had checked a plot for the sigmoid function before running the simulation I would have known this would have been way too much. There is not much influence of the sigmoid function going on here.

![https://deadline-driven-behavior.googlecode.com/svn/trunk/hulpscriptjes/goodresults/sigmoid%20xtimes100.png](https://deadline-driven-behavior.googlecode.com/svn/trunk/hulpscriptjes/goodresults/sigmoid%20xtimes100.png)

Btw, I should remember to add plots of the probability functions themselves in my thesis.


# 3-10-2014 #
Generating more graphs today. First one is this one:

![https://deadline-driven-behavior.googlecode.com/svn/trunk/hulpscriptjes/goodresults/linear0to0.5_100agents.png](https://deadline-driven-behavior.googlecode.com/svn/trunk/hulpscriptjes/goodresults/linear0to0.5_100agents.png)

Slowwander still has the preference. Thinking I should make a graph using multiple runs but I think I will do these graphs first, will be a lot of trouble to make graphs again for multiple runs, I have to think about what would be the most efficient way to do that.

Another graph:
![https://deadline-driven-behavior.googlecode.com/svn/trunk/hulpscriptjes/goodresults/linear0to0.1_100agents.png](https://deadline-driven-behavior.googlecode.com/svn/trunk/hulpscriptjes/goodresults/linear0to0.1_100agents.png)

There's not much difference with the previous graphs so I don't know if I should put them all in the thesis.

Here's the first sigmoid one.

![https://deadline-driven-behavior.googlecode.com/svn/trunk/hulpscriptjes/goodresults/sigmoidnotmultiplied_100agents.png](https://deadline-driven-behavior.googlecode.com/svn/trunk/hulpscriptjes/goodresults/sigmoidnotmultiplied_100agents.png)

Since the sigmoid is neither multiplied in the x nor y direction it has a very sudden transition.

Next time I'll continue with generating graphs for the sigmoid probability function.

# 1-10-2014 #
I'm checking the script that counts the actions at every timestep and I notice that more than 200 actions are logged for every pedestrian, even though I stopped the simulation at maybe a few steps more than 200. It seems that this is the reason that the number of idle actions is so ridiculously high. Could it be that I forgot to delete the previous log, so the new data was appended to the data of an earlier simulation?

Update:
Turns out I was using the wrong action log. I recently moved my files around to make it all work with svn again but I was still reading the action log from the old directory.

I plotted the right graph, and it is how it should be for a linear probability function that goes from 0 to 1.

![https://deadline-driven-behavior.googlecode.com/svn/trunk/hulpscriptjes/goodresults/linear0to1_100agents.png](https://deadline-driven-behavior.googlecode.com/svn/trunk/hulpscriptjes/goodresults/linear0to1_100agents.png)

Yessssssss!

It is remarkable that more pedestrians are doing slow wander than fast wander first, but slow wander decreases sooner than fast wander. I can't explain that at the moment. It is a nice effect that the slow version of wander is preferred when there is still time left.

# 29-9-2014 #
Generated a graph again.

![https://deadline-driven-behavior.googlecode.com/svn/trunk/google%20code/linear0to1.png](https://deadline-driven-behavior.googlecode.com/svn/trunk/google%20code/linear0to1.png)

This can't be right. No way around 150 pedestrians are idle the entire time. I actually know for sure, since there were only 100 pedestrians in the simulation. I will check my scripts for generating the graphs this wednesday.

# 24-9-2014 #
Managed to get the code working again. Friday I will see if I can tweak the parameters to get a nicer graph.

# 22-9-2014 #
Code is still not back to normal. I have trouble concentrating today so that does not really help. It seems that only one time an action is communicated from the Petri net side to the other side. After that, the action seems to always be null. Maybe not all the code about syncing the clocks is not the latest version? I have put breakpoints at the relevant pieces of code. Hopefully I have more concentration on Wednesday so I can fix this.

# 10-9-2014 #
I thought I put all the right files back, but now actions are only executed for one step it seems. I will try to fix this friday. Because I can't remember all the classes I have modified in the last couple of weeks I might have missed one. I'm only selectively putting back files because it is too much work to do for every file, because multiple files containing the same classes have been recoverd from my formatted hard drive and for every class I need to find out which is the latest one.

> I was also thinking, maybe I should extend Petri nets for certain behavior where you have to go somewhere (e.g.the toilet behavior) so that the pedestrian can either run or walk slowly to its destination.

# 8-9-2014 #
Still working on putting the latest version of the code back. I fixed AbstactMasonAction and Fast- and SlowWanderMasonAction. Now I have to fix the other actions and the code with which I can find if a petri net is shared or exclusive, and make sure that only shared Petri nets are disconnected when the pedestrians are finished with the behavior.

# 4-9-2014 #
Last week I accidentally formatted my hard drive, so I lost the progress of the last few weeks. Fortunately, I probably managed to recover the java code, but I still have to put them back in the right place and give them the proper file names. That is the reason that today I am going to arrange my files so that I can commit my code again to svn. At some point in time I started working with 2 copies of my files, one was checked out from svn, and the other one copied from my pc at tno (I did have my reasons though). I will work on merging these two versions so I can commit my code again.
# 29-8-2014 #
Ran the simulation with 50 pedestrians. The resulting graph is a little bit more to my liking. It is possible that the legend of the previous graph was not correct, and that could be why the results were as they are.

![https://deadline-driven-behavior.googlecode.com/svn/trunk/google%20code/11:30:56%2029-08-2014.png](https://deadline-driven-behavior.googlecode.com/svn/trunk/google%20code/11:30:56%2029-08-2014.png)

Now that I have a little bit of reassurance that the probabilities are computed correctly I have a little more confidence that I can tweak the parameters so that fast wander and slow wander decrease to 0 (but slow wander decreases earlier) next monday.


# 28-8-2014 #
Made another graph to see if the pedestrians act correctly. Unfortunately this is not the case. Interpreting the graph, I would say they act even the wrong way around, doing more slowWander as time passes, and decreasing fastWander. It could also be purely random. Tomorrow I will try to find out why this is. Maybe I should build in something to more easily follow the probabilities of the behaviors.

![https://deadline-driven-behavior.googlecode.com/svn/trunk/google%20code/12:40:06%2028-08-2014.png](https://deadline-driven-behavior.googlecode.com/svn/trunk/google%20code/12:40:06%2028-08-2014.png)

Also, I might modify slow and fast wander in such a way that they won't stray too much from the scene.

# 21-8-2014 #
I fixed the problem of the pedestrians going idle. They now remain active through the whole simulation. Now another problem that I encounter is that pedestrians now only take one step when doing slow or fast wander instead of multiple steps, before going back to their own Petri net. I notice that actions are not reinitialized when they are executed a second time. Consequently, the step counter does not go back to its initial value and decreases more and more below zero. The nicest way to fix this would be to reinitialize the actions every time a pedestrian starts one, but this will be too much hassle to do, because it goes against the current structure of the PedestrianAgent code. I will just set the steps counter of the action back at its initial value when an action is finished.

Update:
I think I now solved all issues with pedestrians remaining idle and actions that don't take long enough. Tomorrow I am going back to checking the probabilities of slow and fast wander over time and seeing if I can tweak the parameters so that there will be relaxed behavior (slow wandering) first, and more hurried behavior (fast wandering) as more time passes.

# 18-8-2014 #
It seems that the connections to various slow and fast wander Petri nets slowly disappear until maybe only the connection to the toilet situation is left. I am going to look into all the pieces of code where a Petri net is disconnected. This happens in the removeOldSituations method and transportToken in SituationManager.

I'm wondering if I should modify when a Petri net should be disconnected. Apparently it is an issue with shared situations that pedestrians get stuck in a loop when the Petri net is not disconnected when the pedestrian's token gets transported back to its original Petri net. Maybe I should only disconnect shared situations when Pedestrians are finished with them. Then the non-shared situations will only be disconnected when the Pedestrians move out of the situation area. When the probabilities are set correctly, Pedestrians should not get stuck. First, I have to find out again how I differentiate between Petri nets that are shared and ones that are only connected to one pedestrian.

Update:
I see that Datalayers are wrapped in a Situation instance. The situation instance knows what type of situation it is. I don't know yet how I can get the situation instance when I only have the datalayer but I will look at that wednesday.

# 15-8-2014 #
I still hoped that the problem of the pedestrians becoming idle was because they don't move out of their situation, or otherwise because they end up in a place with no situations. I re-enabled the drawing of the situation squares. However, this does not work properly any more so it seems. After that I decreased the distance again that slow and fast wander cross to make sure they don't move out of the main area too much. Pedestrians still remained idle after the first couple of actions.

Now I will try to find the problem by continuing on the observations I made at 11-7-2014. It seemed that the tokens didn't move back to the base pedestrian Petri net. I will find out if this still is the case and if I can find a solution.
I can't catch this mistake right now. What might be suspicious is that I tried to put a breakpoint at disconnectNets in SituationManager that only breaks when the simulation is past 60 steps. Even at step 173 this method isn't called. Maybe the pedestrians stay too much in the same place. I also noticed that it took the pedestrians a lot longer than previously to get into their idle state.
Maybe making the situations smaller might still be a solution so I'm doing that next. I notice it helps to use a different editor to modify situations.xml than the one in eclipse.

Update: I modified situations.xml. The pedestrians become idle even sooner.

# 14-8-2014 #
First I wanted to fix the problem mentioned in the previous post by making the slow and fast wander situations even smaller. However, this will make starting up the simulation slower and my text editors have trouble processing the large amount of xml that I have generated. This feels like a sign that I should try something else. I am going to modify the slow and fast wander actions so that a larger amount of space will be crossed. Preferably they have to cross at least 20 distanceunit or so, because that is how long and wide the squares are of the slow and fast wander situations. Ideally, slow and fast wander should cross the same amount of space, only the former should do it in more steps than the latter.

I'm also considering modifying slow and fast wander so that at the start of the action, a direction is chosen that the pedestrian will wander in, and then he will only diverge from that direction very minimally. Now a new direction is chosen at every step.

Update:
I've been working on modifying slow wander the way I described before, but I realize that dimensions of 20 by 20 is pretty big considering that in slow wander, a pedestrian only moves 0.6 distanceunit per step. Decreasing the size of the slow and fast wander situations will probably still be necessary. On the one hand, this is annoying. On the other hand, I realize now that it is quite difficult for a pedestrian to move out of their initial slow- and fast wander situations, so the cause of the "faulty" behavior I try to fix is pretty logical, and not due to some obscure bug.

Another update:
I modified both the slowwander and the fastwander action. Now the pedestrians move over the entire area instead of sticking mostly in one place. However, I still have the problem of the pedestrians becoming idle after about one slow and one fast wander, so the problem wasn't that the slow and fast wander situations weren't small enough. I will try to figure out what the problem is tomorrow.

# 11-8-2014 #
Took a break from my thesis for 4 weeks. Need some time to get into the code again. I did fix the logging of the Petri nets in the method disconnectNets of the SituationManager class so I can see again what the situation and pedestrian petri nets look like before and after disconnecting.

I think I might have to figure out again how I differentiate between Petri nets with multiple pedestrians and ones for only a single pedestrian. The latter type should be completely deleted after the Pedestrian has gone back to its own Petri net. Maybe I do this but I can't remember. This does not have a priority though because I think it works alright the way it is.

I see that in the course of the first 25 steps, pedestrians slowly get rid of all their connections to other Petri nets. Maybe this is because they do not move around enough to acquire new Petri nets. Maybe I have to decrease the size of the fastWander and SlowWander situations or increase the distance the pedestrians walk for every action.

# 11-7-2014 #
I'm checking out getRandomTransition() in PedestrianSimDatalayer and notice that at step 74, the only enabled transitions are the source transitions. This could mean the token does not return to the pedestrian datalayer after slow or fast wander. This is weird, because they seem to come back after the first couple of actions. When I see the pedestrians do their first slow and fast wander. The token seems to never have left the base pedestrian net, or it has returned instantly. After these first actions. The token seems permanently lost.
Hopefully I will fix this problem next monday.

# 2-7-2014 #
Fixed the issues with conflicting versions of protoc. Now I have to make sure that the situations side of the simulation does something with the new information it gets sent. It should not update any Petri nets, but simply update the clock of the current pedestrian. This should be done somewhere in the method step() in PedestrianManager or in a method that is called by step().
I find it rather scary to modify the code so much so I really hope it works. I did back it up though just before I started working on modifying the observation message.

Update:
Yesss! The timer of the multiagent simulation is now in sync with the clocks of the individual pedestrians! Next time I will check if the situations' clocks are also in sync and maybe fix that. Then I can figure out why the pedestrians become idle so soon in the simulation. It is possible that it is due to the specific set of parameters I'm using at the moment.

# 30-6-2014 #
I thought I could fix my protobuf problem by installing the latest version, but all things protobuf related are not compatible with each other because some things are still using the old version and other things the new one. I'm just realizing though that I could probably have fixed that by compiling both .proto files I made instead of just one, but the old protobuf version is installing at the moment so I guess I'll just keep using that one.

Update: I now have compiled the proto files with the latest protoc version. I still get errors about the protobuf classes though when I try to run my simulations. I don't have energy to fix this today, so it will have to wait until wednesday.

# 27-6-2014 #
I figured out why the clock is not in sync. The multiagent system only talks to the situation side when a pedestrian is done executing an action.
I have to make sure that every step, a message is being sent. If I can then revert to having a single clock for all pedestrians remains to be seen. I have to keep in mind though that if I keep using separate clocks, that I have to make sure the situations have accurate clocks as well. This could be done as proposed last time, by having a situation take on the clock of the pedestrian that enters it first, or connects with it first.

Update:
I'm trying to modify the observation message sent from the multiagent simulation to the situations and petri net side, but I have trouble using protobuf. I will fix that monday when I have more energy.

# 23-6-2014 #
I'm looking further into how I should fix that the clock does not give the correct amount of steps. I thought of two ways to fix this. The first one is to signal from the multi agent simulation to the Petri net side of the simulationwhen the clock should increase. However,all agents talk individually over sockets to the Petri net side. I did it this way so the system could be multithreaded. Because it works this way signalling that is time to for the next step in the situations is quite difficult or even impossible. Probably giving every pedestrian its own clock is a better option.
The code will get even more ugly because I'm not restructuring everything so there will be some code that doesn't really have a purpose.


I'm just realizing that situations have to have a separate clock. Maybe it can take on the clock of the first pedestrian that enters it?

For next time, I have to do the following things:
  * Make sure that situations get the clock of the first pedestrian that enters is
  * Figure out why the clocks are still not in sync with the step counter on the multiagent simulator side.


# 20-6-2014 #
Today I will try to find out why it is that when I fixed the parameters last time, the pedestrians will only execute slow and fast wander once and then remain idle.

I just discovered that the clock that keeps track of the current number of steps being taken does not work correctly. When the gui indicates that 192 steps have been taken, the clock value is only 50 steps. This might be because of the way I think I keep track of the number of steps. I remember doing that by keeping track of the pedestrians that need a transition to be fired. When I come across a pedestrian that I've seen before, the counter of the clock goes up. I will need a different way to keep track of the time.

One possible way that might fix this is to give every pedestrian its own clock, instead of making one clock that is used by every pedestrian. Because the actual time in steps is kept track of at the multi agent simulation side and the clock is on the situations and Petri net side, and they only communicate through sockets, it is quit difficult to also communicate when the multi agent simulation has increased its amount of steps.

Next time I have to dive into how exactly increasing the steps works and if my problem is solved by giving every pedestrian its own clock.

I should start looking at SituationManager line 441.

# 18-6-2014 #
I suspected I plotted the graph wrong in matlab. Turns out that when I open the csv file with the results that I want to plot, matlab automatically selects only the second, third and fourth column. GoToToilet is in the first column, but only contains zeros for the first few steps, which is I guess why matlab thinks that I don't want to use that column.

Fixed this, and the zoomed in plot now looks like this:

![http://deadline-driven-behavior.googlecode.com/svn/trunk/google%20code/fixed%20linear%20function%20plot.png](http://deadline-driven-behavior.googlecode.com/svn/trunk/google%20code/fixed%20linear%20function%20plot.png)

Which is still not what I want it to be, but it might be fixable by tweaking the parameters.

Maybe I should try out the previous run again, the results might not be as shitty as I thought they were. I also just noticed that the probability function I used was the wrong way around. The probability functions get as input how much time there is still left until the deadline. That is something that _decreases_ as time _increases_. The functions don't get the current time as input, they get _the time that is still left_ after a pedestrian has gone through certain behavior defined by the Petri-net. So, I want the probability of non-goal actions to _increase_ as the time left _increases_.

Update:
I ran the simulation with the parameters modified according to this reasoning, and now the only actions that were being taken are slow wander and idle. I will have to dive into the code again to figure out why this is.

Another update:
I discovered that I was quite confused when I "fixed" computeSituationTransitionRate in TimePlanningTools. I believe that this method is only called at the beginning of the simulation. Still, I thought that I should incorporate how much time there is still left in the computation. This is actually impossible. Now I fixed the method so that it only returns situation.getEstimatedTime(). This method computes an estimation of how much time a Petri-net will cost.

Last update:
I ran the simulation now and while it does seem to be improved in the beginning, most pedestrians remain Idle after the first few steps, except for two who are executing the gototoilet behavior. I think this might have something to do with the probabilities being zero for the other sinks for some reason, or maybe the Petri nets for the walking behavior have been disconnected. I think it is the former rather than the latter. I will look into this coming friday.


# 13-6-2014 #
Today I generated results with the parameters tweaked a little different. This time I'm using the linear function. First the pedestrians only remained idle, this was because the linear function was the wrong way around. Then I had to fix the python script that restructures the results.

Then I finally made a plot:

![http://deadline-driven-behavior.googlecode.com/svn/trunk/google%20code/linear%20function%20plot.png](http://deadline-driven-behavior.googlecode.com/svn/trunk/google%20code/linear%20function%20plot.png)

It doesn't look right at all? Why is slow wander so dominant? I zoomed in in the next plot:

![http://deadline-driven-behavior.googlecode.com/svn/trunk/google%20code/linear%20function%20plot%20zoomed%20in.png](http://deadline-driven-behavior.googlecode.com/svn/trunk/google%20code/linear%20function%20plot%20zoomed%20in.png)

I would expect fastwander and slowwander to be exactly the other way around. I will figure out why this is next week. Some possible explanations:
  * I got the legend or something else in matlab wrong
  * Likelihoods aren't computed correctly
  * Python script messes something up

I will look into that next week.


# 6-6-2014 #
I checked how the probabilities are computed. I modified the sigmoid function that takes as input how much time is left and outputs a likelihood that a certain transition will be fired.
Next time I will remove the goal transition and tweak around the parameters a little bit. Then I will plot the results from the hurried/relaxed behavior log to see if the program does what I want it to do.

Edit:
I removed the goal transition, but I think with how the parameters are now, there is not much difference between hurried and relaxed behavior. Next week I will increase the amount of pedestrians in the simulation and replace the sigmoid probability function with a linear one.

# 4-6-2014 #
I get the feeling that there are some redundant calculations in the code. I reduced the method computeSituationTransitionRate so that it only computes the time that is left to the deadline.
Friday I will check if that is the right thing to do and how this computed "rate" is used in other parts of the code. If that looks alright I will remove the goal transition in the Petri-net of the pedestrians and run the simulation while logging which actions are taken at every step. Then I can check how the ratio between hurried and relaxed behavior changes over time.

# 2-6-2014 #
I notice that when I preprocess the situations and compute how much time these behaviors will cost, in the end I take the largest amount of time computed. With this I mean that there are often multiple ways to go from the start of a Petri-net to the end (in our case, from source to sink), and when I am done computing these paths, I take the largest one to be the estimate of time that will be spent in this situation. When there are loops in the Petri-net, the result would in theory be infinity but in this implementation it is just a very large value. It is very likely that this will result in the situation never being chosen. I have to decide what kind of value I have to use now. Will I use the shortest path? Or maybe the median? For now I will use the shortest path.

Switched to longest path again. I remember why I took longest path. The list that is returned by the computedistances method includes the distances for the paths from every place in the Petri-net. The reason that some distances were so insanely long (Integer.MAX\_VALUE to be exact) was that from some places, some sink transitions are deliberately unreachable. I have removed these results from the list that is returned by computedistances.

Unfortunately, this does not solve the problem of the pedestrians not moving at all at the moment, and the sink transitions having negative weight/rate, so I will now have to look at that.

# 30-5-2014 #
I have been working on my thesis but haven't logged much because I was busy debugging and hadn't found the mistakes yet.

Todo:
I use "rate", a property that belongs to timed transitions to indicate the likelihood of a transition firing. However, as I found out a while ago, timed transitions do not really work in the implementations of petri nets that I use. I hacked around that but in the code  I still do something with rate. I calculate rate based on how much time there is left and how long a situation takes but I think I should change my formula. I will write out what I have and what I think it should be next week. Today I will look at other bugs.

# 19-5-2014 #
Maybe I have to check my Dijkstra path planning, because I don't know how it works any more. I understand Dijkstra's algorithm, but I can't really find in my code how I determine the distance between places.

I also decided that I will probably have to remove the wander action from the basic Pedestrian Petri net, because I think it might not be included in the calculations when deciding an action based on how much time there is left. I know I'm getting sidetracked, but I think it is quite essential to fix this first before I continue with my experiments.

# 16-5-2014 #
I plotted the results of the frequency of the different actions.

![https://deadline-driven-behavior.googlecode.com/svn/trunk/google%20code/first%20plot%20sigmoid%200001.png](https://deadline-driven-behavior.googlecode.com/svn/trunk/google%20code/first%20plot%20sigmoid%200001.png)

The wander action seems to dominate the plot too much to really be able to say something about the other actions. The idle behavior is not really saying much either because that is an action that is executed when the current place a pedestrian's token is in does not map to an actual action.
I expect with the current parameters that there won't be much difference anyway between hurried and relaxed behavior.

Maybe I can create a scenario where there is no goal action and see if I can make the simulation so that there will be more hurried behavior towards the end of the run and more relaxed behavior in the beginning. I will back up my code before I do that.
I plan on modifying this monday. At some point in the near future however, I will have to accept the results I have, even if they're not what I want them to be, and finish the rest of my thesis. I also have to look at the rest of my research questions and see if I can generate results that answer those.

# 14-5-2014 #
I generated the xml for defining the multiple areas for slowwander and fastwander. Even though the environment contains a new situation on every 20 by 20 square, which results in 625 new situations (the area of the environment is 500x500 in total, the unit in which the size is defined is a bit difficult), it does not slow down the system much more, because only petri nets are fired that are at that moment attached to a pedestrian. Now the pedestrians are executing slowwander and fastwander throughout the simulation, and not just at the very beginning.

Now I have to tweak the parameters of the probabilities for go-to-goal and the other actions in such a way that there is a more or less gradually growing frequency of go-to-goal actions and see how the frequency of fastwander and slow wander changes over time.

# 12-5-2014 #
Slow and fast wander are executed when I check the log, but in the gui I only see the regular wander behavior. This is because fast and slow wander are only executed at the very beginning of the simulation. This is probably because I took a precaution to prevent the pedestrians from getting stuck in a loop by disconnecting them from a Petri net when they are finished with it, even though they are still in the situation. Maybe when the parameters are tweaked right this precaution isn't necessary but I'll leave it in for now.

I will try to work around this by creating multiple smaller situations for the slow and fast wander Petri nets. Hopefully this doesn't slow down the system too much, otherwise I will have to try to remove the precaution I put in earlier and check whether the pedestrians get stuck in a loop or if this doesn't happen anymore.

For now I'm working on a script to quickly generate the xml for defining the multiple slowwander and fastwander situations. I will continue working on that wednesday when I hopefully have more concentration.


# Log for 1-5-2014 #
I created a script to count how much every action is taken at every timestep. I can't really find interesting information yet. The frequency of slow wander is not significantly different form fast wander in this configuration. I will have to see if this can be changed by tweaking the parameters or this is due to some mistake I made. I will look at that tomorrow.

# Log for 25-4-2014 #
Found the mistake. The new Petri nets looked like this:

![https://deadline-driven-behavior.googlecode.com/svn/trunk/google%20code/slowWanderSituation.png](https://deadline-driven-behavior.googlecode.com/svn/trunk/google%20code/slowWanderSituation.png)

instead of this:

![https://deadline-driven-behavior.googlecode.com/svn/trunk/google%20code/slowWanderSituation2.png](https://deadline-driven-behavior.googlecode.com/svn/trunk/google%20code/slowWanderSituation2.png)

Also found out that I'm having some svn troubles but I'm probably not going to fix that.

Unfortunately, now I've added these two new situations, the simulation runs a lot slower. Hopefully it helps if I comment out most of the printing statements.

I now have a log that contains for every step which action every pedestrian takes. I will have to make a script which counts the number of pedestrians take a certain action for every action. Probably only slowwander and fastwander are important. I have to take into account that these actions take multiple steps and that slowwander might take not the same amount of steps. At least, that is how it is supposed to go, only I think that now slowwander takes the same number of steps only doesn't get as far as fastwander, the estimate in the petri net does differ though, so I might be gaming my own system a little. I will have to figure out if this is something I will have to work on. Maybe it doesn't matter for the purpose of the graphs.

Probably won't be able to finish the script now, I'll have to continue on it monday, when I have more energy.

# Log for 23-4-2014 #
I wanted to add the _fast wander_ and _slow wander_ actions to the base pedestrian Petri net, but realized that my system maybe only estimates how long behavior takes when they are in a separate situation Petri net. So I am probably going to create two new situations for the two actions that will have the size of the entire environment.

I notice that in situations.xml there are several properties that I set for every situation. One of them is estimated time. I have to find out whether I use this or if I use the estimated time computed with Dijkstra's algorithm.

**Update:** I checked the code and it seems that Dijkstra's algorithm is used and the estimated time property is useless.

I added the slowwander and fastWander situations to situations.xml but now all the pedestrians remain idle. Something has gone wrong. I will try to find out what the problem is this friday.

# Log for 17-4-2014 #
I fixed the twopeopletoiletsituation. The pedestrians got stuck because a while ago I "fixed" the Petri net to make it more elegant. As a result of that, the Petri net had lost the guarantee that both sink transitions would always fire. It was possible that one sink transition fired twice and the other one wouldn't fire. I fixed this again.
Furthermore, in the past I added some extra places and transitions so that the Petri net would wait until the pedestrians had finished their actions before it would fire the sink transitions. In future implementations something has to be done about this in the code instead of in the Petri nets, but for now I'll keep it like this.

I also started working on some distinct hurried and relaxed behavior. For now I only modified the wander action so the pedestrians can wander both fast and slowly. I still have to incorporate these actions into the pedestrians' Petri nets. I will do that next time. Maybe I can then do an experiment focused only on these two actions and see if the ratio of fast vs. slow wandering changes over time.
Next time I will work on this.

# Log for 11-4-2014 #
In order to distinguish between hurried and relaxed behavior I will have to add running and slow walking varieties of the current actions. I will have to see if it is worth the time it takes to implement that.

Also, I notice the twopeopletoilet behavior does not work as planned. The pedestrian that waits for the other one does not wait long enough. Is this a mistake in the Petri net?  Do I use an old Petri net for this or is this because of how the actions in the multi agent simulator are implemented? Probably the second. A way to improve this would be to have the pedestrian that goes to the toilet go back to his friend. However, this will require a lot of extra coding.

On a second run, it seems that the gototoilet pedestrians get stuck in their Petri net and remain idle far after all the other pedestrians have gone to their goal.
In short, there are now a couple of things in the code that I think need to be fixed:
  * More varied behavior to distinguish between hurried and relaxed
  * Pedestrians doing twopeopletoilet behavior get stuck in petri net.
  * One pedestrian does not wait long enough for the other in the gototoilet situation or both pedestrians get stuck.

Though I can probably go on without fixing the last issue. Today I will start on debugging the twopeopletoilet behavior and I will probably continue doing that on monday.


# Log for 4-4-2014 #
Today I finished writing the code to log the pedestrians' actions at every step. Now I have to tweak the parameters so that the pedestrians walk around for a reasonable time before going to their goal, run the simulation, analyse that run and see if I can find some interesting results.

# Log for 2-4-2014 #
Today I will look at what is the best way to log the actions. In this log it is probably not possible to retrieve from which situation this action comes from, but hopefully this still gives enough information about the influence of time on the ratio of hurried behavior or actions vs relaxed behavior.
In this log I want to register the time at which the action is taken, or alternatively how much time is left until the deadline, whichever is more convenient to log. Secondly, I want the name of the action that is taken at that time. For this I will use the classname of the current IMasonAction implementation.
However, I don't know yet whether I can implement the logging of the actions in the implementations of the IMasonAction interface which seems the most conventient to me at the moment, or whether I have to do this somewhere else.

I just realize that if I keep track of which agent is performing the action, which is probably easier to find, it is not important to register the time at every log entry, because every pedestrian executes an action at every single timestep. I'm still in doubt though if this is the right way to analyze hurried or relaxed behavior but I'll see if it leads to useful graphs.

I started on writing the code for logging the actions but since I'm unable to concentrate any more I will continue this friday.

# Log for 31-3-2014 #
I forgot to say an important point in my previous log. I only just noticed that my research questions don't mention anything about emergent behavior, even though that is an important aspect of my research. I think it would be better to add a subquestion in which I say something about emergence, or is it too late to add something like that?
Since my supervisor is not available this week I will work on running a few experiments in the meantime and see if I can make a nice graph showing the effect of time on hurried and relaxed behavior.

I have to change something about how everything is logged, because at the moment, it seems that I log whenever a pedestrian enters a certain situation. This means that whenever a pedestrian executes an action that is not part of a situation, but just part of the set of basic actions every pedestrian has, this is not logged.


# Log for 28-3-2014 #
Last time I started working too early again on my thesis. A few weeks again I tried again with more success.
At the moment I'm trying to decide what experiments to use and how to display my results so that I answer my research questions. I'm considering adding another subquestion that points out the importance of emergent behavior. Probably something along the lines of:
Is it possible to have emergent behavior such as variety in hurried and relaxed behavior result from setting a certain deadline?
This question needs to be tweaked.

Maybe if I do this I can categorize actions as either hurried or relaxed, and show how time influences the frequency of actions from these two categories.
Maybe it is important to add a few simple actions or tweak a few Petri nets so that there is a hurried and relaxed version of some behaviors.

Or maybe I can rate actions on how much time they take and put that in a graph. The x axis will be time left until the deadline and the y axis will be the average number of steps the chosen actions take at that moment or interval.


---


# Log for 3-9-2013 #
Today I am going to start on my thesis again after a long time. Today I'm just doing some minor things, and read my thesis again. I am considering rewriting my code to make it less sloppy and buggy. Maybe I have to search for better Petri net software.







# Log for 16-2-2012 #
I wanted to easily make the behavior more interesting and varied so added "stand still" behavior to the whole area, but the implementation now prevents infinite loops into certain behavior by making sure a behavior cannot be done multiple times when an agent is still in a certain situation area but is this what we want for the "standing still" situation? Probably this implementation is still the best choice but it makes standing still more "artificial looking" because the areas in which the agents are able to stand still have to be manually placed.

# Experiments on 15-2-2012 #
How am I going to do the experiments? Since I vary the functions of the go-to-goal behavior at the moment, I should check the frequency of the firing of transitions to other situations.
We should count how many times a transition to a certain situation is fired at a certain timestep (measured with respect to the time of the deadline)