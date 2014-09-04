results = importdata('/home/djura/vakken/afstudeerstage/hulpscriptjes/results.csv');
%%
plot(results(1:50, :));
legend('GoToToiletMasonAction',	 'FastWanderMasonAction',	 'SlowWanderMasonAction',	 'IdleMasonAction',	 'WaitForFriendMasonAction');

title('Plot using the linear probability function')
%%TODO: Plot only from 1 to 200 steps or even  less

%%