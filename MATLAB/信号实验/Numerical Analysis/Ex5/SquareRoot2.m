function x = SquareRoot
%find the solution of Ax=b, using square root algorithm

%Preparations
L = zeros(10,10);
A = zeros(10,10);
b = zeros(10,1);
for i = 1:10
    for j = 1:10
        A(i,j) = 1/(i+j-1);
    end
end
for i = 1:10
    b(i) = 1/i+1e-7;
end

%Start to calculate L
for j = 1:10
    s = 0;
    for k = 1:(j-1)
        s = s + L(j,k)^2;
    end
    L(j,j) = (A(j,j) - s)^0.5;
    for i = (j+1):10
        s = 0;
        for k = 1:(j-1)
            s = s + L(i,k)*L(j,k);
        end
        L(i,j) = (A(i,j) - s) / L(j,j);
    end
end

%Caculate x
y = L\b;
x = L'\y;
        
