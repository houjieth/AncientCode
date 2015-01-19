function [solution, lambda] = NewtonDown1( e_lambda, e )
%Find the solution of x^2-exp(x)=0 using Newton Going Down Hill algorithm

%Start calculation
x = 1;
x_prev = 1;
while(1 == 1)
    lambda = 1;
    while(lambda >= e_lambda)
        x = x_prev - lambda * (x_prev^2-exp(x_prev)) / (2*x_prev-exp(x_prev));
        if(abs(x^2-exp(x)) < abs(x_prev^2-exp(x_prev)))
            break;
        else
            lambda = lambda/2;
        end
    end
    if(abs(x-x_prev) < e)
        break;
    else
        x_prev = x;
    end
end

solution = x;