function [value, error] = Gauss2( n )
%Calculate 4*integral[x=0 to 1 (1/(1+x^2)) dx] with multiplex Gauss formula(split
%into n pieces)

%Preparation
h = 1 / n;
a = 0;
b = 1;
x = zeros(n+1);
x_ = zeros(n);
for k = 1:(n+1)
    x(k) = a + (k-1)*h;
end
for k = 1:n
    x_(k) = a + (k-0.5)*h;
end

%Start calculation
s = 0;
for k = 0:(n-1)
    s = s + 1/(1+(x_(k+1)-h/2/sqrt(3))^2);
    s = s + 1/(1+(x_(k+1)+h/2/sqrt(3))^2);
end

value = 4*h/2*s;
error = value - pi;
    


