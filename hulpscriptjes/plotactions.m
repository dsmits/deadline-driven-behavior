results = importdata('/home/djura/vakken/afstudeerstage/hulpscriptjes/results.csv');
%%
plot(results(1:200, :));
legend('  StandStillMasonAction ',  '  FastWanderMasonAction ',  '  GoToGoalMasonAction ',  '  SlowWanderMasonAction ',  '  IdleMasonAction ');


title('Plot with utility function Gaussian(200, 50, 1), and goal utility function Gaussian(0,50,1)');
%%TODO: Plot only from 1 to 200 steps or even  less

