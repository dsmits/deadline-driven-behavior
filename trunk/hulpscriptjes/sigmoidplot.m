

t = 0:200;
xshift  = 100;
yshift = 0;
xstretch = 10;
ystretch = 1;
exponent = -1*((t-xshift)/xstretch);
numerator = 1 + exp(exponent);
ystretch ./ numerator - yshift;
y = ystretch ./ numerator -yshift;
plot(y);
plot(t,y)

%%%%