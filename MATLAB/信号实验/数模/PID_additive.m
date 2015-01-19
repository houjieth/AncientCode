function [ i_out ] = PID_additive( J, J1, M, omega, a, t )
%PID_ADDITIVE 
%   INPUT VARIABLES:
%       J
%       J1
%       M
%       omega
%       a
%       t
%   OUTPUT VARIABLES:
%       i
%   INTERNAL VARIABLES:
%        alpha
%        e
%        delta_alpha
%         
%   Kp, Ki, Kd indicates three coefficients of PID

%preparation
Kp = 0.5;
Ki = 0;
Kd = 0;

%pre-processing for M
M_out = M;

%start
alpha(2) = (omega(2) - omega(1)) / t; 
alpha(3) = (omega(3) - omega(2)) / t; 
alpha(4) = (omega(4) - omega(3)) / t; 
alpha(5) = (omega(5) - omega(4)) / t; 
alpha_out(3) = alpha(3);
alpha_out(4) = alpha(4);
alpha_out(5) = alpha(5);
e(3) = alpha_out(3) - alpha(3);
e(4) = alpha_out(4) - alpha(4);
delta_e(4) = e(4) - e(3);
for n = 5 : (length(omega) - 4)
    alpha(n) = (omega(n) - omega(n-1)) / t;
    e(n) = alpha_out(n) - alpha(n);
    delta_e(n) = e(n) - e(n-1);
    delta_alpha(n) = Kp * delta_e(n) + Ki * e(n) + Kd * (delta_e(n) - delta_e(n-1));
    alpha_out(n+1) = alpha(n) + delta_alpha(n);
    i_out(n+1) = (M_out(n+1) / alpha_out(n+1) - J1) * a * alpha_out(n+1);
end

%plot diagraph
%plot(alpha,'r');
%hold on;
%plot(alpha_out,'g');
%calculate delta
