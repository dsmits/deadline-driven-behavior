results = importdata('/home/djura/vakken/afstudeerstage/hulpscriptjes/results.csv');
%%
plot(results(1:200, :));

[hleg1, hobj1] = legend('StandStillMasonAction',	'FastWanderMasonAction',	'GoToGoalMasonAction',	'SlowWanderMasonAction',	'IdleMasonAction');
legenddimensions = get(hleg1, 'position');
%legendimensions(3) = legenddimensions(3)*10
%legendimensions(4) = legenddimensions(4)*10
%set(hleg1,'position', legenddimensions)
%set(hleg1,'position',[x0 y0 width height])


title('Plot with linear utility function from 0 to 1, and goal utility function Sigmoid(100,0,-10,1)');
%%TODO: Plot only from 1 to 200 steps or even  less

