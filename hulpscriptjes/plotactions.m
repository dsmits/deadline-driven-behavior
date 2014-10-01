results = importdata('/home/djura/vakken/afstudeerstage/hulpscriptjes/results.csv');
%%
plot(results(1:200, :));
legend('GoToToiletMasonAction',	'FastWanderMasonAction',	'IdleMasonAction',	'SlowWanderMasonAction',	'WaitForFriendMasonAction');

title('Plot using the linear probability function')
%%TODO: Plot only from 1 to 200 steps or even  less

%%