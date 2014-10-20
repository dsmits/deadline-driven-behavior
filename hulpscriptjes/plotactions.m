results = importdata('/home/djura/vakken/afstudeerstage/hulpscriptjes/results.csv');
%%
plot(results(1:200, :));
legend('  GoToToiletMasonAction ',  '  SlowWanderMasonAction ',  '  FastWanderMasonAction ',  '  GoToGoalMasonAction ',  '  WaitForFriendMasonAction ',  '  IdleMasonAction ');


title('Plot using Sigmoid utility function shifted 100 and stretched times 10 on the x axis. Goal utility function 1 at t=0 and 0 at t=200.')
%%TODO: Plot only from 1 to 200 steps or even  less

