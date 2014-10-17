results = importdata('/home/djura/vakken/afstudeerstage/hulpscriptjes/results.csv');
%%
plot(results(1:200, :));
legend('  StandStillMasonAction ',  '  FastWanderMasonAction ',  '  GoToGoalMasonAction ',  '  SlowWanderMasonAction ',  '  IdleMasonAction ');


title('Plot using Linear utility function with utility=0 at 0 and utility=1 at 200. Goal utility function is always 0.1.')
%%TODO: Plot only from 1 to 200 steps or even  less

