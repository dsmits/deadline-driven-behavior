mean=200;
sigma=50;
x=0:1:200;
fx=1/sqrt(2*pi)/sigma*exp(-(x-mean).^2/2/sigma/sigma);

plot(x,fx)