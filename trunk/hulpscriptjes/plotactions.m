results = importdata('/home/djura/vakken/afstudeerstage/hulpscriptjes/results.csv');
%%
plot(results(1:200, :));
legend('  StandStillMasonAction ',  '  IdleMasonAction ',  '  SlowWanderMasonAction ');


title('Plot Sigmoid utility function shifted 100, stretched times 10 on x axis, multiplied by -1 on y axis. Goal utility function Sigmoid shifted 100, stretched times 10 on x axis, multiplied by -1 on y axis.')
%%TODO: Plot only from 1 to 200 steps or even  less

