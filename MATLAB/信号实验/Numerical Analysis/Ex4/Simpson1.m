function [value, error] = Simpson1( n )
%Calculate integral[x=1 to 2 (1/x) dx] with multiplex simpson formula(split
%into n pieces)

%Preparation
h = 1 / n;
a = 1;
b = 2;
x = zeros(n+1);
x_ = zeros(n);
for k = 1:(n+1)
    x(k) = a + (k-1)*h;
end
for k = 1:n
    x_(k) = a + (k-0.5)*h;
end

%Start calculation
sum = 0;
sum = sum + 1/a;
sum = sum + 1/b;

s = 0;
for k = 0:(n-1)
    s = s + 1/x_(k+1);
end
sum = sum + 4*s;

s = 0;
for k = 1:(n-1)
    s = s + 1/x(k+1);
end
sum = sum + 2*s;

value = h/6*sum;
error = value - log(2);
    


