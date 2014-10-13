results = importdata('/home/djura/vakken/afstudeerstage/hulpscriptjes/results.csv');
%%
plot(results(1:200, :));
legend('  GoToToiletMasonAction ',  '  StandStillMasonAction ',  '  SlowWanderMasonAction ',  '  FastWanderMasonAction ',  '  WaitForFriendMasonAction ',  '  IdleMasonAction ');


title('Plot using Gaussian probability function with mean=200 sigma=50.')
%%TODO: Plot only from 1 to 200 steps or even  less

